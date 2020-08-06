package com.smartflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="core.Station_StationGroup")
public class Station_StationGroup {
	private Integer Id;
	private Integer StationtId;
	private Integer StationGroupId;
	private Integer EditorId;
	private Date EditDateTime;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getStationtId() {
		return StationtId;
	}
	public void setStationtId(Integer stationtId) {
		StationtId = stationtId;
	}
	public Integer getStationGroupId() {
		return StationGroupId;
	}
	public void setStationGroupId(Integer stationGroupId) {
		StationGroupId = stationGroupId;
	}
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	
	
	
}
