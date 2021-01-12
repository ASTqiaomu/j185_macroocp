package demo.macroocp.services;

import demo.macroocp.bean.TransferInfo;

import java.util.Map;

/**
 * 调拨单 Service
 */
public interface TransferInfoServices
{
	// 添加 调拨单
	Map<String, Object> addIntoTransferInfo(TransferInfo transferInfo);

	// 获取最新一条记录的调拨单编号
	Map<String, Object> getTsIDLatest();
}
