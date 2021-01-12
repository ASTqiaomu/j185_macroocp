package demo.macroocp.services;

import demo.macroocp.bean.TransferOrderList;

import java.util.Map;

/**
 * 调拨单列表 Service
 */
public interface TransferOrderListServices {

    //获取调拨单列表，显示调拨单和相关产品信息
    Map<String, Object> getAllTransferOrderList(TransferOrderList transferOrderList);

    //查看调拨单列表详情--订单信息：获取创建人、经销商、仓库、调拨单等相关信息
    Map<String, Object> getAllOrderInformationByTsID(TransferOrderList transferOrderList);

    //查看调拨单列表详情--产品信息：获取展示出入仓库、商品等相关信息
    Map<String, Object> getAllOrderProductListByProductID(TransferOrderList transferOrderList);
}
