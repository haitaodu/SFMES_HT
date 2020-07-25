package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Platform;
import com.smartflow.model.Role;
import com.smartflow.model.Roles_Menus;
import com.smartflow.model.Roles_Users;

public interface RoleDao {

	/**
	 * 分页多条件查询所有角色
	 * @param getDTOByConditionOfRoleDTO
	 * @return
	 */
	public List<Role> getRoleListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO);
	
	/**
	 * 查询角色总条数
	 * @param getDTOByConditionOfRoleDTO
	 * @return
	 */
	public Integer getTotalCountFromRole(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO);
	
	/**
	 * 根据Id查询角色信息
	 * @param roleId
	 */
	public Role getRoleById(Integer roleId);
	
	/**
	 * 根据角色id查询用户名
	 * @param roleId
	 * @return
	 */
	public List<String> getUserNameByRoleId(Integer roleId);
	
	/**
	 * 获取所有的平台
	 * @return
	 */
	public List<Map<String, Object>> getAllPlatform();
	
	/**
	 * 获取所有的用户
	 * @return
	 */
	public List<Map<String, Object>> getAllUser();
	
	/**
	 * 根据roleId查询已分配用户
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getAllocatedUserByRoleId(Integer roleId);
	
	/**
	 * 查询roleName出现的次数，判断roleName是否重复添加
	 * @param roleName
	 * @return
	 */
	public Integer getCountByRoleName(String roleName);
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role);

	/**
	 * 添加角色
	 * @param roles_Menus
	 */
	public void addRoles_Menus(Roles_Menus roles_Menus);
	
	/**
	 * 修改角色
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * 根据角色id查询Roles_Users
	 * @param roleId
	 * @return
	 */
	public List<Roles_Users> getRoles_UsersByRoleId(Integer roleId);
	
	/**
	 * 根据角色id查询Roles_Menus
	 * @param roleId
	 * @return
	 */
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId);
	
	/**
	 * 删除Roles_Menus
	 * @param roles_Menus
	 */
//	public void deleteRoles_Menus(Roles_Menus roles_Menus);
	
	/**
	 * 删除角色
	 * @param role
	 */
//	public void deleteRole(Role role);
	
	/**
	 * 根据角色id获取角色对应的菜单
	 * @param roleId
	 * @return
	 */
	public String getVisitByRoleId(Integer roleId);
	
	/**
	 * 查询所有的区域
	 * @return
	 */
	public List<Map<String, Object>> getTotalArea();
	
	/**
	 * 根据角色id查询角色对应的区域
	 * @param roleId
	 * @return
	 */
	public String getAreaIdListByRoleId(Integer roleId);
	
	/**
	 * 根据区域id查询区域
	 * @param roleId
	 * @return
	 */
//	public List<Map<String, Object>> getAllocatedAreaByRoleId(String areaIdList);
	
	public List<Map<String, Object>> getAreaById(Integer areaId);

	/**
	 * 查询所有实体角色
	 * @return
	 */
	public List<Map<String, Object>> getTotalEntityRole();
	
}
