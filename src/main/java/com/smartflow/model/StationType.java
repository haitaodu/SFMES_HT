package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="core.StationType")
public class StationType {
    private Integer Id;
	private String StationTypeName;
	private Date CreateTime;
	private Integer StationTypeCode;
    @javax.persistence.Id
    @GeneratedValue
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStationTypeName() {
        return StationTypeName;
    }

    public void setStationTypeName(String stationTypeName) {
        StationTypeName = stationTypeName;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Integer getStationTypeCode() {
        return StationTypeCode;
    }

    public void setStationTypeCode(Integer stationTypeCode) {
        StationTypeCode = stationTypeCode;
    }
}
