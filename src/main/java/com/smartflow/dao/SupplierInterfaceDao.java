package com.smartflow.dao;

import java.util.List;

import com.smartflow.model.SupplierInEditInitializeModel;
import com.smartflow.model.SupplierListDTO;
import com.smartflow.model.SupplierModel;
import com.smartflow.util.SupplierReadData;

public interface SupplierInterfaceDao {
	//根据Id号查找core.Supplier的全列数据
		public SupplierModel getDataById(Integer id);
		//根据Id号查找将CreatorId以及EditorId替换为UserName的core.Customer表的全列数据
		public 	SupplierReadData getDetailsById(Integer id);
		//以Supplier表的全列数据为参数插入到数据库中
	    public void addData(SupplierModel supplierModel);
	    //根据前端给出的Id号，生成EditData数据模板，返回给前端，返回数据为数据库表的部分数据
	    public SupplierInEditInitializeModel getEditInitializeData(Integer id);
	    //根据前端给出的Id号删除表内相关的行的数据
	    public void delData(Integer id);
	    //根据前端给出的数据更改相关表内的相关行的数据
	    public void upDateData(SupplierModel supplierModel);
	    //依据前端给出的页码，以及每个页的容量，返回分页数据
	    public List<SupplierListDTO> getPageData(Integer pageNumber,Integer pageSize,String SupplierCode,String SupplierName);
	    //输出总的记录条数
	    public Integer getRowCount(String SupplierCode,String SupplierName);
	    //查询表中包含SupplierCode和SupplierName的数据的个数0或者1，进而作前端重复性判断
	    public int getDataForCheckUnique(String SupplierCode,String SupplierName);
	    //判断该供应商编号是否被注册过
	   public boolean isRegisterSupplierNumber(String SupplierNumber);
}
