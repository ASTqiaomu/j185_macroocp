package demo.macroocp.services.impl;

import demo.macroocp.bean.WRepertory;
import demo.macroocp.dao.WRepertoryDao;
import demo.macroocp.services.WRepertoryServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WRepertoryServicesImpl implements WRepertoryServices
{
	@Resource
	private WRepertoryDao wRepertoryDao;

	@Override
	public Map<String, Object> getRepertory(WRepertory wRepertory)
	{
		Map<String, Object> map = new HashMap<>();

		List<Integer> list = wRepertoryDao.selectRepertory(wRepertory);
		if (list.size() > 0)
		{
			map.put("code", 0);
			map.put("list", list);
			map.put("message", "成功查询到信息！");
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("message", "抱歉，没有查询到任何信息...");
		}

		return map;
	}
}
