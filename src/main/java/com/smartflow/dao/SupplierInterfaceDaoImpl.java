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

import com.smartflow.model.SupplierInEditInitializeModel;
import com.smartflow.model.SupplierListDTO;
import com.smartflow.model.SupplierModel;
import com.smartflow.util.SupplierReadData;

@Repository
public class SupplierInterfaceDaoImpl implements SupplierInterfaceDao{
	@Autowired
	HibernateTemplate hibernate;
	@Override
	@Transactional
	public SupplierModel getDataById(Integer id) {
		// TODO Auto-generated method stub
		return hibernate.get(SupplierModel.class, id);
	}

	@Override
	@Transactional
	public SupplierReadData getDetailsById(Integer id) {
		// TODO Auto-generated method stub
		SupplierReadData supplierReadData=new SupplierReadData();
		SupplierModel  supplierModel=new SupplierModel();
		//对于数据转换的化，我先用这种方式，取二次数据库的值。对效率而言目前看不到什么效率问题
		supplierModel=hibernate.get(SupplierModel.class, id);
		if(supplierModel!=null)
		{
			supplierReadData.setSupplierAddress(supplierModel.getAdress());
			supplierReadData.setSupplierCode(supplierModel.getCode());
			supplierReadData.setSupplierContactPerson(supplierModel.getContactPerson());
			supplierReadData.setSupplierCountry(supplierModel.getCountry());
			supplierReadData.setSupplierCreationDateTime(supplierModel.getCreationDateTime());
			supplierReadData.setSupplierEditeDateTime(supplierModel.getCreationDateTime());
			supplierReadData.setSupplierEmail(supplierModel.getEmail());
			supplierReadData.setSupplierFax(supplierModel.getFax());
			supplierReadData.setSupplierId(supplierModel.getId());
			supplierReadData.setSupplierName(supplierModel.getName());
			supplierReadData.setSupplierPostCode(supplierModel.getPostCode());
			supplierReadData.setSupplierProvince(supplierModel.getProvince());
			supplierReadData.setSupplierState(supplierModel.getState());
			supplierReadData.setSupplierTelphone(supplierModel.getTelphone());
			supplierReadData.setDUNS(supplierModel.getDUNS());
			supplierReadData.setSupplierMobilePhone(supplierModel.getMobilePhone());
			//根据CustomerCreationId以及EditorId找出CustomerCreationName以及EditorName
			SessionFactory sessionFactory=hibernate.getSessionFactory();
			Session session=sessionFactory.openSession();
			//对CustomerCreatorId为空作空指针判断，若不为空，那么就做数据转换
			if(supplierModel.getCreatorId()!=null)
			{

				String hql="select user.name FROM UserModel user WHERE user.id in (select supplier.CreatorId  FROM SupplierModel supplier where supplier.Id=:id )";
				Query  query=session.createQuery(hql);
				query.setParameter("id", id);
				@SuppressWarnings("unchecked")
				List<String> creator=query.list();

				supplierReadData.setSupplierCreatorName(creator.get(0));
			}
			//对CustomerEditorId为空作空指针判断，若不为空，那么就做数据转换
			if(supplierModel.getEditorId()!=null)
			{

				String hql="SELECT user.name FROM UserModel user WHERE user.id in(SELECT supplier.EditorId FROM SupplierModel supplier WHERE supplier.Id=:id) ";
				Query  query=session.createQuery(hql);
				query.setParameter("id", id);
				@SuppressWarnings("unchecked")
				List<String> editor=query.list();

				supplierReadData.setSupplierEditorName(editor.get(0));
			}
			session.close();
		}

		return supplierReadData;
	}

	@Override
	@Transactional
	public void addData(SupplierModel supplierModel) {
		// TODO Auto-generated method stub
		hibernate.save(supplierModel);

	}

	@Override
	@Transactional
	public SupplierInEditInitializeModel getEditInitializeData(Integer id) {
		// TODO Auto-generated method stub
		return hibernate.get(SupplierInEditInitializeModel.class, id);
	}

	@Override
	@Transactional
	public void delData(Integer id) {
		// TODO Auto-generated method stub
	SupplierModel supplierModel=hibernate.get(SupplierModel.class, id);
	supplierModel.setState(-1);
	supplierModel.setCode("Del@"+supplierModel.getCode());
	hibernate.update(supplierModel);
	}

	@Override
	@Transactional
	public void upDateData(SupplierModel supplierModel) {
		// TODO Auto-generated method stub
		hibernate.update(supplierModel);
	}

	@Override
	@Transactional
	public List<SupplierListDTO> getPageData(Integer pageNumber, Integer pageSize,String SupplierCode,String SupplierName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql=" FROM SupplierModel s WHERE State!=-1 AND SupplierCode LIKE '%'+:SupplierCode+'%' AND Name  LIKE '%'+:SupplierName+'%'  ORDER BY s.Id ";
		Query  query=session.createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameter("SupplierCode", SupplierCode);
		query.setParameter("SupplierName", SupplierName);
		@SuppressWarnings("unchecked")
		List<SupplierModel> supplierModelList= query.list();
		List<SupplierListDTO> supplierListDTOList= new ArrayList<>();
		for (SupplierModel supplierModel : supplierModelList) {
			String state = null;
			if(supplierModel.getState()!=null)//作空指针判断，数据库允许为空
			{
			if (supplierModel.getState()==1) {
				state="激活";
			}else if (supplierModel.getState()==0) {
				state = "未激活";
			}else{
				state = "已删除";
			}
			}
			SupplierListDTO supplierListDTO = new SupplierListDTO(supplierModel.getId(), supplierModel.getCode(), supplierModel.getName(), supplierModel.getDUNS(), supplierModel.getCountry(), supplierModel.getProvince(), supplierModel.getAdress(), supplierModel.getPostCode(), supplierModel.getFax(), supplierModel.getContactPerson(), supplierModel.getEmail(), supplierModel.getTelphone(), supplierModel.getMobilePhone(), this.getDetailsById(supplierModel.getId()).getSupplierEditorName(), supplierModel.getEditeDateTime(), state);
			supplierListDTOList.add(supplierListDTO);
		}
		session.close();
		return supplierListDTOList ;
	}

	@Override
	public Integer getRowCount(String SupplierCode,String SupplierName) {
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM SupplierModel  WHERE State!=-1 AND SupplierCode LIKE '%'+:SupplierCode+'%' AND Name  LIKE '%'+:SupplierName+'%'";
		Query  query=session.createQuery(hql);
		query.setParameter("SupplierCode", SupplierCode);
		query.setParameter("SupplierName", SupplierName);
		Integer rowCount=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return rowCount;
		
	}

	@Override
	public int getDataForCheckUnique(String SupplierCode, String SupplierName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=hibernate.getSessionFactory();
		Session session=sessionFactory.openSession();
		String hql="SELECT COUNT (*)FROM SupplierModel WHERE State!=-1 AND Code=:SupplierCode AND Name=:SupplierName ";
		Query  query=session.createQuery(hql);
		query.setParameter("SupplierCode", SupplierCode);
		query.setParameter("SupplierName", SupplierName);
		Integer returnResult=Integer.valueOf(query.uniqueResult().toString());
		session.close();
		return returnResult;
	}

	@Override
	public boolean isRegisterSupplierNumber(String SupplierNumber) {
		List<SupplierModel> supplierModels=(List<SupplierModel>) hibernate.findByNamedParam("From SupplierModel Where SupplierCode=:supplierNumber","supplierNumber",SupplierNumber);
		if(supplierModels.size()>1)
		{
			return true;
		}
		return false;
	}

}
