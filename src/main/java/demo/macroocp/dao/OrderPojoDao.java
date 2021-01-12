package demo.macroocp.dao;

import demo.macroocp.bean.OrderPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderPojoDao {

  List<OrderPojo> getOrderPojoByAgencyName(String agencyName);

  List<OrderPojo> getOrderPojoByMultipleCondition(Map<String, String> map);
}
