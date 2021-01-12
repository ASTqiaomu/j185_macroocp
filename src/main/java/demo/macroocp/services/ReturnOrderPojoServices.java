package demo.macroocp.services;

import demo.macroocp.bean.ReturnOrderInfo;
import demo.macroocp.bean.ReturnOrderItem;

import java.util.List;
import java.util.Map;

public interface ReturnOrderPojoServices {
  Map<String, Object> generateReturnOrder(
      ReturnOrderInfo returnOrderInfo, List<ReturnOrderItem> returnOrderItems);

  Map<String, Object> getAllReturnedOrderInfo(String agencyName);

  Map<String, Object> getReturnedOrderPojoByMultipleCondition(ReturnOrderInfo returnOrderInfo);

  Map<String, Object> changeReturnedOrderStatusByReturnedOrderNum(Map<String, Object> map);
}
