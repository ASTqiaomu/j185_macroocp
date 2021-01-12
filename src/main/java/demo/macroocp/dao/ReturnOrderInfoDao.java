package demo.macroocp.dao;

import demo.macroocp.bean.ReturnOrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ReturnOrderInfoDao {
  int addReturnOrderInfo(ReturnOrderInfo returnOrderInfo);

  int changeReturnedOrderStatusByReturnedOrderNum(Map<String, Object> map);

  int setReturnedPaymentByReturnOrderNum(Map<String, Object> map);
}
