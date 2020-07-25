package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class GetJPHListOutputDTO {
    private Integer Id;
    private String Location;
    private String Material;
    private BigDecimal JPH;
    private Integer EightHoursProduction;//8小时产量
    private Integer TenHoursProduction;//10小时产量
    private Integer TwelveHoursProduction;//12小时产量
    private String Creator;
    private Date CreationDateTime;
    private String Editor;
    private Date EditDateTime;
    private String State;
    private String Name;
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public BigDecimal getJPH() {
        return JPH;
    }

    public void setJPH(BigDecimal JPH) {
        this.JPH = JPH;
    }

    public Integer getEightHoursProduction() {
        return EightHoursProduction;
    }

    public void setEightHoursProduction(Integer eightHoursProduction) {
        EightHoursProduction = eightHoursProduction;
    }

    public Integer getTenHoursProduction() {
        return TenHoursProduction;
    }

    public void setTenHoursProduction(Integer tenHoursProduction) {
        TenHoursProduction = tenHoursProduction;
    }

    public Integer getTwelveHoursProduction() {
        return TwelveHoursProduction;
    }

    public void setTwelveHoursProduction(Integer twelveHoursProduction) {
        TwelveHoursProduction = twelveHoursProduction;
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
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
