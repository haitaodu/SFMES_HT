package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.dto.bom.BomItemForEdite;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.BOMItemModel;
import com.smartflow.util.BOMDataForPage;
import com.smartflow.util.BOMItemData;

public interface BOMHeadService {
	//分页查询
	public List<BOMDataForPage> getPageData(int pagesize, int pageindex, String materialNumberForSearch);
	//总的列数
	public int getRowCount(String materialNumberForSearch);
	//根据Id号查找出BOMHead数据返回给前端
	public  BOMDataForPage getBomDataById(int i);
	//根据Id号查找BOMItem数据返回给前端
	public List<BomItemForEdite> getBOMItemGetById(int i);
	//删除相应的数据
	public void delDataById(int i);
	//根据Id号查找相应的BOMHead表中的信息
	public BOMHeadModel get_BOMHead_Data_ById(int i);
	//根据前端给的数据插入到数据库
	public Boolean add_BOMHead_Data(BOMHeadModel bomHeadModel, List<BOMItemModel> bomItemModels);
	//根据前端数据修改数据库数据
	public void upDate_BOMHead_Data(BOMHeadModel bomHeadModel, List<BOMItemModel> bomItemModels);
	//根据BOMHeadId号查找相应的BOMItem的总条数
	public int  countBOMItemByBOMHeadId(int id);
	//根据Id号查找到相应的BOMHeadItem表的数据并对MaterialNumber+(MaterialName)
	public List<BOMItemData> getDataByIdInItemAddBracket(int i);
	//分页请求数据，由子物料号查找，最顶点的父节点的BOM数据
	public List<BOMDataForPage> readPageDataForParent(int pagesize, int pageindex, String materialNumberForSearch);
	//由字节点求顶级父节点的总的行数（RowCount）
	public Integer getRowCountForPageParent();
	/**
	 * 根据物料号查找BOM表中是否有对应的已注册的BOM信息
	 * @param materialNumber 物料号
	 * @param version 版本号
	 * @return 是否注册过的物料信息
	 */
	public  boolean isRegisterMaterialNumber(String materialNumber, int version);

	/**
	 * 根据物料号是否为原材料判断是否可以被注册为Bom
	 * @param materialNumber 物料号
	 * @return 返回是否是原材料
	 */
	public boolean isCanRegister(String materialNumber);

	/**
	 * 返回bom列表，<string,bomid>
	 * @return 返回bom列表
	 */
	List<Map<String,Object>> getBOMHeadList();

	/**
	 * 根据物料号获取已经注册的bom的最新版本
	 * @param materialNumber 物料号
	 * @return 返回bom的最新版本
	 */
	public BOMHeadModel getRegisterBom(String materialNumber);

	/**
	 * 返回产品名列表
	 * @return 产品名列表
	 */
	List<Map<String,Object>> getProdcutNameList();


	/**
	 *
	 * @param materialNumber 根据物料号查找已注册的产品
	 * @return 返回已注册的BOMHead列表
	 */
	public List<BOMHeadModel> getRegisterProduct(String materialNumber);

}
