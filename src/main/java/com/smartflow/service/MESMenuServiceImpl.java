package com.smartflow.service;

import com.smartflow.dao.MESMenuDao;
import com.smartflow.dto.GetMESMenuListOutputDTO;
import com.smartflow.model.MESMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MESMenuServiceImpl implements MESMenuService {
    @Autowired
    MESMenuDao mesMenuDao;
    @Override
    public List<GetMESMenuListOutputDTO> getMESMenuList() {
        List<MESMenu> mesMenuList = mesMenuDao.getMESMenuList();
        List<GetMESMenuListOutputDTO> menuList = new ArrayList<>();
        for (MESMenu mesMenu : mesMenuList) {
            GetMESMenuListOutputDTO getMESMenuListOutputDTO = new GetMESMenuListOutputDTO();
            getMESMenuListOutputDTO.setId(mesMenu.getId());
            getMESMenuListOutputDTO.setBpid(mesMenu.getPid());
            getMESMenuListOutputDTO.setMpid(mesMenu.getPid());
            getMESMenuListOutputDTO.setIcon(mesMenu.getIcon());
            getMESMenuListOutputDTO.setName(mesMenu.getName());
            getMESMenuListOutputDTO.setRoute(mesMenu.getRoute());
            menuList.add(getMESMenuListOutputDTO);
        }
        return menuList;
    }

    @Override
    public List<Map<String, Object>> getMESMenuListInit() {
        List<Map<String,Object>> parentMenuList = mesMenuDao.getParentMESMenuInit();
        if(!CollectionUtils.isEmpty(parentMenuList)){
            for (Map<String,Object> parentMenu:parentMenuList) {
                List<Map<String,Object>> sonMenuList = mesMenuDao.getChildrenMESMenuList(Integer.parseInt(parentMenu.get("key").toString()));
                parentMenu.put("children", sonMenuList);
            }
        }
        return parentMenuList;
    }
}
