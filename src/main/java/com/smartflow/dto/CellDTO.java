package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CellDTO {
	private Integer Id;
	private String CellNumber;
	private String Description;
	private String State;
	private String Creator;
	private Date CreationDateTime;
	private Date EditDateTime;
	private String EditorId;//C#修改人EditorId
	private String Editor;
	private String SelectedStation;
//	private String Factory;
	private String Area;
	
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("CellNumber")
	public String getCellNumber() {
		return CellNumber;
	} 
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("EditorId")
	public String getEditorId() {
		return EditorId;
	}
	public void setEditorId(String editorId) {
		EditorId = editorId;
	}
	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	@JsonProperty("SelectedStation")
	public String getSelectedStation() {
		return SelectedStation;
	}
	public void setSelectedStation(String selectedStation) {
		SelectedStation = selectedStation;
	}
//	@JsonProperty("Factory")
//	public String getFactory() {
//		return Factory;
//	}
//	public void setFactory(String factory) {
//		Factory = factory;
//	}
	@JsonProperty("Area")
	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}
}
