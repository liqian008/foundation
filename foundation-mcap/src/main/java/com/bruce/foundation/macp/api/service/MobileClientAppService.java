/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.service;

import com.bruce.foundation.macp.api.entity.MobileClientAppInfo;

/**
 * @author liqian
 * 
 */
public interface MobileClientAppService {

    public MobileClientAppInfo getAppInfo(int appId);

    public boolean isAllowedApiMethod(int appId, String methodName);
}
