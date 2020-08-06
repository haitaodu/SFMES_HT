package com.smartflow.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.SupplierInterfaceDao;
import com.smartflow.model.SupplierInEditInitializeModel;
import com.smartflow.model.SupplierListDTO;
import com.smartflow.model.SupplierModel;
import com.smartflow.util.SupplierReadData;

@Service
public class SupplierInterfaceServiceImpl implements SupplierInterfaceService{
@Autowired
SupplierInterfaceDao supplierdao;
	@Override
	public SupplierModel getDataById(Integer id) {
		// TODO Auto-generated method stub
		return supplierdao.getDataById(id);
	}

	@Override
	public SupplierReadData getDetailsById(Integer id) {
		// TODO Auto-generated method stub
		return supplierdao.getDetailsById(id);
	}

	@Override
	public void addData(SupplierModel supplierModel) {
		// TODO Auto-generated method stub
		supplierdao.addData(supplierModel);
	}

	@Override
	public SupplierInEditInitializeModel getEditInitializeData(Integer id) {
		// TODO Auto-generated method stub
		return supplierdao.getEditInitializeData(id);
	}

	@Override
	public void delData(Integer id) {
		// TODO Auto-generated method stub
		supplierdao.delData(id);
	}

	@Override
	public void upDateData(SupplierModel supplierModel) {
		// TODO Auto-generated method stub
		supplierdao.upDateData(supplierModel);
	}

	@Override
	public List<SupplierListDTO> getPageData(Integer pageNumber, Integer pageSize,String SupplierCode,String SupplierName) {
		// TODO Auto-generated method stub
		return supplierdao.getPageData(pageNumber,  pageSize,SupplierCode, SupplierName);
	}

	@Override
	public Integer getRowCount(String SupplierCode,String SupplierName) {
		// TODO Auto-generated method stub
		return supplierdao.getRowCount( SupplierCode, SupplierName);
	}

	@Override
	public int getDataForCheckUnique(String SupplierCode, String SupplierName) {
		// TODO Auto-generated method stub
		return supplierdao.getDataForCheckUnique(SupplierCode, SupplierName);
	}

	@Override
	public boolean isRegisterSupplierNumber(String SupplierNumber) {
		return supplierdao.isRegisterSupplierNumber(SupplierNumber);
	}

}
