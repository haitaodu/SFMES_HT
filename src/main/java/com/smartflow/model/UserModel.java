package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.[User]")
public class UserModel {
@Id
@GeneratedValue
@Column(name="Id")
private int id;
@Column(name="UserName")
private String name;
@Column(name="PassWord")
private String password;
@Column(name="PlatformId")
private Integer platfor;
@Column(name="EmailAddress")
private String emailadress;
@Column(name="Phone")
private String phone;
@Column(name="CreationDateTime")
private String creationdate;
@Column(name="CreatorId")
private  Integer creatorid;
@Column(name="EditDateTime")
private Date editdatetime;
@Column(name="EditorId")
private Integer editorid;
@Column(name="State")
private Integer state;
@Column(name="LastLoginTime")
private Date lastlogintime;
@Column(name="Account")
private String account;
@Column(name="UserCode")
private String usercode;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Integer getPlatfor() {
	return platfor;
}
public void setPlatfor(Integer platfor) {
	this.platfor = platfor;
}
public String getEmailadress() {
	return emailadress;
}
public void setEmailadress(String emailadress) {
	this.emailadress = emailadress;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getCreationdate() {
	return creationdate;
}
public void setCreationdate(String creationdate) {
	this.creationdate = creationdate;
}
public Integer getCreatorid() {
	return creatorid;
}
public void setCreatorid(Integer creatorid) {
	this.creatorid = creatorid;
}
public Date getEditdatetime() {
	return editdatetime;
}
public void setEditdatetime(Date editdatetime) {
	this.editdatetime = editdatetime;
}
public Integer getEditorid() {
	return editorid;
}
public void setEditorid(Integer editorid) {
	this.editorid = editorid;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
public Date getLastlogintime() {
	return lastlogintime;
}
public void setLastlogintime(Date lastlogintime) {
	this.lastlogintime = lastlogintime;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getUsercode() {
	return usercode;
}
public void setUsercode(String usercode) {
	this.usercode = usercode;
}
public Integer getFactoryid() {
	return factoryid;
}
public void setFactoryid(Integer factoryid) {
	this.factoryid = factoryid;
}
@Column(name="FactoryId")
private Integer factoryid;
}
