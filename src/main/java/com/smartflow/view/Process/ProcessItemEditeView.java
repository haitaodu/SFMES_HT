package com.smartflow.view.Process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/8/3 10:30
 * @description：${description}
 */

public class ProcessItemEditeView {
    private int Secquence;
    private Integer ProcessId;
    private int Id;
    private String Description;
    private int StationGroupId;
    private String StationGroupName;
    private boolean IsNeedSetupCheck;
    private String Editor;
    private Date EditDateTime;
    private int Key;
    @JsonProperty("Secquence")
    public int getSecquence() {
        return Secquence;
    }
    public ProcessItemEditeView() {};


    public ProcessItemEditeView(int secquence, Integer processId, int id, String description, int stationGroupId, String stationGroupName, boolean isNeedSetupCheck, String editor, Date editDateTime, int key) {
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

    public void setSecquence(int secquence) {
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
    public int getId() {
        return Id;
    }
    public void setId(int id) {
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
    public int getStationGroupId() {
        return StationGroupId;
    }
    public void setStationGroupId(int stationGroupId) {
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
    public int getKey() {
        return Key;
    }
    public void setKey(int key) {
        this.Key = key;
    }
}
