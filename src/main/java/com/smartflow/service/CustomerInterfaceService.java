package com.smartflow.service;

import java.util.List;

import com.smartflow.model.CustomerInEditInitializeModel;
import com.smartflow.model.CustomerListDto;
import com.smartflow.model.CustomerModel;
import com.smartflow.util.CustomerReadData;

/**
 * @author haita
 */
public interface CustomerInterfaceService {

	/**
	 *
	 * @param id
	 * @return
	 */
		public CustomerModel getDataById(Integer id);

	/**
	 *
	 * @param id
	 * @return
	 */
		public 	CustomerReadData getDetailsById(Integer id);

	/**
	 *
	 * @param customerModel
	 */
	    public void addData(CustomerModel customerModel);

	/**
	 *
	 * @param id
	 * @return
	 */
	public CustomerInEditInitializeModel getEditInitializeData(Integer id);

	/**
	 *
	 * @param id
	 */
	    public void delData(Integer id);

	/**
	 *
	 * @param customerModel
	 */
	public void upDateData(CustomerModel customerModel);

	/***
	 *
	 * @param pageNumber
	 * @param pageSize
	 * @param customerCode
	 * @param customerName
	 * @return
	 */
	    public List<CustomerListDto> getPageData(Integer pageNumber, Integer pageSize, String customerCode, String customerName);

	/**
	 *
	 * @param customerCode
	 * @param customerName
	 * @return
	 */
	public Integer getRowCount(String customerCode, String customerName);

	/**
	 *
	 * @param customerCode
	 * @param customerName
	 * @return
	 */
	    public int getDataForCheckUnique(String customerCode, String customerName);

	/**
	 *
	 * @param customerNumber
	 * @return
	 */
	public boolean isRegisterCustomerNumber(String customerNumber);

	/**
	 *
	 * @param duns
	 * @return
	 */
	    public boolean isregisterdnus(String duns);
}
