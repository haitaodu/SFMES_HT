package com.smartflow.dao;

import com.smartflow.model.IdLayoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 18:00
 * @description：${description}
 * @modified By：
 * @version: version
 */
@Repository
public class IdLayOutDaoImpl  implements IdLayOutDao {
    final
    HibernateTemplate hibernateTemplate;

    @Autowired
    public IdLayOutDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void update(IdLayoutEntity idLayoutEntity) {
        hibernateTemplate.save(idLayoutEntity);
    }


    @Override
    public IdLayoutEntity getDataById(int id) {
        return  hibernateTemplate.get(IdLayoutEntity.class,id);
    }

    @Override
    public void save(IdLayoutEntity idLayoutEntity) {
      hibernateTemplate.save(idLayoutEntity);
    }

    @Override
    public void del(IdLayoutEntity idLayoutEntity) {
        hibernateTemplate.delete(idLayoutEntity);
    }
}
