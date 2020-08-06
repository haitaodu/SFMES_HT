package com.smartflow.dao;


import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.model.Department;

import java.util.List;
import java.util.Map;
public interface DepartmentDao {

    /**
     * 初始化部门下拉框
     * @return
     */
    public List<Map<String,Object>> getDepartmentListInit();

    /**
     * 查询部门总条数
     * @param getDTOByConditionOfRoleDTO
     * @return
     */
    public Integer getTotalCountDepartment(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO);

    /**
     * 查询部门列表信息
     * @param getDTOByConditionOfRoleDTO
     * @return
     */
    public List<Department> getDepartmentListByCondition(GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO);

    /**
     * 根据部门编号查询该部门编号是否重复
     * @param deptNumber
     * @return
     */
    public Integer getCountByDeptNumber(String deptNumber);

    /**
     * 添加部门
     * @param department
     */
    public void addDepartment(Department department);

    /**
     * 修改部门
     * @param department
     */
    public void updateDepartment(Department department);

    /**
     * 根据部门id查询部门信息
     * @param departmentId
     */
    public Department getDepartmentById(Integer departmentId);

}
