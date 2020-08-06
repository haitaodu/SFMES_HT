package com.smartflow.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDTOByConditionOfMaterialListDTO {
	private Integer PageIndex;
	private Integer PageSize;
	private MaterialListDTO TDto;
	private String MaterialNumber;
	private String Description;
	private Integer MaterialGroupType;
//	private Integer IsProduct;
	private Integer FactoryId;
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
	public MaterialListDTO getTDto() {
		return TDto;
	}
	public void setTDto(MaterialListDTO tDto) {
		TDto = tDto;
	}
	@JsonProperty("MaterialNumber")
	public String getMaterialNumber() {
		return MaterialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("MaterialGroupType")
	public Integer getMaterialGroupType() {
		return MaterialGroupType;
	}
	public void setMaterialGroupType(Integer materialGroupType) {
		MaterialGroupType = materialGroupType;
	}
//	@JsonProperty("IsProduct")
//	public Integer getIsProduct() {
//		return IsProduct;
//	}
//	public void setIsProduct(Integer isProduct) {
//		IsProduct = isProduct;
//	}
	@JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	
	
	
	
	
}
