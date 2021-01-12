package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data // 生成getter setter方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
@Component // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "returnorderaudit")
public class ReturnOrderAudit {
  private String returnOrderNumber; // 退货订单编号
  private String auditorName; // 审核人名称
  private String settlementAuditName; // 审核结算人名称
  private Date auditDate; // 审核时间
  private Date settlementAuditDate; // 审核结算时间
  private String rejectedReason; // 驳回原因
}
