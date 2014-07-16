/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.web.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.bruce.designer.mcp.api.command.ApiCommand;
import com.bruce.designer.mcp.api.entity.ApiCommandContext;
import com.bruce.designer.mcp.api.entity.ApiResult;
import com.bruce.designer.mcp.api.entity.ApiResultCode;
import com.bruce.designer.mcp.api.entity.McpResponse;
import com.bruce.designer.mcp.api.entity.MobileClientAppInfo;
import com.bruce.designer.mcp.api.entity.RequestBaseContext;
import com.bruce.designer.mcp.api.service.CommandLookupService;
import com.bruce.designer.mcp.api.service.MobileClientAppService;
import com.bruce.designer.mcp.constants.HttpConstants;
import com.bruce.designer.mcp.passport.entity.UserPassport;
import com.bruce.designer.mcp.passport.service.PassportService;
import com.bruce.designer.mcp.utils.McpUtils;

/**
 * command控制器
 * 
 * @author liqian
 * 
 */
public class CommandController extends AbstractController implements InitializingBean {

    private static final Log logger = LogFactory.getLog(CommandController.class);

    private static final Log httpAccessLogger = LogFactory.getLog("mcp_http_access_log");

    private MobileClientAppService mobileClientAppService;

    private PassportService passportService;

    // TODO: sigCache
//    private ICacheService sigCache;

    private CommandLookupService commandLookupService;

    private DelegatingMessageSource messageSource;

    protected int sigCacheTimeout = 5;// default is 5s

    private final String httpAccessLogFormat = "%s|%s|%s|%s|%s|%s|%s|%s|%s";

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        long curTime = System.currentTimeMillis();
        int userIdLog = 0;
        String clientIpLog = "";
        String userAgentLog = "";
        String methodNameLog = "";
        McpResponse mcpResponse = new McpResponse(response);
        try {
            // log统计

            // 构造参数
            RequestBaseContext requestBaseContext = new RequestBaseContext();
            requestBaseContext.setTime(curTime);
            requestBaseContext.setHttpMethod(request.getMethod().toUpperCase());
            requestBaseContext.setRequestURI(request.getRequestURI());
            //获取请求参数
            Map<String, String> requestParamMap = McpUtils.fillParamMap(request);
            clientIpLog = requestParamMap.get(HttpConstants.CLIENT_IP);
            userAgentLog = requestParamMap.get(HttpConstants.USER_AGENT);
            requestBaseContext.setRequestParamMap(requestParamMap);
            //获取response格式，默认为json
            String format = McpUtils.getResponseFormat(requestParamMap);
            //获取是否为否gzip方式
            boolean requireCompression = McpUtils.isResponseCompressionRequired(requestParamMap);
            mcpResponse.setFormat(format);
            mcpResponse.setRequireCompression(requireCompression);
            mcpResponse.setLanguage(requestParamMap.get(HttpConstants.LANGUAGE));
            mcpResponse.setMessageSource(messageSource);

            // 校验平台级参数的合法性
            if (!this.validateBaseRequiredParams(requestBaseContext, mcpResponse)) {
                return null;
            }

            // 防重发
            if (!this.validateRequestFrequency(requestBaseContext, mcpResponse)) {
                return null;
            }

            // 解析uri到method名字
            McpUtils.URI2CmdMethod(requestBaseContext);
            methodNameLog = requestBaseContext.getRequestParamMap().get(
                    HttpConstants.PARAM_CMD_METHOD);
            String methodValue = requestParamMap.get(HttpConstants.PARAM_CMD_METHOD);
            if (StringUtils.isEmpty(methodValue)) {
                // 没有方法名
                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_UNKNOWN_METHOD));
                return null;
            }

            // 获得对应的command
            ApiCommand apiCommand = commandLookupService.lookupApiCommand(methodValue);
            if (apiCommand == null) {
                // apiCommand is unknown
                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_UNKNOWN_METHOD));
                return null;
            }

            // 是否需要登录
            userIdLog = requestBaseContext.getUserId();
            if (requestBaseContext.getUserId() == 0
                    && this.commandLookupService.isNeedLogin(methodValue)) {
                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_TICKET));
                return null;
            }

            // 权限校验
            // TODO：如果需要登录，对user用户身份的检查，例如是否封禁等等
            // TODO：antispam的检查
            // TODO：流量控制
            // 检查客户端app是否对这个方法有权限
            if (!mobileClientAppService.isAllowedApiMethod(requestBaseContext.getAppInfo()
                    .getAppId(), methodValue)) {
                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_PERMISSION_DENY));
                return null;
            }

            // 封装command参数
            Map<String, MultipartFile> binaryParams = null;
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                binaryParams = multipartRequest.getFileMap();
            }
            ApiCommandContext context = new ApiCommandContext(requestBaseContext.getAppInfo(),
                    requestBaseContext.getUserId(), requestBaseContext.getRequestParamMap(),
                    binaryParams, requestBaseContext.getTicket(), requestBaseContext.getSecretKey(), mcpResponse);

            // 执行业务
            ApiResult apiResult = apiCommand.execute(context);

            mcpResponse.write(apiResult);
            return null;

        } catch (Throwable e) {
            logger.error("CommandController handleRequestInternal", e);
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_UNKNOWN));
        } finally {
            // log统计
            String httpAccessLogMsg = String.format(httpAccessLogFormat//
                    , System.currentTimeMillis() + "" // 时间
                    , StringUtils.defaultString(request.getRequestURI()) // uri
                    , StringUtils.defaultString(request.getQueryString()) // QueryString
                    , userIdLog + "" // userId
                    , StringUtils.defaultString(request.getMethod()) //http method
                    , StringUtils.defaultString(userAgentLog) // http agent
                    , StringUtils.defaultString(clientIpLog) // clientIp
                    , StringUtils.defaultString(methodNameLog) // mcpMethodName
                    , (System.currentTimeMillis() - curTime) + "" // 消耗的时间
            );
            httpAccessLogger.info(httpAccessLogMsg);
        }
        return null;
    }

    /**
     * 校验平台基本参数
     * 
     * @param requestParamMap
     * @param response
     * @param context
     * @param apiResultHolder
     * @return
     * @throws Exception
     */
    private boolean validateBaseRequiredParams(RequestBaseContext requestBaseContext,
            McpResponse mcpResponse) throws Exception {
        Map<String, String> requestParamMap = requestBaseContext.getRequestParamMap();
        
        String version = requestParamMap.get(HttpConstants.PARAM_VER);
        // version is required
        if (StringUtils.isEmpty(version)) {
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_VERSION));
            return false;
        }

        String sig = requestParamMap.get(HttpConstants.PARAM_SIG);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s validateBaseRequiredParams sig=%s", this
                    .getClass().getName(), sig));
        }

        // sig is required
        if (StringUtils.isEmpty(sig)) {
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_SIG));
            return false;
        }

        final int appId = NumberUtils.toInt(requestParamMap.get(HttpConstants.PARAM_APP_ID), 0);
        // appId is required
        if (appId == 0) {
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_APP_ID));
            return false;
        }

        MobileClientAppInfo appInfo = mobileClientAppService.getAppInfo(appId);
        // 接入信息无效
        if (appInfo == null) {
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_APP_ID));
            return false;
        }
        requestBaseContext.setAppInfo(appInfo);

        // t票处理
        final String t = requestParamMap.get(HttpConstants.PARAM_TICKET);
        requestBaseContext.setTicket(t);
        if (StringUtils.isNotEmpty(t)) {
            // 有ticket才有userId
        	UserPassport userPassport = passportService.getPassportByTicket(t);
            if (userPassport != null && userPassport.getUserId() != 0) {
            	requestBaseContext.setUserId(userPassport.getUserId());
                //已登录用户使用userSecretKey
            	requestBaseContext.setSecretKey(userPassport.getUserSecretKey());
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format(
                            "[%s]:{lookupSecretKey:[.getUserId()=%s][.getSecretKey()=%s]}", this
                                    .getClass().getName(), requestBaseContext.getUserId(),
                            requestBaseContext.getSecretKey()));
                }
            } else {
                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_TICKET));
                return false;
            }

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("%s lookupSecretKey getUserId()=%s getSecretKey()=%s",
                        this.getClass().getName(), requestBaseContext.getUserId(),
                        requestBaseContext.getSecretKey()));
            }

            //            logger.debug(String.format("lookupSecretKey %s: %s", requestBaseContext.getUserId(),
            //                    requestBaseContext.getSecretKey()));
            //            
            //            if (StringUtils.isEmpty(requestBaseContext.getSecretKey())) {
            //                // 防止骗取信任，用app的secretKey
            //                requestBaseContext.setSecretKey(requestBaseContext.getAppInfo().getSecretKey());
            //                // 服务取数据出问题，需要记一个错误日志，然后让用户正常使用
            //                logger.error(String.format(
            //                        "getSecretKey failed. appId=%s; userId=%s; ticket=%s", appId, requestBaseContext.getUserId(),
            //                        t));
            //            }
        } else {
            // 无t票时，才用app的secretKey
            requestBaseContext.setSecretKey(requestBaseContext.getAppInfo().getSecretKey());
        }
        String secretKey = requestBaseContext.getSecretKey();
        //        if (secretKey != null) {// 有secretKey时，才校验；当从tt中取用户secretKey失败时，不做校验：用户体验为王
        String normalizedString = McpUtils.generateNormalizedString(requestParamMap);
        String requiredSig = McpUtils.generateSignature(normalizedString, secretKey);

        if (logger.isDebugEnabled()) {
            logger.debug(String
                    .format("%s validateBaseRequiredParams secretKey=%s normalizedString=%s requiredSig=%s",
                            this.getClass().getName(), secretKey, normalizedString, requiredSig));
        }

        if (!StringUtils.equalsIgnoreCase(sig, requiredSig)) {
            // sig is mismatch
            mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_INVALID_SIG));
            return false;
        }
        // }

        return true;
    }

    /**
     * 防重发机制, 根据sig判断
     * 
     * @param requestParamMap
     * @param response
     * @param context
     * @param apiResultHolder
     * @return
     * @throws Exception
     */
    private boolean validateRequestFrequency(RequestBaseContext requestBaseContext,
            McpResponse mcpResponse) throws Exception {
        Map<String, String> requestParamMap = requestBaseContext.getRequestParamMap();
        // TODO: 防重发机制, 根据sig判断
//        String sig = requestParamMap.get(HttpConstants.PARAM_SIG);
//        try {
//            String cacheSig = sigCache.get(sig, String.class);
//
//            if (logger.isDebugEnabled()) {
//                logger.debug(String.format("[%s]:[sigCache.get(sig)=%s]",
//                        this.getClass().getName(), cacheSig));
//            }
//
//            if (cacheSig != null) { // 是重发的请求，用户访问过于频繁
//                mcpResponse.write(new ApiResult(ApiResultCode.E_SYS_REQUEST_TOO_FAST));
//                return false;
//            } else {
//                // 随便传一个value把sig的key存入到memecached里, 标记该sig已经请求过了
//                sigCache.set(sig, "1", String.class, sigCacheTimeout);
//                return true;
//            }
//        } catch (Throwable t) {
//            logger.error("validateRequestFrequency", t);
//        }
        return true;
    }

    public void setMobileClientAppService(MobileClientAppService mobileClientAppService) {
        this.mobileClientAppService = mobileClientAppService;
    }

    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

//    public void setSigCache(ICacheService sigCache) {
//        this.sigCache = sigCache;
//    }

    public void setCommandLookupService(CommandLookupService commandLookupService) {
        this.commandLookupService = commandLookupService;
    }

    public void setMessageSource(DelegatingMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setSigCacheTimeout(int sigCacheTimeout) {
        this.sigCacheTimeout = sigCacheTimeout;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(passportService, "passportService must not null!!");
        Assert.notNull(mobileClientAppService, "mobileClientAppService must not null!!");
//        Assert.notNull(sigCache, "sigCache must not null!!");
        Assert.notNull(commandLookupService, "commandLookupService must not null!!");
        Assert.notNull(messageSource, "messageSource must not null!!");
    }

}
