package com.smartflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

//此类型为数据库core.Process所对应的映射类
@Entity
@Table(name="core.Process")
public class ProcessModel implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="Id")
    private Integer Id;
	@Column(name="ProcessNumber")
	private String ProcessNumber;
	@Column(name="MaterialId")
	private  Integer MaterialId;
	@Column(name="Description")
	private  String Description;
	@Column(name="EditDateTime")
	private Date EditDateTime;
	@Column(name="EditorId")
	private Integer EditorId;
	@Column(name="State")
	private Integer state;
	@Column(name="ValidBegin")
	private Date ValidBegin;
	@Column(name="ValidEnd")
	private Date ValidEnd;
	@Column(name="FactoryId")
	private Integer FactoryId;
	@Column(name="CreatorId")
	private Integer CreatorId;
	@Column(name="CreationDateTime")
	private Date CreationDateTime;

	@ManyToOne
	@JoinColumn(name = "CellId")
	private Cell Cell;
	public Integer getId() {
		return Id;
	}
	private String ParentProcessNumber;
	@Column(name="Version")
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ProcessModel() {};
	public ProcessModel(Integer id, String processNumber, Integer materialId, String description, Date editDateTime,
			Integer editorId, Integer state, Date validBegin, Date validEnd, Integer factoryId, Integer creatorId,
			Date creationDateTime,String parentProcessNumber,Integer version) {
		
		Id = id;
		ProcessNumber = processNumber;
		MaterialId = materialId;
		Description = description;
		EditDateTime = editDateTime;
		EditorId = editorId;
		this.state = state;
		ValidBegin = validBegin;
		ValidEnd = validEnd;
		FactoryId = factoryId;
		CreatorId = creatorId;
		CreationDateTime = creationDateTime;
		ParentProcessNumber=parentProcessNumber;
		this.version=version;
	}
	@JsonProperty("ParentProcessNumber")
	public String getParentProcessNumber() {
		return ParentProcessNumber;
	}

	public void setParentProcessNumber(String parentProcessNumber) {
		ParentProcessNumber = parentProcessNumber;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getProcessNumber() {
		return ProcessNumber;
	}
	public void setProcessNumber(String processNumber) {
		ProcessNumber = processNumber;
	}
	public Integer getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(Integer materialId) {
		MaterialId = materialId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
	public int getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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

	public Cell getCell() {
		return Cell;
	}

	public void setCell(Cell cell) {
		Cell = cell;
	}
}
