package com.smartflow.view.idmodel;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/6/12 10:01
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class IdModelDetailView {
    private int id;
    private String modelCode;
    private String modelName;
    private String value;
    private String state;
    private Date creationDateTime;
    private String creator;
    private Date editDateTime;
    private String editor;
    private Date validFrom;
    private Date validTo;
    private String materialNumber;
    private String stationNumber;
    @JsonProperty("MaterialNumber")
    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }
    @JsonProperty("StationNumber")
    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    @JsonProperty("ModelCode")
    public String getModelCode() {
        return modelCode;
    }
    @JsonProperty("ModelName")
    public String getModelName() {
        return modelName;
    }
    @JsonProperty("ValidFrom")
    public Date getValidFrom() {
        return validFrom;
    }
    @JsonProperty("ValidTo")
    public Date getValidTo() {
        return validTo;
    }
    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @JsonProperty("CreationDateTime")
    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @JsonProperty("Creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    @JsonProperty("EditDateTime")
    public Date getEditDateTime() {
        return editDateTime;
    }

    public void setEditDateTime(Date editDateTime) {
        this.editDateTime = editDateTime;
    }
    @JsonProperty("Editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return "IdModelDetailView{" +
                "id=" + id +
                ", modelCode='" + modelCode + '\'' +
                ", modelName='" + modelName + '\'' +
                ", value='" + value + '\'' +
                ", state='" + state + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", creator='" + creator + '\'' +
                ", editDateTime=" + editDateTime +
                ", editor='" + editor + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }
}
