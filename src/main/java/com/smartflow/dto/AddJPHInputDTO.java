package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class AddJPHInputDTO {
    private Integer LocationId;
    private String MaterialNumber;
    private BigDecimal JPH;
    private Integer CreatorId;
    private Integer State;

    public Integer getLocationId() {
        return LocationId;
    }
    public String getMaterialNumber() {
        return MaterialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        MaterialNumber = materialNumber;
    }
    public void setLocationId(Integer locationId) {
        LocationId = locationId;
    }



    public BigDecimal getJPH() {
        return JPH;
    }

    public void setJPH(BigDecimal JPH) {
        this.JPH = JPH;
    }

    public Integer getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(Integer creatorId) {
        CreatorId = creatorId;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
