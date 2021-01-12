package demo.macroocp.services.impl;

import demo.macroocp.bean.OrderPojo;
import demo.macroocp.dao.OrderInfoDao;
import demo.macroocp.dao.OrderItemDao;
import demo.macroocp.dao.OrderPojoDao;
import demo.macroocp.dao.OrderShippingDao;
import demo.macroocp.services.OrderPojoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderPojoServicesImpl implements OrderPojoServices {
  @Resource OrderInfoDao orderInfoDao;
  @Resource OrderItemDao orderItemDao;
  @Resource OrderShippingDao orderShippingDao;
  @Resource OrderPojoDao orderPojoDao;
  @Resource OrderPojo orderPojo;

  @Override
  public Map<String, Object> getOrderPojoByAgencyName(String agencyName) {
    Map<String, Object> map = new HashMap<>();
    // 查询经销商的所有（未确认收货和已发货的）订单信息
    List<OrderPojo> orderPojoList = orderPojoDao.getOrderPojoByAgencyName(agencyName);
    map.put("orderPojoList", orderPojoList);

    return map;
  }

  @Override
  public Map<String, Object> getOrderPojoByMultipleCondition(Map<String, String> map) {
    Map<String, Object> map1 = new HashMap<>();
    List<OrderPojo> orderPojoList = orderPojoDao.getOrderPojoByMultipleCondition(map);
    map1.put("orderPojoList", orderPojoList);

    return map1;
  }
}
