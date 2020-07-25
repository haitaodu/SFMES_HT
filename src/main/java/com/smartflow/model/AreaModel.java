package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.Area")
public class AreaModel {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="AreaNumber")
	private String AreaNumber;
	@Column(name="Description")
	private String Description;
	@Column(name="FactoryId")
	private int FactoryId;
	@Column(name="CreatorId")
	private int CreatorId;
	@Column(name="CreationDateTime")
    private Date CreationDateTime;
	@Column(name="EditorId")
	private Integer EditorId;
	@Column(name="EditDateTime")
	private Date EditDateTime;
	@Column(name="Name")
	private String Name;
	@Column(name="AreaType")
	private Integer AreaType;
	@Column(name="State")
	private int State;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getAreaNumber() {
		return AreaNumber;
	}
	public void setAreaNumber(String areaNumber) {
		AreaNumber = areaNumber;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(int factoryId) {
		FactoryId = factoryId;
	}
	public int getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(int creatorId) {
		CreatorId = creatorId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getAreaType() {
		return AreaType;
	}
	public void setAreaType(Integer areaType) {
		AreaType = areaType;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
}
