package demo.macroocp.controller;

import demo.macroocp.services.StockOrderServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 备货订单 Controller
 */
@CrossOrigin
@RestController
public class StockOrderController
{
	@Resource
	private StockOrderServices stockOrderServices;

	// 显示所有备货订单编号
	@RequestMapping("/getallstockorderid")
	public Map<String, Object> getAllStockOrderID()
	{
		return this.stockOrderServices.getAllStockOrderID();
	}
}
