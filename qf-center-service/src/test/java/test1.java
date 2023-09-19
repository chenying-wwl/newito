import com.qf.common.model.QfUser;
import com.qf.service.service.impl.QfUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import javax.annotation.Resource;

@TestConfiguration
public class test1 {
    @Resource
    QfUserServiceImpl qfUserService;
    @Test
    void test1(){
        QfUser qfUser = new QfUser();
        qfUser.setUserName("cheny");
        qfUser.setPassword("123456");
        qfUserService.add(qfUser);
    }
    @Test
    void test2(){
        QfUser qfUser = qfUserService.getUserByUserName("cheny");
//
        if(qfUser!=null){
            System.out.println("得到值");
        }
    }
}
