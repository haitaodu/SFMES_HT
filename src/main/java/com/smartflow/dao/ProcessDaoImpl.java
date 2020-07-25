package com.smartflow.dao;

import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.model.UserModel;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ProcessStepDataForPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ProcessDaoImpl implements ProcessDao {
	@Autowired
	HibernateTemplate hibernate;
	@Autowired
	MaterialDao material;
	@Autowired 
	FactoryDao factory;
	@Autowired
	CellDao cellDao;
	@Autowired
	UserDao user;
	@Autowired
	StationGroupDao stationGroup;
	@Override
	public int getCount(String materialNumber,String factoryName) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="SELECT COUNT (*) FROM ProcessModel WHERE State !=- 1 ";
		if (materialNumber!=null) {
			hql+="AND MaterialId IN(FROM Material WHERE MaterialNumber LIKE '%"+materialNumber+"%') ";
		}
		if (factoryName!=null) {
			hql+="AND Cell.id = "+factoryName ;
		}
		Query query=session.createQuery(hql);
		Integer rowCount=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return rowCount;
	}
	@Override
	public List<ProcessDataForPage> getPageData(int pageindex, int pagesize,String materialNumber,String factoryName) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		try {
			String hql = "FROM ProcessModel WHERE State !=- 1 ";
			if (materialNumber != null) {
				hql += "AND MaterialId IN(FROM Material WHERE MaterialNumber LIKE '%" + materialNumber + "%') ";
			}
			if (factoryName != null) {
				Integer factoryId = Integer.valueOf(factoryName);
                hql+="AND Cell.id = "+factoryId ;
			}
			Query query = session.createQuery(hql);


			query.setFirstResult((pageindex - 1) * pagesize);
			query.setMaxResults(pagesize);
			List<ProcessDataForPage> processDataForPages = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<ProcessModel> processModels = query.list();

			for (ProcessModel processModel : processModels) {
				String state = null;
				if (processModel.getState() == 1) {
					state = "已激活";
				} else if (processModel.getState() == 0) {
					state = "未激活";
				} else
					state = "已删除";
				//这里因为数据库取到的EditorId可能为Null,导致无法找到EditorName,会来个判空操作，CreatorId同样如此
				String EditorName = null;
				if (processModel.getEditorId() != null) {
					UserModel u = user.getDataById(processModel.getEditorId());
					if (u != null) {
						EditorName = u.getName();
					}
				}
				String materialnumber = null;
				String factoryname = null;

				ProcessDataForPage processDataForPage = new ProcessDataForPage(processModel.getId(),
						processModel.getProcessNumber(),
						material.getDataById(processModel.getMaterialId()).getMaterialNumber(),
						processModel.getCell() == null ? null : processModel.getCell().getDescription(),//factory.getDataById(processModel.getFactoryId()).getName(),
						state, processModel.getValidBegin(), processModel.getValidEnd(),
						processModel.getCreationDateTime(),
						processModel.getEditDateTime(), EditorName);
				processDataForPages.add(processDataForPage);
			}

			return processDataForPages;
		}
		finally {
			session.close();
		}
	}
	@Override
	public ProcessDataForPage getDataInId(int id) {
		// TODO Auto-generated method stub
		ProcessModel processModel=hibernate.get(ProcessModel.class, id);

		Integer state=null;
		if (processModel.getState()==1) {
			state=1;
		}
		else if (processModel.getState()==0) {
			state=0;
		}
		else {
			state=null;
		}
		//这里因为数据库取到的EditorId可能为Null,导致无法找到EditorName,会来个判空操作，CreatorId同样如此
		String EditorName=null;
		if (processModel.getEditorId()!=null) {
			UserModel u = user.getDataById(processModel.getEditorId());
			if(u != null){
				EditorName = u.getName();
			}
		}	
		ProcessDataForPage processDataForPage=new ProcessDataForPage(processModel.getId(), processModel.getProcessNumber(), material.getDataById(processModel.getMaterialId()).getMaterialNumber(), processModel.getCell() == null ? null : processModel.getCell().getId().toString(), state.toString(), processModel.getValidBegin(), processModel.getValidEnd(), processModel.getCreationDateTime(), processModel.getEditDateTime(),EditorName);//processModel.getFactoryId().toString()
		return processDataForPage;
	}
	@Override
	public List<ProcessStepDataForPage> getDataById(int id) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM ProcessStep WHERE ProcessId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", id);
		List<ProcessStep> processSteps=query.list();
		session.close();
		List<ProcessStepDataForPage> processStepDataForPages=new ArrayList<>();
		int n=0;
		for (ProcessStep processStep : processSteps) {

			String editorName=null;
			if (processStep.getEditorId()!=null) {
				UserModel u = user.getDataById(processStep.getEditorId());
				if(u != null){
					editorName = u.getName();
				}
			}
//			String isMandatory=null;
//			if (processStep.isIsMandatory()==true) {
//				isMandatory="True";
//			}
//			else if (processStep.isIsMandatory()==false) {
//				isMandatory="False";
//			}
			String isNeedSetupCheck=null;
			if (processStep.isIsNeedSetupCheck()==true) {
				isNeedSetupCheck="True";
			}
			else if (processStep.isIsNeedSetupCheck()==false) {
				isNeedSetupCheck="False";
			}
//			String IsBackflush=null;
//			if (processStep.isIsBackflush()==true) {
//				IsBackflush="True";
//			}
//			else if (processStep.isIsBackflush()==false) {
//				IsBackflush="False";
//			}
			processStepDataForPages.add(new ProcessStepDataForPage(processStep.getSecquence(),  processStep.getProcessId(), processStep.getId(), processStep.getDescription(), processStep.getStationGroupId(), stationGroup.getStationGroupById(processStep.getStationGroupId()).getDescription(), isNeedSetupCheck, editorName, processStep.getEditDateTime(), n));
//			processStepDataForPages.add(new ProcessStepDataForPage(processStep.getSecquence(), processStep.getProcessId(), processStep.getId(),processStep.getDescription(), processStep.getStationGroupId(), stationGroup.getStationGroupById(processStep.getStationGroupId()).getDescription(), isMandatory,isNeedSetupCheck, IsBackflush, processStep.getLayer(), processStep.getMaximumTestCount(), editorName, processStep.getEditDateTime(),n));
           n++;
		}
		return processStepDataForPages;
	}
	@Override
	@Transactional
	public void delData(int i) {
		// TODO Auto-generated method stub
		ProcessModel processModel=hibernate.get(ProcessModel.class, i);
		processModel.setState(-1);
		processModel.setProcessNumber("Del@"+processModel.getProcessNumber());
		hibernate.update(processModel);
	}
	@Override
	public ProcessModel getProcessDataById(int i) {
		// TODO Auto-generated method stub
		return hibernate.get(ProcessModel.class, i);
	}
	@Override
	@Transactional
	public void addData(ProcessModel process, List<ProcessStep> processSteps) {
		// TODO Auto-generated method stub

		hibernate.save(process);
		int processId=process.getId();
		for (ProcessStep processStep : processSteps) {
			processStep.setProcessId(processId);
			hibernate.save(processStep);
		}

	}
	@Override
	@Transactional
	public void updateData(ProcessModel process, List<ProcessStep> processSteps) {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String hql="DELETE  FROM 	ProcessStep  processStep  WHERE ProcessId=:ProcessId";
		Query query=session.createQuery(hql);
		query.setParameter("ProcessId", process.getId());
		query.executeUpdate();
		session.close();
		hibernate.update(process);
		int processId=process.getId();
		for (ProcessStep processStep : processSteps) {
			processStep.setProcessId(processId);
		}
		for (ProcessStep processStep : processSteps) {
			hibernate.save(processStep);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getProcessList() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = hibernate.getSessionFactory();
		Session session = sessionFactory.openSession();  
		String sql = "select Id [key],ProcessNumber label from core.Process";
		Query query = session.createSQLQuery(sql);
		session.close();
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<StationGroup> getStationGroup() {
		// TODO Auto-generated method stub
		Session session=hibernate.getSessionFactory().openSession();
		String sql="FROM StationGroup WHERE State=1";
		Query query=session.createQuery(sql);
		@SuppressWarnings("unchecked")
		
		List<StationGroup> stationGroups=query.list();
		session.close();
		return stationGroups;

	}
	@Override
	public int getCountProcessStepByProcessId(int id) {
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM ProcessStep WHERE ProcessId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", id);
		List<ProcessStep> processSteps=query.list();
		session.close();
		List<ProcessStepDataForPage> processStepDataForPages=new ArrayList<>();
		int n=0;
		for (ProcessStep processStep : processSteps) {
			n++;
		}
		return n;
	}
	@Override
	public boolean isUsingInProduct(int processId) {
		Session session=hibernate.getSessionFactory().openSession();
		String hql="SELECT COUNT(*) FROM PartMaterialRecord WHERE ProcessStepId IN (SELECT Id FROM ProcessStep WHERE ProcessId=:ProcessId) ";
		Query query=session.createQuery(hql);
		query.setParameter("ProcessId", processId);
		int n=Integer.valueOf(query.uniqueResult().toString());
		String hql2="SELECT COUNT(*) FROM PartProcessRecord WHERE ProcessStepId IN (SELECT Id FROM ProcessStep WHERE ProcessId=:ProcessId) ";
		Query query2=session.createQuery(hql2);
		query2.setParameter("ProcessId", processId);

		int n2=Integer.valueOf(query2.uniqueResult().toString());
		session.close();
		if (n>0&&n2>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isRegister(String processNumber) {
	List<ProcessModel>  processModels=(List<ProcessModel>) hibernate.findByNamedParam("From ProcessModel Where ProcessNumber=:processNumber","processNumber",processNumber);
		if (processModels.size()>=1)
		{
			return  true;
		}
		return  false;
	}
}





