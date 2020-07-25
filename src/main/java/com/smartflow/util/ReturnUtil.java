package com.smartflow.util;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.controller.BaseController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：tao
 * @date ：Created in 2020/7/10 17:26
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class ReturnUtil extends BaseController {
    public Map<String,Object> getReturnMessage(JSONObject jsonObject){
        Map<String,Object> json = new HashMap<>();
        String validBegin=jsonObject.get("ValidBegin")==null?"":String.valueOf(jsonObject.get("ValidBegin"));
        if ("".equals(validBegin)) {
            json= this.setJson(101, "请选择生效时间", -1);
            return json;
        }
        String validEnd=jsonObject.get("ValidEnd")==null?"":String.valueOf(jsonObject.get("ValidEnd"));
        if ("".equals(validEnd)) {
            json= this.setJson(101, "请选择失效时间", -1);
            return json;
        }
        return null;
    }
}
