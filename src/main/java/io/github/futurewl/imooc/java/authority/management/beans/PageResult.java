package io.github.futurewl.imooc.java.authority.management.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:08:57
 * @version 1.0
 */
@Getter
@Setter
@Builder
@ToString
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total;

}
