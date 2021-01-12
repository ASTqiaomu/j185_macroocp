package demo.macroocp.controller;

import demo.macroocp.bean.WRepertory;
import demo.macroocp.services.WRepertoryServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 仓库库存 Controller
 */
@CrossOrigin
@RestController
public class WRepertoryController
{
	@Resource
	private WRepertoryServices wRepertoryServices;

	// 根据仓库编号和产品编号，查找该产品在该仓库中的 库存
	@RequestMapping("/getrepertory")
	public Map<String, Object> getRepertory(WRepertory wRepertory)
	{
		return this.wRepertoryServices.getRepertory(wRepertory);
	}
}
