package com.smartflow.view.Process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/8/3 10:30
 * @description：${description}
 */

public class ProcessItemEditeView {
    private Integer Secquence;
    private Integer ProcessId;
    private Integer Id;
    private String Description;
    private Integer StationGroupId;
    private String StationGroupName;
    private boolean IsNeedSetupCheck;
    private String Editor;
    private Date EditDateTime;
    private Integer Key;
    private Boolean IsMandatory;
    @JsonProperty("Secquence")
    public Integer getSecquence() {
        return Secquence;
    }
    public ProcessItemEditeView() {};


    public ProcessItemEditeView(Integer secquence, Integer processId, Integer id, String description, Integer stationGroupId, String stationGroupName, boolean isNeedSetupCheck, String editor, Date editDateTime, Integer key) {
        Secquence = secquence;
        ProcessId = processId;
        Id = id;
        Description = description;
        StationGroupId = stationGroupId;
        StationGroupName = stationGroupName;
        IsNeedSetupCheck = isNeedSetupCheck;
        Editor = editor;
        EditDateTime = editDateTime;
        Key = key;
    }

    public void setSecquence(Integer secquence) {
        Secquence = secquence;
    }
    @JsonProperty("ProcessId")
    public Integer getProcessId() {
        return ProcessId;
    }
    public void setProcessId(Integer processId) {
        ProcessId = processId;
    }
    @JsonProperty("Id")
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    @JsonProperty("Description")
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    @JsonProperty("StationGroupId")
    public Integer getStationGroupId() {
        return StationGroupId;
    }
    public void setStationGroupId(Integer stationGroupId) {
        StationGroupId = stationGroupId;
    }
    @JsonProperty("StationGroup")
    public String getStationGroupName() {
        return StationGroupName;
    }
    public void setStationGroupName(String stationGroupName) {
        StationGroupName = stationGroupName;
    }

    @JsonProperty("IsNeedSetupCheck")
    public boolean isIsNeedSetupCheck() {
        return IsNeedSetupCheck;
    }
    public void setIsNeedSetupCheck(boolean isNeedSetupCheck) {
        IsNeedSetupCheck = isNeedSetupCheck;
    }

    @JsonProperty("Editor")
    public String getEditor() {
        return Editor;
    }
    public void setEditor(String editor) {
        Editor = editor;
    }
    @JsonProperty("EditDateTime")
    public Date getEditDateTime() {
        return EditDateTime;
    }
    public void setEditDateTime(Date editDateTime) {
        EditDateTime = editDateTime;
    }
    @JsonProperty("key")
    public Integer getKey() {
        return Key;
    }
    public void setKey(Integer key) {
        this.Key = key;
    }
    @JsonProperty("IsMandatory")
    public Boolean getMandatory() {
        return IsMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        IsMandatory = mandatory;
    }
}
