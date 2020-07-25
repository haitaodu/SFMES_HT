package com.smartflow.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.dao.ProcessDao;
import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ProcessStepDataForPage;
@Service
public class ProcessServiceImpl implements ProcessService{
@Autowired
ProcessDao process;
	@Override
	public int getcount(String materialNumber,String factoryName) {
		// TODO Auto-generated method stub
		return process.getCount(materialNumber,factoryName);
	}

	@Override
	public List<ProcessDataForPage> getPage(int pagesize, int pageindex,String materialNumber,String factoryName) {
		// TODO Auto-generated method stub
		return process.getPageData(pageindex, pagesize,materialNumber,factoryName);
	}

	@Override
	public ProcessDataForPage getDataInId(int id) {
		// TODO Auto-generated method stub
		return process.getDataInId(id);
	}

	@Override
	public List<ProcessStepDataForPage> getDataById(int id) {
		// TODO Auto-generated method stub
		return process.getDataById(id);
	}

	@Override
	public void delData(int i) {
		// TODO Auto-generated method stub
		process.delData(i);
	}

	@Override
	public ProcessModel getProcessDataById(int i) {
		// TODO Auto-generated method stub
		return process.getProcessDataById(i);
	}

	@Override
	@Transactional
	public void addData(ProcessModel Process, List<ProcessStep> ProcessSteps) {
		// TODO Auto-generated method stub
		process.addData(Process, ProcessSteps);
		
	}

	@Override
	@Transactional
	public void updateData(ProcessModel Process, List<ProcessStep> ProcessSteps) {
		// TODO Auto-generated method stub
		process.updateData(Process, ProcessSteps);
	}

	@Override
	public List<Map<String, Object>> getProcessList() {
		// TODO Auto-generated method stub
		return process.getProcessList();
	}

	@Override
	public List<StationGroup> getStationGroup() {
		// TODO Auto-generated method stub
		return process.getStationGroup();
	}

	@Override
	public int getCountProcessStepByProcessId(int id) {
		// TODO Auto-generated method stub
		return process.getCountProcessStepByProcessId(id);
	}

	@Override
	public boolean isUsingInProduct(int processId) {
		// TODO Auto-generated method stub
		return process.isUsingInProduct(processId);
	}

	@Override
	public boolean isRegister(String processNumber) {
		return process.isRegister(processNumber);
	}

}
