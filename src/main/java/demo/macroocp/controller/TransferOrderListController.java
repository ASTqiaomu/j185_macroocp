package demo.macroocp.controller;

import demo.macroocp.bean.TransferOrderList;
import demo.macroocp.services.TransferOrderListServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 调拨单列表 Controller
 */
@CrossOrigin
@RestController
public class TransferOrderListController {

    @Resource
    private TransferOrderListServices transferOrderListServices;

    //查看调拨单列表
    @RequestMapping("/getalltransferorderlist")
    public Map<String, Object> getAllTransferOrderList(TransferOrderList transferOrderList){
        return this.transferOrderListServices.getAllTransferOrderList(transferOrderList);
    }

    //查看详细信息（订单信息）
    @RequestMapping("/getallorderinformation")
    public Map<String, Object> getAllOrderInformationByTsID(TransferOrderList transferOrderList){
        return this.transferOrderListServices.getAllOrderInformationByTsID(transferOrderList);
    }

    //查看详细信息（产品信息）
    @RequestMapping("/getallorderproductlist")
    public Map<String, Object> getAllOrderProductListByProductID(TransferOrderList transferOrderList){
        return this.transferOrderListServices.getAllOrderProductListByProductID(transferOrderList);
    }

}
