package com.smartflow.dao;

import com.smartflow.model.MESMenu;

import java.util.List;
import java.util.Map;

public interface MESMenuDao {
    /**
     * 查询所有菜单
     * @return
     */
    public List<MESMenu> getMESMenuList();

    /**
     * 初始化所有菜单
     * @return
     */
    public List<Map<String,Object>> getParentMESMenuInit();

    /**
     * 根据父菜单id查询子菜单列表
     * @param parentId
     * @return
     */
    public List<Map<String,Object>> getChildrenMESMenuListByParentId(Integer parentId);

    /**
     * 获取子菜单那列表
     * @param parentId
     * @return
     */
    public List<Map<String,Object>> getChildrenMESMenuList(Integer parentId);
}
