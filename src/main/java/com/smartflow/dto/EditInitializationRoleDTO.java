package com.smartflow.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditInitializationRoleDTO {

	private Integer Id;
	private String RoleName;
	private Integer PlatformId;
	private String State;
//	private Integer Type;
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
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
//	@JsonProperty("Type")
//	public Integer getType() {
//		return Type;
//	}
//
//	public void setType(Integer type) {
//		Type = type;
//	}
}
