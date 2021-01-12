package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 抬头表
 */
@Data                                         // 生成getter setter方法
@NoArgsConstructor                            // 无参构造
@AllArgsConstructor                           // 全参构造
@Component                                    // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "buyer")    // 配置文件可以使用该前缀访问bean中的属性
public class Buyer
{
	private String businessUnit;   // 事业部--主键
	private String companyName;    // 所属公司名称
}
