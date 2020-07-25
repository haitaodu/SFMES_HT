package com.smartflow.dto;

public class GetTokenForLoginDTO {
	private String username;
	private String password;
//	private String deviceIP;
//	private String deviceName;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getDeviceIP() {
//		return deviceIP;
//	}
//	public void setDeviceIP(String deviceIP) {
//		this.deviceIP = deviceIP;
//	}
//	public String getDeviceName() {
//		return deviceName;
//	}
//	public void setDeviceName(String deviceName) {
//		this.deviceName = deviceName;
//	}
	
}
