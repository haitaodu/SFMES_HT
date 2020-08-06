package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.AreaDao;
import com.smartflow.model.AreaModel;
import com.smartflow.util.AreaDataForPage;
@Service
public class AreaServiceImpl implements AreaService{
@Autowired
AreaDao area;
	@Override
	public int count(String areaNumber,String areaName,Integer factoryId) {
		// TODO Auto-generated method stub
		return area.countData(areaNumber, areaName, factoryId);
	}

	@Override
	public List<AreaDataForPage> getPageData(int pagesize, int pageindex,String areaNumber,String areaName,Integer factoryId) {
		// TODO Auto-generated method stub
		return area.readPageData(pagesize, pageindex, areaNumber, areaName, factoryId);
	}

	@Override
	public AreaDataForPage getDataById(int id) {
		// TODO Auto-generated method stub
		return area.getDataInId(id);
	}

	@Override
	public void delDataByChangeStatus(int id) {
		// TODO Auto-generated method stub
	area.delByChangeData(id);	
	}

	@Override
	public AreaModel getAreaDataById(int id) {
		// TODO Auto-generated method stub
		return area.getDataById(id);
	}

	@Override
	public void saveDataForArea(AreaModel areaModel) {
		// TODO Auto-generated method stub
		area.postData(areaModel);
	}

	@Override
	public void upDataForArea(AreaModel areaModel) {
		// TODO Auto-generated method stub
		area.upDate(areaModel);
	}

	@Override
	public int getDataForCheckUnique(String AreaNumber, String Descriprion) {
		// TODO Auto-generated method stub
		return area.getDataForCheckUnique(AreaNumber, Descriprion);
	}

	@Override
	public List<Map<String, Object>> getAreaIdAndName() {
		return area.getAreaIdAndName();
	}

	@Override
	public boolean isRegisterAreaNumber(String areaNumber) {
		return area.isRegisterAreaNumber(areaNumber);
	}

}
