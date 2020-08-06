package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class JPHDetailOutPut {
    private Integer Id;
    private String LocationNumber;
    private String MaterialNumber;
    private BigDecimal JPH;


    private String Creator;
    private Date CreationDateTime;
    private String Editor;
    private Date EditDateTime;
    private String State;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getMaterialNumber() {
        return MaterialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        MaterialNumber = materialNumber;
    }

    public BigDecimal getJPH() {
        return JPH;
    }

    public void setJPH(BigDecimal JPH) {
        this.JPH = JPH;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreationDateTime() {
        return CreationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        CreationDateTime = creationDateTime;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String editor) {
        Editor = editor;
    }

    public Date getEditDateTime() {
        return EditDateTime;
    }

    public void setEditDateTime(Date editDateTime) {
        EditDateTime = editDateTime;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
    public String getLocationNumber() {
        return LocationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        LocationNumber = locationNumber;
    }

}
