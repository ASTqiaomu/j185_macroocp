package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "oa")
public class OperatingAccount {
    private String userName;        //用户名
    private String userID;          //用户编号，格式OAxxx，OA代表运营账号，xxx为具体编号
    private String PASSWORD;        //密码
    private String trueName;        //真实姓名
    private String organization;    //所属机构
    private String productLines;     //所属产品线
    private String rName;           //角色名称，可以同时是多个角色，以字段拼接实现
    private String sex;             //性别，M为男性，F为女性
    private String phone;           //手机号码，前2位为国家代码，后11位为手机号码
    private String email;           //电子邮箱
    private Integer aStatus;        //账号状态，0为禁用，1为启用，默认启用
}
