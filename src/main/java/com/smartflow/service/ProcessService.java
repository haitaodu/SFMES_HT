package com.smartflow.service;

import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.view.Process.ProcessDetailView;
import com.smartflow.view.Process.ProcessItemDetailView;
import com.smartflow.view.Process.ProcessItemEditeView;
import com.smartflow.view.Process.ProcsessEditeView;

import java.util.List;
import java.util.Map;

/**
 * @author haita
 */
public interface ProcessService {
	/**
	 * 根据物料号和工厂名查找总的条目数
	 * @param materialNumber 物料号
	 * @param factoryName 工厂名
	 * @return 返回总的条目数
	 */
	public int getcount(String materialNumber,String factoryName);
	//分页请求
	public List<ProcessDataForPage> getPage
	(int pagesize,
	 int pageindex,
	 String materialNumber,
	 String factoryName);

	/**
	 * 用于显示前端的详情页面
	 * @param id id
	 * @return 返回详情页面
	 */
	public ProcessDetailView getDataInId(int id);

	/**
	 * 用于显示前端的详情页面的工艺步骤
	 * @param id processId
	 * @return 返回工艺步骤列表
	 */
	public  List<ProcessItemDetailView> getDataById(int id);

	/**
	 * 根据工艺id找出工艺步骤编辑
	 * @param id 工艺Id
	 * @return 工艺步骤编辑dto
	 */
    public List<ProcessItemEditeView> getProcessStepEditeById(int id);

	/**
	 * 根据前端的id做假删除处理
	 * @param i 前端的id
	 */
	public void delData(int i);

	/**
	 * 根据id找到工艺实体
	 * @param i 工艺id
	 * @return 工艺实体
	 */
	public ProcessModel getProcessDataById(int i);

	/**
	 * 根据工艺，工艺步骤添加
	 * @param Process 工艺
	 * @param processSteps 工艺步骤
	 */
	public void addData(ProcessModel Process,List<ProcessStep> processSteps);

	/**
	 * 根据工艺，工艺步骤更新
	 * @param Process 工艺
	 * @param processSteps 工艺步骤
	 */
	public void updateData(ProcessModel Process,List<ProcessStep> processSteps);

	/**
	 * 获取工艺的列表
	 * @return 返回工艺的列表
	 */
	public List<Map<String, Object>> getProcessList();

	/**
	 * 返回攻占组列表
	 * @return 攻占组列表
	 */
	public List<StationGroup> getStationGroup();

	/**
	 * 根据工艺id查找工艺步骤总的条目数
	 * @param id 工艺id
	 * @return  返回总的条目数
	 */
	public int getCountProcessStepByProcessId(int id);

	/**
	 * 根据ProcessId查找相应的ProcessStep是否在生产中
	 * @param processId 工艺id
	 * @return 返回该工艺是否是在生产中
	 */
	public boolean isUsingInProduct(int processId);

	/**
	 * 根据工艺号查看该工艺号是否是被注册过的
	 * @param processNumber 工艺编号
	 * @return 返回是否注册过的
	 */
	public boolean isRegister(String processNumber);

	/**
	 * 根据岛区查找工站组
	 * @param id CellId
	 * @return 返回工站组
	 */
	public List<StationGroup> getStationGroupByCellId(int id);

	/**
	 *
	 * @param id 根据id返回
	 * @return 返回工艺编辑
	 */
	public ProcsessEditeView getProcessEditeView(int id);



}
