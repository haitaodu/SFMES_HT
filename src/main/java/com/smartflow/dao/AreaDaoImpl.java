package com.smartflow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.model.AreaModel;
import com.smartflow.model.UserModel;
import com.smartflow.util.AreaDataForPage;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AreaDaoImpl implements AreaDao{
	@Autowired
	HibernateTemplate  hibernate;
	@Autowired
	FactoryDao factory;
	@Autowired
	UserDao user;
	private  static  int KEY_0=0;
	private  static  int KEY_1=1;
	private  static  int KEY_2=2;
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaDataForPage> readPageData(int pagesize,
											  int pageindex,
											  String areaNumber,String areaName,Integer factoryId) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM AreaModel  WHERE State!=-1 ";
		Query query=session.createQuery(hqlForPageList
				(hql,areaNumber, areaName, factoryId));
		query.setFirstResult((pageindex-1)*pagesize);
		query.setMaxResults(pagesize);
	    List<AreaModel> areaModels=query.list();
		session.close();
		List<AreaDataForPage> areaDataForPages=new ArrayList<>();
		for (AreaModel areaModel : areaModels) {
			String state;
			if (areaModel.getState()==KEY_1) {
				state="已激活";
				
			}
			else if (areaModel.getState()==KEY_0) {
				state="未激活";
			}
			else {
				state="已删除";
			}
			String areaType=null;

			 if (areaModel.getAreaType()==KEY_0) {
				areaType="虚拟";
			}
			 if (areaModel.getAreaType()==KEY_2) {
				areaType="车间";
			}
			 if (areaModel.getAreaType()==KEY_1) {
				areaType="仓库";
			}
			String EditorName=null;
			if (areaModel.getEditorId()!=null) {
				UserModel u = user.getDataById(areaModel.getEditorId());
				if(u != null){
					EditorName=u.getName();
				}
			}
			AreaDataForPage areaDataForPage=new AreaDataForPage(areaModel.getId(),
					areaModel.getAreaNumber(), areaModel.getName(), areaModel.getDescription(),
					areaModel.getFactoryId(),
					factory.getDataById(areaModel.getFactoryId()).getName(),
					state,
					user.getDataById(areaModel.getCreatorId()) == null ?
							null : user.getDataById(areaModel.getCreatorId()).getName(),
					areaModel.getCreationDateTime(),
					EditorName, areaModel.getEditDateTime(),areaType);
		    areaDataForPages.add(areaDataForPage);
		    
		}
		return areaDataForPages;
	}

	@Override
	public int countData(String areaNumber,String areaName,Integer factoryId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM AreaModel WHERE State != -1 ";
		Query  query=session.createQuery(
				hqlForPageList(hql,areaNumber,areaName,factoryId));
		int rowCount= Integer.parseInt(query.uniqueResult().toString());
		session.close();
		return rowCount;
	}

	@Override
	public AreaModel getDataById(int id) {
		// TODO Auto-generated method stub
		return hibernate.get(AreaModel.class, id);
	}

	@Override
	public AreaDataForPage getDataInId(int id) {
		// TODO Auto-generated method stub
		String state;
		AreaModel areaModel=hibernate.get(AreaModel.class, id);
		if(areaModel.getState()==KEY_1)
		{
			state="1";
		}
		else if(areaModel.getState()==KEY_0)
		{
			state="0";
		}
		else
		{
			state="-1";
		}
		String EditorName=null;
		
		if (areaModel.getEditorId()!=null) {
			EditorName=user.getDataById(areaModel.getEditorId()).getName();
		}
		String areaType=null;
		if (areaModel.getAreaType()==null) {
			areaType="";
		}
		else if (areaModel.getAreaType()==KEY_1) {
			areaType="1";
		}
		else if (areaModel.getAreaType()==KEY_0) {
			areaType="0";
		}
		else if (areaModel.getAreaType()==KEY_2) {
			areaType="2";
		}
		return new AreaDataForPage(areaModel.getId(),
				areaModel.getAreaNumber(),
				areaModel.getName(),
				areaModel.getDescription(),
				areaModel.getFactoryId(),
				factory.getDataById(areaModel.getFactoryId()).getName(),
				state,
				user.getDataById(areaModel.getCreatorId()).getName(),
				areaModel.getCreationDateTime(),
				EditorName,
				areaModel.getEditDateTime(),
				areaType);

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delByChangeData(int id) {
		// TODO Auto-generated method stub
		AreaModel areaModel=hibernate.get(AreaModel.class, id);
		areaModel.setState(-1);
		areaModel.setAreaNumber("Del@"+areaModel.getAreaNumber());
		hibernate.update(areaModel);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void postData(AreaModel areaModel) {
		// TODO Auto-generated method stub
		hibernate.save(areaModel);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void upDate(AreaModel areaModel) {
		// TODO Auto-generated method stub
		hibernate.update(areaModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAreaList() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = hibernate.getSessionFactory();
		Session session = sessionFactory.openSession();  
		String sql = "select Id [key],Name label from core.Area WHERE State != -1";
		Query query = session.createSQLQuery(sql);
	
		List<Map<String, Object>> areaList=query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		session.close();
		return areaList;
	}

	@Override
	public int getDataForCheckUnique(String AreaNumber, String Descriprion) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM AreaModel" +
				" WHERE State!=-1 AND AreaNumber=:AreaNumber " +
				"AND Description=:Description ";
		Query  query=session.createQuery(hql);
		query.setParameter("AreaNumber", AreaNumber);
		query.setParameter("Description", Descriprion);
		int returnResult= Integer.parseInt(query.uniqueResult().toString());
		session.close();
		return returnResult;
	}

	@Override
	public List<Map<String, Object>> getAreaIdAndName() {
		Session session = hibernate.getSessionFactory().openSession();
		String sql = "select Id [key],Name label from core.Area WHERE State=1";
		Query query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list=query.setResultTransformer
				(Transformers.ALIAS_TO_ENTITY_MAP).list();
		session.close();
		return list;
	
	}

	@Override
	public boolean isRegisterAreaNumber(String areaNumber) {
		@SuppressWarnings("unchecked")
		List<AreaModel> areaModels=(List<AreaModel>)
				hibernate.findByNamedParam
						("From AreaModel Where AreaNumber=:areaNumber",
								"areaNumber",areaNumber);
		return areaModels.size() >= 1;
	}

	/**
	 *分页查询语句的判断
	 * @param hql 未加工的分页查询语句
	 * @param areaNumber arg0
	 * @param areaName arg1
	 * @param factoryId arg2
	 * @return 返回hql用于分页插叙
	 */
	private String hqlForPageList
	(String hql, String areaNumber, String areaName, Integer factoryId)
	{
		if (areaNumber!=null && !"".equals(areaNumber)) {
			hql += "and AreaNumber like '%"+areaNumber+"%' ";
		}
		if (areaName!=null && !"".equals(areaName)) {
			hql += "and Name like '%"+areaName+"%' ";
		}
		if (factoryId!=null) {
			hql += "and FactoryId = "+factoryId;
		}
		return hql;
	}
}
