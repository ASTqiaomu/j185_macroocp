package demo.macroocp.dao;

import demo.macroocp.bean.WarehouseInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 仓库信息 DAO
 */
public interface WarehouseInfoDao
{
	// 返回 机构名称 不重复
	@Select("select distinct wOrganization from warehouseinfot")
	List<String> selectAllWOrganization();

	// 根据 机构名称 返回该机构下的所有 仓库名称
	@Select("select wName from warehouseinfot where wOrganization=#{wOrganization}")
	List<String> selectWNameByWOrganization(WarehouseInfo warehouseInfo);

	// 根据 机构 和 仓库名称 返回 仓库编号
	@Select("select wID from warehouseinfot where wOrganization=#{wOrganization} and wName=#{wName}")
	List<String> selectWIDByWOrgAndWName(WarehouseInfo warehouseInfo);
}
