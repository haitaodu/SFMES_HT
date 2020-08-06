package com.smartflow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.smartflow.model.Cell;
import com.smartflow.model.Cell_Station;
@Repository
public class CellDaoImpl implements CellDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Cell getCellById(Integer cellId) {
		//String hql = "select new Cell(Id, CellNumber, Description, State) from Cell where Id = "+cellId;			
		return hibernateTemplate.get(Cell.class, cellId);
	}

	@Override
	public List<Map<String, Object>> getStationIdAndStationNumberByCellId(Integer cellId) {
		String sql = "select Id [key],concat(StationNumber,'('+Name+')') label from core.Station where State = 1 and Id in (select StationId from core.Cell_Station where CellId = :CellId)";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{		
			Query query = session.createSQLQuery(sql);
			//String hql = "select Id [key],StationNumber label from Station where Id in(select StationId from CellStation where CellId = :CellId)";
			//Query query = session.createQuery(sql);
			query.setInteger("CellId", cellId);	
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}

	}

	@Override
	public List<Cell> getCellByCondition(Integer pageIndex, Integer pageSize,String cellNumber,String description) {
		String hql = "from Cell where State = 1 or State = 0";
		if (cellNumber!=null) {
			hql += "and cellNumber like '%"+cellNumber+"%' ";
		}
		if (description!=null) {
			hql += "and description like '%"+description+"%'";
		}
		Session session = hibernateTemplate.getSessionFactory().openSession();
	    try{
			Query query = session.createQuery(hql);
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);		
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}	
	}

	@Override
	public Integer getTotalCountFromCell(String cellNumber,String description) {
		String hql = "select count(*) from Cell where State = 1 or State = 0";
		if (cellNumber!=null) {
			hql += "and cellNumber like '%"+cellNumber+"%' ";
		}
		if (description!=null) {
			hql += "and description like '%"+description+"%'";
		}
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public Integer getCountByCellNumber(String cellNumber) {
		String hql = "select count(*) from Cell where CellNumber = :CellNumber";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(hql);
			query.setString("CellNumber", cellNumber);
			return Integer.parseInt(query.uniqueResult().toString());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public void addCell(Cell cell) {
		hibernateTemplate.save(cell);
	}

	@Override
	public void addCell_Station(Cell_Station cell_Station) {
		hibernateTemplate.save(cell_Station);
	}

	@Override
	public void updateCell(Cell cell) {
		hibernateTemplate.update(cell);
	}

	@Override
	public void updateCell_Station(Cell_Station cell_Station) {
		hibernateTemplate.update(cell_Station);
	}

	//	@Override
	//	public void deleteCellById(Cell cell) {
	//		hibernateTemplate.delete(cell);
	//	}

	@Override
	public void deleteCell_Station(Cell_Station cell_Station) {
		hibernateTemplate.delete(cell_Station);
	}

	@Override
	public List<Cell_Station> getCell_StationByCellId(Integer cellId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from Cell_Station where CellId = "+cellId;
		try{
			Query query = session.createQuery(hql);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}		
	}

	@Override
	public List<Cell_Station> getCell_StationByStationIdAndCellId(Integer stationId, Integer cellId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String hql = "from Cell_Station where CellId = :CellId and StationId = :StationId";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("CellId", cellId);
			query.setInteger("StationId", stationId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<String> getStationNameByCellId(Integer cellId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		//		String hql = "select concat(StationNumber,' '+Name) from core.Station where Id in (select StationId from core.Cell_Station where CellId = :CellId)";
		String hql = "select concat(StationNumber,'('+Name+')') from Station where state = 1 and id in (select stationId from Cell_Station where cellId = :CellId)";
		try{
			Query query = session.createQuery(hql);
			query.setInteger("CellId", cellId);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Map<String, Object>> getCellListInit() {
		String sql = "select Id [key],concat(CellNumber,'('+Description+')') label from core.Cell where State = 1";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}finally{
			session.close();
		}
	}
}
