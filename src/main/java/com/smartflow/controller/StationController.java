package com.smartflow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.common.enumpackage.JsonMessage;
import com.smartflow.common.enumpackage.StationEnum;
import com.smartflow.dto.CreationStationDTO;
import com.smartflow.dto.EditInitializationStationDTO;
import com.smartflow.dto.EditStationDTO;
import com.smartflow.dto.StationDTO;
import com.smartflow.model.Station;
import com.smartflow.service.QualificationService;
import com.smartflow.service.StationGroupService;
import com.smartflow.service.StationService;
import com.smartflow.util.ReadDataUtil;
import com.smartflow.util.global.PageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/api/Station")
public class StationController extends BaseController{
	private static Logger logger = Logger.getLogger(StationController.class);

	final
	StationService stationService;

	final
	StationGroupService stationGroupService;

	final
	QualificationService qualificationService;

	@Autowired
	public StationController(StationService stationService, StationGroupService stationGroupService, QualificationService qualificationService) {
		this.stationService = stationService;
		this.stationGroupService = stationGroupService;
		this.qualificationService = qualificationService;
	}

	/**
	 * 多条件分页查询工站
	 * @param request
	 * @param response
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/GetTByCondition")
	public @ResponseBody Map<String,Object> getStation(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		Map<String, Object> json;
		Map<String, Object> map = new HashMap<>();
		Integer pageIndex = jsonObject.get("PageIndex") == null ? null : Integer.parseInt(jsonObject.get("PageIndex").toString());
		Integer pageSize = jsonObject.get("PageSize") == null ? null : Integer.parseInt(jsonObject.get("PageSize").toString());
		String stationNumber = jsonObject.getString("StationNumber");
		String stationName = jsonObject.getString("StationName");
		Integer count = stationService.getTotalCount(stationNumber, stationName);
		try {	
			List<StationDTO> Tdto = new ArrayList<>();
			List<Station> stationList = null;
				stationList = stationService.getStationList(pageIndex,
						pageSize,
						stationNumber, stationName);
			if (stationList!=null && !stationList.isEmpty()) {
				for (Station station : stationList) {
					String creator = stationService.getUserNameById(station.getCreatorId());
					String editor = stationService.getUserNameById(station.getEditorId());
					String state = PageUtil.paseState(station.getState());
					StationDTO stationDTO = new StationDTO();
					stationDTO.setId(station.getId());
					stationDTO.setStationNumber(station.getStationNumber());
					stationDTO.setName(station.getName());
					stationDTO.setCreationDateTime(station.getCreationDateTime());
					stationDTO.setEditDateTime(station.getEditDateTime());
					stationDTO.setEditor(editor);
					stationDTO.setStationType(getStationLabel(station.getStationType()));
					stationDTO.setState(state);
					stationDTO.setCreator(creator);
					stationDTO.setStationIP(station.getIpAddress());
					Tdto.add(stationDTO);
				}
			}
			map.put("RowCount", count);
			map.put("Tdto", Tdto);			
			json = this.setJson(JsonMessage.SUCCESS_READ.getCode(),
					JsonMessage.SUCCESS_READ.getMessage(), map);
		}catch (Exception e) {
			json = this.setJson(JsonMessage.FALL_READ.getCode(),
					JsonMessage.FALL_READ.getMessage(),-1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 根据ID查询工站详细
	 * @param request
	 * @param response
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetTById/{Id}")
	public @ResponseBody Map<String,Object> getTById(@PathVariable Integer Id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> json ;
		try {		
			Station station = stationService.getStationById(Id);	
			StationDTO StationDTO = new StationDTO();
			if (station!=null) {
				StationDTO.setId(station.getId());
				StationDTO.setStationNumber(station.getStationNumber());
				StationDTO.setName(station.getName());
				StationDTO.setCreationDateTime(station.getCreationDateTime());
				StationDTO.setEditDateTime(station.getEditDateTime());
				String creator = stationService.getUserNameById(station.getCreatorId());
				String editor = stationService.getUserNameById(station.getEditorId());
				StationDTO.setEditor(editor);
				StationDTO.setStationType(getStationLabel(station.getStationType()));
				String state = PageUtil.paseState(station.getState());
				StationDTO.setState(state);
				StationDTO.setCreator(creator);
				StationDTO.setStationIP(station.getIpAddress());
				json = this.setJson(200, "查询成功", StationDTO);
			}else{
				json = this.setJson(0, "该Id在数据库中没有查询到数据！", -1);
			}
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 添加初始化工站
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetAddInitialize")
	public @ResponseBody Map<String, Object> getAddInitialize(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> json ;
		try {
			json = this.setJson(200, "查询成功",addInitialize());
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 修改初始化和根据ID查询工站
	 * @param Id 根据id
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetEditInitialize/{Id}")
	public @ResponseBody Map<String, Object> getEditInitialize(@PathVariable Integer Id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> json;
		Map<String, Object> map = new HashMap<>();
		try {

			json = this.setJson(200, "查询成功",editeInitialize(Id));
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 添加工站
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping (value="/Post")
	public @ResponseBody Map<String, Object> post(@Valid @RequestBody CreationStationDTO creationStationDTO,BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception{
	Map<String, Object> json ;
		try {	
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Station station = new Station();
			Integer count = stationService.getCountByStationNumber(creationStationDTO.getStationNumber());
			if(count==0){
				station.setStationNumber(creationStationDTO.getStationNumber());		
				station.setName(creationStationDTO.getName());
				station.setCreationDateTime(new Date());
				station.setCreatorId(creationStationDTO.getCreatorId());
				station.setEditorId(creationStationDTO.getCreatorId());
				station.setEditDateTime(new Date());
				station.setStationType(creationStationDTO.getStationType());
				station.setState(creationStationDTO.getState());
				station.setIpAddress(creationStationDTO.getStationIP());
				station.setFactoryId(1);
				stationService.addStation(station);
				qualificationService.addQualificationAndStationAccessControl(station);
				json = this.setJson(200, "添加成功",0);    
			}else{
				json = this.setJson(0, "添加失败：工站编号不能重复！",-1);  
			}
		} catch (Exception e) {
			json = this.setJson(0, "添加失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 修改工站
	 * @param editStationDTO
	 * @param result
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PutMapping (value="/Put")
	public @ResponseBody Map<String, Object> put
	(@Valid @RequestBody EditStationDTO editStationDTO,BindingResult result) {

		Map<String, Object> json;
		try {
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Station station = stationService.getStationById(editStationDTO.getId());
			Integer count = stationService.getCountByStationNumber(editStationDTO.getStationNumber());
			if (count==0 || editStationDTO.getStationNumber().equals(station.getStationNumber())) {
				station.setStationNumber(editStationDTO.getStationNumber());
				station.setName(editStationDTO.getName());
				station.setEditDateTime(new Date());
				station.setEditorId(editStationDTO.getEditorId());
				station.setStationType(editStationDTO.getStationType());
				station.setState(editStationDTO.getState());
				station.setIpAddress(editStationDTO.getStationIP());
				station.setFactoryId(1);
				stationService.updateStation(station);
				json = this.setJson(200, "修改成功",0);    
			}else{
				json = this.setJson(0, "工站编号不能重复！", -1);    
			}
			qualificationService.updateQualificationAndStationAccessControl(station);
		} catch (Exception e) {
			json = this.setJson(0, "修改失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;	
	}

	/**
	 * 根据ID删除工站
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping (value="/Delete")
	public @ResponseBody Map<String, Object> deleteStation(HttpServletRequest request) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		JSONArray idArray = jsonObject.getJSONArray("List");		
		Map<String, Object> json ;
		try {
			if (idArray!=null && !"".equals(idArray.toString())) {
				for (int i = 0; i < idArray.size(); i++) {
					Integer id = idArray.getInteger(i);
					Station station = stationService.getStationById(id);
					station.setStationNumber("Del@"+station.getStationNumber());
					station.setState(-1);
					stationService.updateStation(station);
					qualificationService.updateQualificationAndStationAccessControl(station);
				}
				json = this.setJson(200, "删除成功",0);  
			}else{
				json = this.setJson(0, "删除失败：请选择要删除的数据！", -1);
			}
		} catch (Exception e) {
			json = this.setJson(0, "删除失败："+e.getMessage(),-1);
			logger.error(e);
		}
		return json;	
	}


	/**
	 *获取工站类型列表
	 * @return 工站类型列表
	 */
	private List<Map<String,Object>>getStationTypeList()
	{

		List<Map<String, Object>> stationTypeList = new ArrayList<>();

		for (StationEnum stationEnum:StationEnum.values())
		{
			Map<String, Object> stationType = new HashMap<>(2);
			stationType.put("key", stationEnum.getKey());
			stationType.put("label", stationEnum.getValue());
			stationTypeList.add(stationType);
		}

		return  stationTypeList;
	}

	/**
	 * 根据key值查找工站名
	 * @param key  key值
	 * @return 返回攻占名
	 */
	private String getStationLabel(int key)
	{
		for (StationEnum stationEnum:StationEnum.values())
		{
			if (stationEnum.getKey()==key)
			{
             return stationEnum.getValue();
			}
		}
		return "测试工站";
	}

	/**
	 * 获取用于添加工站的初始化数据
	 * @return 返回攻占类型列表以及工厂列表
	 */
	private Map<String,Object> addInitialize()
	{
		Map<String, Object> map = new HashMap<>(2);
		List<Map<String, Object>> factoryList = stationService.getFactory();
		map.put("Factory", factoryList);
		map.put("StationType", getStationTypeList());
		return map;
	}


	/**
	 * 根据id初始化编辑的数据
	 * @param id id
	 * @return 返回初始化编辑的数据
	 */
	private Map<String,Object> editeInitialize(int id)
	{
		Map<String, Object> map=addInitialize();
		EditInitializationStationDTO stationDTO = new EditInitializationStationDTO();
		Station station = stationService.getStationById(id);
		stationDTO.setId(station.getId());
		stationDTO.setStationNumber(station.getStationNumber());
		stationDTO.setName(station.getName());
		stationDTO.setStationType(station.getStationType());
		stationDTO.setState(station.getState());
		stationDTO.setFactoryId(station.getFactoryId());
		stationDTO.setStationIP(station.getIpAddress());
		map.put("Station", stationDTO);
		return map;
	}

}
