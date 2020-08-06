package com.smartflow.dao;

import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    HibernateTemplate hibernateTemplate;
    @Override
    public List<Map<String, Object>> getDepartmentListInit() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        String sql = "select Id [key],Name label from core.Department where State = 1";
        try{
            Query query = session.createSQLQuery(sql);
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }finally{
            session.close();
        }
    }

    @Override
    public Integer getTotalCountDepartment(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try{
            Query query = session.createQuery("select count(*) from Department where State!=-1");
            return Integer.parseInt(query.uniqueResult().toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
    public List<Department> getDepartmentListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        String hql = "from Department where State != -1";
        try{
            Query query = session.createQuery(hql);
            query.setFirstResult((getDTOByConditionOfRoleDTO.getPageIndex()-1)*getDTOByConditionOfRoleDTO.getPageSize());
            query.setMaxResults(getDTOByConditionOfRoleDTO.getPageSize());
            return query.list();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }finally{
            session.close();
        }
    }

    @Override
    public Integer getCountByDeptNumber(String deptNumber) {
        String hql = "select count(*) from Department where DeptNumber = :deptNumber";
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try{
            Query query = session.createQuery(hql);
            query.setParameter("deptNumber", deptNumber);
            return query.uniqueResult() == null ? 0 : Integer.parseInt(query.uniqueResult().toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            session.close();
        }
    }

    @Override
    public void addDepartment(Department department) {
        hibernateTemplate.save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        hibernateTemplate.update(department);
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return hibernateTemplate.get(Department.class, departmentId);
    }
}
