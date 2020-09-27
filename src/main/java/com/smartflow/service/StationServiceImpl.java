package com.smartflow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smartflow.dao.StationDao;
import com.smartflow.model.Station;
import com.smartflow.model.Station_StationGroup;

@Service
public class StationServiceImpl implements StationService {

	private final
	StationDao stationDao;

	private final
	HibernateTemplate hibernateTemplate;
	@Autowired
	public StationServiceImpl(StationDao stationDao, HibernateTemplate hibernateTemplate) {
		this.stationDao = stationDao;
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Integer getTotalCount(String stationNumber,String stationName, String ipAddress, Integer stationType) {
		return stationDao.getTotalCount(stationNumber, stationName, ipAddress, stationType);
	}
	
	@Override
	public List<Station> getStationList(Integer pageIndex,Integer pageSize,String stationNumber,String stationName, String ipAddress, Integer stationType) {
		return stationDao.getStationList(pageIndex, pageSize, stationNumber, stationName, ipAddress, stationType);
	}

	@Override
	public String getUserNameById(Integer userId) {
		return stationDao.getUserNameById(userId);
	}

	@Override
	public Station getStationById(Integer stationId) {
		return stationDao.getStationById(stationId);
	}
	
	@Override
	public List<Map<String, Object>> getStationGroup() {
		return stationDao.getStationGroup();
	}

	@Override
	public Integer getCountByStationNumber(String stationNumber) {
		return stationDao.getCountByStationNumber(stationNumber);
	}
	
	@Transactional
	@Override
	public void addStation(Station station) {
		stationDao.addStation(station);
	}

	@Override
	public List<Map<String, Object>> getFactory() {
		return stationDao.getFactory();
	}

	@Override
	public List<Map<String, Object>> getStationGroupByStationId(Integer stationId) {
		return stationDao.getStationGroupByStationId(stationId);
	}

	@Override
	public List<Station_StationGroup> getStation_StationGroupByStationId(Integer stationId) {
		return stationDao.getStation_StationGroupByStationId(stationId);
	}	
	
	@Transactional
	@Override
	public void updateStation(Station station) {
		stationDao.updateStation(station);		
	}
	
	@Transactional
	@Override
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup) {
		stationDao.updateStation_StationGroup(station_StationGroup);
	}
	

	@Transactional
	@Override
	public void deleteStation_StationGroup(Station_StationGroup station_StationGroup) {
		stationDao.deleteStation_StationGroup(station_StationGroup);
	}

	@Override
	public List<String> getStationGroupNameByStationId(Integer stationId) {
		return stationDao.getStationGroupNameByStationId(stationId);
	}

	@Override
	public List<Map<String, Object>> getWashList() {
		return stationDao.getWashList();
	}


	@Override
	public List<Map<String, Object>> getStationTypeList() {
		return stationDao.getStationTypeList();
	}

	@Override
	public String getStationTypeNameByStationTypeId(Integer stationTypeId) {
		return stationDao.getStationTypeNameByStationTypeId(stationTypeId);
	}
}
