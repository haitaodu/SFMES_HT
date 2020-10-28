package com.smartflow.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="core.Customer")
public class CustomerInEditInitializeModel implements Serializable {
@Id
@GeneratedValue
@Column(name="Id")
private  int id;
@Column(name="CustomerCode")
private String code;
@Column(name="Name")
private String name;
@Column(name="DUNS")
private String duns;
@Column(name="Province")
private String province;
@Column(name="Country")
private String country;
@Column(name="Address")
private String adress;
@Column (name="PostCode")
private String postcode;
@Column(name="Fax")
private String fax;
@Column(name="ContactPerson")
private String contactperson;
@Column(name="Email")
private String email;
@Column(name="Telphone")
private String telphone;
@Column(name="MobilePhone")
private String mobilephone;
@Column(name="EditorId")
private Integer Editorid;
@Column(name="EditDateTime")
private String editdatetime;
@Column(name="State")
private String state;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDuns() {
	return duns;
}
public void setDuns(String duns) {
	this.duns = duns;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getPostcode() {
	return postcode;
}
public void setPostcode(String postcode) {
	this.postcode = postcode;
}
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
public String getContactperson() {
	return contactperson;
}
public void setContactperson(String contactperson) {
	this.contactperson = contactperson;
}
public String getEmail() {
	return email;
}
public void setEmal(String email) {
	this.email = email;
}
public String getTelphone() {
	return telphone;
}
public void setTelphone(String telphone) {
	this.telphone = telphone;
}
public String getMobilephone() {
	return mobilephone;
}
public void setMobilephone(String mobilephone) {
	this.mobilephone = mobilephone;
}
public Integer getEditorId() {
	return Editorid;
}
public void setEditor(Integer Editorid) {
	this.Editorid = Editorid;
}
public String getEditdatetime() {
	return editdatetime;
}
public void setEditdatetime(String editdatetime) {
	this.editdatetime = editdatetime;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
}
