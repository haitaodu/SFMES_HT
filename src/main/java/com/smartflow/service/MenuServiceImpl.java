package com.smartflow.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.MenuDao;
import com.smartflow.model.Roles_Menus;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDao menuDao;
	@Override
	public List<Map<String, Object>> getPDAMenuByRoleId(Integer roleId) {
		return menuDao.getPDAMenuByRoleId(roleId);
	}
	@Override
	public List<Map<String, Object>> getPDAMenuList() {
		return menuDao.getPDAMenuList();
	}
	@Override
	public List<String> getPDAMenuNameByRoleId(Integer roleId) {
		return menuDao.getPDAMenuNameByRoleId(roleId);
	}
	
	@Override
	public List<Map<String, Object>> getAllocatedPDAMenuByRoleId(Integer roleId) {
		return menuDao.getAllocatedPDAMenuByRoleId(roleId);
	}
	
	@Transactional
	@Override
	public void addRoles_Menus(Roles_Menus roles_Menus) {
		menuDao.addRoles_Menus(roles_Menus);
	}
	@Override
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId) {
		return menuDao.getRoles_MenusByRoleId(roleId);
	}
	@Transactional
	@Override
	public void deleteRoles_Menus(Roles_Menus roles_Menus) {
		menuDao.deleteRoles_Menus(roles_Menus);
	}


}
