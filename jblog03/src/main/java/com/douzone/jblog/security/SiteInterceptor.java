package com.douzone.jblog.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

public class SiteInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService siteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		ServletContext sc = request.getServletContext();
//		BlogVo vo = siteService.getSite();
//		if(vo == null) {
//			return false;
//		}
//		
//		sc.setAttribute("site", vo);
		
		return true;
	}
}
