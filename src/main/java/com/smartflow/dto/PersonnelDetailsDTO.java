package com.smartflow.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author haita
 */
public class PersonnelDetailsDTO {
	private Integer Id;
	private String Account;
	private String UserCode;
	private String UserName;
	private String PlatformName;
	private String EmailAddress;
	private String Phone;
	private Date CreationDateTime;
	private String Creator;
	private String Editor;
	private Date LastLoginTime;
	private Date EditDateTime;
	private String State;
	private String Role;
	private String FactoryName;
	private String ICCardNumber;
	private String Station;
//	private String Superior;
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
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	@JsonProperty("LastLoginTime")
	public Date getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("Role")
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
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
