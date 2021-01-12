package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data                                               // 生成getter setter方法
@NoArgsConstructor                                  // 无参构造
@AllArgsConstructor                                 // 全参构造
@Component                                          // 放在Spring容器中作为一个组件
public class OrderShipping {
    private String receiverProvince  ; //收货地址_省
    private String receiverCity      ; //收货地址_市
    private String receiverArea      ; //收货地址_区
    private String receiverStreet    ; //详细收货地址/街道
    private String receiveZip        ; //邮编
    private String receiverName      ; //收货人
    private String receiverMobile    ; //收货人手机
}
