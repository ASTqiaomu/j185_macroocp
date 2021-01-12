package demo.macroocp.services.impl;

import demo.macroocp.bean.OrderShipping;
import demo.macroocp.dao.OrderShippingDao;
import demo.macroocp.services.OrderShippingServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OrderShippingServicesImpl implements OrderShippingServices {
  @Resource OrderShippingDao orderShippingDao;

  @Override
  public OrderShipping getOrderShippingByOrderNumber(String orderNumber) {
    return orderShippingDao.getOrderShippingByOrderNumber(orderNumber);
  }
}
