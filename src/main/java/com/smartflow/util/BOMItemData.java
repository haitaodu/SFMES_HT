package com.smartflow.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BOMItemData {
	int MaterialId;
	String MaterialName;
	String MaterialNumber;
	String Designator;
	float Quantity;
	int StationGroupId;
	String StationGroup;
	String IsNeedSetupCheck;
	int Layer;
	String IsAlternative;
	String Unit;
	int UnitId;
	int Key;
	@JsonProperty("Unit")
	public String getUnit() {
		return Unit;
	}
	@JsonProperty("key")
	public int getKey() {
		return Key;
	}
	public void setKey(int key) {
		this.Key = key;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	@JsonProperty("UnitId")
	public int getUnitId() {
		return UnitId;
	}
	public void setUnitId(int unitId) {
		UnitId = unitId;
	}
	@JsonProperty("MaterialId")
	public int getMaterialId() {
		return MaterialId;
	}
	public BOMItemData() {};
	public BOMItemData(int materialId, String materialName, //int version,
					   String materialNumber, String designator,
			float quantity, int stationGroupId, String stationGroup, String isNeedSetupCheck, int layer,
			String isAlternative,String unit,int unitId,int key) {

		MaterialId = materialId;
		MaterialName = materialName;
		MaterialNumber = materialNumber;
		Designator = designator;
		Quantity = quantity;
		StationGroupId = stationGroupId;
		StationGroup = stationGroup;
		IsNeedSetupCheck = isNeedSetupCheck;
		Layer = layer;
		IsAlternative = isAlternative;
		Unit=unit;
		UnitId=unitId;
		Key=key;
	}
	public void setMaterialId(int materialId) {
		MaterialId = materialId;
	}
	@JsonProperty("MaterialName")
	public String getMaterialName() {
		return MaterialName;
	}
	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
	@JsonProperty("MaterialNumber")
	public String getMaterialNumber() {
		return MaterialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}
	@JsonProperty("Designator")
	public String getDesignator() {
		return Designator;
	}
	public void setDesignator(String designator) {
		Designator = designator;
	}
	@JsonProperty("Quantity")
	public float getQuantity() {
		return Quantity;
	}
	public void setQuantity(float quantity) {
		Quantity = quantity;
	}
	@JsonProperty("StationGroupId")
	public int getStationGroupId() {
		return StationGroupId;
	}
	public void setStationGroupId(int stationGroupId) {
		StationGroupId = stationGroupId;
	}
	@JsonProperty("StationGroup")
	public String getStationGroup() {
		return StationGroup;
	}
	public void setStationGroup(String stationGroup) {
		StationGroup = stationGroup;
	}
	@JsonProperty("IsNeedSetupCheck")
	public String isIsNeedSetupCheck() {
		return IsNeedSetupCheck;
	}
	public void setIsNeedSetupCheck(String isNeedSetupCheck) {
		IsNeedSetupCheck = isNeedSetupCheck;
	}
	@JsonProperty("Layer")
	public int getLayer() {
		return Layer;
	}
	public void setLayer(int layer) {
		Layer = layer;
	}
	@JsonProperty("IsAlternative")
	public String isIsAlternative() {
		return IsAlternative;
	}
	public void setIsAlternative(String isAlternative) {
		IsAlternative = isAlternative;
	}
}
