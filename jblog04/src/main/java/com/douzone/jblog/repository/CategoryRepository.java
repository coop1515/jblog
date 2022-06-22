package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findCategory(String id) {
		return sqlSession.selectList("category.findCategory",id);
		
	}

	public void categoryinsert(String name, String desc, String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("desc", desc);
		map.put("name", name);
		map.put("id", id);
		sqlSession.insert("category.categoryinsert",map);		
	}

	public Long lastCategory(String id) {
		return sqlSession.selectOne("category.lastCategory",id);
		
	}

	public Long getNo(String id, String category) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("category", category);
		return sqlSession.selectOne("category.getNo",map);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
		
	}
}
