package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartflow.dao.RoleDao;
import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Role;
import com.smartflow.model.Roles_Menus;
import com.smartflow.model.Roles_Users;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	
	@Override
	public List<Role> getRoleListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
		return roleDao.getRoleListByCondition(getDTOByConditionOfRoleDTO);
	}

	@Override
	public Integer getTotalCountFromRole(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
		return roleDao.getTotalCountFromRole(getDTOByConditionOfRoleDTO);
	}

	@Override
	public Role getRoleById(Integer roleId) {
		return roleDao.getRoleById(roleId);
	}

	@Override
	public List<String> getUserNameByRoleId(Integer roleId) {
		return roleDao.getUserNameByRoleId(roleId);
	}

	@Override
	public List<Map<String, Object>> getAllPlatform() {
		return roleDao.getAllPlatform();
	}

	@Override
	public List<Map<String, Object>> getAllUser() {
		return roleDao.getAllUser();
	}

	@Override
	public List<Map<String, Object>> getAllocatedUserByRoleId(Integer roleId) {
		return roleDao.getAllocatedUserByRoleId(roleId);
	}
	@Override
	public Integer getCountByRoleName(String roleName) {
		return roleDao.getCountByRoleName(roleName);
	}
	@Transactional
	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Transactional
	@Override
	public void addRoles_Menus(Roles_Menus roles_Menus) {
		roleDao.addRoles_Menus(roles_Menus);
	}
	@Transactional
	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public List<Roles_Users> getRoles_UsersByRoleId(Integer roleId) {
		return roleDao.getRoles_UsersByRoleId(roleId);
	}

	@Override
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId) {
		return roleDao.getRoles_MenusByRoleId(roleId);
	}

//	@Transactional
//	@Override
//	public void deleteRoles_Menus(Roles_Menus roles_Menus) {
//		roleDao.deleteRoles_Menus(roles_Menus);
//	}
	
//	@Transactional
//	@Override
//	public void deleteRole(Role role) {
//		roleDao.deleteRole(role);
//	}
	
	@Override
	public String getVisitByRoleId(Integer roleId) {
		return roleDao.getVisitByRoleId(roleId);
	}

	@Override
	public List<Map<String, Object>> getTotalArea() {
		return roleDao.getTotalArea();
	}
	
	@Override
	public String getAreaIdListByRoleId(Integer roleId) {
		return roleDao.getAreaIdListByRoleId(roleId);
	}

	@Override
	public List<Map<String, Object>> getAreaById(Integer areaId) {
		return roleDao.getAreaById(areaId);
	}

//	@Override
//	public List<Map<String, Object>> getAllocatedAreaByRoleId(String areaIdList) {
//		return roleDao.getAllocatedAreaByRoleId(areaIdList);
//	}

	@Override
	public List<Map<String, Object>> getTotalEntityRole() {
		return roleDao.getTotalEntityRole();
	}
}
