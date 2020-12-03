package com.smartflow.controller;

import com.alibaba.fastjson.JSONObject;
import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.service.BOMHeadService;
import com.smartflow.service.IdModelService;
import com.smartflow.service.StationGroupService;
import com.smartflow.util.ReadDataUtil;
import com.smartflow.view.idmodel.IdModelSaveView;
import com.smartflow.view.idmodel.IdModelUpdateView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：tao
 * @date ：Created in 2020/6/15 13:48
 * @description：${description}
 */

@RestController
@RequestMapping("/api/idModel")
public class IdModelController extends BaseController {
    private final
    ThreadPoolTaskExecutor taskExecutor;
    private final
    IdModelService idModelService;
    private final
    BOMHeadService bomHeadService;
    private final
    StationGroupService stationService;
    private static Logger logger = LoggerFactory.getLogger(IdModelController.class);
    Map<String, Object> json = new HashMap<>();
    private static final String GET_SUCESS = "查询成功!";
    private static final String GET_FALL = "查询失败：未查到对应数据!";

    @Autowired
    public IdModelController(IdModelService idModelService, StationGroupService stationService,
                             ThreadPoolTaskExecutor taskExecutor, BOMHeadService bomHeadService) {
        this.idModelService = idModelService;
        this.taskExecutor = taskExecutor;
        this.stationService = stationService;
        this.bomHeadService = bomHeadService;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value = "/GetTByCondition")
    public Map<String, Object> getIdModelPageList
            (@RequestBody ModelIdConditionInputDTO modelIdConditionInputDTO) {
        Map<String, Object> map = new HashMap<>(16);
        CountDownLatch latch = new CountDownLatch(2);
        try {
            taskExecutor.execute(() -> {
                map.put("Tdto", idModelService.getPage(modelIdConditionInputDTO));
                latch.countDown();
            });

            taskExecutor.execute(() -> {
                map.put("RowCount", idModelService.getCount(modelIdConditionInputDTO));
                latch.countDown();
            });
            latch.await();
            json = this.setJson(200, GET_SUCESS, map);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "/GetTById/{id}")
    public Map<String, Object> getDetailById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>(10);
        try {
            map.put("Tdto", idModelService.getDetail(id));
            json = this.setJson(200, GET_SUCESS, map);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            json = this.setJson(0, GET_FALL, null);
        }


        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping(value = "/Post")
    public Map<String, Object> save(@RequestBody IdModelSaveView idModelSaveView) {
        try {
            if (!idModelSaveView.getVaildFrom().before(idModelSaveView.getVaildTo())) {
                json = this.setJson(-1, "添加失败：开始时间大于结束时间", 0);
                return json;
            }
            idModelService.save(idModelSaveView);

            json = this.setJson(200, "添加成功!", 0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = this.setJson(-1, "添加失败：请检查数据", -1);
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PutMapping(value = "/Put")
    public Map<String, Object> update(@RequestBody IdModelUpdateView idModelUpdateView) {
        try {
            if (!idModelUpdateView.getVaildFrom().before(idModelUpdateView.getVaildTo())) {
                json = this.setJson(-1, "修改失败：请检查数据", 0);
                return json;
            }
            idModelService.update(idModelUpdateView);
            json = this.setJson(200, "修改成功!", 0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = this.setJson(0, "修改失败：请检查数据", -1);
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping (value = "/Delete")
    @SuppressWarnings("unchecked")
    public @ResponseBody Map<String, Object> del(HttpServletRequest request) {
        try {
            JSONObject jsonObject = ReadDataUtil.paramData(request);
            List<Integer> list=(List<Integer>)  jsonObject.get("List");
            if (list.isEmpty())
            {
                json = this.setJson(200, "没选择删除数据!", 0);
                 return json;
            }
            for (int id:list
                 ) {
                idModelService.del(id);

            }

            json = this.setJson(200, "删除成功!", 0);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = this.setJson(0, "删除失败", null);
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "/GetEditInitialize/{id}")
    public Map<String, Object> getEditInitialize(@PathVariable Integer id) {
        try {
            Map<String, Object> map = new HashMap<>(10);
            map.put("Tdto", idModelService.getModelEditInit(id));
            json = this.setJson(200, GET_SUCESS, getInitialize(map));
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            json = this.setJson(0, GET_FALL, null);
        }
        return json;
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "/Initialize")
    public Map<String, Object> initialize() {
        try {
            Map<String, Object> map = new HashMap<>(10);
            json = this.setJson(200, GET_SUCESS, getInitialize(map));
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            json = this.setJson(0, GET_FALL, null);
        }
        return json;
    }

    /**
     * 返回初始化数据
     *
     * @param map map
     * @return 初始化数据，站列表，bom列表
     */
    private Map<String, Object> getInitialize(Map<String, Object> map) {
        map.put("StationList", stationService.getStation());
        map.put("BOMHeadList", bomHeadService.getProdcutNameList());
        return map;
    }
}
