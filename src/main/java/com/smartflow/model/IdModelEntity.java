package com.smartflow.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 10:46
 * @description：${description}
 * @modified By：
 * @version: version
 */

@Entity
@Table(name = "IdModel", schema = "core", catalog = "MESDB_SXHTDL")
public class IdModelEntity {
    @Id
    @GeneratedValue
    @Column(name="Id")
    private int id;
    private String modelCode;
    private String modelName;
    private Timestamp creationDateTime;
    private int creatorId;
    private Timestamp editDateTime;
    private Integer editorId;
    private int state;
    private Timestamp validFrom;
    private Timestamp validTo;
    private int counterIdFormatType;
    private int counterFrom;
    private int counterTo;
    private int increment;
    private int counterManagementType;
    private int resetCounterType;
    private boolean isStationRelated;
    private int stationId;
    private int bomheadId;
    @Basic
    @Column(name = "StationId")
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    @Basic
    @Column(name = "BOMHeadId")
    public int getBomheadId() {
        return bomheadId;
    }

    public void setBomheadId(int bomheadId) {
        this.bomheadId = bomheadId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ModelCode", nullable = false, length = 50)
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @Basic
    @Column(name = "ModelName", nullable = false, length = 50)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @Column(name = "CreationDateTime", nullable = false)
    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Basic
    @Column(name = "CreatorId", nullable = false)
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "EditDateTime", nullable = true)
    public Timestamp getEditDateTime() {
        return editDateTime;
    }

    public void setEditDateTime(Timestamp editDateTime) {
        this.editDateTime = editDateTime;
    }

    @Basic
    @Column(name = "EditorId", nullable = true)
    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    @Basic
    @Column(name = "State", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "ValidFrom", nullable = false)
    public Timestamp getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    @Basic
    @Column(name = "ValidTo", nullable = false)
    public Timestamp getValidTo() {
        return validTo;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    @Basic
    @Column(name = "CounterIdFormatType", nullable = false)
    public int getCounterIdFormatType() {
        return counterIdFormatType;
    }

    public void setCounterIdFormatType(int counterIdFormatType) {
        this.counterIdFormatType = counterIdFormatType;
    }

    @Basic
    @Column(name = "CounterFrom", nullable = false)
    public int getCounterFrom() {
        return counterFrom;
    }

    public void setCounterFrom(int counterFrom) {
        this.counterFrom = counterFrom;
    }

    @Basic
    @Column(name = "CounterTo", nullable = false)
    public int getCounterTo() {
        return counterTo;
    }

    public void setCounterTo(int counterTo) {
        this.counterTo = counterTo;
    }

    @Basic
    @Column(name = "Increment", nullable = false)
    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Basic
    @Column(name = "CounterManagementType", nullable = false)
    public int getCounterManagementType() {
        return counterManagementType;
    }

    public void setCounterManagementType(int counterManagementType) {
        this.counterManagementType = counterManagementType;
    }

    @Basic
    @Column(name = "ResetCounterType", nullable = false)
    public int getResetCounterType() {
        return resetCounterType;
    }

    public void setResetCounterType(int resetCounterType) {
        this.resetCounterType = resetCounterType;
    }

    @Basic
    @Column(name = "IsStationRelated", nullable = false)
    public boolean isStationRelated() {
        return isStationRelated;
    }

    public void setStationRelated(boolean stationRelated) {
        isStationRelated = stationRelated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdModelEntity that = (IdModelEntity) o;
        return id == that.id &&
                creatorId == that.creatorId &&
                state == that.state &&
                counterIdFormatType == that.counterIdFormatType &&
                counterFrom == that.counterFrom &&
                counterTo == that.counterTo &&
                increment == that.increment &&
                counterManagementType == that.counterManagementType &&
                resetCounterType == that.resetCounterType &&
                isStationRelated == that.isStationRelated &&
                Objects.equals(modelCode, that.modelCode) &&
                Objects.equals(modelName, that.modelName) &&
                Objects.equals(creationDateTime, that.creationDateTime) &&
                Objects.equals(editDateTime, that.editDateTime) &&
                Objects.equals(editorId, that.editorId) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelCode, modelName, creationDateTime, creatorId, editDateTime, editorId, state, validFrom, validTo, counterIdFormatType, counterFrom, counterTo, increment, counterManagementType, resetCounterType, isStationRelated);
    }

    @Override
    public String toString() {
        return "IdModelEntity{" +
                "id=" + id +
                ", modelCode='" + modelCode + '\'' +
                ", modelName='" + modelName + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", creatorId=" + creatorId +
                ", editDateTime=" + editDateTime +
                ", editorId=" + editorId +
                ", state=" + state +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", counterIdFormatType=" + counterIdFormatType +
                ", counterFrom=" + counterFrom +
                ", counterTo=" + counterTo +
                ", increment=" + increment +
                ", counterManagementType=" + counterManagementType +
                ", resetCounterType=" + resetCounterType +
                ", isStationRelated=" + isStationRelated +
                '}';
    }
}
