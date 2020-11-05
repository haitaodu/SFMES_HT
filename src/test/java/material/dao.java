package material;

import com.smartflow.dao.BOMHeadDaoImpl;
import com.smartflow.dao.StationDaoImpl;
import com.smartflow.service.StationService;
import com.smartflow.service.StationServiceImpl;
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


     @Test
    public void get()
    {
        StationDaoImpl stationDao=(StationDaoImpl)applicationContext.getBean("stationDaoImpl");
        StationServiceImpl stationService=(StationServiceImpl) applicationContext.getBean("stationServiceImpl");
        logger.info(stationService.getStationList
                ("RE_OP10A",10437).get("PrintStation").toString());
    }

    @Test
    public void get1()
    {
        StationDaoImpl stationDao=(StationDaoImpl)applicationContext.getBean("stationDaoImpl");
        StationServiceImpl stationService=(StationServiceImpl) applicationContext.getBean("stationServiceImpl");
        logger.info(String.valueOf(
                stationDao.getCellByWorkOrderId(10437,"TU_OP25")));

        logger.info(stationService.getStationList("TU_OP25",10437).toString());
    }
}
