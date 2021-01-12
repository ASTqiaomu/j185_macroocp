package demo.macroocp.services.impl;

import demo.macroocp.bean.OperatingAccount;
import demo.macroocp.dao.OperatingAccountDao;
import demo.macroocp.services.OperatingAccountServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OperatingAccountServicesImpl implements OperatingAccountServices {
    @Resource
    private OperatingAccountDao operatingAccountDao;

    @Override
    public Map<String, Object> getOperatingAccountByuserName(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByuserName(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByID(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByID(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountBytrueName(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectBytrueName(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByorganization(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByorganization(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByproductLines(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByproductLines(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountBysex(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectBysex(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByphone(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByphone(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByemail(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByemail(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByaStatus(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.selectByaStatus(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllAccounts() {
        Map<String, Object> map = new HashMap<>();
        List<OperatingAccount>list = this.operatingAccountDao.getAllAccounts();
        if(list.size()>0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        }else{
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Integer DeleteOperationalAccountByuserID(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.deleteOperationalAccountByuserID(oa);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer AddOperationalAccount(OperatingAccount oa){
        Integer lines = this.operatingAccountDao.addOperationalAccount(oa);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer UpdateOperationalAccountStatusByuserID(OperatingAccount oa){
        Integer lines = this.operatingAccountDao.updateOperationalAccountStatusByuserID(oa);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Map <String,Object> UpdateOperationalAccountRoalByuserID(OperatingAccount oa){
        Map <String, Object> map = new HashMap<>();
        List<OperatingAccount> list = this.operatingAccountDao.updateOperationalAccountRoalByuserID(oa);
        if(list.size()>0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        }else{
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public String getMaxuserID() {
        List<OperatingAccount>list = this.operatingAccountDao.getMaxuserID();
        return list.get(0).getUserID();
    }

    @Override
    public Integer resetOperatingAccountPasswordByID(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.resetOperatingAccountPasswordByID(oa);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer updateOperationalAccountByusername(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.updateOperationalAccountByusername(oa);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Map<String, Object> getAccountByuserName(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getAccountByuserName(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
    @Override
    public Map<String, Object> getAccountBytrueName(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getAccountBytrueName(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
    @Override
    public Map<String, Object> getAccountByphone(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getAccountByphone(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
    @Override
    public Map<String, Object> getAccountBysex(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getAccountBysex(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAccountByaStatus(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getAccountByaStatus(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
}
