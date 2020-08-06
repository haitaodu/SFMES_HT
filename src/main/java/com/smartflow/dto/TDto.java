package com.smartflow.dto;

import java.util.Date;

public class TDto {
	private Integer Id;
	private String GroupNumber;
	private String Description;
	private Date CreateDateTime;
	private String Creator;
	private String Editor;
	private Date EditDateTime;
	private String Factory;
	private String State;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getGroupNumber() {
		return GroupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		GroupNumber = groupNumber;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
}
