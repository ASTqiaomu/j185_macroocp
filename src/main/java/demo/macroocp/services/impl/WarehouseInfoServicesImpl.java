package demo.macroocp.services.impl;

import demo.macroocp.bean.WarehouseInfo;
import demo.macroocp.dao.WarehouseInfoDao;
import demo.macroocp.services.WarehouseInfoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WarehouseInfoServicesImpl implements WarehouseInfoServices
{
	@Resource
	private WarehouseInfoDao warehouseInfoDao;

	@Override
	public Map<String, Object> getAllWOrganization()
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = warehouseInfoDao.selectAllWOrganization();
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
	public Map<String, Object> getWNameByWOrganization(WarehouseInfo warehouseInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = warehouseInfoDao.selectWNameByWOrganization(warehouseInfo);
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
	public Map<String, Object> getWIDByWOrgAndWName(WarehouseInfo warehouseInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = warehouseInfoDao.selectWIDByWOrgAndWName(warehouseInfo);
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
