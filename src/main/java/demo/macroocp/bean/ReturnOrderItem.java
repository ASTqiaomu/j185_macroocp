package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data // 生成getter setter方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
@Component // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "returnorderitem")
public class ReturnOrderItem {
  private String returnOrderNumber; // 退货订单编号
  private String returnProductNumber; // 退货产品编码
  private String returnProductName; // 退货产品名称
  private Integer returnProductAmount; // 退回数量
  private Double refundAmount; // 退货金额
}
