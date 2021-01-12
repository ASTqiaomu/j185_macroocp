package demo.macroocp.dao;

import demo.macroocp.bean.OrderShipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderShippingDao {
  @Select("select * from OrderShippingT where orderNumber=#{orderNumber}")
  OrderShipping getOrderShippingByOrderNumber(String orderNumber);
}
