package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author haita
 */
public class EditInitializationStationDTO {
	private Integer Id;	
	private String StationNumber;	
	private String Name;
	private Integer StationType;	
	private int State;
	private int FactoryId;
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
	@JsonProperty("StationType")
	public Integer getStationType() {
		return StationType;
	}
	public void setStationType(Integer stationType) {
		StationType = stationType;
	}
	@JsonProperty("State")
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	@JsonProperty("FactoryId")
	public int getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(int factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("StationIP")
	public String getStationIP() {
		return StationIP;
	}

	public void setStationIP(String stationIP) {
		StationIP = stationIP;
	}
}
