package demo.macroocp.services.impl;

import demo.macroocp.bean.ReturnOrderAudit;
import demo.macroocp.bean.ReturnOrderInfo;
import demo.macroocp.bean.ReturnOrderItem;
import demo.macroocp.bean.ReturnOrderPojo;
import demo.macroocp.dao.ReturnOrderAuditDao;
import demo.macroocp.dao.ReturnOrderInfoDao;
import demo.macroocp.dao.ReturnOrderItemDao;
import demo.macroocp.dao.ReturnOrderPojoDao;
import demo.macroocp.services.ReturnOrderPojoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ReturnOrderPojoServicesImpl implements ReturnOrderPojoServices {
  @Resource ReturnOrderInfoDao returnOrderInfoDao;
  @Resource ReturnOrderItemDao returnOrderItemDao;
  @Resource ReturnOrderPojoDao returnOrderPojoDao;
  @Resource ReturnOrderAuditDao returnOrderAuditDao;
  @Resource ReturnOrderAudit returnOrderAudit;

  @Override
  public Map<String, Object> generateReturnOrder(
      ReturnOrderInfo returnOrderInfo, List<ReturnOrderItem> returnOrderItems) {
    Map<String, Object> map = new HashMap<>();
    // 自动生成16位退货订单编号
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    TimeZone time = TimeZone.getTimeZone("Etc/GMT-8"); // 转换为中国时区
    TimeZone.setDefault(time);
    String newDate = sdf.format(new Date());
    String result = "";
    Random random = new Random();
    for (int i = 0; i < 2; i++) {
      result += random.nextInt(10);
    }
    String returnOrderNumber = newDate + result;
    // 设置退货订单编号
    returnOrderInfo.setReturnOrderNumber(returnOrderNumber);
    // 设置退货订单生成日期
    returnOrderInfo.setReturnOrderDate(new Date());
    // 插入退货订单信息表
    int affectedInfoAmount = returnOrderInfoDao.addReturnOrderInfo(returnOrderInfo);
    map.put("affectedInfoAmount", affectedInfoAmount);
    // 插入退货订单商品表
    for (ReturnOrderItem returnOrderItem : returnOrderItems) {
      // 设置退货订单编号
      returnOrderItem.setReturnOrderNumber(returnOrderNumber);
      // 插入数据库
      returnOrderItemDao.addReturnOrderItem(returnOrderItem);
    }
    // 插入退货订单审核表
    returnOrderAudit.setReturnOrderNumber(returnOrderInfo.getReturnOrderNumber());
    returnOrderAuditDao.addReturnOrderAudit(returnOrderAudit);
    return map;
  }

  @Override
  public Map<String, Object> getAllReturnedOrderInfo(String agencyName) {
    Map<String, Object> map = new HashMap<>();
    List<ReturnOrderPojo> returnOrderPojoList =
        returnOrderPojoDao.getAllReturnedOrderInfo(agencyName);
    map.put("returnOrderPojoList", returnOrderPojoList);

    return map;
  }

  @Override
  public Map<String, Object> getReturnedOrderPojoByMultipleCondition(
      ReturnOrderInfo returnOrderInfo) {
    Map<String, Object> map = new HashMap<>();
    List<ReturnOrderPojo> returnOrderPojoList =
        returnOrderPojoDao.getReturnedOrderPojoByMultipleCondition(returnOrderInfo);
    map.put("returnOrderPojoList", returnOrderPojoList);

    return map;
  }

  @Override
  public Map<String, Object> changeReturnedOrderStatusByReturnedOrderNum(Map<String, Object> map) {
    Map<String, Object> map1 = new HashMap<>();
    // 修改退货订单状态-运营人员操作：确认收货，审核，审核结算
    int affectedAmount = returnOrderInfoDao.changeReturnedOrderStatusByReturnedOrderNum(map);
    map1.put("affectedAmount", affectedAmount);
    // 1.若操作为审核不通过或者通过，设置审核人以及审核日期
    if (map.containsKey("returnOrderStatu")
        && (map.get("returnOrderStatu").toString().equals("5")
            || map.get("returnOrderStatu").toString().equals("4"))) {
      returnOrderAudit.setReturnOrderNumber(map.get("returnOrderNumber").toString());
      returnOrderAudit.setAuditorName(map.get("auditorName").toString());
      returnOrderAudit.setAuditDate(new Date());
      // 2.若为审核驳回,设置退回原因
      if (map.get("returnOrderStatu").toString().equals("4")) {
        returnOrderAudit.setRejectedReason(map.get("rejectedReason").toString());
      }
      // 向退货订单审核表中插入数据
      // returnOrderAuditDao.addReturnOrderAudit(returnOrderAudit);
      // 更新退货订单审核表
      returnOrderAuditDao.updateReturnOrderAudit(returnOrderAudit);
    }
    // 3.若操作为确认结算，则设置对应退货订单信息表中的“应恢复货款金额”项
    if (map.containsKey("returnOrderStatu") && map.get("returnOrderStatu").toString().equals("6")) {
      Map<String, Object> paymentMap = new HashMap<>();
      paymentMap.put("returnOrderNumber", map.get("returnOrderNumber"));
      paymentMap.put("returnedPayment", map.get("returnedPayment"));
      // 设置退货订单信息表的返还货款
      returnOrderInfoDao.setReturnedPaymentByReturnOrderNum(paymentMap);
      // 设置审核结算人信息和时间
      returnOrderAudit.setReturnOrderNumber(map.get("returnOrderNumber").toString());
      returnOrderAudit.setSettlementAuditName(map.get("settlementAuditName").toString());
      returnOrderAudit.setSettlementAuditDate(new Date());
      // 更新审核表
      returnOrderAuditDao.updateReturnOrderAudit(returnOrderAudit);
    }
    return map1;
  }
}
