package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.model.Unit;

public interface UnitDao {
/*get到Unit表的数据
 * 返回数据的形式key,lable
 */

	public List<Map<String, Object>> getUnit();
	/*
	 * 根据UnitId获取相应的Unit数据
	 */
  public Unit getUnitById(int id);
}
