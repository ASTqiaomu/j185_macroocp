package demo.macroocp.services;

import java.util.Map;

public interface OrderInfoServices {
    Map<String, Object> getOrderInfoByAgencyName(String agencyName);
}
