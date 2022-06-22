package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
//		System.out.println(vo);
		sqlSession.insert("user.insert", vo);
		sqlSession.insert("blog.insert",vo);
		return sqlSession.insert("category.insert",vo) == 1;
	}

	public UserVo findByIDAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByIDAndPassword", vo);
	}

}
