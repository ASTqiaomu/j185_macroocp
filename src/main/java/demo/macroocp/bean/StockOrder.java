package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 备货订单表
 * 所属模块：3.6
 * 负责人：胡瑞、彭鑫宇
 */
@Data                                              // 生成getter setter方法
@NoArgsConstructor                                 // 无参构造
@AllArgsConstructor                                // 全参构造
@Component                                         // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "stockorder")    // 配置文件可以使用该前缀访问bean中的属性
public class StockOrder
{
	private String stockOrderID;   // 备货订单编号--主键
}
