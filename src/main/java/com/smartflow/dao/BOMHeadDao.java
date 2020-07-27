package com.smartflow.dao;

import java.util.List;

import com.smartflow.dto.bom.BomItemForEdite;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.BOMItemModel;
import com.smartflow.util.BOMDataForPage;
import com.smartflow.util.BOMItemData;
import com.smartflow.util.BOMItemDataForParent;

public interface BOMHeadDao {


	/**
	 * 分页请求数据
	 * @param pagesize pagesize
	 * @param pageindex pageIndex
	 * @param materialNumberForSearch materialNumber
	 * @return 分页数组
	 */
	 List<BOMDataForPage> readPageData(int pagesize,int pageindex,String materialNumberForSearch);


	/**
	 *总的记录数
	 * @param materialNumberForSearch
	 * @return
	 */
	 int countData(String materialNumberForSearch);


	/**
	 *根据Id查找对应的BOMHead对应的数据
	 * @param i id
	 * @return 返回bom详情
	 */
	 BOMDataForPage getDataInId(int i);


	/**
	 *根据Id号查找到相应的BOMHeadItem表的数据
	 * @param i bomhead id
	 * @return 返回bomitem的列表信息
	 */
	 List<BomItemForEdite> getDataByIdInItem(int i);

	/**
	 *根据Id号删除相应的信息，仅仅是把该字段的State的值改为-1
	 * @param i bomid
	 */
	 void delData(int i);


	/**
	 *根据Id号查找相应的BOMHead表中的信息
	 * @param i id
	 * @return bom entity
	 */
	 BOMHeadModel get_BOMHead_Data_ById(int i);
	//根据前端给的数据插入到数据库

	/**
	 *
	 * @param bomHeadModel bom entity
	 * @param bomItemModels bom item entity
 	 * @return 返回是否成功
	 */
	 Boolean add_BOMHead_Data(BOMHeadModel bomHeadModel,List<BOMItemModel> bomItemModels);


	/**
	 *在数据库表中删除该数列,删除BOMHeadId对应的BOMItem,不删除BOMHead里面的数据
	 * @param id id
	 */
	 void delDataInEntity(int id);


	/**
	 *根据BOMHeadId号查找相应的BOMItem的总条数
	 * @param id id
	 * @return 返回总的条目数
	 */
	 int  countBOMItemByBOMHeadId(int id);


	/**
	 *根据BOMHeadId号插入到BOMItem表的数据
	 * @param id id
	 * @param bomItemModels bomitemmodels
 	 */
	 void  addBOMItemByBOMHeadId(int id,List<BOMItemModel> bomItemModels);


	/**
	 *更新BOMHead
	 * @param bomHeadModel entity
	 */
	 void updateBOMHead(BOMHeadModel bomHeadModel);


	/**
	 *根据Id号查找到相应的BOMHeadItem表的数据并对MaterialNumber+(MaterialName)
	 * @param i bom id
	 * @return bom item list
	 */
	 List<BOMItemData> getDataByIdInItemAddBracket(int i);


	/**
	 *根据MaterialId递归找出他相应的BOMHeadItem子节点
	 * @param materialId 物料id
	 */
	 void getChildData(Integer materialId);


	/**
	 *初始化BOMItem的数组以及数组的排列序号n
	 */
	void init();


	/**
	 *根据物料Id找到相关的产品列表，由子节点找父节点
	 * @param materialId
	 */
	 void getParentData(Integer materialId);
	//分页请求数据，由子物料号查找，最顶点的父节点的BOM数据

	/**
	 *
	 * @param pagesize
	 * @param pageindex
	 * @param materialNumberForSearch
	 * @return
	 */
	 List<BOMDataForPage> readPageDataForParent(int pagesize,int pageindex,String materialNumberForSearch);
	//由字节点求顶级父节点的总的行数（RowCount）

	/**
	 *
	 * @return
	 */
	 Integer getRowCountForPageParent();
    //根据物料号查找BOM表中是否有对应的已注册的BOM信息

	/**
	 *根据物料号和版本号，判断是否已经注册的信息
	 * @param materialNumber 物料号
	 * @version version  版本号
	 * @return 返回是否已经注册的信息
	 */
	  boolean isRegisterMaterialNumber(String materialNumber,int version);

	/**
	 * 根据物料号获取已经注册的bom的最新版本
	 * @param materialNumber 物料号
	 * @return 返回bom的最新版本
	 */
	public BOMHeadModel getRegisterBom(String materialNumber);



}
