/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.passport.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 客户端参数打gzip包，解包操作工具类
 * 
 * @author wp.mengfanxu@XIAONEI.OPI.COM Initial Created at 2011-11-28
 */
public class GZIPUtils {

    private static final Log logger = LogFactory.getLog(GZIPUtils.class);

    /**
     * 将字符串格式压缩成gzip格式的字节数组
     * 
     * @param str
     * @return
     * @throws IOException
     */
    public static byte[] gzip(String str) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zos = new GZIPOutputStream(new BufferedOutputStream(baos));
        try {
            zos.write(str.getBytes("UTF-8"));
        } finally {
            zos.close();
        }
        return baos.toByteArray();
    }

    public static byte[] gzip(byte[] byteArr) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zos = new GZIPOutputStream(new BufferedOutputStream(baos));
        try {
            zos.write(byteArr);
        } finally {
            zos.close();
        }
        return baos.toByteArray();
    }

    /**
     * 将字符串压缩成的gzip格式解析得到字节数组，以该字节数组为参数 buf 解压 buf 字节数组后得到字符串内容
     * 
     * @param buf
     * @return
     */
    public static String unGzipBytes(byte[] buf) {
        try {
            if (buf == null || buf.length == 0) return null;
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            return unGzipStreamToString(bais);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unGzipBytes", e);
        }
        return null;
    }

    /**
     * 将字符串压缩成的gzip格式解析得到字节数组， 将该字节数组转换成字节流，并以这个字节流为参数 in 解压 in 字节流后得到字符串内容
     * 
     * @param in
     * @return
     */
    public static String unGzipStreamToString(InputStream in) {
        try {
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] readBuf = new byte[8 * 1024];
            ByteArrayOutputStream outputByte = new ByteArrayOutputStream();
            int readCount = 0;
            do {
                readCount = gzip.read(readBuf);
                if (readCount > 0) {
                    outputByte.write(readBuf, 0, readCount);
                }
            } while (readCount > 0);
            if (outputByte.size() > 0) {
                return new String(outputByte.toByteArray());
            }
        } catch (Exception e) {
            logger.error("unGzipStreamToString", e);
        }

        return null;
    }

}
