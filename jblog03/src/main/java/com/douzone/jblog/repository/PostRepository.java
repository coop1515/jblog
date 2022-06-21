package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> findAll(Long categoryNo) {
		return sqlSession.selectList("post.findAll", categoryNo);
	}

	public void insertPost(String title, Long no, String contents) {
		Map<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("no", no);
		map.put("contents", contents);
		sqlSession.insert("post.insert", map);
	}
}
