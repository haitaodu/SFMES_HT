package com.smartflow.view.idmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/6/12 10:12
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class IdModelSaveView {
    private String modelCode;
    private String modelName;
    private String supplierCode;
    private String partCode;
    private String supplierSequenceCode;
    private String supplierSelfCode;
    private Date vaildFrom;
    private Date vaildTo;
    private Integer creatorId;
    private Integer state;
    private Integer bomheadId;
    private Integer stationId;
    private boolean timeStamp;
    private Integer numberSufiix;

    @JsonProperty("TimeStamp")
    public boolean isTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(boolean timeStamp) {
        this.timeStamp = timeStamp;
    }
    @JsonProperty("NumberSuffix")
    public Integer getNumberSufiix() {
        return numberSufiix;
    }

    public void setNumberSufiix(Integer numberSufiix) {
        this.numberSufiix = numberSufiix;
    }

    @JsonProperty("BOMHeadId")
    public Integer getBomheadId() {
        return bomheadId;
    }

    public void setBomheadId(Integer bomheadId) {
        this.bomheadId = bomheadId;
    }
    @JsonProperty("StationId")
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    @JsonProperty("State")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @JsonProperty("CreatorId")
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
    @JsonProperty("VaildFrom")
    public Date getVaildFrom() {
        return vaildFrom;
    }

    public void setVaildFrom(Date vaildFrom) {
        this.vaildFrom = vaildFrom;
    }
    @JsonProperty("VaildTo")
    public Date getVaildTo() {
        return vaildTo;
    }

    public void setVaildTo(Date vaildTo) {
        this.vaildTo = vaildTo;
    }
    @JsonProperty("ModelCode")
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
    @JsonProperty("ModelName")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    @JsonProperty("SupplierCode")
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    @JsonProperty("PartCode")
    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }
    @JsonProperty("SupplierSequenceCode")
    public String getSupplierSequenceCode() {
        return supplierSequenceCode;
    }

    public void setSupplierSequenceCode(String supplierSequenceCode) {
        this.supplierSequenceCode = supplierSequenceCode;
    }
    @JsonProperty("SupplierSelfCode")
    public String getSupplierSelfCode() {
        return supplierSelfCode;
    }

    public void setSupplierSelfCode(String supplierSelfCode) {
        this.supplierSelfCode = supplierSelfCode;
    }

    @Override
    public String toString() {
        return "IdModelSaveView{" +
                "modelCode='" + modelCode + '\'' +
                ", modelName='" + modelName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", partCode='" + partCode + '\'' +
                ", supplierSequenceCode='" + supplierSequenceCode + '\'' +
                ", supplierSelfCode='" + supplierSelfCode + '\'' +
                ", vaildFrom=" + vaildFrom +
                ", vaildTo=" + vaildTo +
                ", creatorId=" + creatorId +
                '}';
    }
}
