/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.service.impl;

import java.util.HashMap;
import java.util.Map;
/*
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;*/

import com.bruce.designer.mcp.api.service.McpCacheManager;

/**
 * @author liqian
 * 
 */
public class McpCacheManagerImpl implements McpCacheManager {

    private Map<String, Object> sigCache = new HashMap<String, Object>();

    private Map<String, Object> userSecretKeyCache = new HashMap<String, Object>();

    @Override
    public Map<String, Object> getSigCache() {
        return sigCache;
    }

    @Override
    public Map<String, Object> getUserSecretKeyCache() {
        return userSecretKeyCache;
    }

}
