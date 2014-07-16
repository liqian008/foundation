/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.remoting.RemoteLookupFailureException;

import com.bruce.designer.mcp.api.entity.ApiResult;
import com.bruce.designer.mcp.api.entity.ApiResultCode;
import com.bruce.designer.mcp.api.entity.RequestBaseContext;
import com.bruce.designer.mcp.constants.HttpConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class McpUtils {

    private static final Log logger = LogFactory.getLog(McpUtils.class);

    private static final Pattern ipPattern = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}");

    private final static ObjectMapper jsonObjectMapper = new ObjectMapper();

    public static Gson gson = new GsonBuilder().create();

    public static String ILLEGAL_CONTENT_MSG = "";

    /**
     * 远程调用时间
     * @param t
     * @param msg
     * @return
     */
    public static long rpcTimeCost(long t, Object msg) {
        return logTimeCost(t, "RPC_CALL " + msg, logger);
    }
    
    
    public static long logTimeCost(long t, Object msg) {
        return logTimeCost(t, msg, logger);
    }

    public static long logTimeCost(long t, Object msg, Log logger) {
        long et = System.currentTimeMillis();
        logger.info(msg + " timeCost:" + (et - t));
        return et;
    }

    /**
     * 生成密钥
     * 
     * @return
     */
    public static String generateSecretKey() {
        UUID uuid = UUID.randomUUID();
        long now = System.currentTimeMillis();
        return DigestUtils.md5Hex(uuid.toString() + now);
    }

    /**
     * check if response require compression
     * 
     * @param requestParamMap
     * @return
     */
    public static boolean isResponseCompressionRequired(Map<String, String> requestParamMap) {
        String dataType = requestParamMap.get(HttpConstants.PARAM_DATA_TYPE);
        return StringUtils.equalsIgnoreCase(dataType, HttpConstants.DATA_TYPE_COMPRESSION);
    }

    /**
     * 填充http参数
     * 
     * @param request
     * @return
     */
    public static Map<String, String> fillParamMap(HttpServletRequest request) {
        Map<String, String> requestParamsMap = new HashMap<String, String>();

        // 填充header
        String clientIp = McpUtils.getRemoteAddr(request);
        String userAgent = request.getHeader("user-agent");
        String language = request.getHeader("Accept-Language");
        language = checkLanguage(language);
        requestParamsMap.put(HttpConstants.CLIENT_IP, clientIp);
        requestParamsMap.put(HttpConstants.USER_AGENT, userAgent);
        requestParamsMap.put(HttpConstants.LANGUAGE, language);

        // 填充请求参数
        @SuppressWarnings("unchecked")
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String param = e.nextElement();
            String value = request.getParameter(param);
            if (logger.isDebugEnabled()) {
                logger.debug(String
                        .format("McpUtils getRequestParamMap(HttpServletRequest): param=%s => value=%s",
                                param, value));
            }

            if (value != null) {
                requestParamsMap.put(param, value);
            }
        }
        return requestParamsMap;
    }

    /**
     * 获得http输出格式
     * 
     * @param requestParamMap
     * @return
     */
    public static String getResponseFormat(Map<String, String> requestParamMap) {
        String format = requestParamMap.get(HttpConstants.PARAM_FORMAT);
        if (StringUtils.equalsIgnoreCase(format, HttpConstants.FORMAT_JSON)) {
            return HttpConstants.FORMAT_JSON;
        } else {
            return HttpConstants.DEFAULT_FORMAT;
        }
    }

    /**
     * 转化URI到方法名
     * 
     * @param requestBaseContext
     */
    public static void URI2CmdMethod(RequestBaseContext requestBaseContext) {
        String requestURI = requestBaseContext.getRequestURI();
        Map<String, String> requestParamMap = requestBaseContext.getRequestParamMap();
        String requestMethod = requestURI;
        if (requestMethod.matches("(/\\w+){2,5}")) {
            if (requestMethod.startsWith("/api/")) {
                requestMethod = requestMethod.substring(5);
            } else {
                requestMethod = requestMethod.substring(1);
            }
            String method = requestMethod.replace('/', '.');
            requestParamMap.put(HttpConstants.PARAM_CMD_METHOD, method);
            if (logger.isDebugEnabled()) {
                logger.debug(String.format(
                        "McpUtils requestParamMapFix: requestURI=%s => method=%s",
                        requestURI, method));
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format(
                        "McpUtils requestParamMapFix not rest: requestURI=%s", requestURI));
            }
        }
    }

    /**
     * 左匹配，右边以*结尾代表模糊匹配
     * 
     * @param srcMethodName
     * @param wildcardMethodName
     * @return
     */
    public static boolean leftMatch(String srcMethodName, String wildcardMethodName) {
        if (srcMethodName == null && wildcardMethodName == null) {
            return true;// 全是null，确实匹配。
        }
        if (srcMethodName == null || wildcardMethodName == null) {
            return false;// 有一个是null，不匹配。
        }
        if (srcMethodName.length() == 0 && wildcardMethodName.length() == 0) {
            return true;// 全是空串，确实匹配。
        }
        if (wildcardMethodName.length() == 0) {
            return false;// 匹配串是空串，不匹配。
        }
        if (wildcardMethodName.equals("*")) {
            return true;// 全匹配
        }
        if (wildcardMethodName.endsWith("*")) {
            wildcardMethodName = wildcardMethodName.substring(0, wildcardMethodName.length() - 1);
            return srcMethodName.startsWith(wildcardMethodName);
        }
        return srcMethodName.equalsIgnoreCase(wildcardMethodName);
    }

    /**
     * 用jackson的安全方法转换
     * 
     * @param <T>
     * @param json
     * @param t
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws Exception
     */
    public static <T> T toObjectSafe(String json, Class<T> t) throws JsonParseException,
            JsonMappingException, IOException {
        if (json == null || json.length() == 0) {
            return null;
        }
        return jsonObjectMapper.readValue(getSafeJson(json), t);
    }

    /**
     * 取安全的Json字符串
     * 
     * @param json
     * @return
     */
    public static String getSafeJson(String json) {
        if (json == null || json.length() == 0) {
            return json;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c < 32) {
                continue;
            }
            if (c == '\r' || c == '\n') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 对http请求参数作字典排序，拼接字符串
     * 
     * @param paramMap
     * @param sigParamKey
     * @return
     */
    public final static String generateNormalizedString(Map<String, String> paramMap) {

        Set<String> params = paramMap.keySet();
        List<String> sortedParams = new ArrayList<String>(params);
        Collections.sort(sortedParams);
        StringBuilder sb = new StringBuilder();
        for (String paramKey : sortedParams) {
            if (!needEncryptedForSig(paramKey)) {
                continue; //
            }
            // 参数值只取前5000个字符
            sb.append(paramKey).append('=')
                    .append(StringUtils.substring(paramMap.get(paramKey), 0, 5000));
        }
        return sb.toString();
    }

    private static boolean needEncryptedForSig(String paramKey) {
        for (String notEncryptedParam : HttpConstants.NOT_ENCRYPTED_PARAMS) {
            if (paramKey.equals(notEncryptedParam)) {
                return false;
            }
        }
        return true;
    }

    /**
     * get the md5 signature
     * 
     * @param normalizedString
     * @param secretKey
     * @return
     */
    public final static String generateSignature(String normalizedString, String secretKey) {
        return DigestUtils.md5Hex(normalizedString + secretKey).toLowerCase();
    }

    /**
     * 取客户端的真实IP，考虑了反向代理等因素的干扰
     * 
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {

        if (logger.isDebugEnabled()) {
            logger.debug(String.format(
                    "McpUtils request=%s",
                    new StringBuffer().append("X-Forwarded-For:")
                            .append(request.getHeader("X-Forwarded-For"))
                            .append("\tProxy-Client-IP:")
                            .append(request.getHeader("Proxy-Client-IP"))
                            .append("\t:WL-Proxy-Client-IP:")
                            .append(request.getHeader("WL-Proxy-Client-IP"))
                            .append("\tRemoteAddr:").append(request.getRemoteAddr()).toString()));
        }

        String ip;
        @SuppressWarnings("unchecked")
        Enumeration<String> xffs = request.getHeaders("X-Forwarded-For");
        if (xffs.hasMoreElements()) {
            String xff = xffs.nextElement();
            ip = resolveClientIPFromXFF(xff);
            if (isValidIP(ip)) {
                if (logger.isInfoEnabled()) {
                    logger.info("X-Forwarded-For" + ip);
                }
                return ip;
            }
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (isValidIP(ip)) {
            if (logger.isInfoEnabled()) {
                logger.info("Proxy-Client-IP" + ip);
            }
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValidIP(ip)) {
            if (logger.isInfoEnabled()) {
                logger.info("WL-Proxy-Client-IP" + ip);
            }
            return ip;
        }
        if (logger.isInfoEnabled()) {
            logger.info("None-Proxy-Client-IP" + ip);
        }
        return request.getRemoteAddr();
    }

    /**
     * 从X-Forwarded-For头部中获取客户端的真实IP。
     * X-Forwarded-For并不是RFC定义的标准HTTP请求Header
     * ，可以参考http://en.wikipedia.org/wiki/X-Forwarded-For
     * 
     * @param xff X-Forwarded-For头部的值
     * @return 如果能够解析到client IP，则返回表示该IP的字符串，否则返回null
     */
    private static String resolveClientIPFromXFF(String xff) {
        if (xff == null || xff.length() == 0) {
            return null;
        }
        String[] ss = xff.split(",");
        for (int i = ss.length - 1; i >= 0; i--) {// x-forward-for链反向遍历
            String ip = ss[i].trim();
            if (isValidIP(ip) && !isNativeIP(ip)) { // 判断ip是否合法，是否是公司机房ip
                return ip;
            }
        }

        // 如果反向遍历没有找到格式正确的外网IP，那就正向遍历找到第一个格式合法的IP
        for (int i = 0; i < ss.length; i++) {
            String ip = ss[i].trim();
            if (isValidIP(ip)) {
                return ip;
            }
        }
        return null;
    }

    /**
     * 是否公司内部IP
     * 
     * @param ip
     * @return
     */
    private static boolean isNativeIP(String ip) {
        // int index = ip.lastIndexOf('.');
        // String ipPattern = ip.substring(0, index) + ".*";
        // return NativeIpWhiteList.getInstance().isNativeIp(ipPattern);
        // // TODO: 是否公司内部IP
        return false;
    }

    private static boolean isValidIP(String ip) {
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return false;
        }
        return ipPattern.matcher(ip).matches();
    }

    public static String checkLanguage(String lang) {
        Locale rtLang = HttpConstants.DEFAULT_LANGUAGE;
        if (StringUtils.isEmpty(lang)) {
            return rtLang.toString();
        }
        // lang = lang.replace("-", "_");
        if (lang.toLowerCase().contains(HttpConstants.DEFAULT_LANGUAGE.getLanguage().toLowerCase())) {
            return rtLang.toString();
        }
        for (Locale loc : Locale.getAvailableLocales()) {
            // if
            // (lang.toLowerCase().contains(loc.toLanguageTag().toLowerCase()))
            // {
            // rtLang = loc;
            // break;
            // }
            if (lang.toLowerCase().contains(loc.getLanguage().toLowerCase())) {
                rtLang = loc;
                break;
            }
            if (lang.toLowerCase().contains(loc.getCountry().toLowerCase())) {
                rtLang = loc;
                break;
            }
        }

        return rtLang.toString();
    }

    /**
     * 为了兼容恶心的antispam文案自定义，破坏了和谐和统一。
     * 
     * @param e
     * @return
     */
    public static ApiResult handleExpWithMsg(Exception e) {
        int code = handleExp(e);
//        if (code == ApiResultCode.E_BIZ_RPC_ILLEGAL_CONTENT) {
//            return new ApiResult(code, ILLEGAL_CONTENT_MSG);
//        }
        return new ApiResult(code);
    }
    
    public static int handleExp(Exception e) {
        if (e == null) {
            logger.error("[Exception]", e);
            return ApiResultCode.E_SYS_UNKNOWN;
        } else if (e instanceof RemoteConnectFailureException) {
            logger.error("[Exception]", e);
            return ApiResultCode.E_SYS_RPC_ERROR;
        } else if (e instanceof RemoteLookupFailureException) {
            logger.error("[Exception]", e);
            return ApiResultCode.E_SYS_RPC_ERROR;
        } else {
            logger.error("[Exception]", e);
            return ApiResultCode.E_SYS_UNKNOWN;
        }
    }
}
