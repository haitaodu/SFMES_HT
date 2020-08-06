package com.smartflow.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.model.LocationModel;
import com.smartflow.service.AreaService;
import com.smartflow.service.LocationService;
import com.smartflow.util.LocationDataForPage;
import com.smartflow.util.ReadDataUtil;

@Controller
@RequestMapping("/api/Location")
public class LocationController extends BaseController{
	@Autowired
	LocationService locationService;

	@Autowired
	AreaService areaService;
	private static Logger logger = Logger.getLogger(LocationController.class);

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public @ResponseBody Object readPageData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
		String locationNumber = jsonObject.getString("LocationNumber")==null?null:jsonObject.getString("LocationNumber");
		String description = jsonObject.getString("Description")==null?null:jsonObject.getString("Description");
		Integer areaId = jsonObject.getInteger("AreaId")==null?null:jsonObject.getInteger("AreaId");
		Map<String, Object> json=new HashMap<String,Object>();
		List<LocationDataForPage>pageData= locationService.getPageData(pageNumber, pageSize,locationNumber, description, areaId);
		Map<String, Object> map=new HashMap<String,Object>();

		if (pageNumber==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			/*
			map.put("PageSize", pageSize);
			map.put("PageIndex", pageNumber);
			 */
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		map.put("RowCount", locationService.getCount(locationNumber, description, areaId));
		map.put("Tdto", pageData);

		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTById/{Id}",method=RequestMethod.GET)
	public @ResponseBody Object readDataById(@PathVariable Integer Id)
	{
		Map<String, Object> json=new HashMap<>();
		/*
		if (locationService.getDataById(Id)==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;
		}
		 */
		try {
			json= this.setJson(200, "查询成功", locationService.getDataById(Id));
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public @ResponseBody Object delDataById(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Map<String, Object> json=new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		@SuppressWarnings("unchecked")
		List<Integer> list=(List<Integer>)  jsonObject.get("List");


		if (list.size()==0) {

			json=this.setJson(204, "未选择删除数据", 1);
			return(json);
		}
		for (Integer Id : list) {
		if (locationService.getDataById(Id)==null) {
			json=this.setJson(202, "所删除数据为空", 0);
			return(json);
		}
		else if (locationService.getLocationDataById(Id).getState()==-1) {
			json=this.setJson(202, "该数据已被删除", 0);
			return(json);
		}
		locationService.delData(Id);
		}
		try {
			json= this.setJson(200, "删除成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;

	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetAddInitialize",method=RequestMethod.GET)
	public @ResponseBody Object getAddInitialize()
	{
		Map<String, Object> json=new HashMap<>();
		/*
		if (locationService.getDataById(Id)==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;
		}
		 */
		Map<String, Object> map=new HashMap<>();
		map.put("locationDto", null);
		map.put("AreaList", locationService.getAreaList());
		try {
			json= this.setJson(200, "新增初始化数据成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
	public @ResponseBody Object readGetEditInitializeById(@PathVariable Integer Id)
	{
		Map<String, Object> json=new HashMap<>();
		/*
		if (locationService.getDataById(Id)==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;
		}
		 */
		Map<String, Object> map=new  HashMap<>();
		Map<String, Object> locationDtos=new HashMap<>();
		locationDtos.put("Id", locationService.getDataById(Id).getId());
		locationDtos.put("LocationNumber", locationService.getDataById(Id).getLocationNumber());
		locationDtos.put("Area", locationService.getLocationDataById(Id).getAreaId().toString());
//		locationDtos.put("X", locationService.getDataById(Id).getX());
//		locationDtos.put("Y", locationService.getDataById(Id).getY());
//		locationDtos.put("Z", locationService.getDataById(Id).getZ());
		locationDtos.put("State", locationService.getLocationDataById(Id).getState().toString());
		locationDtos.put("CreationDateTime", locationService.getDataById(Id).getCreateDateTime());
		locationDtos.put("Creator", locationService.getDataById(Id).getCreator());
		locationDtos.put("Editor", locationService.getDataById(Id).getEditor());
		locationDtos.put("EditDateTime", locationService.getDataById(Id).getEditDateTime());
		locationDtos.put("Description", locationService.getDataById(Id).getDescription());
		map.put("locationDto", locationDtos);

		map.put("AreaList", locationService.getAreaList());


		try {
			json= this.setJson(200, "编辑初始化数据成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public @ResponseBody Object post(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> json=new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String locationNumber = jsonObject.get("LocationNumber") == null ? "" : String.valueOf(jsonObject.get("LocationNumber"));
		String areaId = jsonObject.get("AreaId") == null ? "" : String.valueOf(jsonObject.get("AreaId"));
		String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
//		String  x= jsonObject.get("X") == null ? "" : String.valueOf(jsonObject.get("X"));
//		String  y = jsonObject.get("Y") == null ? "" : String.valueOf(jsonObject.get("Y"));
//		String  z = jsonObject.get("Z") == null ? "" : String.valueOf(jsonObject.get("Z"));
		String state = jsonObject.get("State") == null ? "" : String.valueOf(jsonObject.get("State"));
		Integer creatorId = jsonObject.getInteger("CreatorId");
		if (locationNumber.length()>=40)
		{
			json= this.setJson(200, "该库位号过长无法注册", 0);
			return(json);
		}
		//创建者是根据前天返回的登录账户的相关信息得来的，这个用户信息登录暂未开发
		//String creator = jsonObject.get("Creator") == null ? "" : String.valueOf(jsonObject.get("Creator"));
		LocationModel locationModel=new LocationModel();
		locationModel.setLocationNumber(locationNumber);
		locationModel.setDescription(description);
		locationModel.setAreaId(Integer.valueOf(areaId));
		//当前端给的值是空字符的时候默认的数据库字段是0
//		if (!x.equals(""))
//		{
//			locationModel.setX(Float.valueOf(x));
//
//		}
//		if (!y.equals(""))
//		{
//			locationModel.setY(Float.valueOf(y));
//		}
//		if (!z.equals(""))
//		{
//			locationModel.setZ(Float.valueOf(z));
//		}
//		locationModel.setX(0);
//		locationModel.setY(0);
//		locationModel.setZ(0);
		locationModel.setState(Integer.valueOf(state));
		//未开发相关模块，创建者Id写死
		locationModel.setCreatorId(creatorId);
		locationModel.setCreateDateTime(new Date());
		locationModel.setEditDateTime(new Date());
		locationModel.setEditorId(creatorId);
		/*
		int State=1;
		if (state=="激活") {
			State=1;
		}
		else if (state=="未激活") {
			State=0;
		}
		else {
			State=-1;
		}
		 */

		//需要写表的重复操作
		if (locationService.isRegisterLocationNumber(locationNumber))
		{
			json= this.setJson(200, "该库位号已经被注册过", 0);
			return(json);
		}
		if (locationNumber.length()>=40)
		{
			json= this.setJson(200, "该库位号过长无法注册", 0);
			return(json);
		}
		if (locationService.getDataForCheckUnique(locationNumber, description)==0) {
			locationService.saveDataForArea(locationModel);
		}
		else {
			json=this.setJson(103, "你所插入的数据已存在", -1);
			return json;
		}

		try {
			json= this.setJson(200, "添加成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public @ResponseBody Object put(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> json=new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String id = jsonObject.get("Id") == null ? "" : String.valueOf(jsonObject.get("Id"));
		String locationNumber = jsonObject.get("LocationNumber") == null ? "" : String.valueOf(jsonObject.get("LocationNumber"));
		String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
		String  areId = jsonObject.get("AreaId") == null ? "" : String.valueOf(jsonObject.get("AreaId"));
		String state = jsonObject.get("State") == null ? "" : String.valueOf(jsonObject.get("State"));
//		String x = jsonObject.get("X") == null ? "" : String.valueOf(jsonObject.get("X"));
//		String y = jsonObject.get("Y") == null ? "" : String.valueOf(jsonObject.get("Y"));
//		String z = jsonObject.get("Z") == null ? "" : String.valueOf(jsonObject.get("Z"));
		Integer editorId = jsonObject.getInteger("EditorId");
		if (locationNumber.length()>=40)
		{
			json= this.setJson(200, "该库位号过长无法注册", 0);
			return(json);
		}
		LocationModel locationModel=new LocationModel();
		locationModel.setId(Integer.valueOf(id));
		locationModel.setLocationNumber(locationNumber);
		locationModel.setDescription(description);
		locationModel.setAreaId(Integer.valueOf(areId));
//		locationModel.setX(Float.valueOf(x));
//		locationModel.setY(Float.valueOf(y));
//		locationModel.setZ(Float.valueOf(z));
		locationModel.setState(Integer.valueOf(state));
		locationModel.setEditorId(editorId);
		locationModel.setEditDateTime(new Date());
		locationModel.setCreateDateTime(locationService.getLocationDataById(Integer.valueOf(id)).getCreateDateTime());
		locationModel.setCreatorId(editorId);
		if (locationService.isRegisterLocationNumber(locationNumber))
		{
			json= this.setJson(200, "该库位号已经被注册过", 0);
			return(json);
		}

		if (locationService.getLocationDataById(Integer.valueOf(id))==null) {
			json=this.setJson(101, "所修改的数据暂且没有", -1);
			return(json);
		}

		locationService.upDataForArea(locationModel);

		try {
			json= this.setJson(200, "修改成功", 0);

		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * ADM查询库位模块-区域（下拉框）精准查询
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetInitializeList",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAreaNameList(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> AreaName = areaService.getAreaIdAndName();
			Map<String, Object> map = new HashMap<>();
			map.put("AreaList", AreaName);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(200, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
}
