package com.smartflow.dao;

import com.smartflow.model.Qualification;
import com.smartflow.model.StationAccessControl;
import com.smartflow.model.User_Qualification;
import java.util.Map;

import java.util.List;

public interface QualificationDao {
    /**
     * 添加资质
     * @param qualification
     */
    public void addQualification(Qualification qualification);

    /**
     * 添加工站权限控制
     * @param stationAccessControl
     */
    public void addStationAccessControl(StationAccessControl stationAccessControl);

    /**
     * 修改资质
     * @param qualification
     */
    public void updateQualification(Qualification qualification);

    /**
     * 修改工站权限控制
     * @param stationAccessControl
     */
    public void updateStationAccessControl(StationAccessControl stationAccessControl);

    /**
     * 根据工站id获取可访问的工站
     * @param stationId
     */
    public StationAccessControl getStationAccessControlByStationId(Integer stationId);

    /**
     * 根据用户id查询该用户可以访问的工站
     * @param userId
     */
    public List<String> getStationAccessControlListByUserId(Integer userId);

    /**
     * 根据用户id查询可访问的工站下拉框
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getStationListByUserId(Integer userId);

    /**
     * 根据工站id查询资质id
     * @param stationIdList
     * @return
     */
    public List<StationAccessControl> getQualificationIdByStationIdList(List<Integer> stationIdList);

    /**
     * 添加用户资质中间表
     * @param userQualification
     */
    public void addUser_Qualification(User_Qualification userQualification);

    /**
     * 根据资质id获取资质
     * @param qualificationId
     */
     public Qualification getQualificationById(Integer qualificationId);

    /**
     * 根据用户资质id查询用户资质对象
     * @param userQualificationId
     * @return
     */
//     public User_Qualification getUserQualificationById(Integer userQualificationId);

    /**
     * 删除用户资质中间表
     * @param userId
     */
     public void deleteUser_QualificationByUserId(Integer userId);
}
