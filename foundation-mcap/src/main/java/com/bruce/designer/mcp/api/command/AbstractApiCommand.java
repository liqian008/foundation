/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.command;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bruce.designer.mcp.api.entity.ApiCommandContext;
import com.bruce.designer.mcp.api.entity.ApiResult;
import com.bruce.designer.mcp.api.entity.ApiResultCode;
import com.bruce.designer.mcp.api.entity.ClientInfo;
import com.bruce.designer.mcp.api.utils.StatLogUtil;
import com.bruce.designer.mcp.constants.HttpConstants;

/**
 * @author liqian
 * 
 */
public abstract class AbstractApiCommand implements ApiCommand {

    private static final Log userAccesslogger = LogFactory.getLog("mcp_user_access_log");

    private static final Log statAccessLogger = LogFactory.getLog("mcp_stat_access_log");


    /**
     * 子类不可重写
     */
    @Override
    public final ApiResult execute(ApiCommandContext context) {
        long starTime = System.currentTimeMillis();

        ApiResult apiResult = null;

        // ======statAccessLog 统计用 start======
        String methodName = context.getMethodName();
        String extra1 = "";
        //        int duration = 0;
        int value = 1;
        String identifier = "";
        Map<String, String> stringParams = context.getStringParams();
        String clientIp = stringParams.get(HttpConstants.CLIENT_IP);
        ClientInfo clientInfo = context.getClientInfo();
        //        if ("user.login".equals(methodName)) {
        //            identifier = "login";
        //            int snsType = NumberUtils.toInt(stringParams.get("snsType"), 0);
        //            if (snsType == 1) {
        //                extra1 = "fb";
        //            } else if (snsType == 2) {
        //                extra1 = "tw";
        //            }
        //        }
        //        if ("feed.publish".equals(methodName)) {
        //            identifier = "ugc.feed";
        //            value = NumberUtils.toInt(stringParams.get("duration"));
        //        }
        //        if ("feed.comment".equals(methodName)) {
        //            identifier = "ugc.comment";
        //            value = NumberUtils.toInt(stringParams.get("duration"));
        //        }
        //        if ("sns.share".equals(methodName)) {
        //            identifier = "ugc.share";
        //        }
        //        if ("feed.like".equals(methodName)) {
        //            identifier = "ugc.like";
        //        }
        //        if ("user.follow".equals(methodName)) {
        //            identifier = "ugc.follow";
        //        }
        // ======statAccessLog 统计用 end======

        this.beforeExecute(context);
        apiResult = this.onExecute(context);
        this.afterExecute(context, apiResult);

        // ======statAccessLog 统计用 start======
        String extra2 = "";
        if ("user.login".equals(methodName)) {
            extra2 = apiResult.getCode() == ApiResultCode.SUCCESS ? "success" : "failure";
        }
        String statLogStr = StatLogUtil.log(statAccessLogger, System.currentTimeMillis(),
                methodName, context.getUserId() + "", context.getMcpAppInfo().getAppId() + "",
                clientInfo, clientIp, "T2Server", identifier, "1", value + "", extra1, extra2, "",
                "", apiResult.getCode() + "", "");
        // ======statAccessLog 统计用 end======

        // ======userAccessLog mcp自用统计 start======
        // 在stataccess统计的基础上添加了返回结果和消耗时间，也是以“|”分割
        userAccesslogger.info(statLogStr + apiResult.getCode() + "|"
                + (System.currentTimeMillis() - starTime));
        // ======userAccessLog mcp自用统计 end======

        //        currentApiCommandContextHolder.remove();
        return apiResult;
    }

    protected void beforeExecute(ApiCommandContext context) {

    }

    protected void afterExecute(ApiCommandContext context, ApiResult apiResult) {

    }

    public abstract ApiResult onExecute(ApiCommandContext context);

    // /**
    // * 仅在线程周期内有用
    // * @return
    // */
    // protected int getHostId() {
    // return currentApiCommandContextHolder.get().getUserId();
    // }
    //
    // /**
    // * 仅在线程周期内有用
    // * @return
    // */
    // protected int getAppId() {
    // return currentApiCommandContextHolder.get().getMcpAppInfo().getAppId();
    // }
}
