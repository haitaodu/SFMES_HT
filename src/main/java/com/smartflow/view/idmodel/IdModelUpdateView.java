package com.smartflow.view.idmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/6/12 10:13
 * @description：${description}
 */

public class IdModelUpdateView {
    private Integer id;
    private String modelCode;
    private String modelName;
    private String supplierCode;
    private String partCode;
    private String supplierSequenceCode;
    private String supplierSelfCode;
    private Date vaildFrom;
    private Date vaildTo;
    private Integer editorId;
    private Integer state;
    private String bomhead;
    private Integer stationId;
    private boolean timeStamp;
    private Integer numberSufiix;
    @JsonProperty("BOMHead")
    public String getBomhead() {
        return bomhead;
    }
    public void setBomhead(String bomhead) {
        this.bomhead = bomhead;
    }
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

    @JsonProperty("Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @JsonProperty("EditorId")
    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    @Override
    public String toString() {
        return "IdModelUpdateView{" +
                "id=" + id +
                ", modelCode='" + modelCode + '\'' +
                ", modelName='" + modelName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", partCode='" + partCode + '\'' +
                ", supplierSequenceCode='" + supplierSequenceCode + '\'' +
                ", supplierSelfCode='" + supplierSelfCode + '\'' +
                ", vaildFrom=" + vaildFrom +
                ", vaildTo=" + vaildTo +
                ", editorId=" + editorId +
                '}';
    }
}
