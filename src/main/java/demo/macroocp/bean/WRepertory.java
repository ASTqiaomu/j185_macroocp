package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 仓库库存表
 * 负责人：胡瑞、彭鑫宇
 */
@Data                                             // 生成getter setter方法
@NoArgsConstructor                                // 无参构造
@AllArgsConstructor                               // 全参构造
@Component                                        // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "wrepertory")   // 配置文件可以使用该前缀访问bean中的属性
public class WRepertory
{
	private String  wrID;        // 库存编号
	private String  wID;         // 仓库编号
	private String  productID;   // 产品编号
	private Integer repertory;   // 产品库存
}
