package demo.macroocp.dao;

import demo.macroocp.bean.TransferInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 调拨单信息 DAO
 */
public interface TransferInfoDao
{
	// 添加 调拨单
//	@Insert("insert into transferinfot values(#{tsID}, #{tsType}, #{creator}, #{calloutwID}, #{callinwID}, #{transMode}, #{stockOrderID}, #{agencyID}, #{tsVolumeDose}, #{tsTotalVolume}, #{createDate}, #{productID}, #{remark})")
	void insertIntoTransferInfo(TransferInfo transferInfo);

	// 获取最新的一条记录的调拨单编号
	@Select("select tsID from TransferInfoT ORDER BY createDate desc LIMIT 0, 1")
	List<String> selectTsIDLatest();
}
