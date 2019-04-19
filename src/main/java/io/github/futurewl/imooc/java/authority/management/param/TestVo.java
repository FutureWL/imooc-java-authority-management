package io.github.futurewl.imooc.java.authority.management.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-18:16:12
 * @version 1.0
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull(message = "id不可以为空")
    @Max(value = 10, message = "ID 不能大于等于10")
    @Min(value = 0, message = "ID 至少大于等于0")
    private Integer id;

//    @NotEmpty
    private List<String> str;
}
