package com.smartflow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.smartflow.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CreateRoleDTO;
import com.smartflow.dto.EditInitializationRoleDTO;
import com.smartflow.dto.EditRoleDTO;
import com.smartflow.dto.GetDTOByConditionOfRoleDTO;
import com.smartflow.dto.RoleDTO;
import com.smartflow.dto.RoleDetailsDTO;
import com.smartflow.dto.RoleIdArrayAndMenuIdArrayDTO;
import com.smartflow.model.Role;
import com.smartflow.model.Roles_Menus;
import com.smartflow.model.Roles_Users;
import com.smartflow.util.ReadDataUtil;

@RestController
@RequestMapping("/api/Role")
public class RoleController extends BaseController {
    private static final Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    StationService stationService;

    @Autowired
    MenuService menuService;

    @Autowired
    MESMenuService mesMenuService;

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
        List<RoleDTO> Tdto = new ArrayList<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            Integer count = roleService.getTotalCountFromRole(getDTOByConditionOfRoleDTO);
            if (getDTOByConditionOfRoleDTO.getPageIndex() == null) {
                getDTOByConditionOfRoleDTO.setPageIndex(1);
            }
            if (getDTOByConditionOfRoleDTO.getPageIndex() == 0) {
                map.put("RowCount", 0);
                map.put("Tdto", Tdto);

                json = this.setJson(200, "无数据", map);
                return json;
            }
            List<Role> roleList = roleService.getRoleListByCondition(getDTOByConditionOfRoleDTO);
            if (roleList != null && !roleList.isEmpty()) {
                for (Role role : roleList) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setId(role.getId());
                    roleDTO.setRoleName(role.getRoleName());
                    roleDTO.setPlatformId(role.getPlatformId());
                    roleDTO.setPlatformName(userService.getPlatformNameByPlatformId(role.getPlatformId()));
                    roleDTO.setCreatorId(role.getCreatorId());
                    roleDTO.setCreator(stationService.getUserNameById(role.getCreatorId()));
                    roleDTO.setCreationDateTime(role.getCreationDateTime());
                    roleDTO.setEditorId(role.getEditorId());
                    roleDTO.setEditor(stationService.getUserNameById(role.getEditorId()));
                    roleDTO.setEditDateTime(role.getEditDateTime());
                    if (role.getVisit() != null && !"".equals(role.getVisit())) {
                        List<String> visitList = Arrays.asList(role.getVisit().split(","));
                        List<String> visitMenuList = new ArrayList<>(visitList);
                        if(!StringUtils.isEmpty(role.getVisitBtn())){
                            List<String> buttonList = Arrays.asList(role.getVisitBtn().split(","));
                            visitMenuList.addAll(buttonList);
                        }
                        roleDTO.setMenu(visitMenuList);
                    }
//					roleDetailsDTO.setMenu(StringUtils.collectionToDelimitedString(visitList, ","));//字符串类型的菜单Id:eg "1,3,11,7,5"
//                    if (role.getAreaIdList() != null && !"".equals(role.getAreaIdList())) {
//                        List<String> areaIdList = Arrays.asList(role.getAreaIdList().split(","));
//                        List<String> areaNumberList = new ArrayList<>();
//                        if (areaIdList != null && !areaIdList.isEmpty()) {
//                            for (String areaId : areaIdList) {
//                                if (!"".equals(areaId)) {
//                                    List<Map<String, Object>> areaList = roleService.getAreaById(Integer.parseInt(areaId));
//                                    for (Map<String, Object> area : areaList) {
//                                        String areaNumber = (String) area.get("label");
//                                        areaNumberList.add(areaNumber);
//                                    }
//                                }
//                                roleDTO.setArea(StringUtils.collectionToDelimitedString(areaNumberList, ","));
//                            }
//                        }
//                    }
                    String state = "";
                    if (role.getState() == 0) {
                        state = "未激活";
                    } else if (role.getState() == 1) {
                        state = "激活";
                    }
                    roleDTO.setState(state);
//                    roleDTO.setType(role.getType() == null ? null : (role.getType() == 1 ? "实体" : "非实体"));
                    Tdto.add(roleDTO);
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
            Role role = roleService.getRoleById(Id);
            RoleDetailsDTO roleDetailsDTO = new RoleDetailsDTO();
            if (role != null) {
                roleDetailsDTO.setId(role.getId());
                roleDetailsDTO.setRoleName(role.getRoleName());
                roleDetailsDTO.setPlatformName(userService.getPlatformNameByPlatformId(role.getPlatformId()));
                roleDetailsDTO.setCreator(stationService.getUserNameById(role.getCreatorId()));
                roleDetailsDTO.setCreationDateTime(role.getCreationDateTime());
                roleDetailsDTO.setEditor(stationService.getUserNameById(role.getEditorId()));
                roleDetailsDTO.setEditDateTime(role.getEditDateTime());
                String state = "";
                if (role.getState() == 0) {
                    state = "未激活";
                } else if (role.getState() == 1) {
                    state = "激活";
                }
                roleDetailsDTO.setState(state);
            }
            List<String> userNameList = roleService.getUserNameByRoleId(Id);
            roleDetailsDTO.setUser(StringUtils.collectionToDelimitedString(userNameList, ","));
            //查询角色对应的PDA菜单权限
            List<String> pdaMenuNameList = menuService.getPDAMenuNameByRoleId(Id);
            roleDetailsDTO.setPDAMenu(StringUtils.collectionToDelimitedString(pdaMenuNameList, ","));
            //查询角色对应的菜单权限
            if (!StringUtils.isEmpty(role.getVisit())) {
                List<String> visitList = Arrays.asList(role.getVisit().split(","));
                List<String> visitMenuList = new ArrayList<>(visitList);
                if(!StringUtils.isEmpty(role.getVisitBtn())){
                    List<String> buttonList = Arrays.asList(role.getVisitBtn().split(","));
                    visitMenuList.addAll(buttonList);
                }
                roleDetailsDTO.setMenu(visitMenuList);
            }
//			roleDetailsDTO.setMenu(StringUtils.collectionToDelimitedString(visitList, ","));//字符串类型的菜单Id:eg "1,3,11,7,5"
//            if (role.getAreaIdList() != null && !"".equals(role.getAreaIdList())) {
//                List<String> areaIdList = Arrays.asList(role.getAreaIdList().split(","));
//                List<String> areaNumberList = new ArrayList<>();
//                if (areaIdList != null && !areaIdList.isEmpty()) {
//                    for (String areaId : areaIdList) {
//                        if (!"".equals(areaId)) {
//                            List<Map<String, Object>> areaList = roleService.getAreaById(Integer.parseInt(areaId));
//                            for (Map<String, Object> map : areaList) {
//                                String areaNumber = (String) map.get("label");
//                                areaNumberList.add(areaNumber);
//                            }
//                        }
//                        roleDetailsDTO.setArea(StringUtils.collectionToDelimitedString(areaNumberList, ","));
//                    }
//                }
//            }
//            roleDetailsDTO.setType(role.getType() == null ? null : (role.getType() == 1 ? "实体" : "非实体"));
            json = this.setJson(200, "查询成功！", roleDetailsDTO);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败:" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 添加初始化查询平台模块
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetAddInitialize", method = RequestMethod.GET)
    public Map<String, Object> getAddInitialize() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Map<String, Object>> TotalPlatform = roleService.getAllPlatform();
            List<Map<String, Object>> TotalUser = roleService.getAllUser();
            List<Map<String, Object>> PDAMenu = menuService.getPDAMenuList();
//            List<Map<String, Object>> TotalArea = roleService.getTotalArea();
            List<Map<String, Object>> MESMenu = mesMenuService.getMESMenuListInit();
            Map<String, Object> map = new HashMap<>();
            map.put("TotalPlatform", TotalPlatform);
            map.put("TotalUser", TotalUser);
//            map.put("TotalArea", TotalArea);
            map.put("PDAMenu", PDAMenu);
            map.put("MESMenuList", MESMenu);

            json = this.setJson(200, "查询成功！", map);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败：" + e.getMessage(), -1);
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
        try {
            List<Map<String, Object>> TotalPlatform = roleService.getAllPlatform();
            List<Map<String, Object>> TotalUser = roleService.getAllUser();

            List<Map<String, Object>> AllocatedPDAMenu = menuService.getAllocatedPDAMenuByRoleId(Id);
//            List<Map<String, Object>> TotalArea = roleService.getTotalArea();
//            String areaIds = roleService.getAreaIdListByRoleId(Id);
            List<Map<String, Object>> AllocatedUser = roleService.getAllocatedUserByRoleId(Id);
//            List<Map<String, Object>> AllocatedArea = new ArrayList<>();
//            if (areaIds != null && !"".equals(areaIds)) {
//                List<String> areaIdList = Arrays.asList(areaIds.split(","));
//                if (areaIdList != null && !areaIdList.isEmpty()) {
//                    List<Integer> areaIdLists = areaIdList.stream().map(Integer::parseInt).collect(Collectors.toList());
//                    for (Integer areaId : areaIdLists) {
//                        if (!"".equals(areaId)) {
//                            List<Map<String, Object>> areaNumber = roleService.getAreaById(areaId);
//                            if (areaNumber != null && !areaNumber.isEmpty()) {
//                                AllocatedArea.add(areaNumber.get(0));
//                            }
//                        }
//                    }
//                }
//            }

            List<Map<String, Object>> PDAMenu = menuService.getPDAMenuList();
            Role role = roleService.getRoleById(Id);
            EditInitializationRoleDTO editInitializationRoleDTO = null;
            List<String> menu = new ArrayList<>();
            if (role != null) {
                editInitializationRoleDTO = new EditInitializationRoleDTO();
                editInitializationRoleDTO.setId(role.getId());
                editInitializationRoleDTO.setRoleName(role.getRoleName());
                editInitializationRoleDTO.setPlatformId(role.getPlatformId());
                editInitializationRoleDTO.setState(role.getState().toString());
                //查询角色对应的菜单权限
                if (role.getVisit() != null && !"".equals(role.getVisit())) {
                    List<String> visitList = Arrays.asList(role.getVisit().split(","));
                    List<String> visitMenuList = new ArrayList<>(visitList);
                    if(!StringUtils.isEmpty(role.getVisitBtn())){
                        List<String> buttonList = Arrays.asList(role.getVisitBtn().split(","));
                        visitMenuList.addAll(buttonList);
                    }
                    //roleDetailsDTO.setMenu(StringUtils.collectionToDelimitedString(visitList, ","));//字符串类型的菜单Id:eg "1,3,11,7,5"
                    //menu = visitList.stream().map(Integer::parseInt).collect(Collectors.toList());
                    menu = visitMenuList;
                }
//                editInitializationRoleDTO.setType(role.getType());
            }
            List<Map<String, Object>> MESMenu = mesMenuService.getMESMenuListInit();
            Map<String, Object> map = new HashMap<>();
            map.put("TotalPlatform", TotalPlatform);
            map.put("AllocatedUser", AllocatedUser);
            map.put("AllocatedPDAMenu", AllocatedPDAMenu);
            map.put("TotalUser", TotalUser);
//            map.put("TotalArea", TotalArea);
//            map.put("AllocatedArea", AllocatedArea);
            map.put("PDAMenu", PDAMenu);
            map.put("MESMenuList", MESMenu);
            map.put("Role", editInitializationRoleDTO);
            map.put("Menu", menu);
            json = this.setJson(200, "查询成功！", map);
        } catch (Exception e) {
            json = this.setJson(0, "查询失败：" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetReactAllRoleDTOForAddRole", method = RequestMethod.GET)
    public Map<String, Object> getReactAllRoleDTOForAddRole() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Map<String, Object>> TotalRole = userService.getTotalRole();
            json = this.setJson(200, "查询成功！", TotalRole);
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
     * @param createRoleDTO
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Post", method = RequestMethod.POST)
    public Map<String, Object> post(@Valid @RequestBody CreateRoleDTO createRoleDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            Role role = new Role();
            Integer count = roleService.getCountByRoleName(createRoleDTO.getRoleName());
            if (count == 0) {
                role.setRoleName(createRoleDTO.getRoleName());
                role.setPlatformId(createRoleDTO.getPlatformId());
                role.setCreatorId(createRoleDTO.getCreatorId());
                role.setCreationDateTime(new Date());
                role.setEditorId(createRoleDTO.getCreatorId());
                role.setEditDateTime(new Date());
                role.setState(createRoleDTO.getState());
//                role.setType(createRoleDTO.getType());
                //给角色配置菜单权限
                if (createRoleDTO.getMenu() != null && !createRoleDTO.getMenu().isEmpty()) {
                    List<String> menuList = createRoleDTO.getMenu().stream().filter(s -> !s.contains("-")).collect(Collectors.toList());
                    role.setVisit(StringUtils.collectionToDelimitedString(menuList, ","));
                    List<String> buttonList = createRoleDTO.getMenu().stream().filter(s -> s.contains("-")).collect(Collectors.toList());
                    role.setVisitBtn(StringUtils.collectionToDelimitedString(buttonList, ","));
                }
//                if (createRoleDTO.getArea() != null && !createRoleDTO.getArea().isEmpty()) {
//                    role.setAreaIdList(StringUtils.collectionToDelimitedString(createRoleDTO.getArea(), ","));
//                }
                roleService.addRole(role);
                Roles_Users roles_Users = new Roles_Users();
                roles_Users.setRoleId(role.getId());
                roles_Users.setCreatorId(createRoleDTO.getCreatorId());
                roles_Users.setCreationDateTime(new Date());
                if (createRoleDTO.getUser() != null && !createRoleDTO.getUser().isEmpty()) {
                    for (Integer userId : createRoleDTO.getUser()) {
                        roles_Users.setUserId(userId);
                        userService.addRoles_Users(roles_Users);
                    }
                }
                Roles_Menus roles_Menus = new Roles_Menus();
                roles_Menus.setRoleId(role.getId());
                roles_Menus.setCreatorId(createRoleDTO.getCreatorId());
                roles_Menus.setCreationDateTime(new Date());
                if (createRoleDTO.getPDAMenu() != null && !createRoleDTO.getPDAMenu().isEmpty()) {
                    for (Integer pdaMenuId : createRoleDTO.getPDAMenu()) {
                        roles_Menus.setMenuId(pdaMenuId);
                        menuService.addRoles_Menus(roles_Menus);
                    }
                }
                json = this.setJson(200, "添加成功！", 0);
            } else {
                json = this.setJson(0, "添加失败：角色名称不能重复！", -1);
            }
        } catch (Exception e) {
            json = this.setJson(0, "添加失败:" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 给角色添加菜单权限
     *
     * @param RoleIdArrayAndMenuIdArrayDTO
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/AddPermission", method = RequestMethod.POST)
    public Map<String, Object> addPermission(@RequestBody List<RoleIdArrayAndMenuIdArrayDTO> RoleIdArrayAndMenuIdArrayDTO) {
        Map<String, Object> json = new HashMap<>();
        try {
            Roles_Menus roles_Menus = new Roles_Menus();
            roles_Menus.setCreatorId(10);
            roles_Menus.setCreationDateTime(new Date());
            if (RoleIdArrayAndMenuIdArrayDTO != null && !RoleIdArrayAndMenuIdArrayDTO.isEmpty()) {
                for (RoleIdArrayAndMenuIdArrayDTO roleIdArrayAndMenuIdArrayDTO2 : RoleIdArrayAndMenuIdArrayDTO) {
                    Integer roleId = roleIdArrayAndMenuIdArrayDTO2.getRoleId();
                    Integer menuId = roleIdArrayAndMenuIdArrayDTO2.getMenuId();
                    roles_Menus.setRoleId(roleId);
                    roles_Menus.setMenuId(menuId);
                    roleService.addRoles_Menus(roles_Menus);
                }
            }
            json = this.setJson(200, "添加成功！", 0);
        } catch (Exception e) {
            json = this.setJson(0, "添加失败：" + e.getMessage(), -1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 修改角色保存
     *
     * @param editRoleDTO
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Put", method = RequestMethod.PUT)
    public Map<String, Object> put(@Valid @RequestBody EditRoleDTO editRoleDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            List<Roles_Users> roles_UsersList = roleService.getRoles_UsersByRoleId(editRoleDTO.getId());
            for (Roles_Users roles_Users : roles_UsersList) {
                userService.deleteRoles_Users(roles_Users);
            }
            Roles_Users roles_Users = new Roles_Users();
            roles_Users.setRoleId(editRoleDTO.getId());
            roles_Users.setCreatorId(editRoleDTO.getEditorId());
            roles_Users.setCreationDateTime(new Date());
            if (editRoleDTO.getUser() != null && !editRoleDTO.getUser().isEmpty()) {
                for (Integer userId : editRoleDTO.getUser()) {
                    roles_Users.setUserId(userId);
                    userService.addRoles_Users(roles_Users);
                }
            }

            List<Roles_Menus> roles_MenusList = menuService.getRoles_MenusByRoleId(editRoleDTO.getId());
            for (Roles_Menus roles_Menus : roles_MenusList) {
                menuService.deleteRoles_Menus(roles_Menus);
            }
            Roles_Menus roles_Menus = new Roles_Menus();
            roles_Menus.setRoleId(editRoleDTO.getId());
            roles_Menus.setCreatorId(editRoleDTO.getEditorId());
            roles_Menus.setCreationDateTime(new Date());
            if (editRoleDTO.getPDAMenu() != null && !editRoleDTO.getPDAMenu().isEmpty()) {
                for (Integer pdaMenuId : editRoleDTO.getPDAMenu()) {
                    roles_Menus.setMenuId(pdaMenuId);
                    menuService.addRoles_Menus(roles_Menus);
                }
            }
//			roles_Users.setRoleId(editRoleDTO.getId());
//			roles_Users.setCreatorId(10);
//			roles_Users.setCreationDateTime(new Date());
//			for (Integer userId : editRoleDTO.getUser()) {
//				roles_Users.setUserId(userId);
//				userService.updateRoles_Users(roles_Users);
//			}
            Role role = roleService.getRoleById(editRoleDTO.getId());
            Integer count = roleService.getCountByRoleName(editRoleDTO.getRoleName());
            if (count == 0 || editRoleDTO.getRoleName().equals(role.getRoleName())) {
                role.setRoleName(editRoleDTO.getRoleName());
                role.setPlatformId(editRoleDTO.getPlatformId());
                role.setEditorId(editRoleDTO.getEditorId());
                role.setEditDateTime(new Date());
                role.setState(editRoleDTO.getState());
//                role.setType(editRoleDTO.getType());
                if (editRoleDTO.getMenu() != null && !editRoleDTO.getMenu().isEmpty()) {
                    List<String> menuList = editRoleDTO.getMenu().stream().filter(s -> !s.contains("-")).collect(Collectors.toList());
                    role.setVisit(StringUtils.collectionToDelimitedString(menuList, ","));
                    List<String> buttonList = editRoleDTO.getMenu().stream().filter(s -> s.contains("-")).collect(Collectors.toList());
                    role.setVisitBtn(StringUtils.collectionToDelimitedString(buttonList, ","));
                }
//                if (editRoleDTO.getArea() != null && !editRoleDTO.getArea().isEmpty()) {
//                    role.setAreaIdList(StringUtils.collectionToDelimitedString(editRoleDTO.getArea(), ","));
//                }
                roleService.updateRole(role);
                json = this.setJson(200, "修改成功！", 0);
            } else {
                json = this.setJson(0, "修改失败：角色名称不能重复！", -1);
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
                    Role role = roleService.getRoleById(id);
                    role.setRoleName("Del@" + role.getRoleName());
                    role.setState(-1);
                    roleService.updateRole(role);
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