package com.smartflow.view.Process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/7/29 18:00
 * @description：${description}
 */

public class ProcsessEditeView {
    int	Id;
    String ProcessNumber;
    String MaterialNumber;
    int FactoryId;
    int State;
    Date ValidBegin;
    Date ValidEnd;
    Date CreationDateTime;
    Date    EditDateTime;
    int    Editor;
    String parentProcessNumber;
    int version;



    public ProcsessEditeView() {};
    public ProcsessEditeView(int id, String processNumber, String materialNumber, int factoryId, int state,
                             Date validBegin, Date validEnd, Date creationDateTime, Date editDateTime,
                             int editor
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
    public int getFactoryId() {
        return FactoryId;
    }
    public void setFactory(int factoryId) {
        FactoryId = factoryId;
    }
    @JsonProperty("State")
    public int getState() {
        return State;
    }
    public void setState(int state) {
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
    public int getEditor() {
        return Editor;
    }
    public void setEditor(int editor) {
        Editor = editor;
    }
}
