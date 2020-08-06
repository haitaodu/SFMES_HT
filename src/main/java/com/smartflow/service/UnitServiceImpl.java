package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.UnitDao;
@Service
public class UnitServiceImpl implements UnitService{
@Autowired
UnitDao unitDao;
	@Override
	public List<Map<String, Object>> getUnit() {
		// TODO Auto-generated method stub
		return unitDao.getUnit();
	}

}
