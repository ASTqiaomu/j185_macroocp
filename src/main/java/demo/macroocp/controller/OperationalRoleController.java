package demo.macroocp.controller;

import com.alibaba.fastjson.JSON;
import demo.macroocp.bean.OperationalRole;
import demo.macroocp.bean.OperationalRolePojo;
import demo.macroocp.services.OperationalRoleServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OperationalRoleController {
    @Resource
    private OperationalRoleServices operationalRoleServices;

    @RequestMapping("/getOperationalRoleByrName")
    public Map<String, Object> getOperationalRoleByrName(OperationalRole or) {
        return this.operationalRoleServices.getOperationalRoleByrName(or);
    }
    @RequestMapping("/getOperationalRoleByrID")
    public Map<String, Object> getOperationalRoleByrID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String rid = hashMap.get("rid").toString();
        OperationalRole or = new OperationalRole();
        or.setRID(rid);
        return this.operationalRoleServices.getOperationalRoleByrID(or);
    }
    @RequestMapping("/getOperationalRoleByrStatus")
    public Map<String, Object> getOperationalRoleByrStatus(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        OperationalRole or = new OperationalRole();
        Integer status = Integer.parseInt(hashMap.get("status").toString());
        or.setRStatus(status);
        return this.operationalRoleServices.getOperationalRoleByrStatus(or);
    }
    @RequestMapping("/getOperationalRoleByrPermissionLevel")
    public Map<String, Object> getOperationalRoleByrPermissionLevel(OperationalRole or) {
        return this.operationalRoleServices.getOperationalRoleByrPermissionLevel(or);
    }
    @RequestMapping("/getAllRoles")
    public Map<String, Object> getAllRoles() {
        return this.operationalRoleServices.getAllRoles();
    }
    @RequestMapping("/getOperationalRoleByName_rStatus")
    public Map<String, Object> getOperationalRoleByName_rStatus(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String search_rname = hashMap.get("search_rname").toString();
        String rstatus = hashMap.get("status").toString();
        if (rstatus.equals("2"))
        {
            rstatus = "(0, 1)";
        }
        else if (rstatus.equals("1")){
            rstatus = "(1)";
        }
        else if (rstatus.equals("0")){
            rstatus = "(0)";
        }
        OperationalRolePojo orp = new OperationalRolePojo();
        orp.setRName(search_rname);
        orp.setRStatus(rstatus);
        return this.operationalRoleServices.getOperationalRoleByName_rStatus(orp);
    }
    @RequestMapping("/deleteOperationalRoleByrID")
    public Integer deleteOperationalRoleByrID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String rid = hashMap.get("rid").toString();
        OperationalRole or = new OperationalRole();
        or.setRID(rid);
        return this.operationalRoleServices.deleteOperationalRoleByrID(or);
    }
    @RequestMapping("/updateOperationalRole")
    public Integer updateOperationalRole(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String rname = hashMap.get("rname").toString();
        String rid = hashMap.get("rid").toString();
        String rdescription = hashMap.get("rdescription").toString();
        Integer rpermissionLevel = Integer.parseInt(hashMap.get("rpermissionLevel").toString());
        Integer rstatus = Integer.parseInt(hashMap.get("rstatus").toString());
        OperationalRole or = new OperationalRole();
        or.setRName(rname);
        or.setRID(rid);
        or.setRDescription(rdescription);
        or.setRPermissionLevel(rpermissionLevel);
        or.setRStatus(rstatus);
        return this.operationalRoleServices.updateOperationalRole(or);
    }
    @RequestMapping("/updateOperationalRoleLevelByrID")
    public Integer updateOperationalRoleLevelByrID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String rid = hashMap.get("rid").toString();
        Integer rpermissionLevel = Integer.parseInt(hashMap.get("rpermissionLevel").toString());
        OperationalRole or = new OperationalRole();
        or.setRID(rid);
        or.setRPermissionLevel(rpermissionLevel);
        return this.operationalRoleServices.updateOperationalRoleLevelByrID(or);
    }
    @RequestMapping("/addOperationalRole")
    public Map<String, Object> addOperationalRole(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String str_maxid = operationalRoleServices.getMaxrID();
        int int_maxid = Integer.parseInt(str_maxid.substring(2));
        String rid = "OR" + (int_maxid + 1);
        String rname = hashMap.get("rname").toString();
        String rdescription = hashMap.get("rdescription").toString();
        Integer rpermissionLevel = Integer.parseInt(hashMap.get("rpermissionLevel").toString());
        Integer rstatus = Integer.parseInt(hashMap.get("rstatus").toString());
        OperationalRole or = new OperationalRole();
        or.setRName(rname);
        or.setRID(rid);
        or.setRDescription(rdescription);
        or.setRPermissionLevel(rpermissionLevel);
        or.setRStatus(rstatus);
        Integer lines = operationalRoleServices.addOperationalRole(or);
        if (lines > 0){
            List<OperationalRole> list = Arrays.asList(or);
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
//            System.out.println(map);
            return map;
        }else {
            return null;
        }
    }
    @RequestMapping("/updateOperationalRolerStatusByrID")
    public Integer updateOperationalRolerStatusByrID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String rid = hashMap.get("rid").toString();
        Integer rstatus = Integer.parseInt(hashMap.get("rstatus").toString());
        OperationalRole or = new OperationalRole();
        or.setRID(rid);
        or.setRStatus(rstatus);
        return this.operationalRoleServices.updateOperationalRolerStatusByrID(or);
    }
}
