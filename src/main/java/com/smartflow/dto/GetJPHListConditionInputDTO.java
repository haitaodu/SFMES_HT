package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class GetJPHListConditionInputDTO {
    private  String TDto;
    public String getTDto() {
        return TDto;
    }
    public void setTDto(String TDto) {
        this.TDto = TDto;
    }
    private Integer LocationId;
    private Integer PageIndex;
    private Integer PageSize;
    private String MaterialNumber;
    public String getMaterialNumber() {
        return MaterialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        MaterialNumber = materialNumber;
    }

    public Integer getLocationId() {
        return LocationId;
    }

    public void setLocationId(Integer locationId) {
        LocationId = locationId;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

}
