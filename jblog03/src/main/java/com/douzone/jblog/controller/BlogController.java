package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pahtNo2") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
			}
		else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
//		categoryService.getCategories(id, categoryNo); // ""url로 들어오면 categoryNo가 0이면 id를 통해 찾아야함.
		return null;
	}
	
	@RequestMapping("/id")
	public String adminBasic(@PathVariable("id") String id) {
		return null;
	}
}
