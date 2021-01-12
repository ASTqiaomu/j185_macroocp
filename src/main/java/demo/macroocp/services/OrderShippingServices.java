package demo.macroocp.services;

import demo.macroocp.bean.OrderShipping;

public interface OrderShippingServices {
  OrderShipping getOrderShippingByOrderNumber(String orderNumber);
}
