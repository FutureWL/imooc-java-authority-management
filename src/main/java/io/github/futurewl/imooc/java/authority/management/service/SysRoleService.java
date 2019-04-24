package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.base.Preconditions;
import io.github.futurewl.imooc.java.authority.management.common.RequestHolder;
import io.github.futurewl.imooc.java.authority.management.dao.SysRoleMapper;
import io.github.futurewl.imooc.java.authority.management.exception.ParamException;
import io.github.futurewl.imooc.java.authority.management.model.SysAcl;
import io.github.futurewl.imooc.java.authority.management.model.SysRole;
import io.github.futurewl.imooc.java.authority.management.param.RoleParam;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import io.github.futurewl.imooc.java.authority.management.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-24:15:25
 * @version 1.0
 */
@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    public void save(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已存在");
        }
        SysRole role = SysRole.builder()
                .name(param.getName())
                .type(param.getType())
                .remark(param.getRemark())
                .status(param.getStatus())
                .build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        role.setOperatorTime(new Date());
        sysRoleMapper.insertSelective(role);
    }

    public void update(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新权限点不存在");
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限点");
        }
        SysRole after = SysRole
                .builder()
                .id(param.getId())
                .name(param.getName())
                .type(param.getType())
                .remark(param.getRemark())
                .status(param.getStatus())
                .build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperatorTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(String name, Integer id) {
        return false;
    }

}
