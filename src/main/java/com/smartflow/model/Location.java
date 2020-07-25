package com.smartflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="core.Location")
public class Location {
	private Integer Id;
	private String LocationNumber;
	private String Description;
	private Integer AreaId;
	private Double X;
	private Double Y;
	private Double Z;
	private Integer State;
	private Integer CreatorId;
	private Date CreateDateTime;
	private Integer EditorId;
	private Date EditDateTime;
	private Integer Type;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getLocationNumber() {
		return LocationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		LocationNumber = locationNumber;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Integer getAreaId() {
		return AreaId;
	}
	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}
	public Double getX() {
		return X;
	}
	public void setX(Double x) {
		X = x;
	}
	public Double getY() {
		return Y;
	}
	public void setY(Double y) {
		Y = y;
	}
	public Double getZ() {
		return Z;
	}
	public void setZ(Double z) {
		Z = z;
	}
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
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
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	
	
}
