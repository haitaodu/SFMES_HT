package com.smartflow.dao;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smartflow.dto.bom.BomItemForEdite;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.BOMItemModel;
import com.smartflow.model.Material;
import com.smartflow.model.UserModel;
import com.smartflow.util.BOMDataForPage;
import com.smartflow.util.BOMItemData;
import com.smartflow.util.bom.AbstractBomUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author haita
 */
@Repository
public class BOMHeadDaoImpl implements BOMHeadDao {
	List<BOMItemData> bomItemDatas=new ArrayList<>();
	List<Integer> materialIds=new ArrayList<>();
	Integer rowCount=0;
	String m="●";
	int n=0;
	final
	HibernateTemplate hibernate;
	private final
	MaterialDao materialDao;
	final
	UserDao user;
	private final
	StationGroupDao stationGroup;
	private final
	FactoryDao factoryDao;
	private final
	UnitDao unitDao;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BOMHeadDaoImpl(HibernateTemplate hibernate, MaterialDao materialDao, UserDao user, StationGroupDao stationGroup, FactoryDao factoryDao, UnitDao unitDao) {
		this.hibernate = hibernate;
		this.materialDao = materialDao;
		this.user = user;
		this.stationGroup = stationGroup;
		this.factoryDao = factoryDao;
		this.unitDao = unitDao;
	}

	@Override
	public List<BOMDataForPage> readPageData(int pagesize,
											 int pageindex,
											 String materialNumberForSearch) {
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM BOMHeadModel  WHERE State!=-1 ";
		if (materialNumberForSearch!=null) {
			hql+="AND MaterialId IN(FROM Material WHERE MaterialNumber LIKE '%"
					+materialNumberForSearch+"%') ";
		}
		logger.info(new Date()+"查询BOM列表进入BOM数据库");
		Query query=session.createQuery(hql);
		query.setFirstResult((pageindex-1)*pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<BOMHeadModel> bomList=query.list();
		List<BOMDataForPage> bomDataForPages=new ArrayList<>();
		for (BOMHeadModel bomHeadModel : bomList) {
			Material material=materialDao.getDataById(bomHeadModel.getMaterialId());
			String materialNumber=material.getMaterialNumber();
			String  materialName=material.getDescription();
            String editor=null;
            String creator=null;
            if (bomHeadModel.getEditorId()!=null) {
            	UserModel u = user.getDataById(bomHeadModel.getEditorId());
            	if(u != null){
            		editor = u.getName();
            	}
			}
            if (bomHeadModel.getCreatorId()!=null) {
            	UserModel u = user.getDataById(bomHeadModel.getCreatorId());
            	if(u != null){
            		creator = u.getName();
            	}
			}
			BOMDataForPage bomDataForPage=new BOMDataForPage(bomHeadModel.getId(),
					bomHeadModel.getMaterialId(),
					materialNumber,
					bomHeadModel.getVersion(),
					bomHeadModel.getProductName(), bomHeadModel.getValidBegin(),
					bomHeadModel.getValidEnd(), creator, bomHeadModel.getCreationDateTime(),
					editor, bomHeadModel.getEditDateTime(),
					factoryDao.getDataById(bomHeadModel.getFactoryId()).getName(),materialName);
			bomDataForPages.add(bomDataForPage);
		}
		logger.debug(new Date()+"查询BOM列表出数据库");
		session.close();
		return bomDataForPages;
	}
	@Override
	public int countData(String materialNumberForSearch) {
	
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		
		String hql="SELECT COUNT (*)FROM BOMHeadModel WHERE State!=-1 ";
		if (materialNumberForSearch!=null) {
			hql+="AND MaterialId IN(FROM Material WHERE MaterialNumber LIKE '%"+materialNumberForSearch+"%') ";
		}
		Query  query=session.createQuery(hql);
		Integer rowCount=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return rowCount;
	}
	@Override
	public BOMDataForPage getDataInId(int i) {
		BOMHeadModel bomHeadModel=hibernate.get(BOMHeadModel.class, i);
		if (bomHeadModel!=null) {
			Material material=materialDao.getDataById(bomHeadModel.getMaterialId());
			String materialNumber=material.getMaterialNumber();
			String  materialName=material.getDescription();
            String creatorName=null;
            String editorName=null;
            if (bomHeadModel.getEditorId()!=null) {
            	editorName=user.getDataById(bomHeadModel.getEditorId()).getName();
			}
            if (bomHeadModel.getCreatorId()!=null) {
            	creatorName=user.getDataById(bomHeadModel.getCreatorId()).getName();
			}

			return new BOMDataForPage(bomHeadModel.getId(),
					bomHeadModel.getMaterialId(),
					materialNumber, bomHeadModel.getVersion(),
					bomHeadModel.getProductName(), bomHeadModel.getValidBegin(),
					bomHeadModel.getValidEnd(), creatorName,
					bomHeadModel.getCreationDateTime(), editorName,
					bomHeadModel.getEditDateTime(),
					bomHeadModel.getFactoryId().toString(),materialName);

		}
		else {
			return null;
		}

	}
	@Override
	public List<BomItemForEdite> getDataByIdInItem(int i) {
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM BOMItemModel WHERE BOMHeadId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", i);
		List<BOMItemModel> bomItemModels=query.list();
		session.close();
		List<BomItemForEdite> bomItemDatas=new ArrayList<>();
		int n=0;
		for (BOMItemModel bomItemModel : bomItemModels) {
			Material material=materialDao.getDataById(bomItemModel.getMaterialId());
			String materialNumber=material.getMaterialNumber();
			String  materialName=material.getDescription();
			int stationId=bomItemModel.getStationGroupId();
			int unitId=bomItemModel.getUnit();
			bomItemDatas.add(new BomItemForEdite(bomItemModel.getMaterialId(), materialName,
					materialNumber, bomItemModel.getDesignator(), bomItemModel.getQuantity(),
					stationId,
					stationGroup.getStationGroupById(bomItemModel.getStationGroupId()).getDescription(),
					bomItemModel.isIsNeedSetupCheck(),
					bomItemModel.getLayer(),
					bomItemModel.isIsAlternative(),
					unitDao.getUnitById(bomItemModel.getUnit()).getName(),
					unitId,n));
			n++;
		}
		return bomItemDatas;
	}
	@Transactional
	@Override
	public void delData(int i) {
		BOMHeadModel bModel=hibernate.get(BOMHeadModel.class, i);
		bModel.setState(-1);
		hibernate.update(bModel);
	}
	@Override
	public BOMHeadModel get_BOMHead_Data_ById(int i) {
		return hibernate.get(BOMHeadModel.class, i);
	}
	@Override
	@Transactional
	public Boolean add_BOMHead_Data(BOMHeadModel bomHeadModel, List<BOMItemModel> bomItemModels) {
		hibernate.save(bomHeadModel);
		int bomHeadId=bomHeadModel.getId();
		for (BOMItemModel bomItemModel : bomItemModels) {
			bomItemModel.setBOMHeadId(bomHeadId);
			bomItemModel.setLayer(1);
			bomItemModel.setDesignator("1");
			hibernate.save(bomItemModel);
		}
		return true;
	}
	@Transactional
	@Override
	public void delDataInEntity(int id) {
		Session session=hibernate.getSessionFactory().openSession();
		String sql="FROM BOMItemModel WHERE BOmHeadId=:Id";		
		Query query=session.createQuery(sql);
		query.setParameter("Id", id);
		@SuppressWarnings("unchecked")
		List<BOMItemModel> bomItemModels=query.list();
		session.close();
		for (BOMItemModel bomItemModel : bomItemModels) {
			hibernate.delete(bomItemModel);
		}
	}
	@Override
	public int countBOMItemByBOMHeadId(int id) {
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM BOMItemModel WHERE BOMHeadId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<BOMItemModel> bomItemModels=query.list();
		session.close();

		int n=0;
		for (BOMItemModel bomItemModel : bomItemModels) {
			n++;
		}
		return n;
	}
	@Override
	@Transactional
	public void addBOMItemByBOMHeadId(int id,List<BOMItemModel> bomItemModels) {
		for (BOMItemModel bomItemModel : bomItemModels) {
			bomItemModel.setBOMHeadId(id);
			bomItemModel.setLayer(1);
			bomItemModel.setDesignator("1");
			hibernate.save(bomItemModel);
		}

	}
	@Override
	@Transactional
	public void updateBOMHead(BOMHeadModel bomHeadModel) {
		hibernate.update(bomHeadModel);

	}
	@Override
	public List<BOMItemData> getDataByIdInItemAddBracket(int i) {
		bomItemDatas=new ArrayList<>();
		n=0;
		m="●";
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM BOMItemModel WHERE BOMHeadId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", i);
		List<BOMItemModel> bomItemModels=query.list();
		session.close();
		for (BOMItemModel bomItemModel : bomItemModels) {
			Material material=materialDao.getDataById(bomItemModel.getMaterialId());
			String materialNumber=material.getMaterialNumber();
			String  materialName=material.getDescription();
			bomItemDatas.add(new BOMItemData(bomItemModel.getMaterialId(),materialName,
					m+materialNumber+"("+materialName+")",
					bomItemModel.getDesignator(), bomItemModel.getQuantity(), 
					bomItemModel.getStationGroupId(), stationGroup.getStationGroupById(bomItemModel.getStationGroupId()).getDescription(),
					AbstractBomUtil.parseBoolenToString(bomItemModel.isIsNeedSetupCheck()), bomItemModel.getLayer(),
					AbstractBomUtil.parseBoolenToString(bomItemModel.isIsAlternative()),
					unitDao.getUnitById(bomItemModel.getUnit()).getName(),bomItemModel.getUnit(),n));
			n=n+1;
			m=m+"●";
			getChildData(bomItemModel.getMaterialId());
			m=m.substring(0, m.length()-1);
		}
		return bomItemDatas;
	}
	@Override
	public void getChildData(Integer  materialId) {
		@SuppressWarnings("unchecked")
		List<BOMHeadModel> bomHeadModels=(List<BOMHeadModel>) hibernate.findByNamedParam
				("FROM BOMHeadModel WHERE MaterialId=:materialId", "materialId", materialId);
		if (bomHeadModels.isEmpty()) {
             return;
		}
		else {
			Integer bomHeadId=bomHeadModels.get(0).getId();
			@SuppressWarnings("unchecked")
			List<BOMItemModel> bomItemModels=(List<BOMItemModel>) hibernate.findByNamedParam("FROM BOMItemModel WHERE BOMHeadId=:bomHeadId", "bomHeadId", bomHeadId);
			for (BOMItemModel bomItemModel : bomItemModels) {
				Material material=materialDao.getDataById(bomItemModel.getMaterialId());
				String materialNumber=material.getMaterialNumber();
				String  materialName=material.getDescription();
				bomItemDatas.add(new BOMItemData(bomItemModel.getMaterialId(),materialName, //bomItemModel.getVersion(),
						m+materialNumber+"("+materialName+")",
						bomItemModel.getDesignator(), bomItemModel.getQuantity(), 
						bomItemModel.getStationGroupId(), stationGroup.getStationGroupById(bomItemModel.getStationGroupId()).getDescription(),
						AbstractBomUtil.parseBoolenToString(bomItemModel.isIsNeedSetupCheck()), bomItemModel.getLayer(),
						AbstractBomUtil.parseBoolenToString(bomItemModel.isIsAlternative()),
						unitDao.getUnitById(bomItemModel.getUnit()).getName(),bomItemModel.getUnit(),n));
				n=n+1;
				m=m+"●";
				getChildData(bomItemModel.getMaterialId());
				m=m.substring(0, m.length()-1);
			}
		}
	}
	@Override
	public void init() {

		bomItemDatas=new ArrayList<>();

	
		materialIds=new ArrayList<>();
	}
	@Override
	public void getParentData(Integer materialId) {
		@SuppressWarnings("unchecked")
		List<BOMHeadModel> bomHeadModels=(List<BOMHeadModel>) hibernate.findByNamedParam("From BOMHeadModel Where Id in (SELECT BOMHeadId From BOMItemModel Where MaterialId=:MaterialId)", "MaterialId", materialId);
		if (bomHeadModels.isEmpty()) {
			materialIds.add(materialId);
			return ;
		}
		for (BOMHeadModel bomHeadModel : bomHeadModels) {
			getParentData(bomHeadModel.getMaterialId());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BOMDataForPage> readPageDataForParent(int pagesize, int pageindex, String materialNumberForSearch) {
		rowCount=0;
		Session session=hibernate.getSessionFactory().openSession();
		String hql=null;
		if (materialNumberForSearch!=null) {
			hql = "FROM Material WHERE MaterialNumber LIKE '%" + materialNumberForSearch + "%' and State=1 ";
		}
		Query query=session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Material> materialList=query.list();
		for (Material material : materialList) {
			getParentData(material.getId());
		}
		//及时关闭session
		session.close();
		List<BOMHeadModel> bomList=new ArrayList<>();
		List<BOMDataForPage> bomDataForPages=new ArrayList<>();
		for (Integer materialId : materialIds) {
			List<BOMHeadModel> bomHeadModels=
					(List<BOMHeadModel>)hibernate.findByNamedParam
							("FROM BOMHeadModel WHERE MaterialId=:MaterialId AND State=1", "MaterialId",materialId);
			bomList.add(bomHeadModels.get(0));
		}
		/*
		 * @autor Du
		 * @reason:这里引入了 PageHelper 这个java 插件，便于进行分页，也方便进行对总的条数进行统计
		 */
		PageHelper.startPage(pagesize, pageindex);
		PageInfo<BOMHeadModel> pageInfo=new PageInfo<>(bomList);
		List<BOMHeadModel> bomListForPage=pageInfo.getList();
		rowCount=pageInfo.getSize();
		for (BOMHeadModel bomHeadModel : bomListForPage) {
			Material material=materialDao.getDataById(bomHeadModel.getMaterialId());
			String materialNumber=material.getMaterialNumber();
			String  materialName=material.getDescription();

			BOMDataForPage bomDataForPage=new BOMDataForPage(bomHeadModel.getId(),
					bomHeadModel.getMaterialId(),
					materialNumber,
					bomHeadModel.getVersion(), materialName,
					bomHeadModel.getValidBegin(), bomHeadModel.getValidEnd(),
					user.getDataById(bomHeadModel.getCreatorId()).getName(),
					bomHeadModel.getCreationDateTime(),
					user.getDataById(bomHeadModel.getEditorId()).getName(),
					bomHeadModel.getEditDateTime(),
					factoryDao.getDataById(bomHeadModel.getFactoryId()).getName(),materialName);
			bomDataForPages.add(bomDataForPage);
		}
		return bomDataForPages;
	}
	@Override
	public Integer getRowCountForPageParent() {
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isRegisterMaterialNumber(String materialNumber,int version) {
		List<BOMHeadModel> bomHeadModels=(List<BOMHeadModel>)
				hibernate.findByNamedParam
						("From BOMHeadModel Where MaterialId  IN" +
								"(Select id From Material WHERE  " +
								"MaterialNumber=:materialNumber ) and Version=:version" +
										" and state!=-1",
								new String[]{"materialNumber","version"},new Object[]{materialNumber,version});
		return !bomHeadModels.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BOMHeadModel getRegisterBom(String materialNumber) {
		List<BOMHeadModel> bomHeadModels=getRegisterProduct(materialNumber);
		if (bomHeadModels.isEmpty())
		{
			return null;
		}
		return bomHeadModels.get(bomHeadModels.size()-1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BOMHeadModel> getRegisterProduct(String materialNumber) {
		return (List<BOMHeadModel>)
				hibernate.findByNamedParam
						(" From BOMHeadModel " +
										"Where state!=-1 and MaterialId  IN" +
										"(Select id From Material WHERE  " +
										"MaterialNumber=:materialNumber )" +
										" order by Version",
								"materialNumber",materialNumber);

	}


}
