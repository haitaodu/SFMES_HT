package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDTO {
	private Integer Id;
	private String RoleName;
	private Integer PlatformId;
	private String PlatformName;
	private Date CreationDateTime;
	private Integer CreatorId;
	private String Creator;
	private String State;
	private Integer EditorId;
	private String Editor;
	private Date EditDateTime;
	private List<String> Menu;
	private String Area;
	private String Type;
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
	@JsonProperty("PlatformId")
	public Integer getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(Integer platformId) {
		PlatformId = platformId;
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
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("EditorId")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
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
	@JsonProperty("Type")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}
