package cn.com.welleplus.fire.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ��������
 * @author Lu
 *
 */
public class Utils {

	private static final String salt = "Welle"; //����ʹ�õ���
	
	public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);
	}

}
