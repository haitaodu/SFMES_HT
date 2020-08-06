package com.smartflow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.service.*;
import com.smartflow.util.MaterialDataForSearch;
import com.smartflow.util.ProcessDataForPage;
import com.smartflow.util.ReadDataUtil;
import com.smartflow.view.Process.ProcessItemDetailView;
import com.smartflow.view.Process.ProcessItemEditeView;
import com.smartflow.view.Process.ProcsessEditeView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author haita
 */
@Controller
@RequestMapping("api/Process")
public class ProcessController extends BaseController {
	private final
	ProcessService processService;
	private final
	MaterialService materialService;
	private final
    CellService cellService;
	final
	BOMHeadService bomHeadService;
	private static Logger logger = Logger.getLogger(ProcessController.class);
    Map<String, Object> json = new HashMap<String, Object>(9);
    private static final  String STATUS_CODE="Status";
    private static final  int   STATUS_ERROR=101;
	@Autowired
	public ProcessController(MaterialService materialService, StationService stationService, ProcessService processService, CellService cellService, BOMHeadService bomHeadService) {
		this.materialService = materialService;
		StationService stationService1 = stationService;
		this.processService = processService;
        this.cellService = cellService;
		this.bomHeadService = bomHeadService;
	}

	@PostMapping(value="/GetTByCondition")
	@CrossOrigin(origins = "*",maxAge = 3600)
	public @ResponseBody Object getPageData
			(HttpServletRequest request,HttpServletResponse response)
			throws Exception
	{
		JSONObject jsonObject=ReadDataUtil.paramData(request);
		int pagesize=jsonObject.getIntValue("PageSize");
		int pageindex=jsonObject.getIntValue("PageIndex");
		String materialNumber=jsonObject.getString("MaterialNumber");
		String factoryName=jsonObject.getString("FactoryName");
		List<ProcessDataForPage> pages=processService.getPage(pagesize, pageindex,materialNumber,factoryName);
		Map<String, Object> json;
		Map<String, Object> map=new HashMap<>();
		if (pageindex==0) {
			map.put("RowCount", 0);
			map.put("Tdto", new ArrayList<>());
			json= this.setJson(200, "无数据", map);
			return(json);
		}
		map.put("RowCount", processService.getcount(materialNumber,factoryName));
		map.put("Tdto", pages);
	
		try {
			json=this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
		}
		return json;
	}
	@GetMapping (value="/GetTById/{Id}")
	public @ResponseBody Object readDataById(@PathVariable Integer Id)
	{
		Map<String, Object> json=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		map.put("Process", processService.getDataInId(Id));
		map.put("ProcessStep", processService.getDataById(Id));
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/Delete")
	public @ResponseBody Object delDataById(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> json=new HashMap<>();
		JSONObject jsonObject = ReadDataUtil.paramData(request);
	    @SuppressWarnings("unchecked")
		List<Integer> list=(List<Integer>)  jsonObject.get("List");
	   
	    if (list.isEmpty()) {
	    	json=this.setJson(204, "未选择删除数据", 1);
			return(json);
		}
	    for (Integer Id : list) {
		if (processService.getProcessDataById(Id)==null) {
			json=this.setJson(202, "所删除数据为空", 1);
			return(json);
		}
		else if (processService.getProcessDataById(Id).getState()==-1) {
			json=this.setJson(203, "该数据已删除", -1);
			return(json);
		}
		processService.delData(Id);
	    }
		try {
			json= this.setJson(200, "删除成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}
	@GetMapping (value="/GetProcessListOfALl")
	public @ResponseBody Object getProcessListOfALl()
	{
		Map<String, Object> json=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		map.put("ProcessListOfALl", processService.getProcessList());
	
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/Post")
	public @ResponseBody Object addDataForProcess(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		ProcessModel processModel=parseToProcessHeadForAdd(jsonObject);
		List<ProcessStep> processSteps=parseProcessStepForList(jsonObject);
		if (json.get(STATUS_CODE)!=null&&(int)json.get(STATUS_CODE)==STATUS_ERROR)
        {
            return json;
        }try {
		processService.addData(processModel,processSteps);
			json= this.setJson(200, "添加成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PutMapping(value="/Put")
	public @ResponseBody Object updateDataForProcess(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, Object> json = new HashMap<>(10);
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String  ProcessNumber=jsonObject.getString("ProcessNumber");
		Integer idInteger=jsonObject.getInteger("Id");
		Integer version=jsonObject.getInteger("Version");
		String parentProcessNumber=(String) jsonObject.get("ParentProcessNumber");
		String materialNumber=jsonObject.getString("MaterialNumber");
        String validEnd=jsonObject.get("ValidEnd")==null?"":String.valueOf(jsonObject.get("ValidEnd"));
        String validBegin=jsonObject.get("ValidBegin")==null?"":String.valueOf(jsonObject.get("ValidBegin"));
        Integer factoryId=jsonObject.getInteger("FactoryId");
        Integer state=jsonObject.getInteger("State");
        Integer editorId = jsonObject.getInteger("EditorId");
        String description=jsonObject.get("Description")==null?"":String.valueOf(jsonObject.get("Description"));
		if (parentProcessNumber==null)
		{
			json= this.setJson(STATUS_ERROR, "总工艺号不允许为空", -1);
			return json;
		}
        if (idInteger==null) {
			json= this.setJson(STATUS_ERROR, "工艺Id不允许为空", -1);
			return json;
		}
		if (ProcessNumber==null) {
			json= this.setJson(STATUS_ERROR, "工艺号不允许为空", -1);
			return json;
		}
		if ("".equals(validBegin)) {
			json= this.setJson(STATUS_ERROR, "请选择生效时间", -1);
			return json;
		}
		if ("".equals(validEnd)) {
			json= this.setJson(STATUS_ERROR, "请选择失效时间", -1);
			return json;
		}
		if (factoryId==null) {
			json= this.setJson(STATUS_ERROR, "岛区Id不能为空", -1);
			return json;
		}
		if(state==null)
		{
			json= this.setJson(STATUS_ERROR, "状态不能为空", -1);
			return json;
		}
		if(ProcessNumber.length()>=40)
		{
			json= this.setJson(STATUS_ERROR, "工艺号超过限定长度", -1);
			return json;
		}
	    ProcessModel processModel=new ProcessModel();
		Date nowTime=new Date();
		processModel.setCreationDateTime(nowTime);
		processModel.setCreatorId(editorId);
		processModel.setEditDateTime(nowTime);
		processModel.setEditorId(editorId);
		processModel.setCell(cellService.getCellById(factoryId));
		processModel.setState(state);
		processModel.setVersion(version);
		processModel.setProcessNumber(ProcessNumber);
		processModel.setDescription(description);
		if (materialService.getMaterialByNumber(materialNumber)==null) {
			json= this.setJson(STATUS_ERROR, "你输入的物料号有错", -1);
			return json;
		}
		processModel.setMaterialId(materialService.getMaterialByNumber(materialNumber).getId());
		processModel.setId(idInteger);
		processModel.setState(1);
		validBegin = validBegin.replace("Z", " UTC");
		SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		Date  vBDate= utcFormat.parse(validBegin);
		 validEnd= validEnd.replace("Z", " UTC");
		Date  vEDate= utcFormat.parse(validEnd);
		if (vBDate.after(vEDate))
		{
			json= this.setJson(STATUS_ERROR, "生效时间大于失效时间", -1);
			return json;
		}
		processModel.setValidBegin(vBDate);
		processModel.setValidEnd(vEDate);
		processModel.setParentProcessNumber(parentProcessNumber);
		/*
		 *向BOMItem表中增加数据
		 */
		String processStepList = jsonObject.get("ProcessStepList") == null ? null : jsonObject.get("ProcessStepList").toString();
	    List<Map<String,Object>> maps=getMaps(processStepList);
		List<ProcessStep> processSteps=new ArrayList<>();
		for (int i=0;i<maps.size();i++) {
        ProcessStep processStep=new ProcessStep();
        Map<String, Object> mapForProcessStepModel=maps.get(i);
       processStep.setDescription(mapForProcessStepModel.get("Description").toString());
        if(mapForProcessStepModel.get("IsNeedSetupCheck")==null)
       {
    	   Integer columnNumber=i+1;
    	   String column=columnNumber.toString();
    	   json= this.setJson(102, "你在工艺步骤第"+column+"行未选择是否上料", -1);
			return json;
       }   
       processStep.setIsNeedSetupCheck(Boolean.valueOf(mapForProcessStepModel.get("IsNeedSetupCheck").toString()));
       if(mapForProcessStepModel.get("StationGroupId").toString().equals("请选择"))
       {
    	   Integer columnNumber=i+1;
    	   String column=columnNumber.toString();
    	   json= this.setJson(102, "你在工艺步骤第"+column+"行未选择工站组", -1);
			return json;
       }   
       processStep.setStationGroupId(Integer.valueOf(mapForProcessStepModel.get("StationGroupId").toString()));
       if(mapForProcessStepModel.get("Secquence")==null)
       {
    	   Integer columnNumber=i+1;
    	   String column=columnNumber.toString();
    	   json= this.setJson(102, "你在工艺步骤第"+column+"行的步骤未填写", -1);
			return json;
       }   
       processStep.setSecquence(Integer.valueOf(mapForProcessStepModel.get("Secquence").toString()));
       processStep.setEditDateTime(new Date());
       processStep.setEditorId(editorId);
       processSteps.add(processStep);
		}
		if (processService.isUsingInProduct(processModel.getId())==true) {
			json= this.setJson(102, "你的产品正在使用中无法更改", -1);
			return json;
		}
		processService.updateData(processModel, processSteps);
		try {
			json= this.setJson(200, "修改成功", 0);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}


	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping (value="/GetEditInitialize/{Id}")
		public @ResponseBody Object getEditInitializeById(@PathVariable Integer Id)
		{
		Map<String, Object> json;
		Map<String, Object> map=new  HashMap<>();
		ProcsessEditeView procsessEditeView=processService.getProcessEditeView(Id);
        map.put("StationGroup", getStationGroupListByCellId(procsessEditeView.getFactoryId()));
		map.put("VersionList",getVersionList(procsessEditeView.getMaterialNumber()));
        map.put("FactoryList", cellService.getCellListInit());
        map.put("Process", procsessEditeView);
		List<ProcessItemEditeView> processDataForPages=processService.getProcessStepEditeById(Id);
		map.put("ProcessStepList",processDataForPages);
		if (processDataForPages.isEmpty()) {
			map.put("ProcessStepListCount", 0);
		}
		else {
			map.put("ProcessStepListCount",
					processDataForPages.get(processDataForPages.size()-1).getSecquence());
		}
		try {
			json= this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping(value="/GetAddInitialize")
	public @ResponseBody Object getAddInitialize(HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		Map<String, Object> json=new HashMap<String,Object>();
		Map<String, Object> map=new HashMap<>();
		map.put("FactoryList", cellService.getCellListInit());
		List<Map<String, Object>> sGList=new ArrayList<>();
		for (StationGroup  stationGroup : processService.getStationGroup()) {
			Map<String, Object> sG=new HashMap<>();
			sG.put("key", stationGroup.getId());
			sG.put("label" ,stationGroup.getDescription());
			sGList.add(sG);
		}
        map.put("StationGroup", sGList);
     
		try {
			json= this.setJson(200, "新增初始化数据成功",map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(),1);
			logger.error(e);
		}
		return json;

	}
	@CrossOrigin(origins="*",maxAge=3600)
	@GetMapping(value="/GetInitializeList")
	public @ResponseBody Map<String, Object> getFactoryNameList(){
		Map<String, Object> json = new HashMap<>();
		try{
			List<Map<String, Object>> cellList = cellService.getCellListInit();
			Map<String, Object> map = new HashMap<>();
			map.put("FactoryList", cellList);
			json = this.setJson(200, "查询成功！", map);
		}catch(Exception e){
			json = this.setJson(0, "查询失败："+e.getMessage(), -1);
			logger.error(e);
		}
		return json;
	}

	private List<Map<String, Object>> getMaps(String processStepList)
    {
        JSONArray jsonArray=JSONArray.parseArray(processStepList);
        JSONObject jsonOne;
        List<Map<String, Object>> maps=new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++)
        {
            Map<String, Object> map=new HashMap<>();
            jsonOne=jsonArray.getJSONObject(i);
            map.put("Secquence",jsonOne.get("Secquence"));
            map.put("Description",jsonOne.get("Description"));
            map.put("StationGroupId",jsonOne.get("StationGroupId"));
            map.put("IsNeedSetupCheck",jsonOne.get("IsNeedSetupCheck"));
            maps.add(map);
        }
        return maps;
    }


    private ProcessModel parseToProcessHeadForAdd(JSONObject jsonObject) throws ParseException {
        Integer factoryId=jsonObject.getInteger("FactoryId");
        String processNumber =jsonObject.getString("ProcessNumber");
        String  materialNumber=jsonObject.getString("MaterialSplits");
        String validBegin=jsonObject.get("ValidBegin")==null?"":String.valueOf(jsonObject.get("ValidBegin"));
        String description=jsonObject.get("Description")==null?"":
                String.valueOf(jsonObject.get("Description"));
        String parentProcessNumber=(String) jsonObject.get("ParentProcessNumber");
        Integer creatorId = jsonObject.getInteger("CreatorId");
        String validEnd=jsonObject.get("ValidEnd")==null?"":String.valueOf(jsonObject.get("ValidEnd"));
        validBegin = validBegin.replace("Z", " UTC");
        Integer version=jsonObject.getIntValue("Version");
        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date  vBDate= utcFormat.parse(validBegin);
        validEnd= validEnd.replace("Z", " UTC");
        Date  vEDate= utcFormat.parse(validEnd);
        Date nowTime=new Date();
        ProcessModel processModel=new ProcessModel();
        if (parentProcessNumber==null)
		{
			json= this.setJson(STATUS_ERROR, "总工艺号不允许为空", -1);
			return processModel;
		}
        if (processNumber==null) {
            json= this.setJson(STATUS_ERROR, "工艺号不允许为空", -1);
            return processModel;
        }
        if ("".equals(validBegin)) {
            json= this.setJson(STATUS_ERROR, "请选择生效时间", -1);
            return processModel;
        }
        if ("".equals(validEnd)) {
            json= this.setJson(STATUS_ERROR, "请选择失效时间", -1);
            return processModel;
        }
        if (factoryId==null) {
            json= this.setJson(STATUS_ERROR, "工厂Id不能为空", -1);
            return processModel;
        }
        if(processNumber.length()>=40)
        {
            json= this.setJson(STATUS_ERROR, "工艺号超过限定长度", -1);
            return processModel;
        }
        if (processService.isRegister(processNumber))
        {
            json= this.setJson(STATUS_ERROR, "此工艺号已被注册", -1);
            return processModel;
        }
		Map<String,Object> map=getVersionMaterialNumber(materialNumber);
        if (materialService.getMaterialByNumber((String) map.get("materialNumber"))==null) {
            json= this.setJson(STATUS_ERROR, "你输入的物料号有错", -1);
            return processModel;
        }
        processModel.setCreationDateTime(nowTime);
        processModel.setCreatorId(creatorId);
        processModel.setEditDateTime(nowTime);
        processModel.setEditorId(creatorId);
		processModel.setCell(factoryId == null ? null : cellService.getCellById(factoryId));
        processModel.setProcessNumber(processNumber);
        processModel.setState(1);
        processModel.setDescription(description);
        map=getVersionMaterialNumber(materialNumber);
        processModel.setMaterialId(materialService.getMaterialByNumber((String) map.get("materialNumber")).getId());
        processModel.setState(1);
        processModel.setValidBegin(vBDate);
        processModel.setValidEnd(vEDate);
        processModel.setVersion((int)map.get("version"));
        processModel.setParentProcessNumber(parentProcessNumber);
        return processModel;
    }


    private List<ProcessStep> parseProcessStepForList(JSONObject jsonObject)
    {
        String processStepList = jsonObject.get("ProcessStepList")
				== null ? null : jsonObject.get("ProcessStepList").toString();
        Integer creatorId = jsonObject.getInteger("CreatorId");
        List<Map<String,Object>> maps=getMaps(processStepList);
        List<ProcessStep> processSteps=new ArrayList<>();
        for (int i=0;i<maps.size();i++) {
            ProcessStep processStep=new ProcessStep();
            Map<String, Object> mapForProcessStepModel=maps.get(i);
            if(mapForProcessStepModel.get("IsNeedSetupCheck")==null)
            {
                Integer columnNumber=i+1;
                String column=columnNumber.toString();
                json= this.setJson(STATUS_ERROR, "你在工艺步骤第"+column+"行未选择是否检查", -1);
                return processSteps;
            }
            if("请选择".equals(mapForProcessStepModel.get("StationGroupId").toString()))
            {
                Integer columnNumber=i+1;
                String column=columnNumber.toString();
                json= this.setJson(STATUS_ERROR, "你在工艺步骤第"+column+"行未选择工站组", -1);
                return processSteps;
            }
            if(mapForProcessStepModel.get("Secquence")==null)
            {
                Integer columnNumber=i+1;
                String column=columnNumber.toString();
                json= this.setJson(STATUS_ERROR, "你在工艺步骤第"+column+"行的步骤次序未填写", -1);
                return processSteps;
            }
            processStep.setDescription(mapForProcessStepModel.get("Description").toString());
            processStep.setStationGroupId(Integer.valueOf(mapForProcessStepModel.get("StationGroupId").toString()));
            processStep.setSecquence(Integer.valueOf(mapForProcessStepModel.get("Secquence").toString()));
            processStep.setIsNeedSetupCheck(Boolean.valueOf(mapForProcessStepModel.get("IsNeedSetupCheck").toString()));
            processStep.setEditDateTime(new Date());
            processStep.setEditorId(creatorId);
            processSteps.add(processStep);
        }
        return processSteps;
    }


	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value="/GetMaterialListByMaterialNumber")
	public @ResponseBody Object getMaterialListByMaterialNumber
			(HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		JSONObject jsonObject = ReadDataUtil.paramData(request);
		String materialNumber = jsonObject.get("MaterialNumber") == null ? "" : String.valueOf(jsonObject.get("MaterialNumber"));
		Map<String, Object> map = new HashMap<>();
		List<BOMHeadModel> bomHeadModels = bomHeadService.getRegisterProduct(materialNumber);
		if (bomHeadModels.isEmpty()) {
			map.put("ReturnCode", 0);
			map.put("ReturnMessage", "此物料号没有注册");
			json = this.setJson(200, "此物料号没有注册", map);
			return json;
		}
		map.put("ReturnCode", 1);
		map.put("ReturnMessage", "请求数据成功");
		List<MaterialDataForSearch> materialDataForSearches = new ArrayList<>();
		List<Map<String, Object>> list=new ArrayList<>();
		int i=0;
		for (BOMHeadModel bomHeadModel : bomHeadModels) {

			Map<String, Object> materialData=new HashMap<>();
		    materialData.put("key",i);
		    materialData.put("label",materialNumber+"|"+bomHeadModel.getVersion()+"|"+materialService.
					getMaterialByNumber(materialNumber).getDescription());
		    list.add(materialData);
		    i++;
		}
		map.put("Tdto", list);
		try {
			json = this.setJson(200, "查询成功", map);
		} catch (Exception e) {
			json = this.setJson(0, e.getMessage(), 1);
			logger.error(e);
		}
		return json;
	}


	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping (value="/GetStationGroupList/{Id}")
	public @ResponseBody Object getStationGroupList(@PathVariable Integer Id) {
		Map<String, Object> json;
		Map<String, Object> map=new  HashMap<>();
		map.put("StationGroup", getStationGroupListByCellId(Id));
		json= this.setJson(200, "查询成功", map);
		return json;
	}


	/**
	 * 将前端的字符串分割成version,materialNumber;
	 * @param arg 混合字符串
	 * @return 返回分割好的字符串
	 */
	private Map<String,Object> getVersionMaterialNumber(String arg)
	{
      String[] args=arg.split("\\|");
      String materialNumber=args[0];
      int version=Integer.parseInt(args[1]);
      Map<String,Object> map=new HashMap<>();
      map.put("materialNumber",materialNumber);
      map.put("version",version);
      return map;
	}

    private  List<Map<String,Integer>> getVersionList(String materialNumber)
	{
		List<Map<String,Integer>> integerList=new ArrayList<>();
		List<BOMHeadModel> bomHeadModels=bomHeadService.getRegisterProduct(materialNumber);
		int key=0;
		for (BOMHeadModel bomHeadModel : bomHeadModels)
		{
			Map<String,Integer> map=new HashMap<>();
			map.put("key",key);
			map.put("label",bomHeadModel.getVersion());
			integerList.add(map);
			key++;
		}
		return integerList;
	}


	private List<Map<String, Object>> getStationGroupListByCellId(int id)
	{
		List<StationGroup> stationGroups=processService.getStationGroupByCellId(id);
		List<Map<String, Object>> sGList=new ArrayList<>();
		for (StationGroup  stationGroup : stationGroups) {
			Map<String, Object> sG=new HashMap<>();
			sG.put("key", stationGroup.getId());
			sG.put("label" ,stationGroup.getDescription());
			sGList.add(sG);
		}
		return sGList;
	}
}
