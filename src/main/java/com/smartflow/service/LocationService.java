package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.model.LocationModel;
import com.smartflow.util.LocationDataForPage;

public interface LocationService {
	//获取数据库数据的总条目数
	public int getCount(String locationNumber, String description, Integer areaId);
	//分页请求返回给前端数据
	public List<LocationDataForPage> getPageData(int pageIndex, int pageSize, String locationNumber, String description, Integer areaId);
	//根据	Id号查找相应的记录数据
	public LocationDataForPage getDataById(int id);
	//根据Id号删除相应的数据，实际上是把改行的State换成-1
	public void delData(int i);
	//根据Id号查找相关数据并返回给前端，数据类型为Location表的原数据
	public LocationModel getLocationDataById(int id);
	//根据前端传来的数据插入到数据表中
	public void saveDataForArea(LocationModel locationModel);
	//根据前台给的数据更新到数据库表中华
	public void upDataForArea(LocationModel locationModel);
	//找到表Area相应的Id,Description以map形式呈现给前端
	public List<Map<String, Object>>  getAreaList();
	//查询表中包含LocationNumber和Description的数据的个数0或者1，进而作前端重复性判断
	public int getDataForCheckUnique(String LocationNumber, String Description);
	//根据库位号判定LocationNumber是否被注册过
	public boolean isRegisterLocationNumber(String LocationNumber);

}
