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

import com.smartflow.service.CellService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CreateStationGroupDTO;
import com.smartflow.dto.CreationStationDTO;
import com.smartflow.dto.InputStationGroupViewModel;
import com.smartflow.dto.StationGroupListDTO;
import com.smartflow.dto.TDto;
import com.smartflow.model.Station;
import com.smartflow.model.StationGroup;
import com.smartflow.model.Station_StationGroup;
import com.smartflow.service.StationGroupService;
import com.smartflow.service.StationService;
import com.smartflow.util.ReadDataUtil;

@Controller
@RequestMapping("/api/StationGroup")
public class StationGroupController extends BaseController{
	private static Logger logger = Logger.getLogger(StationController.class);
	@Autowired
	StationService stationService;

	@Autowired
	StationGroupService stationGroupService;

	@Autowired
	CellService cellService;
	/**
	 * 多条件分页查询工站组
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getStationGroup(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		//TDto tDto = (TDto) jsonObject.getObject("TDto", TDto.class);
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageIndex = jsonObject.get("PageIndex") == null ? null : Integer.parseInt(jsonObject.get("PageIndex").toString());
		Integer pageSize = jsonObject.get("PageSize") == null ? null : Integer.parseInt(jsonObject.get("PageSize").toString());
		String groupNumber = jsonObject.getString("GroupNumber") == null ? null : jsonObject.getString("GroupNumber");
		String description = jsonObject.getString("Description") == null ? null : jsonObject.getString("Description");
		Integer count = stationGroupService.getTotalCount(groupNumber, description);
		List<StationGroup> stationGroupList = null;
		List<StationGroupListDTO> Tdto = new ArrayList<>();
		try {			
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
				stationGroupList = stationGroupService.getStationGroupList(pageIndex, pageSize,groupNumber, description);
			}

			if (stationGroupList!=null && !stationGroupList.isEmpty()) {
				for (StationGroup stationGroup : stationGroupList) {
					StationGroupListDTO stationGroupListDTO = new StationGroupListDTO();
					stationGroupListDTO.setId(stationGroup.getId());
					stationGroupListDTO.setGroupNumber(stationGroup.getGroupNumber());
					stationGroupListDTO.setDescription(stationGroup.getDescription());
					stationGroupListDTO.setCreateDateTime(stationGroup.getCreateDateTime());
					stationGroupListDTO.setEditDateTime(stationGroup.getEditDateTime());
//					if (stationGroup.getFactoryId()!=null) {
//						String factory = stationGroupService.getFactoryNameById(stationGroup.getFactoryId());
//						stationGroupListDTO.setFactory(factory);
//					}

					String state = null;
					if (stationGroup.getState()==1) {
						state = "激活";
					}else if (stationGroup.getState()==0) {
						state = "未激活";
					}else{
						state = "已删除";
					}
					stationGroupListDTO.setState(state);
					String creator = stationService.getUserNameById(stationGroup.getCreatorId());
					stationGroupListDTO.setCreator(creator);
					if (stationGroup.getEditorId()!=null) {
						String editor = stationService.getUserNameById(stationGroup.getEditorId());
						stationGroupListDTO.setEditor(editor);
					}	
					//已选择的工站
					List<String> selectStationName = stationGroupService.getStationNameByStationGroupId(stationGroup.getId());
					stationGroupListDTO.setSelectedStation(StringUtils.collectionToDelimitedString(selectStationName, ","));
					stationGroupListDTO.setCell(stationGroup.getCell() == null ? null : stationGroup.getCell().getDescription());
					stationGroupListDTO.setSafetyStock(stationGroup.getSafetyStock());
					Tdto.add(stationGroupListDTO);
				}	
			}

			map.put("RowCount", count);
			map.put("Tdto", Tdto);
			json = this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据ID查询工站组详细
	 * @param Id
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
			StationGroup stationGroup = stationGroupService.getStationGroupById(Id);
			StationGroupListDTO stationGroupListDTO = new StationGroupListDTO();
			if (stationGroup!=null) {				
				stationGroupListDTO.setId(stationGroup.getId());
				stationGroupListDTO.setGroupNumber(stationGroup.getGroupNumber());
				stationGroupListDTO.setDescription(stationGroup.getDescription());
				stationGroupListDTO.setCreationDateTime(stationGroup.getCreateDateTime());
				stationGroupListDTO.setEditDateTime(stationGroup.getEditDateTime());
//				if (stationGroup.getFactoryId()!=null) {
//					String factory = stationGroupService.getFactoryNameById(stationGroup.getFactoryId());
//					stationGroupListDTO.setFactory(factory);
//				}
				String state = null;
				if (stationGroup.getState()==1) {
					state = "激活";
				}else if (stationGroup.getState()==0) {
					state = "未激活";
				}else{
					state = "已删除";
				}
				stationGroupListDTO.setState(state);
				String creator = stationService.getUserNameById(stationGroup.getCreatorId());				
				stationGroupListDTO.setCreator(creator);
				if (stationGroup.getEditorId()!=null) {
					String editor = stationService.getUserNameById(stationGroup.getEditorId());
					stationGroupListDTO.setEditor(editor);
				}	
				//已选择的工站
				List<String> selectStationName = stationGroupService.getStationNameByStationGroupId(stationGroup.getId());
				stationGroupListDTO.setSelectedStation(StringUtils.collectionToDelimitedString(selectStationName, ","));
				stationGroupListDTO.setCell(stationGroup.getCell() == null ? null : stationGroup.getCell().getDescription());
				stationGroupListDTO.setSafetyStock(stationGroup.getSafetyStock());
			}

			json = this.setJson(200, "查询成功",stationGroupListDTO);
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
			List<Map<String, Object>> factoryList = stationService.getFactory();
			List<Map<String, Object>> stationGroup = stationService.getStationGroup();
			List<Map<String, Object>> stationList = stationGroupService.getStation();
			List<Map<String, Object>> cellList = cellService.getCellListInit();
			map.put("Factory", factoryList);
			map.put("StationGroup", stationGroup);
			map.put("SelectedStation", null);
			map.put("TotalStation", stationList);
			map.put("CellList", cellList);
			json = this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 修改初始化和根据ID查询工站组
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
			List<Map<String, Object>> Factory = stationGroupService.getFactory(); 
			List<Map<String, Object>> SelectedStation = stationGroupService.getStationByStationGroupById(Id);
			List<Map<String, Object>> TotalStation = stationGroupService.getStation();
			List<Map<String, Object>> cellList = cellService.getCellListInit();
			StationGroup StationGroup = stationGroupService.getStationGroupById(Id);
			StationGroupListDTO stationGroupListDTO = null;
			if (StationGroup!=null) {
				stationGroupListDTO = new StationGroupListDTO();
				stationGroupListDTO.setId(StationGroup.getId());
				stationGroupListDTO.setGroupNumber(StationGroup.getGroupNumber());
				stationGroupListDTO.setDescription(StationGroup.getDescription());
				stationGroupListDTO.setCreateDateTime(StationGroup.getCreateDateTime());
				stationGroupListDTO.setEditDateTime(StationGroup.getEditDateTime());
//				stationGroupListDTO.setFactoryId(StationGroup.getFactoryId()==null?"":StationGroup.getFactoryId().toString());
				stationGroupListDTO.setState(StationGroup.getState().toString());
				String creator = stationService.getUserNameById(StationGroup.getCreatorId());				
				stationGroupListDTO.setCreator(creator);
				if (StationGroup.getEditorId()!=null) {
					String editor = stationService.getUserNameById(StationGroup.getEditorId());
					stationGroupListDTO.setEditor(editor);
				}
				stationGroupListDTO.setCell(StationGroup.getCell() == null ? null : StationGroup.getCell().getId().toString());
				stationGroupListDTO.setSafetyStock(StationGroup.getSafetyStock());
			}
			map.put("Factory", Factory);
			map.put("SelectedStation", SelectedStation);
			map.put("TotalStation", TotalStation);
			map.put("StationGroup", stationGroupListDTO);
			map.put("CellList", cellList);
			json = this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, "查询失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 添加工站组
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> Post(@Valid @RequestBody CreateStationGroupDTO createStationGroupDTO, HttpServletRequest request,HttpServletResponse response,BindingResult result) throws Exception{
		Map<String, Object> json = new HashMap<String, Object>();
//		JSONObject jsonObject = ReadDataUtil.paramData(request);
		//Integer id = jsonObject.get("Id") == null ? null : Integer.valueOf(jsonObject.get("Id").toString());
		//String groupNumber = jsonObject.get("GroupNumber") == null ? "" : String.valueOf(jsonObject.get("GroupNumber"));
		//String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
		//String createDateTime = jsonObject.get("CreateDateTime") == null ? "" : String.valueOf(jsonObject.get("CreateDateTime"));
		//Integer factoryId = jsonObject.get("FactoryId") == null ? null : Integer.valueOf(jsonObject.get("FactoryId").toString());
		//Integer state = jsonObject.get("State") == null ? null : Integer.valueOf(jsonObject.get("State").toString());
		//String stationIdArray = jsonObject.get("StationIdArray") == null ? "" : String.valueOf(jsonObject.get("StationIdArray"));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {	
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			StationGroup stationGroup = new StationGroup();
			Integer count = stationGroupService.getCountByGroupNumber(createStationGroupDTO.getGroupNumber());
			if (count==0) {
				stationGroup.setGroupNumber(createStationGroupDTO.getGroupNumber());
				stationGroup.setDescription(createStationGroupDTO.getDescription());
				//stationGroup.setCreateDateTime(sdf.parse(createDateTime));
				stationGroup.setCreateDateTime(new Date());
//				stationGroup.setFactoryId(createStationGroupDTO.getFactoryId());
				stationGroup.setState(createStationGroupDTO.getState());
				stationGroup.setCreatorId(createStationGroupDTO.getCreatorId());
				stationGroup.setEditDateTime(new Date());
				stationGroup.setEditorId(createStationGroupDTO.getCreatorId());
				stationGroup.setCell(cellService.getCellById(createStationGroupDTO.getCellId()));
				stationGroup.setSafetyStock(createStationGroupDTO.getSafetyStock());
				stationGroupService.addStationGroup(stationGroup);
				//JSONArray StationIdArray = JSONArray.parseArray(createStationGroupDTO.getStationIdArray());
				Station_StationGroup station_StationGroup = new Station_StationGroup();
				station_StationGroup.setStationGroupId(stationGroup.getId());
				station_StationGroup.setEditDateTime(new Date());
				station_StationGroup.setEditorId(createStationGroupDTO.getCreatorId());
				if (createStationGroupDTO.getStationIdArray()!=null && !createStationGroupDTO.getStationIdArray().isEmpty()) {
					for (Integer stationId : createStationGroupDTO.getStationIdArray()) {
						Station station = stationService.getStationById(stationId);
						if (station!=null) {
							station_StationGroup.setStationtId(stationId); 
							stationGroupService.addStation_StationGroup(station_StationGroup);
						}	
					}
				}
				json = this.setJson(200, "添加成功",0);
			}else{
				json = this.setJson(0, "添加失败：工作站组编号不能重复！",-1);  
			}			
		} catch (Exception e) {
			json = this.setJson(0, "添加失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 修改工站组
	 * @param inputStationGroupViewModel
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public @ResponseBody Map<String, Object> Put(@Valid @RequestBody InputStationGroupViewModel inputStationGroupViewModel,BindingResult result) throws Exception{
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
			String groupNumber = jsonObject.get("GroupNumber") == null ? "" : String.valueOf(jsonObject.get("GroupNumber"));
			String description = jsonObject.get("Description") == null ? "" : String.valueOf(jsonObject.get("Description"));
			Integer state = jsonObject.get("State") == null ? null : Integer.parseInt(jsonObject.get("State").toString());			
			String stationIdArray = jsonObject.get("StationIdArray") == null ? "" : String.valueOf(jsonObject.get("StationIdArray"));
			JSONArray StationIdArray = JSONArray.parseArray(stationIdArray);
			StationGroup stationGroup = stationGroupService.getStationGroupById(id);
			stationGroup.setGroupNumber(groupNumber);
			stationGroup.setDescription(description);
			stationGroup.setState(state);
			stationGroupService.updateStationGroup(stationGroup);

			List<Station_StationGroup> station_StationGroupList = stationGroupService.getStation_StationGroupByStationGroupId(id);

			for (int i = 0; i < station_StationGroupList.size(); i++) {			
				Station_StationGroup station_StationGroup = station_StationGroupList.get(i);
				for (int j = 0; j < StationIdArray.size(); j++) {	
					if (i == j) {
						Station station = stationService.getStationById(Integer.parseInt(StationIdArray.get(i).toString()));
						station_StationGroup.setEditDateTime(new Date());
						if (station!=null) {
							station_StationGroup.setStationtId(Integer.parseInt(StationIdArray.get(j).toString()));						
							stationGroupService.updateStation_StationGroup(station_StationGroup);  	
						}
					}								
				}
			}*/
			List<Station_StationGroup> station_StationGroupList = stationGroupService.getStation_StationGroupByStationGroupId(inputStationGroupViewModel.getId());
			for (Station_StationGroup station_StationGroup : station_StationGroupList) {
				stationService.deleteStation_StationGroup(station_StationGroup);
			}
			Station_StationGroup station_StationGroup = new Station_StationGroup();
			station_StationGroup.setStationGroupId(inputStationGroupViewModel.getId());
			station_StationGroup.setEditorId(inputStationGroupViewModel.getEditorId());
			station_StationGroup.setEditDateTime(new Date());
			if (inputStationGroupViewModel.getStationIdArray()!=null && !inputStationGroupViewModel.getStationIdArray().isEmpty()) {
				for (Integer stationId : inputStationGroupViewModel.getStationIdArray()) {
					station_StationGroup.setStationtId(stationId);
					stationGroupService.addStation_StationGroup(station_StationGroup);
				}
			}			

			StationGroup stationGroup = stationGroupService.getStationGroupById(inputStationGroupViewModel.getId());
			Integer count = stationGroupService.getCountByGroupNumber(inputStationGroupViewModel.getGroupNumber());
			if (count==0 || inputStationGroupViewModel.getGroupNumber().equals(stationGroup.getGroupNumber())) {
				stationGroup.setGroupNumber(inputStationGroupViewModel.getGroupNumber());
				stationGroup.setDescription(inputStationGroupViewModel.getDescription());
				stationGroup.setState(inputStationGroupViewModel.getState());
//				stationGroup.setFactoryId(inputStationGroupViewModel.getFactoryId());
				stationGroup.setEditorId(inputStationGroupViewModel.getEditorId());
				stationGroup.setEditDateTime(new Date());
				stationGroup.setCell(cellService.getCellById(inputStationGroupViewModel.getCellId()));
				stationGroup.setSafetyStock(inputStationGroupViewModel.getSafetyStock());
				stationGroupService.updateStationGroup(stationGroup);
				json = this.setJson(200, "修改成功",0);    
			}else{
				json = this.setJson(0, "修改失败：工作站编号不能重复！",-1);   
			}			 
		} catch (Exception e) {
			json = this.setJson(0, "修改失败："+e.getMessage(),-1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;	
	}

	/**
	 * 根据ID删除工站组
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteStation(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> json = new HashMap<String, Object>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		JSONArray idArray = jsonObject.getJSONArray("List");
		try {	
			//			List<Station_StationGroup> station_StationGroupList = stationGroupService.getStation_StationGroupByStationGroupId(id);
			//			if (station_StationGroupList!=null) {
			//				stationGroupService.deleteStation_StationGroupByStationGroupId(id);
			//			}			
			//			StationGroup stationGroup = stationGroupService.getStationGroupById(id);
			//			if (stationGroup!=null) {
			//				stationGroupService.deleteStationGroupById(stationGroup);
			//			}		
			if (idArray.size()!=0) {
				for (int i = 0; i < idArray.size(); i++) {
					Integer id = idArray.getInteger(i);
					StationGroup stationGroup = stationGroupService.getStationGroupById(id);
					stationGroup.setGroupNumber("Del@"+stationGroup.getGroupNumber());
					stationGroup.setState(-1);
					stationGroupService.updateStationGroup(stationGroup);					  
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
