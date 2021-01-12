package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data                                               // 生成getter setter方法
@NoArgsConstructor                                  // 无参构造
@AllArgsConstructor                                 // 全参构造
@Component                                          // 放在Spring容器中作为一个组件
public class OrderInfo {
    private String orderNumber        ;  //销售订单号
    private Integer orderStatus       ;  //订单状态
    private String sourceNumber       ;  //销售订单来源单号
    private String agencyName         ;  //经销商
    private String memberName         ;  //所属网店（即会员）
    private Date orderDate            ;  //订单日期
    private String buyerNickname      ;  //买家昵称
    private String expressCompany     ;  //快递公司
    private String expressNumber      ;  //快递单号
    private String deliveringWarehouse;  //发货仓名称
}
