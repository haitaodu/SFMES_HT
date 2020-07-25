package com.smartflow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static void main(String[] args) {
		/*String str = "DISC506002000001010100021012510415112071072512406129051520715408 GZ116243-6692 0000012   B20 0229648 20180226";
		System.out.println(str.indexOf(" "));
		int gzIndex = str.lastIndexOf("GZ");
		int _index = str.lastIndexOf("-");
		int b2Index = str.lastIndexOf("B20");
		String gz = str.substring(gzIndex, _index+5);
		String b2 = str.substring(b2Index+4, str.length()-8).trim();
		System.out.println(str.substring(gzIndex, _index)+"============"+gz);
		System.out.println(str.substring(b2Index)+"==========="+b2+"===");*/
                                                                                                                                                                                                                                                                                                                                                                 
		/*String input = "DISC506002000001010100021012510415112071072512406129051520715408 GZ116243-6692 0000012   B20 0229648";
		// 20180226 //\\s+[0-9]{8}
		String reg2_1_1="\\s+[0-9]{7}";
		String reg2_1_2="[A-Z]{1}[0-9]{2}\\s+[0-9]{7}";

		String reg3="[0-9]{7}";
		Pattern p = Pattern.compile(reg2_1_2);

		Matcher m = p.matcher(input);
		String str = null;
		while (m.find()) {
			str = m.group();
			System.out.println(str);
		}
		Pattern p2 = Pattern.compile(reg3);
		Matcher m2 = p2.matcher(str);
		while (m2.find()) {
			System.out.println(m2.group());
		}*/
		String str = "DISC506002000001010100021012510415112071072512406129051520715408 GZ116243-6692 0000012   B20 0229648 20181026";
		String gz = getGZStr(str);
		String b = getBStr(str);
		System.out.println("gz="+gz);
		System.out.println("b="+b);

	}

	/**
	 * 获取GZ字符串
	 * @param str
	 * @return
	 */
	public static String getGZStr(String str){
		String reg="[A-Z]{2}[0-9]{6}-[0-9]{4}";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while (m.find()) {
			//System.out.println(m.group());
			return m.group();
		}
		return null;
	}
	
	/**
	 * 获取B后面的字符串
	 * @param str
	 * @return
	 */
	public static String getBStr(String str){
		String reg1="[A-Z]{1}[0-9]{2}\\s+[0-9]{7}";
		String reg2="[0-9]{7}";
		Pattern p = Pattern.compile(reg1);
		Matcher m = p.matcher(str);
		String str1 = null;
		while (m.find()) {
			str1 = m.group();
			//System.out.println(str1);
		}
		Pattern p2 = Pattern.compile(reg2);
		Matcher m2 = p2.matcher(str1);
		while (m2.find()) {
			//System.out.println(m2.group());
			return m2.group();
		}
		return null;
	}

	/**
	 * 根据等级编号查询等级名称
	 * @param level
	 * @return
	 */
	public static String getLevelName(Integer level){
		String levelName = null;
		if(level == 1){
			levelName = "部门员工";
		}else if(level == 2){
			levelName = "部门组长";
		}else if(level == 3){
			levelName = "部门经理";
		}else if(level == 4){
			levelName = "部门总监";
		}
		return levelName;
	}
}
