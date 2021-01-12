package demo.macroocp.controller;

import com.alibaba.fastjson.JSON;
import demo.macroocp.bean.ReturnOrderInfo;
import demo.macroocp.bean.ReturnOrderItem;
import demo.macroocp.services.ReturnOrderPojoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ReturnOrderController {
  @Resource ReturnOrderPojoServices returnOrderPojoServices;

  // 显示所有退货订单
  @RequestMapping("/showAllReturnOrders")
  Map<String, Object> showAllReturnOrders(String agencyName) {
    Map<String, Object> map = new HashMap<>();
    map = returnOrderPojoServices.getAllReturnedOrderInfo(agencyName);

    return map;
  }
  // 多条件查询所有退货订单
  @RequestMapping("/queryReturnOrder")
  Map<String, Object> queryReturnOrder(@RequestBody ReturnOrderInfo returnOrderInfo) {
    System.out.println("========>" + returnOrderInfo);
    Map<String, Object> map = new HashMap<>();
    map = returnOrderPojoServices.getReturnedOrderPojoByMultipleCondition(returnOrderInfo);

    return map;
  }
  // 生成退货订单
  @RequestMapping("/generateReturnOrder")
  Map<String, Object> generateReturnOrder(@RequestBody HashMap param) {
    Map<String, Object> map = new HashMap<>();

    String items = JSON.toJSONString(param.get("returnOrderItems"));
    List<ReturnOrderItem> returnOrderItems = JSON.parseArray(items, ReturnOrderItem.class);
    // List<ReturnOrderItem> returnOrderItems = (List<ReturnOrderItem>)
    // param.get("returnOrderItems");
    String jsonString = JSON.toJSONString(param.get("returnOrderInfo"));
    ReturnOrderInfo returnOrderInfo = JSON.parseObject(jsonString, ReturnOrderInfo.class);

    System.out.println("====>" + returnOrderItems);
    System.out.println("====>" + returnOrderInfo);
    map = returnOrderPojoServices.generateReturnOrder(returnOrderInfo, returnOrderItems);

    return map;
  }
  // 操作退货订单，即改变退货订单状态
  @RequestMapping("/updateReturnOrder")
  Map<String, Object> updateReturnOrder(@RequestBody Map<String, Object> operateMap) {
    Map<String, Object> map = new HashMap<>();
    map = returnOrderPojoServices.changeReturnedOrderStatusByReturnedOrderNum(operateMap);

    return map;
  }
}
