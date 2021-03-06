package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.Station;
import com.smartflow.model.Station_StationGroup;
import com.smartflow.view.StationList;

/**
 * @author haita
 */
public interface StationDao {

	/**
	 * 查询工站总条数
	 * @return
	 */
	public Integer getTotalCount(String stationNumber, String stationName, String ipAddress, Integer stationType);
	
	/**
	 * 获取工站列表(分页查询)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Station> getStationList(Integer pageIndex, Integer pageSize, String stationNumber, String stationName, String ipAddress, Integer stationType);
	
	/**
	 * ͨ通过用户id查询用户名
	 * @param userId
	 * @return
	 */
	public String getUserNameById(Integer userId);
	
	/**
	 * 根据ID查询工站详细
	 * @param stationId
	 * @return
	 */
	public Station getStationById(Integer stationId);
	
	/**
	 * 查询工站类型
	 */
	public List<Map<String, Object>> getStationGroup();

	/**
	 * 根据工站编号查询该工站在数据库有无记录
	 * @param stationNumber
	 * @return
	 */
	public Integer getCountByStationNumber(String stationNumber);
	
	/**
	 * 添加工站
	 * @param station
	 */
	public void addStation(Station station);
	
	/**
	 * 查询工厂名称
	 * @return
	 */
	public List<Map<String, Object>> getFactory();
	
	/**
	 * 根据工站id查询工站组
	 * @param stationId
	 * @return
	 */
	public List<Map<String, Object>> getStationGroupByStationId(Integer stationId);
	
	/**
	 * 根据stationId查询Station_StationGroup
	 * @param stationId
	 * @return
	 */
	public List<Station_StationGroup> getStation_StationGroupByStationId(Integer stationId);
	
	/**
	 * 修改工站
	 * @param station
	 */
	public void updateStation(Station station);
	
	/**
	 * 修改工站_工站组表
	 * @param station_StationGroup
	 */
	public void updateStation_StationGroup(Station_StationGroup station_StationGroup);
	

	
	/**
	 * 根据stationId删除Station_StationGroup
	 * @param stationId
	 */
	public void deleteStation_StationGroup(Station_StationGroup station_StationGroup);
	

	
	/**
	 * 根据stationId查询StationGroupName
	 * @param stationId
	 * @return
	 */
	public List<String> getStationGroupNameByStationId(Integer stationId);


	public List<Map<String,Object>> getWashList();

	/**
	 * 查询工站类型
	 * @return
	 */
	public List<Map<String,Object>> getStationTypeList();

	/**
	 * 根据工站类型id查询工站类型
	 * @param stationTypeId
	 * @return
	 */
	public String getStationTypeNameByStationTypeId(Integer stationTypeId);


	/**
	 *
	 * @param workOrderId 工单Id
	 * @param cellId 岛区Id
	 * @return 返回工站信息列表
	 */
	public List<StationList> getStationList(long workOrderId,int cellId);



	public int getCellByWorkOrderId(long workOrderId,String stationNumber);
}
