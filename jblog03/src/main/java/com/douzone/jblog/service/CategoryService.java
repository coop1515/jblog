package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getCategories(String id) {
		return categoryRepository.findCategory(id);
	}

	public void categoryinsert(String name, String desc, String id) {
		categoryRepository.categoryinsert(name,desc, id);
		
	}

	public Long lastCategory(String id) {
		return categoryRepository.lastCategory(id);
		
	}

	public Long getNo(String id, String category) {
		return categoryRepository.getNo(id,category);
	}

}
