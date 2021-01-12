package demo.macroocp.dao;

import demo.macroocp.bean.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemDao {
  @Select("select * from OrderDetailT where orderNumber=#{orderNumber}")
  List<OrderItem> getOrderItemByOrderNumber(String orderNumber);
}
