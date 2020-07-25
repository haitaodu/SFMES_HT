package com.smartflow.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "core.JPH")
public class JPH {
    @Id
    @GeneratedValue
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "LineLocationId")
    private com.smartflow.model.Location Location;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaterialId")
    private com.smartflow.model.Material Material;
    private BigDecimal JPH;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CreatorId")
    private User Creator;
    private Date CreationDateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EditorId")
    private User Editor;
    private Date EditDateTime;
    private Integer State;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public com.smartflow.model.Location getLocation() {
        return Location;
    }

    public void setLocation(com.smartflow.model.Location location) {
        this.Location = location;
    }

    public com.smartflow.model.Material getMaterial() {
        return Material;
    }

    public void setMaterial(com.smartflow.model.Material material) {
        Material = material;
    }

    public BigDecimal getJPH() {
        return JPH;
    }

    public void setJPH(BigDecimal JPH) {
        this.JPH = JPH;
    }

    public User getCreator() {
        return Creator;
    }

    public void setCreator(User creator) {
        Creator = creator;
    }

    public Date getCreationDateTime() {
        return CreationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        CreationDateTime = creationDateTime;
    }

    public User getEditor() {
        return Editor;
    }

    public void setEditor(User editor) {
        Editor = editor;
    }

    public Date getEditDateTime() {
        return EditDateTime;
    }

    public void setEditDateTime(Date editDateTime) {
        EditDateTime = editDateTime;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
