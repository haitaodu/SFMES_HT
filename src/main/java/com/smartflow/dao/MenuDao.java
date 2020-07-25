package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.Roles_Menus;

public interface MenuDao {

	/**
	 * 根据角色id查询PDA菜单权限
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getPDAMenuByRoleId(Integer roleId);
	
	/**
	 * 获取PDA菜单下拉列表
	 * @return
	 */
	public List<Map<String, Object>> getPDAMenuList();
	
	/**
	 * 根据roleId查询PDA的菜单权限
	 * @param roleId
	 * @return
	 */
	public List<String> getPDAMenuNameByRoleId(Integer roleId);
	
	/**
	 * 查询roleId已分配的PDA菜单权限
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getAllocatedPDAMenuByRoleId(Integer roleId);
	
	/**
	 * 添加Roles_Menus表
	 * @param roles_Menus
	 */
	public void addRoles_Menus(Roles_Menus roles_Menus);
	
	/**
	 * 根据roleId查询Roles_Menus
	 * @param roleId
	 */
	public List<Roles_Menus> getRoles_MenusByRoleId(Integer roleId);
	
	/**
	 * 删除Roles_Menus
	 * @param roles_Menus
	 */
	public void deleteRoles_Menus(Roles_Menus roles_Menus);
}
