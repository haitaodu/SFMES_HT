package material;

import com.smartflow.dao.BOMHeadDaoImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：tao
 * @date ：Created in 2020/7/17 11:05
 * @description：${description}
 */


public class dao {
    private ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext
                    ("spring-config.xml");
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Test
    public void getIsRegister()
    {
        BOMHeadDaoImpl bomHeadDao=(BOMHeadDaoImpl)applicationContext.getBean("BOMHeadDaoImpl");
        logger.info(String.valueOf(bomHeadDao.isRegisterMaterialNumber("0000A",1)));
    }
}
