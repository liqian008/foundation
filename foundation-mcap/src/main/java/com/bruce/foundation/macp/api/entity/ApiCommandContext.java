/**
 * $Id: ApiCommandContext.java 118779 2012-11-26 14:05:51Z aiquan.yuan@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.entity;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.bruce.foundation.macp.constants.HttpConstants;
import com.bruce.foundation.macp.utils.McpUtils;

/**
 * @author liqian
 */
public class ApiCommandContext implements Serializable {

    private static final Log logger = LogFactory.getLog(ApiCommandContext.class);

    private static final long serialVersionUID = 1L;

    private long beginTime;

    private String methodName;

    private int userId;

    /** 客户端接入授权信息 */
    private MobileClientAppInfo mcpAppInfo;

    /** 客户端数据采集 */
    private ClientInfo clientInfo;

    /** current secret key */
    private String secretKey;

    private String ticket;

    // the input parameters map
    private Map<String, String> stringParams;

    // multipart post binary data
    private Map<String, MultipartFile> binaryParams;
    
    private McpResponse mcpResponse;

    // public ApiCommandContext(MobileClientAppInfo mcpAppInfo, User user,
    // Map<String, String> stringParams) {
    // this(mcpAppInfo, user, stringParams, null, null, null);
    // }

    public ApiCommandContext(MobileClientAppInfo mcpAppInfo, int userId,
            Map<String, String> stringParams, Map<String, MultipartFile> binaryParams,
            String ticket, String secretKey, McpResponse mcpResponse) {
        this.beginTime = System.currentTimeMillis();
        this.mcpAppInfo = mcpAppInfo;
        this.userId = userId;
        this.stringParams = stringParams;
        this.binaryParams = binaryParams;
        this.ticket = ticket;
        this.secretKey = secretKey;
        this.methodName = stringParams.get(HttpConstants.PARAM_CMD_METHOD);
        this.mcpResponse = mcpResponse;
        String strClientInfo = stringParams.get(HttpConstants.PARAM_CLIENT_INFO);
        if (logger.isDebugEnabled() && strClientInfo != null) {
            logger.debug(String.format("%s strClientInfo=%s", this.getClass().getName(),
                    strClientInfo.toString()));
        }
        if (StringUtils.isNotBlank(strClientInfo)) {
            try {
                this.clientInfo = McpUtils.toObjectSafe(strClientInfo, ClientInfo.class);
            } catch (Exception e) {
                logger.error("ApiCommandContext", e);
                String sig = stringParams.get("sig");
                logger.error(String.format("ClientInfo sig[%s] error: %s", sig, strClientInfo));
            }
        }
        if (clientInfo == null) {
            clientInfo = new ClientInfo();
        }
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public MobileClientAppInfo getMcpAppInfo() {
        return mcpAppInfo;
    }

    public void setMcpAppInfo(MobileClientAppInfo mcpAppInfo) {
        this.mcpAppInfo = mcpAppInfo;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Map<String, String> getStringParams() {
        return stringParams;
    }

    public void setStringParams(Map<String, String> stringParams) {
        this.stringParams = stringParams;
    }

    public Map<String, MultipartFile> getBinaryParams() {
        return binaryParams;
    }

    public void setBinaryParams(Map<String, MultipartFile> binaryParams) {
        this.binaryParams = binaryParams;
    }
    
    public McpResponse getMcpResponse() {
        return mcpResponse;
    }

    @Override
    public String toString() {
        return "ApiCommandContext [beginTime=" + beginTime + ", methodName=" + methodName
                + ", userId=" + userId + ", mcpAppInfo=" + mcpAppInfo + ", clientInfo="
                + clientInfo + ", secretKey=" + secretKey + ", ticket=" + ticket
                + ", stringParams=" + stringParams + ", binaryParams=" + binaryParams + "]";
    }

}
