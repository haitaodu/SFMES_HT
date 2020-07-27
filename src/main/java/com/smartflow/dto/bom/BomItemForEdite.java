package com.smartflow.dto.bom;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ：tao
 * @date ：Created in 2020/7/27 14:10
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class BomItemForEdite {
    int MaterialId;
    String MaterialName;
    String MaterialNumber;
    String Designator;
    float Quantity;
    int StationGroupId;
    String StationGroup;
    Boolean IsNeedSetupCheck;
    int Layer;
    Boolean IsAlternative;
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
    public BomItemForEdite() {};
    public BomItemForEdite(int materialId, String materialName, //int version,
                           String materialNumber, String designator,
                           float quantity, int stationGroupId, String stationGroup, Boolean isNeedSetupCheck, int layer,
                           Boolean isAlternative, String unit, int unitId, int key) {

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
    public Boolean isIsNeedSetupCheck() {
        return IsNeedSetupCheck;
    }
    public void setIsNeedSetupCheck(Boolean isNeedSetupCheck) {
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
    public Boolean isIsAlternative() {
        return IsAlternative;
    }
    public void setIsAlternative(Boolean isAlternative) {
        IsAlternative = isAlternative;
    }

}
