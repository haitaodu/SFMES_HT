package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDepartmentDTO {
	private String DeptNumber;
	private String Name;
	private Integer CreatorId;
	private Integer State;

	@NotBlank(message="{department.DeptNumber.required}")
	@Size(max=46,message="{department.DeptNumber.invalid}")
	@JsonProperty("DeptNumber")
	public String getDeptNumber() {
		return DeptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		DeptNumber = deptNumber;
	}
	@NotBlank(message="{department.Name.required}")
	@JsonProperty("Name")
	@Size(max=50,message="{department.Name.invalid}")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@NotNull(message="{role.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
}
