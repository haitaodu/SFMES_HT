package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class DepartmentDetailsDTO {
	private Integer Id;
	private String DeptNumber;
	private String Name;
	private String State;
	private Date CreationDateTime;
	private String Creator;
	private Date EditDateTime;
	private String Editor;

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
}
