package io.github.futurewl.imooc.java.authority.management.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-24:15:26
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class RoleParam {

    private Integer id;

    @NotNull(message = "角色名称不可以为空")
    @Length(max = 20, min = 2, message = "角色名称长度需要在2-64个字之间")
    private String name;

    @NotNull(message = "必须指定角色类型")
    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer type = 1;

    @NotNull(message = "必须指定角色的状态")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    @Length(max = 256, message = "角色URL长度需要在256个字符以内")
    private String remark;

}
