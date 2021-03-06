package com.smartflow.service;

import com.smartflow.model.UserError;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/10/22 10:00
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface UserErrorService {
    void insert(UserError userError);

    void update(UserError  userError);

    UserError getById(int id);

    List<UserError> getByErrorId(int errorId);
}
