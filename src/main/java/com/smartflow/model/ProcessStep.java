package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.ProcessStep")
public class ProcessStep {
@Id
@GeneratedValue
@Column(name="Id")
private int Id;
@Column(name="Secquence")
private int Secquence;
@Column(name="ProcessId")
private Integer ProcessId;
@Column(name="Description")
private String Description;
@Column(name="StationGroupId")
private int StationGroupId;
@Column(name="IsMandatory")
private boolean IsMandatory;
@Column(name="IsNeedSetupCheck")
private boolean IsNeedSetupCheck;
@Column(name="MaximumTestCount")
private Integer MaximumTestCount;
@Column(name="IsBackflush")
private boolean IsBackflush;
@Column(name="EditorId")
private Integer EditorId;
@Column(name="EditDateTime")
private Date EditDateTime;
@Column(name="Layer")
private int Layer;
public int getSecquence() {
	return Secquence;
}
public void setSecquence(int secquence) {
	Secquence = secquence;
}
public Integer getProcessId() {
	return ProcessId;
}
public void setProcessId(Integer processId) {
	ProcessId = processId;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
public int getStationGroupId() {
	return StationGroupId;
}
public void setStationGroupId(int stationGroupId) {
	StationGroupId = stationGroupId;
}
public boolean isIsMandatory() {
	return IsMandatory;
}
public void setIsMandatory(boolean isMandatory) {
	IsMandatory = isMandatory;
}
public boolean isIsNeedSetupCheck() {
	return IsNeedSetupCheck;
}
public void setIsNeedSetupCheck(boolean isNeedSetupCheck) {
	IsNeedSetupCheck = isNeedSetupCheck;
}
public Integer getMaximumTestCount() {
	return MaximumTestCount;
}
public void setMaximumTestCount(Integer maximumTestCount) {
	MaximumTestCount = maximumTestCount;
}
public boolean isIsBackflush() {
	return IsBackflush;
}
public void setIsBackflush(boolean isBackflush) {
	IsBackflush = isBackflush;
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
public int getLayer() {
	return Layer;
}
public void setLayer(int layer) {
	Layer = layer;
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
}
