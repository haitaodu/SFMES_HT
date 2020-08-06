package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="core.Location")
public class LocationModel {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="LocationNumber")
	private String LocationNumber;
	@Column(name="Description")
	private String Description;
	@Column(name="AreaId")
	private Integer AreaId;
	@Column(name="X")
	private float X;
	@Column(name="Y")
	private float Y;
	@Column(name="Z")
	private float Z;
	@Column(name="State")
	private Integer State;
	@Column(name="CreatorId")
	private Integer CreatorId;
	@Column(name="CreateDateTime")
	private Date CreateDateTime;
	@Column(name="EditorId")
	private Integer EditorId;
	@Column(name="EditDateTime")
	private Date EditDateTime;
	@Column(name="Type")
	private Integer Type;
	@JsonProperty("Id")
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@JsonProperty("LocationNumber")
	public String getLocationNumber() {
		return LocationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		LocationNumber = locationNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("Area")
	public Integer getAreaId() {
		return AreaId;
	}
	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}
	@JsonProperty("X")
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	@JsonProperty("Y")
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
	@JsonProperty("Z")
	public float getZ() {
		return Z;
	}
	public void setZ(float z) {
		Z = z;
	}
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty(" Creator")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@JsonProperty("CreateDateTime")
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	@JsonProperty("Editor")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("Type")
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}

}
