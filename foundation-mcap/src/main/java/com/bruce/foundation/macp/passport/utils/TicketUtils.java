/**
 * 
 */
package com.bruce.foundation.macp.passport.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * head(8)|uniqueKey(128)[userId(32)appId(32)]|jam(1)
 * 
 * @author liqian
 * 
 */
public class TicketUtils {

    private static final Log logger = LogFactory.getLog(TicketUtils.class);

    private final static String KEY = "@asdfs#@$%23";
    
    private static final String T_VERSION = "B";
    
    public static final String ENCODING_TYPE = "UTF-8";
    
    public static final String MIX_SPLIT_STR = "_";
    
    // 安全限制长度，防止刷暴缓存, 单点登录用
    public static final int IDENTITY_MAX_LEN = 256;

    
    /**
     * 生成ticket
     * @param userId
     * @param identity
     * @return
     */
    public static String generateTicket(int userId, String identity) {
        if (userId <= 0 || StringUtils.isEmpty(identity)) {
            return null;
        }
        
        identity = cutIdentify2Safe(identity);
        identity = DigestUtils.md5Hex(identity).toLowerCase();
        
        long now = System.currentTimeMillis();
        // 规则是userid的长度<(ver+now)
        String mix = mixContent(T_VERSION + now, userId + "");

        mix = mix + MIX_SPLIT_STR + identity;
        String ticket = null;
        try {
            ticket = T_VERSION + AzDGCrypt.encrypt(mix, KEY);
            ticket = ticket.replace("\n", "").replace("\r", "").replace("/", "-").replace("+", "_")
                    .replace("=", ".");
        } catch (Exception e) {
            logger.error("generateTicket", e);
        }

        return ticket;
    }

//    public static int decryptTicket(String ticket) {
//        int userId = 0;
//        if (StringUtils.isEmpty(ticket)) {
//            return userId;
//        }
//        try {
//            ticket = ticket.substring(1);
//            ticket = ticket.replace("-", "/").replace("_", "+").replace(".", "=");
//            String dStr = AzDGCrypt.decrypt(ticket, KEY);
//            userId = getUserIdFromMix(dStr);
//        } catch (Exception e) {
//            logger.error("decryptTicket", e);
//        }
//        return userId;
//    }
    
    public static String decryptTicket(String ticket) {

        String mix = null;
        if (StringUtils.isEmpty(ticket)) {
            return mix;
        }
        try {
            ticket = ticket.substring(1);
            ticket = ticket.replace("-", "/").replace("_", "+").replace(".", "=");
            mix = AzDGCrypt.decrypt(ticket, KEY);
        } catch (Exception e) {
            logger.error("decryptTicket", e);
        }
        return mix;
    }

    private static String mixContent(String maskStr, String uidStr) {
        int maskIdx = 0;
        try {
            byte[] maskBytes = maskStr.getBytes(ENCODING_TYPE);
            byte[] uidBytes = uidStr.getBytes(ENCODING_TYPE);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            for (int i = 0; i < uidBytes.length; i++) {
                maskIdx = maskIdx % maskBytes.length;
                byteOut.write(uidBytes[i]);
                byteOut.write(maskBytes[maskIdx++]);
            }
            return new String(byteOut.toByteArray(), ENCODING_TYPE);
        } catch (Exception e) {
            logger.error("", e);
        }
        
        return null;
    }
    
    public static int getUserIdFromMix(String mix) {
        
        try {
            if (StringUtils.isEmpty(mix) || !mix.contains(MIX_SPLIT_STR)) {
                return 0;
            }
            
            String[] mixArr = mix.split(MIX_SPLIT_STR);
            if (mixArr == null || mixArr.length < 2) {
                return 0;
            }
            
            byte[] mixBytes = mixArr[0].getBytes(ENCODING_TYPE);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            for (int i = 0; i < mixBytes.length; i++) {
                if (i % 2 == 0) {
                    byteOut.write(mixBytes[i]);
                }
            }
            return NumberUtils.toInt(new String(byteOut.toByteArray(), ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        
        return 0;
    }
    
    public static String getIdentityMD5FromMix(String mix) {
        
        String identity = null;
        try {
            if (StringUtils.isEmpty(mix) || !mix.contains(MIX_SPLIT_STR)) {
                return identity;
            }
            String[] mixArr = mix.split(MIX_SPLIT_STR);
            if (mixArr == null || mixArr.length < 2) {
                return identity;
            }
            identity = mixArr[1];
        } catch (Exception e) {
            logger.error("", e);
        }
        
        return identity;
    }
    
    public static String cutIdentify2Safe(String identity) {
        if (StringUtils.isEmpty(identity)) {
            return identity;
        }
        if (identity.length() > IDENTITY_MAX_LEN) {
            // 安全限制长度，防止刷暴缓存
            identity = identity.substring(0, IDENTITY_MAX_LEN);
        }
        return identity;
    }

    public static void main(String[] s) {

        long time = System.currentTimeMillis();
        String t = generateTicket(324265734, "aksdfjaklsdfj9123rhkjwefhkjqwfhasdkjfh");
        System.out.println(t + " cost:" + (System.currentTimeMillis() - time));
//        System.out.println(decryptTicket(t));
        time = System.currentTimeMillis();
        String mix = decryptTicket(t);
        //        int uid = decryptTicket("K-nXSSav6he3oqYRVu7tRf9685btSiavEGDOEUwTZLU=");
        System.out.println(mix + " cost:" + (System.currentTimeMillis() - time));
        System.out.println(getUserIdFromMix(mix));
        System.out.println(getIdentityMD5FromMix(mix));

    }

}
