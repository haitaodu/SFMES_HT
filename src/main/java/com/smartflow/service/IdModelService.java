package com.smartflow.service;

import com.smartflow.dto.idmode.ModelEditInitialize;
import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.view.idmodel.IdModelDetailView;
import com.smartflow.view.idmodel.IdModelPageView;
import com.smartflow.view.idmodel.IdModelSaveView;
import com.smartflow.view.idmodel.IdModelUpdateView;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 14:07
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IdModelService {
    /**
     * 根据前端数据，查找分页数据
     * @param modelIdConditionInputDTO 前端传入数据
     * @return 分页数据
     */
    List<IdModelPageView> getPage(ModelIdConditionInputDTO modelIdConditionInputDTO);

    /**
     * 根据前端传来的数据作删除处理
     * @param id id号
     */
    void del(Integer id);

    /**
     * 保存
     * @param idModelSaveView 前端视图
     */
    void save(IdModelSaveView idModelSaveView);

    /**
     * 更新
     * @param ipUpdateView 前端的更新视图
     */
    void update(IdModelUpdateView ipUpdateView);

    /**
     * 根据id查找详情
     * @param id id号
     * @return 详情视图
     */
    IdModelDetailView getDetail(int id);

    /**
     * 模糊查询出数据库的总的实体数据。用于前端作分页处理
     * @param modelIdConditionInputDTO 前端输入的查询条件
     * @return 返回总的条目数
     */
    Integer getCount(ModelIdConditionInputDTO modelIdConditionInputDTO);

    /**
     *用于更改菜单的初始化
     * @param id 根据Id初始化ModelEdit
     * @return 返回初始化的更改
     */
    ModelEditInitialize getModelEditInit(int id);



}
