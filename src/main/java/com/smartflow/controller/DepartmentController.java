package com.smartflow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.*;
import com.smartflow.model.Department;
import com.smartflow.model.Role;
import com.smartflow.model.Roles_Menus;
import com.smartflow.model.Roles_Users;
import com.smartflow.service.DepartmentService;
import com.smartflow.service.StationService;
import com.smartflow.service.UserService;
import com.smartflow.util.ReadDataUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Department")
public class DepartmentController extends BaseController{

    private static final Logger logger = Logger.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;
    @Autowired
    StationService stationService;

    /**
     * 分页多条件查询所有角色
     *
     * @param getDTOByConditionOfRoleDTO
     * @return
     */
    @RequestMapping(value = "/GetTByCondition", method = RequestMethod.POST)
    public Map<String, Object> getTByCondition(@Valid @RequestBody GetDTOByConditionOfRoleDTO getDTOByConditionOfRoleDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List<DepartmentDTO> Tdto = new ArrayList<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            Integer count = departmentService.getTotalCountDepartment(getDTOByConditionOfRoleDTO);
            if (getDTOByConditionOfRoleDTO.getPageIndex() == null) {
                getDTOByConditionOfRoleDTO.setPageIndex(1);
            }
            if (getDTOByConditionOfRoleDTO.getPageIndex() == 0) {
                map.put("RowCount", 0);
                map.put("Tdto", Tdto);
                json = this.setJson(200, "无数据", map);
                return json;
            }
            List<Department> departmentList = departmentService.getDepartmentListByCondition(getDTOByConditionOfRoleDTO);
            if (!CollectionUtils.isEmpty(departmentList)) {
                for (Department department : departmentList) {
                    DepartmentDTO departmentDTO = new DepartmentDTO();
                    departmentDTO.setId(department.getId());
                    departmentDTO.setDeptNumber(department.getDeptNumber());
                    departmentDTO.setName(department.getName());
                    departmentDTO.setCreator(stationService.getUserNameById(department.getCreatorId()));
                    departmentDTO.setCreationDateTime(department.getCreationDateTime());
                    departmentDTO.setEditor(stationService.getUserNameById(department.getEditorId()));
                    departmentDTO.setEditDateTime(department.getEditDateTime());
                    String state = "";
                    if (department.getState() == 0) {
                        state = "未激活";
                    } else if (department.getState() == 1) {
                        state = "激活";
                    }
                    departmentDTO.setState(state);
                    Tdto.add(departmentDTO);
                }
            }
            map.put("RowCount", count);
            map.put("Tdto", Tdto);
            json = this.setJson(200, "查询成功！", map);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败:" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 根据Id查询角色信息
     *
     * @param Id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetTById/{Id}", method = RequestMethod.GET)
    public Map<String, Object> getTById(@PathVariable Integer Id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Department department = departmentService.getDepartmentById(Id);
            DepartmentDetailsDTO departmentDetailsDTO = new DepartmentDetailsDTO();
            if (department != null) {
                departmentDetailsDTO.setId(department.getId());
                departmentDetailsDTO.setDeptNumber(department.getDeptNumber());
                departmentDetailsDTO.setName(department.getName());
                departmentDetailsDTO.setCreator(stationService.getUserNameById(department.getCreatorId()));
                departmentDetailsDTO.setCreationDateTime(department.getCreationDateTime());
                departmentDetailsDTO.setEditor(stationService.getUserNameById(department.getEditorId()));
                departmentDetailsDTO.setEditDateTime(department.getEditDateTime());
                String state = "";
                if (department.getState() == 0) {
                    state = "未激活";
                } else if (department.getState() == 1) {
                    state = "激活";
                }
                departmentDetailsDTO.setState(state);
            }
            json = this.setJson(200, "查询成功！", departmentDetailsDTO);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败:" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }
    /**
     * 修改初始化和根据ID查询角色
     *
     * @param Id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetEditInitialize/{Id}", method = RequestMethod.GET)
    public Map<String, Object> getEditInitialize(@PathVariable Integer Id) {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        try {
            Department department = departmentService.getDepartmentById(Id);
            EditInitializationDepartmentDTO editInitializationDepartmentDTO = null;
            if (department != null) {
                editInitializationDepartmentDTO = new EditInitializationDepartmentDTO();
                editInitializationDepartmentDTO.setId(department.getId());
                editInitializationDepartmentDTO.setDeptNumber(department.getDeptNumber());
                editInitializationDepartmentDTO.setName(department.getName());
                editInitializationDepartmentDTO.setState(department.getState().toString());
            }
            map.put("Department", editInitializationDepartmentDTO);
            json = this.setJson(200, "查询成功！", map);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败：" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 添加角色保存
     *
     * @param createDepartmentDTO
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Post", method = RequestMethod.POST)
    public Map<String, Object> post(@Valid @RequestBody CreateDepartmentDTO createDepartmentDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            Integer count = departmentService.getCountByDeptNumber(createDepartmentDTO.getDeptNumber());
            if (count == 0) {
                departmentService.addDepartment(createDepartmentDTO);
                json = this.setJson(200, "添加成功！", 0);
            } else {
                json = this.setJson(0, "添加失败：部门编号不能重复！", -1);
            }
        } catch (Exception e) {
            json = this.setJson(0, "添加失败:" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 修改角色保存
     *
     * @param editDepartmentDTO
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Put", method = RequestMethod.PUT)
    public Map<String, Object> put(@Valid @RequestBody EditDepartmentDTO editDepartmentDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            Department department = departmentService.getDepartmentById(editDepartmentDTO.getId());
            Integer count = departmentService.getCountByDeptNumber(editDepartmentDTO.getDeptNumber());
            if (count == 0 || editDepartmentDTO.getDeptNumber().equals(department.getDeptNumber())) {
                departmentService.updateDepartment(editDepartmentDTO);
                json = this.setJson(200, "修改成功！", 0);
            } else {
                json = this.setJson(0, "修改失败：部门编号不能重复！", -1);
            }
        } catch (Exception e) {
            json = this.setJson(0, "修改失败：" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> json = new HashMap<>();
        JSONObject jsonObject = ReadDataUtil.paramData(request);
        JSONArray idArray = jsonObject.getJSONArray("List");
        try {
//			List<Roles_Users> roles_UsersList = roleService.getRoles_UsersByRoleId(id);
//			for (Roles_Users roles_Users : roles_UsersList) {
//				userService.deleteRoles_Users(roles_Users);
//			}
//			List<Roles_Menus> roles_MenusList = roleService.getRoles_MenusByRoleId(id);
//			for (Roles_Menus roles_Menus : roles_MenusList) {
//				roleService.deleteRoles_Menus(roles_Menus);
//			}
//			Role role = roleService.getRoleById(id);
//			roleService.deleteRole(role);
            if (idArray.size() != 0) {
                for (int i = 0; i < idArray.size(); i++) {
                    Integer id = idArray.getInteger(i);
                    Department department = departmentService.getDepartmentById(id);
                    department.setDeptNumber("Del@" + department.getDeptNumber());
                    department.setState(-1);
                    departmentService.updateDepartment(department);
                }
                json = this.setJson(200, "删除成功！", 0);
            } else {
                json = this.setJson(0, "删除失败:请选择要删除的数据！", -1);
            }
        } catch (Exception e) {
            json = this.setJson(0, "删除失败：" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }
}
