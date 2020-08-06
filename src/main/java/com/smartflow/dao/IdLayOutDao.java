package com.smartflow.dao;

import com.smartflow.model.IdLayoutEntity;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 17:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IdLayOutDao {

    /**
     * 更细数据
     * @param idLayoutEntity 实体
      */
    void update(IdLayoutEntity idLayoutEntity);


    /**
     * 根据Id号查找
     * @param id id
     * @return 返回实体
     */
    IdLayoutEntity getDataById(int id);

    /**
     * 保存实体
     * @param idLayoutEntity 实体
     */
    void save(IdLayoutEntity idLayoutEntity);

    /**
     * 真的删除idLayout的数据
     * @param idLayoutEntity idLayout的实体
     */
    void del(IdLayoutEntity idLayoutEntity);
}
