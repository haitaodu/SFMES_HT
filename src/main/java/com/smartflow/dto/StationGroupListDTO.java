package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationGroupListDTO {

	private Integer Id;
	private String GroupNumber;
	private String Description;
	private Date CreationDateTime;
	private Date CreateDateTime;
	private String Creator;
	private String Editor;
	private Date EditDateTime;
//	private String FactoryId;
//	private String Factory;
	private String State;
	private String SelectedStation;
	private String Cell;
	private Integer SafetyStock;//安全库存
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("GroupNumber")
	public String getGroupNumber() {
		return GroupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		GroupNumber = groupNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("CreateDateTime")
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
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
//	@JsonProperty("FactoryId")
//	public String getFactoryId() {
//		return FactoryId;
//	}
//	public void setFactoryId(String factoryId) {
//		FactoryId = factoryId;
//	}
//	@JsonProperty("Factory")
//	public String getFactory() {
//		return Factory;
//	}
//	public void setFactory(String factory) {
//		Factory = factory;
//	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("SelectedStation")
	public String getSelectedStation() {
		return SelectedStation;
	}
	public void setSelectedStation(String selectedStation) {
		SelectedStation = selectedStation;
	}
	@JsonProperty("Cell")
	public String getCell() {
		return Cell;
	}

	public void setCell(String cell) {
		Cell = cell;
	}
	@JsonProperty("SafetyStock")
	public Integer getSafetyStock() {
		return SafetyStock;
	}

	public void setSafetyStock(Integer safetyStock) {
		SafetyStock = safetyStock;
	}
}
