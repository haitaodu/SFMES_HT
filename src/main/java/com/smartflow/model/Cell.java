package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 线体
 * @author admin
 *
 */
@Entity
@Table(name="core.Cell")
public class Cell implements Serializable {
	private Integer Id;
	private String CellNumber;
	private String Description;
	private Date EditDateTime;
	private Integer EditorId;
	private Integer FactoryId;
	private Integer CreatorId;
	private Date CreationDateTime;
	private Integer State;
	private Integer AreaId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getCellNumber() {
		return CellNumber;
	}
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
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
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}

	public Integer getAreaId() {
		return AreaId;
	}

	public void setAreaId(Integer areaId) {
		AreaId = areaId;
	}

	@Override
	public String toString() {
		return "Cell [Id=" + Id + ", CellNumber=" + CellNumber + ", Description=" + Description + ", EditDateTime="
				+ EditDateTime + ", EditorId=" + EditorId + ", FactoryId=" + FactoryId + ", CreatorId=" + CreatorId
				+ ", CreationDateTime=" + CreationDateTime + ", State=" + State + "]";
	}
	
	public Cell() {
	
	}
	public Cell(Integer id, String cellNumber, String description, Integer state) {
		Id = id;
		CellNumber = cellNumber;
		Description = description;
		State = state;
	}
	public Cell(Integer id, String cellNumber, String description, Date editDateTime, Integer editorId,
			Integer creatorId, Date creationDateTime, Integer state) {
		Id = id;
		CellNumber = cellNumber;
		Description = description;
		EditDateTime = editDateTime;
		EditorId = editorId;
		CreatorId = creatorId;
		CreationDateTime = creationDateTime;
		State = state;
	}
	
	
	
}
