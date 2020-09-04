package com.smartflow.dao;

import com.smartflow.dto.GetJPHListConditionInputDTO;
import com.smartflow.model.JPH;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class JPHDaoImpl implements JPHDao {
    final
    HibernateTemplate hibernateTemplate;

    @Autowired
    public JPHDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Integer getTotalCountJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO) {
        String hql = "select count(*) from JPH where State != -1 ";
        if(!StringUtils.isEmpty(getJPHListConditionInputDTO.getMaterialNumber())){
            hql += " and Material.MaterialNumber like '%"+getJPHListConditionInputDTO.getMaterialNumber()+"%'";
        }
        if(!StringUtils.isEmpty(getJPHListConditionInputDTO.getLocationId())) {
            hql += " and Location.Id = "+getJPHListConditionInputDTO.getLocationId();
        }
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            return query.uniqueResult() == null ? 0 : Integer.parseInt(query.uniqueResult().toString());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<JPH> getJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO) {
        String hql = "from JPH where State != -1 ";
        if(!StringUtils.isEmpty(getJPHListConditionInputDTO.getMaterialNumber())){
            hql += " and Material.MaterialNumber like '%"+getJPHListConditionInputDTO.getMaterialNumber()+"%'";
        }
        if(!StringUtils.isEmpty(getJPHListConditionInputDTO.getLocationId())) {
            hql += " and Location.Id = "+getJPHListConditionInputDTO.getLocationId();
        }
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            query.setFirstResult((getJPHListConditionInputDTO.getPageIndex()-1)*getJPHListConditionInputDTO.getPageSize());
            query.setMaxResults(getJPHListConditionInputDTO.getPageSize());
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addJPH(JPH jph) {
        hibernateTemplate.save(jph);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJPH(JPH jph) {
        hibernateTemplate.update(jph);
    }

    @Override
    public JPH getJPHById(Integer jphId) {
        return hibernateTemplate.get(JPH.class, jphId);
    }

    @Override
    public void deleteJPH(JPH jph) {
        hibernateTemplate.update(jph);
    }
}
