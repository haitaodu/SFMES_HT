package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="core.Qualification")
public class Qualification {
    @javax.persistence.Id
    @GeneratedValue
    private Integer Id;
	private String QualificationCode;
	private String Name;
	private Integer State;
	private Date EditDateTime;
    private Integer EditorId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getQualificationCode() {
        return QualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        QualificationCode = qualificationCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
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
