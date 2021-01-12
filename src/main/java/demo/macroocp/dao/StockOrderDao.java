package demo.macroocp.dao;

import demo.macroocp.bean.StockOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 备货订单 DAO
 */
public interface StockOrderDao
{
	// 显示所有备货订单编号
	@Select("select * from stockordert")
	List<StockOrder> selectAllStockOrderID();
}
