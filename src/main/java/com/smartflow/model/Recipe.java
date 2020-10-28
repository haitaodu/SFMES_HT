package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Table(name="core.Recipe")
public class Recipe implements Serializable {
	private Integer Id;
	private String RecipeName;
	private String Version;
	private Integer Layer;
	private Integer State;
	private Integer StationId;
	private Integer MachineId;
	private Integer ProductTypeId;
	private Integer CreatorId;
	private Date CreateDateTime;
	private Integer EditorId;
	private Date EditDateTime;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getRecipeName() {
		return RecipeName;
	}
	public void setRecipeName(String recipeName) {
		RecipeName = recipeName;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public Integer getLayer() {
		return Layer;
	}
	public void setLayer(Integer layer) {
		Layer = layer;
	}
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	public Integer getStationId() {
		return StationId;
	}
	public void setStationId(Integer stationId) {
		StationId = stationId;
	}
	public Integer getMachineId() {
		return MachineId;
	}
	public void setMachineId(Integer machineId) {
		MachineId = machineId;
	}
	public Integer getProductTypeId() {
		return ProductTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		ProductTypeId = productTypeId;
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
//	@OneToMany(cascade=CascadeType.REMOVE)
	
	
	
}
