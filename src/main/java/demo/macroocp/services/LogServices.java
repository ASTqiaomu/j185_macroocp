package demo.macroocp.services;

import demo.macroocp.bean.Log;
import java.util.Map;

public interface LogServices {
    Map <String,Object> getLogBylogID(Log log);     //按照日志编号查找信息
    Map <String,Object> getLogByopType(Log log);    //按照操作类型查找信息
    Map <String,Object> getLogByopObject(Log log);  //按照操作对象查找信息
}
