package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 工作站组实体
 * @author admin
 *
 */
@Entity
@Table(name="core.StationGroup")
public class StationGroup implements Serializable {
	private Integer Id;
	private String GroupNumber;
	private String Description;
	private Date CreateDateTime;
	private Integer EditorId;
	private Date EditDateTime;
	private Integer FactoryId;
	private Integer State;
	private Integer CreatorId;
	private Cell Cell;
	private Integer SafetyStock;//安全库存
	@Id
	@GeneratedValue
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("GroupNumber")
	public String getGroupNumber() {
		return GroupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		GroupNumber = groupNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("CreateDateTime")
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	@JsonProperty("EditorId")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	@JsonProperty("FactoryId")
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	@JsonProperty("CellId")
	@ManyToOne
	@JoinColumn(name = "CellId")
	public Cell getCell() {
		return Cell;
	}

	public void setCell(Cell cell) {
		Cell = cell;
	}

	@JsonProperty("SafetyStock")
	public Integer getSafetyStock() {
		return SafetyStock;
	}

	public void setSafetyStock(Integer safetyStock) {
		SafetyStock = safetyStock;
	}
}
