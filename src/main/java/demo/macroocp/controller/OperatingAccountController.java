package demo.macroocp.controller;

import com.alibaba.fastjson.JSON;
import demo.macroocp.bean.OperatingAccount;
import demo.macroocp.services.OperatingAccountServices;
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
public class OperatingAccountController {
    @Resource
    private OperatingAccountServices operatingAccountservices;

    @RequestMapping("/getOperatingAccountByuserName")
    public Map<String, Object> getOperatingAccountByuserName(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByuserName(oa);
    }
    @RequestMapping("/getOperatingAccountByID")
    public Map<String, Object> getOperatingAccountByID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserID(userid);
        return this.operatingAccountservices.getOperatingAccountByID(oa);
    }

    @RequestMapping("/getOperatingAccountBytrueName")
    public Map<String, Object> getOperatingAccountBytrueName(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountBytrueName(oa);
    }
    @RequestMapping("/getOperatingAccountByorganization")
    public Map<String, Object> getOperatingAccountByorganization(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByorganization(oa);
    }
    @RequestMapping("/getOperatingAccountByproductLines")
    public Map<String, Object> getOperatingAccountByproductLines(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByproductLines(oa);
    }
    @RequestMapping("/getOperatingAccountBysex")
    public Map<String, Object> getOperatingAccountBysex(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountBysex(oa);
    }
    @RequestMapping("/getOperatingAccountByphone")
    public Map<String, Object> getOperatingAccountByphone(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByphone(oa);
    }
    @RequestMapping("/getOperatingAccountByemail")
    public Map<String, Object> getOperatingAccountBgetOperatingAccountByemailysex(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByemail(oa);
    }
    @RequestMapping("/getOperatingAccountByaStatus")
    public Map<String, Object> getOperatingAccountByaStatus(OperatingAccount oa) {
        return this.operatingAccountservices.getOperatingAccountByaStatus(oa);
    }

    //查询所有账户信息
    @RequestMapping("/getAllAccounts")
    public Map<String, Object> getAllAccounts(){
        return this.operatingAccountservices.getAllAccounts();
    }

    //新增账户
    @RequestMapping("/addOperationalAccount")
    public Map<String, Object>addOperationalAccount(@RequestBody String data){
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String str_maxid = operatingAccountservices.getMaxuserID();
        int int_maxid = Integer.parseInt(str_maxid.substring(2));
        String userid = "OA" + (int_maxid + 1);
        String username = hashMap.get("username").toString();
        String password = "123456";
        String truename = hashMap.get("truename").toString();
        String organization = hashMap.get("organization").toString();
        String productLines = hashMap.get("productLines").toString();
        String rname = hashMap.get("rname").toString();
        String sex = hashMap.get("sex").toString();
        String phone = hashMap.get("phone").toString();
        String email = hashMap.get("email").toString();
        Integer astatus = 1;
        OperatingAccount oa =new OperatingAccount();
        oa.setUserName(username);
        oa.setUserID(userid);
        oa.setPASSWORD(password);
        oa.setTrueName(truename);
        oa.setOrganization(organization);
        oa.setProductLines(productLines);
        oa.setRName(rname);
        oa.setSex(sex);
        oa.setPhone(phone);
        oa.setEmail(email);
        oa.setAStatus(astatus);
        Integer lines = operatingAccountservices.AddOperationalAccount(oa);
        if (lines > 0){
            List<OperatingAccount> list = Arrays.asList(oa);
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

    //删除账号，参数有userid，返回值Integer，为影响的行数
    @RequestMapping("/deleteOperationalAccountByuserID")
    public Integer deleteOperationalAccountByuserID(@RequestBody String data){
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserID(userid);
        return this.operatingAccountservices.DeleteOperationalAccountByuserID(oa);
    }

    //分配角色，参数有userid，rname，返回值为map，map里面放userid和rname
    @RequestMapping("/updateOperationalAccountRnameByuserID")
    public Map<String, Object> updateOperationalAccountRnameByuserID(@RequestBody String data){
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        String rname = hashMap.get("rname").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserID(userid);
        oa.setRName(rname);
        return this.operatingAccountservices.UpdateOperationalAccountRoalByuserID(oa);
    }

    //启用/禁用账号，参数有userid，返回值为为Integer，为影响的行数
    @RequestMapping("/updateOperationalAccountStatusByruserID")
    public Integer updateOperationalAccountStatusByruserID(@RequestBody String data){
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        Integer astatus = Integer.parseInt(hashMap.get("astatus").toString());
        OperatingAccount oa = new OperatingAccount();
        oa.setUserID(userid);
        oa.setAStatus(astatus);
        return this.operatingAccountservices.UpdateOperationalAccountStatusByuserID(oa);
    }

    @RequestMapping("/resetOperatingAccountPasswordByID")
    public Integer resetOperatingAccountPasswordByID(@RequestBody String data){
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserID(userid);
        return this.operatingAccountservices.resetOperatingAccountPasswordByID(oa);
    }
    @RequestMapping("/updateOperationalAccountByusername")
    public Integer updateOperationalAccountByusername(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String organization = hashMap.get("organization").toString();
        String productLines = hashMap.get("productLines").toString();
        String rname = hashMap.get("rname").toString();
        String phone = hashMap.get("phone").toString();
        String email = hashMap.get("email").toString();
        String sex = hashMap.get("sex").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserName(username);
        oa.setOrganization(organization);
        oa.setProductLines(productLines);
        oa.setRName(rname);
        oa.setPhone(phone);
        oa.setEmail(email);
        oa.setSex(sex);
        return this.operatingAccountservices.updateOperationalAccountByusername(oa);
    }

    @RequestMapping("/getAccountByuserName")
    public Map<String, Object> getAccountByuserName(OperatingAccount oa) {
        return this.operatingAccountservices.getAccountByuserName(oa);
    }
    @RequestMapping("/getAccountBytruerName")
    public Map<String, Object> getAccountBytrueName(OperatingAccount oa) {
        return this.operatingAccountservices.getAccountBytrueName(oa);
    }
    @RequestMapping("/getAccountByphone")
    public Map<String, Object> getAccountByphone(OperatingAccount oa) {
        return this.operatingAccountservices.getAccountByphone(oa);
    }
    @RequestMapping("/getAccountBysex")
    public Map<String, Object> getAccountBysex(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String search_sex = hashMap.get("search_sex").toString();
        if (search_sex.equals("2"))
        {
            search_sex = "('M', 'F')";
        }
        else if (search_sex.equals("1")){
            search_sex = "('M')";
        }
        else if (search_sex.equals("0")){
            search_sex = "('F')";
        }
        OperatingAccount oa = new OperatingAccount();
        oa.setSex(search_sex);
        return this.operatingAccountservices.getAccountBysex(oa);
    }
    @RequestMapping("/getAccountByaStatus")
    public Map<String, Object> getAccountaByStatus(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        int search_astatus = Integer.parseInt(hashMap.get("search_astatus").toString());
        if (search_astatus==2)
        {
            return this.operatingAccountservices.getAllAccounts();
        }
        else {
            OperatingAccount oa = new OperatingAccount();
            oa.setAStatus(search_astatus);
            System.out.println("search_astatus="+search_astatus);
            return this.operatingAccountservices.getAccountByaStatus(oa);
        }
    }
}
