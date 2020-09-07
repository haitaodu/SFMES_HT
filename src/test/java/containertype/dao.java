package containertype;

import com.smartflow.dao.ContainerTypeDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ：tao
 * @date ：Created in 2020/9/7 9:51
 * @description：${description}
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class dao {
    @Autowired
    ContainerTypeDao containerTypeDao;
    @Test
    public void getList()
    {
        System.out.println(containerTypeDao.getContainerType());
    }
}
