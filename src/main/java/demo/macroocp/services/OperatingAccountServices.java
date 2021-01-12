package demo.macroocp.services;

import demo.macroocp.bean.OperatingAccount;

import java.util.Map;

public interface OperatingAccountServices {
    Map <String,Object> getOperatingAccountByuserName(OperatingAccount oa);     //按照用户名查找信息
    Map <String,Object> getOperatingAccountByID(OperatingAccount oa);           //按照ID查找信息
    Map <String,Object> getOperatingAccountBytrueName(OperatingAccount oa);     //按照真实姓名查找信息
    Map <String,Object> getOperatingAccountByorganization(OperatingAccount oa); //按照所属机构查找信息
    Map <String,Object> getOperatingAccountByproductLines(OperatingAccount oa); //按照所属产品线查找信息
    Map <String,Object> getOperatingAccountBysex(OperatingAccount oa);          //按照性别查找信息
    Map <String,Object> getOperatingAccountByphone(OperatingAccount oa);        //按照手机号码查找信息
    Map <String,Object> getOperatingAccountByemail(OperatingAccount oa);        //按照电子邮箱查找信息
    Map <String,Object> getOperatingAccountByaStatus(OperatingAccount oa);      //按照账号状态查找信息
    Map <String,Object> getAllAccounts();                                       //查询所有用户的信息
    Integer AddOperationalAccount(OperatingAccount oa);
    Integer DeleteOperationalAccountByuserID(OperatingAccount oa);
    Integer UpdateOperationalAccountStatusByuserID(OperatingAccount oa);
    Map <String,Object> UpdateOperationalAccountRoalByuserID(OperatingAccount oa);
    String getMaxuserID();
    Integer resetOperatingAccountPasswordByID(OperatingAccount oa);             //按照ID重置密码
    Integer updateOperationalAccountByusername(OperatingAccount oa);
    Map <String,Object> getAccountByuserName(OperatingAccount oa);     //按照用户名查找信息
    Map <String,Object> getAccountBytrueName(OperatingAccount oa);           //按照ID查找信息
    Map <String,Object> getAccountByphone(OperatingAccount oa);     //按照真实姓名查找信息
    Map <String,Object> getAccountBysex(OperatingAccount oa); //按照所属机构查找信息
    Map <String,Object> getAccountByaStatus(OperatingAccount oa); //按照所属机构查找信息
}
