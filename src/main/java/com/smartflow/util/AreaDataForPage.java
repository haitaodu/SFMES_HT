package com.smartflow.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaDataForPage {
	int Id;
	String  AreaNumber;
	String Name;
	String  Description;
	Integer FactoryId;
	String   Factory;
	String  State;
	String  Creator;
	Date CreationDateTime;
	String AreaType;
	String Editor;
	Date EditDateTime;
	public AreaDataForPage() {};
	public AreaDataForPage(int id, String areaNumber, String name, String description, Integer factoryId,
			String factory, String state, String creator, Date creationDateTime, String editor, Date editDateTime,String areaType) {
		
		Id = id;
		AreaNumber = areaNumber;
		Name = name;
		Description = description;
		FactoryId = factoryId;
		Factory = factory;
		State = state;
		Creator = creator;
		CreationDateTime = creationDateTime;
		Editor = editor;
		EditDateTime = editDateTime;
		AreaType=areaType;
	}
	@JsonProperty("AreaType")
	public String getAreaType() {
		return AreaType;
	}
	public void setAreaType(String areaType) {
		AreaType = areaType;
	}
	@JsonProperty("Id")
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@JsonProperty("AreaNumber")
	public String getAreaNumber() {
		return AreaNumber;
	}
	public void setAreaNumber(String areaNumber) {
		AreaNumber = areaNumber;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("Factory")
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
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

}
