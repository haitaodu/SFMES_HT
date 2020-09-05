package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.dto.GetDTOByConditionOfUserDTO;
import com.smartflow.model.LoginRecord;
import com.smartflow.model.Roles_Users;
import com.smartflow.model.User;
import com.smartflow.model.UserModel;

public interface UserDao {

	/**
	 * 多条件分页查询用户
	 * @param getDTOByConditionOfUserDTO
	 * @return
	 */
	public List<User> getUserByCondition(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO);
	
	/**
	 * 查询用户总条数
	 * @param getDTOByConditionOfUserDTO
	 * @return
	 */
	public Integer getTotalCountFromUser(GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO);
	
	/**
	 * 根据模板id获取模板名称
	 * @param platformId
	 * @return
	 */
	public String getPlatformNameByPlatformId(Integer platformId);
	
	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	
	/**
	 * 根据userI的查询角色名称
	 * @param userId
	 * @return
	 */
	public List<String> getRoleNameFromByUserId(Integer userId);
	
	/**
	 * 获取所有的模板
	 * @return
	 */
	public List<Map<String, Object>> getTotalPlatform();
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Map<String, Object>> getTotalRole();
	
	/**
	 * 根据用户id查询用户对应的角色
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getAllocatedRole(Integer userId);

	/**
	 * 查询UserCode出现的次数，判断UserCode是否重复添加
	 * @param userCode
	 * @return
	 */
	public Integer getCountByUserCode(String userCode);

	/**
	 * 查询UserICCardNumber出现的次数，判断UserICCardNumber出现的次数是否重复添加
	 * @param icCardNumber
	 * @return
	 */
	public Integer getCountByICCardNumber(String icCardNumber);
	
	/**
	 * 查询Account出现的次数，判断Account是否重复添加
	 * @param account
	 * @return
	 */
	public Integer getCountByAccount(String account);
	
	/**
	 * 查询Username出现的次数，判断Username是否重复添加
	 * @param username
	 * @return
	 */
	public Integer getCountByUsername(String username);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 添加角色_用户中间表
	 * @param roles_Users
	 */
	public void addRoles_Users(Roles_Users roles_Users);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 修改角色_用户中间表
	 * @param roles_Users
	 */
	public void updateRoles_Users(Roles_Users roles_Users);
	
	/**
	 * 根据用户id查询Roles_Users
	 * @param userId
	 */
	public List<Roles_Users> getRoles_UsersByUserId(Integer userId);
	
	/**
	 * 删除Roles_Users
	 * @param Roles_Users
	 */
	public void deleteRoles_Users(Roles_Users roles_Users);
	
	/**
	 * 删除User
	 * @param user
	 */
//	public void deleteUser(User user);
	
	//通过Id找到相应的数据
	public UserModel getDataById(int i);
	
	/**
	 * 根据用户名判断用户是否存在
	 * @param username
	 * @return
	 */
	public List<User> getUserByUserName(String username);
	
	/**
	 * 退出登录时根据用户id修改最后登录时间
	 * @param userId
	 */
	public void updateLastLoginTime(Integer userId);
	
	/**
	 * 根据用户id查询用户密码
	 * @param userId
	 * @return
	 */
	public String getPasswordByUserId(Integer userId);
	
	/**
	 * 根据用户id修改密码
	 * @param password
	 * @param userId
	 */
	public void updatePassword(String password, Integer userId);
	
	/**
	 * 添加登录记录
	 * @param loginRecord
	 */
	public void addLoginRecorod(LoginRecord loginRecord);
	
	/**
	 * 根据用户id查询登录记录
	 * @param userId
	 * @return
	 */
	public LoginRecord getLoginRecordByUserId(Integer userId);
	
	/**
	 * 修改登录记录
	 * @param loginRecord
	 */
	public void updateLoginRecord(LoginRecord loginRecord);
}
