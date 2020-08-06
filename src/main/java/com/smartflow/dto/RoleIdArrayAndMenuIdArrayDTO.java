package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleIdArrayAndMenuIdArrayDTO {
	private Integer RoleId;
	private Integer MenuId;
	@JsonProperty("RoleId")
	public Integer getRoleId() {
		return RoleId;
	}
	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}
	@JsonProperty("MenuId")
	public Integer getMenuId() {
		return MenuId;
	}
	public void setMenuId(Integer menuId) {
		MenuId = menuId;
	}
	
}
