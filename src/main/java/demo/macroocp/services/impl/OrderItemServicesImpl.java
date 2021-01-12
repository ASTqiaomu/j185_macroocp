package demo.macroocp.services.impl;

import demo.macroocp.bean.OrderItem;
import demo.macroocp.dao.OrderItemDao;
import demo.macroocp.services.OrderItemServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderItemServicesImpl implements OrderItemServices {
  @Resource OrderItemDao orderItemDao;

  @Override
  public List<OrderItem> getOrderInfoByOrderNumber(String orderNumber) {
    return orderItemDao.getOrderItemByOrderNumber(orderNumber);
  }
}
