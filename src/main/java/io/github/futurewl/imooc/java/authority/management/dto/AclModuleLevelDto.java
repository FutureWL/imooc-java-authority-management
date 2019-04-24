package io.github.futurewl.imooc.java.authority.management.dto;

import com.google.common.collect.Lists;
import io.github.futurewl.imooc.java.authority.management.model.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:12:32
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class AclModuleLevelDto extends SysAclModule {

    private List<AclModuleLevelDto> aclModuleLevelDtoList = Lists.newArrayList();

    public static AclModuleLevelDto aAclModule(SysAclModule aclModule) {
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }


}
