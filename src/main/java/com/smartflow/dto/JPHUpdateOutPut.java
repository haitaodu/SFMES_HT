package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class JPHUpdateOutPut {
    List<Map<String,Object>> LocationList;
    String MaterialNumber;
    Integer State;
    BigDecimal JPH;
    String LoactionId;
    Integer Id;



    public List<Map<String, Object>> getLocationList() {
        return LocationList;
    }

    public void setLocationList(List<Map<String, Object>> locationList) {
        LocationList = locationList;
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
    public String getLoactionId() {
        return LoactionId;
    }

    public void setLoactionId(String loactionId) {
        LoactionId = loactionId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
