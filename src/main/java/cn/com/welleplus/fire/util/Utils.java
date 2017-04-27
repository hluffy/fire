package cn.com.welleplus.fire.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密明文
 * @author Lu
 *
 */
public class Utils {

	private static final String salt = "Welle"; //加密使用的盐
	
	public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);
	}

}
