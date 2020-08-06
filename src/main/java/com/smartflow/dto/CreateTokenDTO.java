package com.smartflow.dto;

public class CreateTokenDTO {

	private Integer userId;
	private String account;
	private String password;
	private String deviceName;
	private String deviceIP;
	
	
	public CreateTokenDTO() {
		
	}
	public CreateTokenDTO(Integer userId, String account, String password, String deviceName, String deviceIP) {
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.deviceName = deviceName;
		this.deviceIP = deviceIP;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}
	
	
}
