package demo.macroocp.services.impl;

import demo.macroocp.bean.ProductInfo;
import demo.macroocp.bean.StockOrder;
import demo.macroocp.bean.WRepertory;
import demo.macroocp.dao.ProductInfoDao;
import demo.macroocp.dao.WRepertoryDao;
import demo.macroocp.services.ProductInfoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductInfoServicesImpl implements ProductInfoServices
{
	@Resource
	private ProductInfoDao productInfoDao;

	@Resource
	private WRepertoryDao wRepertoryDao;

	@Override
	public Map<String, Object> getAllByStockOrderID(StockOrder stockOrder)
	{
		Map<String, Object> map = new HashMap<>();

		List<ProductInfo> list = productInfoDao.selectAllByStockOrderID(stockOrder);
		if (list.size() > 0)
		{
			map.put("code", 0);
			map.put("list", list);
			map.put("count", list.size());
			map.put("message", "成功查询到信息！");
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("count", 0);
			map.put("message", "抱歉，没有查询到任何信息...");
		}

		return map;
	}

	@Override
	public Map<String, Object> updateTsQuantity(String productID, Integer tsQuantity, String wID)
	{
		Map<String, Object> map = new HashMap<>();

		WRepertory wRepertory = new WRepertory();
		wRepertory.setWID(wID);
		wRepertory.setProductID(productID);
		Integer num = this.wRepertoryDao.selectRepertory(wRepertory).get(0);

		if (tsQuantity <= num && tsQuantity >= 0)   // 修改合理 小于库存 大于0
		{
			this.productInfoDao.updateTsQuantity(productID, tsQuantity);
			map.put("code", 0);
			map.put("message", "成功修改调拨数量！");
		}
		else
		{
			map.put("code", 1);
			map.put("message", "您修改的字段小于0或大于库存量，请修改...");
		}

		return map;
	}

	@Override
	public Map<String, Object> getAllProductInfo()
	{
		Map<String, Object> map = new HashMap<>();

		List<ProductInfo> list = productInfoDao.selectAllProductInfo();
		if (list.size() > 0)
		{
			map.put("code", 0);
			map.put("list", list);
			map.put("count", list.size());
			map.put("message", "成功查询到信息！");
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("count", 0);
			map.put("message", "抱歉，没有查询到任何信息...");
		}

		return map;
	}

	@Override
	public Map<String, Object> getAllByProductID(ProductInfo productInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<ProductInfo> list = productInfoDao.selectAllByProductID(productInfo);
		if (list.size() > 0)
		{
			map.put("code", 0);
			map.put("list", list);
			map.put("count", list.size());
			map.put("message", "成功查询到信息！");
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("count", 0);
			map.put("message", "抱歉，没有查询到任何信息...");
		}

		return map;
	}
}
