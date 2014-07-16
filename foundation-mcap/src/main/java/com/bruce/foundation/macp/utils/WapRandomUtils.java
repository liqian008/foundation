/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.utils;

import java.util.LinkedList;
import java.util.Random;

import org.apache.commons.lang.math.NumberUtils;

/*import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;*/

/**
 * 随机数的一些应用
 * 
 * @author liqian
 * 
 */
public class WapRandomUtils {

    /**
     * Logger for this class
     */
    // private static final Log logger =
    // LogFactory.getLog(WapRandomUtils.class);

    /**
     * 获得随机数(数字)
     * 
     * @param salt
     * @return
     */
    public static String getRandomNum(String salt) {
        if (salt == null) {
            salt = "";
        }
        salt += System.currentTimeMillis();

        long speedNum = NumberUtils.toLong(salt, System.currentTimeMillis());

        Random random = new Random(speedNum);

        // ArrayList
        LinkedList<String> ls = new LinkedList<String>();

        for (int i = 0; i < 10; i++) {// 0-9
            ls.add(String.valueOf(i));
        }
        int index = random.nextInt(ls.size());
        if (index > (ls.size() - 1)) {
            index = ls.size() - 1;
        }

        return ls.get(index);
    }

    /**
     * 获得随机数(字符串)
     * 
     * @param salt
     * @return
     */
    public static char getRandomChar(String salt) {
        if (salt == null) {
            salt = "";
        }
        salt += System.currentTimeMillis();

        long speedNum = NumberUtils.toLong(salt, System.currentTimeMillis());

        Random random = new Random(speedNum);

        // ArrayList
        LinkedList<String> ls = new LinkedList<String>();

        for (int i = 0; i < 26; i++) {// a-z
            ls.add(String.valueOf(97 + i));
        }
        int index = random.nextInt(ls.size());
        if (index > (ls.size() - 1)) {
            index = ls.size() - 1;
        }
        char ch = (char) NumberUtils.toInt(String.valueOf(ls.get(index)), '0');
        return ch;
    }

    /**
     * 生成随机数(只包含数字)
     * 
     * @param num
     * @param salt
     * @return
     * @throws InterruptedException
     */
    public static String generateRandomCodeNum(int num, String salt) throws InterruptedException {
        String code = "";
        for (int i = 0; i < num; i++) {
            code += getRandomNum(salt);
            Thread.sleep(new Random().nextInt(10) + 10);// 休眠以控制字符的重复问题
        }
        return code;
    }

    /**
     * 生成随机数(包含数字和字母)
     * 
     * @param num
     * @param salt
     * @return
     * @throws InterruptedException
     * @throws InterruptedException
     */
    public static String generateRandomCodeChar(int num, String salt) throws InterruptedException {
        String code = "";
        for (int i = 0; i < num; i++) {
            code += getRandomChar(salt);
            Thread.sleep(new Random().nextInt(10) + 10);// 休眠以控制字符的重复问题
        }
        return code;
    }

    /**
     * 生成指定长度的随机字符串
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();

        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }
}
