package demo.macroocp.services.impl;

import demo.macroocp.bean.TransferOrderList;
import demo.macroocp.dao.TransferOrderListDao;
import demo.macroocp.services.TransferOrderListServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransferOrderListServicesImpl implements TransferOrderListServices {

    @Resource
    private TransferOrderListDao transferOrderListDao;

    @Override
    public Map<String, Object> getAllTransferOrderList(TransferOrderList transferOrderList) {

        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<TransferOrderList> list = this.transferOrderListDao.selectTransferOrderList(transferOrderList);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllOrderInformationByTsID(TransferOrderList transferOrderList) {

        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<TransferOrderList> list = this.transferOrderListDao.selectOrderInformationByTsID(transferOrderList);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllOrderProductListByProductID(TransferOrderList transferOrderList) {

        Map<String, Object> map = new HashMap<>();
        //调用数据访问层的模块
        List<TransferOrderList> list = this.transferOrderListDao.selectOrderProductListByProductID(transferOrderList);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            //返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
}
