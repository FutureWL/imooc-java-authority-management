package io.github.futurewl.imooc.java.authority.management.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:11:44
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class AclModuleParam {

    private Integer id;

    @NotBlank(message = "权限模块不能为空")
    @Length(min = 2, max = 20, message = "权限模块名称长度需要在2～20个字之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    @NotNull(message = "权限模块状态不能为空")
    private Integer status;

    @Length(max = 200, message = "权限模块备注长度需要在200个字以内")
    private String remark;

}
