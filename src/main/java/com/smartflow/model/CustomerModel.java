package com.smartflow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "customerModel")
@Table(name="core.Customer")
public class CustomerModel {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int CustomerId;
	@Column(name="CustomerCode")
	private  String CustomerCode;
	@Column(name="Name")
	private String CustomerName;
	@Column(name="DUNS")
	private String DUNS;
	@Column(name="Country")
	private String CustomerCountry;
	@Column(name="Province")
	private String CustomerProvince;
	@Column(name="Address")
	private String CustomerAddress;
	@Column(name="PostCode")
	private String CustomerPostCode;
	@Column(name="Fax")
	private String CustomerFax;
	@Column(name="ContactPerson")
	private String CustomerContactPerson;
	@Column(name="Email")
	private String CustomerEmail;
	@Column(name="CreatorId")
    private Integer CustomerCreatorId;
	@Column(name="CreationDateTime")
	private Date CustomerCreationDateTime;
	@Column(name="EditorId")
	private Integer CustomerEditorId;
	@Column(name="EditDateTime")
	private Date CustomerEditeDateTime;
	@Column(name="State")
	private Integer CustomerState;
	@Column(name="Telphone")
	private String CustomerTelphone;
    @Column(name="MobilePhone")
    private String CustomerMobilePhone;
    @XmlElement
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	@XmlElement
	public String getCustomerCode() {
		return CustomerCode;
	}
	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	@XmlElement
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	@XmlElement
	public String getDUNS() {
		return DUNS;
	}
	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}
	@XmlElement
	public String getCustomerCountry() {
		return CustomerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		CustomerCountry = customerCountry;
	}
	@XmlElement
	public String getCustomerProvince() {
		return CustomerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		CustomerProvince = customerProvince;
	}
	@XmlElement
	public String getCustomerAdress() {
		return CustomerAddress;
	}
	public void setCustomerAdress(String customerAddress) {
		CustomerAddress = customerAddress;
	}
	@XmlElement
	public String getCustomerPostCode() {
		return CustomerPostCode;
	}
	public void setCustomerPostCode(String customerPostCode) {
		CustomerPostCode = customerPostCode;
	}
	@XmlElement
	public String getCustomerFax() {
		return CustomerFax;
	}
	public void setCustomerFax(String customerFax) {
		CustomerFax = customerFax;
	}
	@XmlElement
	public String getCustomerContactPerson() {
		return CustomerContactPerson;
	}
	public void setCustomerContactPerson(String customerContactPerson) {
		CustomerContactPerson = customerContactPerson;
	}
	@XmlElement
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	@XmlElement
	public Integer getCustomerCreatorId() {
		return CustomerCreatorId;
	}
	public void setCustomerCreatorId(Integer customerCreatorId) {
		CustomerCreatorId = customerCreatorId;
	}
	@XmlElement
	public Date getCustomerCreationDateTime() {
		return CustomerCreationDateTime;
	}
	public void setCustomerCreationDateTime(Date customerCreationDateTime) {
		CustomerCreationDateTime = customerCreationDateTime;
	}
	@XmlElement
	public Integer getCustomerEditorId() {
		return CustomerEditorId;
	}
	public void setCustomerEditorId(Integer customerEditorId) {
		CustomerEditorId = customerEditorId;
	}
	@XmlElement
	public Date getCustomerEditeDateTime() {
		return CustomerEditeDateTime;
	}
	public void setCustomerEditeDateTime(Date customerEditeDateTime) {
		CustomerEditeDateTime = customerEditeDateTime;
	}
	@XmlElement
	public Integer getCustomerState() {
		return CustomerState;
	}
	public void setCustomerState(Integer customerState) {
		CustomerState = customerState;
	}
	@XmlElement
	public String getCustomerTelphone() {
		return CustomerTelphone;
	}
	public void setCustomerTelphone(String customerTelphone) {
		CustomerTelphone = customerTelphone;
	}
	@XmlElement
	public String getCustomerMobilePhone() {
		return CustomerMobilePhone;
	}
	public void setCustomerMobilePhone(String customerMobilePhone) {
		CustomerMobilePhone = customerMobilePhone;
	}
	
}
