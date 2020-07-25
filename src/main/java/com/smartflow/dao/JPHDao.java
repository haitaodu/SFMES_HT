package com.smartflow.dao;

import com.smartflow.dto.GetJPHListConditionInputDTO;
import com.smartflow.model.JPH;

import java.util.List;

public interface JPHDao {
    /**
     * 根据物料号、库位查询JPH列表总条数
      * @param getJPHListConditionInputDTO
     * @return
     */
   Integer getTotalCountJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO);
    /**
     * 根据物料号、库位查询JPH列表
     * @param getJPHListConditionInputDTO
     * @return
     */
     List<JPH> getJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO);

    /**
     * 添加JPH
     * @param jph
     */
     void addJPH(JPH jph);

    /**
     * 修改JPH
     * @param jph
     */
     void updateJPH(JPH jph);

    /**
     * 根据jphId获取JPH详情
     * @param jphId
     * @return
     */
     JPH getJPHById(Integer jphId);

    /**
     * 根据jph删除JPH
     * @param jph
     */
     void deleteJPH(JPH jph);
}
