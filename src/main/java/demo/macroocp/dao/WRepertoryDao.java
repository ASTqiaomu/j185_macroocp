package demo.macroocp.dao;

import demo.macroocp.bean.WRepertory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 仓库库存 DAO
 */
public interface WRepertoryDao
{
	// 根据仓库编号和产品编号，查找该产品在该仓库中的 库存
	@Select("select repertory from wrepertoryt where wID=#{wID} and productID=#{productID}")
	List<Integer> selectRepertory(WRepertory wRepertory);
}
