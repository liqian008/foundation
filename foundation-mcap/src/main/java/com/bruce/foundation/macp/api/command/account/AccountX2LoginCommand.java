/**
 * Copyright 2009-2012 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.entity.ApiResult;
import com.bruce.foundation.macp.api.entity.ApiResultCode;
import com.bruce.foundation.macp.api.utils.BizErrorMapperUtils;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.macp.passport.utils.TicketUtils;
import com.bruce.foundation.macp.utils.McpUtils;


/**
 * 登录
 * 
 * @author liqian
 * @version 2012-10-9下午06:17:21
 */
public class AccountX2LoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(AccountX2LoginCommand.class);

    private PassportService passportService;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(passportService, "passportService is required!");
    }

    /**
     * x2登录
     * 
     * @param token 访问token(必传）
     * @return 登录结果
     * 
     *         <pre>
     * 错误码：
     * IlleagleToken "无效的token"
     * </pre>
     */
    @Override
    public ApiResult onExecute(ApiCommandContext context) {
        ApiResult apiResult = null;

        // 取参数
        Map<String, String> stringParams = context.getStringParams();

        String token = StringUtils.defaultString(stringParams.get("token"));
        // 标志登录的唯一性，单点登录用
        String identity = StringUtils.defaultString(stringParams.get("identity"));

        // 执行
        LoginResult loginResult = null;
        UserPassport userPassport = null;
        try {
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(identity)){
                return new ApiResult(ApiResultCode.E_SYS_PARAM);
            }
            
            // 安全限制长度，防止刷暴缓存
            identity = TicketUtils.cutIdentify2Safe(identity);

            long t = System.currentTimeMillis();
            // 调用登录接口
            loginResult = accountService.x2Login(token);
            McpUtils.rpcTimeCost(t, "accountService.x2Login");

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("%s loginResult=%s", this.getClass().getName(),
                        McpUtils.gson.toJson(loginResult)));
            }

            if (loginResult == null) {
                return new ApiResult(ApiResultCode.E_BIZ_LOGIN_FAILED);
            }
            // 兼容底层的错误
            if (loginResult.getCode() != ErrorCode.SystemSuccess) {
                return new ApiResult(BizErrorMapperUtils.getMcpErrorCode(loginResult.getCode()));
            }
            if (loginResult.getUserId() == 0) {
                return new ApiResult(ApiResultCode.E_BIZ_LOGIN_FAILED);
            }

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("%s userId=%s", this.getClass().getName(),
                        loginResult.getUserId()));
            }

            // 过滤底层多余的错误信息
            BizErrorMapperUtils.filterBizResult(loginResult);

            int userId = loginResult.getUserId();
            // 还没有登录过
            userPassport = new UserPassport();
            userPassport.setUserId(userId);
            userPassport.setIdentity(identity);
            userPassport.setAppId(context.getMcpAppInfo().getAppId());
            userPassport.setThirdPartyToken(token);
            userPassport.setCreateTime(System.currentTimeMillis());
            String userSecretKey = McpUtils.generateSecretKey();
            userPassport.setUserSecretKey(userSecretKey);
            passportService.destroyTicket(userId);
            String ticket = passportService.createTicket(userPassport);
            if (StringUtils.isEmpty(ticket)) {
                logger.error(String.format("%s ticket is empty!", this.getClass().getName()));
                return new ApiResult(ApiResultCode.E_BIZ_LOGIN_FAILED);
            }
            userPassport.setTicket(ticket);

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("%s userPassport=%s", this.getClass().getName(),
                        userPassport));
            }

        } catch (Exception e) {
            return McpUtils.handleExpWithMsg(e);
        }

        // 返回结果
        apiResult = new ApiResult(ApiResultCode.SUCCESS, buildResult(userPassport, loginResult));
        return apiResult;
    }

    private Map<String, Object> buildResult(UserPassport userPassport, LoginResult loginResult) {
        Map<String, Object> rtMap = new HashMap<String, Object>();
        rtMap.put("t", userPassport.getTicket());
        rtMap.put("loginResult", loginResult);
        rtMap.put("secretKey", userPassport.getUserSecretKey());
        rtMap.put("time", System.currentTimeMillis());
        return rtMap;
    }

    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

}
