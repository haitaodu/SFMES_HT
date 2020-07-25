package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class EditDepartmentDTO {
	private Integer Id;
	private String DeptNumber;
	private String Name;
	private Integer EditorId;
	private Integer State;
	@NotNull(message="{department.Id.required}")
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}

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
	@Size(max=50,message="{department.Name.invalid}")
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

}
