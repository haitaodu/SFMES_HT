package com.smartflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartflow.exception.ErrorCodeEnum;
import com.smartflow.exception.MyException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.model.AreaModel;
import com.smartflow.service.AreaService;
import com.smartflow.service.LocationService;
import com.smartflow.service.StationService;
import com.smartflow.util.AreaDataForPage;
import com.smartflow.util.ReadDataUtil;
@Controller
@RequestMapping("/api/Area")
public class AreaController  extends BaseController{
	@Autowired
	AreaService areaService;
	@Autowired
	StationService stationService;
	private static Logger logger = Logger.getLogger(BOMController.class);

	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/GetTByCondition")
	public @ResponseBody Object getPages(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		Map<String, Object> map=new HashMap<String,Object>();

		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
		String areaNumber = jsonObject.getString("AreaNumber")==null?null:jsonObject.getString("AreaNumber");
		String areaName = jsonObject.getString("AreaName")==null?null:jsonObject.getString("AreaName");
		Integer factoryId = jsonObject.getInteger("FactoryId")==null?null:jsonObject.getInteger("FactoryId");
		Map<String, Object> json=new HashMap<String,Object>();
		if (pageNumber==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		List<AreaDataForPage> pagedata=areaService.getPageData(pageSize, pageNumber,areaNumber, areaName, factoryId);

		if (pageNumber<1||pageSize<1)
		{
			throw new MyException(ErrorCodeEnum.PAGE_ERROR);
		}
		map.put("RowCount", areaService.count(areaNumber, areaName, factoryId));
		map.put("Tdto", pagedata);

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

		if (areaService.getDataById(Id)==null) {
			json=this.setJson(102, "您需要的数据暂且没有", 0);
			return json;
		}

		AreaDataForPage dataForDetail=areaService.getDataById(Id);

		if(areaService.getDataById(Id).getState().equals("1"))
		{
			dataForDetail.setState("已激活");
		}
		else if(areaService.getDataById(Id).getState().equals("0"))
		{
			dataForDetail.setState("未激活");;
		}
		else
		{
			dataForDetail.setState("已删除");
		}
		if (areaService.getDataById(Id).getAreaType().equals("")) {
			dataForDetail.setAreaType("无类型");
		}
		else if (areaService.getDataById(Id).getAreaType().equals("1")) {
			dataForDetail.setAreaType("仓库");
		}
		else if (areaService.getDataById(Id).getAreaType().equals("0")) {
			dataForDetail.setAreaType("虚拟");
		}
		else if(areaService.getDataById(Id).getAreaType().equals("2")){
			dataForDetail.setAreaType("车间");
		}

		try {
			json= this.setJson(200, "查询成功",dataForDetail);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public @ResponseBody Object delDataById(HttpServletRequest request,HttpServletResponse response) throws Exception
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
			if (areaService.getDataById(Id)==null) {
				json=this.setJson(202, "所删除数据为空", 0);
				return(json);
			}
			else if (areaService.getAreaDataById(Id).getState()==-1) {
				json=this.setJson(202, "该数据已被删除", 0);
				return(json);
			}
			areaService.delDataByChangeStatus(Id);
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
		
		List<Map<String, Object>> areaList=new ArrayList<>();
		Map<String, Object> area1=new HashMap<>();
		Map<String, Object> area2=new HashMap<>();
		Map<String, Object> area3=new HashMap<>();
		area1.put("key", 0);
		area1.put("label","虚拟 ");
		areaList.add(area1);
		area2.put("key", 1);
		area2.put("label", "仓库");
		areaList.add(area2);
		area3.put("key", 2);
		area3.put("label", "车间");
		areaList.add(area3);
		map.put("AreaTypeList", areaList);
		map.put("FactoryList", stationService.getFactory());
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
		List<Map<String, Object>> areaList=new ArrayList<>();
		Map<String, Object> area1=new HashMap<>();
		Map<String, Object> area2=new HashMap<>();
		Map<String, Object> area3=new HashMap<>();
		area1.put("key", 0);
		area1.put("label","虚拟 ");
		areaList.add(area1);
		area2.put("key", 1);
		area2.put("label", "仓库");
		areaList.add(area2);
		area3.put("key", 2);
		area3.put("label", "车间");
		areaList.add(area3);
		Map<String, Object> map=new  HashMap<>();
		
		map.put("AreaTypeList", areaList);
		map.put("areaDto", areaService.getDataById(Id));
		map.put("FactoryList", stationService.getFactory());
		try {
			json= this.setJson(200, "编辑初始化成功", map);
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
		String areaNumber = jsonObject.get("AreaNumber") == null ? "" : String.valueOf(jsonObject.get("AreaNumber"));
		String name = jsonObject.get("Name") == null ? "" : String.valueOf(jsonObject.get("Name"));
		String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
		String  factoryId = jsonObject.get("FactoryId") == null ? "" : String.valueOf(jsonObject.get("FactoryId"));
		String state = jsonObject.get("State") == null ? "" : String.valueOf(jsonObject.get("State"));
		Integer creatorId = jsonObject.getInteger("CreatorId");
		//创建者是根据前天返回的登录账户的相关信息得来的，这个用户信息登录暂未开发
		//String creator = jsonObject.get("Creator") == null ? "" : String.valueOf(jsonObject.get("Creator"));
		Integer areaType=jsonObject.getInteger("AreaType");
		AreaModel areaModel=new AreaModel();
		areaModel.setAreaNumber(areaNumber);
		areaModel.setCreationDateTime(new Date());
		areaModel.setDescription(description);
		areaModel.setFactoryId(Integer.valueOf(factoryId));
		areaModel.setName(name);
		//未开发相关模块，创建者Id写死
		areaModel.setAreaType(areaType);
		areaModel.setCreatorId(creatorId);
		areaModel.setEditDateTime(new Date());
		areaModel.setEditorId(creatorId);
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
		areaModel.setState(Integer.valueOf(state));
		//需要写表的重复操作
		if (areaNumber.length()>=40)
		{
			json= this.setJson(103, "您输入的区域号过长无法注册", 0);
			return json;
		}
		if (areaService.isRegisterAreaNumber(areaNumber))
		{
			json= this.setJson(103, "您输入的区域号已被注册", 0);
			return json;
		}
		if (areaService.getDataForCheckUnique(areaNumber, description)==0) {
			areaService.saveDataForArea(areaModel);

		}
		else {
			json= this.setJson(103, "您所插入的数据已存在", 0);
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
		String name = jsonObject.get("Name") == null ? "" : String.valueOf(jsonObject.get("Name"));
		String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
		String  factoryId = jsonObject.get("FactoryId") == null ? "" : String.valueOf(jsonObject.get("FactoryId"));
		String state = jsonObject.get("State") == null ? "" : String.valueOf(jsonObject.get("State"));
		String areaNumber = jsonObject.get("AreaNumber") == null ? "" : String.valueOf(jsonObject.get("AreaNumber"));
		Integer areaType=jsonObject.getInteger("AreaType");
		Integer editorId = jsonObject.getInteger("EditorId");
		AreaModel areaModel=new AreaModel();
		areaModel.setAreaNumber(areaNumber);
		areaModel.setEditDateTime(new Date());
		areaModel.setDescription(description);
		areaModel.setFactoryId(Integer.valueOf(factoryId));
		areaModel.setName(name);
		areaModel.setId(Integer.valueOf(id));
		//未开发相关模块，创建者Id写死
		areaModel.setEditorId(editorId);
		areaModel.setAreaType(areaType);
		areaModel.setState(Integer.valueOf(state));
		areaModel.setCreationDateTime(areaService.getAreaDataById(Integer.valueOf(id)).getCreationDateTime());
		areaModel.setCreatorId(areaService.getAreaDataById(Integer.valueOf(id)).getCreatorId());
		/*
		if (areaService.getDataForCheckUnique(areaNumber, description)==0) {

		}
		else {
			json=this.setJson(201, "你所修改的数据是相同数据", -1);
			return json;
		}
		判别更改的数据是否是同一个数据，这个判别方法适用于添加，不适用于更改
		 */
		if (areaNumber.length()>=40)
		{
			json= this.setJson(103, "您输入的区域号过长无法注册", 0);
			return json;
		}
		/*
		if (areaService.isRegisterAreaNumber(areaNumber))
		{
			json= this.setJson(103, "您输入的区域号已被注册", 0);
			return json;
		}
		*/
		areaService.upDataForArea(areaModel);
		try {
			json= this.setJson(200, "编辑成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * ADM查询区域模块-获取工厂名称(下拉框)
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetInitializeList",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFactoryNameList(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> FactoryName = stationService.getFactory();	
			Map<String, Object> map = new HashMap<>();
			map.put("FactoryList", FactoryName);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

}
