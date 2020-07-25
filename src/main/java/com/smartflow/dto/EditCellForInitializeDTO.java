package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditCellForInitializeDTO {

	private Integer Id;
	private String CellNumber;
	private String Description;
	private String State;
	private String FactoryId;
	private String AreaId;
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
	@JsonProperty("FactoryId")
	public String getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(String factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("AreaId")
	public String getAreaId() {
		return AreaId;
	}

	public void setAreaId(String areaId) {
		AreaId = areaId;
	}
}
