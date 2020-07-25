package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditInitializationDepartmentDTO {

	private Integer Id;
	private String DeptNumber;
	private String Name;
	private String State;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("DeptNumber")
	public String getDeptNumber() {
		return DeptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		DeptNumber = deptNumber;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
}
