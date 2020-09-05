package com.smartflow.dao;

import java.util.List;

import com.smartflow.model.CustomerInEditInitializeModel;
import com.smartflow.model.CustomerListDto;
import com.smartflow.model.CustomerModel;
import com.smartflow.util.CustomerReadData;
import com.smartflow.util.ReadDataUtil;

public interface CustomerInterfaceDao {
	//根据Id号查找core.Customer的全列数据
	public CustomerModel getDataById(Integer id);
	//根据Id号查找将CreatorId以及EditorId替换为UserName的core.Customer表的全列数据
	public 	CustomerReadData getDetailsById(Integer id);
	//以Customer表的全列数据为参数插入到数据库中
    public void addData(CustomerModel customerModel);
    //根据前端给出的Id号，生成EditData数据模板，返回给前端，返回数据为数据库表的部分数据
    public CustomerInEditInitializeModel getEditInitializeData(Integer id);
    //根据前端给出的Id号删除表内相关的行的数据
    public void delData(Integer id);
    //根据前端给出的数据更改相关表内的相关行的数据
    public void upDateData(CustomerModel customerModel);
    //依据前端给出的页码，以及每个页的容量，返回分页数据
    public List<CustomerListDto> getPageData(Integer pageNumber, Integer pageSize, String CustomerCode, String CustomerName);
    //查询分页数据库数据总条数
    public Integer getRowCount(String CustomerCode, String CustomerName);
    //查询表中包含CustomerCode和Name的数据的个数0或者1，进而作前端重复性判断
    public int getDataForCheckUnique(String CustomerCode, String CustomerName);
    //检查客户编号是否是别注册过的
    public boolean isRegisterCustomerNumber(String CustomerNumber);
    //检查邓氏码是否是被注册过
    public boolean isRegisterDNUS(String DUNS);
}
