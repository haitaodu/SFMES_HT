package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartflow.dao.MaterialDao;
import com.smartflow.dto.GetDTOByConditionOfMaterialListDTO;
import com.smartflow.model.Material;
import com.smartflow.util.MaterialDataForSearch;
@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialDao;
	
	@Override
	public Integer getMaterialTotalCount(GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO) {
		return materialDao.getMaterialTotalCount(getDTOByConditionOfMaterialListDTO);
	}

	@Override
	public List<Material> getMaterialByCondition(
			GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO) {
		return materialDao.getMaterialByCondition(getDTOByConditionOfMaterialListDTO);
	}

	@Override
	public String getCompanyNumberByCompanyId(Integer companyId) {
		return materialDao.getCompanyNumberByCompanyId(companyId);
	}

	@Override
	public String getStationGroupNumberByStationGroupId(Integer stationGroupId) {
		return materialDao.getStationGroupNumberByStationGroupId(stationGroupId);
	}

	@Override
	public String getLocationNumberByLocationId(Integer locationId) {
		return materialDao.getLocationNumberByLocationId(locationId);
	}

	@Override
	public String getMaterialGroupTypeNameByMaterialGroupTypeId(Integer materialGroupTypeId) {
		return materialDao.getMaterialGroupTypeNameByMaterialGroupTypeId(materialGroupTypeId);
	}

	@Override
	public String getMSLNameByMSLId(Integer mslId) {
		return materialDao.getMSLNameByMSLId(mslId);
	}
	
	@Override
	public String getProcurementTypeNameByProcurementTypeId(Integer procurementTypeId) {
		return materialDao.getProcurementTypeNameByProcurementTypeId(procurementTypeId);
	}

	@Override
	public String getUnitNameByUnitId(Integer unitId) {
		return materialDao.getUnitNameByUnitId(unitId);
	}

	@Override
	public Material getMaterialById(Integer materialId) {
		return materialDao.getMaterialById(materialId);
	}

	@Override
	public List<Map<String, Object>> getMaterialTypeNameAndId() {
		return materialDao.getMaterialTypeNameAndId();
	}

	@Override
	public List<Map<String, Object>> getUnitNameAndId() {
		return materialDao.getUnitNameAndId();
	}

	@Override
	public List<Map<String, Object>> getProcurementTypeNameAndId() {
		return materialDao.getProcurementTypeNameAndId();
	}

	@Override
	public List<Map<String, Object>> getLocationNumberAndId() {
		return materialDao.getLocationNumberAndId();
	}

	@Override
	public List<Map<String, Object>> getMSLNameAndId() {
		return materialDao.getMSLNameAndId();
	}

	@Override
	public List<Map<String, Object>> getStationGroupNumberAndId() {
		return materialDao.getStationGroupNumberAndId();
	}

	@Override
	public List<Map<String, Object>> getCompanyNameAndId() {
		return materialDao.getCompanyNameAndId();
	}

	@Override
	public Integer getCountByMaterialNumber(String materialNumber) {
		return materialDao.getCountByMaterialNumber(materialNumber);
	}
	

	@Override
	public Integer getCountByCustomerMaterialNumber(String customerMaterialNumber) {
		return materialDao.getCountByCustomerMaterialNumber(customerMaterialNumber);
	}

	@Override
	public Integer getCountBySupplierMaterialNumber(String supplierMaterialNumber) {
		return materialDao.getCountBySupplierMaterialNumber(supplierMaterialNumber);
	}
	
	@Transactional
	@Override
	public void addMaterial(Material material) {
		materialDao.addMaterial(material);
	}

	@Transactional
	@Override
	public void updateMaterial(Material material) {
		materialDao.updateMaterial(material);
	}

	@Override
	public void deleteMaterial(Material material) {
		materialDao.deleteMaterial(material);
	}

	@Override
	public List<MaterialDataForSearch> getDataForSearch(String materialNumber) {
		// TODO Auto-generated method stub
		return materialDao.getDataForSearch(materialNumber);
	}

	@Override
	public List<String> getMaterials() {
		// TODO Auto-generated method stub
		return materialDao.getMaterials();
	}

	@Override
	public Material getMaterialByNumber(String materialNumber) {
		// TODO Auto-generated method stub
		return materialDao.getMaterialByNumber(materialNumber);
	}


}
