package com.smartflow.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：tao
 * @date ：Created in 2020/9/3 17:42
 * @description：${description}
 */
@Repository
public class ContainerTypeDaoImpl implements  ContainerTypeDao{
    final
    HibernateTemplate hibernateTemplate;

    @Autowired
    public ContainerTypeDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Map<String, Object>> getContainerType() {
        SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        Session session = sessionFactory.openSession();
        String sql = "select Id [key],Name label from core.ContainerType";
        try{
            Query query = session.createSQLQuery(sql);
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }finally{
            session.close();

        }
    }
}
