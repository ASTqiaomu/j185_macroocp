package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data                                               // 生成getter setter方法
@NoArgsConstructor                                  // 无参构造
@AllArgsConstructor                                 // 全参构造
@Component                                          // 放在Spring容器中作为一个组件
public class OrderItem {
    private String productNumber    ; //商品编号
    private String productName      ; //商品名称
    private Integer purchaseQuantity ; //商品购买数量
    private Double productPrice     ; //商品单价
}
