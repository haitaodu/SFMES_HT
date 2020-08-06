package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnMaterialForAddInitializeViewModel {
	private String MaterialType;
	private String Unit;
	private String ProcurementType;
	private String Location;
	private String MSL;
	private String StationGroup;
	private String Company;
	private String Factory;
	private EditMaterialForEditRetrunInitialization TDto;
	@JsonProperty("MaterialType")
	public String getMaterialType() {
		return MaterialType;
	}
	public void setMaterialType(String materialType) {
		MaterialType = materialType;
	}
	@JsonProperty("Unit")
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	@JsonProperty("ProcurementType")
	public String getProcurementType() {
		return ProcurementType;
	}
	public void setProcurementType(String procurementType) {
		ProcurementType = procurementType;
	}
	@JsonProperty("Location")
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	@JsonProperty("MSL")
	public String getMSL() {
		return MSL;
	}
	public void setMSL(String mSL) {
		MSL = mSL;
	}
	@JsonProperty("StationGroup")
	public String getStationGroup() {
		return StationGroup;
	}
	public void setStationGroup(String stationGroup) {
		StationGroup = stationGroup;
	}
	@JsonProperty("Company")
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	@JsonProperty("Factory")
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	@JsonProperty("TDto")
	public EditMaterialForEditRetrunInitialization getTDto() {
		return TDto;
	}
	public void setTDto(EditMaterialForEditRetrunInitialization tDto) {
		TDto = tDto;
	}
	
}
