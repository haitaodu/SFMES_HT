package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartflow.dao.StationGroupDao;
import com.smartflow.model.StationGroup;
import com.smartflow.model.Station_StationGroup;

/**
 * @author haita
 */
@Service
public class StationGroupServiceImpl implements StationGroupService {

	@Autowired
	StationGroupDao stationGroupDao;
	
	@Override
	public Integer getTotalCount(String groupNumber,String description) {
		return stationGroupDao.getTotalCount(groupNumber, description);
	}
	@Override
	public List<StationGroup> getStationGroupList(Integer pageIndex,Integer pageSize,String groupNumber,String description) {
		return stationGroupDao.getStationGroupList(pageIndex, pageSize, groupNumber, description);
	}
	@Override
	public String getFactoryNameById(Integer factoryId) {
		return stationGroupDao.getFactoryNameById(factoryId);
	}
	@Override
	public StationGroup getStationGroupById(Integer stationGroupId) {
		return stationGroupDao.getStationGroupById(stationGroupId);
	}
	@Override
	public List<Map<String, Object>> getFactory() {
		return stationGroupDao.getFactory();
	}
	@Override
	public List<Map<String, Object>> getStationByStationGroupById(Integer stationGroupId) {
		return stationGroupDao.getStationByStationGroupById(stationGroupId);
	}
	@Override
	public List<Map<String, Object>> getStation() {
		return stationGroupDao.getStation();
	}
	@Override
	public Integer getCountByGroupNumber(String groupNumber) {
		return stationGroupDao.getCountByGroupNumber(groupNumber);
	}
	@Transactional
	@Override
	public void addStationGroup(StationGroup stationGroup) {
		stationGroupDao.addStationGroup(stationGroup);
	}
	@Transactional
	@Override
	public void addStation_StationGroup(Station_StationGroup station_StationGroup) {
		stationGroupDao.addStation_StationGroup(station_StationGroup);
	}
	@Transactional
	@Override
	public void updateStationGroup(StationGroup stationGroup) {
		stationGroupDao.updateStationGroup(stationGroup);
	}
	@Transactional
	@Override
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup) {
		stationGroupDao.updateStation_StationGroup(station_StationGroup);
	}
	@Override
	public List<Station_StationGroup> getStation_StationGroupByStationGroupId(Integer stationGroupId) {
		return stationGroupDao.getStation_StationGroupByStationGroupId(stationGroupId);
	}
	@Transactional
	@Override
	public void deleteStationGroup(StationGroup stationGroup) {
		stationGroupDao.deleteStationGroup(stationGroup);
	}
//	@Transactional
//	@Override
//	public void deleteStation_StationGroupByStationGroupId(Integer stationGroupId) {
//		stationGroupDao.deleteStation_StationGroupByStationGroupId(stationGroupId);
//	}
	@Override
	public List<String> getStationNameByStationGroupId(Integer stationGroupId) {
		return stationGroupDao.getStationNameByStationGroupId(stationGroupId);
	}

	@Override
	public List<Map<String, Object>> getTraceStation() {
		return stationGroupDao.getTraceStation();
	}


}
