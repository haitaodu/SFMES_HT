package com.smartflow.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	private Integer Id;
	private String Account;
	private String UserCode;
	private String ICCardNumber;
	private String UserName;
	private String Password;
	private String PlatformName;
	private String EmailAddress;
	private String Phone;
	private Date CreationDateTime;
	private Date LastLoginTime;
	private String State;
	private String FactoryName;
	private String Station;
	private String Department;
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
	@JsonProperty("ICCardNumber")
	public String getICCardNumber() {
		return ICCardNumber;
	}

	public void setICCardNumber(String ICCardNumber) {
		this.ICCardNumber = ICCardNumber;
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
	@JsonProperty("PlatformName")
	public String getPlatformName() {
		return PlatformName;
	}
	public void setPlatformName(String platformName) {
		PlatformName = platformName;
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
	@JsonProperty("FactoryName")
	public String getFactoryName() {
		return FactoryName;
	}
	public void setFactoryName(String factoryName) {
		FactoryName = factoryName;
	}
	@JsonProperty("Station")
	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}
	@JsonProperty("Department")
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
	@JsonProperty("Level")
	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}
}
