package com.smartflow.dao;

import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.model.IdLayoutEntity;
import com.smartflow.model.IdModelEntity;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 11:11
 * @description:主表为IdModel的数据库存取接口
 * @modified By：
 * @version: version
 */
@Repository
public class IdModelDaoImpl implements  IdModelDao{
    final
    HibernateTemplate hibernateTemplate;
    private  org.apache.log4j.Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    public IdModelDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    @SuppressWarnings("all")
    @Override
    public List<IdModelEntity> getIdModelPageSearch(ModelIdConditionInputDTO modelIdConditionInputDTO) {
        Session session=hibernateTemplate.getSessionFactory().openSession();
      try {
          String hql="from IdModelEntity where State!=-1";
          Query query=session.createQuery
                  (hqlForPageList(hql,modelIdConditionInputDTO.getModelName(),
                          modelIdConditionInputDTO.getModelCode(),
                          modelIdConditionInputDTO.getProductName(),
                          modelIdConditionInputDTO.getStationNumber()));
          query.setFirstResult
                  ((modelIdConditionInputDTO.getPageIndex()-1)*modelIdConditionInputDTO.getPageSize());
          query.setMaxResults(modelIdConditionInputDTO.getPageSize());
         return  (List<IdModelEntity>)query.list();
      }
      catch (Exception e)
      {
          logger.info(e);
          return new ArrayList<>();
      }
      finally {
      session.close();
      }

    }

    @SuppressWarnings("all")
    @Override
    public List<IdLayoutEntity> getByIdModelId(int id) {
        return (List<IdLayoutEntity>) hibernateTemplate.findByNamedParam
                ("from IdLayoutEntity where idModelId=:arg order by  sequence","arg",id);
    }

    @Override
    public int getTotalEntity(ModelIdConditionInputDTO modelIdConditionInputDTO) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "select count(*) from IdModelEntity where State!=-1";
            Query query = session.createQuery(hqlForPageList(hql, modelIdConditionInputDTO.getModelName(),
                    modelIdConditionInputDTO.getModelCode(),
                    modelIdConditionInputDTO.getProductName(),
                    modelIdConditionInputDTO.getStationNumber()));
            return query.uniqueResult() == null ? 0 : Integer.parseInt(query.uniqueResult().toString());
        }
        catch (Exception e)
        {
            logger.info(e.getCause());
            return 0;
        }
        finally {
            session.close();
        }
    }
    @Override
    public void save(IdModelEntity idModelEntity) {
        hibernateTemplate.save(idModelEntity);
    }

    @Override
    public void update(IdModelEntity idModelEntity) {
        hibernateTemplate.update(idModelEntity);
    }

    @Override
    public void del(int id) {
   IdModelEntity idModelEntity=hibernateTemplate.get(IdModelEntity.class,id);
   if (idModelEntity!=null)
   {
       idModelEntity.setState(-1);
       save(idModelEntity);
   } }

    @Override
    public IdModelEntity getEntityById(int id) {
        return hibernateTemplate.get(IdModelEntity.class,id);
    }

    /**
     * 修饰原始的hql语句使他便于查询
     * @param hql 原始的hql查询语句
     * @param modelName 模板名
     * @param modelCode 模板号
     * @return 修饰好的查询语句
     */
    private  String hqlForPageList(String hql,String modelName,
                                   String modelCode,String productId,
                                   String stationId)
    {
        if (modelName!=null&&!"".equals(modelName))
        {
            hql+=" and modelName like '%"+modelName+"%'";
        }
        if (modelCode!=null&&!"".equals(modelCode))
        {
            hql+="and modelCode like '%"+modelCode+"%'";
        }
        if (productId!=null&&!"".equals(productId))
        {
            hql+=" and bomHeadId="+productId;
        }
        if (stationId!=null&&!"".equals(stationId))
        {
            hql+=" and stationId="+stationId;
        }
        return hql;
    }
}
