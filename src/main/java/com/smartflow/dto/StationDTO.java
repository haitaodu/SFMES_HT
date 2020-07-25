package com.smartflow.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationDTO {
	private Integer Id;
	private String StationNumber;
	private String Name;
	private Date CreationDateTime;
	private Date EditDateTime;
	private String Editor;
	private String StationType;
	private String State;
//	private String Factory;
//	private String FactoryId;
	private String Creator;
//	private String SelectedStationGroup;
	private String StationIP;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("StationNumber")
	public String getStationNumber() {
		return StationNumber;
	}
	public void setStationNumber(String stationNumber) {
		StationNumber = stationNumber;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
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
	@JsonProperty("StationType")
	public String getStationType() {
		return StationType;
	}
	public void setStationType(String stationType) {
		StationType = stationType;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
//	@JsonProperty("Factory")
//	public String getFactory() {
//		return Factory;
//	}
//	public void setFactory(String factory) {
//		Factory = factory;
//	}
//	@JsonProperty("FactoryId")
//	public String getFactoryId() {
//		return FactoryId;
//	}
//	public void setFactoryId(String factoryId) {
//		FactoryId = factoryId;
//	}
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
//	@JsonProperty("SelectedStationGroup")
//	public String getSelectedStationGroup() {
//		return SelectedStationGroup;
//	}
//	public void setSelectedStationGroup(String selectedStationGroup) {
//		SelectedStationGroup = selectedStationGroup;
//	}
	@JsonProperty("StationIP")
	public String getStationIP() {
		return StationIP;
	}

	public void setStationIP(String stationIP) {
		StationIP = stationIP;
	}

	public StationDTO() {

	}
//	public StationDTO(Integer id, String stationNumber, String name, Date creationDateTime, Date editDateTime,
//			String editor, String stationType, String state, String factory, String creator) {
//		Id = id;
//		StationNumber = stationNumber;
//		Name = name;
//		CreationDateTime = creationDateTime;
//		EditDateTime = editDateTime;
//		Editor = editor;
//		StationType = stationType;
//		State = state;
//		Factory = factory;
//		Creator = creator;
//	}
	
	
}
