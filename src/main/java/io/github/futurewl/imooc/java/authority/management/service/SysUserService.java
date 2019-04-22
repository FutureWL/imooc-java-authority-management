package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.base.Preconditions;
import io.github.futurewl.imooc.java.authority.management.dao.SysUserMapper;
import io.github.futurewl.imooc.java.authority.management.exception.ParamException;
import io.github.futurewl.imooc.java.authority.management.model.SysUser;
import io.github.futurewl.imooc.java.authority.management.param.UserParam;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import io.github.futurewl.imooc.java.authority.management.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-20:14:29
 * @version 1.0
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam userParam) {
        BeanValidator.check(userParam);
        if (checkTelephoneExist(userParam.getTelephone(), userParam.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(userParam.getMail(), userParam.getId())) {
            throw new ParamException("邮箱已被占用");
        }
//        String password = PasswordUtil.randomPassword();
        String password = "123456";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser
                .builder()
                .username(userParam.getUsername())
                .password(encryptedPassword)
                .telephone(userParam.getTelephone())
                .mail(userParam.getMail())
                .deptId(userParam.getDeptId())
                .status(userParam.getStatus())
                .remark(userParam.getRemark())
                .build();
        user.setOperator("system");
        user.setOperatorIp("127.0.0.1");
        user.setOperatorTime(new Date());

        // todo 发送 Email
        sysUserMapper.insertSelective(user);

    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder()
                .id(param.getId())
                .username(param.getUsername())
                .password(before.getPassword())
                .telephone(param.getTelephone())
                .mail(param.getMail())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    private boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

}
