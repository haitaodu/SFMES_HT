package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.dto.GetDTOByConditionOfMaterialListDTO;
import com.smartflow.model.Material;
import com.smartflow.util.MaterialDataForSearch;

public interface MaterialService {
	/**
	 * 获取物料总条数
	 * @param getDTOByConditionOfMaterialListDTO
	 * @return
	 */
	public Integer getMaterialTotalCount(GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO);
	
	/**
	 * 多条件分页查询物料
	 * @param getDTOByConditionOfMaterialListDTO
	 * @return
	 */
	public List<Material> getMaterialByCondition(GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO);

	/**
	 * 根据公司id获取公司名称
	 * @param companyId
	 * @return
	 */
	public String getCompanyNumberByCompanyId(Integer companyId);
	
	/**
	 * 根据工站组id获取工站组名称
	 * @param stationGroupId
	 * @return
	 */
	public String getStationGroupNumberByStationGroupId(Integer stationGroupId);

	/**
	 * 根据请求地点id查询请求地点名称
	 * @param locationId
	 * @return
	 */
	public String getLocationNumberByLocationId(Integer locationId);
	
	/**
	 * 根据物料类型id查询物料类型名称
	 * @param materialGroupTypeId
	 * @return
	 */
	public String getMaterialGroupTypeNameByMaterialGroupTypeId(Integer materialGroupTypeId);
	
	/**
	 * 根据MSLId查询MSL名称
	 * @param mslId
	 * @return
	 */
	public String getMSLNameByMSLId(Integer mslId);
	
	/**
	 * 根据采购类型id查询采购类型名称
	 * @param procurementTypeId
	 * @return
	 */
	public String getProcurementTypeNameByProcurementTypeId(Integer procurementTypeId);
	
	/**
	 * 根据unitId查询UnitName
	 * @param unitId
	 * @return
	 */
	public String getUnitNameByUnitId(Integer unitId);
	
	/**
	 * 根据物料id查询物料详情
	 * @param material
	 * @return
	 */
	public Material getMaterialById(Integer materialId);
	
	/**
	 * 查询物料类型
	 * @return
	 */
	public List<Map<String, Object>> getMaterialTypeNameAndId();
	
	/**
	 * 查询单位名称和id
	 * @return
	 */
	public List<Map<String, Object>> getUnitNameAndId();
	
	/**
	 * 查询采购类型id和名称
	 * @return
	 */
	public List<Map<String, Object>> getProcurementTypeNameAndId();
	
	/**
	 * 查询请求地点id和名称
	 * @return
	 */
	public List<Map<String, Object>> getLocationNumberAndId();
	
	/**
	 * 查询MSL id和名称
	 * @return
	 */
	public List<Map<String, Object>> getMSLNameAndId();
	
	/**
	 * 查询工站组id和名称
	 * @return
	 */
	public List<Map<String, Object>> getStationGroupNumberAndId();
	
	/**
	 * 查询公司名称和id
	 * @return
	 */
	public List<Map<String, Object>> getCompanyNameAndId();
	
	/**
	 * 查询materialNumber存在的次数，判断materialNumber是否重复
	 * @param materialNumber
	 * @return
	 */
	public Integer getCountByMaterialNumber(String materialNumber);
	
	/**
	 * 查询customerMaterialNumber存在的次数，判断customerMaterialNumber是否重复
	 * @param customerMaterialNumber
	 * @return
	 */
	public Integer getCountByCustomerMaterialNumber(String customerMaterialNumber);
	
	/**
	 * 查询supplierMaterialNumber存在的次数，判断supplierMaterialNumber是否重复
	 * @param supplierMaterialNumber
	 * @return
	 */
	public Integer getCountBySupplierMaterialNumber(String supplierMaterialNumber);
	
	/**
	 * 添加物料
	 * @param material
	 */
	public void addMaterial(Material material);
	
	/**
	 * 修改物料
	 * @param material
	 */
	public void updateMaterial(Material material);
	
	/**
	 * 删除物料
	 * @param material
	 */
	public void deleteMaterial(Material material);
	//根据前端传来的物料号模糊查询物料信息
	public List<MaterialDataForSearch> getDataForSearch(String  materialNumber);
	//查找全部的物料信息
	public List<String> getMaterials();
	//根据物料号精准查找物料信息
	public  Material getMaterialByNumber(String materialNumber);
	
}
