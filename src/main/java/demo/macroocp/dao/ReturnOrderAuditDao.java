package demo.macroocp.dao;

import demo.macroocp.bean.ReturnOrderAudit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnOrderAuditDao {
  int addReturnOrderAudit(ReturnOrderAudit returnOrderAudit);

  int updateReturnOrderAudit(ReturnOrderAudit returnOrderAudit);
}
