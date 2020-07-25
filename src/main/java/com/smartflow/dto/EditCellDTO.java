package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditCellDTO {
	private Integer Id;
	private String CellNumber;
	private String Description;
	private Integer State;
	private Date EditDateTime;
	private Integer EditorId;
//	private List<Integer> Station;
//	private Integer FactoryId;
	private Integer AreaId;
	@NotNull(message="{cell.Id.required}")
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@Size(max=16,message="{cell.CellNumber.invalid}")
	@NotNull(message="{cell.CellNumber.required}")
	@JsonProperty("CellNumber")
	public String getCellNumber() {
		return CellNumber;
	}
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}
	@NotNull(message="{cell.Description.required}")
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@NotNull(message="{cell.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("EditorId")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
//	@JsonProperty("Station")
//	public List<Integer> getStation() {
//		return Station;
//	}
//	public void setStation(List<Integer> station) {
//		Station = station;
//	}
//	@JsonProperty("FactoryId")
//	public Integer getFactoryId() {
//		return FactoryId;
//	}
//	public void setFactoryId(Integer factoryId) {
//		FactoryId = factoryId;
//	}
	@NotNull(message="{cell.AreaId.required}")
	@JsonProperty("AreaId")
	public Integer getAreaId() {
		return AreaId;
	}

	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}
}
