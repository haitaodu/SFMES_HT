package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.model.AreaModel;
import com.smartflow.util.AreaDataForPage;

public interface AreaService {
	//找出数据库该表数据总条数
	public int count(String areaNumber,String areaName,Integer factoryId);
	//分页请求
	public List<AreaDataForPage> getPageData(int pagesize,int pageindex,String areaNumber,String areaName,Integer factoryId);
    //根据Id号找到数据给前台
	public AreaDataForPage getDataById(int id);
	//根据前端Id号更改后端State数据
	public void delDataByChangeStatus (int id);
	//根据前端Id传递参数，查找出相应表的信息
	public AreaModel getAreaDataById(int id);
	//根据前端传来的数据插入到数据表中
	public void saveDataForArea(AreaModel areaModel);
	//根据前台给的数据更新到数据库表中华
	public void upDataForArea(AreaModel areaModel);
	//查询表中包含AreaNumber和Description的数据的个数0或者1，进而作前端重复性判断
    public int getDataForCheckUnique(String AreaNumber,String Descriprion);
    
    /**
     * 查询区域id和区域名称
     * @return
     */
    public List<Map<String,Object>> getAreaIdAndName();
	//根据区域号查询相应的区域号是否被注册过
	public boolean isRegisterAreaNumber(String areaNumber);
}
