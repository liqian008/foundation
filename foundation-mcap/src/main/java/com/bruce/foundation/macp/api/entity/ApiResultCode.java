/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.entity;

/**
 * "E"表示“error”(错误)；"SYS"表示"system"（系统平台级的）;"biz"表示“business”（业务级的）
 * @author liqian
 */
public final class ApiResultCode {

    // 成功
    public final static int SUCCESS = 0;

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

    // >1000且<10000为业务级错误


//    public final static int E_BIZ_LOGIN_NO_ACCOUNT = 1002;// login
//                                                          // failed:NO_ACCOUNT
//
//    public final static int E_BIZ_LOGIN_WRONG_PASSWORD = 1003;// login
//                                                              // failed:WRONG_PASSWORD
//
//    public final static int E_BIZ_SAFE_CAPTCHA = 1004; // 需要图形验证码
//
//    public final static int E_BIZ_PHOHE_NUMBER_USED = 1005; // 该手机号已使用，请更换手机号绑定
//
//    public final static int E_BIZ_USER_BIND_UNVERIFIED = 1006; // 用户尚未进行绑定验证操作
//
//    public final static int E_BIZ_SAFE_VERIFY_CODE_INVALID = 1007; // 验证码错误

    public final static int E_BIZ_BATCH_RUN_CYCLE_CALL = 1008; // 批处理时，循环调用了
    
    /** 
     * x2底层业务错误, 错误号段[20000,30000)，半闭半开区间
     * 格式：x2底层业务错误标识号（2）|底层模块号(01~99)|错误序列id(01~99) 
     * 201xx: 底层业务系统级错误
     * 202xx: 上传服务错误
     * 203xx: account服务错误
     * 204xx: user服务错误
     * 205xx: relation服务错误
     * 206xx: feed服务错误(暂无)
     * 207xx: search服务错误(暂无)
     * 208xx: message服务错误(暂无)
     * 209xx: push服务错误(暂无)
     * 
     */
    // 底层业务系统级错误
//    public final static int E_BIZ_X2_SYSTEM_ERROR = 20101;
//    public final static int E_BIZ_X2_ILLEAGLE_PARAM = 20102;
//    public final static int E_BIZ_X2_DATE_PARSE_ERROR = 20103;
//    public final static int E_BIZ_X2_INIT_CLIENT_ITF_FAILED = 20104;
//    public final static int E_BIZ_X2_ERROR_ICE = 20105;
//    public final static int E_BIZ_X2_USER_SYSTEM_ERROR = 20106;
//    public final static int E_BIZ_X2_DUPLICATE_OPERATION = 20107;
//    public final static int E_BIZ_X2_MCS_CLIENT_ERROR = 20108;
    
    // 上传服务错误
    public final static int E_BIZ_X2_UPLOAD_SERVICE_ERROR = 20201;
    public final static int E_BIZ_X2_UPLOAD_SYSTEM_ERROR = 20202;
    public final static int E_BIZ_X2_UPLOAD_UNKNOW = 20203;
    public final static int E_BIZ_X2_UPLOAD_INVALID_USER = 20204;
    public final static int E_BIZ_X2_UPLOAD_INVALID_ARGUMENTS = 20205;
    public final static int E_BIZ_X2_UPLOAD_NO_FILE = 20206;
    public final static int E_BIZ_X2_UPLOAD_UNSUPPORTED_PAGE_TYPE = 20207;
    public final static int E_BIZ_X2_UPLOAD_IMAGE_TOO_BIG = 20208;
    public final static int E_BIZ_X2_UPLOAD_MISSING_FILE_TYPE = 20209;
    public final static int E_BIZ_X2_UPLOAD_IMAGE_PROCESS_ERROR = 20210;
    public final static int E_BIZ_X2_UPLOAD_FILE_PROCESS_FAILED = 20211;
    public final static int E_BIZ_X2_UPLOAD_IMAGE_TOO_LITTLE = 20212;
    public final static int E_BIZ_X2_UPLOAD_DOWNLOAD_FAILED = 20213;
    public final static int E_BIZ_X2_UPLOAD_IMAGE_OVER_MAXIMUM = 20214;
    
    // account服务错误
    public final static int E_BIZ_X2_ACCOUNT_NOT_REGISTER = 20301;
    public final static int E_BIZ_X2_CAPTCHA_WRONG = 20302;
    public final static int E_BIZ_X2_ILLEAGLE_TOKEN = 20303;
    public final static int E_BIZ_X2_NAME_IS_TOO_LONG = 20304;
    public final static int E_BIZ_X2_PUSH_SERVICE_ERROR = 20305;
    public final static int E_BIZ_X2_PHONE_NO_USED = 20306;
    public final static int E_BIZ_X2_ILLEAGLE_PHONE_NO = 20307;
    
    // user服务错误
    public final static int E_BIZ_X2_PROFILE_NOT_EXIST = 20401;
    public final static int E_BIZ_X2_ILLEGAL_SCHOOL_ID = 20402;
    public final static int E_BIZ_X2_ILLEGAL_DEPART_ID = 20403;
    public final static int E_BIZ_X2_USER_INFO_NOT_COMPLETE = 20404;
    public final static int E_BIZ_X2_EXCEED_MAX_PHOTO_NUM = 20405;
    public final static int E_BIZ_X2_MESSAGE_SEND_FAIL = 20406;
    public final static int E_BIZ_X2_CANNOT_DELETE_CURRENT_PHOTO = 20407;
    public final static int E_BIZ_X2_CANNOT_DELETE_CURRENT_SCHOOL = 20408;

    // relation服务错误
    public final static int E_BIZ_X2_CONTACT_NOT_EXIST = 20501;
    public final static int E_BIZ_X2_SELF_RELATION_OPERATE = 20502;
    public final static int E_BIZ_X2_PARTNER_IS_BLOCKED = 20503;
    public final static int E_BIZ_X2_NO_RELATION_EXIST = 20504;
    public final static int E_BIZ_X2_DELETE_NO_EXIST_CONTACT = 20505;
    public final static int E_BIZ_X2_IS_RENREN_FRIEND = 20506;
    public final static int E_BIZ_X2_FOLLOWING_REACH_THE_LIMIT = 20507;
    public final static int E_BIZ_X2_BE_SHIELDED = 20508;
    public final static int E_BIZ_X2_GREET_NO_MORE_THAN48_HOURS = 20509;
    public final static int E_BIZ_X2_REPEAT_GREET = 20510;
    public final static int E_BIZ_X2_NO_REPLY_GREET = 20511;

}
