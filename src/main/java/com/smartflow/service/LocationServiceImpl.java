package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.AreaDao;
import com.smartflow.dao.LocationDao;
import com.smartflow.model.LocationModel;
import com.smartflow.util.LocationDataForPage;
@Service
public class LocationServiceImpl implements LocationService{
@Autowired
LocationDao locationDaotion;
@Autowired
AreaDao areadao;
	@Override
	public int getCount(String locationNumber,String description,Integer areaId) {
		// TODO Auto-generated method stub
		return locationDaotion.getCount(locationNumber, description, areaId);
	}

	@Override
	public List<LocationDataForPage> getPageData(int pageIndex, int pageSize,String locationNumber,String description,Integer areaId) {
		// TODO Auto-generated method stub
		return locationDaotion.getPageData(pageIndex, pageSize, locationNumber, description, areaId);
	}

	@Override
	public LocationDataForPage getDataById(int id) {
		// TODO Auto-generated method stub
		return locationDaotion.getLocationData(id);
	}

	@Override
	public void delData(int i) {
		// TODO Auto-generated method stub
    locationDaotion.delDataById(i);
		
	}

	@Override
	public LocationModel getLocationDataById(int id) {
		// TODO Auto-generated method stub
		return locationDaotion.getDataById(id);
	}

	@Override
	public void saveDataForArea(LocationModel locationModel) {
		// TODO Auto-generated method stub
	 locationDaotion.postData(locationModel);
	}

	@Override
	public void upDataForArea(LocationModel locationModel) {
		// TODO Auto-generated method stub
		locationDaotion.upDate(locationModel);
	}

	@Override
	public List<Map<String, Object>> getAreaList() {
		// TODO Auto-generated method stub
		return areadao.getAreaList();
	}

	@Override
	public int getDataForCheckUnique(String LocationNumber, String Description) {
		// TODO Auto-generated method stub
		return locationDaotion.getDataForCheckUnique(LocationNumber, Description);
	}

	@Override
	public boolean isRegisterLocationNumber(String LocationNumber) {
		return locationDaotion.isRegisterLocationNumber(LocationNumber);
	}

}
