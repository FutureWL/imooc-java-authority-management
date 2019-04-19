package io.github.futurewl.imooc.java.authority.management.service;

import io.github.futurewl.imooc.java.authority.management.dao.SysDeptMapper;
import io.github.futurewl.imooc.java.authority.management.exception.ParamException;
import io.github.futurewl.imooc.java.authority.management.model.SysDept;
import io.github.futurewl.imooc.java.authority.management.param.DeptParam;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import io.github.futurewl.imooc.java.authority.management.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-19:14:18
 * @version 1.0
 */
@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同层级下存在相同名称的部门");
        }
        SysDept dept = SysDept
                .builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        dept.setOperator("system"); // todo
        dept.setOperatorIp("127.0.0.1"); // todo
        dept.setOperatorTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        // TODO: 2019-04-19
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

}
