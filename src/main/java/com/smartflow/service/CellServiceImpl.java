package com.smartflow.service;

import com.smartflow.dao.CellDao;
import com.smartflow.model.Cell;
import com.smartflow.model.Cell_Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CellServiceImpl implements CellService {

	private final
	CellDao cellDao;

	@Autowired
	public CellServiceImpl(CellDao cellDao) {
		this.cellDao = cellDao;
	}

	@Override
	public Cell getCellById(Integer cellId) {
		return cellDao.getCellById(cellId);
	}

	@Override
	public List<Map<String, Object>> getStationIdAndStationNumberByCellId(Integer cellId) {
		return cellDao.getStationIdAndStationNumberByCellId(cellId);
	}

	@Override
	public List<Cell> getCellByCondition(Integer pageIndex, Integer pageSize,String cellNumber,String description) {
		return cellDao.getCellByCondition(pageIndex, pageSize, cellNumber, description);
	}

	@Override
	public Integer getTotalCountFromCell(String cellNumber,String description) {
		return cellDao.getTotalCountFromCell(cellNumber, description);
	}
	@Override
	public Integer getCountByCellNumber(String cellNumber) {
		return cellDao.getCountByCellNumber(cellNumber);
	}
	@Transactional
	@Override
	public void addCell(Cell cell) {
		cellDao.addCell(cell);
	}

	@Transactional
	@Override
	public void addCell_Station(Cell_Station cell_Station) {
		cellDao.addCell_Station(cell_Station);
	}

	@Transactional
	@Override
	public void updateCell(Cell cell) {
		cellDao.updateCell(cell);
	}

	@Transactional
	@Override
	public void updateCell_Station(Cell_Station cell_Station) {
		cellDao.updateCell_Station(cell_Station);
	}

//	@Transactional
//	@Override
//	public void deleteCellById(Cell cell) {
//		cellDao.deleteCellById(cell);
//	}

	@Override
	public List<Cell_Station> getCell_StationByCellId(Integer cellId) {
		return cellDao.getCell_StationByCellId(cellId);
	}
	
	@Override
	public List<Cell_Station> getCell_StationByStationIdAndCellId(Integer stationId, Integer cellId) {
		return cellDao.getCell_StationByStationIdAndCellId(stationId, cellId);
	}
	
	@Transactional
	@Override
	public void deleteCell_Station(Cell_Station cell_Station) {
		cellDao.deleteCell_Station(cell_Station);
	}

	@Override
	public List<String> getStationNameByCellId(Integer cellId) {
		return cellDao.getStationNameByCellId(cellId);
	}

	@Override
	public List<Map<String, Object>> getCellListInit() {
		return cellDao.getCellListInit();
	}
}
