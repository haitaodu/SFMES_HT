package com.smartflow.service;

import com.smartflow.dto.GetMESMenuListOutputDTO;
import com.smartflow.model.MESMenu;

import java.util.List;
import java.util.Map;

public interface MESMenuService {
    /**
     * 查询所有菜单
     * @return
     */
    public List<GetMESMenuListOutputDTO> getMESMenuList();

    /**
     * 初始化所有菜单
     * @return
     */
    public List<Map<String,Object>> getMESMenuListInit();
}
