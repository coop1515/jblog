package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository; 
	
	public List<PostVo> getPostList(Long categoryNo) {
		return postRepository.findAll(categoryNo);
	}

	public void insertPost(String title, Long no, String contents) {
		postRepository.insertPost(title, no, contents);
		
	}

	public PostVo postOne(Long categoryNo, Long postNo) {
		return postRepository.findOne(categoryNo, postNo);
	}

}
