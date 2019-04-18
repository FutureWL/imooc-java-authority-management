package io.github.futurewl.imooc.java.authority.management.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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

    @NotNull
    private Integer id;

}
