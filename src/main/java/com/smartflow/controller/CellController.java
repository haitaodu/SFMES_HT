package com.smartflow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CellDTO;
import com.smartflow.dto.CreationCellDTO;
import com.smartflow.dto.EditCellDTO;
import com.smartflow.dto.EditCellForInitializeDTO;
import com.smartflow.model.Cell;
import com.smartflow.model.Cell_Station;
import com.smartflow.service.*;
import com.smartflow.util.ReadDataUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author haita
 */
@RestController
@RequestMapping("/api/Cell")
public class CellController extends BaseController{
	private static Logger logger = Logger.getLogger(CellController.class);

	private static final String FIND_SUCCESS="查询成功";

	private static final String FIND_FALL="查询失败:";

	private static final int FALL_CODE=0;

	private static final int SUCCESS_CODE=200;

	private static final int FALL_ENTITY=-1;
	private final
	CellService cellService;

	private final
	StationService stationService;

	private final
	LocationService locationService;

	private final
	AreaService areaService;


	@Autowired
	public CellController(StationService stationService,
						  StationGroupService stationGroupService,
						  LocationService locationService,
						  AreaService areaService,CellService cellService) {
		this.stationService = stationService;
		this.locationService = locationService;
		this.areaService = areaService;
		this.cellService = cellService;
	}

	/**
	 * 添加线体初始化加载所有工站
	 * @return 返回初始化得所有工站号
	 */
	@CrossOrigin(origins = "*",maxAge=3600)
	@GetMapping(value="/GetAddInitialize")
	public Map<String, Object> getAddInitialize(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> areaList = locationService.getAreaList();
			Map<String, Object> map = new HashMap<>();
			map.put("SelectedCell", null);
			map.put("AreaList", areaList);
			map.put("TDto", null);
			json = this.setJson(SUCCESS_CODE, FIND_SUCCESS, map);
		}catch(Exception e){
			json = this.setJson(FALL_CODE, FIND_FALL+e.getMessage(), FALL_ENTITY);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 编辑线体初始化加载所有工站和已选工站
	 * @param Id 详情描述初始化数据根据Id查找初始化数据
	 * @return 返回初始化的数据
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@GetMapping(value="/GetEditInitialize/{Id}")
	public Map<String, Object> getEditInitialize(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> areaList = locationService.getAreaList();
			Cell cell = cellService.getCellById(Id);	
			EditCellForInitializeDTO TDto = null;
			if(cell!=null){
				TDto = new EditCellForInitializeDTO();
				TDto.setId(cell.getId());
				TDto.setCellNumber(cell.getCellNumber());
				TDto.setDescription(cell.getDescription());
				TDto.setState(cell.getState().toString());
				TDto.setAreaId(cell.getAreaId()== null ? null : cell.getAreaId().toString());
			}			
			Map<String, Object> map = new HashMap<>();
			map.put("AreaList", areaList);
			map.put("TDto", TDto);
			json = this.setJson(200, FIND_SUCCESS, map);
		}catch(Exception e){
			json = this.setJson(0, FIND_FALL+e.getMessage(), -1);
			logger.error(e.getCause().getMessage());
		}
		return json;
	}

	/**
	 * 	多条件分页查询cell
	 * @param request 前端请求参数
	 * @param response 返回参数
	 * @return 返回列表信息
	 * @throws Exception 返回异常信息
	 */
	@CrossOrigin(origins = "*",maxAge=3600)
	@PostMapping (value="/GetTByCondition")
	public Map<String, Object> getTByCondition(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> json = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		List<CellDTO> Tdto = new ArrayList<CellDTO>();
		try{			
			Integer pageIndex = jsonObject.getInteger("PageIndex")==null?null:Integer.parseInt(jsonObject.getInteger("PageIndex").toString());
			Integer pageSize = jsonObject.getInteger("PageSize")==null?null:Integer.parseInt(jsonObject.getInteger("PageSize").toString());
			String cellNumber = jsonObject.getString("CellNumber");
			String description = jsonObject.getString("Description");
			
			//查询Cell总条数
			Integer count = cellService.getTotalCountFromCell(cellNumber, description);
			if (pageIndex==null) {
				pageIndex = 1;
			}
			if (pageIndex==0) {
				map.put("RowCount", 0);
				map.put("Tdto", Tdto);			
				json = this.setJson(200, "无数据", map);
				return json;
			}
			List<Cell> cellList = cellService.getCellByCondition(pageIndex, pageSize, cellNumber, description);	
		
			String state = "";
			if (cellList!=null && !cellList.isEmpty()) {
				for (Cell cell : cellList) {
					CellDTO cellDTO = new CellDTO();
					cellDTO.setId(cell.getId());
					cellDTO.setCellNumber(cell.getCellNumber());
					cellDTO.setDescription(cell.getDescription());
					cellDTO.setCreator(stationService.getUserNameById(cell.getCreatorId()));
					cellDTO.setCreationDateTime(cell.getCreationDateTime());
					cellDTO.setEditorId(stationService.getUserNameById(cell.getEditorId()));
					cellDTO.setEditDateTime(cell.getEditDateTime());
					cellDTO.setArea(cell.getAreaId() == null ? null : areaService.getDataById(cell.getAreaId()).getName());
					if (cell.getState()==1) {
						state = "激活";
					}else if(cell.getState()==0){//-1已删除
						state = "未激活";
					}
					cellDTO.setState(state);
				Tdto.add(cellDTO);
				}
			}			
			map.put("RowCount", count);
			map.put("Tdto", Tdto);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, FIND_FALL+e.getMessage(), -1);
			logger.error(e);
		}
		return json;	
	}

	/**
	 * 根据ID查询cell详细
	 * @param Id 岛区Id
	 * @return 返回详细信息
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@GetMapping(value="/GetTById/{Id}")
	public Map<String, Object> getTById(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			Cell cell = cellService.getCellById(Id);
			CellDTO cellDTO = new CellDTO();	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
			cellDTO.setId(cell.getId());
			cellDTO.setCellNumber(cell.getCellNumber());
			cellDTO.setDescription(cell.getDescription());
			cellDTO.setCreator(stationService.getUserNameById(cell.getCreatorId()));
			cellDTO.setCreationDateTime(cell.getCreationDateTime());
			cellDTO.setEditor(stationService.getUserNameById(cell.getEditorId()));
			cellDTO.setEditDateTime(cell.getEditDateTime());
			cellDTO.setArea(cell.getAreaId() == null ? null : areaService.getDataById(cell.getAreaId()).getName());
			String state = "";
			if (cell.getState()==1) {
				state = "激活";
			}else if(cell.getState()==0){//-1已删除
				state = "未激活";
			}
			cellDTO.setState(state);
			json = this.setJson(200, "查询成功！", cellDTO);
		}catch(Exception e){
			json = this.setJson(0, FIND_FALL+e.getMessage(), -1);
			logger.error(e);
		}	
		return json;	
	}

	/**
	 * 添加线体
	 * @return 返回添加结果
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@PostMapping(value="/Post")
	public Map<String, Object> post(@Valid @RequestBody CreationCellDTO creationCellDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Cell cell = new Cell();
			Integer count = cellService.getCountByCellNumber(creationCellDTO.getCellNumber());
			if (count==0) {
				cell.setCellNumber(creationCellDTO.getCellNumber());
				cell.setDescription(creationCellDTO.getDescription());
				cell.setState(creationCellDTO.getState());
				cell.setCreationDateTime(new Date());
				cell.setCreatorId(creationCellDTO.getCreatorId());	
				cell.setEditorId(creationCellDTO.getCreatorId());
				cell.setEditDateTime(new Date());
				cell.setAreaId(creationCellDTO.getAreaId());
				cellService.addCell(cell);
				Cell_Station cell_Station = new Cell_Station();
				cell_Station.setCellId(cell.getId());
				cell_Station.setEditorId(creationCellDTO.getCreatorId());
				cell_Station.setEditDateTime(new Date());
				json = this.setJson(200, "添加成功！", 0);
			}else{
				json = this.setJson(0, "添加失败:线体编号不能重复！", -1);
			}					
		}catch(Exception e){
			json = this.setJson(0, "添加失败："+e.getMessage(), -1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 修改线体
	 * @param editCellDTO 编辑数据
	 * @return 返回编辑结果
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@PutMapping(value="/Put")
	public Map<String, Object> put(@Valid @RequestBody EditCellDTO editCellDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			List<Cell_Station> cell_StationList = cellService.getCell_StationByCellId(editCellDTO.getId());
			if (cell_StationList!=null && !cell_StationList.isEmpty()) {
				for (Cell_Station cell_Station : cell_StationList) {
					cellService.deleteCell_Station(cell_Station);
				}
			}			
			Cell_Station cell_Station = new Cell_Station();
			cell_Station.setCellId(editCellDTO.getId());
			cell_Station.setEditorId(editCellDTO.getEditorId());
			cell_Station.setEditDateTime(new Date());
			Cell cell = cellService.getCellById(editCellDTO.getId());
			Integer count = cellService.getCountByCellNumber(editCellDTO.getCellNumber());
			if(count==0 || editCellDTO.getCellNumber().equals(cell.getCellNumber())){
				cell.setId(editCellDTO.getId());
				cell.setCellNumber(editCellDTO.getCellNumber());
				cell.setDescription(editCellDTO.getDescription());
				cell.setEditDateTime(new Date());
				cell.setEditorId(editCellDTO.getEditorId());
				cell.setState(editCellDTO.getState());
//				cell.setCreatorId(editCellDTO.getEditorId());
//				cell.setCreationDateTime(new Date());
				cell.setAreaId(editCellDTO.getAreaId());
				cellService.updateCell(cell);
				json = this.setJson(200, "修改成功！", 0);
			}else{
				json = this.setJson(0, "修改失败：线体编号不能重复！", -1);
			}
		}catch(Exception e){
			json = this.setJson(0, "修改失败:"+e.getMessage(), -1);
			logger.error(e);
		}
		return json;
	}

	/**
	 * 根据线体ID删除线体
	 * @return 返回结果状态
	 * @throws Exception 删除失败的异常
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@PostMapping (value="/Delete")
	public Map<String, Object> deleteCell(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		JSONArray idArray = jsonObject.getJSONArray("List");
		Map<String, Object> json = new HashMap<>();
		try{
			if (!idArray.isEmpty()) {
				for (int i = 0; i < idArray.size(); i++) {
					Integer id = idArray.getInteger(i);
					Cell cell = cellService.getCellById(id);
					cell.setCellNumber("Del@"+cell.getCellNumber());
					cell.setState(-1);
					cellService.updateCell(cell);					
				}
				json = this.setJson(200, "删除成功！", 0);
			}else{
				json = this.setJson(0, "删除失败:请选择要删除的数据！", -1);
			}
		}catch(Exception e){
			json = this.setJson(0, "删除失败："+e.getMessage(), -1);
			logger.error(e);
		}
		return json;
	}
} 
