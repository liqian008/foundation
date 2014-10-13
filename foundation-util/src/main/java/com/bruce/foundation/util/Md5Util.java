package com.bruce.foundation.util;

import java.security.MessageDigest;

public class Md5Util {

	/**
	 * md5加密，32位
	 * 
	 * @param str
	 * @return
	 */
	public static String md5Encode(String str) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte bytes[] = md5.digest();
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0xff);
				if (s.length() == 1) {
					buf.append("0");
				}
				buf.append(s);
			}

		} catch (Exception ex) {
		}
		return buf.toString();
	}

	/**
	 * md5加密，16位
	 * 
	 * @param str
	 * @return
	 */
	public static String md5Encode16(String plainText) {
		String md5Result = md5Encode(plainText);
		if (md5Result != null) {
			return md5Result.substring(8, 24);
		}
		return null;
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
//		System.out.println(md5Encode("278436491_" + time));
//		System.out.println(md5Encode16("278436491_" + time));
		System.out.println(md5Encode("liqian"));
	}
}
