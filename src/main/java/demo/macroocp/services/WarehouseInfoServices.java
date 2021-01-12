package demo.macroocp.services;

import demo.macroocp.bean.WarehouseInfo;

import java.util.Map;

/**
 * 仓库信息 Service
 */
public interface WarehouseInfoServices
{
	// 返回 机构名称
	Map<String, Object> getAllWOrganization();

	// 根据 机构名称 返回该机构下的所有 仓库名称
	Map<String, Object> getWNameByWOrganization(WarehouseInfo warehouseInfo);

	// 根据 机构 和 仓库名称 得到 仓库编号
	Map<String, Object> getWIDByWOrgAndWName(WarehouseInfo warehouseInfo);
}
