/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bruce.designer.mcp.api.entity.MobileClientAppInfo;
import com.bruce.designer.mcp.api.service.MobileClientAppService;
import com.bruce.designer.mcp.utils.McpUtils;

/**
 * @author liqian
 * 
 */
public class MobileClientAppServiceImpl implements MobileClientAppService, Runnable {

    private static final Log logger = LogFactory.getLog(MobileClientAppServiceImpl.class);

    // private Map<String, MobileClientAppInfo> appKeyAppInfoMap = null;

    private Map<Integer, MobileClientAppInfo> appIdAppInfoMap = null;

    private Map<Integer, List<String>> appAuthMap = null;

    private synchronized void loadApp() {
        logger.info("loadApp start");
        long startTime = System.currentTimeMillis();

        // TODO: 加载app
        // test app
        appIdAppInfoMap = new HashMap<Integer, MobileClientAppInfo>();
        MobileClientAppInfo mcai = new MobileClientAppInfo();
        mcai.setAppId(1);
        mcai.setAppName("testapp");
        mcai.setAppUrl("ttt");
        mcai.setSecretKey("1qaz2wsx");
        appIdAppInfoMap.put(mcai.getAppId(), mcai);
        appAuthMap = new HashMap<Integer, List<String>>();
        List<String> methods = new ArrayList<String>();
        methods.add("test.cmd");
        methods.add("account.*");
        methods.add("feed.*");
        methods.add("relation.*"); 
        methods.add("photo.*"); 
        methods.add("search.*"); // search
        methods.add("user.*"); // user
        methods.add("upload.*"); // upload
        methods.add("sys.*");
        methods.add("message.*");
        methods.add("decision.*");
        methods.add("push.*");
        methods.add("batch.*");
        methods.add("thirdparty.*");
        methods.add("usercounter.*");
        appAuthMap.put(mcai.getAppId(), methods);

        logger.info("loadApp end timecost:" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void run() {
        this.loadApp();
    }

    @Override
    public MobileClientAppInfo getAppInfo(int appId) {
        // if (StringUtils.isEmpty(appKey) || this.appKeyAppInfoMap == null) {
        // return null;
        // }
        // return this.appKeyAppInfoMap.get(appKey);
        if (appId == 0 || this.appIdAppInfoMap == null) {
            return null;
        }
        return this.appIdAppInfoMap.get(appId);
    }

    /**
     * 检查是否允许调用api
     */
    @Override
    public boolean isAllowedApiMethod(int appId, String methodName) {
//        if (this.appAuthMap == null) {
//            return false;
//        }
//        Collection<String> apiMethods = this.appAuthMap.get(appId);
//        if (apiMethods == null) {
//            return false;
//        }
//        for (String method : apiMethods) {
//            if (McpUtils.leftMatch(methodName, method)) {
//                return true;
//            }
//        }
//        return false;
    	return true;
    }
}
