package com.smartflow.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class ReadDataUtil {
	public static JSONObject paramData(HttpServletRequest request) throws Exception{
		
		BufferedReader reader = request.getReader();
		String input = null;
		StringBuffer requestBody = new StringBuffer();
		while((input = reader.readLine()) != null) {
			requestBody.append(input);
		}
		JSONObject jsonObject = JSONObject.parseObject(requestBody.toString());
		return jsonObject;
	}
}
