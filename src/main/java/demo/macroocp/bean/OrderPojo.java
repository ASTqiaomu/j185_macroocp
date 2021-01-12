package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data // 生成getter setter方法
@ToString(callSuper = true)
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
@Component // 放在Spring容器中作为一个组件
public class OrderPojo extends OrderInfo {
  private List<OrderItem> orderItems;
  private OrderShipping orderShipping;
}
