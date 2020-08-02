package com.smartflow.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haita
 */
public class BaseController {
	public Map<String, Object> setJson(Integer status, String message, Object entity)
	{
		Map<String, Object> json = new HashMap<>(3);
		json.put("Status", status);
		json.put("Data", entity);
		json.put("ErrorMessage", message);
		return json;
	}
}
