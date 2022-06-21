package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@PathVariable("pahtNo2") Optional<Long> pathNo1,
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
		List<CategoryVo> list = categoryService.getCategories(id, categoryNo); // ""url로 들어오면 categoryNo가 0이면 id를 통해 찾아야함.
		model.addAttribute("categorylist", list);
		List<PostVo> postlist = postService.getPostList();
		model.addAttribute("postlist", postlist);
//		System.out.println(postlist.get(1));
		model.addAttribute("postfirst", postlist.get(1));
		return "admin/blog-main";
	}
	
	@Auth
	@RequestMapping("/modify")
	public String modify(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {
//		System.out.println(authUser.getId()+ " " + id);
		if(!authUser.getId().equals(id)) {
		return null;
		}
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		return "admin/basic";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(
		BlogVo vo,
		@RequestParam("file") MultipartFile multipartFile, Model model) {
		String url  = fileuploadService.restore(multipartFile);
		vo.setLogo(url);
//		System.out.println(url);
		blogService.updateBlog(vo);
		System.out.println(vo);
		model.addAttribute("blog", vo);
		
		return "redirect:/{id}/modify";
	}
	
	@Auth
	@RequestMapping("/write")
	public String set(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {
//		System.out.println(authUser.getId()+ " " + id);
		if(!authUser.getId().equals(id)) {
		return null;
		}
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		return "admin/write";
	}
	
	@Auth
	@RequestMapping("/category")
	public String category(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {
//		System.out.println(authUser.getId()+ " " + id);
		if(!authUser.getId().equals(id)) {
		return null;
		}
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		return "admin/category";
	}
}
