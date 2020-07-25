package com.smartflow.dao;

import com.smartflow.model.MESMenu;
import com.smartflow.util.ParseFieldToMapUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MESMenuDaoImpl implements MESMenuDao {

    @Autowired
    HibernateTemplate hibernateTemplate;
    @Override
    public List<MESMenu> getMESMenuList() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from MESMenu");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Map<String, Object>> getParentMESMenuInit() {
        String hql = "select new MESMenu(id, name) from MESMenu where id != 1 and pid is NULL";
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(hql);
            List<MESMenu> menuList = query.list();
            return ParseFieldToMapUtil.parseMenuFieldToMap(menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public List<Map<String, Object>> getChildrenMESMenuListByParentId(Integer parentId) {
        String hql = "select new MESMenu(id, name) from MESMenu where pid = "+parentId;
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(hql);
            return ParseFieldToMapUtil.parseMenuFieldToMap(query.list());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Map<String, Object>> getChildrenMESMenuList(Integer parentId) {
        List<Map<String, Object>> childrenMenuList = getChildrenMESMenuListByParentId(parentId);
        for (Map<String,Object> childrenMenu:childrenMenuList) {
            List<Map<String,Object>> childMenuList = getChildrenMESMenuList(Integer.parseInt(childrenMenu.get("key").toString()));
            if(!CollectionUtils.isEmpty(childMenuList)){
                childrenMenu.put("children", childMenuList);
            }
        }
        return childrenMenuList;
    }
}
