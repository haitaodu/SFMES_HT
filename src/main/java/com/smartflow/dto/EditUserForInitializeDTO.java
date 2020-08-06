package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class EditUserForInitializeDTO {

	private Integer Id;
	private String Account;
	private String UserCode;
	private String UserName;
	private String Password;
	private Integer PlatformId;
	private String EmailAddress;
	private String Phone;
	private Date CreationDateTime;
	private Date LastLoginTime;
	private String FactoryId;
	private String State;
	private String ICCardNumber;
	private List<Map<String,Object>> StationList;
//	private Integer SuperiorId;//上级id
	private String DepartmentId;
	private String Level;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("Account")
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	@JsonProperty("UserCode")
	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	@JsonProperty("UserName")
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@JsonProperty("Password")
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@JsonProperty("PlatformId")
	public Integer getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(Integer platformId) {
		PlatformId = platformId;
	}
	@JsonProperty("EmailAddress")
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	@JsonProperty("Phone")
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	@JsonProperty("LastLoginTime")
	public Date getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
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
	@JsonProperty("ICCardNumber")
	public String getICCardNumber() {
		return ICCardNumber;
	}

	public void setICCardNumber(String ICCardNumber) {
		this.ICCardNumber = ICCardNumber;
	}
	@JsonProperty("StationList")
	public List<Map<String, Object>> getStationList() {
		return StationList;
	}

	public void setStationList(List<Map<String, Object>> stationList) {
		StationList = stationList;
	}
	@JsonProperty("DepartmentId")
	public String getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}

	@JsonProperty("Level")
	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}
}
