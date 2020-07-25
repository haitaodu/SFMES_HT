package com.smartflow.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartflow.model.CustomerInEditInitializeModel;
import com.smartflow.model.CustomerListDto;
import com.smartflow.model.CustomerModel;
import com.smartflow.util.CustomerReadData;
@Repository
public class CustomerInterfaceDaoImpl implements CustomerInterfaceDao{

	//以HibernateTemplate为模板来进行数据库的CRUD	
	@Autowired
	HibernateTemplate  hibernate;
	@Transactional
	@Override
	public CustomerModel getDataById(Integer id) {
		// TODO Auto-generated method stub
		
		return hibernate.get(CustomerModel.class, id);
	}

	@Override
	@Transactional
	public CustomerReadData getDetailsById(Integer id) {
		// TODO Auto-generated method stub
		CustomerReadData customerReadData=new CustomerReadData();
		CustomerModel customerModel=new CustomerModel();
		//对于数据转换的化，我先用这种方式，取二次数据库的值。对效率而言目前看不到什么效率问题
		
		customerModel=hibernate.get(CustomerModel.class, id);
		if(customerModel!=null)
		{
		customerReadData.setCustomerAdress(customerModel.getCustomerAdress());
		customerReadData.setCustomerCode(customerModel.getCustomerCode());
		customerReadData.setCustomerContactPerson(customerModel.getCustomerContactPerson());
		customerReadData.setCustomerCountry(customerModel.getCustomerCountry());
		customerReadData.setCustomerCreationDateTime(customerModel.getCustomerCreationDateTime());
		customerReadData.setCustomerEditeDateTime(customerModel.getCustomerEditeDateTime());
		customerReadData.setCustomerEmail(customerModel.getCustomerEmail());
		customerReadData.setCustomerFax(customerModel.getCustomerFax());
		customerReadData.setCustomerId(customerModel.getCustomerId());
		customerReadData.setCustomerName(customerModel.getCustomerName());
		customerReadData.setCustomerPostCode(customerModel.getCustomerPostCode());
		customerReadData.setCustomerProvince(customerModel.getCustomerProvince());
		customerReadData.setCustomerState(customerModel.getCustomerState());
		customerReadData.setCustomerTelphone(customerModel.getCustomerTelphone());
		customerReadData.setDUNS(customerModel.getDUNS());
		customerReadData.setCustomerMobilePhone(customerModel.getCustomerMobilePhone());
		//根据CustomerCreationId以及EditorId找出CustomerCreationName以及EditorName
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		//对CustomerCreatorId为空作空指针判断，若不为空，那么就做数据转换
		if(customerModel.getCustomerCreatorId()!=null)
		{
			
			String hql="select user.name FROM UserModel user WHERE user.id in (select customer.CustomerCreatorId  FROM CustomerModel customer where customer.CustomerId=:id )";
			Query  query=session.createQuery(hql);
			query.setParameter("id", id);
		    @SuppressWarnings("unchecked")
			List<String> creator=query.list();
			
		    customerReadData.setCustomerCreatorName(creator.get(0));
		}
		//对CustomerEditorId为空作空指针判断，若不为空，那么就做数据转换
		if(customerModel.getCustomerEditorId()!=null)
		{

			String hql="SELECT user.name FROM UserModel user WHERE user.id in(SELECT customer.CustomerEditorId FROM CustomerModel customer WHERE customer.CustomerId=:id) ";
			Query  query=session.createQuery(hql);
			query.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<String> editor=query.list();
			
			customerReadData.setCustomerEditorName(editor.get(0));
		}
		session.close();
		}
		return customerReadData;
	}

	@Override
	@Transactional
	public void addData(CustomerModel customerModel) {
		// TODO Auto-generated method stub
		hibernate.save(customerModel);
		
	}

	@Override
	@Transactional
	public CustomerInEditInitializeModel getEditInitializeData(Integer id) {
		// TODO Auto-generated method stub
		return hibernate.get(CustomerInEditInitializeModel.class, id);
	}

	@Override
	@Transactional
	public void delData(Integer id) {
		// TODO Auto-generated method stub
	CustomerModel customerModel=hibernate.get(CustomerModel.class, id);
	customerModel.setCustomerState(-1);
	customerModel.setCustomerCode("Del@"+customerModel.getCustomerCode());
	hibernate.update(customerModel);
	}

	@Override
	@Transactional
	public void upDateData(CustomerModel customerModel) {
		// TODO Auto-generated method stub
		hibernate.update(customerModel);
		
	}

	@Override
	@Transactional
	public List<CustomerListDto> getPageData(Integer pageNumber, Integer pageSize,String CustomerCode,String CustomerName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="FROM CustomerModel WHERE CustomerState !=- 1 AND CustomerCode LIKE '%'+:CustomerCode+'%' AND CustomerName  LIKE '%'+:CustomerName+'%' ORDER BY Id ";		
		Query  query=session.createQuery(hql);
		query.setParameter("CustomerCode", CustomerCode);
		query.setParameter("CustomerName", CustomerName);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<CustomerModel> list= query.list();
		List<CustomerListDto> listDtos=new ArrayList<>();
		for (CustomerModel customerReadData : list) {
			String state = null;
			
			if(customerReadData.getCustomerState()!=null)
			{
			if ( customerReadData.getCustomerState()==1) {
				state = "激活";
			}else if (customerReadData.getCustomerState()==0) {
				state = "未激活";
			}else{
				state = "已删除";
			}
			}
			CustomerListDto customerListDto=new CustomerListDto(customerReadData.getCustomerId(), customerReadData.getCustomerCode(), customerReadData.getCustomerName(), customerReadData.getDUNS(), customerReadData.getCustomerCountry(), customerReadData.getCustomerProvince(), customerReadData.getCustomerAdress(), customerReadData.getCustomerPostCode(), customerReadData.getCustomerFax(), customerReadData.getCustomerContactPerson(), customerReadData.getCustomerEmail(), customerReadData.getCustomerTelphone(), customerReadData.getCustomerMobilePhone(),this.getDetailsById(customerReadData.getCustomerId()) == null ? null : this.getDetailsById(customerReadData.getCustomerId()).getCustomerEditorName(), customerReadData.getCustomerEditeDateTime(), state);
			listDtos.add(customerListDto);
		}
		session.close();
		return listDtos;
	}

	@Override
	public Integer getRowCount(String CustomerCode,String CustomerName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM CustomerModel WHERE CustomerState!=-1 AND CustomerCode LIKE '%'+:CustomerCode+'%' AND CustomerName  LIKE '%'+:CustomerName+'%'";
		Query  query=session.createQuery(hql);
		query.setParameter("CustomerCode", CustomerCode);
		query.setParameter("CustomerName", CustomerName);
		Integer rowCount=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return rowCount;
		
	}

	@Override
	public int getDataForCheckUnique(String CustomerCode, String CustomerName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM CustomerModel WHERE State!=-1 AND CustomerCode=:CustomerCode AND CustomerName=:CustomerName ";
		Query  query=session.createQuery(hql);
		query.setParameter("CustomerCode", CustomerCode);
		query.setParameter("CustomerName", CustomerName);
		Integer returnResult=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return returnResult;
	}

	@Override
	public boolean isRegisterCustomerNumber(String CustomerNumber) {
		List<CustomerModel> customerModels=(List<CustomerModel>) hibernate.findByNamedParam("From CustomerModel Where CustomerCode=:customerNumber","customerNumber",CustomerNumber);
		if(customerModels.size()>=1)
		{
			return true;
		}
			return  false;
	}

	@Override
	public boolean isRegisterDNUS(String DUNS) {
		List<CustomerModel> customerModels=(List<CustomerModel>) hibernate.findByNamedParam("From CustomerModel Where DUNS=:DUNS","DUNS",DUNS);
		if(customerModels.size()>=1)
		{
			return true;
		}
		return false;
	}

}
