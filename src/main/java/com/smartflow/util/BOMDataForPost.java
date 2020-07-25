package com.smartflow.util;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartflow.model.BOMItemModel;

public class BOMDataForPost {
	private Integer MaterialId;
	private Date ValidBegin;
	private Date  ValidEnd;
	private Integer FactoryId;
	private Integer version;
	//private Integer CreatorId;
	//private Date CreationDateTime;
	private List<BOMItemModel> BomItemList;
	@JsonProperty("MaterialId")
	public Integer getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(Integer materialId) {
		MaterialId = materialId;
	}
	@JsonProperty("ValidBegin")
	public Date getValidBegin() {
		return ValidBegin;
	}
	public void setValidBegin(Date validBegin) {
		ValidBegin = validBegin;
	}
	@JsonProperty("ValidEnd")
	public Date getValidEnd() {
		return ValidEnd;
	}
	public void setValidEnd(Date validEnd) {
		ValidEnd = validEnd;
	}
	@JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	/*
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	*/
	@JsonProperty("BomItemList")
	public List<BOMItemModel> getBomItemList() {
		return BomItemList;
	}
	public void setBomItemList(List<BOMItemModel> bomItemList) {
		BomItemList = bomItemList;
	}
	
	

}
