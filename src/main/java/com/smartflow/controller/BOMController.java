package com.smartflow.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.BOMItemModel;
import com.smartflow.model.StationGroup;
import com.smartflow.service.BOMHeadService;
import com.smartflow.service.MaterialService;
import com.smartflow.service.ProcessService;
import com.smartflow.service.StationService;
import com.smartflow.service.UnitService;
import com.smartflow.util.BOMDataForPage;
import com.smartflow.util.MaterialDataForSearch;
import com.smartflow.util.ReadDataUtil;

/**
 * @author haita
 */
@Controller
@RequestMapping("/api/BOM")
public class BOMController  extends BaseController {
	private static final  String STATUS_CODE="Status";
	private static final int STATUS_ERROR=101;
	private static Logger logger = Logger.getLogger(BOMController.class);
	private final
	BOMHeadService bomHeadService;
	private final
	MaterialService materialService;
	private final
	ProcessService processService;
	private final
	StationService stationService;
	private final
	UnitService unitService;
	Map<String, Object> json;
	Map<String, Object> map;
	@Autowired
	public BOMController(MaterialService materialService, ProcessService processService,
						 StationService stationService, UnitService unitService,BOMHeadService bomHeadService) {
		this.materialService = materialService;
		this.processService = processService;
		this.stationService = stationService;
		this.unitService = unitService;
		this.bomHeadService = bomHeadService;

	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping (value="/GetTByCondition")
	public @ResponseBody Object getPageData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		init();
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
		logger.debug(new Date()+"进入BOM列表接口");
		if (pageNumber==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		String materialNumber=jsonObject.getString("MaterialNumber");
		logger.info(new Date()+"进入API");
		List<BOMDataForPage> pagedata=bomHeadService.getPageData(pageSize, pageNumber,materialNumber);
		
		map.put("RowCount", bomHeadService.getRowCount(materialNumber));
		logger.info(new Date()+"完成API");
		map.put("Tdto", pagedata);
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e.getCause().getMessage());
		}
		return json;

	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetTById/{Id}")
	public @ResponseBody Object readDataById(@PathVariable Integer Id)
	{
		init();
        logger.debug(new Date()+"进入BOM详情接口");
		map.put("BomHead", bomHeadService.getBomDataById(Id));
		map.put("BomItemList", bomHeadService.getDataByIdInItemAddBracket(Id));
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e.getCause().getMessage());
		}
		return json;
	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/Delete")
	public @ResponseBody Object delDataById(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		init();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
	    @SuppressWarnings("unchecked")
		List<Integer> list=(List<Integer>)  jsonObject.get("List");
	    if (list.isEmpty())
	    {
	    	json=this.setJson(204, "未选择删除数据", 1);
			return(json);
		}
	    for (Integer Id : list) {
		if (bomHeadService.getBomDataById(Id)==null) {
			json=this.setJson(202, "所删除数据为空", 0);
			return(json);
		}
		else if (bomHeadService.get_BOMHead_Data_ById(Id).getState()==-1) {
			json=this.setJson(203, "该数据已删除", -1);
			return(json);
		}
		bomHeadService.delDataById(Id);
	    }
		try {
			json= this.setJson(200, "删除成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e.getCause().getMessage());
		}
		return json;

	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/GetMaterialDataForSearch")
	public @ResponseBody Object getMaterialDataForSearch(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		init();
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		String materialNumber =
				jsonObject.get("MaterialNumber") == null ? "" : String.valueOf(jsonObject.get("MaterialNumber"));
		List<MaterialDataForSearch>
				materialDataForSearchs
				=materialService.getDataForSearch(materialNumber);
		try {
			json= this.setJson(200, "查询成功", materialDataForSearchs);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}

	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetMaterialNumber")
	public @ResponseBody Object getMaterialNumber(HttpServletRequest request,HttpServletResponse response) {
		init();
		List<String> materialNumbers=new ArrayList<>();
		for (String material: materialService.getMaterials()) {
			materialNumbers.add(material);
		}
		try {
			json= this.setJson(200, "查询成功", materialNumbers);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/GetMaterialByMaterialNumber")
	public @ResponseBody Object getMaterialByMaterialNumber(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		String materialNumber = jsonObject.get("MaterialNumber") == null ? "" : String.valueOf(jsonObject.get("MaterialNumber"));
		Map<String, Object> map=new  HashMap<>();
		if (materialService.getMaterialByNumber(materialNumber)==null
				||materialService.getMaterialByNumber(materialNumber).getState()==-1) {
			map.put("ReturnCode", 0);
			map.put("ReturnMessage", "你所请求的数据不存在");
			json= this.setJson(200, "你所请求的数据不存在", map);
			return json;
		}
		if(!bomHeadService.isCanRegister(materialNumber))
		{
			map.put("ReturnCode", 0);
			map.put("ReturnMessage", "此物料号为原材料无法注册");
			json= this.setJson(200, "此物料号为原材料无法注册", map);
			return json;
		}
		map.put("ReturnCode", 1);
		map.put("ReturnMessage", "请求数据成功");

		MaterialDataForSearch materialDataForSearch=new MaterialDataForSearch();
		materialDataForSearch.setMaterialName(materialService.getMaterialByNumber
				(materialNumber).getDescription());
		materialDataForSearch.setMaterialNumber(materialNumber);
		BOMHeadModel bomHeadModel=bomHeadService.getRegisterBom(materialNumber);
		if (bomHeadModel!=null)
		{
			materialDataForSearch.setVersion(bomHeadModel.getVersion());
		}
		else
		{
			materialDataForSearch.setVersion(0);
		}
		map.put("Tdto", materialDataForSearch);
		try {
			json= this.setJson(200, "查询成功",map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}


	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/Post")
	public @ResponseBody Object addDataForBomHead(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		init();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		BOMHeadModel bomHeadModel=parseToBomAdd(jsonObject);
		String  materialNumber=jsonObject.getString("MaterialNumber");
		if(!bomHeadService.isCanRegister(materialNumber))
		{
			json= this.setJson(STATUS_ERROR, "此物料号为原材料无法注册",
					-1);
		}
		if (bomHeadService.isRegisterMaterialNumber(materialNumber,
				jsonObject.getInteger("Version")))
		{
			json= this.setJson(STATUS_ERROR, "此物料号已被注册", -1);
		}
		List<BOMItemModel> bomItemModels=parseToBomItemAdd(jsonObject);
		if (json.get(STATUS_CODE)!=null&&(int)json.get(STATUS_CODE)==STATUS_ERROR)
		{
			return json;
		}
		bomHeadService.add_BOMHead_Data(bomHeadModel,
				bomItemModels);
		try {
			json= this.setJson(200, "添加成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e.getCause().getMessage());
		}
		return json;
	}
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PutMapping(value="/Put")
	public @ResponseBody Object updateDataForBOMHead(HttpServletRequest request,HttpServletResponse response) throws Exception
	{init();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		BOMHeadModel bomHeadModel=parseToBomEdite(jsonObject);
		List<BOMItemModel> bomItemModels=parseToBomItem(jsonObject);
		if (json.get(STATUS_CODE)!=null&&(int)json.get(STATUS_CODE)==STATUS_ERROR)
		{
			return json;
		}
		bomHeadService.upDate_BOMHead_Data(bomHeadModel, bomItemModels);
		try {
			json= this.setJson(200, "编辑成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetEditInitialize/{Id}")
	public @ResponseBody Object getEditInitializeById(@PathVariable Integer Id)
	{init();
		map.put("StationGroup", processService.getStationGroup());
		List<Map<String, Object>> sGList=new ArrayList<>();
		for (StationGroup  stationGroup : processService.getStationGroup()) {
			Map<String, Object> sG=new HashMap<>();
			sG.put("key", stationGroup.getId());
			sG.put("label" ,stationGroup.getDescription());
			sGList.add(sG);
		}
        map.put("StationGroup", sGList);
        map.put("UnitList", unitService.getUnit());
        map.put("FactoryList", stationService.getFactory());
		map.put("BomHead", bomHeadService.getBomDataById(Id));
		map.put("BomItemList", bomHeadService.getBOMItemGetById(Id));
		map.put("BomItemListCount", bomHeadService.countBOMItemByBOMHeadId(Id));
		try {
			json= this.setJson(200, "编辑初始化成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	//获取工厂的Id以及工厂Name
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping (value="/GetAddInitialize")
	public @ResponseBody Object getAddInitialize(HttpServletRequest request,HttpServletResponse response) throws Exception
	{init();
		map.put("FactoryList", stationService.getFactory());
		List<Map<String, Object>> sGList=new ArrayList<>();
		for (StationGroup  stationGroup : processService.getStationGroup()) {
			Map<String, Object> sG=new HashMap<>();
			sG.put("key", stationGroup.getId());
			sG.put("label" ,stationGroup.getDescription());
			sGList.add(sG);
		}
        map.put("StationGroup", sGList);
        map.put("UnitList", unitService.getUnit());
		try {
			json= this.setJson(200, "新增初始化成功",map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping (value="/GetTByConditionForParent")
	public @ResponseBody Object getPageDataForParent(HttpServletRequest request,HttpServletResponse response) throws Exception
	{init();
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		int pageSize=jsonObject.getIntValue("PageSize");
		int pageNumber=jsonObject.getIntValue("PageIndex");
		Map<String, Object> map=new HashMap<String,Object>();
		if (pageNumber==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		
		String materialNumber=jsonObject.getString("MaterialNumber");
		List<BOMDataForPage> pagedata=bomHeadService.readPageDataForParent(pageSize, pageNumber,materialNumber);
		
		map.put("RowCount", bomHeadService.getRowCountForPageParent());
		map.put("Tdto", pagedata);
	
        
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}

	private BOMHeadModel parseToBomAdd(JSONObject jsonObject) throws ParseException {
		BOMHeadModel bomHeadModel=new BOMHeadModel();
		String  materialNumber=jsonObject.getString("MaterialNumber");
		if (materialNumber==null) {
			json= this.setJson(STATUS_ERROR, "物料号不允许为空", -1);
			return bomHeadModel;
		}
		String name=jsonObject.get("Name")==null?"":String.valueOf(jsonObject.get("Name"));
		if ("".equals(name))
		{
			json= this.setJson(STATUS_ERROR, "请填写产品名", -1);
			return bomHeadModel;
		}
		String validBegin=jsonObject.get("ValidBegin")==null?"":String.valueOf(jsonObject.get("ValidBegin"));
		if ("".equals(validBegin)) {
			json= this.setJson(STATUS_ERROR, "请选择生效时间", -1);
			return bomHeadModel;
		}
		String validEnd=jsonObject.get("ValidEnd")==null?"":String.valueOf(jsonObject.get("ValidEnd"));
		if ("".equals(validEnd)) {
			json= this.setJson(STATUS_ERROR, "请选择失效时间", -1);
			return bomHeadModel;
		}
		Integer factoryId=jsonObject.getInteger("FactoryId");
		if (factoryId==null) {
			json= this.setJson(STATUS_ERROR, "工厂Id不能为空", -1);
			return bomHeadModel;
		}
		Integer version=jsonObject.getInteger("Version");
		if (version==null) {
			json= this.setJson(STATUS_ERROR, "版本号不能为空", -1);
			return bomHeadModel;
		}
		Integer creatorId = jsonObject.getInteger("CreatorId");
		String eRPBOMVersion=jsonObject.getString("ERPBOMVersion");
		
		Date nowTime=new Date();
		bomHeadModel.setCreatorId(2);
		bomHeadModel.setEditDateTime(nowTime);
		bomHeadModel.setEditorId(2);
		bomHeadModel.setVersion(version);
		bomHeadModel.setFactoryId(factoryId);
		bomHeadModel.setERPBOMVersion(eRPBOMVersion);
		bomHeadModel.setProductName(name);
		if (materialService.getMaterialByNumber(materialNumber)==null) {
			json= this.setJson(STATUS_ERROR, "你输入的物料号有错", -1);
			return bomHeadModel;
		}
		bomHeadModel.setMaterialId(materialService.getMaterialByNumber(materialNumber).getId());
		bomHeadModel.setState(1);
		validBegin = validBegin.replace("Z", " UTC");
		validEnd= validEnd.replace("Z", " UTC");
		SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date  vBDate= utcFormat.parse(validBegin);
		Date  vEDate= utcFormat.parse(validEnd);
		if (vBDate.after(vEDate))
		{
			json= this.setJson(STATUS_ERROR, "生效时间大于失效时间", -1);
			return bomHeadModel;
		}
		bomHeadModel.setValidBegin(vBDate);
		bomHeadModel.setValidEnd(vEDate);
		return bomHeadModel;
	}

	private List<BOMItemModel> parseToBomItem(JSONObject jsonObject)
	{
		String  materialNumber=jsonObject.getString("MaterialNumber");
		String bomItemList = jsonObject.get("BomItemList") == null ? null : jsonObject.get("BomItemList").toString();
		JSONArray jsonArray=JSONArray.parseArray(bomItemList);
		JSONObject jsonOne;
		List<Map<String, Object>> maps=new ArrayList<>();
		for(int i=0;i<jsonArray.size();i++)
		{
			Map<String, Object> map=new HashMap<>();
			jsonOne=jsonArray.getJSONObject(i);
			map.put("MaterialNumber",jsonOne.get("MaterialNumber"));
			map.put("Quantity",jsonOne.get("Quantity"));
			map.put("StationGroupId",jsonOne.get("StationGroupId"));
			map.put("IsNeedSetupCheck",jsonOne.get("IsNeedSetupCheck"));
			map.put("UnitId",jsonOne.get("UnitId"));
			map.put("IsAlternative",jsonOne.get("IsAlternative"));
			map.put("Version", 1);
			maps.add(map);
		}
		List<BOMItemModel> bomItemModels=new ArrayList<>();
		for (int i=0;i<maps.size();i++) {
			BOMItemModel bomItemModel=new BOMItemModel();
			Map<String, Object> mapForBomItemModel=maps.get(i);
			if(mapForBomItemModel.get("IsAlternative")==null)
			{
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择是否是产出品", -1);
				return bomItemModels;
			}
			if(mapForBomItemModel.get("IsNeedSetupCheck")==null)
			{
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择是否上料", -1);
				return bomItemModels;
			}
			if (mapForBomItemModel.get("MaterialNumber")==null) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未填写物料号", -1);
				return bomItemModels;
			}
			else if (materialService.getMaterialByNumber(mapForBomItemModel.get("MaterialNumber").toString())==null
					||materialNumber.equals(mapForBomItemModel.get("MaterialNumber").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行填写的物料号错误", -1);
				return bomItemModels;
			}
			if (mapForBomItemModel.get("Quantity")==null) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未填写用量", -1);
				return bomItemModels;
			}
			if ("请选择".equals(mapForBomItemModel.get("StationGroupId").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择工站组", -1);
				return bomItemModels;
			}
			if ("请选择".equals(mapForBomItemModel.get("UnitId").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择单位", -1);
				return bomItemModels;
			}
			if (mapForBomItemModel.get("Version")==null) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择版本号", -1);
				return bomItemModels;
			}
			bomItemModel.setIsAlternative(Boolean.valueOf(mapForBomItemModel.get("IsAlternative").toString()));
			bomItemModel.setIsNeedSetupCheck(Boolean.valueOf(mapForBomItemModel.get("IsNeedSetupCheck").toString()));
			bomItemModel.setMaterialId(materialService.getMaterialByNumber(mapForBomItemModel.get("MaterialNumber").toString()).getId());
			bomItemModel.setQuantity(Float.valueOf(mapForBomItemModel.get("Quantity").toString()));
			bomItemModel.setStationGroupId(Integer.valueOf(mapForBomItemModel.get("StationGroupId").toString()));
			bomItemModel.setUnit(Integer.valueOf(mapForBomItemModel.get("UnitId").toString()));
			bomItemModel.setVersion(Integer.valueOf(mapForBomItemModel.get("Version").toString()));
			bomItemModels.add(bomItemModel);
		}
		return bomItemModels;
	}

	private List<BOMItemModel> parseToBomItemAdd(JSONObject jsonObject)
	{
		String  materialNumber=jsonObject.getString("MaterialNumber");
		String bomItemList = jsonObject.get("BomItemList") == null ? null : jsonObject.get("BomItemList").toString();
		JSONArray jsonArray=JSONArray.parseArray(bomItemList);
		JSONObject jsonOne;
		List<Map<String, Object>> maps=new ArrayList<>();
		for(int i=0;i<jsonArray.size();i++)
		{
			Map<String, Object> map=new HashMap<>();
			jsonOne=jsonArray.getJSONObject(i);
			map.put("MaterialNumber",jsonOne.get("MaterialNumber"));
			map.put("Quantity",jsonOne.get("Quantity"));
			map.put("StationGroupId",jsonOne.get("StationGroupId"));
			map.put("IsNeedSetupCheck",jsonOne.get("IsNeedSetupCheck"));
			map.put("Unit",jsonOne.get("Unit"));
			map.put("IsAlternative",jsonOne.get("IsAlternative"));
			maps.add(map);
		}
		List<BOMItemModel> bomItemModels=new ArrayList<>();
		for (int i=0;i<maps.size();i++) {
			BOMItemModel bomItemModel=new BOMItemModel();
			Map<String, Object> mapForBomItemModel=maps.get(i);
			if(mapForBomItemModel.get("IsAlternative")==null)
			{
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择是否是产出品", -1);
				return bomItemModels;
			}
			if(mapForBomItemModel.get("IsNeedSetupCheck")==null)
			{
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择是否上料", -1);
				return bomItemModels;}
			if (mapForBomItemModel.get("MaterialNumber")==null) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未填写物料号", -1);
				return bomItemModels;
			}
			else if (materialService.getMaterialByNumber(mapForBomItemModel.get("MaterialNumber").toString())==null
					||materialNumber.equals(mapForBomItemModel.get("MaterialNumber").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行填写的物料号错误", -1);
				return bomItemModels;
			}
			if (mapForBomItemModel.get("Quantity")==null) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未填写用量", -1);
				return bomItemModels;
			}
			if ("请选择".equals(mapForBomItemModel.get("StationGroupId").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择工站组", -1);
				return bomItemModels;
			}
			if ("请选择".equals(mapForBomItemModel.get("Unit").toString())) {
				Integer columnNumber=i+1;
				String column=columnNumber.toString();
				json= this.setJson(STATUS_ERROR, "你在BOM子表中第"+column+"行未选择单位", -1);
				return bomItemModels;}
			bomItemModel.setIsAlternative(Boolean.valueOf(mapForBomItemModel.get("IsAlternative").toString()));
			bomItemModel.setIsNeedSetupCheck(Boolean.valueOf(mapForBomItemModel.get("IsNeedSetupCheck").toString()));
			bomItemModel.setMaterialId(materialService.getMaterialByNumber(mapForBomItemModel.get("MaterialNumber").toString()).getId());
			bomItemModel.setQuantity(Float.valueOf(mapForBomItemModel.get("Quantity").toString()));
			bomItemModel.setStationGroupId(Integer.valueOf(mapForBomItemModel.get("StationGroupId").toString()));
			bomItemModel.setUnit((int)mapForBomItemModel.get("Unit"));
			bomItemModels.add(bomItemModel);
		}
		return bomItemModels;
	}
	private BOMHeadModel parseToBomEdite(JSONObject jsonObject) throws ParseException {
		BOMHeadModel bomHeadModel=parseToBomAdd(jsonObject);
		Integer id=jsonObject.getInteger("Id");
		if (id==null) {
			json= this.setJson(STATUS_ERROR, "BOMHeadId号不能为空", -1);
		}
		else {
			bomHeadModel.setId(id);
		}
		return bomHeadModel;
	}

	private void init()
	{
		this.json=new HashMap<>();
		this.map=new HashMap<>();
	}
}
