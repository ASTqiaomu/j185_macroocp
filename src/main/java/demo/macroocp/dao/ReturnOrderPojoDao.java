package demo.macroocp.dao;

import demo.macroocp.bean.ReturnOrderInfo;
import demo.macroocp.bean.ReturnOrderPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReturnOrderPojoDao {
  List<ReturnOrderPojo> getReturnedOrderPojoByMultipleCondition(ReturnOrderInfo returnOrderInfo);

  List<ReturnOrderPojo> getAllReturnedOrderInfo(String agencyName);
}
