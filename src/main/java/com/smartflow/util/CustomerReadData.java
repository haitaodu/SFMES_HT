package com.smartflow.util;

import java.util.Date;

import javax.persistence.Column;

public class CustomerReadData {
	private int CustomerId;
	private  String CustomerCode;
	private String CustomerName;
	private String DUNS;
	private String CustomerCountry;
	private String CustomerProvince;
	private String CustomerAdress;
	private String CustomerPostCode;
	private String CustomerFax;
	private String CustomerContactPerson;
	private String CustomerEmail;
    private String CustomerCreatorName;
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public String getCustomerCode() {
		return CustomerCode;
	}
	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getDUNS() {
		return DUNS;
	}
	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}
	public String getCustomerCountry() {
		return CustomerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		CustomerCountry = customerCountry;
	}
	public String getCustomerProvince() {
		return CustomerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		CustomerProvince = customerProvince;
	}
	public String getCustomerAdress() {
		return CustomerAdress;
	}
	public void setCustomerAdress(String customerAdress) {
		CustomerAdress = customerAdress;
	}
	public String getCustomerPostCode() {
		return CustomerPostCode;
	}
	public void setCustomerPostCode(String customerPostCode) {
		CustomerPostCode = customerPostCode;
	}
	public String getCustomerFax() {
		return CustomerFax;
	}
	public void setCustomerFax(String customerFax) {
		CustomerFax = customerFax;
	}
	public String getCustomerContactPerson() {
		return CustomerContactPerson;
	}
	public void setCustomerContactPerson(String customerContactPerson) {
		CustomerContactPerson = customerContactPerson;
	}
	public String getCustomerEmail() {
		return CustomerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}
	public String getCustomerCreatorName() {
		return CustomerCreatorName;
	}
	public void setCustomerCreatorName(String customerCreatorName) {
		CustomerCreatorName = customerCreatorName;
	}
	public Date getCustomerCreationDateTime() {
		return CustomerCreationDateTime;
	}
	public void setCustomerCreationDateTime(Date customerCreationDateTime) {
		CustomerCreationDateTime = customerCreationDateTime;
	}
	public String getCustomerEditorName() {
		return CustomerEditorName;
	}
	public void setCustomerEditorName(String customerEditorName) {
		CustomerEditorName = customerEditorName;
	}
	public Date getCustomerEditeDateTime() {
		return CustomerEditeDateTime;
	}
	public void setCustomerEditeDateTime(Date customerEditeDateTime) {
		CustomerEditeDateTime = customerEditeDateTime;
	}
	public Integer getCustomerState() {
		return CustomerState;
	}
	public void setCustomerState(Integer customerState) {
		CustomerState = customerState;
	}
	public String getCustomerTelphone() {
		return CustomerTelphone;
	}
	public void setCustomerTelphone(String customerTelphone) {
		CustomerTelphone = customerTelphone;
	}
	public String getCustomerMobilePhone() {
		return CustomerMobilePhone;
	}
	public void setCustomerMobilePhone(String customerMobilePhone) {
		CustomerMobilePhone = customerMobilePhone;
	}
	private Date CustomerCreationDateTime;
	private String CustomerEditorName;
	private Date CustomerEditeDateTime;
	private Integer CustomerState;
	private String CustomerTelphone;
	private String CustomerMobilePhone;

}
