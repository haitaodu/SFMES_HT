package com.smartflow.view.Process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/7/29 18:00
 * @description：${description}
 */

public class ProcsessEditeView {
    Integer Id;
    String ProcessNumber;
    String MaterialNumber;
    Integer FactoryId;
    Integer State;
    Date ValidBegin;
    Date ValidEnd;
    Date CreationDateTime;
    Date    EditDateTime;
    Integer    Editor;
    String parentProcessNumber;
    Integer version;



    public ProcsessEditeView() {};
    public ProcsessEditeView(Integer id, String processNumber, String materialNumber, Integer factoryId, Integer state,
                             Date validBegin, Date validEnd, Date creationDateTime, Date editDateTime,
                             Integer editor
            , String parentProcessNumber, Integer version) {

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
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
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
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
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
    public Integer getFactoryId() {
        return FactoryId;
    }
    public void setFactory(Integer factoryId) {
        FactoryId = factoryId;
    }
    @JsonProperty("State")
    public Integer getState() {
        return State;
    }
    public void setState(Integer state) {
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
    public Integer getEditor() {
        return Editor;
    }
    public void setEditor(Integer editor) {
        Editor = editor;
    }
}
