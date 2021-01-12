package demo.macroocp.services.impl;

import demo.macroocp.bean.TransferInfo;
import demo.macroocp.dao.TransferInfoDao;
import demo.macroocp.services.TransferInfoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransferInfoServicesImpl implements TransferInfoServices
{
	@Resource
	private TransferInfoDao transferInfoDao;

	@Override
	public Map<String, Object> addIntoTransferInfo(TransferInfo transferInfo)
	{
		Map<String, Object> map = new HashMap<>();

		this.transferInfoDao.insertIntoTransferInfo(transferInfo);
		String tsID = transferInfo.getTsID();
		if (tsID != null)
		{
			map.put("code", 0);
			map.put("tsID", tsID);
			map.put("message", "成功制定调拨单，调拨单号：" + tsID);
		}
		else
		{
			map.put("code", 1);
			map.put("flag", false);
			map.put("message", "制定调拨单失败，请联系管理员。");
		}

		return map;
	}

	@Override
	public Map<String, Object> getTsIDLatest()
	{
		Map<String, Object> map = new HashMap<>();

		List<String> list = this.transferInfoDao.selectTsIDLatest();
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
