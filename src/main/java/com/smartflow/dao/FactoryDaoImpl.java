package com.smartflow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.model.Factory;
@Repository

public class FactoryDaoImpl implements FactoryDao{
@Autowired
HibernateTemplate hibernate;
	@Override
	public Factory getDataById(int i) {
		// TODO Auto-generated method stub
		return hibernate.get(Factory.class, i);
	}

}
