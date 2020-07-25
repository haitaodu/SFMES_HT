package com.smartflow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.dto.GetDTOByConditionOfMaterialListDTO;
import com.smartflow.model.Material;
import com.smartflow.util.MaterialDataForSearch;

@Repository
public class MaterialDaoImpl implements MaterialDao{

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public Integer getMaterialTotalCount(GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from Material where State !=- 1 ";
		if (getDTOByConditionOfMaterialListDTO.getMaterialNumber()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getMaterialNumber())) {
			hql += "and materialNumber like '%"+getDTOByConditionOfMaterialListDTO.getMaterialNumber()+"%' ";
		}
		if (getDTOByConditionOfMaterialListDTO.getDescription()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getDescription())) {
			hql += "and description like '%"+getDTOByConditionOfMaterialListDTO.getDescription()+"%' ";
		}
		if (getDTOByConditionOfMaterialListDTO.getMaterialGroupType()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getMaterialGroupType())) {
			hql += "and materialGroupType = "+getDTOByConditionOfMaterialListDTO.getMaterialGroupType();
		}
//		if (getDTOByConditionOfMaterialListDTO.getIsProduct()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getIsProduct())) {
//			hql += "and isProduct = "+getDTOByConditionOfMaterialListDTO.getIsProduct();
//		}
		if (getDTOByConditionOfMaterialListDTO.getFactoryId()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getFactoryId())) {
			hql += "and factoryId = "+getDTOByConditionOfMaterialListDTO.getFactoryId();
		}
		try{
			Query query = session.createQuery(hql);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Material> getMaterialByCondition(
			GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from Material where State !=- 1 ";
		if (getDTOByConditionOfMaterialListDTO.getMaterialNumber()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getMaterialNumber())) {
			hql += "and materialNumber like '%"+getDTOByConditionOfMaterialListDTO.getMaterialNumber()+"%' ";
		}
		if (getDTOByConditionOfMaterialListDTO.getDescription()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getDescription())) {
			hql += "and description like '%"+getDTOByConditionOfMaterialListDTO.getDescription()+"%' ";
		}
		if (getDTOByConditionOfMaterialListDTO.getMaterialGroupType()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getMaterialGroupType())) {
			hql += "and materialGroupType = "+getDTOByConditionOfMaterialListDTO.getMaterialGroupType();
		}
//		if (getDTOByConditionOfMaterialListDTO.getIsProduct()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getIsProduct())) {
//			hql += "and isProduct = "+getDTOByConditionOfMaterialListDTO.getIsProduct();
//		}
		if (getDTOByConditionOfMaterialListDTO.getFactoryId()!=null && !"".equals(getDTOByConditionOfMaterialListDTO.getFactoryId())) {
			hql += "and factoryId = "+getDTOByConditionOfMaterialListDTO.getFactoryId();
		}
		try{
			Query query = session.createQuery(hql);
			query.setFirstResult((getDTOByConditionOfMaterialListDTO.getPageIndex()-1)*getDTOByConditionOfMaterialListDTO.getPageSize());
			query.setMaxResults(getDTOByConditionOfMaterialListDTO.getPageSize());
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public String getCompanyNumberByCompanyId(Integer companyId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select concat(companyCode,'('+name+')') from Company where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", companyId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public String getStationGroupNumberByStationGroupId(Integer stationGroupId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select concat(groupNumber,'('+description+')') from StationGroup where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", stationGroupId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		
	}

	@Override
	public String getLocationNumberByLocationId(Integer locationId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select concat(locationNumber,'('+description+')') from Location where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", locationId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}		
	}

	@Override
	public String getMaterialGroupTypeNameByMaterialGroupTypeId(Integer materialGroupTypeId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select name from MaterialGroup where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", materialGroupTypeId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

		
	}

	@Override
	public String getMSLNameByMSLId(Integer mslId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select name from MSL where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", mslId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Override
	public String getProcurementTypeNameByProcurementTypeId(Integer procurementTypeId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select name from ProcurementType where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", procurementTypeId);
			return query.uniqueResult().toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public String getUnitNameByUnitId(Integer unitId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select name from Unit where id = :id";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("id", unitId);
			if (query.uniqueResult()!=null) {
				return query.uniqueResult().toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Material getMaterialById(Integer materialId) {
		return hibernateTemplate.get(Material.class, materialId);
	}

	@Override
	public List<Map<String, Object>> getMaterialTypeNameAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],CONCAT(MaterialGroupCode,'('+Name+')') label from core.MaterialGroup";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		
	}

	@Override
	public List<Map<String, Object>> getUnitNameAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],Name label from core.Unit where State = 1";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getProcurementTypeNameAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],concat(ProcurementCode,'('+Name+')') label from core.ProcurementType";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getLocationNumberAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],concat(LocationNumber,'('+Description+')') label from core.Location where State = 1 and AreaId in (select Id from core.Area where State = 1)";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getMSLNameAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],Name label from core.MSL";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getStationGroupNumberAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],CONCAT(GroupNumber,'('+Description+')') label from core.StationGroup where State = 1";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getCompanyNameAndId() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],concat(CompanyCode,'('+Name+')') label from core.Company";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Integer getCountByMaterialNumber(String materialNumber) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from Material where MaterialNumber = :MaterialNumber";
		try{
			Query query = session.createQuery(hql);
			query.setString("MaterialNumber", materialNumber);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	
	@Override
	public Integer getCountByCustomerMaterialNumber(String customerMaterialNumber) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from Material where CustomerMaterialNumber = :CustomerMaterialNumber";
		try{
			Query query = session.createQuery(hql);
			query.setString("CustomerMaterialNumber", customerMaterialNumber);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Integer getCountBySupplierMaterialNumber(String supplierMaterialNumber) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from Material where SupplierMaterialNumber = :SupplierMaterialNumber";
		try{
			Query query = session.createQuery(hql);
			query.setString("SupplierMaterialNumber", supplierMaterialNumber);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public void addMaterial(Material material) {
		hibernateTemplate.save(material);
	}

	@Override
	public void updateMaterial(Material material) {
		hibernateTemplate.update(material);
	}

	@Override
	public void deleteMaterial(Material material) {
		hibernateTemplate.delete(material);
	}

	@Override
	public Material getDataById(int id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Material.class, id);
	}

	@Override
	public List<MaterialDataForSearch> getDataForSearch(String materialNumber) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		String hql="FROM Material WHERE State !=- 1 and MaterialNumber LIKE '%'+MaterialName+'%'";
		Query query=session.createQuery(hql);
		query.setParameter("MaterialName", materialNumber);
		@SuppressWarnings("unchecked")
		List<Material> materialList=query.list();
		session.close();
		List<MaterialDataForSearch> materialDataForSearchs=new ArrayList<>();
 		for (Material material : materialList) {
			MaterialDataForSearch materialDataForSearch=new  MaterialDataForSearch();
			materialDataForSearch.setMaterialName(material.getDescription());
			materialDataForSearch.setMaterialNumber(material.getMaterialNumber());
			materialDataForSearchs.add(materialDataForSearch);
		}
		return materialDataForSearchs;
	}

	@Override
	public List<String> getMaterials() {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		String hql="SELECT materialNumber FROM Material";
		Query query=session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> materialList=query.list();
		session.close();
		return materialList;
	}

	@Override
	public Material getMaterialByNumber(String materialNumber) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		String hql="FROM Material WHERE MaterialNumber=:materialNumber";
		
		Query query=session.createQuery(hql);
		query.setParameter("materialNumber", materialNumber);
		@SuppressWarnings("unchecked")
		List<Material> materials=query.list();
		session.close();
		if (materials.size()==0) {
			return null;
		}
		return materials.get(0);
	}
	
}
