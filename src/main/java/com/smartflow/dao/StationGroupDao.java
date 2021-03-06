package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.StationGroup;
import com.smartflow.model.Station_StationGroup;

/**
 * @author haita
 */
public interface StationGroupDao {
	
	/**
	 * 查询工站组总条数
	 * @return
	 */
	public Integer getTotalCount(String groupNumber, String description);

	/**
	 * 获取工站组列表
	 * @return
	 */
	public List<StationGroup> getStationGroupList(Integer pageIndex, Integer pageSize, String groupNumber, String description);
	
	/**
	 * 根据工厂id查询工厂名称
	 * @param factoryId
	 * @return
	 */
	public String getFactoryNameById(Integer factoryId);
	
	/**
	 * 根据id查询工站组详情
	 * @param stationGroupId
	 * @return
	 */
	public StationGroup getStationGroupById(Integer stationGroupId);
	
	/**
	 * 查询工厂
	 * @return
	 */
	public List<Map<String, Object>> getFactory();
	

	//public List getStationByStationGroupId(Integer stationGroupId);
	/**
	 * 通过stationGroupId查询station
	 * @param stationGroupId
	 * @return
	 */
	public List<Map<String, Object>> getStationByStationGroupById(Integer stationGroupId);
	
	/**
	 * 查询所有的工站
	 * @return
	 */
	public List<Map<String, Object>> getStation();

	/**
	 * 获取物料使用工站下拉框（只显示工站类型是生产工站和打标工站的工站）
	 * @return
	 */
	public List<Map<String,Object>> getUseStation();
	
	/**
	 * 查询GroupNumber出现的次数，判读GroupNumber是否重复添加
	 * @param groupNumber
	 * @return
	 */
	public Integer getCountByGroupNumber(String groupNumber);
	
	/**
	 * 添加工站组
	 * @param stationGroup
	 */
	public void addStationGroup(StationGroup stationGroup);
	
	/**
	 * 添加工站_工站组
	 * @param station_StationGroup
	 */
	public void addStation_StationGroup(Station_StationGroup station_StationGroup);
	
	/**
	 * 修改工站
	 * @param stationGroup
	 */
	public void updateStationGroup(StationGroup stationGroup);
	
	/**
	 * 修改工站_工站组
	 * @param station_StationGroup
	 */
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup);
	
	/**
	 * 根据StationGroupId查询Station_StationGroup
	 * @param stationGroupId
	 */
	public List<Station_StationGroup> getStation_StationGroupByStationGroupId(Integer stationGroupId);
	
	/**
	 * 删除StationGroup
	 * @param id
	 */
	public void deleteStationGroup(StationGroup stationGroup);
	
	/**
	 * 根据StationGroupId删除Station_StationGroup
	 * @param stationGroupId
	 */
//	public void deleteStation_StationGroupByStationGroupId(Integer stationGroupId);
	
	/**
	 * 根据stationGroupId查询工站名称
	 * @param stationGroupId
	 * @return
	 */
	public List<String> getStationNameByStationGroupId(Integer stationGroupId);


	/**
	 * 查询物料上料点所有的工站列表
	 * @return  上料点料表
	 */
	public List<Map<String, Object>> getTraceStation();
}
