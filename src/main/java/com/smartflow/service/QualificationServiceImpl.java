package com.smartflow.service;

import com.smartflow.dao.QualificationDao;
import com.smartflow.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    QualificationDao qualificationDao;
    @Override
    public List<String> getStationAccessControlListByUserId(Integer userId) throws Exception {
        return qualificationDao.getStationAccessControlListByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> getStationListByUserId(Integer userId) {
        return qualificationDao.getStationListByUserId(userId);
    }

    @Override
    public void addQualificationAndStationAccessControl(Station station) {
        Qualification qualification = new Qualification();
        qualification.setQualificationCode(station.getStationNumber());
        qualification.setName(station.getName());
        qualification.setState(1);
        qualification.setEditDateTime(new Date());
        qualification.setEditorId(station.getEditorId());
        qualificationDao.addQualification(qualification);
        StationAccessControl stationAccessControl = new StationAccessControl();
        stationAccessControl.setStation(station);
        stationAccessControl.setQualification(qualification);
        stationAccessControl.setState(1);
        stationAccessControl.setEditDateTime(new Date());
        stationAccessControl.setEditorId(station.getEditorId());
        qualificationDao.addStationAccessControl(stationAccessControl);
    }

    @Override
    public void updateQualificationAndStationAccessControl(Station station) {
        StationAccessControl stationAccessControl = qualificationDao.getStationAccessControlByStationId(station.getId());
        stationAccessControl.setState(station.getState());
        stationAccessControl.setEditDateTime(new Date());
        stationAccessControl.setEditorId(station.getEditorId());
        qualificationDao.updateStationAccessControl(stationAccessControl);
        Qualification qualification = stationAccessControl.getQualification();
        qualification.setQualificationCode(station.getStationNumber());
        qualification.setName(station.getName());
        qualification.setState(station.getState());
        qualification.setEditDateTime(new Date());
        qualification.setEditorId(station.getEditorId());
        qualificationDao.updateQualification(qualification);
    }

    @Override
    public void addUser_Qualification(List<Integer> stationIdList, User user) throws Exception{
        List<StationAccessControl> qualificationIdList = qualificationDao.getQualificationIdByStationIdList(stationIdList);
        for (StationAccessControl stationAccessControl:qualificationIdList) {
            User_Qualification userQualification = new User_Qualification();
            userQualification.setUser(user);
            userQualification.setQualification(qualificationDao.getQualificationById(stationAccessControl.getQualification().getId()));
            userQualification.setValidateFrom(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 3000);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            userQualification.setValidateTo(calendar.getTime());
            userQualification.setState(1);
            userQualification.setEditDateTime(new Date());
            userQualification.setEditorId(user.getCreatorId());
            qualificationDao.addUser_Qualification(userQualification);
        }
    }

    @Override
    public void deleteUser_QualificationByUserId(Integer userId) throws Exception{
        qualificationDao.deleteUser_QualificationByUserId(userId);
    }
}
