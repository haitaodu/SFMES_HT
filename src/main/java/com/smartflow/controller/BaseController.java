package com.smartflow.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	};
	public Map<String, Object> setJson(Integer status, String message, Object entity)
	{
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("Status", status);
		json.put("Data", entity);
		json.put("ErrorMessage", message);
		return json;
	}
}
