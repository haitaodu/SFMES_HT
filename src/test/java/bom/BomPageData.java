package bom;

import com.smartflow.dao.BOMHeadDaoImpl;
import com.smartflow.service.BOMHeadServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author ：tao
 * @date ：Created in 2020/4/18 18:41
 * @description：测试
 * @modified By：
 * @version: version
 */

public class BomPageData {
    private ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext
                    ("spring-config.xml");
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Test
    public  void getBOMView()
    {

        BOMHeadServiceImpl bomHeadService=(BOMHeadServiceImpl)applicationContext.getBean("BOMHeadServiceImpl") ;
        assertNotNull(bomHeadService.getPageData(10,1,""));
        logger.info(bomHeadService.getPageData(10,1,"").toString());
    }
    @Test
    public  void getBomRegister()
    {
        BOMHeadDaoImpl bomHeadDao=(BOMHeadDaoImpl)applicationContext.getBean("BOMHeadDaoImpl");
        assertNotNull(bomHeadDao.getRegisterBom("11-01"));
        logger.info(bomHeadDao.getRegisterBom("11-01").toString());
    }
}
