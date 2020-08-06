package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditRoleDTO {
	private Integer Id;
	private String RoleName;
	private Integer PlatformId;  
	private Date EditDateTime;
	private Integer EditorId;
	private Integer State;
	private List<Integer> User;
	private List<String> Menu;
	private List<Integer> PDAMenu;
//	private List<Integer> Area;
	private Integer Type;
	@NotNull(message="{role.Id.required}")
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@NotNull(message="{role.RoleName.required}")
	@Size(max=46,message="{role.RoleName.invalid}")
	@JsonProperty("RoleName")
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	@NotNull(message="{role.PlatformId.required}")
	@JsonProperty("PlatformId")
	public Integer getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(Integer platformId) {
		PlatformId = platformId;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("EditorId")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	@NotNull(message="{role.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty("User")
	public List<Integer> getUser() {
		return User;
	}
	public void setUser(List<Integer> user) {
		User = user;
	}
	//@NotEmpty(message="{role.Menu.required}")
	@JsonProperty("Menu")
	public List<String> getMenu() {
		return Menu;
	}
	public void setMenu(List<String> menu) {
		Menu = menu;
	}
	@JsonProperty("PDAMenu")
	public List<Integer> getPDAMenu() {
		return PDAMenu;
	}
	public void setPDAMenu(List<Integer> pDAMenu) {
		PDAMenu = pDAMenu;
	}
//	@JsonProperty("Area")
//	public List<Integer> getArea() {
//		return Area;
//	}
//	public void setArea(List<Integer> area) {
//		Area = area;
//	}
	@JsonProperty("Type")
	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}
}
