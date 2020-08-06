package com.smartflow.service;

import com.smartflow.model.Qualification;
import com.smartflow.model.Station;
import com.smartflow.model.User;

import java.util.List;
import java.util.Map;

public interface QualificationService {
    /**
     * 根据用户id查询该用户可以访问的工站
     * @param userId
     */
    public List<String> getStationAccessControlListByUserId(Integer userId)throws Exception;

    /**
     * 根据用户id查询可访问的工站下拉框
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getStationListByUserId(Integer userId);

    /**
     * 添加资质表
     * @param station
     */
    public void addQualificationAndStationAccessControl(Station station);

    /**
     * 修改资质表
     * @param station
     */
    public void updateQualificationAndStationAccessControl(Station station);

    /**
     * 添加用户资质中间表
     * @param stationIdList
     * @param user
     */
    public void addUser_Qualification(List<Integer> stationIdList, User user) throws Exception;

    /**
     * 删除用户资质中间表
     * @param userId
     */
    public void deleteUser_QualificationByUserId(Integer userId) throws Exception;
}
