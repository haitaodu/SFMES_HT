package com.smartflow.dao;

import com.smartflow.model.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/10/21 18:16
 * @description：${description}
 */
@Repository
public class UserErrorDaoImpl implements  UserErrorDao {
    final
    HibernateTemplate hibernateTemplate;

    @Autowired
    public UserErrorDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    @Override
    public void insert(UserError userError)
    {
     hibernateTemplate.save(userError);
    }

    @Override
    public void update(UserError userError)
    {
        hibernateTemplate.update(userError);
    }

    @Override
    public UserError getById(int id) {
        return (UserError) hibernateTemplate.findByNamedParam
                ("from UserError where errorid=:errorid",
                        "errorid",id).get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserError> getByErrorId(int errorId) {
        return (List<UserError>) hibernateTemplate.findByNamedParam
                ("from UserError where errorid=:errorid",
                        "errorid",errorId);

    }
}
