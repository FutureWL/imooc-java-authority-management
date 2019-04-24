package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.base.Preconditions;
import io.github.futurewl.imooc.java.authority.management.beans.PageQuery;
import io.github.futurewl.imooc.java.authority.management.beans.PageResult;
import io.github.futurewl.imooc.java.authority.management.common.RequestHolder;
import io.github.futurewl.imooc.java.authority.management.dao.SysAclMapper;
import io.github.futurewl.imooc.java.authority.management.exception.ParamException;
import io.github.futurewl.imooc.java.authority.management.model.SysAcl;
import io.github.futurewl.imooc.java.authority.management.param.AclParam;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import io.github.futurewl.imooc.java.authority.management.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-24:13:44
 * @version 1.0
 */
@Service
public class SysAclService {

    @Resource
    private SysAclMapper sysAclMapper;

    public void save(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAclModuleId(), param.getName(), param.getId())) {
            throw new ParamException("同层级下存在相同名称的权限点");
        }
        SysAcl acl = SysAcl
                .builder()
                .name(param.getName())
                .arlModuleId(param.getAclModuleId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        acl.setCode(generateCode());
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        acl.setOperatorTime(new Date());
        sysAclMapper.insertSelective(acl);
    }

    public void update(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAclModuleId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限点");
        }
        SysAcl before = sysAclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新权限点不存在");
        if (checkExist(param.getAclModuleId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限点");
        }
        SysAcl after = SysAcl
                .builder()
                .id(param.getId())
                .name(param.getName())
                .arlModuleId(param.getAclModuleId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperatorTime(new Date());
        sysAclMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(Integer aclModuleId, String name, Integer id) {
        return sysAclMapper.countByNameAndAclModuleId(aclModuleId, name, id) > 0;
    }

    private String generateCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int) (Math.random() * 100);
    }

    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysAclMapper.countByAclModuleId(aclModuleId);
        if (count > 0) {
            List<SysAcl> aclList = sysAclMapper.getPageByAclModuleId(aclModuleId, page);
            return PageResult.<SysAcl>builder().data(aclList).total(count).build();
        }
        return PageResult.<SysAcl>builder().build();
    }

}
