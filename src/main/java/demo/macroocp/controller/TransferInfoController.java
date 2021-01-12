package demo.macroocp.controller;

import demo.macroocp.bean.TransferInfo;
import demo.macroocp.services.TransferInfoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 调拨单 Controller
 */
@CrossOrigin
@RestController
public class TransferInfoController
{
	@Resource
	private TransferInfoServices transferInfoServices;

	// 添加 调拨单
	@RequestMapping("/addintotransferinfo")
	public Map<String, Object> addIntoTransferInfo(@RequestBody TransferInfo transferInfo)
	{
		return this.transferInfoServices.addIntoTransferInfo(transferInfo);
	}

	// 获取最新一条记录的调拨单编号
	@RequestMapping("/gettsidlatest")
	public Map<String, Object> getTsIDLatest()
	{
		return this.transferInfoServices.getTsIDLatest();
	}
}
