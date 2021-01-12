package demo.macroocp.controller;

import demo.macroocp.bean.ProductInfo;
import demo.macroocp.bean.StockOrder;
import demo.macroocp.services.ProductInfoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 产品信息 Controller
 */
@CrossOrigin
@RestController
public class ProductInfoController
{
	@Resource
	private ProductInfoServices productInfoServices;
	@Resource
	private ProductInfo productInfo;

	// 电商调拨单 选择备货订单编号后 显示产品信息
	@RequestMapping("/getallbystockorderid")
	public Map<String, Object> getAllByStockOrderID(StockOrder stockOrder)
	{
		return this.productInfoServices.getAllByStockOrderID(stockOrder);
	}

	// 修改 调拨数量
	@RequestMapping("/updatetsquantity")
	public Map<String, Object> updateTsQuantity(String productID, Integer tsQuantity, String wID)
	{
		return this.productInfoServices.updateTsQuantity(productID, tsQuantity, wID);
	}

	// 获取所有 产品信息
	@RequestMapping("/getallproductinfo")
	public Map<String, Object> getAllProductInfo()
	{
		return this.productInfoServices.getAllProductInfo();
	}

	// 根据 产品编号 获取所有 产品信息 getAllByProductID
	@RequestMapping("/getallbyproductid")
	public Map<String, Object> getAllByProductID(ProductInfo productInfo)
	{
		return this.productInfoServices.getAllByProductID(productInfo);
	}
}
