package idmodel;

import com.smartflow.dto.idmode.ModelIdConditionInputDTO;
import com.smartflow.service.IdModelServiceImpl;
import static org.junit.Assert.*;

import com.smartflow.view.idmodel.IdModelSaveView;
import com.smartflow.view.idmodel.IdModelUpdateView;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 15:29
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class service {
    private ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext
                    ("spring-config.xml");
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private IdModelServiceImpl idModelService= (IdModelServiceImpl)applicationContext.getBean
            ("idModelServiceImpl");
    private ModelIdConditionInputDTO modelIdConditionInputDTO=new ModelIdConditionInputDTO();
    private IdModelSaveView idModelSaveView=new IdModelSaveView();
    private IdModelUpdateView idModelUpdateView=new IdModelUpdateView();
    {
        modelIdConditionInputDTO.setPageIndex(1);
        modelIdConditionInputDTO.setPageSize(10);
        modelIdConditionInputDTO.setModelCode("");
        modelIdConditionInputDTO.setModelName("");
        modelIdConditionInputDTO.setProductName("1203");
        //modelIdConditionInputDTO.setStationNumber("1");
        idModelSaveView.setCreatorId(10);
        idModelSaveView.setModelCode("ModeCode");
        idModelSaveView.setModelName("ModeName");
        idModelSaveView.setSupplierCode("SupplierCode");
        idModelSaveView.setPartCode("PartCode");
        idModelSaveView.setSupplierSelfCode("SupplierSelfCode");
        idModelSaveView.setVaildFrom(new Date());
        idModelSaveView.setVaildTo(new Date());
        idModelSaveView.setSupplierSequenceCode("SupplierSequenceCode");
        idModelSaveView.setNumberSufiix(3);
        idModelSaveView.setTimeStamp(true);
        idModelSaveView.setState(1);
        idModelSaveView.setBomhead("1203");
        idModelSaveView.setStationId(17);
        idModelSaveView.setTimeStamp(true);
        idModelUpdateView.setEditorId(10);
        idModelUpdateView.setId(1);
        idModelUpdateView.setModelCode("UpdateModeCode");
        idModelUpdateView.setModelName("UpdateModeName");
        idModelUpdateView.setPartCode("UpdatePartCode");
        idModelUpdateView.setSupplierCode("testSupplierCode");
        idModelUpdateView.setSupplierSelfCode("testSelfCode");
        idModelUpdateView.setVaildFrom(new Date());
        idModelUpdateView.setVaildTo(new Date());
        idModelUpdateView.setSupplierSequenceCode("UpdateSequenceCode");
    }

    @Test
    public void getPageView()
    {
        assertNotNull(idModelService.getPage(modelIdConditionInputDTO));
        logger.info(idModelService.getPage(modelIdConditionInputDTO).toString());
    }

    @Test
    public void getDetail()
    {
        assertNotNull(idModelService.getDetail(1));
        logger.info(idModelService.getDetail(1).toString());
    }

    @Test
    public void del()
    {
        assertNotNull(idModelService);
        idModelService.del(1);
    }

     @Test
    public void save()
     {
         assertNotNull(idModelSaveView);
         idModelService.save(idModelSaveView);
     }

     @Test
     public void update()
     {
         assertNotNull(idModelUpdateView);
         idModelService.update(idModelUpdateView);
     }

     @Test
    public void prefixChar()
     {

     }
}
