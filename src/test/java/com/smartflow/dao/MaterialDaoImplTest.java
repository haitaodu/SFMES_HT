package com.smartflow.dao;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ：tao
 * @date ：Created in 2020/11/30 17:02
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@ActiveProfiles("development")
public class MaterialDaoImplTest {
    @Autowired
    MaterialDao materialDao;

    @Test
    public void  get()
    {
        log.info("dsdw");
         log.info(materialDao.getDataById(11).toString());
    }


}