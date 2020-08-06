package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditStationDTO {
	private Integer Id;
	private String StationNumber;
	private String Name;
	private Date EditDateTime;
	private Integer EditorId;
	private Integer StationType;
	private Integer State;
//	private Integer FactoryId;
//	private List<Integer> StationGroupIdArray;
	private String StationIP;
	@NotNull(message="{station.Id.required}")
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
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

	/**
	 *    ip地址合法校验
	 *  1		25[0-5]                     	250-255
	 *  2		2[0-4]\\d                    	200-249
	 *  3		[1]{1}\\d{1}\\d{1}   		100-199
	 *  4		[1-9]{1}\\d{1}			10-99
	 *  5		\\d{1}				0-9
	 *  6		($|(?!\\.$)\\.)               	结束 或者 不以.结束的加上.
	 *  7		(?!^0{1,3}(\\.0{1,3}){3}$)     	排除 0.0.0.0 		(?!^0{1,3}(\\.0{1,3}){3}$)^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$
	 *  8		(?!^255(\\.255){3}$)      	排除 255.255.255.255	(?!^255(\\.255){3}$)^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$
	 */
	@Pattern(regexp="^$|^(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))$",message="{station.StationIP.illegal}")//不检测""空字符串：^$
	@JsonProperty("StationIP")
	public String getStationIP() {
		return StationIP;
	}

	public void setStationIP(String stationIP) {
		StationIP = stationIP;
	}
}
