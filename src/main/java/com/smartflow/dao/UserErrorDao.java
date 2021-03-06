package com.smartflow.dao;

import com.smartflow.model.UserError;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/10/21 18:15
 * @description：${description}
 */
public interface UserErrorDao {
    void insert(UserError userError);

    void update(UserError  userError);

    UserError getById(int id);

    List<UserError> getByErrorId(int errorId);
}
