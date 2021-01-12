package demo.macroocp;

import demo.macroocp.bean.*;
import demo.macroocp.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class MacroocpApplicationTests {

    //依赖注入
    @Resource
    private LogDao logDao;
    @Resource
    private OperatingAccountDao oaDao;
    @Resource
    private OperationalRoleDao orDao;
    @Resource
    private Log log;
    @Resource
    private OperatingAccount oa;
    @Resource
    private OperationalRole or;

    @Test
    void contextLoads() {
    }
    @Test
    void selectTest()
    {
        System.out.println("====>" + this.logDao.selectBylogID(log));
        System.out.println("====>" + this.logDao.selectByopType(log));
        System.out.println("====>" + this.logDao.selectByopObject(log));
        System.out.println("====>" + this.oaDao.selectByuserName(oa));
        System.out.println("====>" + this.oaDao.selectByID(oa));
        System.out.println("====>" + this.oaDao.selectBytrueName(oa));
        System.out.println("====>" + this.oaDao.selectByorganization(oa));
        System.out.println("====>" + this.oaDao.selectByproductLines(oa));
        System.out.println("====>" + this.oaDao.selectBysex(oa));
        System.out.println("====>" + this.oaDao.selectByphone(oa));
        System.out.println("====>" + this.oaDao.selectByemail(oa));
        System.out.println("====>" + this.oaDao.selectByaStatus(oa));
        System.out.println("====>" + this.orDao.selectOperationalRoleByrName(or));
        System.out.println("====>" + this.orDao.selectOperationalRoleByrID(or));
        System.out.println("====>" + this.orDao.selectOperationalRoleByrStatus(or));
        System.out.println("====>" + this.orDao.selectOperationalRoleByrPermissionLevel(or));
    }
}



