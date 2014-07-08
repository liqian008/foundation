package com.bruce.baseAdmin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 全站拦截器
 * 可以重写父类 preHandle 、postHandle 、afterCompletion 方法，实现对全局页面请求的一些逻辑处理
 * @author Taven
 */
public class WebDefaultInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebDefaultInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
	        HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("=====WebDefaultInterceptor preHandle=========");
	    return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
	        HttpServletResponse response, Object handler,
	        ModelAndView modelAndView) throws Exception {
//	    System.out.println("=====WebDefaultInterceptor postHandler=========");
	    super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
	        HttpServletResponse response, Object handler, Exception ex)
	        throws Exception {
//	    System.out.println("=====WebDefaultInterceptor afterCompletion=========");
	    super.afterCompletion(request, response, handler, ex);
	}
	
}
