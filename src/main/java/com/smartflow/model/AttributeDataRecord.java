package com.smartflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="trace.AttributeDataRecord")
public class AttributeDataRecord {
	private Integer Id;
	private Integer EntityType;
	private Integer EntityId;
	private String EntityUniqueCode;
	private Integer AssignedStationNumberId;
	private Integer AttributeTypeId;
	private String AttributeValue;
	private Integer AssignMethod;
	private Date CreateDateTime;
	private Integer CreatorId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getEntityType() {
		return EntityType;
	}
	public void setEntityType(Integer entityType) {
		EntityType = entityType;
	}
	public Integer getEntityId() {
		return EntityId;
	}
	public void setEntityId(Integer entityId) {
		EntityId = entityId;
	}
	public String getEntityUniqueCode() {
		return EntityUniqueCode;
	}
	public void setEntityUniqueCode(String entityUniqueCode) {
		EntityUniqueCode = entityUniqueCode;
	}
	public Integer getAssignedStationNumberId() {
		return AssignedStationNumberId;
	}
	public void setAssignedStationNumberId(Integer assignedStationNumberId) {
		AssignedStationNumberId = assignedStationNumberId;
	}
	public Integer getAttributeTypeId() {
		return AttributeTypeId;
	}
	public void setAttributeTypeId(Integer attributeTypeId) {
		AttributeTypeId = attributeTypeId;
	}
	public String getAttributeValue() {
		return AttributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		AttributeValue = attributeValue;
	}
	public Integer getAssignMethod() {
		return AssignMethod;
	}
	public void setAssignMethod(Integer assignMethod) {
		AssignMethod = assignMethod;
	}
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	
}
