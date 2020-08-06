package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.Cell;
import com.smartflow.model.Cell_Station;

public interface CellDao {

	/**
	 * 根据Id查询线体Cell
	 * @param cellId
	 * @return
	 */
	public Cell getCellById(Integer cellId);
	
	/**
	 * 根据cellId查询工作站Id和工作站编号
	 * @param cellId
	 * @return
	 */
	public List<Map<String, Object>> getStationIdAndStationNumberByCellId(Integer cellId);
	
	/**
	 * 多条件分页查询cell
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Cell> getCellByCondition(Integer pageIndex,Integer pageSize,String cellNumber,String description);
	
	/**
	 * 查询Cell总条数
	 * @return
	 */
	public Integer getTotalCountFromCell(String cellNumber,String description);
	
	/**
	 * 查询cellNumber存在的次数,判断cellNumber是否重复添加
	 * @param cellNumber
	 * @return
	 */
	public Integer getCountByCellNumber(String cellNumber);
	
	/**
	 * 添加线体
	 * @param cell
	 */
	public void addCell(Cell cell);
	
	/**
	 * 添加Cell_Station表
	 * @param cell_Station
	 */
	public void addCell_Station(Cell_Station cell_Station);
	
	/**
	 * 修改线体
	 * @param cell
	 */
	public void updateCell(Cell cell);
	
	/**
	 * 修改Cell_Station表
	 * @param cell_Station
	 */
	public void updateCell_Station(Cell_Station cell_Station);
	
	/**
	 * 根据线体id删除线体
	 * @param cellId
	 */
//	public void deleteCellById(Cell cell);
	
	/**
	 * 根据cellId查询Cell_Station
	 * @param cellId
	 * @return
	 */
	public List<Cell_Station> getCell_StationByCellId(Integer cellId);
	
	/**
	 * 根据stationId和cellId查询Cell_Station
	 * @param stationId
	 * @param cellId
	 * @return
	 */
	public List<Cell_Station> getCell_StationByStationIdAndCellId(Integer stationId,Integer cellId);
	
	/**
	 * 删除Cell_Station
	 * @param cell_Station
	 */
	public void deleteCell_Station(Cell_Station cell_Station);
	
	/**
	 * 根据线体id查询工站名称
	 * @param cellId
	 * @return
	 */
	public List<String> getStationNameByCellId(Integer cellId);

	/**
	 * 查询线体下拉框
	 * @return
	 */
	public List<Map<String,Object>> getCellListInit();
}
