package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDetailsDTO {
	private Integer Id;
	private String RoleName;
	private String State;
	private String PlatformName;
	private Date CreationDateTime;
	private String Creator;
	private Date EditDateTime;
	private String Editor;
	private String User;
//	private String Menu;
	private String PDAMenu;
	private List<String> Menu;
	private String Area;
//	private String Type;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("RoleName")
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("PlatformName")
	public String getPlatformName() {
		return PlatformName;
	}
	public void setPlatformName(String platformName) {
		PlatformName = platformName;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	@JsonProperty("User")
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
//	@JsonProperty("Menu")
//	public String getMenu() {
//		return Menu;
//	}
//	public void setMenu(String menu) {
//		Menu = menu;
//	}
	@JsonProperty("PDAMenu")
	public String getPDAMenu() {
		return PDAMenu;
	}
	public void setPDAMenu(String pDAMenu) {
		PDAMenu = pDAMenu;
	}
	@JsonProperty("Menu")
	public List<String> getMenu() {
		return Menu;
	}
	public void setMenu(List<String> menu) {
		Menu = menu;
	}
	@JsonProperty("Area")
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
//	@JsonProperty("Type")
//	public String getType() {
//		return Type;
//	}
//
//	public void setType(String type) {
//		Type = type;
//	}
}
