/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.constants;

import java.util.Locale;

/**
 * 和http请求相关的常量，注意添加参数的时候，要改变不需要参加sig加密的参数
 * 
 * @author liqian
 * 
 */
public final class HttpConstants {

    public final static String USER_AGENT = "HTTP_REQUEST_USER_AGENT";

    public final static String CLIENT_IP = "HTTP_REQUEST_CLIENT_IP";

    public final static String LANGUAGE = "HTTP_REQUEST_LANGUAGE";

    public final static String PARAM_APP_ID = "app_id";

    public final static String PARAM_VER = "v";

    public final static String PARAM_CMD_METHOD = "cmd_method";

    public final static String PARAM_TICKET = "t";

    public final static String PARAM_ACCESS_TOKEN = "access_token";

    public final static String PARAM_SIG = "sig";

    public final static String PARAM_FORMAT = "format";

    public final static String PARAM_DATA_TYPE = "gz";

    // 不需要参加sig加密的参数
    public final static String[] NOT_ENCRYPTED_PARAMS = { USER_AGENT, CLIENT_IP, LANGUAGE,
            PARAM_SIG };

    // public static final String FORMAT_PROTO = "proto";

    public static final String FORMAT_JSON = "json";

    // public static final String FORMAT_TXT = "txt";

    public final static String DEFAULT_FORMAT = FORMAT_JSON;

    // public final static String[] FORMATS = { FORMAT_JSON, FORMAT_PROTO,
    // FORMAT_TXT };

    public final static String DATA_TYPE_COMPRESSION = "compression";

    public final static String PARAM_CLIENT_INFO = "client_info";

    public final static String MISC = "misc";

    public final static Locale DEFAULT_LANGUAGE = Locale.CHINA;
}
