package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 调拨单列表(查询信息)
 * 所属模块：3.6
 * 负责人：胡瑞、彭鑫宇
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "transferorderlist")
public class TransferOrderList {

    //产品信息表表项
    private Integer serialNum;           // 序号--唯一
    private String productID;           // 产品编号--主键
    private String productName;         // 产品名称
    private String type;                // 品类
    private Integer callinWInventory;    // 调入仓库库存
    private Integer tsQuantity;          // 调拨数量
    private Integer checkQuantity;       // 审核数量
    private Integer actualShipments;     // 实际发货数量
    private Integer signedQuantity;      // 已签收数量
    private String unit;                // 单位
    private Double unitVolume;          // 单位体积
    private Double price;               // 产品单价
    private String stockOrderID;        // 备货订单编号--外键

    //调拨单信息表表项
    private String tsID;               // 调拨单号--主键
    private String tsType;             // 调拨类型
    private String creator;            // 创建者--外键
    private String calloutwID;         // 调出仓库--外键
    private String callinwID;          // 调入仓库--外键
    private String transMode;          // 发送方式
    //    private String  stockOrderID;    备货订单编号--外键
//    private String  agencyID;        送货地址--外键
    private Integer tsVolumeDose;       // 调拨总量
    private Double tsTotalVolume;      // 调拨总体积
    private Date createDate;         // 创建日期 需要具体时
    //    private String  productID;       产品编号--外键
    private String remark;             // 备注

    //查看详细信息：
    //运营角色表表项
    private String userName;        //用户名
    private String userID;          //用户编号，格式OAxxx，OA代表运营账号，xxx为具体编号
    private String PASSWORD;        //密码
    private String trueName;        //真实姓名
    private String organization;    //所属机构
    private String productLines;     //所属产品线
    private String rName;           //角色名称，可以同时是多个角色，以字段拼接实现
    private String sex;             //性别，M为男性，F为女性
    private String phone;           //手机号码，前2位为国家代码，后11位为手机号码
    private String email;           //电子邮箱

    //经销商表表项
    private String agencyID;       // 经销商编号--主键
    private String agencyName;     // 经销商名称
    private String deliverAd;      // 收货地址--唯一
    private String consignee;      // 收货人
    private String consigneeTel;   // 收货人电话
    private String consigneeID;    // 收货人身份证

    //库存表表项
    private String wrID;        // 库存编号
    private String wID;         // 仓库编号
    private Integer repertory;   // 产品库存

    //查询条件：时间范围
    private Date startDate;     //开始时间
    private Date endDate;       //结束时间

    //查询条件：出入库名称
    private String wOrganizationOut; //出库机构
    private String wnameOut;    //出库名称
    private String wOrganizationIn; //出库机构
    private String wnameIn;     //入库名称
}
