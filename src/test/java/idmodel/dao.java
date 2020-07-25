package idmodel;

import com.smartflow.dao.IdModelDaoImpl;
import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 15:29
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class dao {
    private ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext
                    ("spring-config.xml");
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private IdModelDaoImpl idModelDao = (IdModelDaoImpl)applicationContext.getBean
            ("idModelDaoImpl");
    private ModelIdConditionInputDTO modelIdConditionInputDTO=new ModelIdConditionInputDTO();

    {
        modelIdConditionInputDTO.setPageIndex(1);
        modelIdConditionInputDTO.setPageSize(10);
        modelIdConditionInputDTO.setModelCode("code");
        modelIdConditionInputDTO.setModelName("name");
    }

    @Test
    public void pageData()
    {
        logger.info(idModelDao.getIdModelPageSearch(modelIdConditionInputDTO).toString());
    }

    @Test
   public void getIdLayoutEntity()
    {
     logger.info(idModelDao.getByIdModelId(1).toString());
    }

    @Test
    public void getCount()
    {
        logger.info(Integer.valueOf(idModelDao.getTotalEntity(modelIdConditionInputDTO)).toString());
    }
}
