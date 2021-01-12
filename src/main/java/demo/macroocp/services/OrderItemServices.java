package demo.macroocp.services;

import demo.macroocp.bean.OrderItem;

import java.util.List;

public interface OrderItemServices {
  List<OrderItem> getOrderInfoByOrderNumber(String orderNumber);
}
