package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.AreaModel;
import com.smartflow.util.AreaDataForPage;


public interface AreaDao {


    /**
     *分页数组
     * @param pagesize pageSize pagesize
     * @param pageindex pageIndex pageIndex
     * @param areaNumber areaNumber areaNumber
     * @param areaName areaName areaname
     * @param factoryId factoryId factoryId
     * @return 返回分页数组
     */
	 List<AreaDataForPage> readPageData(int pagesize,int pageindex,String areaNumber,String areaName,Integer factoryId);


    /**
     * 查找总的条目数
     * @param areaNumber areaNumber
     * @param areaName areaName
     * @param factoryId factoryId
     * @return 总的条目数
     */
    int countData(String areaNumber,String areaName,Integer factoryId);


    /**
     * 根据Id找到相应的信息
     * @param id id
     * @return 返回AreaModel Entity
     */
    AreaModel getDataById(int id);


    /**
     * 根据Id找到相应的呈现给前端的信息
     * @param id id
     * @return 返回area 详情
     */
     AreaDataForPage getDataInId(int id);


    /**
     * 根据Id找到相应的信息并且更改State的状态
     * @param id id
     */
     void delByChangeData(int id);


    /**
     * 根据前端给的参数插入数据到数据库表
     * @param areaModel entity
     */
     void postData(AreaModel areaModel);


    /**
     * 根据前端所传数据更新数据库列表相关信息
     * @param areaModel entity
     */
     void upDate(AreaModel areaModel);


    /**
     * 查找表Area的相关数据以（key,value）形式展现
     * @return 获取区域数组列表
     */
     List<Map<String, Object>> getAreaList();



    /**
     * 查询表中包含AreaNumber和Description的数据的个数0或者1，进而作前端重复性判断
     * @param AreaNumber areaNumber
     * @param Descriprion description
     * @return 返回成功或失败
     */
     int getDataForCheckUnique(String AreaNumber,String Descriprion);
    
    /**
     * 查询区域id和区域名称
     * @return area List
     */
    public List<Map<String,Object>> getAreaIdAndName();


    /**
     * 根据区域号查询相应的区域号是否被注册过
     * @param areaNumber
     * @return 返回是否
     */
    public boolean isRegisterAreaNumber(String areaNumber);
}
