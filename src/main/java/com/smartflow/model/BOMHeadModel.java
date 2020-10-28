package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.BOMHead")
public class BOMHeadModel implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="MaterialId")
    private int MaterialId;
	@Column(name="ValidBegin")
	private Date ValidBegin;
	@Column(name="ValidEnd")
	private Date ValidEnd;
	@Column(name="EditDateTime")
	private Date EditDateTime;
	@Column(name="EditorId")
	private Integer EditorId;
	@Column(name="State")
	private Integer State;
	@Column(name="FactoryId")
	private Integer FactoryId;
	@Column(name="CreatorId")
	private Integer CreatorId;
	@Column(name="CreationDateTime")
	private Date CreationDateTime;
	@Column(name="Version")
	private int Version;
	@Column(name="ERPBOMVersion")
	private String ERPBOMVersion;
	@Column(name="ProductName")
	private String ProductName;

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String prodcutName) {
		ProductName = prodcutName;
	}

	@Override
	public String toString() {
		return "BOMHeadModel{" +
				"Id=" + Id +
				", MaterialId=" + MaterialId +
				", ValidBegin=" + ValidBegin +
				", ValidEnd=" + ValidEnd +
				", EditDateTime=" + EditDateTime +
				", EditorId=" + EditorId +
				", State=" + State +
				", FactoryId=" + FactoryId +
				", CreatorId=" + CreatorId +
				", CreationDateTime=" + CreationDateTime +
				", Version=" + Version +
				", ERPBOMVersion='" + ERPBOMVersion + '\'' +
				", ProductName='" + ProductName + '\'' +
				'}';
	}

	public BOMHeadModel() {};
	public BOMHeadModel(int id, int materialId, Date validBegin, Date validEnd, Date editDateTime, Integer editorId,
			Integer state, Integer factoryId, Integer creatorId, Date creationDateTime, int version,
			String eRPBOMVersion,String productName) {
		Id = id;
		MaterialId = materialId;
		ValidBegin = validBegin;
		ValidEnd = validEnd;
		EditDateTime = editDateTime;
		EditorId = editorId;
		State = state;
		FactoryId = factoryId;
		CreatorId = creatorId;
		CreationDateTime = creationDateTime;
		Version = version;
		ERPBOMVersion = eRPBOMVersion;
		ProductName=productName;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(int materialId) {
		MaterialId = materialId;
	}
	public Date getValidBegin() {
		return ValidBegin;
	}
	public void setValidBegin(Date validBegin) {
		ValidBegin = validBegin;
	}
	public Date getValidEnd() {
		return ValidEnd;
	}
	public void setValidEnd(Date validEnd) {
		ValidEnd = validEnd;
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
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}
	public String getERPBOMVersion() {
		return ERPBOMVersion;
	}
	public void setERPBOMVersion(String eRPBOMVersion) {
		ERPBOMVersion = eRPBOMVersion;
	}

	
}
