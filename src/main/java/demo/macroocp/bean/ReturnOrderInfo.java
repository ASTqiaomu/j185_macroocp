package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data // 生成getter setter方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
@Component // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "returnorderinfo")
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ReturnOrderInfo {
  private String returnOrderNumber; // 退货订单编号（主键）
  private Date returnOrderDate; // 退货提交日期
  private String saleOrderNumber; // 原销售订单号（外键）
  private Integer returnOrderStatu; // 退货订单状态
  private String returnType; // 退货方式
  private String returnReason; // 退货原因
  private String expressCompany; // 快递公司
  private String expressNumber; // 快递单号
  private String customerRemark; // 客户备注
  private String oWarehouseName; // 原发货仓库名称
  private String returnedWarehouseName; // 退回仓库
  private String agencyName; // 经销商账号
  private String memberName; // 会员名称
  private Double returnedPayment; // 应恢复货款金额
}
