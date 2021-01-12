package demo.macroocp.dao;

import demo.macroocp.bean.OrderInfo;
import demo.macroocp.bean.OrderPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoDao {
  // @Select("select * from OrderInfoT where agencyName=#{agencyName} and orderStatus in (1,2)")
  List<OrderInfo> getOrderInfoByAgencyName(String agencyName);

  List<OrderPojo> getOrderPojoByAgencyName();
}
