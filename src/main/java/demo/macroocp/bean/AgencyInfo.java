package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 经销商信息表
 * 所属模块：3.6
 * 负责人：胡瑞、彭鑫宇
 */
@Data                                              // 生成getter setter方法
@NoArgsConstructor                                 // 无参构造
@AllArgsConstructor                                // 全参构造
@Component                                         // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "agencyinfo")    // 配置文件可以使用该前缀访问bean中的属性
public class AgencyInfo
{
	private String agencyID;       // 经销商编号--主键
	private String agencyName;     // 经销商名称
	private String deliverAd;      // 收货地址--唯一
	private String consignee;      // 收货人
	private String consigneeTel;   // 收货人电话
	private String consigneeID;    // 收货人身份证
}
