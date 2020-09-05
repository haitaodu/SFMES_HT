package com.smartflow.view.Process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/7/30 15:47
 * @description：${description}
 */

public class ProcessDetailView {
    int	Id;
    String ProcessNumber;
    String MaterialNumber;
    String FactoryId;
    String State;
    Date ValidBegin;
    Date ValidEnd;
    Date CreationDateTime;
    Date    EditDateTime;
    String    Editor;
    String parentProcessNumber;
    int version;
    public ProcessDetailView() {};
    public ProcessDetailView(int id, String processNumber, String materialNumber, String factoryId, String state,
                             Date validBegin, Date validEnd, Date creationDateTime, Date editDateTime, String editor
            , String parentProcessNumber, int version) {

        Id = id;
        ProcessNumber = processNumber;
        MaterialNumber = materialNumber;
        this.FactoryId = factoryId;
        State = state;
        ValidBegin = validBegin;
        ValidEnd = validEnd;
        CreationDateTime = creationDateTime;
        EditDateTime = editDateTime;
        Editor = editor;
        this.parentProcessNumber=parentProcessNumber;
        this.version=version;
    }
    @JsonProperty("Version")
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    @JsonProperty("ParentProcessNumber")
    public String getParentProcessNumber() {
        return parentProcessNumber;
    }
    public void setParentProcessNumber(String parentProcessNumber) {
        this.parentProcessNumber = parentProcessNumber;
    }
    @JsonProperty("Id")
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    @JsonProperty("ProcessNumber")
    public String getProcessNumber() {
        return ProcessNumber;
    }
    public void setProcessNumber(String processNumber) {
        ProcessNumber = processNumber;
    }
    @JsonProperty("MaterialNumber")
    public String getMaterialNumber() {
        return MaterialNumber;
    }
    public void setMaterialNumber(String materialNumber) {
        MaterialNumber = materialNumber;
    }
    @JsonProperty("Factory")
    public String getFactoryId() {
        return FactoryId;
    }
    public void setFactory(String factoryId) {
        FactoryId = factoryId;
    }
    @JsonProperty("State")
    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
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
    @JsonProperty("CreationDateTime")
    public Date getCreationDateTime() {
        return CreationDateTime;
    }
    public void setCreationDateTime(Date creationDateTime) {
        CreationDateTime = creationDateTime;
    }
    @JsonProperty("EditDateTime")
    public Date getEditDateTime() {
        return EditDateTime;
    }
    public void setEditDateTime(Date editDateTime) {
        EditDateTime = editDateTime;
    }
    @JsonProperty("Editor")
    public String getEditor() {
        return Editor;
    }
    public void setEditor(String editor) {
        Editor = editor;
    }
}
