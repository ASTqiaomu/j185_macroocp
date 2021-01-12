package demo.macroocp.services.impl;

import demo.macroocp.bean.OperationalRole;
import demo.macroocp.bean.OperationalRolePojo;
import demo.macroocp.dao.OperationalRoleDao;
import demo.macroocp.services.OperationalRoleServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OperationalRoleServicesImpl implements OperationalRoleServices {
    @Resource
    private OperationalRoleDao operationalRoledao;

    @Override
    public Map<String, Object> getOperationalRoleByrName(OperationalRole or) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRole> list = this.operationalRoledao.selectOperationalRoleByrName(or);
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
    public Map<String, Object> getOperationalRoleByrID(OperationalRole or) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRole> list = this.operationalRoledao.selectOperationalRoleByrID(or);
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
    public Map<String, Object> getOperationalRoleByrStatus(OperationalRole or) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRole> list = this.operationalRoledao.selectOperationalRoleByrStatus(or);
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
    public Map<String, Object> getOperationalRoleByrPermissionLevel(OperationalRole or) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRole> list = this.operationalRoledao.selectOperationalRoleByrPermissionLevel(or);
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
    public Map<String, Object> getAllRoles() {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRole> list = this.operationalRoledao.selectAllRoles();
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
    public Map<String, Object> getOperationalRoleByName_rStatus(OperationalRolePojo orp) {
        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<OperationalRolePojo> list = this.operationalRoledao.selectOperationalRoleByName_rStatus(orp);
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
    public Integer deleteOperationalRoleByrID(OperationalRole or) {
        Integer lines = this.operationalRoledao.deleteOperationalRoleByrID(or);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer updateOperationalRole(OperationalRole or) {
        Integer lines = this.operationalRoledao.updateOperationalRole(or);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer updateOperationalRoleLevelByrID(OperationalRole or) {
        Integer lines = this.operationalRoledao.updateOperationalRoleLevelByrID(or);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public String getMaxrID() {
        List<OperationalRole> list = this.operationalRoledao.getMaxrID();
        return list.get(0).getRID();
    }

    @Override
    public Integer addOperationalRole(OperationalRole or) {
        Integer lines = this.operationalRoledao.addOperationalRole(or);
//        System.out.println("受影响的行："+lines);
        return lines;
    }

    @Override
    public Integer updateOperationalRolerStatusByrID(OperationalRole or) {
        Integer lines = this.operationalRoledao.updateOperationalRolerStatusByrID(or);
//        System.out.println("受影响的行："+lines);
        return lines;
    }
}
