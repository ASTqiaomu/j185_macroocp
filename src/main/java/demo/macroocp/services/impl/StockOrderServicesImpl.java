package demo.macroocp.services.impl;

import demo.macroocp.bean.StockOrder;
import demo.macroocp.dao.StockOrderDao;
import demo.macroocp.services.StockOrderServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StockOrderServicesImpl implements StockOrderServices
{
	@Resource
	private StockOrderDao stockOrderDao;

	@Override
	public Map<String, Object> getAllStockOrderID()
	{
		Map<String, Object> map = new HashMap<>();

		List<StockOrder> list = stockOrderDao.selectAllStockOrderID();
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
