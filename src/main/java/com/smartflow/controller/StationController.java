package com.smartflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.smartflow.service.QualificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CreationStationDTO;
import com.smartflow.dto.EditInitializationStationDTO;
import com.smartflow.dto.EditStationDTO;
import com.smartflow.dto.StationDTO;
import com.smartflow.model.Station;
import com.smartflow.model.Station_StationGroup;
import com.smartflow.service.StationGroupService;
import com.smartflow.service.StationService;

import com.smartflow.util.ReadDataUtil;

@Controller
@RequestMapping("/api/Station")
public class StationController extends BaseController{
	private static Logger logger = Logger.getLogger(StationController.class);

	@Autowired
	StationService stationService;

	@Autowired
	StationGroupService stationGroupService;

	@Autowired
	QualificationService qualificationService;
	
	/**
	 * 多条件分页查询工站
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getStation(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageIndex = jsonObject.get("PageIndex") == null ? null : Integer.parseInt(jsonObject.get("PageIndex").toString());
		Integer pageSize = jsonObject.get("PageSize") == null ? null : Integer.parseInt(jsonObject.get("PageSize").toString());
		String stationNumber = jsonObject.getString("StationNumber") == null ? null : jsonObject.getString("StationNumber");
		String stationName = jsonObject.getString("StationName") == null ? null : jsonObject.getString("StationName");
		Integer count = stationService.getTotalCount(stationNumber, stationName);
		try {	
			List<StationDTO> Tdto = new ArrayList<>();
			List<Station> stationList = null;
			if (pageSize!=null) {
				if (pageIndex==null) {
					pageIndex = 1;
				}
				if (pageIndex==0) {
					map.put("RowCount", 0);
					map.put("Tdto", Tdto);			
					json = this.setJson(200, "无数据", map);
					return json;
				}
				//Integer rowSize = count%pageSize == 0 ? count/pageSize : count/pageSize+1;	
				stationList = stationService.getStationList(pageIndex, pageSize, stationNumber, stationName);
			}

			if (stationList!=null && !stationList.isEmpty()) {
				for (Station station : stationList) {
					String creator = stationService.getUserNameById(station.getCreatorId());
					String editor = stationService.getUserNameById(station.getEditorId());
					String state = null;
					if (station.getState()==1) {
						state = "激活";
					}else if (station.getState()==0) {
						state = "未激活";
					}else{
						state = "已删除";
					}
//					StationDTO stationDTO = new StationDTO(station.getId(), station.getStationNumber(), station.getName(), station.getCreationDateTime(),
//							station.getEditDateTime(), editor, station.getStationType(), state, station.getFactoryId().toString(), creator);
					StationDTO stationDTO = new StationDTO();
					stationDTO.setId(station.getId());
					stationDTO.setStationNumber(station.getStationNumber());
					stationDTO.setName(station.getName());
					stationDTO.setCreationDateTime(station.getCreationDateTime());
					stationDTO.setEditDateTime(station.getEditDateTime());
					stationDTO.setEditor(editor);
					String stationType = null;
					if (station.getStationType()==0) {
						stationType = "制造工站";
					}else if(station.getStationType()==1){
						stationType = "测试工站";
					}else if(station.getStationType()==-1){
						stationType = "维修工站";
					}
					stationDTO.setStationType(stationType);
					stationDTO.setState(state);
//					if (station.getFactoryId()!=null) {
//						stationDTO.setFactoryId(stationGroupService.getFactoryNameById(station.getFactoryId()));
//					}
					stationDTO.setCreator(creator);
					//添加已分配工站组
//					List<String> stationGroupNameList = stationService.getStationGroupNameByStationId(station.getId());
//					stationDTO.setSelectedStationGroup(StringUtils.collectionToDelimitedString(stationGroupNameList, ","));
					stationDTO.setStationIP(station.getIpAddress());
					Tdto.add(stationDTO);
				}
			}
			map.put("RowCount", count);
			map.put("Tdto", Tdto);			
			json = this.setJson(200, "查询成功", map);
		}catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据ID查询工站详细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTById/{Id}",method=RequestMethod.GET)  
	public @ResponseBody Map<String,Object> GetTById(@PathVariable Integer Id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> json = new HashMap<String, Object>();
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
				String stationType = null;
				if (station.getStationType()==0) {
					stationType = "制造工站";
				}else if(station.getStationType()==1){
					stationType = "测试工站";
				}else if(station.getStationType()==-1){
					stationType = "维修工站";
				}
				StationDTO.setStationType(stationType);
				String state = null;
				if (station.getState()==1) {
					state = "激活";
				}else if (station.getState()==0) {
					state = "未激活";
				}else{
					state = "已删除";
				}
				StationDTO.setState(state);
//				if (station.getFactoryId()!=null) {
//					StationDTO.setFactory(stationGroupService.getFactoryNameById(station.getFactoryId()));
//				}
				StationDTO.setCreator(creator);	
				//添加已分配工站组
//				List<String> stationGroupNameList = stationService.getStationGroupNameByStationId(station.getId());
//				StationDTO.setSelectedStationGroup(StringUtils.collectionToDelimitedString(stationGroupNameList, ","));
				StationDTO.setStationIP(station.getIpAddress());
				json = this.setJson(200, "查询成功", StationDTO);
			}else{
				json = this.setJson(0, "该Id在数据库中没有查询到数据！", -1);
			}
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 添加初始化工站
	 * @param request
	 * @param response
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetAddInitialize",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> GetAddInitialize(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {	
			List<Map<String, Object>> Factory = stationService.getFactory();
//			List<Map<String, Object>> TotalStationGroup = stationService.getStationGroup();
			map.put("Factory", Factory);
//			map.put("SelectedStationGroup", null);
//			map.put("TotalStationGroup", TotalStationGroup);
			List<Map<String, Object>> stationTypeList = new ArrayList<>();
			Map<String, Object> stationType1 = new HashMap<>();
			stationType1.put("key", 0);
			stationType1.put("label", "制造工站");
			stationTypeList.add(stationType1);
			
			Map<String, Object> stationType2 = new HashMap<>();
			stationType2.put("key", 1);
			stationType2.put("label", "测试工站");
			stationTypeList.add(stationType2);
			
			Map<String, Object> stationType3 = new HashMap<>();
			stationType3.put("key", -1);
			stationType3.put("label", "维修工站");
			stationTypeList.add(stationType3);
			map.put("StationType", stationTypeList);
			json = this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 修改初始化和根据ID查询工站
	 * @param Id
	 * @param request
	 * @param response
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> GetEditInitialize(@PathVariable Integer Id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> Factory = stationService.getFactory();
//			List<Map<String, Object>> SelectedStationGroup = stationService.getStationGroupByStationId(Id);
//			List<Map<String, Object>> TotalStationGroup = stationService.getStationGroup();

			Station station = stationService.getStationById(Id);	
			EditInitializationStationDTO Station = null;
			if (station!=null) {
				Station = new EditInitializationStationDTO();
				Station.setId(station.getId());
				Station.setStationNumber(station.getStationNumber());
				Station.setName(station.getName());		
				Station.setStationType(station.getStationType());
//				String state = null;
//				if (station.getState()==1) {
//					state = "激活";
//				}else if (station.getState()==0) {
//					state = "未激活";
//				}else{//station.getState()==-1
//					state = "已删除";
//				}
				Station.setState(station.getState().toString());
				Station.setFactoryId(station.getFactoryId()==null?"":station.getFactoryId().toString());
				Station.setStationIP(station.getIpAddress());
			}
			
			map.put("Factory", Factory);
//			map.put("SelectedStationGroup", SelectedStationGroup);
//			map.put("TotalStationGroup", TotalStationGroup);
			map.put("Station", Station);
			List<Map<String, Object>> stationTypeList = new ArrayList<>();
			Map<String, Object> stationType1 = new HashMap<>();
			stationType1.put("key", 0);
			stationType1.put("label", "制造工站");
			stationTypeList.add(stationType1);
			
			Map<String, Object> stationType2 = new HashMap<>();
			stationType2.put("key", 1);
			stationType2.put("label", "测试工站");
			stationTypeList.add(stationType2);
			
			Map<String, Object> stationType3 = new HashMap<>();
			stationType3.put("key", -1);
			stationType3.put("label", "维修工站");
			stationTypeList.add(stationType3);
			map.put("StationType", stationTypeList);
			json = this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 添加工站
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> Post(@Valid @RequestBody CreationStationDTO creationStationDTO,BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		JSONObject jsonObject = ReadDataUtil.paramData(request);
//		String stationNumber = jsonObject.get("StationNumber") == null ? "" : String.valueOf(jsonObject.get("StationNumber"));
//		String name = jsonObject.get("Name") == null ? "" : String.valueOf(jsonObject.get("Name"));
//		//String creationDateTime = jsonObject.get("CreationDateTime") == null ? "" : String.valueOf(jsonObject.get("CreationDateTime"));
//		//Integer creatorId = jsonObject.get("CreatorId") == null ? null : Integer.valueOf(jsonObject.get("CreatorId").toString());
//		Integer stationType = jsonObject.get("StationType") == null ? null : Integer.valueOf(jsonObject.get("StationType").toString());
//		Integer state = jsonObject.get("State") == null ? null : Integer.valueOf(jsonObject.get("State").toString());
//		Integer factoryId = jsonObject.get("FactoryId") == null ? null : Integer.valueOf(jsonObject.get("FactoryId").toString());
 
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, Object> json = new HashMap<String, Object>();
		try {	
			if (result.hasErrors()) {
//				for (FieldError fieldError : result.getFieldErrors()) {
//					System.out.println(fieldError.getDefaultMessage());
//					System.out.println(fieldError.getField());
//					System.out.println(fieldError.getRejectedValue());
//				}
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Station station = new Station();
			//判断stationNumber是否重复（重复则不能新增，不重复则做新增操作）
			Integer count = stationService.getCountByStationNumber(creationStationDTO.getStationNumber());
			if(count==0){
				station.setStationNumber(creationStationDTO.getStationNumber());		
				station.setName(creationStationDTO.getName());
//				if (creationDateTime!=null&&!"".equals(creationDateTime)) {
//					station.setCreationDateTime(sdf.parse(creationDateTime));
//				}
				station.setCreationDateTime(new Date());
				station.setCreatorId(creationStationDTO.getCreatorId());
				station.setEditorId(creationStationDTO.getCreatorId());
				
				
				station.setEditDateTime(new Date());
				station.setStationType(creationStationDTO.getStationType());
				station.setState(creationStationDTO.getState());
//				station.setFactoryId(creationStationDTO.getFactoryId());
				station.setIpAddress(creationStationDTO.getStationIP());
				stationService.addStation(station);
				
//				Station_StationGroup station_StationGroup = new Station_StationGroup();
//				station_StationGroup.setEditorId(creationStationDTO.getCreatorId());
//				station_StationGroup.setEditDateTime(new Date());
//				station_StationGroup.setStationtId(station.getId());
//				if (creationStationDTO.getStationGroupIdArray()!=null && !creationStationDTO.getStationGroupIdArray().isEmpty()) {
//					for (Integer stationGroupId : creationStationDTO.getStationGroupIdArray()) {
//						station_StationGroup.setStationGroupId(stationGroupId);
//						stationGroupService.addStation_StationGroup(station_StationGroup);
//					}
//				}
				//添加资质表
				qualificationService.addQualificationAndStationAccessControl(station);
				json = this.setJson(200, "添加成功",0);    
			}else{
				json = this.setJson(0, "添加失败：工站编号不能重复！",-1);  
			}
		} catch (Exception e) {
			json = this.setJson(0, "添加失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 修改工站
	 * @param editStationDTO
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public @ResponseBody Map<String, Object> Put(@Valid @RequestBody EditStationDTO editStationDTO,BindingResult result) throws Exception{
//		JSONObject jsonObject = ReadDataUtil.paramData(request);
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			/*Integer id = jsonObject.get("Id") == null ? null : Integer.valueOf(jsonObject.get("Id").toString());
			String stationNumber = jsonObject.get("StationNumber") == null ? "" : String.valueOf(jsonObject.get("StationNumber"));
			String name = jsonObject.get("Name") == null ? "" : String.valueOf(jsonObject.get("Name"));
			//String editDateTime = jsonObject.get("EditDateTime") == null ? "" : String.valueOf(jsonObject.get("EditDateTime"));
			Integer editorId = jsonObject.get("EditorId") == null ? null : Integer.valueOf(jsonObject.get("EditorId").toString());
			Integer stationType = jsonObject.get("StationType") == null ? null : Integer.valueOf(jsonObject.get("StationType").toString());
			Integer state = jsonObject.get("State") == null ? null : Integer.valueOf(jsonObject.get("State").toString());
			Integer factoryId = jsonObject.get("FactoryId") == null ? null : Integer.valueOf(jsonObject.get("FactoryId").toString());
			String stationGroupIdArray = jsonObject.get("StationGroupIdArray") == null ? "" : String.valueOf(jsonObject.get("StationGroupIdArray"));
			JSONArray StationGroupIdArray = JSONArray.parseArray(stationGroupIdArray);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			Date editDateTime_val = null;
//			if (editDateTime!=null && !"".equals(editDateTime)) {
//				editDateTime_val = sdf.parse(editDateTime);
//			}

			Station station = stationService.getStationById(id);
			station.setStationNumber(stationNumber);
			station.setName(name);
//			station.setEditDateTime(editDateTime_val);
			station.setEditDateTime(new Date());
			station.setEditorId(editorId);
			station.setStationType(stationType);
			station.setState(state);
			station.setFactoryId(factoryId);

			List<Station_StationGroup> station_StationGroupList = stationService.getStation_StationGroupByStationId(id);			
			stationService.updateStation(station);

			for (int i = 0; i < station_StationGroupList.size(); i++) {
				Station_StationGroup station_StationGroup = station_StationGroupList.get(i);
				if (StationGroupIdArray!=null && !"".equals(StationGroupIdArray)) {
					for (int j = 0; j < StationGroupIdArray.size(); j++) {
						if (i==j) {
							station_StationGroup.setStationGroupId(Integer.parseInt(StationGroupIdArray.get(j).toString()));		
							station_StationGroup.setEditDateTime(new Date());
							stationService.updateStation_StationGroup(station_StationGroup);
						}				
					}
				}				
			}	*/				
//			List<Station_StationGroup> station_StationGroupList = stationService.getStation_StationGroupByStationId(editStationDTO.getId());
//			if (station_StationGroupList!=null && !station_StationGroupList.isEmpty()) {
//				for (Station_StationGroup station_StationGroup : station_StationGroupList) {
//					stationService.deleteStation_StationGroup(station_StationGroup);
//				}
//			}
			
//			Station_StationGroup station_StationGroup = new Station_StationGroup();
//			station_StationGroup.setEditorId(editStationDTO.getEditorId());
//			station_StationGroup.setEditDateTime(new Date());
//			station_StationGroup.setStationtId(editStationDTO.getId());
//			if (editStationDTO.getStationGroupIdArray()!=null && !editStationDTO.getStationGroupIdArray().isEmpty()) {
//				for (Integer stationGroupId : editStationDTO.getStationGroupIdArray()) {
//					station_StationGroup.setStationGroupId(stationGroupId);
//					stationGroupService.addStation_StationGroup(station_StationGroup);
//				}
//			}
			
			Station station = stationService.getStationById(editStationDTO.getId());
			Integer count = stationService.getCountByStationNumber(editStationDTO.getStationNumber());
			if (count==0 || editStationDTO.getStationNumber().equals(station.getStationNumber())) {
				station.setStationNumber(editStationDTO.getStationNumber());
				station.setName(editStationDTO.getName());
				station.setEditDateTime(new Date());
				station.setEditorId(editStationDTO.getEditorId());
				station.setStationType(editStationDTO.getStationType());
				station.setState(editStationDTO.getState());
//				station.setFactoryId(editStationDTO.getFactoryId());
				station.setIpAddress(editStationDTO.getStationIP());
				stationService.updateStation(station);
				json = this.setJson(200, "修改成功",0);    
			}else{
				json = this.setJson(0, "工站编号不能重复！", -1);    
			}
			qualificationService.updateQualificationAndStationAccessControl(station);
		} catch (Exception e) {
			json = this.setJson(0, "修改失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;	
	}

	/**
	 * 根据ID删除工站
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteStation(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		JSONArray idArray = jsonObject.getJSONArray("List");		
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			//			Station station = new Station();
			//			station.setId(id);
//			Station station = stationService.getStationById(id);
//			if (station!=null) {
//				stationService.deleteStation(station);
//				json = this.setJson(200, "删除成功",1);    
//			}else{
//				json = this.setJson(0, "删除失败：该id在数据库中没有对应记录！",-1);
//			}	
			
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
			e.printStackTrace();
		}
		return json;	
	}
}
