package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreationCellDTO {
	private Integer Id;
	private String CellNumber;
	private String Description;
	private Integer State;
//	private Integer Creator;
	private Integer CreatorId;
	private Date CreationDateTime;
//	private List<Integer> Station;
//	private Integer FactoryId;
	private Integer AreaId;
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
	@Size(max=50,message="{cell.Description.invalid}")
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
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
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
