package bom;

import com.smartflow.dao.BOMHeadDao;
import com.smartflow.dao.BOMHeadDaoImpl;
import com.smartflow.service.BOMHeadService;
import com.smartflow.service.BOMHeadServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author ：tao
 * @date ：Created in 2020/4/18 18:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
@ActiveProfiles("development")
public class BomPageData {
    //private ClassPathXmlApplicationContext applicationContext =
    //        new ClassPathXmlApplicationContext
    //                ("spring-config.xml");
    @Autowired
    BOMHeadDao bomHeadDao;
    @Autowired
    BOMHeadService bomHeadService;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Test
    public  void getBOMView()
    {
        assertNotNull(bomHeadService.getPageData(10,1,""));
        logger.info(bomHeadService.getPageData(10,1,"").toString());
    }
    @Test
    public  void getBomRegister()
    {
        assertNotNull(bomHeadDao.getRegisterBom("11-01"));
        logger.info(bomHeadDao.getRegisterBom("11-01").toString());
    }
}
