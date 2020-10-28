package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="core.Role")
public class Role implements Serializable {
	private Integer Id;
	private String RoleName;
	private Integer PlatformId;                                                                                                                                                                                                                                                                                                                                                                                       
	private Date CreationDateTime;
	private Integer CreatorId;
	private Date EditDateTime;
	private Integer EditorId;
	private Integer State;
	private Integer FactoryId;
	private String Visit;
	private String VisitBtn;
	private String AreaIdList;
//	private Integer Type;//是否是实体
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public Integer getPlatformId() {
		return PlatformId;
	}
	public void setPlatformId(Integer platformId) {
		PlatformId = platformId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
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
	public String getVisit() {
		return Visit;
	}
	public void setVisit(String visit) {
		Visit = visit;
	}

	public String getVisitBtn() {
		return VisitBtn;
	}

	public void setVisitBtn(String visitBtn) {
		VisitBtn = visitBtn;
	}

	public String getAreaIdList() {
		return AreaIdList;
	}
	public void setAreaIdList(String areaIdList) {
		AreaIdList = areaIdList;
	}

//	public Integer getType() {
//		return Type;
//	}
//
//	public void setType(Integer type) {
//		Type = type;
//	}
}
