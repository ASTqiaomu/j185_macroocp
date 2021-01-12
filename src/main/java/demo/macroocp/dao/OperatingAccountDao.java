package demo.macroocp.dao;

import demo.macroocp.bean.OperatingAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

public interface OperatingAccountDao {
    @Select("select * from oat where userName=#{userName}")
    List<OperatingAccount> selectByuserName(OperatingAccount oa);
    @Select("select * from oat where userID=#{userID}")
    List<OperatingAccount> selectByID(OperatingAccount oa);
    @Select("select * from oat where trueName=#{trueName}")
    List<OperatingAccount> selectBytrueName(OperatingAccount oa);
    @Select("select * from oat where organization=#{organization}")
    List<OperatingAccount> selectByorganization(OperatingAccount oa);
    @Select("select * from oat where productLines=#{productLines}")
    List<OperatingAccount> selectByproductLines(OperatingAccount oa);
    @Select("select * from oat where sex=#{sex}")
    List<OperatingAccount> selectBysex(OperatingAccount oa);
    @Select("select * from oat where phone=#{phone}")
    List<OperatingAccount> selectByphone(OperatingAccount oa);
    @Select("select * from oat where email=#{email}")
    List<OperatingAccount> selectByemail(OperatingAccount oa);
    @Select("select * from oat where aStatus=#{aStatus}")
    List<OperatingAccount> selectByaStatus(OperatingAccount oa);

    //获取所有账号信息
    @Select("select * from oat order by substr(userID,3)+0 asc")
    List<OperatingAccount> getAllAccounts();
    //增加账号
    @Insert("insert into oat values(#{rName},#{userID},#{PASSWORD},#{trueName},#{organization},#{productLines},#{rName},#{sex},#{phone},#{email},${aStatus})")
    Integer addOperationalAccount(OperatingAccount oa);
    //删除信息
    @Delete("delete from oat where userID=#{userID}")
    Integer deleteOperationalAccountByuserID(OperatingAccount oa);
    //启用/禁用账户
    @Update("update oat set aStatus=#{aStatus} where userID=#{userID}")
    Integer updateOperationalAccountStatusByuserID(OperatingAccount oa);

    //查找信息
    @Select("select * from oat where userName like '%${userName}%'")
    List<OperatingAccount> getAccountByuserName(OperatingAccount oa);
    @Select("select * from oat where trueName like '%${trueName}%'")
    List<OperatingAccount> getAccountBytrueName(OperatingAccount oa);
    @Select("select * from oat where phone like '%${phone}%'")
    List<OperatingAccount> getAccountByphone(OperatingAccount oa);
    @Select("select * from oat where sex in ${sex}")
    List<OperatingAccount> getAccountBysex(OperatingAccount oa);
    @Select("select * from oat where aStatus in ${aStatus}")
    List<OperatingAccount> getAccountByaStatus(OperatingAccount oa);

    //分配角色
    @Update("update oat set rName=#{rName} where userID=#{userID}")
    List<OperatingAccount> updateOperationalAccountRoalByuserID(OperatingAccount oa);

    @Select("select MAX(userid) as userID from oat")
    List<OperatingAccount> getMaxuserID();
    @Update("update oat set PASSWORD='123456' where userID=#{userID}")
    Integer resetOperatingAccountPasswordByID(OperatingAccount oa);

//    @Update("update ort set rName=#{rName}, rID=#{rID},rDescription=#{rDescription},rStatus=${rStatus},rPermissionLevel=${rPermissionLevel} where rID=#{rID}")

    @Update("update oat set organization=#{organization}, productLines=#{productLines}, rname=#{rName}, phone=#{phone}, email=#{email}, sex=#{sex} where userName=#{userName}")
    Integer updateOperationalAccountByusername(OperatingAccount oa);
}
