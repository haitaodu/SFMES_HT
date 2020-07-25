package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.model.Material;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.smartflow.dao.BOMHeadDao;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.BOMItemModel;
import com.smartflow.util.BOMDataForPage;
import com.smartflow.util.BOMItemData;
@Service
public class BOMHeadServiceImpl implements BOMHeadService {
private final
BOMHeadDao bom;
private final
HibernateTemplate  hibernateTemplate;
private  final
MaterialService materialService;

	@Autowired
	public BOMHeadServiceImpl(BOMHeadDao bom, HibernateTemplate hibernateTemplate
	,MaterialService materialService) {
		this.bom = bom;
		this.hibernateTemplate = hibernateTemplate;
		this.materialService=materialService;
	}

	@Override
	public List<BOMDataForPage> getPageData(int pagesize, int pageindex,String materialNumberForSearch) {
		return bom.readPageData(pagesize, pageindex,materialNumberForSearch);
	}
	@Override
	public int getRowCount(String materialNumberForSearch) {
		return bom.countData(materialNumberForSearch);
	}
	@Override
	public BOMDataForPage getBomDataById(int i) {
		return bom.getDataInId(i);
	}
	@Override
	public List<BOMItemData> getBOMItemGetById(int i) {
		return  bom.getDataByIdInItem(i);
	}
	@Override
	public void delDataById(int i) {
		bom.delData(i);
	}
	@Override
	public BOMHeadModel get_BOMHead_Data_ById(int i) {
		return bom.get_BOMHead_Data_ById(i);
	}
	@Override
	public Boolean add_BOMHead_Data(BOMHeadModel bomHeadModel, List<BOMItemModel> bomItemModels) {
		return bom.add_BOMHead_Data(bomHeadModel, bomItemModels);
	}
	@Override
	public void upDate_BOMHead_Data(BOMHeadModel bomHeadModel, List<BOMItemModel> bomItemModels) {
		bom.delDataInEntity(bomHeadModel.getId());
		bom.updateBOMHead(bomHeadModel);
		bom.addBOMItemByBOMHeadId(bomHeadModel.getId(),bomItemModels);
	}
	@Override
	public int countBOMItemByBOMHeadId(int id) {
		return bom.countBOMItemByBOMHeadId(id);
	}
	@Override
	public List<BOMItemData> getDataByIdInItemAddBracket(int i) {
		return bom.getDataByIdInItemAddBracket(i);
	}
	@Override
	public List<BOMDataForPage> readPageDataForParent(int pagesize, int pageindex, String materialNumberForSearch) {
		return bom.readPageDataForParent(pagesize, pageindex, materialNumberForSearch);
	}
	@Override
	public Integer getRowCountForPageParent() {
		return bom.getRowCountForPageParent();
	}

	@Override
	public boolean isRegisterMaterialNumber(String materialNumber,int version) {
		return bom.isRegisterMaterialNumber(materialNumber,version);
	}

	/**
	 * 根据物料号查询到物料类型为1时，则为原材料不可生成bomhead
	 * @param materialNumber 物料号
	 * @return 返回是否可以注册
	 */
	@Override
	public boolean isCanRegister(String materialNumber) {
		Material material=
				materialService.getMaterialByNumber(materialNumber);

		if (material.getMaterialGroupType()==null) {
			return true;
		}
		return material.getMaterialGroupType() != 1;
	}

	@Override
	public List<Map<String, Object>> getBOMHeadList() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select distinct bom.Id [key],m.MateriaLNumber label from core.BOMHead as bom join core.Material as m on bom.State = 1 and  m.Id=bom.MaterialId";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public BOMHeadModel getRegisterBom(String materialNumber) {
		return bom.getRegisterBom(materialNumber);
	}

	@Override
	public List<Map<String, Object>> getProdcutNameList() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select distinct bom.Id [key]," +
				"bom.ProductName label from core.BOMHead bom where bom.State = 1 ";
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			return null;
		}finally{
			session.close();
		}
	}

}
