package com.smartflow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ：tao
 * @date ：Created in 2020/4/18 18:33
 * @description：${description}
 * @modified By：
 * @version: version
 */

@Entity
@Table(name = "BomPageView", schema = "dbo", catalog = "MESDB_SXHTDL")
public class BomPageViewEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name =  "Id")
    private int id;
    @Column(name =  "MaterialId")
    private int materialId;
    @Column(name =  "ValidBegin")
    private Timestamp validBegin;
    @Column(name =  "ValidEnd")
    private Timestamp validEnd;
    @Column(name =  "EditDateTime")
    private Timestamp editDateTime;
    @Column(name =  "CreationDateTime")
    private Timestamp creationDateTime;
    @Column(name =  "Version")
    private int version;
    @Column(name =  "MaterialNumber")
    private String materialNumber;
    @Column(name =  "Name")
    private String name;
    @Column(name =  "Creator")
    private String creator;
    @Column(name =  "Editor")
    private String editor;
    @Column(name =  "Factory")
    private String factory;

    @Basic
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MaterialId")
    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "ValidBegin")
    public Timestamp getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(Timestamp validBegin) {
        this.validBegin = validBegin;
    }

    @Basic
    @Column(name = "ValidEnd")
    public Timestamp getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Timestamp validEnd) {
        this.validEnd = validEnd;
    }

    @Basic
    @Column(name = "EditDateTime")
    public Timestamp getEditDateTime() {
        return editDateTime;
    }

    public void setEditDateTime(Timestamp editDateTime) {
        this.editDateTime = editDateTime;
    }

    @Basic
    @Column(name = "CreationDateTime")
    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Basic
    @Column(name = "Version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Basic
    @Column(name = "MaterialNumber")
    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "Editor")
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Basic
    @Column(name = "Factory")
    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BomPageViewEntity that = (BomPageViewEntity) o;
        return id == that.id &&
                materialId == that.materialId &&
                version == that.version &&
                Objects.equals(validBegin, that.validBegin) &&
                Objects.equals(validEnd, that.validEnd) &&
                Objects.equals(editDateTime, that.editDateTime) &&
                Objects.equals(creationDateTime, that.creationDateTime) &&
                Objects.equals(materialNumber, that.materialNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(factory, that.factory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialId, validBegin, validEnd, editDateTime, creationDateTime, version, materialNumber, name, creator, editor, factory);
    }
}
