package io.github.futurewl.imooc.java.authority.management.dto;

import com.google.common.collect.Lists;
import io.github.futurewl.imooc.java.authority.management.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-19:14:28
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }

}
