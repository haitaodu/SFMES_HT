package com.smartflow.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDataForPage {
	int Id;
    String LocationNumber;
    String Description;
    String Area;
    float X;
    float Y;
    float Z;
    String State;
    public LocationDataForPage() {};
    public LocationDataForPage(int id, String locationNumber, String description, String area, float x, float y,
			float z, String state, Date createDateTime, String creator, String editor, Date editDateTime) {
		
		Id = id;
		LocationNumber = locationNumber;
		Description = description;
		Area = area;
		X = x;
		Y = y;
		Z = z;
		State = state;
		CreateDateTime = createDateTime;
		Creator = creator;
		Editor = editor;
		EditDateTime = editDateTime;
	}
    @JsonProperty("Id")
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@JsonProperty("LocationNumber")
	public String getLocationNumber() {
		return LocationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		LocationNumber = locationNumber;
	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@JsonProperty("Area")
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	@JsonProperty("X")
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	@JsonProperty("Y")
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
	@JsonProperty("Z")
	public float getZ() {
		return Z;
	}
	public void setZ(float z) {
		Z = z;
	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JsonProperty("CreateDateTime")
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	Date CreateDateTime;
    String Creator;
    String Editor;
    Date EditDateTime;

}
