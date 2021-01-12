package demo.macroocp.dao;

import demo.macroocp.bean.OperationalRole;
import demo.macroocp.bean.OperationalRolePojo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OperationalRoleDao {
    @Select("select * from ort where rName=#{rName}")
    List<OperationalRole> selectOperationalRoleByrName(OperationalRole or);
    @Select("select * from ort where rID=#{rID}")
    List<OperationalRole> selectOperationalRoleByrID(OperationalRole or);
    @Select("select * from ort where rStatus=#{rStatus}")
    List<OperationalRole> selectOperationalRoleByrStatus(OperationalRole or);
    @Select("select * from ort where rPermissionLevel=#{rPermissionLevel}")
    List<OperationalRole> selectOperationalRoleByrPermissionLevel(OperationalRole or);
    @Select("select * from ort order by substr(rID,3)+0 asc")
    List<OperationalRole> selectAllRoles();
    @Select("select * from ort where rName like ('%${rName}%') and rStatus in ${rStatus}")
    List<OperationalRolePojo> selectOperationalRoleByName_rStatus(OperationalRolePojo orp);
    @Delete("delete from ort where rID=#{rID}")
    Integer deleteOperationalRoleByrID(OperationalRole or);
    @Update("update ort set rName=#{rName}, rID=#{rID},rDescription=#{rDescription}," +
            "rStatus=${rStatus},rPermissionLevel=${rPermissionLevel} where rID=#{rID}")
    Integer updateOperationalRole(OperationalRole or);
    @Update("update ort set rPermissionLevel=${rPermissionLevel} where rID=#{rID}")
    Integer updateOperationalRoleLevelByrID(OperationalRole or);
    @Select("select MAX(rid) as rID from ort")
    List<OperationalRole> getMaxrID();
    @Insert("insert into ort values(#{rName},#{rID},#{rDescription},${rStatus},${rPermissionLevel})")
    Integer addOperationalRole(OperationalRole or);
    @Update("update ort set rStatus=${rStatus} where rID=#{rID}")
    Integer updateOperationalRolerStatusByrID(OperationalRole or);
}
