package com.smartflow.dao;

import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.model.UserModel;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ProcessStepDataForPage;
import com.smartflow.util.global.PageUtil;
import com.smartflow.view.Process.ProcessDetailView;
import com.smartflow.view.Process.ProcessItemDetailView;
import com.smartflow.view.Process.ProcessItemEditeView;
import com.smartflow.view.Process.ProcsessEditeView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author haita
 */
@Repository
public class ProcessDaoImpl implements ProcessDao {
	final
	HibernateTemplate hibernate;
	private final
	MaterialDao material;
	final
	FactoryDao factory;
	final
	UserDao user;
	private final
	StationGroupDao stationGroup;

	@Autowired
	public ProcessDaoImpl(HibernateTemplate hibernate, MaterialDao material, FactoryDao factory, CellDao cellDao, UserDao user, StationGroupDao stationGroup) {
		this.hibernate = hibernate;
		this.material = material;
		this.factory = factory;
		CellDao cellDao1 = cellDao;
		this.user = user;
		this.stationGroup = stationGroup;
	}

	@Override
	public int getCount(String materialNumber,String factoryName) {
		
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

				ProcessDataForPage processDataForPage =
						new ProcessDataForPage(processModel.getId(),
						processModel.getProcessNumber(),
						material.getDataById(processModel.getMaterialId()).getMaterialNumber(),
						processModel.getCell() == null ? null : processModel.getCell().getDescription(),//factory.getDataById(processModel.getFactoryId()).getName(),
						state, processModel.getValidBegin(), processModel.getValidEnd(),
						processModel.getCreationDateTime(),
						processModel.getEditDateTime(),
								EditorName,processModel.getParentProcessNumber(),processModel.getVersion());
				processDataForPages.add(processDataForPage);
			}

			return processDataForPages;
		}
		finally {
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public ProcessDetailView getDataInId(int id) {
		ProcessModel processModel=hibernate.get(ProcessModel.class, id);
		String EditorName=null;
		if (processModel.getEditorId()!=null) {
			UserModel u = user.getDataById(processModel.getEditorId());
			if(u != null){
				EditorName = u.getName();
			}
		}
		return new ProcessDetailView(
				processModel.getId(),
				processModel.getProcessNumber(),
				material.getDataById(processModel.getMaterialId()).getMaterialNumber(),
				processModel.getCell().getDescription(),
                PageUtil.paseState(processModel.getState()),
                processModel.getValidBegin(),
				processModel.getValidEnd(),
				processModel.getCreationDateTime(),
				processModel.getEditDateTime(),
				EditorName,processModel.getParentProcessNumber(),processModel.getVersion());

	}
	@Override
	public List<ProcessItemDetailView> getDataById(int id) {
		

		return parseProcessStepListToDetailList(getProcessStepById(id));
	}

	@Override
	public List<ProcessItemEditeView> getProcessStepEditeById(int id) {

		return parseProcessStepListToEditeList(getProcessStepById(id));
	}

	@Override
	@Transactional
	public void delData(int i) {
		ProcessModel processModel=hibernate.get(ProcessModel.class, i);
		processModel.setState(-1);
		processModel.setProcessNumber("Del@"+processModel.getProcessNumber());
		hibernate.update(processModel);
	}
	@Override
	public ProcessModel getProcessDataById(int i) {
		
		return hibernate.get(ProcessModel.class, i);
	}
	@Override
	@Transactional
	public void addData(ProcessModel process, List<ProcessStep> processSteps) {
		

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
		
		SessionFactory sessionFactory = hibernate.getSessionFactory();
		Session session = sessionFactory.openSession();  
		String sql = "select Id [key],ProcessNumber label from core.Process";
		Query query = session.createSQLQuery(sql);
		session.close();
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	@Override
	public List<StationGroup> getStationGroup() {
		
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

		int n=0;
		for (ProcessStep processStep : getProcessStepById(id)) {
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
		return n > 0 && n2 > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isRegister(String processNumber) {
	List<ProcessModel>  processModels=(List<ProcessModel>)
            hibernate.findByNamedParam
                    ("From ProcessModel Where ProcessNumber=:processNumber",
                            "processNumber",processNumber);
        return !processModels.isEmpty();
    }

	@Override
	public List<StationGroup> getStationGroupByCellId(int id) {
		Session session=hibernate.getSessionFactory().openSession();
		String sql="FROM StationGroup WHERE State=1 and CellId=:id";
		Query query=session.createQuery(sql);
		query.setParameter("id",id);
		@SuppressWarnings("unchecked")
		List<StationGroup> stationGroups=query.list();
		session.close();
		return stationGroups;
	}

    @Override
    public ProcsessEditeView getProcessEditeView(int id) {
        ProcessModel processModel=hibernate.get(ProcessModel.class, id);
        return new ProcsessEditeView(
                processModel.getId(),
                processModel.getProcessNumber(),
                material.getDataById(processModel.getMaterialId()).getMaterialNumber(),
                processModel.getCell().getId(),
                processModel.getState(),
                processModel.getValidBegin(),
                processModel.getValidEnd(),
                processModel.getCreationDateTime(),
                processModel.getEditDateTime(),
                processModel.getEditorId(),processModel.getParentProcessNumber(),processModel.getVersion());
    }


	/**
	 * 根据工艺id查找出工艺步骤列表
	 * @param id 工艺id
	 * @return 返回工艺步骤列表
	 */
	@SuppressWarnings("unchecked")
	private List<ProcessStep> getProcessStepById(int id)
	{
		Session session=hibernate.getSessionFactory().openSession();
		String hql="FROM ProcessStep WHERE ProcessId=:i";
		Query query=session.createQuery(hql);
		query.setParameter("i", id);
		List<ProcessStep> processSteps=query.list();
		session.close();
		return processSteps;
	}

	/**
	 * 将工艺步骤列表修饰成工艺修改列表
	 * @param processSteps 工艺列表
	 * @return 工艺修改列表
	 */
	private List<ProcessItemEditeView> parseProcessStepListToEditeList(List<ProcessStep> processSteps)
	{
		List<ProcessItemEditeView> processItemEditeViews=new ArrayList<>();
		int n=0;
		for (ProcessStep processStep : processSteps) {
			String editorName=null;
			if (processStep.getEditorId()!=null) {
				UserModel u = user.getDataById(processStep.getEditorId());
				if(u != null){
					editorName = u.getName();
				}
			}
			processItemEditeViews.add(new ProcessItemEditeView
					(processStep.getSecquence(),
							processStep.getProcessId(),
							processStep.getId(),
							processStep.getDescription(),
							processStep.getStationGroupId(),
							stationGroup.getStationGroupById(
									processStep.getStationGroupId()).getDescription(),
							processStep.isIsNeedSetupCheck(),
							editorName, processStep.getEditDateTime(), n));

			n++;
		}
		return processItemEditeViews;
	}


	/**
	 * 将工艺步骤列表修改为工艺详情列表
	 * @param processSteps 工艺步骤列表
	 * @return 工艺详情列表
	 */
	private List<ProcessItemDetailView> parseProcessStepListToDetailList(List<ProcessStep> processSteps)
	{
		List<ProcessItemDetailView> processItemDetailViews=new ArrayList<>();
		int n=0;
		for (ProcessStep processStep : processSteps) {
			String editorName=null;
			if (processStep.getEditorId()!=null) {
				UserModel u = user.getDataById(processStep.getEditorId());
				if(u != null){
					editorName = u.getName();
				}
			}
			processItemDetailViews.add(new ProcessItemDetailView
					(processStep.getSecquence(),
							processStep.getProcessId(),
							processStep.getId(),
							processStep.getDescription(),
							processStep.getStationGroupId(),
							stationGroup.getStationGroupById(
									processStep.getStationGroupId()).getDescription(),
							PageUtil.parseToTrueFalse(processStep.isIsNeedSetupCheck()),
							editorName, processStep.getEditDateTime(), n));

			n++;
		}
		return processItemDetailViews;
	}
}





