package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartflow.model.StationGroup;
import com.smartflow.model.Station_StationGroup;

/**
 * @author haita
 */
@Repository
public class StationGroupDaoImpl implements StationGroupDao {

	final
    HibernateTemplate hibernateTemplate;

    @Autowired
    public StationGroupDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
	public Integer getTotalCount(String groupNumber,String description) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession(); 
		String hql = "select count(*) from StationGroup where State !=- 1 ";
		if (groupNumber!=null) {
			hql += "and groupNumber like '%"+groupNumber+"%' ";
		}
		if (description!=null) {
			hql += "and description like '%"+description+"%'";
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
	public List<StationGroup> getStationGroupList
			(Integer pageIndex,
			 Integer pageSize,
			 String groupNumber,
			 String description) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from StationGroup where State !=- 1 ";
		if (groupNumber!=null) {
			hql += "and groupNumber like '%"+groupNumber+"%' ";
		}
		if (description!=null) {
			hql += "and description like '%"+description+"%'";
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
	public String getFactoryNameById(Integer factoryId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "select concat(f.factoryCode,'('+f.name+')') from Factory f where f.id = "+factoryId;
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
	public StationGroup getStationGroupById(Integer stationGroupId) {
		return hibernateTemplate.get(StationGroup.class, stationGroupId);
	}
	@Override
	public List<Map<String, Object>> getFactory() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			String sql = "select Id [key],Name label from core.Factory";
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
	public List<Map<String, Object>> getStationByStationGroupById(Integer stationGroupId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where State = 1 and Id in(select StationtId from core.Station_StationGroup where StationGroupId = :StationGroupId)";
		try{
			Query query = session.createSQLQuery(sql);
			query.setInteger("StationGroupId", stationGroupId);
			return query.
					setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).
					list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public List<Map<String, Object>> getStation() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where State = 1";
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
	public List<Map<String, Object>> getUseStation() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where State = 1 and (StationType = 1 or StationType = 12)";
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
	public Integer getCountByGroupNumber(String groupNumber) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from StationGroup where State != -1 and GroupNumber = :GroupNumber";
		try{
			Query query = session.createQuery(hql);
			query.setString("GroupNumber", groupNumber);
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
	public void addStationGroup(StationGroup stationGroup) {
		hibernateTemplate.save(stationGroup);
	}
	
	@Transactional
	@Override
	public void addStation_StationGroup(Station_StationGroup station_StationGroup) {
		hibernateTemplate.save(station_StationGroup);
	}
	
	@Transactional
	@Override
	public void updateStationGroup(StationGroup stationGroup) {
		hibernateTemplate.update(stationGroup);
	}
	
	@Transactional
	@Override
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup) {
		hibernateTemplate.update(station_StationGroup);
	}
	@Override
	public List<Station_StationGroup> getStation_StationGroupByStationGroupId(Integer stationGroupId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Station_StationGroup where StationGroupId = :StationGroupId";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("StationGroupId", stationGroupId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Transactional
	@Override
	public void deleteStationGroup(StationGroup stationGroup) {
		hibernateTemplate.delete(stationGroup);
	}

	@Override
	public List<String> getStationNameByStationGroupId(Integer stationGroupId) {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		try{
		String hql = "select concat(StationNumber,'('+Name+')') from Station where state = 1 and id in(select stationtId from Station_StationGroup where stationGroupId = :StationGroupId)";

			Query query = session.createQuery(hql);
			query.setInteger("StationGroupId", stationGroupId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getTraceStation() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where State = 1 and StationType=7";
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


}
