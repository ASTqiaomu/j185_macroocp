package demo.macroocp.services.impl;

import demo.macroocp.bean.Log;
import demo.macroocp.dao.LogDao;
import demo.macroocp.services.LogServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class LogServicesImpl implements LogServices {
    @Resource
    private LogDao logDao;

    @Override
    public Map<String, Object> getLogBylogID(Log log) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<Log> list = this.logDao.selectBylogID(log);
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
    public Map<String, Object> getLogByopType(Log log) {
        Map<String, Object> map = new HashMap<>();
        List<Log> list = this.logDao.selectByopType(log);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
        }else{
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getLogByopObject(Log log) {
        Map<String, Object> map = new HashMap<>();
        List<Log> list = this.logDao.selectByopObject(log);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
        }else{
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
}
