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
 * @author weilai create by 2019-04-24:13:46
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class AclParam {

    private Integer id;

    @NotNull(message = "权限点名称不可以为空")
    @Length(max = 64, min = 2, message = "权限点名称长度需要在2-64个字之间")
    private String name;

    @NotNull(message = "必须指定权限模块")
    private Integer arlModuleId;

    @Length(min = 6, max = 256, message = "权限点URL长度需要在6～256个字符之间")
    private String url;

    @NotNull(message = "必须指定权限点类型")
    @Min(value = 0, message = "权限点类型不合法")
    @Max(value = 2, message = "权限点类型不合法")
    private Integer type;

    @NotNull(message = "必须指定权限点状态")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 2, message = "权限点状态不合法")
    private Integer status;

    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;

    @Length(max = 256, message = "权限点URL长度需要在256个字符以内")
    private String remark;

}
