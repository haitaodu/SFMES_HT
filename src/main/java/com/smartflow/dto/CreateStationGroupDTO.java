package com.smartflow.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateStationGroupDTO {

	private String GroupNumber;
	private String Description;
	private Integer State;
//	private Integer FactoryId;
	private Integer CreatorId;
	private List<Integer> StationIdArray;
	private Integer CellId;
	private Integer SafetyStock;//安全库存
	@NotNull(message="{stationGroup.GroupNumber.required}")
	@Size(max=16,message="{stationGroup.GroupNumber.invalid}")
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
	@NotNull(message="{stationGroup.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty("StationIdArray")
	public List<Integer> getStationIdArray() {
		return StationIdArray;
	}
	public void setStationIdArray(List<Integer> stationIdArray) {
		StationIdArray = stationIdArray;
	}
//	@JsonProperty("FactoryId")
//	public Integer getFactoryId() {
//		return FactoryId;
//	}
//	public void setFactoryId(Integer factoryId) {
//		FactoryId = factoryId;
//	}
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@NotNull(message="{stationGroup.CellId.required}")
	@JsonProperty("CellId")
	public Integer getCellId() {
		return CellId;
	}
	public void setCellId(Integer cellId) {
		CellId = cellId;
	}
	@JsonProperty("SafetyStock")
	public Integer getSafetyStock() {
		return SafetyStock;
	}

	public void setSafetyStock(Integer safetyStock) {
		SafetyStock = safetyStock;
	}

	public CreateStationGroupDTO() {
		
	}

}
