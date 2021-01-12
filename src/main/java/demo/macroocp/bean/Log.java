package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "log")
public class Log {
    private Integer logID;          //日志编号
    private String opType;          //操作类型，增加ADD，删除DEL，编辑EDT
    private String opObject;        //操作对象，ORxxx或者OAxxx
    private Date opTime;            //操作时间
    private String opDescription;   //操作内容
}
