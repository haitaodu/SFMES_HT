package com.smartflow.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

public class CreateUserDTO {
	private Integer Id;
	private String UserName;
	private String UserCode;//工号
	private String ICCardNumber;//IC卡号
	private String Account;
//	private String Password;
	private Integer PlatformId;
	private String EmailAddress;
	private String Phone;
	private Date CreationDateTime;
//	private Integer Creator;
	private Integer CreatorId;
//	private Integer UserState;//C#名称UserState，前台State
	private Integer State;
	private List<Integer> Role;
	private List<Integer> Station;
	private Integer FactoryId;
//	private Integer SuperiorId;
	private Integer DepartmentId;
	private Integer Level;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@NotNull(message="{user.UserName.required}")
	@Size(max=50,message="{user.UserName.invalid}")
	@JsonProperty("UserName")
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@NotBlank(message="{user.UserCode.required}")
	@Size(max=50,message="{user.UserCode.invalid}")
	@JsonProperty("UserCode")
	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	@NotNull(message="{user.Account.required}")
	@Size(max=46,message="{user.Account.invalid}")
	@Pattern(regexp="^[A-Za-z0-9]{1,46}$",message="{user.Account.illegal}")// ^[A-Za-z0-9]{1,46}$
	@JsonProperty("Account")
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
//	@NotNull(message="{user.Password.required}")
//	@JsonProperty("Password")
//	public String getPassword() {
//		return Password;
//	}
//	public void setPassword(String password) {
//		Password = password;
//	}
	//@NotNull(message="{user.PlatformId.required}")
	@JsonProperty("PlatformId")
	public Integer getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(Integer platformId) {
		PlatformId = platformId;
	}
//	@Pattern(regexp = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$",message = "{user.EmailAddress.invalid}")
	@Email(message="{user.EmailAddress.invalid}")
	@Size(max=50,message="{user.EmailAddress.illegal}")
	@JsonProperty("EmailAddress")
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	@Pattern(regexp="^\\s{0}$|^[1][3,4,5,7,8][0-9]{9}$",message="{user.Phone.illegal}")//电话号^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$，手机号 // ^\s{0}$| 允许为空
	@Size(max=50,message="{user.Phone.required}")
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
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@NotNull(message="{user.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty("Role")
	public List<Integer> getRole() {
		return Role;
	}
	public void setRole(List<Integer> role) {
		Role = role;
	}
    @JsonProperty("Station")
    public List<Integer> getStation() {
        return Station;
    }

    public void setStation(List<Integer> station) {
        Station = station;
    }

    @JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	@NotNull(message="{user.ICCardNumber.required}")
	@Size(max=50,message="{user.ICCardNumber.invalid}")
	@JsonProperty("ICCardNumber")
	public String getICCardNumber() {
		return ICCardNumber;
	}

	public void setICCardNumber(String ICCardNumber) {
		this.ICCardNumber = ICCardNumber;
	}
	@JsonProperty("DepartmentId")
	public Integer getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		DepartmentId = departmentId;
	}

	@JsonProperty("Level")
	public Integer getLevel() {
		return Level;
	}

	public void setLevel(Integer level) {
		Level = level;
	}
}
