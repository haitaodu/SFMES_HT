package com.smartflow.dao;

import com.smartflow.model.Qualification;
import com.smartflow.model.StationAccessControl;
import com.smartflow.model.User_Qualification;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QualificationDaoImpl implements QualificationDao {
    @Autowired
    HibernateTemplate hibernateTemplate;
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void addQualification(Qualification qualification) {
        hibernateTemplate.save(qualification);
    }

    @Override
    public void addStationAccessControl(StationAccessControl stationAccessControl) {
        hibernateTemplate.save(stationAccessControl);
    }

    @Override
    public void updateQualification(Qualification qualification) {
        hibernateTemplate.update(qualification);
    }

    @Override
    public void updateStationAccessControl(StationAccessControl stationAccessControl) {
        hibernateTemplate.update(stationAccessControl);
    }

    @Override
    public StationAccessControl getStationAccessControlByStationId(Integer stationId) {
        String hql = "from StationAccessControl where Station.id = "+stationId;
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            List<StationAccessControl> stationAccessControlList = query.list();
            return stationAccessControlList.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
    public List<String> getStationAccessControlListByUserId(Integer userId) {
        String hql = "select name from Station where Id in(select Station.id from StationAccessControl where State = 1 and Qualification.Id in (select Qualification.Id from User_Qualification where User.id = "+userId+"))";
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
    public List<Map<String, Object>> getStationListByUserId(Integer userId) {
        String sql = "select Id [key],CONCAT(StationNumber,'('+Name+')') label from core.Station where Id in(select StationId from core.StationAccessControl where State = 1 and QualificationId in (select QualificationId from core.User_Qualification where UserId = "+userId+"))";
        Session session = sessionFactory.openSession();
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
    public List<StationAccessControl> getQualificationIdByStationIdList(List<Integer> stationIdList) {
        String hql = "from StationAccessControl where Station.id in :stationIdList";
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            query.setParameterList("stationIdList", stationIdList);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
    public void addUser_Qualification(User_Qualification userQualification) {
        hibernateTemplate.save(userQualification);
    }

    @Override
    public Qualification getQualificationById(Integer qualificationId) {
        return hibernateTemplate.get(Qualification.class, qualificationId);
    }



    @Override
    public void deleteUser_QualificationByUserId(Integer userId) {
        String hql = "delete from User_Qualification where User.id = "+userId;
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery(hql);
            query.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
