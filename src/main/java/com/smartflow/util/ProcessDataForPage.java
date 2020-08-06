package com.smartflow.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessDataForPage {
int	Id;
String ProcessNumber;
String MaterialNumber;
String Factory;
String State;
Date ValidBegin;
Date ValidEnd;
Date CreationDateTime;
Date    EditDateTime;
String    Editor;
public ProcessDataForPage() {};
public ProcessDataForPage(int id, String processNumber, String materialNumber, String factory, String state,
		Date validBegin, Date validEnd, Date creationDateTime, Date editDateTime, String editor) {
	
	Id = id;
	ProcessNumber = processNumber;
	MaterialNumber = materialNumber;
	Factory = factory;
	State = state;
	ValidBegin = validBegin;
	ValidEnd = validEnd;
	CreationDateTime = creationDateTime;
	EditDateTime = editDateTime;
	Editor = editor;
}
@JsonProperty("Id")
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
@JsonProperty("ProcessNumber")
public String getProcessNumber() {
	return ProcessNumber;
}
public void setProcessNumber(String processNumber) {
	ProcessNumber = processNumber;
}
@JsonProperty("MaterialNumber")
public String getMaterialNumber() {
	return MaterialNumber;
}
public void setMaterialNumber(String materialNumber) {
	MaterialNumber = materialNumber;
}
@JsonProperty("Factory")
public String getFactory() {
	return Factory;
}
public void setFactory(String factory) {
	Factory = factory;
}
@JsonProperty("State")
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
@JsonProperty("ValidBegin")
public Date getValidBegin() {
	return ValidBegin;
}
public void setValidBegin(Date validBegin) {
	ValidBegin = validBegin;
}
@JsonProperty("ValidEnd")
public Date getValidEnd() {
	return ValidEnd;
}
public void setValidEnd(Date validEnd) {
	ValidEnd = validEnd;
}
@JsonProperty("CreationDateTime")
public Date getCreationDateTime() {
	return CreationDateTime;
}
public void setCreationDateTime(Date creationDateTime) {
	CreationDateTime = creationDateTime;
}
@JsonProperty("EditDateTime")
public Date getEditDateTime() {
	return EditDateTime;
}
public void setEditDateTime(Date editDateTime) {
	EditDateTime = editDateTime;
}
@JsonProperty("Editor")
public String getEditor() {
	return Editor;
}
public void setEditor(String editor) {
	Editor = editor;
}

}
