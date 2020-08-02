package com.smartflow.controller;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.*;
import com.smartflow.model.JPH;
import com.smartflow.model.Material;
import com.smartflow.service.JPHService;
import com.smartflow.service.MaterialService;
import com.smartflow.util.MaterialDataForSearch;
import com.smartflow.util.ReadDataUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/JPH")
public class JPHController extends BaseController {
    private static Logger logger = Logger.getLogger(JPHController.class);
    final
    MaterialService materialService;

    final
    JPHService jphService;

    @Autowired
    public JPHController(MaterialService materialService, JPHService jphService) {
        this.materialService = materialService;
        this.jphService = jphService;
    }

    /**
     * 初始化列表查询条件
     * @return
     */
    @GetMapping (value = "/GetInitializeList")
    public Map<String,Object> getJPHListByConditionInit(){
        Map<String,Object> json ;
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> LocationList = materialService.getLocationNumberAndId();
            map.put("LocationList", LocationList);
            json = this.setJson(200, "初始化成功!", map);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "初始化失败："+e.getMessage(), 1);
        }
        return json;
    }


    /**
     * 根据物料号、库位查询JPH
     * @param getJPHListConditionInputDTO
     * @return
     */
    @RequestMapping(value = "/GetTByCondition",method = RequestMethod.POST)
    public Map<String,Object> getJPHListByCondition(@RequestBody GetJPHListConditionInputDTO getJPHListConditionInputDTO){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            Integer rowCount = jphService.getTotalCountJPHListByCondition(getJPHListConditionInputDTO);
            List<GetJPHListOutputDTO> JPHList = jphService.getJPHListByCondition(getJPHListConditionInputDTO);
            map.put("RowCount", rowCount);
            map.put("Tdto", JPHList);
            json = this.setJson(200, "查询成功!", map);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "查询失败："+e.getMessage(), null);
        }
        return json;
    }
    /**
     * 根据Id找出JPH的数据，用于ADM的JPH详情查询
     */
    @RequestMapping(value = "/GetTById/{Id}",method = RequestMethod.GET)
    public Map<String,Object> getJPHDetailById(@PathVariable Integer Id) {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("Tdto", jphService.getJPHDetailOutPutById(Id));
            json = this.setJson(200, "查询成功!", map);
        }catch(NullPointerException e)
        {
            logger.error(e);
            json = this.setJson(0, "查询失败：未查到对应数据", null);
        }

        return json;
    }

    @RequestMapping(value = "/Post",method = RequestMethod.POST)
    public Map<String,Object> saveJPH(@RequestBody AddJPHInputDTO addJPHInputDTO){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            jphService.addJPH(addJPHInputDTO);
            json = this.setJson(200, "添加成功!", 0);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "添加失败：请检查数据", -1);
        }
        return json;
    }
    @RequestMapping(value = "/Put",method = RequestMethod.PUT)
    public Map<String,Object> updateJPH(@RequestBody EditJPHInputDTO editJPHInputDTO){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            jphService.updateJPH(editJPHInputDTO);
            json = this.setJson(200, "修改成功!", 0);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "修改失败：请检查数据", -1);
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetMaterialByMaterialNumber", method = RequestMethod.POST)
    public @ResponseBody
    Object getMaterialByMaterialNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = ReadDataUtil.paramData(request);
        Map<String, Object> json = new HashMap<String, Object>();
        String materialNumber = jsonObject.get("MaterialNumber") == null ? "" :
                String.valueOf(jsonObject.get("MaterialNumber"));
        Map<String, Object> map = new HashMap<>();
        Material material=materialService.getMaterialByNumber(materialNumber);
        if (material == null || material.getState() == -1) {
            map.put("ReturnCode", 0);
            map.put("ReturnMessage", "你所请求的数据不存在");
            json = this.setJson(200, "sucesss", map);
            return json;

        }
        map.put("ReturnCode", 1);
        map.put("ReturnMessage", "请求数据成功");
        MaterialDataForSearch materialDataForSearch = new MaterialDataForSearch();
        materialDataForSearch.setMaterialName(material.getDescription());
        materialDataForSearch.setMaterialNumber(materialNumber);
        materialDataForSearch.setVersion(material.getVersion());
        map.put("Tdto", materialDataForSearch);
        try {
            json = this.setJson(200, "Success", map);
        } catch (Exception e) {
            json = this.setJson(0, e.getMessage(), 1);
            logger.error(e);
            e.printStackTrace();
        }
        return json;

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/GetAddInitialize",method = RequestMethod.GET)
    public Map<String,Object> getJPHLUpdateInit(){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            List<Map<String,Object>> LocationList =
                    materialService.getLocationNumberAndId();
            map.put("LocationList", LocationList);
            json = this.setJson(200, "初始化成功!", map);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "初始化失败："+e.getMessage(), 1);
        }
        return json;
    }

    /**
     *
     */
    @RequestMapping(value = "/GetEditInitialize/{Id}",method = RequestMethod.GET)
    public Map<String,Object> getJPHLSaveInit(@PathVariable Integer Id){
        Map<String,Object> json = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        Map<String, Object> Tdto=new HashMap<>();
        try{
            JPHUpdateOutPut jphUpdateOutPut=jphService.getJPHUpdateOutPutDTP(Id);
            Tdto.put("MaterialNumber",jphUpdateOutPut
                    .getMaterialNumber());
            Tdto.put("State",jphUpdateOutPut.getState());
            Tdto.put("JPH",jphUpdateOutPut.getJPH());
            Tdto.put("LoactionId",jphUpdateOutPut.
                    getLoactionId());
            Tdto.put("Id",jphUpdateOutPut.getId());
            map.put("Tdto",Tdto);
            map.put("LocationList", jphUpdateOutPut.
                    getLocationList());
            json = this.setJson(200, "初始化成功!", map);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            json = this.setJson(0, "初始化失败："+e.getMessage(), 1);
        }
        return json;
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public @ResponseBody
    Object delDataById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        {
            Map<String, Object> json = new HashMap<>();
            try {
                json = this.setJson(200, "Success", 0);
            JSONObject jsonObject = ReadDataUtil.paramData(request);
            @SuppressWarnings("unchecked")
            List<Integer> list = (List<Integer>) jsonObject.get("List");
            if (list.size() == 0) {
                json = this.setJson(204, "未选择删除数据", 1);
                return (json);
            }
            for (Integer Id : list) {
                JPH jph=jphService.getJPHById(Id);
                if (jph == null) {
                    json = this.setJson(202, "所删除数据为空", 0);
                    return (json);
                } else if (jph.getState() == -1) {
                    json = this.setJson(203, "该数据已删除", -1);
                    return (json);
                }
                jphService.deleteJPH(Id);
            }
        }
            catch (Exception e){
                json = this.setJson(0, e.getMessage(), 1);
                logger.error(e);
                e.printStackTrace();
            }
            finally {
                return  json;
            }
        }
    }




}
