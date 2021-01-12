package demo.macroocp.dao;

import demo.macroocp.bean.ReturnOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnOrderItemDao {
  int addReturnOrderItem(ReturnOrderItem returnOrderItem);
}
