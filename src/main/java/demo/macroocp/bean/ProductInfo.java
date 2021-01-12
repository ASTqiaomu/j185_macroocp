package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 产品信息表
 * 所属模块：3.6
 * 负责人：胡瑞、彭鑫宇
 */
@Data                                               // 生成getter setter方法
@NoArgsConstructor                                  // 无参构造
@AllArgsConstructor                                 // 全参构造
@Component                                          // 放在Spring容器中作为一个组件
@ConfigurationProperties(prefix = "productinfo")    // 配置文件可以使用该前缀访问bean中的属性
public class ProductInfo
{
	private Integer serialNum;           // 序号--唯一
	private String  productID;           // 产品编号--主键
	private String  productName;         // 产品名称
	private String  type;                // 品类
	private Integer callinWInventory;    // 调入仓库库存
	private Integer tsQuantity;          // 调拨数量
	private Integer checkQuantity;       // 审核数量
	private Integer actualShipments;     // 实际发货数量
	private Integer signedQuantity;      // 已签收数量
	private String  unit;                // 单位
	private Double  unitVolume;          // 单位体积
	private Double  price;               // 产品单价
	private String  stockOrderID;        // 备货订单编号--外键
}
