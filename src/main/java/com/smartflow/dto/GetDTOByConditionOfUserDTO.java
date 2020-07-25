package com.smartflow.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDTOByConditionOfUserDTO {
	private Integer PageIndex;
	private Integer PageSize;
	private UserDTO TDto;
	private String UserName;
	private String Account;
	private Integer FactoryId;
//	private List<Integer> RoleIdList;
	private Integer DepartmentId;
	@NotNull(message = "{pageIndex.required}")
	@JsonProperty("PageIndex")
	public Integer getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		PageIndex = pageIndex;
	}

	@NotNull(message = "{pageSize.required}")
	@JsonProperty("PageSize")
	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}

	@JsonProperty("TDto")
	public UserDTO getTDto() {
		return TDto;
	}

	public void setTDto(UserDTO tDto) {
		TDto = tDto;
	}

	@JsonProperty("UserName")
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@JsonProperty("Account")
	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	@JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}

	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}

//	@JsonProperty("RoleIdList")
//	public List<Integer> getRoleIdList() {
//		return RoleIdList;
//	}
//
//	public void setRoleIdList(List<Integer> roleIdList) {
//		RoleIdList = roleIdList;
//	}

	@JsonProperty("DepartmentId")
	public Integer getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		DepartmentId = departmentId;
	}
}