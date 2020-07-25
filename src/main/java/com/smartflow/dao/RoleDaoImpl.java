package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Role;
import com.smartflow.model.Roles_Menus;
import com.smartflow.model.Roles_Users;
@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Role> getRoleListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery("from Role where State!=-1");
			query.setFirstResult((getDTOByConditionOfRoleDTO.getPageIndex()-1)*getDTOByConditionOfRoleDTO.getPageSize());
			query.setMaxResults(getDTOByConditionOfRoleDTO.getPageSize());
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

	}

	@Override
	public Integer getTotalCountFromRole(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery("select count(*) from Role where State!=-1");
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Role getRoleById(Integer roleId) {
		return hibernateTemplate.get(Role.class, roleId);
	}

	@Override
	public List<String> getUserNameByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select UserName from core.[User] where State !=- 1 and Id in (select UserId from core.Roles_Users where RoleId = :RoleId)";
		try{
			Query query = session.createSQLQuery(sql);
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
	public List<Map<String, Object>> getAllPlatform() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],PlatformName label from core.Platform";
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
	public List<Map<String, Object>> getAllUser() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "select Id [key],UserName label from core.[User] where State = 1";
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
	public List<Map<String, Object>> getAllocatedUserByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],UserName label from core.[User] where State = 1 and Id in (select UserId from core.Roles_Users where RoleId = :RoleId)";
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
	public Integer getCountByRoleName(String roleName) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from Role where RoleName = :RoleName";
		try{
			Query query = session.createQuery(hql);
			query.setString("RoleName", roleName);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public void addRole(Role role) {
		hibernateTemplate.save(role);
	}

	@Override
	public void addRoles_Menus(Roles_Menus roles_Menus) {
		hibernateTemplate.save(roles_Menus);
	}

	@Override
	public void updateRole(Role role) {
		hibernateTemplate.update(role);
	}

	@Override
	public List<Roles_Users> getRoles_UsersByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from Roles_Users where RoleId = "+roleId;
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
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from Roles_Menus where RoleId = "+roleId;
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
	public String getVisitByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select visit from Role where State !=- 1 and id = "+roleId;
		try{
			Query query = session.createQuery(hql);
			if (query.uniqueResult()==null) {
				return null;
			}else{
				return query.uniqueResult().toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getTotalArea() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],concat(AreaNumber,'('+Description+')') label from core.Area where State = 1";
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
	public String getAreaIdListByRoleId(Integer roleId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select areaIdList from Role where Id = "+roleId;
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

//	@Override
//	public List<Map<String, Object>> getAllocatedAreaByRoleId(String areaIdList) {
//		Session session = hibernateTemplate.getSessionFactory().openSession();
//		String sql = "select Id [key],concat(AreaNumber,'('+Description+')') from core.Area where Id in (:areaIdList)";
//		Query query = session.createSQLQuery(sql);
//		query.setString("areaIdList", areaIdList);
//		return query.list();
//	}


	@Override
	public List<Map<String, Object>> getAreaById(Integer areaId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],concat(AreaNumber,'('+Description+')') label from core.Area where Id ="+areaId;
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
	public List<Map<String, Object>> getTotalEntityRole() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String sql = "select Id [key],RoleName label from core.Role where Type = 1";
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
//	@Override
//	public void deleteRoles_Menus(Roles_Menus roles_Menus) {
//		hibernateTemplate.delete(roles_Menus);
//	}

//	@Override
//	public void deleteRole(Role role) {
//		hibernateTemplate.delete(role);
//	}

}
