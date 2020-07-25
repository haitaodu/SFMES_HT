package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.dto.GetDTOByConditionOfUserDTO;
import com.smartflow.model.LoginRecord;
import com.smartflow.model.Roles_Users;
import com.smartflow.model.User;
import com.smartflow.model.UserModel;
import com.smartflow.service.UserService;
import org.springframework.util.CollectionUtils;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public List<User> getUserByCondition(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO) {
//		String hql = "select new User(Id, UserName, Password, PlatformId, EmailAddress, Phone, CreationDateTime, State, LastLoginTime, Account) from User";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from User where State !=- 1 ";
		if (getDTOByConditionOfUserDTO.getUserName()!=null && !"".equals(getDTOByConditionOfUserDTO.getUserName())) {
			hql += "and userName like '%"+getDTOByConditionOfUserDTO.getUserName()+"%' ";
		}
		if (getDTOByConditionOfUserDTO.getAccount()!=null && !"".equals(getDTOByConditionOfUserDTO.getAccount())) {
			hql += "and account like '%"+getDTOByConditionOfUserDTO.getAccount()+"%'";
		}
		if(getDTOByConditionOfUserDTO.getFactoryId() != null){
			hql += " and factoryId = "+getDTOByConditionOfUserDTO.getFactoryId();
		}
//		if(!CollectionUtils.isEmpty(getDTOByConditionOfUserDTO.getRoleIdList())){
//			hql += " and Id in (select userId from Roles_Users where RoleId in (:roleIdList))";
//		}
		if(getDTOByConditionOfUserDTO.getDepartmentId() != null){
			hql += " and department.id = "+getDTOByConditionOfUserDTO.getDepartmentId();
		}
		try{
			Query query = session.createQuery(hql);
//			if(!CollectionUtils.isEmpty(getDTOByConditionOfUserDTO.getRoleIdList())){
//				query.setParameterList("roleIdList", getDTOByConditionOfUserDTO.getRoleIdList());
//			}
			query.setFirstResult((getDTOByConditionOfUserDTO.getPageIndex()-1)*getDTOByConditionOfUserDTO.getPageSize());
			query.setMaxResults(getDTOByConditionOfUserDTO.getPageSize());
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public Integer getTotalCountFromUser(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO) {
		String hql = "select count(*) from User where State !=- 1 ";
		if (getDTOByConditionOfUserDTO.getUserName()!=null && !"".equals(getDTOByConditionOfUserDTO.getUserName())) {
			hql += "and userName like '%"+getDTOByConditionOfUserDTO.getUserName()+"%' ";
		}
		if (getDTOByConditionOfUserDTO.getAccount()!=null && !"".equals(getDTOByConditionOfUserDTO.getAccount())) {
			hql += "and account like '%"+getDTOByConditionOfUserDTO.getAccount()+"%'";
		}
		if(getDTOByConditionOfUserDTO.getFactoryId() != null){
			hql += " and factoryId = "+getDTOByConditionOfUserDTO.getFactoryId();
		}
//		if(!CollectionUtils.isEmpty(getDTOByConditionOfUserDTO.getRoleIdList())){
//			hql += " and Id in (select userId from Roles_Users where RoleId in (:roleIdList))";
//		}
		if(getDTOByConditionOfUserDTO.getDepartmentId() != null){
			hql += " and department.id = "+getDTOByConditionOfUserDTO.getDepartmentId();
		}
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{			
			Query query = session.createQuery(hql);
//			if(!CollectionUtils.isEmpty(getDTOByConditionOfUserDTO.getRoleIdList())){
//				query.setParameterList("roleIdList", getDTOByConditionOfUserDTO.getRoleIdList());
//			}
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public String getPlatformNameByPlatformId(Integer platformId) {
		String hql = "select platformName from Platform  where id = "+platformId;
		Session session = hibernateTemplate.getSessionFactory().openSession();
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
	public List<String> getRoleNameFromByUserId(Integer userId) {
		String hql = "select roleName from Role where state = 1 and id in(select roleId from Roles_Users where userId = :userId)";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Override
	public User getUserById(Integer userId) {
		return hibernateTemplate.get(User.class, userId);
	}
	
	@Override
	public List<Map<String, Object>> getTotalPlatform() {
		String sql = "select Id [key],PlatformName label from core.Platform";
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
	public List<Map<String, Object>> getTotalRole() {
		String sql = "select Id [key],RoleName label from core.Role where State = 1";
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
	public List<Map<String, Object>> getAllocatedRole(Integer userId) {
		String sql = "select Id [key],RoleName label from core.Role where State = 1 and Id in(select RoleId from core.Roles_Users where UserId = :UserId)";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			query.setInteger("UserId", userId);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

	}

	@Override
	public Integer getCountByUserCode(String userCode) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from User where UserCode = :UserCode";
		//String sql = "select count(*) from core.[User] where UserCode = :UserCode collate Chinese_PRC_CS_AI_WS";
		try{
			Query query = session.createQuery(hql);
			//Query query = session.createSQLQuery(sql);
			query.setString("UserCode", userCode);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

    @Override
    public Integer getCountByICCardNumber(String icCardNumber) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        String hql = "select count(*) from User where ICCardNumber = :ICCardNumber";
        try{
            Query query = session.createQuery(hql);
            //Query query = session.createSQLQuery(sql);
            query.setString("ICCardNumber", icCardNumber);
            return Integer.parseInt(query.uniqueResult().toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
	public Integer getCountByAccount(String account) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		//String hql = "select count(*) from User where Account = :Account";
		String sql = "select count(*) from core.[User] where Account = :Account collate Chinese_PRC_CS_AI_WS";
		try{
			//Query query = session.createQuery(hql);
			Query query = session.createSQLQuery(sql);
			query.setString("Account", account);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	
	@Override
	public Integer getCountByUsername(String username) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "select count(*) from User where UserName = :UserName";
		try{
			Query query = session.createQuery(hql);
			query.setString("UserName", username);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public void addUser(User user) {
		hibernateTemplate.save(user);
	}
	@Override
	public void addRoles_Users(Roles_Users roles_Users) {
		hibernateTemplate.save(roles_Users);
	}
	@Override
	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}
	@Override
	public void updateRoles_Users(Roles_Users roles_Users) {
		hibernateTemplate.update(roles_Users);
	}
	@Override
	public List<Roles_Users> getRoles_UsersByUserId(Integer userId) {
		String hql = "from Roles_Users where UserId = "+userId;
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
	public void deleteRoles_Users(Roles_Users roles_Users) {
		hibernateTemplate.delete(roles_Users);
	}
//	@Override
//	public void deleteUser(User user) {
//		hibernateTemplate.delete(user);
//	}
	
	@Override
	public UserModel getDataById(int i) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(UserModel.class, i);
	}
	@Override
	public List<User> getUserByUserName(String username) {
//		String hql = "from User where Account = :Account";
		String sql = "select * from core.[User] where Account  = :Account";// collate Chinese_PRC_CS_AI_WS
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
//			Query query = session.createQuery(hql);
			Query query = session.createSQLQuery(sql).addEntity(User.class);
			query.setString("Account", username);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public void updateLastLoginTime(Integer userId) {
		String hql = "update User set LastLoginTime = GETDATE() where Id = "+userId;
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Override
	public String getPasswordByUserId(Integer userId) {
		String hql = "select password from User where Id = "+userId;
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		try{
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
	public void updatePassword(String password, Integer userId) {
		String hql = "update User set Password = :Password where Id = :Id";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		try{
			query.setString("Password", password);
			query.setInteger("Id", userId);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	@Override
	public void addLoginRecorod(LoginRecord loginRecord) {
		hibernateTemplate.save(loginRecord);
	}
	@Override
	public LoginRecord getLoginRecordByUserId(Integer userId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from LoginRecord where UserId = "+userId+" order by Id desc";
		try{
			Query query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			LoginRecord loginRecord = (LoginRecord) query.uniqueResult();
			return loginRecord;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}
	@Override
	public void updateLoginRecord(LoginRecord loginRecord) {
		hibernateTemplate.update(loginRecord);
	}



}
