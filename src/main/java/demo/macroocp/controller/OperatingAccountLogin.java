package demo.macroocp.controller;

import com.alibaba.fastjson.JSON;
import demo.macroocp.bean.OperatingAccount;
import demo.macroocp.services.OperatingAccountServices;
import demo.macroocp.services.OperationalRoleServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OperatingAccountLogin {
    @Resource
    private OperatingAccountServices operatingAccountServices;
    @Resource
    private OperationalRoleServices operationalRoleServices;

    @RequestMapping("/hasaccount")
    @ResponseBody
    public boolean hasAccount(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        OperatingAccount operatingAccount = new OperatingAccount();
        String username = hashMap.get("username").toString();
        operatingAccount.setUserName(username);
        Map<String, Object> map = this.operatingAccountServices.getOperatingAccountByuserName(operatingAccount);
        if (map.get("code") == "0") {
            return true;
        }
        return false;
    }

    @RequestMapping("/checkaccount")
    @ResponseBody
    public int checkAccount(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String password = hashMap.get("password").toString();
        OperatingAccount operatingAccount = new OperatingAccount();
        operatingAccount.setUserName(username);
        Map<String, Object> map = this.operatingAccountServices.getOperatingAccountByuserName(operatingAccount);
        int res = 2;
        List<OperatingAccount> list = (List<OperatingAccount>) map.get("list");
        OperatingAccount o = list.get(0);

        if (!username.equals(o.getUserName())) {    //账号正确
        } else if (password.equals(o.getPASSWORD())) {    //密码正确
            if (o.getAStatus() == 1) {   //账号启用
                res = 1;
            } else {  //账号禁用
                res = 0;
            }
        }
        return res;
    }

    @RequestMapping("/login")
    @ResponseBody
    public int login(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
//        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        int status = checkAccount(data);
        if (status == 1) {  //账号密码正确，账号启用
            OperatingAccount operatingAccount = new OperatingAccount();
            operatingAccount.setUserName(username);
            Map<String, Object> map = this.operatingAccountServices.getOperatingAccountByuserName(operatingAccount);
            List<OperatingAccount> list = (List<OperatingAccount>) map.get("list");
            OperatingAccount o = list.get(0);
            // 将用户信息保存到session中
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();
            session.setAttribute("login_username",o.getUserName());
            session.setAttribute("login_userid",o.getUserID());
            session.setAttribute("login_truename",o.getTrueName());
            session.setAttribute("login_rname",o.getRName());
            session.setAttribute("organization", o.getOrganization());
            return 1;
        }else if (status == 0){ //账号密码正确，账号禁用
            return 0;
        }else {
            return 2;   //前端未处理该值
        }
    }

    @RequestMapping("/getloginuser")
    @ResponseBody
    public Map<String, Object> getloginuser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String login_username = session.getAttribute("login_username").toString();
        String login_userid = session.getAttribute("login_userid").toString();
        String login_truename = session.getAttribute("login_truename").toString();
        String login_rname = session.getAttribute("login_rname").toString();
        String organization = session.getAttribute("organization").toString();
        map.put("login_username",login_username);
        map.put("login_userid",login_userid);
        map.put("login_truename",login_truename);
        map.put("login_rname",login_rname);
        map.put("organization", organization);
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Integer logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return 0;
    }
}
