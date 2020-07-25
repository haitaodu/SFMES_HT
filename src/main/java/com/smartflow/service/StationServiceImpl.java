package com.smartflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smartflow.dao.StationDao;
import com.smartflow.model.Station;
import com.smartflow.model.Station_StationGroup;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	StationDao stationDao;
	
	@Override
	public Integer getTotalCount(String stationNumber,String stationName) {
		return stationDao.getTotalCount(stationNumber, stationName);
	}
	
	@Override
	public List<Station> getStationList(Integer pageIndex,Integer pageSize,String stationNumber,String stationName) {
		return stationDao.getStationList(pageIndex, pageSize, stationNumber, stationName);
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
	
//	@Transactional
//	@Override
//	public void deleteStation(Station station) {
//		this.deleteStation_StationGroupByStationId(station.getId());
//		this.deleteRecipeByStationId(station.getId());
//		this.deleteAttributeDataRecordByAssignedStationNumberId(station.getId());
//		this.deletePartFailureDataRecordByStationId(station.getId());
//		this.deleteCell_StationByStationId(station.getId());
//		stationDao.deleteStation(station);
//	}

	@Transactional
	@Override
	public void deleteStation_StationGroup(Station_StationGroup station_StationGroup) {
		stationDao.deleteStation_StationGroup(station_StationGroup);
	}
//	@Transactional
//	@Override
//	public void deleteRecipeByStationId(Integer stationId) {
//		stationDao.deleteRecipeByStationId(stationId);
//	}
//	@Transactional
//	@Override
//	public void deleteAttributeDataRecordByAssignedStationNumberId(Integer stationId) {
//		stationDao.deleteAttributeDataRecordByAssignedStationNumberId(stationId);
//	}
//	@Transactional
//	@Override
//	public void deletePartFailureDataRecordByStationId(Integer stationId) {
//		stationDao.deletePartFailureDataRecordByStationId(stationId);
//	}
//	@Transactional
//	@Override
//	public void deleteCell_StationByStationId(Integer stationId) {
//		stationDao.deleteCell_StationByStationId(stationId);
//	}

	@Override
	public List<String> getStationGroupNameByStationId(Integer stationId) {
		return stationDao.getStationGroupNameByStationId(stationId);
	}

	
}
