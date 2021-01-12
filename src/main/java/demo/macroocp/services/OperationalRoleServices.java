package demo.macroocp.services;

import demo.macroocp.bean.OperationalRole;
import demo.macroocp.bean.OperationalRolePojo;

import java.util.Map;

public interface OperationalRoleServices {
    Map <String,Object> getOperationalRoleByrName(OperationalRole or);              //按照角色名称查找信息
    Map <String,Object> getOperationalRoleByrID(OperationalRole or);                //按照角色编号查找信息
    Map <String,Object> getOperationalRoleByrStatus(OperationalRole or);            //按照角色状态查找信息
    Map <String,Object> getOperationalRoleByrPermissionLevel(OperationalRole or);   //按照权限等级查找信息
    Map <String,Object> getAllRoles();                                              //查找所有角色，并以角色编号升序排序
    Map <String,Object> getOperationalRoleByName_rStatus(OperationalRolePojo orp);  //按照角色名称(模糊)和角色状态查找信息
    Integer deleteOperationalRoleByrID(OperationalRole or);                         //根据角色编号删除信息
    Integer updateOperationalRole(OperationalRole or);                              //更新角色所有信息
    Integer updateOperationalRoleLevelByrID(OperationalRole or);                    //根据角色编号更新角色权限等级
    String getMaxrID();                                                             //获取最大角色编号
    Integer addOperationalRole(OperationalRole or);                                 //增加角色
    Integer updateOperationalRolerStatusByrID(OperationalRole or);                  //按照角色编号更新角色状态
}
