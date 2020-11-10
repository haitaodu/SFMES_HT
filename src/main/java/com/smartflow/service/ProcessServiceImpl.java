package com.smartflow.service;

import com.smartflow.dao.ProcessDao;
import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.view.Process.ProcessDetailView;
import com.smartflow.view.Process.ProcessItemDetailView;
import com.smartflow.view.Process.ProcessItemEditeView;
import com.smartflow.view.Process.ProcsessEditeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
/**
 * @author haita
 */
@Service
public class ProcessServiceImpl implements ProcessService{
private final
ProcessDao process;

	@Autowired
	public ProcessServiceImpl(ProcessDao process) {
		this.process = process;
	}

	@Override
	public int getcount(String materialNumber,String factoryName) {
		
		return process.getCount(materialNumber,factoryName);
	}

	@Override
	public List<ProcessDataForPage> getPage(int pagesize, int pageindex,String materialNumber,String factoryName) {
		
		return process.getPageData(pageindex, pagesize,materialNumber,factoryName);
	}

	@Override
	public ProcessDetailView getDataInId(int id) {
		
		return process.getDataInId(id);
	}

	@Override
	public List<ProcessItemDetailView> getDataById(int id) {
		
		return process.getDataById(id);
	}

	@Override
	public List<ProcessItemEditeView> getProcessStepEditeById(int id) {
		return process.getProcessStepEditeById(id);
	}

	@Override
	public void delData(int i) {
		
		process.delData(i);
	}

	@Override
	public ProcessModel getProcessDataById(int i) {
		
		return process.getProcessDataById(i);
	}

	@Override
	@Transactional
	public void addData(ProcessModel Process,
						List<ProcessStep> ProcessSteps) {
		process.addData(Process, ProcessSteps);
	}

	@Override
	@Transactional
	public void updateData(ProcessModel Process,
						   List<ProcessStep> ProcessSteps) {
		process.updateData(Process, ProcessSteps);
	}

	@Override
	public List<Map<String, Object>> getProcessList() {
		
		return process.getProcessList();
	}

	@Override
	public List<StationGroup> getStationGroup() {
		
		return process.getStationGroup();
	}

	@Override
	public int getCountProcessStepByProcessId(int id)
	{
		return process.getCountProcessStepByProcessId(id);
	}

	@Override
	public boolean isUsingInProduct(int processId) {
		
		return process.isUsingInProduct(processId);
	}

	@Override
	public boolean isRegister(String processNumber)
	{
		return process.isRegister(processNumber);
	}

	@Override
	public List<StationGroup> getStationGroupByCellId(int id)

	{
		return process.getStationGroupByCellId(id);
	}

	@Override
	public ProcsessEditeView getProcessEditeView(int id)
	{
		return process.getProcessEditeView(id);
	}

}
