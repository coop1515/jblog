package com.douzone.jblog.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;
import com.douzone.jblog.security.BlogInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
@Import({MvcConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class})
public class WebConfig implements WebMvcConfigurer {

	
	
	@Bean
	public HandlerInterceptor handlerMethod() {
		return new BlogInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerMethod()).addPathPatterns("/{id}/admin/*")
		.excludePathPatterns("/assets/**")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout");
	}
	
	
}
