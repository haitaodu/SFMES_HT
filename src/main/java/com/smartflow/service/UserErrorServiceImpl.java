package com.smartflow.service;

import com.smartflow.dao.UserErrorDao;
import com.smartflow.model.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/10/22 10:06
 * @description：${description}
 * @modified By：
 * @version: version
 */
@Transactional
@Service
public class UserErrorServiceImpl implements UserErrorService{
private final
UserErrorDao userErrorDao;

    @Autowired
    public UserErrorServiceImpl(UserErrorDao userErrorDao) {
        this.userErrorDao = userErrorDao;
    }

    @Override
    public void insert(UserError userError) {
        userErrorDao.insert(userError);
    }

    @Override
    public void update(UserError userError) {
        userErrorDao.update(userError);
    }

    @Override
    public UserError getById(int id) {
        return userErrorDao.getById(id);
    }

    @Override
    public List<UserError> getByErrorId(int errorId) {
        return userErrorDao.getByErrorId(errorId);
    }
}
