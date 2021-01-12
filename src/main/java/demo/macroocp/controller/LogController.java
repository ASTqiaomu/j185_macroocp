package demo.macroocp.controller;

import demo.macroocp.bean.Log;
import demo.macroocp.services.LogServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
public class LogController {
    @Resource
    private LogServices logServices;

    @RequestMapping("/getLogBylogID")
    public Map<String, Object> getLogBylogID(Log log) {
        return this.logServices.getLogBylogID(log);
    }
    @RequestMapping("/getLogByopType")
    public Map<String, Object> getLogByopType(Log log) {
        return this.logServices.getLogByopType(log);
    }
    @RequestMapping("/getLogByopObject")
    public Map<String, Object> getLogByopObject(Log log) {
        return this.logServices.getLogByopObject(log);
    }
}
