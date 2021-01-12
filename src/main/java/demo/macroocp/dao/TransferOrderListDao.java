package demo.macroocp.dao;

import demo.macroocp.bean.TransferOrderList;

import java.util.List;

/**
 * 调拨单列表信息 DAO
 */
public interface TransferOrderListDao {

    //调拨单列表，根据选项动态查询调拨单和相关产品信息
    List<TransferOrderList> selectTransferOrderList(TransferOrderList transferOrderList);

    //查看调拨单列表详情--根据调拨单号订单信息：展示创建人、经销商、仓库、调拨单等相关信息
    List<TransferOrderList> selectOrderInformationByTsID(TransferOrderList transferOrderList);

    //查看调拨单列表详情--根据产品ID查看产品信息：展示出入仓库、商品等相关信息
    List<TransferOrderList> selectOrderProductListByProductID(TransferOrderList transferOrderList);
}
