package demo.macroocp.services;

import demo.macroocp.bean.WRepertory;

import java.util.Map;

/**
 * 仓库库存 Service
 */
public interface WRepertoryServices
{
	// 根据仓库编号和产品编号，查找该产品在该仓库中的 库存
	Map<String, Object> getRepertory(WRepertory wRepertory);
}
