/**
 * 
 */
package com.bruce.foundation.macp.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.bruce.foundation.macp.api.entity.ApiResultCode;


/**
 * @author liqian
 *
 */
public class BizErrorMapperUtils {

//    private static Map<ErrorCode, Integer> bizCodeMap = new HashMap<ErrorCode, Integer>();
//    static {
//        bizCodeMap.put(ErrorCode.SystemError, ApiResultCode.E_BIZ_X2_SYSTEM_ERROR);
//        bizCodeMap.put(ErrorCode.AccountNotRegister, ApiResultCode.E_BIZ_X2_ACCOUNT_NOT_REGISTER);
//        bizCodeMap.put(ErrorCode.CaptchaWrong, ApiResultCode.E_BIZ_X2_CAPTCHA_WRONG);
//        bizCodeMap.put(ErrorCode.IlleagleToken, ApiResultCode.E_BIZ_X2_ILLEAGLE_TOKEN);
//        bizCodeMap.put(ErrorCode.ContactNotExist, ApiResultCode.E_BIZ_X2_CONTACT_NOT_EXIST);
//        bizCodeMap.put(ErrorCode.ProfileNotExist, ApiResultCode.E_BIZ_X2_PROFILE_NOT_EXIST);
//        bizCodeMap.put(ErrorCode.IlleagleParam, ApiResultCode.E_BIZ_X2_ILLEAGLE_PARAM);
//        bizCodeMap.put(ErrorCode.DateParseError, ApiResultCode.E_BIZ_X2_DATE_PARSE_ERROR);
//        bizCodeMap.put(ErrorCode.InitClientItfFailed, ApiResultCode.E_BIZ_X2_INIT_CLIENT_ITF_FAILED);
//        bizCodeMap.put(ErrorCode.SelfRelationOperate, ApiResultCode.E_BIZ_X2_SELF_RELATION_OPERATE);
//        bizCodeMap.put(ErrorCode.PartnerIsBlocked, ApiResultCode.E_BIZ_X2_PARTNER_IS_BLOCKED);
//        bizCodeMap.put(ErrorCode.NoRelationExist, ApiResultCode.E_BIZ_X2_NO_RELATION_EXIST);
//        bizCodeMap.put(ErrorCode.DeleteNoExistContact, ApiResultCode.E_BIZ_X2_DELETE_NO_EXIST_CONTACT);
//        bizCodeMap.put(ErrorCode.NameIsTooLong, ApiResultCode.E_BIZ_X2_NAME_IS_TOO_LONG);
//        bizCodeMap.put(ErrorCode.DuplicateOperation, ApiResultCode.E_BIZ_X2_DUPLICATE_OPERATION);
//        bizCodeMap.put(ErrorCode.IllegalSchoolId, ApiResultCode.E_BIZ_X2_ILLEGAL_SCHOOL_ID);
//        bizCodeMap.put(ErrorCode.IllegalDepartId, ApiResultCode.E_BIZ_X2_ILLEGAL_DEPART_ID);
//        bizCodeMap.put(ErrorCode.UserInfoNotComplete, ApiResultCode.E_BIZ_X2_USER_INFO_NOT_COMPLETE);
//        bizCodeMap.put(ErrorCode.ExceedMaxPhotoNum, ApiResultCode.E_BIZ_X2_EXCEED_MAX_PHOTO_NUM);
//        bizCodeMap.put(ErrorCode.McsClientError, ApiResultCode.E_BIZ_X2_MCS_CLIENT_ERROR);
//        bizCodeMap.put(ErrorCode.uploadServiceError, ApiResultCode.E_BIZ_X2_UPLOAD_SERVICE_ERROR);
//        bizCodeMap.put(ErrorCode.IsRenrenFriend, ApiResultCode.E_BIZ_X2_IS_RENREN_FRIEND);
//        bizCodeMap.put(ErrorCode.FollowingReachTheLimit, ApiResultCode.E_BIZ_X2_FOLLOWING_REACH_THE_LIMIT);
//        bizCodeMap.put(ErrorCode.BeShielded, ApiResultCode.E_BIZ_X2_BE_SHIELDED);
//        bizCodeMap.put(ErrorCode.GreetNoMoreThan48Hours, ApiResultCode.E_BIZ_X2_GREET_NO_MORE_THAN48_HOURS);
//        bizCodeMap.put(ErrorCode.RepeatGreet, ApiResultCode.E_BIZ_X2_REPEAT_GREET);
//        bizCodeMap.put(ErrorCode.NoReplyGreet, ApiResultCode.E_BIZ_X2_NO_REPLY_GREET);
//        bizCodeMap.put(ErrorCode.MessageSendFail, ApiResultCode.E_BIZ_X2_MESSAGE_SEND_FAIL);
//        bizCodeMap.put(ErrorCode.CannotDeleteCurrentPhoto, ApiResultCode.E_BIZ_X2_CANNOT_DELETE_CURRENT_PHOTO);
//        bizCodeMap.put(ErrorCode.CannotDeleteCurrentSchool, ApiResultCode.E_BIZ_X2_CANNOT_DELETE_CURRENT_SCHOOL);
//        bizCodeMap.put(ErrorCode.UploadSystemError, ApiResultCode.E_BIZ_X2_UPLOAD_SYSTEM_ERROR);
//        bizCodeMap.put(ErrorCode.UploadUnknow, ApiResultCode.E_BIZ_X2_UPLOAD_UNKNOW);
//        bizCodeMap.put(ErrorCode.UploadInvalidUser, ApiResultCode.E_BIZ_X2_UPLOAD_INVALID_USER);
//        bizCodeMap.put(ErrorCode.UploadInvalidArguments, ApiResultCode.E_BIZ_X2_UPLOAD_INVALID_ARGUMENTS);
//        bizCodeMap.put(ErrorCode.UploadNoFile, ApiResultCode.E_BIZ_X2_UPLOAD_NO_FILE);
//        bizCodeMap.put(ErrorCode.UploadUnsupportedPageType, ApiResultCode.E_BIZ_X2_UPLOAD_UNSUPPORTED_PAGE_TYPE);
//        bizCodeMap.put(ErrorCode.UploadImageTooBig, ApiResultCode.E_BIZ_X2_UPLOAD_IMAGE_TOO_BIG);
//        bizCodeMap.put(ErrorCode.UploadMissingFileType, ApiResultCode.E_BIZ_X2_UPLOAD_MISSING_FILE_TYPE);
//        bizCodeMap.put(ErrorCode.UploadImageProcessError, ApiResultCode.E_BIZ_X2_UPLOAD_IMAGE_PROCESS_ERROR);
//        bizCodeMap.put(ErrorCode.UploadFileProcessFailed, ApiResultCode.E_BIZ_X2_UPLOAD_FILE_PROCESS_FAILED);
//        bizCodeMap.put(ErrorCode.UploadImageTooLittle, ApiResultCode.E_BIZ_X2_UPLOAD_IMAGE_TOO_LITTLE);
//        bizCodeMap.put(ErrorCode.UploadDownloadFailed, ApiResultCode.E_BIZ_X2_UPLOAD_DOWNLOAD_FAILED);
//        bizCodeMap.put(ErrorCode.UploadImageOverMaximum, ApiResultCode.E_BIZ_X2_UPLOAD_IMAGE_OVER_MAXIMUM);
//        bizCodeMap.put(ErrorCode.ErrorIce, ApiResultCode.E_BIZ_X2_ERROR_ICE);
//        bizCodeMap.put(ErrorCode.UserSystemError, ApiResultCode.E_BIZ_X2_USER_SYSTEM_ERROR);
//        bizCodeMap.put(ErrorCode.PushServiceError, ApiResultCode.E_BIZ_X2_PUSH_SERVICE_ERROR);
//        bizCodeMap.put(ErrorCode.PhoneNoIsUsed, ApiResultCode.E_BIZ_X2_PHONE_NO_USED);
//        bizCodeMap.put(ErrorCode.IlleaglePhoneNo, ApiResultCode.E_BIZ_X2_ILLEAGLE_PHONE_NO);
//    }
//    
//    public static int getMcpErrorCode(ErrorCode bizErrorCode) {
//        return bizCodeMap.get(bizErrorCode);
//    }
//    
//    public static BaseResult filterBizResult(BaseResult baseResult) {
//        baseResult.setCode(null);
//        baseResult.setErrorMessage(null);
//        return baseResult;
//    }
    
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        UserListResult rt1 = new UserListResult();
//        rt1.setCode(ErrorCode.ErrorIce);
//        BaseResult rt  = (BaseResult) rt1;
////        rt.setCode(null);
//        rt.setErrorMessage("eee");
//        rt.setSuccess(true);
//        System.out.println(McpUtils.gson.toJson(rt));
//    }

}
