/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.service;

import java.util.Map;

/**
 * @author liqian
 * 
 */
public interface McpCacheManager {

    // TODO: sigcache的实现
    public Map<String, Object> getSigCache();

    public Map<String, Object> getUserSecretKeyCache();
}
