package demo.macroocp.dao;

import demo.macroocp.bean.AgencyInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 经销商信息 DAO
 */
public interface AgencyInfoDao
{
	// 根据 经销商名称 返回其所属地址
	@Select("select deliverAd from agencyinfot where agencyName=#{agencyName}")
	List<String> selectDeliverAdByAgencyName(AgencyInfo agencyInfo);

	// 根据 收货地址 查找信息
	@Select("select * from agencyinfot where deliverAd=#{deliverAd}")
	List<AgencyInfo> selectAllByDeliverAd(AgencyInfo agencyInfo);

	// 返回 经销商名称
	@Select("select distinct agencyName from agencyinfot")
	List<String> selectAgencyName();

	// 返回 经销商编号 通过 经销商名称 和 送货地址
	@Select("select agencyID from agencyinfot where agencyName=#{agencyName} and deliverAd=#{deliverAd}")
	List<String> selectAgencyID(AgencyInfo agencyInfo);
}
