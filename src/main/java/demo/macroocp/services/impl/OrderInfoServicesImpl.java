package demo.macroocp.services.impl;

import demo.macroocp.bean.OrderInfo;
import demo.macroocp.dao.OrderInfoDao;
import demo.macroocp.services.OrderInfoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderInfoServicesImpl implements OrderInfoServices {
  @Resource OrderInfoDao orderInfoDao;

  @Override
  public Map<String, Object> getOrderInfoByAgencyName(String agencyName) {
    Map<String, Object> map = new HashMap<>();
    List<OrderInfo> orderInfoList = orderInfoDao.getOrderInfoByAgencyName(agencyName);
    map.put("orderInfoList", orderInfoList);

    return map;
  }
}
