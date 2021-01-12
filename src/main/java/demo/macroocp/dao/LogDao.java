package demo.macroocp.dao;

import demo.macroocp.bean.Log;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogDao {
    @Select("select * from logt where logID=#{logID}")
    List<Log> selectBylogID(Log log);
    @Select("select * from logt where opType=#{opType}")
    List<Log> selectByopType(Log log);
    @Select("select * from logt where opObject=#{opObject}")
    List<Log> selectByopObject(Log log);
}
