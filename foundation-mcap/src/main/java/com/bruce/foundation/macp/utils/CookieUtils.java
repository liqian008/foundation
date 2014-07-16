/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * @author liqian
 * 
 */
public class CookieUtils {

    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(key)) {
                return cookies[i].getValue();
            }
        }

        return null;
    }

    public static void saveCookie(HttpServletResponse response, String key, String value,
            String domain) {
        saveCookie(response, key, value, -1, "/", domain);
    }

    public static void saveCookie(HttpServletResponse response, String key, String value,
            String path, String domain) {
        saveCookie(response, key, value, -1, path, domain);
    }

    public static void saveCookie(HttpServletResponse response, String key, String value,
            int second, String path, String domain) {
        value = StringUtils.remove(value, '\n');
        value = StringUtils.remove(value, '\r');
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse response, String key, int second,
            String path, String domain) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }
}
