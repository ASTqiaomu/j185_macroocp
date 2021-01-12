package demo.macroocp.services;

import demo.macroocp.bean.ProductInfo;
import demo.macroocp.bean.StockOrder;

import java.util.Map;

/**
 * 产品信息 Service
 */
public interface ProductInfoServices
{
	// 电商调拨单 选择备货订单编号后 显示产品信息
	Map<String, Object> getAllByStockOrderID(StockOrder stockOrder);

	// 更新 调拨数量
	Map<String, Object> updateTsQuantity(String productID, Integer tsQuantity, String wID);

	// 获取所有 产品信息
	Map<String, Object> getAllProductInfo();

	// 根据 产品编号 返回所有信息
	Map<String, Object> getAllByProductID(ProductInfo productInfo);
}
