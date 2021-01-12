package demo.macroocp.services;

import demo.macroocp.bean.AgencyInfo;

import java.util.Map;

/**
 * 经销商信息 Service
 */
public interface AgencyInfoServices
{
	// 根据 经销商名称 返回其所属地址
	Map<String, Object> getDeliverAdByAgencyName(AgencyInfo agencyInfo);

	// 根据 收货地址 查找信息
	Map<String, Object> getAllByDeliverAd(AgencyInfo agencyInfo);

	// 查询 经销商名称
	Map<String, Object> getAgencyName();

	// 查询 经销商编号
	Map<String, Object> getAgencyID(AgencyInfo agencyInfo);
}
