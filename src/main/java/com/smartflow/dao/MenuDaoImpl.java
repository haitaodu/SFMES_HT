package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.model.Roles_Menus;

@Repository
public class MenuDaoImpl implements MenuDao{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Map<String, Object>> getPDAMenuByRoleId(Integer roleId) {
		String sql = "select Id MenuKey,MenuName MenuName,Url MenuRoute from core.Menu where State !=- 1 and Id in (select MenuId from core.Roles_Menus where RoleId = :RoleId) order by Secquence";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			query.setInteger("RoleId", roleId);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getPDAMenuList() {	
		String sql = "select Id [key],MenuName label from core.Menu where State = 1 ";
		Session session = hibernateTemplate.getSessionFactory().openSession();
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
	public List<String> getPDAMenuNameByRoleId(Integer roleId) {
		String hql = "select menuName from Menu where State !=- 1 and Id in (select menuId from Roles_Menus where RoleId = :RoleId)";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			query.setInteger("RoleId", roleId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getAllocatedPDAMenuByRoleId(Integer roleId) {
		String sql = "select Id [key],MenuName label from core.Menu where State = 1 and Id in (select MenuId from core.Roles_Menus where RoleId = :RoleId)";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			query.setInteger("RoleId", roleId);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Override
	public void addRoles_Menus(Roles_Menus roles_Menus) {
		hibernateTemplate.save(roles_Menus);
	}

	@Override
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId) {
		String hql = "from Roles_Menus where RoleId = "+roleId;
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

	}

	@Override
	public void deleteRoles_Menus(Roles_Menus roles_Menus) {
		hibernateTemplate.delete(roles_Menus);
	}

}
