package com.smartflow.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDTOByConditionOfRoleDTO {
	private Integer PageIndex;
	private Integer PageSize;
	private RoleDTO TDto;
	@NotNull(message="{pageIndex.required}")
	@JsonProperty("PageIndex")
	public Integer getPageIndex() {
		return PageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		PageIndex = pageIndex;
	}
	@NotNull(message="{pageSize.required}")
	@JsonProperty("PageSize")
	public Integer getPageSize() {
		return PageSize;
	}
	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}
	@JsonProperty("TDto")
	public RoleDTO getTDto() {
		return TDto;
	}
	public void setTDto(RoleDTO tDto) {
		TDto = tDto;
	}
	
}
