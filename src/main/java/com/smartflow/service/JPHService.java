package com.smartflow.service;

import com.smartflow.dto.*;
import com.smartflow.model.JPH;

import java.util.List;

public interface JPHService {
    /**
     * 根据物料号、库位查询JPH列表总条数
     * @param getJPHListConditionInputDTO
     * @return
     */
    public Integer getTotalCountJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO);

    /**
     * 根据物料号、库位查询JPH列表
     * @param getJPHListConditionInputDTO
     * @return
     */
    public List<GetJPHListOutputDTO> getJPHListByCondition(GetJPHListConditionInputDTO getJPHListConditionInputDTO);

    /**
     * 添加JPH
     * @param addJPHInputDTO
     */
    public void addJPH(AddJPHInputDTO addJPHInputDTO);

    /**
     * 修改JPH
     * @param editJPHInputDTO
     */
    public void updateJPH(EditJPHInputDTO editJPHInputDTO);

    /**
     * 根据jphId获取JPH详情
     * @param jphId
     * @return
     */
    public JPH getJPHById(Integer jphId);

    /**
     * 根据jph删除JPH
     * @param Id
     */
    public void deleteJPH(Integer Id);

    /**
     *
     * @param Id
     * @return  JPH详情
     */
    public JPHDetailOutPut getJPHDetailOutPutById(Integer Id);

    /**
     *
     * @param Id
     * @return JPH的编辑初始化
     */
    public JPHUpdateOutPut getJPHUpdateOutPutDTP(Integer Id);
}
