package com.smartflow.service;

import com.smartflow.dao.DepartmentDao;
import com.smartflow.dto.CreateDepartmentDTO;
import com.smartflow.dto.EditDepartmentDTO;
import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<Map<String, Object>> getDepartmentListInit() {
        return departmentDao.getDepartmentListInit();
    }

    @Override
    public Integer getTotalCountDepartment(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
        return departmentDao.getTotalCountDepartment(getDTOByConditionOfRoleDTO);
    }

    @Override
    public List<Department> getDepartmentListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO) {
        return departmentDao.getDepartmentListByCondition(getDTOByConditionOfRoleDTO);
    }

    @Override
    public Integer getCountByDeptNumber(String deptNumber) {
        return departmentDao.getCountByDeptNumber(deptNumber);
    }

    @Override
    public void addDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Department department = new Department();
        department.setDeptNumber(createDepartmentDTO.getDeptNumber());
        department.setName(createDepartmentDTO.getName());
        department.setState(createDepartmentDTO.getState());
        department.setCreatorId(createDepartmentDTO.getCreatorId());
        department.setCreationDateTime(new Date());
        departmentDao.addDepartment(department);
    }

    @Override
    public void updateDepartment(EditDepartmentDTO editDepartmentDTO) {
        Department department = departmentDao.getDepartmentById(editDepartmentDTO.getId());
        department.setDeptNumber(editDepartmentDTO.getDeptNumber());
        department.setName(editDepartmentDTO.getName());
        department.setState(editDepartmentDTO.getState());
        department.setEditDateTime(new Date());
        department.setEditorId(editDepartmentDTO.getEditorId());
        departmentDao.updateDepartment(department);
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }
}
