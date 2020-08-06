package com.smartflow.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import javax.validation.constraints.NotEmpty;
//import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreationStationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5553236250379963428L;
	private String StationNumber;
	private String Name;
	private Date CreationDateTime;
	private Integer CreatorId;
	private Integer StationType;
	private Integer State;
//	private Integer FactoryId;
//	private List<Integer> StationGroupIdArray;
	private String StationIP;
	@Pattern(regexp="^[a-zA-Z0-9_-]{1,16}$",message="{station.StationNumber.illegal}")
	@NotNull(message="{station.StationNumber.required}")
	@Size(max=16,message="{station.StationNumber.invalid}")
	@JsonProperty("StationNumber")
	public String getStationNumber() {
		return StationNumber;
	}
	public void setStationNumber(String stationNumber) {
		StationNumber = stationNumber;
	}
	@NotNull(message="{station.Name.required}")
	@Size(max=50,message="{station.Name.invalid}")
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
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@NotNull(message="{station.StationType.required}")
	@JsonProperty("StationType")
	public Integer getStationType() {
		return StationType;
	}
	public void setStationType(Integer stationType) {
		StationType = stationType;
	}
	@NotNull(message="{station.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
//	@JsonProperty("FactoryId")
//	public Integer getFactoryId() {
//		return FactoryId;
//	}
//	public void setFactoryId(Integer factoryId) {
//		FactoryId = factoryId;
//	}
//	@JsonProperty("StationGroupIdArray")
//	public List<Integer> getStationGroupIdArray() {
//		return StationGroupIdArray;
//	}
//	public void setStationGroupIdArray(List<Integer> stationGroupIdArray) {
//		StationGroupIdArray = stationGroupIdArray;
//	}
	@Pattern(regexp="^$|^(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))$",message="{station.StationIP.illegal}")//不检测""空字符串：^$
	@JsonProperty("StationIP")
	public String getStationIP() {
		return StationIP;
	}

	public void setStationIP(String stationIP) {
		StationIP = stationIP;
	}
}
