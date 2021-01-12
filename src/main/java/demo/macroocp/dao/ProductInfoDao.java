package demo.macroocp.dao;

import demo.macroocp.bean.ProductInfo;
import demo.macroocp.bean.StockOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 产品信息 DAO
 */
public interface ProductInfoDao
{
	// 电商调拨单 选择备货订单编号后 显示产品信息
	@Select("select * from productinfot where stockOrderID=#{stockOrderID}")
	List<ProductInfo> selectAllByStockOrderID(StockOrder stockOrder);

	// 前端修改调拨数量后，更新
	@Update("update productinfot set tsQuantity=#{tsQuantity} where productID=#{productID}")
	void updateTsQuantity(String productID, Integer tsQuantity);

	// 获取所有 产品信息
	@Select("select * from productinfot")
	List<ProductInfo> selectAllProductInfo();

	// 根据 产品编号 获取所有 产品信息
	@Select("select * from productinfot where productID=#{productID}")
	List<ProductInfo> selectAllByProductID(ProductInfo productInfo);
}
