package com.smartflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.smartflow.service.*;
import com.smartflow.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.CollectionUtils;
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
import com.smartflow.dto.CreateUserDTO;
import com.smartflow.dto.EditUserDTO;
import com.smartflow.dto.EditUserForInitializeDTO;
import com.smartflow.dto.GetDTOByConditionOfUserDTO;
import com.smartflow.dto.PersonnelDetailsDTO;
import com.smartflow.dto.UserDTO;
import com.smartflow.model.Roles_Users;
import com.smartflow.model.User;
import com.smartflow.util.MD5Util;
import com.smartflow.util.ReadDataUtil;

@RestController
@RequestMapping("/api/Staff")
public class UserController extends BaseController{
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	StationService stationService;
	
	
	@Autowired
	StationGroupService stationGroupService;

	@Autowired
    QualificationService qualificationService;

	@Autowired
	RoleService roleService;

	@Autowired
	DepartmentService departmentService;

	/**
	 * 工厂初始化
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTByConditionInit",method=RequestMethod.GET)
	public Map<String,Object> getTByConditionInit(){
		Map<String, Object> json = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		try{
			List<Map<String, Object>> FactoryList = stationService.getFactory();
			map.put("FactoryList", FactoryList);
//			List<Map<String, Object>> roleList = roleService.getTotalEntityRole();
//			map.put("RoleList", roleList);
			List<Map<String, Object>> departmentList = departmentService.getDepartmentListInit();
			map.put("DepartmentList", departmentList);
			json = this.setJson(200, "初始化成功！", map);
		}catch(Exception e){
			e.printStackTrace();
			json = this.setJson(0, "初始化失败:"+e.getMessage(), -1);
		}
		return json;
	}
	
	/**
	 * 多条件分页查询用户
	 * @param getDTOByConditionOfUserDTO
	 * @return
	 * @throws Exception 
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public Map<String, Object> getTByCondition(@Valid @RequestBody GetDTOByConditionOfUserDTO getDTOByConditionOfUserDTO,BindingResult result) throws Exception{
		Map<String, Object> json = new HashMap<>();
		List<UserDTO> Tdto = new ArrayList<>();		
		Map<String, Object> map = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Integer count = userService.getTotalCountFromUser(getDTOByConditionOfUserDTO);
			if(getDTOByConditionOfUserDTO.getPageIndex()==null){
				getDTOByConditionOfUserDTO.setPageIndex(1);
			}
			if (getDTOByConditionOfUserDTO.getPageIndex()==0) {
				map.put("RowCount", 0);
				map.put("Tdto", Tdto);			
				json = this.setJson(200, "无数据", map);
				return json;
			}
			List<User> userList = userService.getUserByCondition(getDTOByConditionOfUserDTO);
			if (userList!=null && !userList.isEmpty()) {
				for (User user : userList) {
					UserDTO userDTO = new UserDTO();
					userDTO.setId(user.getId());
					userDTO.setUserCode(user.getUserCode());
					userDTO.setICCardNumber(user.getICCardNumber());
					userDTO.setUserName(user.getUserName());
					userDTO.setPassword(user.getPassword());
					userDTO.setPlatformName(userService.getPlatformNameByPlatformId(user.getPlatformId()));
					userDTO.setAccount(user.getAccount());
					userDTO.setEmailAddress(user.getEmailAddress());
					userDTO.setPhone(user.getPhone());
					userDTO.setCreationDateTime(user.getCreationDateTime());
					String state = "";
					if (user.getState()==1) {
						state = "激活";
					}else if(user.getState()==0){
						state = "未激活";
					}
					userDTO.setState(state);
					userDTO.setLastLoginTime(user.getLastLoginTime());
					userDTO.setFactoryName(user.getFactoryId() == null ? null : stationGroupService.getFactoryNameById(user.getFactoryId()));
					List<String> stationNameList = qualificationService.getStationAccessControlListByUserId(user.getId());
                    String stationName = StringUtils.collectionToDelimitedString(stationNameList, ",");
                    userDTO.setStation(stationName);
					userDTO.setDepartment(user.getDepartment() == null ? null : user.getDepartment().getName());
					userDTO.setLevel(user.getLevel() == null ? null : StringUtil.getLevelName(user.getLevel()));
					Tdto.add(userDTO);
				}
			}
			map.put("Tdto", Tdto);
			map.put("RowCount", count);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败:"+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据ID查询用户详细
	 * @param Id
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTById/{Id}",method=RequestMethod.GET)
	public Map<String, Object> getTById(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			User user = userService.getUserById(Id);
			PersonnelDetailsDTO personnelDetailsDTO = new PersonnelDetailsDTO();
			if (user!=null) {				
				personnelDetailsDTO.setId(user.getId());
				personnelDetailsDTO.setUserName(user.getUserName());
				personnelDetailsDTO.setUserCode(user.getUserCode());
				personnelDetailsDTO.setICCardNumber(user.getICCardNumber());
				personnelDetailsDTO.setEmailAddress(user.getEmailAddress());
				personnelDetailsDTO.setPhone(user.getPhone());
				personnelDetailsDTO.setAccount(user.getAccount());
				personnelDetailsDTO.setPlatformName(userService.getPlatformNameByPlatformId(user.getPlatformId()));
				personnelDetailsDTO.setCreationDateTime(user.getCreationDateTime());
				personnelDetailsDTO.setCreator(stationService.getUserNameById(user.getCreatorId()));
				personnelDetailsDTO.setEditDateTime(user.getEditDateTime());
				personnelDetailsDTO.setEditor(stationService.getUserNameById(user.getEditorId()));
				personnelDetailsDTO.setEmailAddress(user.getEmailAddress());
				personnelDetailsDTO.setLastLoginTime(user.getLastLoginTime());
				personnelDetailsDTO.setFactoryName(user.getFactoryId() == null ? null : stationGroupService.getFactoryNameById(user.getFactoryId()));
				String state = "";
				if(user.getState()==1){
					state = "激活";//state = "正常";
				}else if (user.getState()==0) {
					state = "未激活";
				}else if (user.getState()==-1) {
					state = "已删除";//state = "锁定";
				}
				personnelDetailsDTO.setState(state);
			}
			List<String> roleNameList = userService.getRoleNameFromByUserId(Id);
			//将List集合转化成String字符串
			String roleName = StringUtils.collectionToDelimitedString(roleNameList, ",");
			personnelDetailsDTO.setRole(roleName);
            List<String> stationNameList = qualificationService.getStationAccessControlListByUserId(user.getId());
            String stationName = StringUtils.collectionToDelimitedString(stationNameList, ",");
            personnelDetailsDTO.setStation(stationName);
			personnelDetailsDTO.setDepartment(user.getDepartment() == null ? null : user.getDepartment().getName());
			personnelDetailsDTO.setLevel(user.getLevel() == null ? null : StringUtil.getLevelName(user.getLevel()));
			json = this.setJson(200, "查询成功！", personnelDetailsDTO);
		}catch(Exception e){
			json = this.setJson(0, "查询失败！", -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 添加初始化查询用户以及平台模块(String类型)
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetAddInitialize",method=RequestMethod.GET)
	public Map<String, Object> getAddInitialize(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> TotalRole = userService.getTotalRole();
			List<Map<String, Object>> TotalPlatform = userService.getTotalPlatform();
            List<Map<String, Object>> stationList = stationGroupService.getStation();
//			List<Map<String, Object>> roleList = roleService.getTotalEntityRole();
//			for (Map<String, Object> role:roleList) {
//				List<Map<String, Object>> userList = roleService.getAllocatedUserByRoleId(Integer.parseInt(role.get("key").toString()));
//				role.put("children", userList);
//			}
//			List<Map<String, Object>> TotalUser = roleService.getAllUser();
			Map<String, Object> map = new HashMap<>();
//			JSONArray.toJSON(TotalRole).toString();
			map.put("TotalRole", TotalRole);
			map.put("AllocatedRole", null);
			map.put("TotalPlatform", TotalPlatform);
			map.put("UserInitilizeDTO", null);
			List<Map<String,Object>> FactoryList = stationService.getFactory();
			map.put("FactoryList", FactoryList);
            map.put("StationList", stationList);
//            map.put("UserList", TotalUser);
			List<Map<String, Object>> departmentList = departmentService.getDepartmentListInit();
			map.put("DepartmentList", departmentList);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 修改用户初始化加载角色和平台模块数据(String类型)
	 * @param Id
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
	public Map<String, Object> getEditInitialize(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> TotalRole = userService.getTotalRole();
			List<Map<String, Object>> TotalPlatform = userService.getTotalPlatform();
			List<Map<String, Object>> AllocatedRole = userService.getAllocatedRole(Id);
            List<Map<String, Object>> stationList = stationGroupService.getStation();
            List<Map<String, Object>> allocatedStation = qualificationService.getStationListByUserId(Id);
//			List<Map<String, Object>> TotalUser = roleService.getAllUser();
			List<Map<String, Object>> departmentList = departmentService.getDepartmentListInit();
			User user = userService.getUserById(Id);
			EditUserForInitializeDTO UserInitilizeDTO = null;
			if (user!=null) {
				UserInitilizeDTO = new EditUserForInitializeDTO();
				UserInitilizeDTO.setAccount(user.getAccount());
				UserInitilizeDTO.setUserCode(user.getUserCode());
				UserInitilizeDTO.setCreationDateTime(user.getCreationDateTime());
				UserInitilizeDTO.setEmailAddress(user.getEmailAddress());
				UserInitilizeDTO.setId(user.getId());
				UserInitilizeDTO.setLastLoginTime(user.getLastLoginTime());
				UserInitilizeDTO.setPassword(user.getPassword());
				UserInitilizeDTO.setPhone(user.getPhone());
				UserInitilizeDTO.setPlatformId(user.getPlatformId());			
				UserInitilizeDTO.setState(user.getState().toString());
				UserInitilizeDTO.setUserName(user.getUserName());
				UserInitilizeDTO.setFactoryId(user.getFactoryId() == null ? "" : user.getFactoryId().toString());
				UserInitilizeDTO.setICCardNumber(user.getICCardNumber());
				UserInitilizeDTO.setDepartmentId(user.getDepartment() == null ? null : user.getDepartment().getId().toString());
				UserInitilizeDTO.setLevel(user.getLevel() == null ? null : user.getLevel().toString());
			}			
			Map<String, Object> map = new HashMap<>();
			map.put("TotalRole", TotalRole);
			map.put("AllocatedRole", AllocatedRole);
			map.put("TotalPlatform", TotalPlatform);
			map.put("UserInitilizeDTO", UserInitilizeDTO);
            map.put("StationList", stationList);
            map.put("AllocatedStation", allocatedStation);
//			map.put("UserList", TotalUser);
			map.put("DepartmentList", departmentList);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 添加用户保存(Username不能重复)
	 * @param createUserDTO
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public Map<String, Object> post(@Valid @RequestBody CreateUserDTO createUserDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			User user = new User();
			Integer count = userService.getCountByAccount(createUserDTO.getAccount());
//			Integer count = userService.getCountByUsername(createUserDTO.getUserName());
			Integer userCode = userService.getCountByUserCode(createUserDTO.getUserCode());//UserCode不能重复
			if(userCode != 0){
				json = this.setJson(0, "添加失败：员工工号不能重复！", -1);
				return json;
			}
			Integer icCardNumber = userService.getCountByICCardNumber(createUserDTO.getICCardNumber());//UserCode不能重复
			if(icCardNumber != 0){
				json = this.setJson(0, "添加失败：员工IC卡号不能重复！", -1);
				return json;
			}
			if(count==0){
				user.setUserCode(createUserDTO.getUserCode());
				user.setICCardNumber(createUserDTO.getICCardNumber());
				user.setUserName(createUserDTO.getUserName());
				user.setAccount(createUserDTO.getAccount());
//				user.setPassword(createUserDTO.getPassword());
//				user.setPassword(MD5Util.md5(createUserDTO.getPassword()));//密码使用MD5加密
				user.setPassword(MD5Util.md5("88888888"));//新增时默认密码为88888888
				user.setPlatformId(createUserDTO.getPlatformId());
				user.setEmailAddress(createUserDTO.getEmailAddress());
				user.setPhone(createUserDTO.getPhone());
				user.setCreationDateTime(new Date());
				user.setCreatorId(createUserDTO.getCreatorId());
				user.setEditDateTime(new Date());
				user.setEditorId(createUserDTO.getCreatorId());
				user.setState(createUserDTO.getState());
				user.setFactoryId(createUserDTO.getFactoryId());
				user.setDepartment(departmentService.getDepartmentById(createUserDTO.getDepartmentId()));
				user.setLevel(createUserDTO.getLevel());
				userService.addUser(user);
				if(!CollectionUtils.isEmpty(createUserDTO.getStation())){
                    qualificationService.addUser_Qualification(createUserDTO.getStation(), user);
                }
				if (createUserDTO.getRole()!=null && !createUserDTO.getRole().isEmpty()) {
					for (Integer roleId : createUserDTO.getRole()) {
						Roles_Users roles_Users = new Roles_Users();
						roles_Users.setUserId(user.getId());
						roles_Users.setCreatorId(createUserDTO.getCreatorId());
						roles_Users.setCreationDateTime(new Date());
						roles_Users.setRoleId(roleId);
						userService.addRoles_Users(roles_Users);
					}
				}				
				json = this.setJson(200, "添加成功！", 0);
			}else{
				json = this.setJson(0, "添加失败：账号不能重复！", -1);
			}			
		}catch(Exception e){
			json = this.setJson(0, "添加失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();		
		}
		return json;
	}
	
	/**
	 * 修改用户保存（先删除再新增）
	 * @param editUserDTO
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public Map<String, Object> put(@Valid @RequestBody EditUserDTO editUserDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{	
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			List<Roles_Users> roles_UsersList = userService.getRoles_UsersByUserId(editUserDTO.getId());
			for (Roles_Users roles_Users : roles_UsersList) {
				userService.deleteRoles_Users(roles_Users);
			}
			Roles_Users roles_Users = new Roles_Users();
			roles_Users.setUserId(editUserDTO.getId());
			roles_Users.setCreatorId(editUserDTO.getEditorId());
			roles_Users.setCreationDateTime(new Date());
			if(!CollectionUtils.isEmpty(editUserDTO.getRole())) {
				for (Integer roleId : editUserDTO.getRole()) {
					roles_Users.setRoleId(roleId);
					userService.addRoles_Users(roles_Users);
				}
			}
//			Roles_Users roles_Users = new Roles_Users();
//			roles_Users.setUserId(userId);
//			userService.updateRoles_Users(roles_Users);
			User user = userService.getUserById(editUserDTO.getId());
			Integer count = userService.getCountByAccount(editUserDTO.getAccount());//Account不能重复
//			Integer count = userService.getCountByUsername(editUserDTO.getUserName());

			Integer userCodeCount = userService.getCountByUserCode(editUserDTO.getUserCode());//UserCode不能重复
			if(userCodeCount != 0 && !editUserDTO.getUserCode().equals(user.getUserCode())){
				json = this.setJson(0, "修改失败：员工工号不能重复！", -1);
				return json;
			}
			Integer icCardNumberCount = userService.getCountByICCardNumber(editUserDTO.getICCardNumber());//UserCode不能重复
			if(icCardNumberCount != 0 && !editUserDTO.getICCardNumber().equals(user.getICCardNumber())){
				json = this.setJson(0, "修改失败：员工IC卡号不能重复！", -1);
				return json;
			}
			if (count==0 || editUserDTO.getAccount().equals(user.getAccount())) {
				user.setUserCode(editUserDTO.getUserCode());
				user.setICCardNumber(editUserDTO.getICCardNumber());
				user.setUserName(editUserDTO.getUserName());
				user.setAccount(editUserDTO.getAccount());
				//user.setPassword(MD5Util.md5(editUserDTO.getPassword()));//MD5加密密码
				user.setPlatformId(editUserDTO.getPlatformId());
				user.setEmailAddress(editUserDTO.getEmailAddress());
				user.setPhone(editUserDTO.getPhone());
				user.setEditDateTime(new Date());
				user.setEditorId(editUserDTO.getEditorId());
				user.setState(editUserDTO.getState());
				user.setFactoryId(editUserDTO.getFactoryId());
				user.setDepartment(editUserDTO.getDepartmentId() == null ? null : departmentService.getDepartmentById(editUserDTO.getDepartmentId()));
				user.setLevel(editUserDTO.getLevel());
				userService.updateUser(user);
				qualificationService.deleteUser_QualificationByUserId(editUserDTO.getId());
                if(!CollectionUtils.isEmpty(editUserDTO.getStation())){
                    qualificationService.addUser_Qualification(editUserDTO.getStation(), user);
                }
				json = this.setJson(200, "修改成功！", 0);
			}else{
				json = this.setJson(0, "修改失败：账号不能重复！", -1);
			}
		}catch(Exception e){
			json = this.setJson(0, "修改失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();		
		}
		return json;
	}
	
	/**
	 * 根据用户ID删除用户保存
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> json = new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		JSONArray idArray = jsonObject.getJSONArray("List");
		try{
//			List<Roles_Users> roles_UsersList = userService.getRoles_UsersByUserId(id);
//			for (Roles_Users roles_Users : roles_UsersList) {
//				userService.deleteRoles_Users(roles_Users);
//			}
//			User user = userService.getUserById(id);
//			userService.deleteUser(user);
			if (idArray.size()!=0) {
				for (int i = 0; i < idArray.size(); i++) {
					Integer id = idArray.getInteger(i);
					User user = userService.getUserById(id);
					user.setAccount("Del@"+user.getAccount());
					user.setState(-1);
					userService.updateUser(user);
                    qualificationService.deleteUser_QualificationByUserId(id);
				}
				json = this.setJson(200, "删除成功！", 0);
			}else{
				json = this.setJson(0, "删除失败：请选择要删除的数据！", -1);
			}			
		}catch(Exception e){
			json = this.setJson(0, "删除失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();		
		}
		return json;
	}

}
