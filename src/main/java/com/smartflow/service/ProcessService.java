package com.smartflow.service;

import java.util.List;
import java.util.Map;

import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ProcessStepDataForPage;

public interface ProcessService {
	//计算Process表的总行数
	public int getcount(String materialNumber,String factoryName);
	//分页请求
	public List<ProcessDataForPage> getPage(int pagesize,int pageindex,String materialNumber,String factoryName);
	//根据Id号找出相应的信息记录
	public ProcessDataForPage getDataInId(int id);
	//根据前台传来的Id查找ProcessStep表单的数组
	public  List<ProcessStepDataForPage> getDataById(int id);
	//根据前端传来的Id号作假删除处理
	public void delData(int i);
	//根据前端传来的Id号查找表Process的全部数据
	public ProcessModel getProcessDataById(int i);
	//根据前端传来得参数添加工艺表的数据
	public void addData(ProcessModel Process,List<ProcessStep> processSteps);
	//根据前端传来的数据更改数据库的数据
	public void updateData(ProcessModel Process,List<ProcessStep> processSteps);
	//get到表的process的编号以及Id封装成数组
	public List<Map<String, Object>> getProcessList();
	//get到表StationGroup表的数据
	public List<StationGroup> getStationGroup();
	//根据ProcessId，查找对应的ProcessStep总的条数
	public int getCountProcessStepByProcessId(int id);
	//根据ProcessId查找相应的ProcessStep是否在生产中
	public boolean isUsingInProduct(int processId);
	//根据ProcessNumber查找相应的Process是否是已经注册过的
	public boolean isRegister(String processNumber);
}
