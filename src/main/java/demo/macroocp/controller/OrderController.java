package demo.macroocp.controller;

import demo.macroocp.services.OrderPojoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class OrderController {
  @Resource OrderPojoServices orderPojoServices;

  // 经销商账号查看所有销售订单
  @RequestMapping("/showAllOrders")
  Map<String, Object> showAllOrders(String agencyName) {
    Map<String, Object> map = new HashMap<>();
    map = orderPojoServices.getOrderPojoByAgencyName(agencyName);

    return map;
  }
  // 经销商账号查询某些销售订单
  @RequestMapping("/queryOrder")
  Map<String, Object> queryOrder(@RequestBody Map<String, String> queryMap) {
    Map<String, Object> map = new HashMap<>();
    map = orderPojoServices.getOrderPojoByMultipleCondition(queryMap);

    return map;
  }
}
