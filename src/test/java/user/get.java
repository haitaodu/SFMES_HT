package user;

import com.smartflow.dao.UserDao;
import com.smartflow.dao.UserErrorDao;
import com.smartflow.model.UserError;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ：tao
 * @date ：Created in 2020/10/16 11:22
 * @description：${description}
 * @modified By：
 * @version: version
 */


@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@ActiveProfiles("development")
public class get {
    @Autowired
    UserDao userDao;
    @Autowired
    UserErrorDao userErrorDao;

    @Test
    public void getUserByDepartment()
    {

        log.info(userDao.getUsersByDepartment(8).size());

    }

    @Test
    public void getUserError()
    {
        log.info(userErrorDao.getById(112));
    }

    @Test
    public void insertUserError()
    {
        UserError userError=new UserError();
        userError.setDepartid(8);
        userError.setErrorid(12);
        userError.setUserid(28);
        userErrorDao.insert(userError);
    }

    @Test
    public void getUserByUserName()
    {
        log.info(userDao.getUserByName("杜海涛"));
        log.info(userDao.getUserList().toString());
    }
}
