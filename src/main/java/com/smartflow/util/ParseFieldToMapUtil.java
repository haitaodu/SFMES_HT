package com.smartflow.util;

import com.smartflow.model.MESMenu;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseFieldToMapUtil {
    public static List<Map<String,Object>> parseMenuFieldToMap(List<MESMenu> menuList){
        List<Map<String,Object>> mapList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuList)){
            for (MESMenu mesMenu:menuList) {
                Map<String,Object> map = new HashMap<>();
                map.put("key", mesMenu.getId());
                map.put("title", mesMenu.getName());
                mapList.add(map);
            }
        }
        return mapList;
    }

    public String parseFiledToString(String number, String description){
        return StringUtils.isEmpty(description) ? number : number + "(" + description + ")";
    }
}
