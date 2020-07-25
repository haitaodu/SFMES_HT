package com.smartflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="core.Roles_Menus")
public class Roles_Menus {
	private Integer Id;
	private Integer RoleId;
	private Integer MenuId;
	private Date CreationDateTime;
	private Integer CreatorId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getRoleId() {
		return RoleId;
	}
	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}
	public Integer getMenuId() {
		return MenuId;
	}
	public void setMenuId(Integer menuId) {
		MenuId = menuId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	
	
}
