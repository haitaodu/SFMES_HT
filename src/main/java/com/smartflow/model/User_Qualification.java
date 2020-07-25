package com.smartflow.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="core.User_Qualification")
public class User_Qualification {
    @javax.persistence.Id
    @GeneratedValue
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User User;
    @ManyToOne
    @JoinColumn(name = "QualificationId")
    private Qualification Qualification;
    private Date ValidateFrom;
    private Date ValidateTo;
    private Integer State;
    private Date EditDateTime;
    private Integer EditorId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public com.smartflow.model.User getUser() {
        return User;
    }

    public void setUser(com.smartflow.model.User user) {
        User = user;
    }

    public Qualification getQualification() {
        return Qualification;
}

    public void setQualification(Qualification qualification) {
        Qualification = qualification;
    }

    public Date getValidateFrom() {
        return ValidateFrom;
    }

    public void setValidateFrom(Date validateFrom) {
        ValidateFrom = validateFrom;
    }

    public Date getValidateTo() {
        return ValidateTo;
    }

    public void setValidateTo(Date validateTo) {
        ValidateTo = validateTo;
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
