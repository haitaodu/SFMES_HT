package com.smartflow.dao;

import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.model.IdLayoutEntity;
import com.smartflow.model.IdModelEntity;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 10:48
 * @description：存取数据的接口
 * @modified By：
 * @version: version
 */

public interface IdModelDao {

    /**
     *分页查询
     * @param modelIdConditionInputDTO 分页查找条件DTO
     * @return IdModel列表
     */
    List<IdModelEntity>  getIdModelPageSearch(ModelIdConditionInputDTO modelIdConditionInputDTO);

    /**
     *根据外键ModelId或取IdLayOut
     * @param id ModelId
     * @return IdLayOut实体数组
     */
    List<IdLayoutEntity> getByIdModelId(int id);

    /**
     *根据前端的数据模糊查询出所有满足条件的Entiry
     * @param modelIdConditionInputDTO 分页查找条件
     * @return 返回总的满足条件的实体数
     */
    int getTotalEntity(ModelIdConditionInputDTO modelIdConditionInputDTO);

    /**
     * 保存实体
     * @param idModelEntity 实体
     */
    void  save(IdModelEntity idModelEntity);

    /**
     * 更新实体
     * @param idModelEntity 实体
     */
    void update(IdModelEntity idModelEntity);

    /**
     * 根据id号作假删除
     * @param id id
     */
    void del(int id);

    /**
     *根据id号获取实体数据
     * @return 返回实体
     * @param id id
     */
    IdModelEntity getEntityById(int id);

}
