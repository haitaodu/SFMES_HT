package com.smartflow.dao;

import java.util.List;

import com.smartflow.model.LocationModel;
import com.smartflow.util.LocationDataForPage;

public interface LocationDao {
//查找总的记录数	
public int getCount(String locationNumber,String description,Integer areaId);
//分页请求
public List<LocationDataForPage> getPageData(int pageindex,int pagesize,String locationNumber,String description,Integer areaId);
//根据Id号查找相关数据
public LocationDataForPage getLocationData(int id);
//public根据Id号删除相关的信息，并非真的删除相应的数据，而是把那一行的State设置为-1
public void delDataById(int id);
//根据Id号查找相关的数据，数据类型不经过处理，为数据库Model
public LocationModel getDataById(int id);
//根据前端给的参数插入数据到数据库表
public void postData(LocationModel locationModel);
//根据前端所传数据更新数据库列表相关信息
public void upDate(LocationModel  locationModel);
//查询表中包含LocationNumber和Description的数据的个数0或者1，进而作前端重复性判断
public int getDataForCheckUnique(String LocationNumber,String Description);
//根据库位号判定LocationNumber是否被注册过
public boolean isRegisterLocationNumber(String LocationNumber);
}
