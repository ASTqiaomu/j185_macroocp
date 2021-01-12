package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 调拨单信息表
 * 所属模块：3.6
 * 负责人：胡瑞、彭鑫宇
 */
@Data                                                // 生成getter setter方法
@NoArgsConstructor                                   // 无参构造
@AllArgsConstructor                                  // 全参构造
@Component                                           // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "transferinfo")    // 配置文件可以使用该前缀访问bean中的属性
public class TransferInfo
{
	private String  tsID;               // 调拨单号--主键
	private String  tsType;             // 调拨类型
	private String  creator;            // 创建者--外键
	private String  calloutwID;         // 调出仓库--外键
	private String  callinwID;          // 调入仓库--外键
	private String  transMode;          // 发送方式
	private String  stockOrderID;       // 备货订单编号--外键
	private String  agencyID;           // 送货地址--外键
	private Integer tsVolumeDose;       // 调拨总量
	private Double  tsTotalVolume;      // 调拨总体积
	private Date    createDate;         // 创建日期 需要具体时
	private String  productID;          // 产品编号--外键
	private String  remark;             // 备注
}
