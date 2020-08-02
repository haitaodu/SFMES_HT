package com.smartflow.dao;

import java.util.List;
import java.util.Map;

import com.smartflow.dto.process.ProcessStepDataEdite;
import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ProcessStepDataForPage;
import com.smartflow.view.Process.ProcessDetailView;
import com.smartflow.view.Process.ProcessItemDetailView;
import com.smartflow.view.Process.ProcsessEditeView;

/**
 * @author haita
 */
public interface ProcessDao {
	/**
	 * 根据物料号，工厂名查找总的条目数
	 * @param materialNumber 物料号
	 * @param factoryName 工厂名
	 * @return 返回总的条目数
	 */
	public int getCount(String materialNumber,String factoryName);

	/**
	 * 返回分页数据
	 * @param pageindex 分页页码
	 * @param pagesize 分页大小
	 * @param materialNumber 物料号
	 * @param factoryName 工厂名
	 * @return 返回分页列表
	 */
	public List<ProcessDataForPage> getPageData(int pageindex,
												int pagesize,
												String materialNumber,String factoryName);

	/**
	 * 根据id查找工艺的详情数据
	 * @param id 工艺id
	 * @return  返回工艺详情
	 */
	public ProcessDetailView getDataInId(int id);

	/**
	 * 根据id查找工艺步骤详情
	 * @param id 工艺id
	 * @return 返回工艺步骤列表
	 */
	public  List<ProcessItemDetailView> getDataById(int id);

	/**
	 * 根据前端的id作假删除处理
	 * @param i 工艺的id
	 */
	public void delData(int i);

	/**
	 * 根据id查找工艺实体
	 * @param i id
	 * @return 返回工艺实体
	 */
	public ProcessModel getProcessDataById(int i);

	/**
	 * 根据工艺以及工艺步骤添加数据
	 * @param process 工艺
	 * @param processSteps 工艺步骤
	 */
	public void addData(ProcessModel process,List<ProcessStep> processSteps);

	/**
	 * 工艺实体
	 * @param process 工艺实体
	 * @param processSteps 工艺步骤
	 */
	public void updateData(ProcessModel process,List<ProcessStep> processSteps);

	/**
	 * 工艺列表
	 * @return 返回工艺列表
	 */
	public List<Map<String, Object>> getProcessList();

	/**
	 * 返回工站组列表
	 * @return 工站组
	 */
	public List<StationGroup> getStationGroup();

	/**
	 * 根据工艺Id查找总的工艺步骤条目
	 * @param id 工艺id
	 * @return 饭hi工艺总的条目数
	 */
	public int getCountProcessStepByProcessId(int id);

	/**
	 * 根据ProcessId查找相应的ProcessStep是否在生产中
	 * @param processId 工艺id
	 * @return 返回是否是在使用中
	 */
	public boolean isUsingInProduct(int processId);

	/**
	 * 根据ProcessNumber查找相应的Process是否是已经注册过的
	 * @param processNumber 工艺编号
	 * @return 返回该工艺编号是否被注册过
	 */
	public boolean isRegister(String processNumber);

	/**
	 * 根据岛区查找工站组列表
	 * @return 攻占组列表
	 */
	 public List<StationGroup> getStationGroupByCellId(int id);
	/**
	 *
	 * @param id 根据id返回
	 * @return 返回工艺编辑
	 */
	public ProcsessEditeView getProcessEditeView(int id);
}
