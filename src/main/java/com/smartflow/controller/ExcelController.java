package com.smartflow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.CreateProcessDTO;
import com.smartflow.dto.EditProcessDTO;
import com.smartflow.model.BOMHeadModel;
import com.smartflow.model.ProcessModel;
import com.smartflow.model.ProcessStep;
import com.smartflow.model.StationGroup;
import com.smartflow.service.*;
import com.smartflow.util.*;
import com.smartflow.view.Process.ProcessItemEditeView;
import com.smartflow.view.Process.ProcsessEditeView;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Process")
public class ExcelController extends BaseController {
    private static Logger logger = Logger.getLogger(ExcelController.class);
    @Autowired
    MaterialService materialService;

    @Autowired
    CellService cellService;

    @Autowired
    ProcessService processService;

    @Autowired
    BOMHeadService bomHeadService;

    @Autowired
    StationGroupService stationGroupService;
    Map<String, Object> json = new HashMap<String, Object>(9);
    private static final  String STATUS_CODE="Status";
    private static final  int   STATUS_ERROR=101;
    /**
     * @Title: getExcelTemplate
     * @Description: 生成Excel模板并导出
     * @param @param uuid
     * @param @param request
     * @param @param response
     * @param @return
     * @return Data
     * @throws
     */
    @GetMapping("/GetExcelTemplate/{cellId}")
    public Map<String,Object> getExcelTemplate(@PathVariable Integer cellId, HttpServletRequest request){
        Map<String,Object> json = new HashMap<>();
        String fileName = "工艺导入Excel模板.xls"; //模板名称
        String[] headers = {"工站组","描述","是否需要上料检验"}; //列标题
        List<String> strList = new ArrayList<>();
        List<Map<String,Object>> stationGroupList = getStationGroupListByCellId(cellId);
        if(!CollectionUtils.isEmpty(stationGroupList)){
            for (Map<String,Object> map:stationGroupList) {
                String key = String.valueOf(map.get("key"));
                String value = String.valueOf(map.get("label"));
                strList.add(key+"-"+value);
            }
        }
        //下拉框数据
        List<String[]> downData = new ArrayList();
        String[] str1 = strList.toArray(new String[0]);
        String[] str2 = {"是", "否"};
        downData.add(str1);
        downData.add(str2);
        String[] downRows = {"0","2"}; //下拉的列序号数组(序号从0开始)
        String webappPath = request.getSession().getServletContext().getRealPath("/upload");
        String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/";
        try {
            //"C:\\Users\\smartflow\\Desktop\\"+fileName+".xls"
            ExcelUtil.createExcelTemplate(webappPath+"\\"+fileName, headers, downData, downRows, null);//, request, response
            json = this.setJson(200, "获取工艺导入模板成功", filePath+fileName);
        } catch (Exception e) {
            logger.error("批量导入信息异常：" + e.getMessage());
        }
        return json;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value="/Post")
    public Map<String, Object> importExcel(@RequestParam("upfile") CommonsMultipartFile upfile, @Valid CreateProcessDTO createProcessDTO,BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                json = this.setJson(0, errorMessage, -1);
                return json;
            }
            init();
            ProcessModel processModel = parseToProcessHeadForAdd(createProcessDTO);
            if (json.get(STATUS_CODE)!=null&&(int)json.get(STATUS_CODE)==STATUS_ERROR)
            {
                return json;
            }
            System.out.println(upfile.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("/upload");
            System.out.println(path);
            File file =  new File(path, upfile.getOriginalFilename());

            FileUtils.copyInputStreamToFile(upfile.getInputStream(),file);
            ExcelReader excelReader = new ExcelReader();
            List<Map<String, Object>> dataList = excelReader.readExcel(file.getPath(),1);
            List<Map> mlist = ExcelUtil.toSingle(dataList);
            List<ProcessStep> processSteps = new ArrayList<>();
            int i = 2;
            for(Map m : mlist){
                ProcessStep processStep = new ProcessStep();
                processStep.setEditorId(createProcessDTO.getCreatorId());
                processStep.setEditDateTime(new Date());
                String description = m.get("Description") == null ? null : m.get("Description").toString();
                String stationGroupValue = m.get("StationGroupId") == null ? null : m.get("StationGroupId").toString();
                if(StringUtils.isEmpty(stationGroupValue)){
                    json = this.setJson(0, "在excel第"+i+"行中选择的工站组不能为空", 1);
                    return json;
                }
                if(StringUtils.isEmpty(description)){
                    json = this.setJson(0, "在excel第"+i+"行中输入的描述不能为空", 1);
                    return json;
                }
                processStep.setDescription(description);
                Integer stationGroupId = Integer.parseInt(stationGroupValue.split("-")[0]);
                processStep.setStationGroupId(stationGroupId);
                processStep.setIsNeedSetupCheck(Boolean.valueOf(m.get("IsNeedSetupCheck").toString()));
                processStep.setSecquence(i-1);
                processSteps.add(processStep);
                i++;
            }
            processService.addData(processModel,processSteps);
            json = this.setJson(200, "添加成功", 0);
        }catch(Exception e){
            e.printStackTrace();
            json = this.setJson(0, e.getMessage(), 1);
        }
        return json;
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value="/GetEditInitialize/{Id}",method=RequestMethod.GET)
    public @ResponseBody Object getEditInitializeById(@PathVariable Integer Id, HttpServletRequest request)
    {
        Map<String, Object> json;
        try {
            Map<String, Object> map=new  HashMap<>();
            ProcsessEditeView procsessEditeView=processService.getProcessEditeView(Id);
            map.put("StationGroup", getStationGroupListByCellId(procsessEditeView.getFactoryId()));
            map.put("VersionList",getVersionList(procsessEditeView.getMaterialNumber()));
            map.put("FactoryList", cellService.getCellListInit());
            map.put("Process", procsessEditeView);
            List<ProcessItemEditeView> processDataForPages=processService.getProcessStepEditeById(Id);

//            map.put("ProcessStepList",processDataForPages);
//            if (processDataForPages.isEmpty()) {
//                map.put("ProcessStepListCount", 0);
//            }
//            else {
//                map.put("ProcessStepListCount",
//                        processDataForPages.get(processDataForPages.size()-1).getSecquence());
//            }
            List<Map<String, String>> processStepList = new ArrayList<>();
            for (ProcessItemEditeView processItem:processDataForPages) {
                Map<String, String> processStep = new HashMap<>();
                StationGroup stationGroup = stationGroupService.getStationGroupById(processItem.getStationGroupId());
                processStep.put("StationGroupId", stationGroup.getId()+"-"+stationGroup.getDescription());
                processStep.put("Description", processItem.getDescription());
                processStep.put("IsNeedSetupCheck", processItem.isIsNeedSetupCheck() == true ? "是" : "否");
                processStepList.add(processStep);
            }

            String fileName = "工艺编辑Excel模板.xls"; //模板名称
            String[] headers = {"工站组","描述","是否需要上料检验"}; //列标题
            List<String> strList = new ArrayList<>();
            List<Map<String,Object>> stationGroupList = getStationGroupListByCellId(procsessEditeView.getFactoryId());
            if(!CollectionUtils.isEmpty(stationGroupList)){
                for (Map<String,Object> stationGroup : stationGroupList) {
                    String key = String.valueOf(stationGroup.get("key"));
                    String value = String.valueOf(stationGroup.get("label"));
                    strList.add(key+"-"+value);
                }
            }
            //下拉框数据
            List<String[]> downData = new ArrayList();
            String[] str1 = strList.toArray(new String[0]);
            String[] str2 = {"是", "否"};
            downData.add(str1);
            downData.add(str2);
            String[] downRows = {"0","2"}; //下拉的列序号数组(序号从0开始)
            String webappPath = request.getSession().getServletContext().getRealPath("/upload");
            String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/";
            ExcelUtil.createExcelTemplate(webappPath+"\\"+fileName, headers, downData, downRows, processStepList);
            map.put("FilePath", filePath+fileName);
            json = this.setJson(200, "查询成功", map);
        } catch (Exception e) {
            json = this.setJson(0, e.getMessage(),1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value="/Put")
    public @ResponseBody Object updateDataForProcess(@RequestParam("upfile") CommonsMultipartFile upfile,
                                                     @Valid EditProcessDTO editProcessDTO, BindingResult result,
                                                     HttpServletRequest request) throws Exception
    {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            json = this.setJson(0, errorMessage, -1);
            return json;
        }
        if ("".equals(editProcessDTO.getValidBegin())) {
            json= this.setJson(STATUS_ERROR, "请选择生效时间", -1);
            return json;
        }
        if ("".equals(editProcessDTO.getValidEnd())) {
            json= this.setJson(STATUS_ERROR, "请选择失效时间", -1);
            return json;
        }
        ProcessModel processModel = processService.getProcessDataById(editProcessDTO.getId());
        Date nowTime=new Date();
        processModel.setEditDateTime(nowTime);
        processModel.setEditorId(editProcessDTO.getEditorId());
        processModel.setCell(cellService.getCellById(editProcessDTO.getFactoryId()));
        processModel.setProcessNumber(editProcessDTO.getProcessNumber());
        processModel.setState(editProcessDTO.getState());

//        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        String validBegin = editProcessDTO.getValidBegin().replace("Z", " UTC");
//        Date vBDate = utcFormat.parse(validBegin);
//        String validEnd = editProcessDTO.getValidEnd().replace("Z", " UTC");
//        Date vEDate= utcFormat.parse(validEnd);
        Date vBDate = new Date(editProcessDTO.getValidBegin());
        Date vEDate= new Date(editProcessDTO.getValidEnd());
        processModel.setValidBegin(vBDate);
        processModel.setValidEnd(vEDate);
        processModel.setDescription(editProcessDTO.getDescription());
        processModel.setVersion(editProcessDTO.getVersion());
        processModel.setParentProcessNumber(editProcessDTO.getParentProcessNumber());
        processModel.setId(editProcessDTO.getId());
        if (vBDate.after(vEDate))
        {
            json= this.setJson(STATUS_ERROR, "生效时间大于失效时间", -1);
            return json;
        }
        processModel.setValidBegin(vBDate);
        processModel.setValidEnd(vEDate);


        String path = request.getSession().getServletContext().getRealPath("/upload");
        File file =  new File(path, upfile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(upfile.getInputStream(),file);
        ExcelReader excelReader = new ExcelReader();
        List<Map<String, Object>> dataList = excelReader.readExcel(file.getPath(),1);
        List<Map> mlist = ExcelUtil.toSingle(dataList);
        List<ProcessStep> processSteps = new ArrayList<>();
        int i = 2;
        for(Map m : mlist){
            ProcessStep processStep = new ProcessStep();
            processStep.setEditorId(editProcessDTO.getEditorId());
            processStep.setEditDateTime(new Date());
            String description = m.get("Description") == null ? null : m.get("Description").toString();
            String stationGroupValue = m.get("StationGroupId") == null ? null : m.get("StationGroupId").toString();
            if(StringUtils.isEmpty(stationGroupValue)){
                json = this.setJson(0, "在excel第"+i+"行中选择的工站组不能为空", 1);
                return json;
            }
            if(StringUtils.isEmpty(description)){
                json = this.setJson(0, "在excel第"+i+"行中输入的描述不能为空", 1);
                return json;
            }
            processStep.setDescription(description);
            Integer stationGroupId = Integer.parseInt(stationGroupValue.split("-")[0]);
            processStep.setStationGroupId(stationGroupId);
            processStep.setIsNeedSetupCheck(Boolean.valueOf(m.get("IsNeedSetupCheck").toString()));
            processStep.setSecquence(i-1);
            processSteps.add(processStep);
            i++;
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

    private void init()
    {
        this.json=new HashMap<>();
    }

    private ProcessModel parseToProcessHeadForAdd(CreateProcessDTO createProcessDTO) throws ParseException {

        String validBegin = createProcessDTO.getValidBegin();
        String validEnd = createProcessDTO.getValidEnd();

//        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        validBegin = validBegin.replace("Z", " UTC");
//        Date vBDate = utcFormat.parse(validBegin);
//        validEnd = validEnd.replace("Z", " UTC");
//        Date vEDate= utcFormat.parse(validEnd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date vBDate = sdf.parse(validBegin);
        Date vEDate= sdf.parse(validEnd);
        Date nowTime = new Date();
        ProcessModel processModel=new ProcessModel();
        if (StringUtils.isEmpty(validBegin)) {
            json= this.setJson(STATUS_ERROR, "请选择生效时间", -1);
            return processModel;
        }
        if (StringUtils.isEmpty(validEnd)) {
            json = this.setJson(STATUS_ERROR, "请选择失效时间", -1);
            return processModel;
        }
        if (processService.isRegister(createProcessDTO.getProcessNumber()))
        {
            json= this.setJson(STATUS_ERROR, "此工艺号已被注册", -1);
            return processModel;
        }
        Map<String,Object> map=getVersionMaterialNumber(createProcessDTO.getMaterialSplits());
        if (materialService.getMaterialByNumber((String) map.get("materialNumber"))==null) {
            json= this.setJson(STATUS_ERROR, "你输入的物料号有错", -1);
            return processModel;
        }
        processModel.setCreationDateTime(nowTime);
        processModel.setCreatorId(createProcessDTO.getCreatorId());
        processModel.setEditDateTime(nowTime);
        processModel.setEditorId(createProcessDTO.getCreatorId());
        processModel.setCell(createProcessDTO.getFactoryId() == null ? null : cellService.getCellById(createProcessDTO.getFactoryId()));
        processModel.setProcessNumber(createProcessDTO.getProcessNumber());
        processModel.setState(1);
        processModel.setMaterialId(materialService.getMaterialByNumber((String) map.get("materialNumber")).getId());
        processModel.setValidBegin(vBDate);
        processModel.setValidEnd(vEDate);
        processModel.setDescription(createProcessDTO.getDescription());
        processModel.setVersion((int)map.get("version"));
        processModel.setParentProcessNumber(createProcessDTO.getParentProcessNumber());
        return processModel;
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
}
