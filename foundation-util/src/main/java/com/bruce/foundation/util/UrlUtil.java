package com.bruce.foundation.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Comments for UrlUtil.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-21 下午03:38:33
 */
public class UrlUtil {

	private static final Log logger = LogFactory.getLog(UrlUtil.class);

	/**
	 * join url
	 * 
	 * @param origURL
	 * @param appendUrl
	 * @return
	 */
	public static String joinUrl(String origURL, String appendUrl) {
		if (origURL != null && origURL.length() > 0) {
			if (origURL.indexOf("?") == -1) {
				return origURL + "?" + appendUrl;
			} else {
				return origURL + "&" + appendUrl;
			}
		} else {
			return "";
		}
	}

	public static String joinUrlForHyperLink(String origURL, String appendUrl) {
		if (origURL != null && origURL.length() > 0) {
			if (origURL.indexOf("?") == -1) {
				return origURL + "?" + appendUrl;
			} else {
				return origURL + "&amp;" + appendUrl;
			}
		} else {
			return "";
		}
	}

	/**
	 * 判断refer中是否含有问号
	 * */
	public static boolean isContainQuestionMark(String url) {
		if (!StringUtils.isEmpty(url)) {
			return url.contains("?");
		}
		return false;
	}

	/**
	 * 将请求参数转换为字符串
	 * */
	public static String getParamters(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Enumeration<String> emu = (Enumeration<String>) request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		while (emu.hasMoreElements()) {
			String str = emu.nextElement();
			sb.append(str);
			sb.append("=");
			String temp = request.getParameter(str);
			try {
				temp = URLEncoder.encode(temp, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("", e); //$NON-NLS-1$
			}
			sb.append(temp);
			if (emu.hasMoreElements()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 给URL添加参数
	 * 
	 * @param url
	 * @param key
	 * @param value
	 * @return
	 */
	public static String addParameter(String url, String key, String value) {
		String param = getUrlParamString(key, value);
		return joinUrl(url, param);
	}

	public static String getUrlParamString(String key, String value) {
		StringBuffer sb = new StringBuffer();
		sb.append(key);
		sb.append("=");

		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("", e); //$NON-NLS-1$
		}
		sb.append(value);
		return sb.toString();
	}

	/**
	 * 将url中的某些参数去掉
	 * */
	public static String removeParameter(String url, String param) {
		if (url != null) {
			String regx = "(&" + param + "=[\\%0-9A-Za-z-_]*)|(" + param + "=[\\%0-9A-Za-z-_]*&)";
			Pattern p = Pattern.compile(regx);
			Matcher m = p.matcher(url);
			return m.replaceAll("");
		}
		return url;
	}

	public static String getRefererUrl(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (StringUtils.isBlank(referer)) {
			return "";
		}
		return referer;
	}

	public static String getRequestUrl(HttpServletRequest request) {
		String srcUrl = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (queryString != null) {
			srcUrl = joinUrl(srcUrl, queryString);
		}
		return srcUrl;
	}

	public static String getFullUrl(String path) {
//		return ConstFront.CONTEXT_PATH + path;
		return "";
	}

}
