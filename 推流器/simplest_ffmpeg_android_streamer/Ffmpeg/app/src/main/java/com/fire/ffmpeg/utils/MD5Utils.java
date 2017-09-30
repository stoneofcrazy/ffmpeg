package com.fire.ffmpeg.utils;

import java.security.MessageDigest;

public class MD5Utils {
	
	private static final char HEX_DIGITS[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};
	
	private static String toHexString(byte[] b){
		int len = b.length;
		StringBuilder sb = new StringBuilder(len * 2);
		for (int i = 0; i < len; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	
	
	public static String md5(String str){
		if (str == null) {
			return null;
		}
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buffer = str.getBytes();
			md5.update(buffer, 0, buffer.length);
			
			return toHexString(md5.digest());
		} catch (Exception e) {
		}
		return null;
	}
}
