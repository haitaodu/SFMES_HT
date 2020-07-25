package com.smartflow.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePasswordDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688988000274130417L;
	
	@NotNull(message="{user.Id.required}")
	@JsonProperty("UserId")
	private Integer UserId;	
	@NotBlank(message="{user.OldPassword.required}")
	@JsonProperty("OldPassword")	
	private String OldPassword;
	@NotBlank(message="{user.NewPassword.required}")
	@JsonProperty("NewPassword")
	private String NewPassword;
	@NotBlank(message="{user.ConfirmPassword.required}")
	@JsonProperty("ConfirmPassword")	
	private String ConfirmPassword;
	
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getOldPassword() {
		return OldPassword;
	}
	public void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
	}
	public String getNewPassword() {
		return NewPassword;
	}
	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	
}
