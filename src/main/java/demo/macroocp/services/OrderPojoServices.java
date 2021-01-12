package demo.macroocp.services;

import java.util.Map;

public interface OrderPojoServices {
  Map<String, Object> getOrderPojoByAgencyName(String agencyName);

  Map<String, Object> getOrderPojoByMultipleCondition(Map<String, String> map);
}
