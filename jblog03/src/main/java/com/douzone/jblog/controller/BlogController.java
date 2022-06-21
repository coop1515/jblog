package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
			}
		else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog",vo);
//		System.out.println(vo);
		List<CategoryVo> list = categoryService.getCategories(id);
		model.addAttribute("categorylist", list);
//		System.out.println(categoryNo);
		if(categoryNo == 0) {
			categoryNo = categoryService.lastCategory(id);
//			System.out.println(categoryNo);
		}
		List<PostVo> postlist = postService.getPostList(categoryNo);
//		System.out.println(postlist);
		model.addAttribute("postlist", postlist);
//		System.out.println(postlist.get(1));
		if(!postlist.isEmpty()) {
			model.addAttribute("postfirst", postlist.get(0));
			
		}
		return "admin/blog-main";
	}
	
	@Auth
	@RequestMapping("/admin/modify")
	public String modify(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {

		if(!authUser.getId().equals(id)) {
		return null;
		}
		BlogVo vo = blogService.getBlog(id);

		return "admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/update")
	public String update(
		BlogVo vo,
		@RequestParam("file") MultipartFile multipartFile, Model model) {
		String url  = fileuploadService.restore(multipartFile);
		vo.setLogo(url);

		blogService.updateBlog(vo);

		
		return "redirect:/{id}/admin/modify";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String write(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model
			) {
		if(!authUser.getId().equals(id)) {
		return null;
		}
		List<CategoryVo> list = categoryService.getCategories(id);
		model.addAttribute("list", list);
		return "admin/write";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model,
			String title, String category, String contents) {
		if(!authUser.getId().equals(id)) {
		return null;
		}
		List<CategoryVo> list = categoryService.getCategories(id);
		model.addAttribute("list", list);
		Long no = categoryService.getNo(id,category);
		postService.insertPost(title, no, contents);
		return "admin/write";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String category(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {
		if(!authUser.getId().equals(id)) {
		return null;
		}
		List<CategoryVo> list = categoryService.getCategories(id);
		model.addAttribute("list", list);
		return "admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model
			,String name, String desc) {

		if(!authUser.getId().equals(id)) {
		return null;
		}
		categoryService.categoryinsert(name, desc, id);
		return "redirect:/{id}/admin/category";
	}
}
