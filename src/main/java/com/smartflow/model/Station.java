package com.smartflow.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
/**
 * 工作站实体
 * @author admin
 *
 */
@Entity
@Table(name="core.Station")
public class Station {
	private Integer Id;
	private String StationNumber;
	private String Name;
	private Date CreationDateTime;
	private Date EditDateTime;
	private Integer EditorId;
	private Integer StationType;
	private Integer State;
	private Integer FactoryId;
	private Integer CreatorId;
	private String IpAddress;//工站id
	@Id
	@GeneratedValue
	@Column(unique=true, nullable=false)
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@Column(unique=true, nullable=false)
	public String getStationNumber() {
		return StationNumber;
	}
	public void setStationNumber(String stationNumber) {
		StationNumber = stationNumber;
	}
	@Column(nullable=false)
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
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
	@Column(nullable=false)
	public Integer getStationType() {
		return StationType;
	}	
	public void setStationType(Integer stationType) {
		StationType = stationType;
	}
	@Column(nullable=false)
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

	public String getIpAddress() {
		return IpAddress;
	}

	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Station [Id=" + Id + ", StationNumber=" + StationNumber + ", Name=" + Name + ", CreationDateTime="
				+ CreationDateTime + ", EditDateTime=" + EditDateTime + ", EditorId=" + EditorId + ", StationType="
				+ StationType + ", State=" + State + ", FactoryId=" + FactoryId + ", CreatorId=" + CreatorId + "]";
	}
	
	
}
