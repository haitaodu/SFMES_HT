package com.smartflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.CustomerInterfaceDao;
import com.smartflow.model.CustomerInEditInitializeModel;
import com.smartflow.model.CustomerListDto;
import com.smartflow.model.CustomerModel;
import com.smartflow.util.CustomerReadData;
/**
 * @author haita
 */
@Service
public class CustomerInterfaceServiceImpl implements CustomerInterfaceService {
private final
CustomerInterfaceDao customer;

	@Autowired
	public CustomerInterfaceServiceImpl(CustomerInterfaceDao customer) {
		this.customer = customer;
	}

	@Override
	public CustomerModel getDataById(Integer id) {
		return customer.getDataById(id);
	}

	@Override
	public CustomerReadData getDetailsById(Integer id) {
		return customer.getDetailsById(id);
	}

	@Override
	public void addData(CustomerModel customerModel) {
		customer.addData(customerModel);
	}

	@Override
	public CustomerInEditInitializeModel getEditInitializeData(Integer id) {
		return customer.getEditInitializeData(id);
	}

	@Override
	public void delData(Integer id) {
		customer.delData(id);
	}

	@Override
	public void upDateData(CustomerModel customerModel) {
		customer.upDateData(customerModel);
	}

	@Override
	public List<CustomerListDto> getPageData(Integer pageNumber, Integer pageSize, String customerCode, String customerName) {
		return customer.getPageData(pageNumber, pageSize, customerCode, customerName);
	}

	@Override
	public Integer getRowCount(String customerCode, String customerName) {
		return customer.getRowCount(customerCode,customerName);
	}

	@Override
	public int getDataForCheckUnique(String customerCode, String customerName) {
		return customer.getDataForCheckUnique(customerCode, customerName);
	}

	@Override
	public boolean isRegisterCustomerNumber(String customerNumber) {
		return customer.isRegisterCustomerNumber(customerNumber);
	}

	@Override
	public boolean isregisterdnus(String dnus) {
		return customer.isRegisterDNUS(dnus);
	}

}
