/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.entity;

import java.util.Map;

/**
 * 请求相关的基础数据
 * 
 * @author liqian
 * 
 */
public class RequestBaseContext implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;

    private long time;

    private String httpMethod;

    private String requestURI;

    private Map<String, String> requestParamMap;

    private MobileClientAppInfo appInfo;

    // private AccessToken accessToken;

    private String ticket;

    private String secretKey;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public MobileClientAppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(MobileClientAppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public Map<String, String> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, String> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "RequestBaseContext [userId=" + userId + ", time=" + time + ", httpMethod="
                + httpMethod + ", requestURI=" + requestURI + ", requestParamMap="
                + requestParamMap + ", appInfo=" + appInfo + ", ticket=" + ticket + ", secretKey="
                + secretKey + "]";
    }

}
