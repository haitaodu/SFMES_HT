package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditInitializationStationDTO {
	private Integer Id;	
	private String StationNumber;	
	private String Name;
	private Integer StationType;	
	private String State;	
	private String FactoryId;
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
	@JsonProperty("StationIP")
	public String getStationIP() {
		return StationIP;
	}

	public void setStationIP(String stationIP) {
		StationIP = stationIP;
	}
}
