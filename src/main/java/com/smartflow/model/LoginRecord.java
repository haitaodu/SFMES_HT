package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="core.LoginRecord")
public class LoginRecord implements Serializable {
	private Integer Id;
	private String DeviceName;
	private String DeviceIP;
	private Date LoginTime;
	private Date LogoutTime;
	private boolean IsLogoutFlag;
//	private String SessionId;
	private Integer UserId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public String getDeviceIP() {
		return DeviceIP;
	}
	public void setDeviceIP(String deviceIP) {
		DeviceIP = deviceIP;
	}
	public Date getLoginTime() {
		return LoginTime;
	}
	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}
	public Date getLogoutTime() {
		return LogoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		LogoutTime = logoutTime;
	}
	public boolean getIsLogoutFlag() {
		return IsLogoutFlag;
	}
	public void setIsLogoutFlag(boolean isLogoutFlag) {
		IsLogoutFlag = isLogoutFlag;
	}
//	public String getSessionId() {
//		return SessionId;
//	}
//	public void setSessionId(String sessionId) {
//		SessionId = sessionId;
//	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	
}
