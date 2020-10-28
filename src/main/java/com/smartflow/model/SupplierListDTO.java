package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierListDTO implements Serializable {
	
	private Integer Id;
	private String SupplierCode;
	private String Name;
	private String DUNS;
	private String Country;
	private String Province;
	private String Address;
	private String PostCode;
	private String Fax;
	private String ContactPerson;
	private String Email;
	private String Telphone;
	private String MobilePhone;
	private String Editor;
	private Date EditDateTime;
	private String State;
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("SupplierCode")
	public String getSupplierCode() {
		return SupplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("DUNS")
	public String getDUNS() {
		return DUNS;
	}
	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}
	@JsonProperty("Country")
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	@JsonProperty("Province")
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	@JsonProperty("Address")
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@JsonProperty("PostCode")
	public String getPostCode() {
		return PostCode;
	}
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}
	@JsonProperty("Fax")
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	@JsonProperty("ContactPerson")
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	@JsonProperty("Email")
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@JsonProperty("Telphone")
	public String getTelphone() {
		return Telphone;
	}
	public void setTelphone(String telphone) {
		Telphone = telphone;
	}
	@JsonProperty("MobilePhone")
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
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
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public SupplierListDTO() {

	}
	public SupplierListDTO(Integer id, String supplierCode, String name, String dUNS, String country, String province,
			String address, String postCode, String fax, String contactPerson, String email, String telphone,
			String mobilePhone, String editor, Date editDateTime, String state) {

		Id = id;
		SupplierCode = supplierCode;
		Name = name;
		DUNS = dUNS;
		Country = country;
		Province = province;
		Address = address;
		PostCode = postCode;
		Fax = fax;
		ContactPerson = contactPerson;
		Email = email;
		Telphone = telphone;
		MobilePhone = mobilePhone;
		Editor = editor;
		EditDateTime = editDateTime;
		State = state;
	}

}
