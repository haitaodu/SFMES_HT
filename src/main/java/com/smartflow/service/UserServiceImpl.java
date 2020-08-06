package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartflow.dao.UserDao;
import com.smartflow.dto.GetDTOByConditionOfUserDTO;
import com.smartflow.model.LoginRecord;
import com.smartflow.model.Roles_Users;
import com.smartflow.model.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserByCondition(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO) {
		return userDao.getUserByCondition(getDTOByConditionOfUserDTO);
	}
	@Override
	public Integer getTotalCountFromUser(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO) {
		return userDao.getTotalCountFromUser(getDTOByConditionOfUserDTO);
	}
	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	@Override
	public String getPlatformNameByPlatformId(Integer platformId) {
		return userDao.getPlatformNameByPlatformId(platformId);
	}
	@Override
	public List<String> getRoleNameFromByUserId(Integer userId) {
		return userDao.getRoleNameFromByUserId(userId);
	}
	@Override
	public List<Map<String, Object>> getTotalPlatform() {
		return userDao.getTotalPlatform();
	}
	@Override
	public List<Map<String, Object>> getTotalRole() {
		return userDao.getTotalRole();
	}
	@Override
	public List<Map<String, Object>> getAllocatedRole(Integer userId) {
		return userDao.getAllocatedRole(userId);
	}

	@Override
	public Integer getCountByUserCode(String userCode) {
		return userDao.getCountByUserCode(userCode);
	}

	@Override
	public Integer getCountByICCardNumber(String icCardNumber) {
		return userDao.getCountByICCardNumber(icCardNumber);
	}

	@Override
	public Integer getCountByAccount(String account) {
		return userDao.getCountByAccount(account);
	}
	
	@Override
	public Integer getCountByUsername(String username) {
		return userDao.getCountByUsername(username);
	}

	@Transactional
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	@Transactional
	@Override
	public void addRoles_Users(Roles_Users roles_Users) {
		userDao.addRoles_Users(roles_Users);
	}
	
	@Transactional
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	@Transactional
	@Override
	public void updateRoles_Users(Roles_Users roles_Users) {
		userDao.updateRoles_Users(roles_Users);
	}
	@Override
	public List<Roles_Users> getRoles_UsersByUserId(Integer userId) {
		return userDao.getRoles_UsersByUserId(userId);
	}
	@Transactional
	@Override
	public void deleteRoles_Users(Roles_Users roles_Users) {
		userDao.deleteRoles_Users(roles_Users);
	}
//	@Transactional
//	@Override
//	public void deleteUser(User user) {
//		userDao.deleteUser(user);
//	}
	@Override
	public List<User> getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
	}
	@Transactional
	@Override
	public void updateLastLoginTime(Integer userId) {
		userDao.updateLastLoginTime(userId);
	}
	@Override
	public String getPasswordByUserId(Integer userId) {
		return userDao.getPasswordByUserId(userId);
	}
	@Transactional
	@Override
	public void updatePassword(String password, Integer userId) {
		userDao.updatePassword(password, userId);
	}
	@Transactional
	@Override
	public void addLoginRecorod(LoginRecord loginRecord) {
		userDao.addLoginRecorod(loginRecord);
	}
	@Override
	public LoginRecord getLoginRecordByUserId(Integer userId) {
		return userDao.getLoginRecordByUserId(userId);
	}
	@Transactional
	@Override
	public void updateLoginRecord(LoginRecord loginRecord) {
		userDao.updateLoginRecord(loginRecord);
	}
}
