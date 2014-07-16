/**
 * 
 */
package com.bruce.designer.mcp.api.command.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


/**
 * @author liqian
 *
 */
public class MyTest {
    
    public static byte[] KEY_MASK = {(byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE};
    public static String CHARSET = "UTF-8";
    public static void main(String[] s) throws UnsupportedEncodingException {
        
        String key = "SJs#w";
        for (int i = 0; i < key.getBytes(CHARSET).length; i++) {
            System.out.println(Integer.toHexString((0xFF & key.getBytes(CHARSET)[i])));
        }
//        byte[] keyByte = getKeyByte(key);
        byte[] keyByte = key.getBytes(CHARSET);
//        for (int i = 0; i < keyByte.length; i++) {
//            System.out.println(Integer.toHexString((byte) (0xFF & keyByte[i])));
//        }
        
        System.out.println("=====encrypt======");
        byte[] content = "QWERTY123421asdfUIasdfasdf_+_+(l23423OP".getBytes(CHARSET);
//        for (int i = 0; i < content.length; i++) {
//            System.out.println(Integer.toHexString((byte) (0xFF & content[i])));
//        }
        byte[] cipherByte = encrypt(content, keyByte);
        System.out.println("=====encrypt rt======");
        for (int i = 0; i < cipherByte.length; i++) {
            System.out.println(Integer.toHexString((0xFF & cipherByte[i])));
        }
        
        cipherByte[2] = (byte)0x03;
        System.out.println("=====decrypt======");
        byte[] contentBytes = decrypt(cipherByte, keyByte);
        System.out.println("=====decrypt rt======");
        for (int i = 0; i < contentBytes.length; i++) {
            System.out.println(Integer.toHexString((0xFF & contentBytes[i])));
        }
        System.out.println(new String(contentBytes, CHARSET));
        
        byte[] header = {(byte)0xfe, (byte)0x03};
        System.out.println(isOdd(header));
    }
    
    private static byte[] encrypt(byte[] txt, byte[] key) {
        
        if (txt == null || key == null) {
            return null;
        }
        
        byte[] header = {(byte)0x53, (byte)0x00};
        if (isOdd(txt)) {
            header[1] = (byte) (header[1] | 0x01);
        }
        byte[] content = Arrays.copyOf(header, header.length + txt.length);
        for (int i = header.length; i < header.length + txt.length; i++) {
            content[i] = txt[i - header.length];
        }
        
        System.out.println(content.length+ " " + key.length);
        int groupCnt = content.length / key.length;
        int mod = content.length % key.length;
        System.out.println(mod);
        groupCnt = mod ==  0 ? groupCnt : (groupCnt + 1);
        System.out.println(groupCnt);
        byte[] cipherBytes = new byte[content.length];
        int idxAsc = 0;
        int idxDesc = content.length;
        for (int i = 0; i < groupCnt; i++) {
            for (int j = 0; j < key.length && idxAsc < content.length; j++) {
                cipherBytes[idxAsc] = (byte) (content[idxDesc - 1] ^ key[j]);
                cipherBytes[idxAsc] = (byte) (cipherBytes[idxAsc] << 3 | ((int)cipherBytes[idxAsc] & 0xFF) >>> (8 - 3));
                idxDesc--;
                idxAsc++;
            }
        }
        return cipherBytes;
    }
    
    private static byte[] decrypt(byte[] cipherBytes, byte[] key) {
        
        if (cipherBytes == null || key == null) {
            return null;
        }
        
        System.out.println(cipherBytes.length);
        int groupCnt = cipherBytes.length / key.length;
        int mod = cipherBytes.length % key.length;
        System.out.println(mod);
        groupCnt = mod ==  0 ? groupCnt : (groupCnt + 1);
        System.out.println(groupCnt);
        byte[] contentBytes = new byte[cipherBytes.length];
        int idxAsc = 0;
        int idxDesc = cipherBytes.length;
        for (int i = 0; i < groupCnt; i++) {
            for (int j = 0; j < key.length && idxAsc < cipherBytes.length; j++) {
                cipherBytes[idxAsc] = (byte) (cipherBytes[idxAsc] << (8 - 3) | ((int)cipherBytes[idxAsc] & 0xFF) >>> 3);
                contentBytes[idxDesc - 1] = (byte) (cipherBytes[idxAsc] ^ key[j]);
                idxDesc--;
                idxAsc++;
            }
        }
        
        byte[] header = {(byte)0x53, (byte)0x00};
        byte[] txt = Arrays.copyOfRange(contentBytes, header.length, contentBytes.length);
        
        byte headOdd = (byte) (contentBytes[1] & 0x01);
        byte realOdd = (byte) 0x00;
        if (isOdd(txt)) {
            realOdd = (byte)0x01;
        }
        if (contentBytes[0] != header[0] || headOdd != realOdd) {
            return null;
        }
        
        return txt;
    }
    
    private static byte[] getKeyByte(String key) throws UnsupportedEncodingException {
        byte[] oKeyBytes = key.getBytes(CHARSET);
        System.out.println(oKeyBytes.length + " " + KEY_MASK.length);
        if (oKeyBytes.length < KEY_MASK.length) {
            return null;
        }
        int groupCnt = oKeyBytes.length / KEY_MASK.length;
        System.out.println(groupCnt);
//        int mod = oKeyByte.length % KEY_MASK.length;
//        System.out.println(mod);
//        groupCnt = mod ==  0 ? groupCnt : (groupCnt - 1);
        System.out.println(groupCnt);
        byte[] keyBytes = new byte[groupCnt * 8];
        
        int idx = 0;
        for (int i = 0; i < groupCnt; i++) {
            for (int j = 0; j < KEY_MASK.length; j++) {
                keyBytes[idx] = (byte)(oKeyBytes[idx] & KEY_MASK[j]);
                keyBytes[idx] |= getOdd(keyBytes[idx], 8);
                idx++;
            }
        }
        
        return keyBytes;
    }
    
    private static byte getOdd(byte b, int size) {
        int oddCnt = 0;
        byte oeMask = (byte)0x01;
        for (int i = 0; i < size; i++) {
            byte endByte = (byte) ((b >>> (size - 1 - i)) & oeMask);
            if (endByte == oeMask) {
                oddCnt++;
            }
        }
        return oddCnt % 2 == 0 ? (byte)0x00 : (byte)0x01;
    }
    
    /**
     * 判断1是否为奇数个
     * @param bs
     * @return
     */
    private static boolean isOdd(byte[] bs) {
        int oddCnt = 0;
        byte oeMask = (byte)0x01;
        int size = 8;
        for (int j = 0; j < bs.length; j++) {
            for (int i = 0; i < size; i++) {
                byte endByte = (byte) ((bs[j] >>> (size - 1 - i)) & oeMask);
                if (endByte == oeMask) {
                    oddCnt++;
                }
            }
        }
        
        return oddCnt % 2 != 0;
    }
}
