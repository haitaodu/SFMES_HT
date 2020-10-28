package com.smartflow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="core.StationAccessControl")
public class StationAccessControl implements Serializable {
    @javax.persistence.Id
    @GeneratedValue
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "StationId")
	private Station Station;
    @ManyToOne
    @JoinColumn(name = "QualificationId")
	private Qualification Qualification;
	private Integer State;
	private Integer AccessLevel;
    private Date EditDateTime;
    private Integer EditorId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public com.smartflow.model.Station getStation() {
        return Station;
    }

    public void setStation(com.smartflow.model.Station station) {
        Station = station;
    }

    public com.smartflow.model.Qualification getQualification() {
        return Qualification;
    }

    public void setQualification(com.smartflow.model.Qualification qualification) {
        Qualification = qualification;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public Integer getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        AccessLevel = accessLevel;
    }

    public Date getEditDateTime() {
        return EditDateTime;
    }

    public void setEditDateTime(Date editDateTime) {
        EditDateTime = editDateTime;
    }

    public Integer getEditorId() {
        return EditorId;
    }

    public void setEditorId(Integer editorId) {
        EditorId = editorId;
    }
}
