package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StationDaoImpl implements StationDao{

	@Autowired
	HibernateTemplate hibernateTemplate;


	@Override
	public Integer getTotalCount(String stationNumber,String stationName, String ipAddress, Integer stationType) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "select count(*) from Station where State !=- 1 ";
		if (stationNumber!=null) {
			hql += "and stationNumber like '%"+stationNumber+"%' ";
		}
		if (stationName!=null) {
			hql += "and name like '%"+stationName+"%'";
		}
		if(ipAddress != null){
			hql += "and ipAddress = '"+ipAddress+"'";
		}
		if(stationType != null && stationType != 0){
			hql += " and stationType = "+stationType;
		}
		try{
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();		
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Override
	public List<Station> getStationList(Integer pageIndex,Integer pageSize,String stationNumber,String stationName, String ipAddress, Integer stationType) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Station where State !=- 1 ";
		if (stationNumber!=null) {
			hql += "and stationNumber like '%"+stationNumber+"%' ";
		}
		if (stationName!=null) {
			hql += "and name like '%"+stationName+"%'";
		}
		if(ipAddress != null){
			hql += "and ipAddress = '"+ipAddress+"'";
		}
		if(stationType != null && stationType != 0){
			hql += " and stationType = "+stationType;
		}
		try{
			Query query = session.createQuery(hql);
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public String getUserNameById(Integer userId) {	
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
//		String sql = "select UserName from core.[User] where Id = "+userId;
//		Query query = session.createSQLQuery(sql);
		String hql = "select u.userName from User u where u.id = "+userId;
		try{
			Query query = session.createQuery(hql);
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
	public Station getStationById(Integer stationId) {		
		return hibernateTemplate.get(Station.class, stationId);
	}

	@Override
	public List<Map<String, Object>> getStationGroup() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],concat(GroupNumber,'('+Description+')') label from core.StationGroup where State = 1";
		try{
			Query query = session.createSQLQuery(sql);//.addScalar("key", StandardBasicTypes.INTEGER).addScalar("label", StandardBasicTypes.STRING);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Integer getCountByStationNumber(String stationNumber) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "select count(*) from Station where StationNumber = :StationNumber";
		try{
			Query query = session.createQuery(hql);
			query.setString("StationNumber", stationNumber);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}	
	
	@Transactional
	@Override
	public void addStation(Station station) {
		hibernateTemplate.save(station);
	}

	@Override
	public List<Map<String, Object>> getFactory() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();  
		String sql = "select Id [key],concat(FactoryCode,'('+Name+')') label from core.Factory";
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
	public List<Map<String, Object>> getStationGroupByStationId(Integer stationId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select distinct Id [key],concat(GroupNumber,'('+Description+')') label from core.StationGroup where State = 1 and Id in (select StationGroupId from core.Station_StationGroup where StationtId = "+stationId+");";
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

	@Transactional
	@Override
	public void updateStation(Station station) {
		hibernateTemplate.update(station);
	}

	@Transactional
	@Override
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup) {
		hibernateTemplate.update(station_StationGroup);
	}

//	@Transactional
//	@Override
//	public void deleteStation(Station station) {
//		hibernateTemplate.delete(station);
//	}

	@Transactional
	@Override
	public void deleteStation_StationGroup(Station_StationGroup station_StationGroup) {
		hibernateTemplate.delete(station_StationGroup);		
	}

	@Override
	public List<Station_StationGroup> getStation_StationGroupByStationId(Integer stationId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Station_StationGroup where StationtId = :StationtId";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("StationtId", stationId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<String> getStationGroupNameByStationId(Integer stationId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "select concat(GroupNumber,'('+Description+')') from StationGroup where state = 1 and id in (select stationGroupId from Station_StationGroup where stationtId = :StationId)";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("StationId", stationId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getWashList() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where State = 1 and StationType=7 or StationType=8";
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
	public List<Map<String, Object>> getStationTypeList() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],StationTypeName label from core.StationType";
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
	public String getStationTypeNameByStationTypeId(Integer stationTypeId) {
		StationType stationType = hibernateTemplate.get(StationType.class, stationTypeId);
		return stationType == null ? null : stationType.getStationTypeName();
	}
}
