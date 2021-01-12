package demo.macroocp.services.impl;

import demo.macroocp.bean.AgencyInfo;
import demo.macroocp.dao.AgencyInfoDao;
import demo.macroocp.services.AgencyInfoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AgencyInfoServicesImpl implements AgencyInfoServices
{
	@Resource
	private AgencyInfoDao agencyInfoDao;

	@Override
	public Map<String, Object> getDeliverAdByAgencyName(AgencyInfo agencyInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = agencyInfoDao.selectDeliverAdByAgencyName(agencyInfo);
		if (list.size() > 0)
		{
			map.put("code", 0);
			map.put("list", list);
			map.put("count", list.size());
			map.put("message", "查询信息成功！");
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("count", 0);
			map.put("message", "抱歉，没有查询到任何信息...");
		}

		System.out.println(map);

		return map;
	}

	@Override
	public Map<String, Object> getAllByDeliverAd(AgencyInfo agencyInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<AgencyInfo> list = agencyInfoDao.selectAllByDeliverAd(agencyInfo);
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
	public Map<String, Object> getAgencyName()
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = agencyInfoDao.selectAgencyName();
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
	public Map<String, Object> getAgencyID(AgencyInfo agencyInfo)
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = agencyInfoDao.selectAgencyID(agencyInfo);
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
