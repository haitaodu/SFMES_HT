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

import com.smartflow.model.Unit;
@Repository
public class UnitDaoImpl implements UnitDao{
@Autowired
HibernateTemplate hibernate;
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUnit() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = hibernate.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select Id [key],Name label from core.Unit where State=1";
		Query query = session.createSQLQuery(sql);//.addScalar("key", StandardBasicTypes.INTEGER).addScalar("label", StandardBasicTypes.STRING);
		List<Map<String, Object>> units=query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		session.close();
		return units;
	}
	@Override
	public Unit getUnitById(int id) {
		// TODO Auto-generated method stub
		return hibernate.get(Unit.class, id);
	}

}
