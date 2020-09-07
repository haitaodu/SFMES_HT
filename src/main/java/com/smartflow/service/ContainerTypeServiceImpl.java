package com.smartflow.service;

import com.smartflow.dao.ContainerTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ：tao
 * @date ：Created in 2020/9/7 17:31
 * @description：${description}
 * @modified By：
 * @version: version
 */

@Service
public class ContainerTypeServiceImpl implements  ContainerTypeService{
 @Autowired
    ContainerTypeDao containerTypeDao;
    @Override
    public List<Map<String, Object>> getContainerType() {
        return containerTypeDao.getContainerType();
    }
}
