package com.smartflow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	/**
	 * MD5方法
	 * @return 密文
	 */
	public static String md5(String inputPwd) throws Exception{ 
		//加密后的字符串
		String encodeStr = DigestUtils.md5Hex(inputPwd);
		//System.out.println("MD5加密后的字符串为：encodeStr="+encodeStr);
		return encodeStr;
	}
	
	/**
	 * 判断输入的密码是否正确
	 * @param inputPwd 前端输入的密码
	 * @param md5Pwd 数据库MD5加密的密码
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String inputPwd, String md5Pwd) throws Exception{
		//根据传入的密钥进行验证
		if (inputPwd.equalsIgnoreCase(md5Pwd)) {
			System.out.println("MD5验证通过");
			return true;
		}
		return false;
	}
	
	public static boolean matchStr(String str){
		String regex = "^[a-z0-9_-]{1,46}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(md5("1"));
//		System.out.println(verify("123456", "e10adc3949ba59abbe56e057f20f883e"));
		System.out.println(matchStr("admin"));
	}
	
}
