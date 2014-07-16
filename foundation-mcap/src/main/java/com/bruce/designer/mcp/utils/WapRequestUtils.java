/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author liqian
 * 
 */
public class WapRequestUtils {

    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(WapRequestUtils.class);

    public static final Pattern IP_PATTERN = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}");

    /**
     * 解析link，生成wap可用的link
     * 
     * @param request
     * @param link
     * @param defaultLink link为空时的默认取值
     * @return
     */
    public static String parseLink(HttpServletRequest request, String link, String defaultLink) {
        if (StringUtils.isEmpty(link)) {
            link = defaultLink;
        }

        try {
            if (link.indexOf("?") == -1) {
                link = link.concat("?");
            } else {
                link = link.concat("&");
            }

            String sid = (String) request.getAttribute("sid");
            if (sid != null && sid.length() > 0) {
                link = link.concat("sid=").concat(sid);
            }

            return link.concat("&" + WapRandomUtils.getRandomString(9));
        } catch (Exception e) {
            logger.error("parseLink(HttpServletRequest, String, String)", e); //$NON-NLS-1$
        }
        return link;
    }

    /**
     * 返回request里面的via
     * 
     * @param request
     * @return
     */
    public static String getViaFromRequest(HttpServletRequest request) {
        return (String) request.getAttribute("via");
    }

    /**
     * 判断用于是否用cmwap方式上网
     * 
     * @param request
     * @return
     */
    public static boolean isCMWap(HttpServletRequest request) {
        String via = request.getHeader("via");
        return StringUtils.isNotEmpty(via);
    }

    /**
     * 判断用于是否用ctwap方式上网
     * 
     * @param request
     * @return
     */
    public static boolean isCTWap(HttpServletRequest request) {
        String phno = request.getHeader("x-up-calling-line-id");
        return StringUtils.isNotEmpty(phno);
    }

    /**
     * 判断用于是否用ctwap方式上网（通过手机号码）
     * 
     * @param request
     * @return
     */
    public static boolean isCTWapByNo(HttpServletRequest request) {
        String no = getCTWapNumber(request).substring(0, 3);
        if ("133".equals(no) || "153".equals(no) || "189".equals(no) || "180".equals(no)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 取得电信上网手机号码
     * 
     * @param request
     * @return
     */
    public static String getCTWapNumber(HttpServletRequest request) {
        String phno = StringUtils.defaultString(request.getHeader("x-up-calling-line-id"), "");
        if (phno.length() > 3) {
            return phno;
        } else {
            return "errornumber";
        }

    }

    /**
     * 此方法只会返回sid=xxxx, 不包含 & 符。
     * 
     * @param request
     * @return
     */
    public static String getAuthString(HttpServletRequest request) {
        String sid = (String) request.getAttribute("sid");
        if (StringUtils.isNotEmpty(sid)) {
            sid = "sid=".concat((String) request.getAttribute("sid"));
        } else {
            sid = "";
        }

        return sid;
    }

    /**
     * 返回ssid
     * 
     * @param request
     * @return
     */
    public static String getSsid(HttpServletRequest request) {
        String ssid = (String) request.getAttribute("ssid");
        if (StringUtils.isNotEmpty(ssid)) {
            return ssid;
        }
        return "";
    }

    /**
     * 返回sid
     * 
     * @param request
     * @return
     */
    public static String getSid(HttpServletRequest request) {
        String sid = StringUtils.defaultString((String) request.getAttribute("sid"));

        return sid;
    }

    /**
     * 此方法的&符是不经过转码的
     * 
     * @param request
     * @return
     */
    public static String makeAuthString(HttpServletRequest request) {
        try {
            String sid = (String) request.getAttribute("sid");
            if (StringUtils.isNotEmpty(sid)) {
                sid = "&sid=".concat((String) request.getAttribute("sid"));
            } else {
                sid = "";
            }

            return sid.concat("&" + WapRandomUtils.getRandomString(6));
        } catch (Exception e) {
            logger.error("makeAuthString(HttpServletRequest)", e); //$NON-NLS-1$
        }
        return "".concat("&" + WapRandomUtils.getRandomString(6));
    }

    /**
     * 把返回信息和返回的错误追加到querystring
     * 
     * @param request
     * @return
     */
    public static String makeRetMsgString(HttpServletRequest request, String msgAttr, String errAttr) {

        String retmsg = (String) request.getAttribute(msgAttr);
        if (retmsg != null) {
            try {
                retmsg = URLEncoder.encode(retmsg, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("makeRetMsgString(HttpServletRequest, String, String)", e); //$NON-NLS-1$
            }
            return "&" + msgAttr + "=" + retmsg;
        }

        String reterr = (String) request.getAttribute(errAttr);
        if (reterr != null) {
            try {
                reterr = URLEncoder.encode(reterr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("makeRetMsgString(HttpServletRequest, String, String)", e); //$NON-NLS-1$
            }
            return "&" + errAttr + "=" + reterr;
        }
        return "";
    }

    // /**
    // * get from
    // *
    // * @param request
    // * @param response
    // * @return
    // */
    // public static String getFrom(HttpServletRequest request,
    // HttpServletResponse response) {
    // String from = request.getParameter("from");
    // request.setAttribute("from", from);
    // if (StringUtils.isNotEmpty(from)) {
    // CookieUtils.saveCookie(response, "from", from, 60 * 60, "/",
    // "." + DomainDef.getDomainRoot());
    // }
    // return from;
    // }

    /**
     * is uc web
     * 
     * @param from
     * @return
     */
    public static boolean isUcweb(String from) {
        return (from != null && from.equalsIgnoreCase("ucweb"));
    }

    /**
     * get user agent
     * 
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    /**
     * 判断是否合法ip格式
     * 
     * @param ip
     * @return
     */
    public static boolean isValidIP(String ip) {
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            return false;
        }
        return IP_PATTERN.matcher(ip).matches();
    }

    /**
     * 获得X-Forwarded-For中的ip数据
     * 
     * @param request
     * @return
     */
    public static String getIpFromXForwardedFor(HttpServletRequest request) {
        String ip = null;
        // 2011-03-14 by Marshal 移除警告： Enumeration<String> => Enumeration<?>
        Enumeration<?> xffe = request.getHeaders("X-Forwarded-For");
        while (xffe.hasMoreElements()) {
            String xffIp = (String) xffe.nextElement();
            if (isValidIP(xffIp) && !xffIp.startsWith("10.")) { // 过滤掉10.开头的IP
                ip = xffIp;
            }
            // 对opera mini 4.2.1 的支持
            if (xffIp.indexOf(',') != -1) {
                String[] ips = StringUtils.split(xffIp, ",\n\r ");
                if (ips.length > 1) {
                    ip = ips[0];
                    if (isValidIP(ip) && !ip.startsWith("10.")) {
                        return ip;
                    }
                }
            }
        }

        return ip;
    }

    /**
     * 取F5之前的第一个IP，有可能是代理服务器的IP，但一定是真实的。
     * 
     * @author Marshal(shuai.ma@opi-corp.com)
     * @param request
     * @return
     */
    public static String getFirstIpBeforeF5(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String strIps = request.getHeader("X-Forwarded-For");
        if (strIps == null) {
            return ip;
        }
        String[] ips = strIps.split("[,\\s]+");
        // 从后往前取，找到一个非内容的IP，即：F5之前的第一个
        for (int i = ips.length - 1; i >= 0; i--) {
            String xffIp = ips[i];
            if (WapRequestUtils.isValidIP(xffIp) && !xffIp.startsWith("10.")) { // 过滤掉10.开头的IP
                ip = xffIp;
                return ip;
            }
        }
        // 如果全是内网IP，取第一个
        if (ips.length > 0 && WapRequestUtils.isValidIP(ips[0])) {
            return ips[0];
        }
        return ip;
    }
}
