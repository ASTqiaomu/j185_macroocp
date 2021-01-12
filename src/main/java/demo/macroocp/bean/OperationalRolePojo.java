package demo.macroocp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OperationalRolePojo {
    private String rName;//角色名称
    private String rID;//角色编号，格式ORxxx，OR代表运营角色，xxx为具体编号
    private String rDescription;//角色描述
    private String rStatus;//角色状态，0为禁用，1为启用，默认启用
    private Integer rPermissionLevel;//权限等级，0为最高权限，数字越大，权限越低
}
