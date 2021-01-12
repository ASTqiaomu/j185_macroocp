package demo.macroocp.controller;

import demo.macroocp.bean.WarehouseInfo;
import demo.macroocp.services.WarehouseInfoServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class WarehouseInfoController
{
	@Resource
	private WarehouseInfoServices warehouseInfoServices;

	// 返回 机构名称
	@RequestMapping("/getallworganization")
	public Map<String, Object> getAllWOrganization()
	{
		return this.warehouseInfoServices.getAllWOrganization();
	}

	// 根据 机构名称 返回该机构下的所有 仓库名称
	@RequestMapping("/getwnamebyworganization")
	public Map<String, Object> getWNameByWOrganization(WarehouseInfo warehouseInfo)
	{
		return this.warehouseInfoServices.getWNameByWOrganization(warehouseInfo);
	}

	// 根据 机构 和 仓库名称 得到 仓库编号
	@RequestMapping("/getwidbyworgandwname")
	public Map<String, Object> getWIDByWOrgAndWName(WarehouseInfo warehouseInfo)
	{
		return this.warehouseInfoServices.getWIDByWOrgAndWName(warehouseInfo);
	}
}
