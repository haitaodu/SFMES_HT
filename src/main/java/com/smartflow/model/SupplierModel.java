package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.Supplier")
public class SupplierModel {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="SupplierCode")
	private  String Code;
	@Column(name="Name")
	private String Name;
	@Column(name="DUNS")
	private String DUNS;
	@Column(name="Country")
	private String Country;
	@Column(name="Province")
	private String Province;
	@Column(name="Address")
	private String Address;
	@Column(name="PostCode")
	private String PostCode;
	@Column(name="Fax")
	private String Fax;
	@Column(name="ContactPerson")
	private String ContactPerson;
	@Column(name="Email")
	private String Email;
	@Column(name="CreatorId")
    private Integer CreatorId;
	@Column(name="CreationDateTime")
	private Date CreationDateTime;
	@Column(name="EditorId")
	private Integer EditorId;
	@Column(name="EditDateTime")
	private Date EditeDateTime;
	@Column(name="State")
	private Integer State;
	@Column(name="Telphone")
	private String Telphone;
    @Column(name="MobilePhone")
    private String MobilePhone;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDUNS() {
		return DUNS;
	}
	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getAdress() {
		return Address;
	}
	public void setAdress(String adress) {
		Address = adress;
	}
	public String getPostCode() {
		return PostCode;
	}
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	public Date getEditeDateTime() {
		return EditeDateTime;
	}
	public void setEditeDateTime(Date editeDateTime) {
		EditeDateTime = editeDateTime;
	}
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	public String getTelphone() {
		return Telphone;
	}
	public void setTelphone(String telphone) {
		Telphone = telphone;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	
	public SupplierModel() {
	
	}
	public SupplierModel(int id, String code, String name, String dUNS, String country, String province, String address,
			String postCode, String fax, String contactPerson, String email, Integer editorId, Date editeDateTime,
			int state, String telphone, String mobilePhone) {
		Id = id;
		Code = code;
		Name = name;
		DUNS = dUNS;
		Country = country;
		Province = province;
		Address = address;
		PostCode = postCode;
		Fax = fax;
		ContactPerson = contactPerson;
		Email = email;
		EditorId = editorId;
		EditeDateTime = editeDateTime;
		State = state;
		Telphone = telphone;
		MobilePhone = mobilePhone;
	}

	
	
}
