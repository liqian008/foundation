/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.entity;

/**
 * "E"表示“error”(错误)；"SYS"表示"system"（系统平台级的）;"biz"表示“business”（业务级的）
 * @author liqian
 */
public class ErrorCode {

    // 请求成功
    public final static int RESULT_SUCCESS = 1;
    // 请求失败
    public final static int RESULT_FAILED = 0;

    // <1000的为系统错误
    public final static int E_SYS_UNKNOWN = 1;// unknown

    public final static int E_SYS_PARAM = 2;// invalid or unkown parameter

    public final static int E_SYS_PERMISSION_DENY = 3;// permission deny

    public final static int E_SYS_REQUEST_TOO_FAST = 4;// user request is too fast

    public final static int E_SYS_ANTISPAM = 5;// antispam相关

    public final static int E_SYS_INVALID_APP_ID = 6;// invalid appid

    public final static int E_SYS_INVALID_TICKET = 7;// invalid tikect

    public final static int E_SYS_INVALID_SIG = 8;// invalid sig

    public final static int E_SYS_INVALID_VERSION = 9;// invalid version

    public final static int E_SYS_UNKNOWN_METHOD = 10;// unknown method

    public final static int E_SYS_UNKNOWN_RESULT_FORMAT = 11;// unknown results format

    public final static int E_SYS_RPC_ERROR = 12;// RPC error
    
    public final static int E_SYS_TICKET_KICKED = 13;// ticket has been kicked out

}
