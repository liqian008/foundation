/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public final class RequestUtil {
	
	public static final String JSON_SUFFIX = ".json";
	

	public static String getRemoteIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
     * 是否为post请求
     * @param request
     * @return
     */
    public static boolean isPost(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("post", request.getMethod());
    }

    /**
     * 是否为get请求
     * @param request
     * @return
     */
    public static boolean isGet(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("get", request.getMethod());
    }
    
    /**
     * 是否为json请求
     * @param request
     * @return
     */
    public static boolean isJsonRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return StringUtils.endsWith(uri, JSON_SUFFIX);
    }
    
}
