package com.smartflow.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CreationMaterialDTO;
import com.smartflow.dto.EditMaterialDTO;
import com.smartflow.dto.EditMaterialForEditRetrunInitialization;
import com.smartflow.dto.GetDTOByConditionOfMaterialListDTO;
import com.smartflow.dto.MaterialListDTO;
import com.smartflow.model.Material;
import com.smartflow.service.MaterialService;
import com.smartflow.service.StationGroupService;
import com.smartflow.service.StationService;
import com.smartflow.util.ReadDataUtil;

/**
 * @author haita
 */
@RestController
@RequestMapping("/api/Material")
public class MaterialController extends BaseController{
	private static final Logger logger = Logger.getLogger(MaterialController.class);
	
	@Autowired
	MaterialService materialService;
	
	@Autowired
	StationService stationService;
	
	@Autowired
	StationGroupService stationGroupService;
	
	/**
	 * 分页查询初始化物料类型、工厂下拉框
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTByConditionInit",method=RequestMethod.GET)
	public Map<String, Object> getTByConditionInit(){
		Map<String, Object> json = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		try{
			List<Map<String, Object>> MaterialGroupType = materialService.getMaterialTypeNameAndId();
			List<Map<String, Object>> Factory = stationService.getFactory();
			map.put("MaterialGroupType", MaterialGroupType);
			map.put("Factory", Factory);
			json = this.setJson(200, "初始化成功", map);
		}catch(Exception e){
			json = this.setJson(0, "初始化数据失败:"+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 多条件分页查询物料
	 * @param getDTOByConditionOfMaterialListDTO
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTByCondition",method=RequestMethod.POST)
	public Map<String, Object> getMaterialByCondition(@Valid @RequestBody GetDTOByConditionOfMaterialListDTO getDTOByConditionOfMaterialListDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<MaterialListDTO> Tdto = new ArrayList<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			if (getDTOByConditionOfMaterialListDTO.getPageIndex()==null) {
				getDTOByConditionOfMaterialListDTO.setPageIndex(1);
			}
			if (getDTOByConditionOfMaterialListDTO.getPageIndex()==0) {
				map.put("RowCount", 0);
				map.put("Tdto", Tdto);			
				json = this.setJson(200, "无数据", map);
				return json;
			}
			Integer RowCount = materialService.getMaterialTotalCount(getDTOByConditionOfMaterialListDTO);
			List<Material> materialList = materialService.getMaterialByCondition(getDTOByConditionOfMaterialListDTO);
			for (Material material : materialList) {
				MaterialListDTO materialListDTO = new MaterialListDTO();
//				if (material.getCompanyId()!=null) {
//					materialListDTO.setCompanyNumber(materialService.getCompanyNumberByCompanyId(material.getCompanyId()));
//				}
//				materialListDTO.setContainerSize(material.getContainerSize());
				materialListDTO.setCreationDateTime(material.getCreationDateTime());
				if (material.getCreatorId()!=null) {
					materialListDTO.setCreator(stationService.getUserNameById(material.getCreatorId()));
				}
				materialListDTO.setCustomerMaterialNumber(material.getCustomerMaterialNumber());
//				if (material.getDefaultStationGroupId()!=null) {
//					materialListDTO.setDefaultStationGroup(materialService.getStationGroupNumberByStationGroupId(material.getDefaultStationGroupId()));
//				}
//				if (material.getDefaultStorageLocationId()!=null) {
//					materialListDTO.setDefaultStorageLocation(materialService.getLocationNumberByLocationId(material.getDefaultStorageLocationId()));
//				}
//				if (material.getDefaultTargetFeedingLocationId()!=null) {
//					materialListDTO.setDefaultTargetFeedingLocation(materialService.getLocationNumberByLocationId(material.getDefaultTargetFeedingLocationId()));
//				}
				materialListDTO.setDescription(material.getDescription());
				materialListDTO.setEditDateTime(material.getEditDateTime());
				if (material.getEditorId()!=null) {
					materialListDTO.setEditor(stationService.getUserNameById(material.getEditorId()));
				}				
				materialListDTO.setExpirationTime(material.getExpirationTime());
				materialListDTO.setFactoryNumber(stationGroupService.getFactoryNameById(material.getFactoryId()));
				materialListDTO.setId(material.getId());
//				String isMultiPanel = null;
//				if (material.getIsMultiPanel()==true) {
//					isMultiPanel = "True";
//				}else if(material.getIsMultiPanel()==false){
//					isMultiPanel = "False";
//				}
//				materialListDTO.setIsMultiPanel(isMultiPanel);
//				String isProduct = null;
//				if(material.getIsProduct()==true)
//					isProduct = "是";
//				else if(material.getIsProduct()==false)
//					isProduct = "否";
//				materialListDTO.setIsProduct(isProduct);
				if (material.getMaterialGroupType()!=null) {
					materialListDTO.setMaterialGroupType(materialService.getMaterialGroupTypeNameByMaterialGroupTypeId(material.getMaterialGroupType()));
				}				
				materialListDTO.setMaterialNumber(material.getMaterialNumber());
				materialListDTO.setMinimumPackageQuantity(material.getMinimumPackageQuantity());
//				materialListDTO.setMSL(material.getMSL()==null ? null : material.getMSL().toString());
//				materialListDTO.setNumberOfPanels(material.getNumberOfPanels());
//				if (material.getProcurementType()!=null) {
//					materialListDTO.setProcurementType(materialService.getProcurementTypeNameByProcurementTypeId(material.getProcurementType()));
//				}
//				String requireBackflush = null;
//				if(material.getRequireBackflush()==true)
//					requireBackflush = "是";
//				else if(material.getRequireBackflush()==false)
//					requireBackflush = "否";
//				materialListDTO.setRequireBackflush(requireBackflush);
				materialListDTO.setSafetyStock(material.getSafetyStock());
				String setupFlag = null;
				if(material.getSetupFlag()==true)
					setupFlag = "是";
				else if(material.getSetupFlag()==false)
					setupFlag = "否";
				materialListDTO.setSetupFlag(setupFlag);
//				materialListDTO.setSpecification(material.getSpecification());
				String state = null;
				if (material.getState()==1) {
					state = "激活";
				}else if(material.getState()==0){
					state = "未激活";
				}
				materialListDTO.setStateValue(state);
				materialListDTO.setSupplierMaterialNumber(material.getSupplierMaterialNumber());
				materialListDTO.setUnit(materialService.getUnitNameByUnitId(material.getUnit()));
				materialListDTO.setValidBegin(material.getValidBegin());
				materialListDTO.setValidEnd(material.getValidEnd());
//				materialListDTO.setVersion(material.getVersion());
				String requireFIFO = null;
				if (material.getRequireFIFO()!=null) {
					if (material.getRequireFIFO()==true)
						requireFIFO = "是";
					else if (material.getRequireFIFO()==false)
						requireFIFO = "否";
				}				
				materialListDTO.setRequireFIFO(requireFIFO);
				String requireCheckCustomerLabel = null;
				if (material.getRequireCheckCustomerLabel()!=null) {
					if (material.getRequireCheckCustomerLabel()==true) 
						requireCheckCustomerLabel = "是";
					else if (material.getRequireCheckCustomerLabel()==false) 
						requireCheckCustomerLabel = "否";
				}				
				materialListDTO.setRequireCheckCustomerLabel(requireCheckCustomerLabel);
				Tdto.add(materialListDTO);
			}
			map.put("RowCount", RowCount);
			map.put("Tdto", Tdto);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();			
		}
		return json;
	}
	
	/**
	 * 根据ID查询物料详细
	 * @param Id
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetTById/{Id}",method=RequestMethod.GET)
	public Map<String, Object> getMaterialById(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			Material material = materialService.getMaterialById(Id);
			MaterialListDTO materialListDTO = new MaterialListDTO();
			if (material!=null) {				
//				if (material.getCompanyId()!=null) {
//					materialListDTO.setCompanyNumber(materialService.getCompanyNumberByCompanyId(material.getCompanyId()));
//				}
//				materialListDTO.setContainerSize(material.getContainerSize());
				materialListDTO.setCreationDateTime(material.getCreationDateTime());
				if (material.getCreatorId()!=null) {
					materialListDTO.setCreator(stationService.getUserNameById(material.getCreatorId()));
				}
				materialListDTO.setCustomerMaterialNumber(material.getCustomerMaterialNumber());
//				if (material.getDefaultStationGroupId()!=null) {
//					materialListDTO.setDefaultStationGroup(materialService.getStationGroupNumberByStationGroupId(material.getDefaultStationGroupId()));
//				}
//				if (material.getDefaultStorageLocationId()!=null) {
//					materialListDTO.setDefaultStorageLocation(materialService.getLocationNumberByLocationId(material.getDefaultStorageLocationId()));
//				}
//				if (material.getDefaultTargetFeedingLocationId()!=null) {
//					materialListDTO.setDefaultTargetFeedingLocation(materialService.getLocationNumberByLocationId(material.getDefaultTargetFeedingLocationId()));
//				}//	默认回冲库位
				materialListDTO.setDescription(material.getDescription());
				materialListDTO.setEditDateTime(material.getEditDateTime());
				if (material.getEditorId()!=null) {
					materialListDTO.setEditor(stationService.getUserNameById(material.getEditorId()));
				}
				materialListDTO.setExpirationTime(material.getExpirationTime());
				materialListDTO.setFactoryNumber(stationGroupService.getFactoryNameById(material.getFactoryId()));
				materialListDTO.setId(material.getId());
//				String isMultiPanel = null;
//				if (material.getIsMultiPanel()==true) {
//					isMultiPanel = "True";
//				}else if(material.getIsMultiPanel()==false){
//					isMultiPanel = "False";
//				}
//				materialListDTO.setIsMultiPanel(isMultiPanel);
//				String isProduct = null;
//				if(material.getIsProduct()==true)
//					isProduct = "True";
//				else if(material.getIsProduct()==false)
//					isProduct = "False";
//				materialListDTO.setIsProduct(isProduct);
				if (material.getMaterialGroupType()!=null) {
					materialListDTO.setMaterialGroupType(materialService.getMaterialGroupTypeNameByMaterialGroupTypeId(material.getMaterialGroupType()));
				}
				materialListDTO.setMaterialNumber(material.getMaterialNumber());
				materialListDTO.setMinimumPackageQuantity(material.getMinimumPackageQuantity());
//				if(material.getMSL()!=null){
//					materialListDTO.setMSL(materialService.getMSLNameByMSLId(material.getMSL()));
//				}
//				materialListDTO.setNumberOfPanels(material.getNumberOfPanels());
//				if (material.getProcurementType()!=null) {
//					materialListDTO.setProcurementType(materialService.getProcurementTypeNameByProcurementTypeId(material.getProcurementType()));
//				}
//				String requireBackflush = null;
//				if(material.getRequireBackflush()==true)
//					requireBackflush = "True";
//				else if(material.getRequireBackflush()==false)
//					requireBackflush = "False";
//				materialListDTO.setRequireBackflush(requireBackflush);
				materialListDTO.setSafetyStock(material.getSafetyStock());
				String setupFlag = null;
				if(material.getSetupFlag()==true)
					setupFlag = "True";
				else if(material.getSetupFlag()==false)
					setupFlag = "False";
				materialListDTO.setSetupFlag(setupFlag);
//				materialListDTO.setSpecification(material.getSpecification());
				String state = null;
				if (material.getState()==1) {
					state = "激活";
				}else if(material.getState()==0){
					state = "未激活";
				}
				materialListDTO.setStateValue(state);
				materialListDTO.setSupplierMaterialNumber(material.getSupplierMaterialNumber());
				materialListDTO.setUnit(materialService.getUnitNameByUnitId(material.getUnit()));
				materialListDTO.setValidBegin(material.getValidBegin());
				materialListDTO.setValidEnd(material.getValidEnd());
//				materialListDTO.setVersion(material.getVersion());
				String requireFIFO = null;
				if (material.getRequireFIFO()!=null) {
					if (material.getRequireFIFO()==true) 
						requireFIFO = "True";
					else if (material.getRequireFIFO()==false) 
						requireFIFO = "False";
				}				
				materialListDTO.setRequireFIFO(requireFIFO);
				
				String requireCheckCustomerLabel = null;
				if (material.getRequireCheckCustomerLabel()!=null) {
					if (material.getRequireCheckCustomerLabel()==true)
						requireCheckCustomerLabel = "True";
					else if(material.getRequireCheckCustomerLabel()==false)
						requireCheckCustomerLabel = "False";
				}
				materialListDTO.setRequireCheckCustomerLabel(requireCheckCustomerLabel);
			}
			json = this.setJson(200, "查询成功！", materialListDTO);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 添加初始化物料所需数据
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetAddInitialize",method=RequestMethod.GET)
	public Map<String, Object> getAddInitialize(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> MaterialType = materialService.getMaterialTypeNameAndId();
			List<Map<String, Object>> Unit = materialService.getUnitNameAndId();
//			List<Map<String, Object>> ProcurementType = materialService.getProcurementTypeNameAndId();
			List<Map<String, Object>> Location = materialService.getLocationNumberAndId();
//			List<Map<String, Object>> MSL = materialService.getMSLNameAndId();
//			List<Map<String, Object>> StationGroup = materialService.getStationGroupNumberAndId();
//			List<Map<String, Object>> Company = materialService.getCompanyNameAndId();
			List<Map<String, Object>> Factory = stationService.getFactory();
			EditMaterialForEditRetrunInitialization TDto = null;
			Map<String, Object> map = new HashMap<>();
			map.put("MaterialType", MaterialType);
			map.put("Unit", Unit);
//			map.put("ProcurementType", ProcurementType);
			map.put("Location", Location);
//			map.put("MSL", MSL);
//			map.put("StationGroup", StationGroup);
//			map.put("Company", Company);
			map.put("Factory", Factory);	
			map.put("TDto", TDto);	
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	   
	/**
	 * 修改初始化
	 * @param Id
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
	public Map<String, Object> getEditInitialize(@PathVariable Integer Id){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> MaterialType = materialService.getMaterialTypeNameAndId();
			List<Map<String, Object>> Unit = materialService.getUnitNameAndId();
//			List<Map<String, Object>> ProcurementType = materialService.getProcurementTypeNameAndId();
			List<Map<String, Object>> Location = materialService.getLocationNumberAndId();
//			List<Map<String, Object>> MSL = materialService.getMSLNameAndId();
//			List<Map<String, Object>> StationGroup = materialService.getStationGroupNumberAndId();
//			List<Map<String, Object>> Company = materialService.getCompanyNameAndId();
			List<Map<String, Object>> Factory = stationService.getFactory();
			Material material = materialService.getMaterialById(Id);
			EditMaterialForEditRetrunInitialization TDto = null;
			if (material!=null) {
				TDto = new EditMaterialForEditRetrunInitialization();
//				TDto.setCompanyId(material.getCompanyId() == null ? "" : material.getCompanyId().toString());
//				TDto.setContainerSize(material.getContainerSize());
				TDto.setCustomerMaterialNumber(material.getCustomerMaterialNumber());
//				TDto.setDefaultStationGroupId(material.getDefaultStationGroupId() == null ? "" : material.getDefaultStationGroupId().toString());
//				TDto.setDefaultStorageLocationId(material.getDefaultStorageLocationId()==null?"":material.getDefaultStorageLocationId().toString());
				//默认存储库位
//				TDto.setDefaultTargetFeedingLocationId(material.getDefaultTargetFeedingLocationId()==null?"":material.getDefaultTargetFeedingLocationId().toString());
				TDto.setDescription(material.getDescription());
				TDto.setExpirationTime(material.getExpirationTime());
				TDto.setFactoryId(material.getFactoryId().toString());
				TDto.setId(material.getId());
//				TDto.setIsMultiPanel(material.getIsMultiPanel());
//				TDto.setIsProduct(material.getIsProduct());
				TDto.setMaterialGroupTypeId(material.getMaterialGroupType() == null ? "" : material.getMaterialGroupType().toString());
				TDto.setMaterialNumber(material.getMaterialNumber());
				TDto.setMinimumPackageQuantity(material.getMinimumPackageQuantity());
//				TDto.setMSLId(material.getMSL() == null ? "" : material.getMSL().toString());
//				TDto.setNumberOfPanels(material.getNumberOfPanels());
//				TDto.setProcurementTypeId(material.getProcurementType() == null ? "" : material.getProcurementType().toString());
//				TDto.setRequireBackflush(material.getRequireBackflush());
				TDto.setSafetyStock(material.getSafetyStock());
				TDto.setSetupFlag(material.getSetupFlag());
//				TDto.setSpecification(material.getSpecification());
				TDto.setState(material.getState().toString());
				TDto.setSupplierMaterialNumber(material.getSupplierMaterialNumber());
				TDto.setUnitId(material.getUnit());
				TDto.setValidBegin(material.getValidBegin());
				TDto.setValidEnd(material.getValidEnd());
//				TDto.setVersion(material.getVersion());
				if (material.getRequireFIFO()!=null) {
					TDto.setRequireFIFO(material.getRequireFIFO());//是否需要先进先出
				}
				if (material.getRequireCheckCustomerLabel()!=null) {
					TDto.setRequireCheckCustomerLabel(material.getRequireCheckCustomerLabel());//是否需要扫描客户标签
				}
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("MaterialType", MaterialType);
			map.put("Unit", Unit);
//			map.put("ProcurementType", ProcurementType);
			map.put("Location", Location);
//			map.put("MSL", MSL);
//			map.put("StationGroup", StationGroup);
//			map.put("Company", Company);
			map.put("Factory", Factory);	
			map.put("TDto", TDto);	
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 添加物料保存
	 * @param creationMaterialDTO
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/Post",method=RequestMethod.POST)
	public Map<String,Object> post(@Valid @RequestBody CreationMaterialDTO creationMaterialDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Material material = new Material();		
			if (creationMaterialDTO.getMaterialNumber()!=null && !"".equals(creationMaterialDTO.getMaterialNumber())) {
				Integer count = materialService.getCountByMaterialNumber(creationMaterialDTO.getMaterialNumber());
				if(creationMaterialDTO.getCustomerMaterialNumber()!=null && !"".equals(creationMaterialDTO.getCustomerMaterialNumber())){
					Integer customerMaterialNumberCount = materialService.getCountByCustomerMaterialNumber(creationMaterialDTO.getCustomerMaterialNumber());
					if(customerMaterialNumberCount != 0){
						json = this.setJson(0, "添加失败：客户侧料号不能重复！", -1);
						return json;
					}
				}
				if(creationMaterialDTO.getSupplierMaterialNumber()!=null && !"".equals(creationMaterialDTO.getSupplierMaterialNumber())){
					Integer supplierMaterialNumberCount = materialService.getCountBySupplierMaterialNumber(creationMaterialDTO.getSupplierMaterialNumber());
					if(supplierMaterialNumberCount != 0){
						json = this.setJson(200, "添加失败：供应商侧料号不能重复！", -1);
						return json;
					}
				}
				if (count==0) {
					material.setMaterialNumber(creationMaterialDTO.getMaterialNumber());
//					if (creationMaterialDTO.getVersion()!=null) {
//						material.setVersion(creationMaterialDTO.getVersion());
						material.setVersion(1);
//					}
					if (creationMaterialDTO.getDescription()!=null && !"".equals(creationMaterialDTO.getDescription())) {
						material.setDescription(creationMaterialDTO.getDescription());
					}
					
//						material.setSpecification(creationMaterialDTO.getSpecification());
					
						material.setCustomerMaterialNumber(creationMaterialDTO.getCustomerMaterialNumber());
					
						material.setSupplierMaterialNumber(creationMaterialDTO.getSupplierMaterialNumber());
					
						material.setMaterialGroupType(creationMaterialDTO.getMaterialGroupType());
					
//					if (creationMaterialDTO.getIsProduct()!=null && !"".equals(creationMaterialDTO.getIsProduct())) {
//						material.setIsProduct(creationMaterialDTO.getIsProduct());
						material.setIsProduct(false);
//					}
//					if (creationMaterialDTO.getIsMultiPanel()!=null && !"".equals(creationMaterialDTO.getIsMultiPanel())) {
//						material.setIsMultiPanel(Integer.parseInt(creationMaterialDTO.getIsMultiPanel())==1?true:false);
						material.setIsMultiPanel(false);
//					}
//					if(!"".equals(creationMaterialDTO.getRequireBackflush())){
//						material.setRequireBackflush(creationMaterialDTO.getRequireBackflush());
//					}//允许为空
//					if (creationMaterialDTO.getNumberOfPanels()!=null) {
//						material.setNumberOfPanels(creationMaterialDTO.getNumberOfPanels());
						material.setNumberOfPanels(0);
//					}
					if (creationMaterialDTO.getUnit()!=null) {
						material.setUnit(creationMaterialDTO.getUnit());
					}					
					if(!"".equals(creationMaterialDTO.getSetupFlag())){
						material.setSetupFlag(creationMaterialDTO.getSetupFlag());
					}//允许为空			
					
//						material.setProcurementType(creationMaterialDTO.getProcurementType());
					
						material.setMinimumPackageQuantity(creationMaterialDTO.getMinimumPackageQuantity());
					
						material.setExpirationTime(creationMaterialDTO.getExpirationTime());//单位：小时（原来是天）

						material.setSafetyStock(creationMaterialDTO.getSafetyStock());
					
//						material.setDefaultStorageLocationId(creationMaterialDTO.getDefaultStorageLocationId());
					
//						material.setContainerSize(creationMaterialDTO.getContainerSize());
								
//						material.setMSL(creationMaterialDTO.getMSL());
					if (creationMaterialDTO.getState()!=null) {
						material.setState(creationMaterialDTO.getState());	
					}
						
//						material.setDefaultStationGroupId(creationMaterialDTO.getDefaultStationGroupId().equals("")?null:Integer.parseInt(creationMaterialDTO.getDefaultStationGroupId()));
			
//						material.setDefaultTargetFeedingLocationId(creationMaterialDTO.getDefaultTargetFeedingLocation()==null?null:Integer.parseInt(creationMaterialDTO.getDefaultTargetFeedingLocation()));
//						material.setCompanyId(creationMaterialDTO.getCompanyId().equals("")?null:Integer.parseInt(creationMaterialDTO.getCompanyId()));
					if (creationMaterialDTO.getFactoryId()!=null) {
						material.setFactoryId(creationMaterialDTO.getFactoryId()==null?null:Integer.parseInt(creationMaterialDTO.getFactoryId()));
					}
					
					material.setCreationDateTime(new Date());
					material.setCreatorId(creationMaterialDTO.getCreatorId());
					material.setEditDateTime(new Date());
					material.setEditorId(creationMaterialDTO.getCreatorId());
					String validBeginStr = creationMaterialDTO.getValidBegin(); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");//注意格式化的表达式
					if (validBeginStr!=null && !"".equals(validBeginStr)) {
						validBeginStr = validBeginStr.replace("Z", " UTC");//注意是空格+UTC
						Date validBegin = format.parse(validBeginStr);
						material.setValidBegin(validBegin);
					}				
					String validEndStr = creationMaterialDTO.getValidEnd();	
					if(validEndStr!=null && !"".equals(validEndStr)){
						validEndStr = validEndStr.replace("Z", " UTC");
						Date validEnd = format.parse(validEndStr);
						material.setValidEnd(validEnd);
					}
					if (!"".equals(creationMaterialDTO.getRequireFIFO())) {
						material.setRequireFIFO(creationMaterialDTO.getRequireFIFO());//是否需要先进先出
					}
					if (!"".equals(creationMaterialDTO.getRequireCheckCustomerLabel())) {
						material.setRequireCheckCustomerLabel(creationMaterialDTO.getRequireCheckCustomerLabel());//是否需要扫描客户标签
					}
					materialService.addMaterial(material);
					json = this.setJson(200, "添加成功！", 0);
				}else{
					json = this.setJson(0, "添加失败：物料号不能重复!", -1);
				}
			}				
		}catch(Exception e){
			json = this.setJson(0, "添加失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 修改物料保存
	 * @param editMaterialDTO
	 * @return
	 */
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/Put",method=RequestMethod.PUT)
	public Map<String, Object> put(@Valid @RequestBody EditMaterialDTO editMaterialDTO,BindingResult result){
		Map<String, Object> json = new HashMap<>();
		try{
			if (result.hasErrors()) {
				String errorMessage = result.getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
				json = this.setJson(0, errorMessage, -1);
				return json;
			}
			Material material = materialService.getMaterialById(editMaterialDTO.getId());
			if (editMaterialDTO.getMaterialNumber()!=null && !"".equals(editMaterialDTO.getMaterialNumber())) {
				Integer count = materialService.getCountByMaterialNumber(editMaterialDTO.getMaterialNumber());
				if (count==0 || editMaterialDTO.getMaterialNumber().equals(material.getMaterialNumber())) {
					material.setMaterialNumber(editMaterialDTO.getMaterialNumber());
//					if (editMaterialDTO.getVersion()!=null) {
//						material.setVersion(editMaterialDTO.getVersion());
						material.setVersion(1);
//					}
					if (editMaterialDTO.getDescription()!=null && !"".equals(editMaterialDTO.getDescription())) {
						material.setDescription(editMaterialDTO.getDescription());
					}
//						material.setSpecification(editMaterialDTO.getSpecification());
					
						material.setCustomerMaterialNumber(editMaterialDTO.getCustomerMaterialNumber());
			
						material.setSupplierMaterialNumber(editMaterialDTO.getSupplierMaterialNumber());
					
						material.setMaterialGroupType(editMaterialDTO.getMaterialGroupType());
//					if (!"".equals(editMaterialDTO.getIsProduct())) {
//						material.setIsProduct(editMaterialDTO.getIsProduct());
						material.setIsProduct(false);
//					}
//					if (!"".equals(editMaterialDTO.getIsMultiPanel())) {
//						material.setIsMultiPanel(editMaterialDTO.getIsMultiPanel());
						material.setIsMultiPanel(false);
//					}
					
//						material.setRequireBackflush(editMaterialDTO.getRequireBackflush());
//					if (editMaterialDTO.getNumberOfPanels()!=null) {
//						material.setNumberOfPanels(editMaterialDTO.getNumberOfPanels());
						material.setNumberOfPanels(0);
//					}
					if (editMaterialDTO.getUnit()!=null) {
						material.setUnit(editMaterialDTO.getUnit());
					}
	//				if(!"".equals(editMaterialDTO.getSetupFlag())){
						material.setSetupFlag(editMaterialDTO.getSetupFlag());
	//				}	
						material.setProcurementType(editMaterialDTO.getProcurementType());
		
						material.setMinimumPackageQuantity(editMaterialDTO.getMinimumPackageQuantity());
				
						material.setExpirationTime(editMaterialDTO.getExpirationTime());
					
						material.setSafetyStock(editMaterialDTO.getSafetyStock());
					
//						material.setDefaultStorageLocationId(editMaterialDTO.getDefaultStorageLocationId());
					
//						material.setContainerSize(editMaterialDTO.getContainerSize());
					
//						material.setMSL(editMaterialDTO.getMSL());
					if (editMaterialDTO.getState()!=null) {
						material.setState(editMaterialDTO.getState());
					}
//					if (editMaterialDTO.getDefaultStationGroupId()!=null && !"".equals(editMaterialDTO.getDefaultStationGroupId())) {
//						material.setDefaultStationGroupId(editMaterialDTO.getDefaultStationGroupId()==null?null:Integer.parseInt(editMaterialDTO.getDefaultStationGroupId()));
//					}
//					material.setDefaultTargetFeedingLocationId(editMaterialDTO.getDefaultTargetFeedingLocation()==null?null:Integer.parseInt(editMaterialDTO.getDefaultTargetFeedingLocation()));
//					material.setCompanyId(editMaterialDTO.getCompanyId()== ""?null:Integer.parseInt(editMaterialDTO.getCompanyId()));
					if (editMaterialDTO.getFactoryId()!=null) {
						material.setFactoryId(editMaterialDTO.getFactoryId()==null?null:Integer.parseInt(editMaterialDTO.getFactoryId()));
					}
	//				material.setEditDateTime(editMaterialDTO.getEditDateTime());
	//				material.setEditorId(editMaterialDTO.getEditorId());
					material.setEditDateTime(new Date());
					material.setEditorId(editMaterialDTO.getEditorId());
					String validBeginStr = editMaterialDTO.getValidBegin(); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");//注意格式化的表达式
					if (validBeginStr!=null && !"".equals(validBeginStr)) {
						validBeginStr = validBeginStr.replace("Z", " UTC");//注意是空格+UTC
						Date validBegin = format.parse(validBeginStr);
						material.setValidBegin(validBegin);
					}				
					String validEndStr = editMaterialDTO.getValidEnd();	
					if(validEndStr!=null && !"".equals(validEndStr)){
						validEndStr = validEndStr.replace("Z", " UTC");
						Date validEnd = format.parse(validEndStr);
						material.setValidEnd(validEnd);
					}
					if (!"".equals(editMaterialDTO.getRequireFIFO())) {
						material.setRequireFIFO(editMaterialDTO.getRequireFIFO());//是否需要先进先出
					}
					if (!"".equals(editMaterialDTO.getRequireCheckCustomerLabel())) {
						material.setRequireCheckCustomerLabel(editMaterialDTO.getRequireCheckCustomerLabel());//是否需要扫描客户标签
					}
					materialService.updateMaterial(material);
					json = this.setJson(200, "修改成功！", 0);
				}else{
					json = this.setJson(0, "修改失败：物料号不能重复！", -1);
				}			
			}else{
				json = this.setJson(0, "修改失败：物料号不能为空！", -1);
			}
		}catch(Exception e){
			json = this.setJson(0, "修改失败："+e.getMessage(), -1);
			logger.error(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据物料Id删除物料
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
			if (idArray.size()!=0) {
				for (int i = 0; i < idArray.size(); i++) {
					Integer id = idArray.getInteger(i);
					Material material = materialService.getMaterialById(id);
					material.setMaterialNumber("Del@"+material.getMaterialNumber());
					material.setState(-1);
					materialService.updateMaterial(material);
				}	
				json = this.setJson(200, "删除成功!", 0);
			}else{
				json = this.setJson(0, "删除失败：请选择要删除的数据!", -1);
			}			
		}catch(Exception e){
			json = this.setJson(0, "删除失败："+e.getMessage(), -1);
			logger.error(e);

		}
		return json;
	}
}
