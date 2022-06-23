package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(BlogVo vo) {
		return sqlSession.update("blog.update",vo) == 1;
	}

	public BlogVo find(String id) {
		return sqlSession.selectOne("blog.find",id);
	}
}
