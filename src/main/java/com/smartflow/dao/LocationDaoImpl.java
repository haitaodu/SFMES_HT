package com.smartflow.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.model.LocationModel;
import com.smartflow.model.UserModel;
import com.smartflow.util.LocationDataForPage;
@Repository
public class LocationDaoImpl implements LocationDao{
@Autowired
HibernateTemplate hibernate;
@Autowired
UserDao user;
@Autowired
AreaDao area;
	@Override
	public int getCount(String locationNumber,String description,Integer areaId) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="SELECT COUNT (*)FROM LocationModel WHERE State!=-1  And AreaId In (Select Id From AreaModel Where State!=-1)";
		if (locationNumber!=null && !"".equals(locationNumber)) {
			hql += "and LocationNumber like '%"+locationNumber+"%' ";
		}
		if (description!=null && !"".equals(description)) {
			hql += "and Description like '%"+description+"%' ";
		}
		if (areaId!=null) {
			hql += "and AreaId = "+areaId;
		}
		Query query=session.createQuery(hql);
		Integer count=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return count;
	}

	@Override
	public List<LocationDataForPage> getPageData(int pageindex, int pagesize,String locationNumber,String description,Integer areaId) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM LocationModel  WHERE State!=-1 And AreaId In (Select Id From AreaModel Where State!=-1) ";
		if (locationNumber!=null && !"".equals(locationNumber)) {
			hql += "and LocationNumber like '%"+locationNumber+"%' ";
		}
		if (description!=null && !"".equals(description)) {
			hql += "and Description like '%"+description+"%' ";
		}
		if (areaId!=null) {
			hql += "and AreaId = "+areaId;
		}
	    Query query=session.createQuery(hql);
	    query.setFirstResult((pageindex-1)*pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<LocationModel> locationModels=query.list();
		//及时关闭session
		
		List<LocationDataForPage> locationDataForPages=new ArrayList<>();
		for (LocationModel locationModel : locationModels) {
			String state=null;
			if (locationModel.getState()==1) {
				state="已激活";
				
			}
			else if (locationModel.getState()==0) {
				state="未激活";
			}
			else {
				state="已删除";
			}
		String creatorName=null;
		if (locationModel.getCreatorId()!=null) {
			UserModel u = user.getDataById(locationModel.getCreatorId());
			if(u != null){
				creatorName=u.getName();
			}
		}
		String editorName=null;
		if (locationModel.getEditorId()!=null) {
			UserModel u = user.getDataById(locationModel.getEditorId());
			if(u != null){
				editorName=u.getName();
			}
		}
		LocationDataForPage locationDataForPage=new LocationDataForPage(locationModel.getId(), locationModel.getLocationNumber(), locationModel.getDescription(),area.getDataById(locationModel.getAreaId()).getName() , locationModel.getX(),locationModel.getY(), locationModel.getZ(), state, locationModel.getCreateDateTime(), creatorName, editorName, locationModel.getEditDateTime());
		locationDataForPages.add(locationDataForPage);
		}
		session.close();
		return locationDataForPages;
	}

	@Override
	public LocationDataForPage getLocationData(int id) {
		// TODO Auto-generated method stub
		LocationModel locationModel=hibernate.get(LocationModel.class, id);
		if (locationModel==null) {
			return null;
		}
		String state=null;
		if (locationModel.getState()==1) {
			state="已激活";
			
		}
		else if (locationModel.getState()==0) {
			state="未激活";
		}
		else {
			state="已删除";
		}
	String creatorName=null;
	if (locationModel.getCreatorId()!=null) {
		creatorName=user.getDataById(locationModel.getCreatorId()).getName();
	}
	String editorName=null;
	if (locationModel.getEditorId()!=null) {
		
		editorName=user.getDataById(locationModel.getEditorId()).getName();
	}
	LocationDataForPage locationDataForPage=new LocationDataForPage(locationModel.getId(), locationModel.getLocationNumber(), locationModel.getDescription(),area.getDataById(locationModel.getAreaId()).getName() , locationModel.getX(),locationModel.getY(), locationModel.getZ(), state, locationModel.getCreateDateTime(), creatorName, editorName, locationModel.getEditDateTime());
	return locationDataForPage;
	}

	@Override
	@Transactional
	public void delDataById(int id) {
		// TODO Auto-generated method stub
		LocationModel locationModel=hibernate.get(LocationModel.class, id);
	    locationModel.setState(-1);
	    locationModel.setLocationNumber("Del@"+locationModel.getLocationNumber());
	    hibernate.update(locationModel);
	}

	@Override
	public LocationModel getDataById(int id) {
		// TODO Auto-generated method stub
		return hibernate.get(LocationModel.class, id);
	}

	@Override
	@Transactional
	public void postData(LocationModel locationModel) {
		// TODO Auto-generated method stub
		hibernate.save(locationModel);
		
	}

	@Override
	@Transactional
	public void upDate(LocationModel locationModel) {
		// TODO Auto-generated method stub
		hibernate.update(locationModel);
		
	}

	@Override
	public int getDataForCheckUnique(String LocationNumber, String Description) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="SELECT COUNT (*)FROM LocationModel WHERE State!=-1 AND LocationNumber=:LocationNumber AND Description=:Description ";
		Query query=session.createQuery(hql);
		query.setParameter("Description", Description);
		query.setParameter("LocationNumber", LocationNumber);
		Integer count=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return count;
	}

	@Override
	public boolean isRegisterLocationNumber(String LocationNumber) {
		List<LocationModel> locationModels=(List<LocationModel>)hibernate.findByNamedParam
				("From LocationModel Where LocationNumber=:locationNumber and State!=0",
						"locationNumber",LocationNumber);
		if(locationModels.size()>1)
		{
			return  true;
		}
		return  false;
	}


}
