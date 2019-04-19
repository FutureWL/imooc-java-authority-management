package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.base.Preconditions;
import io.github.futurewl.imooc.java.authority.management.dao.SysDeptMapper;
import io.github.futurewl.imooc.java.authority.management.exception.ParamException;
import io.github.futurewl.imooc.java.authority.management.model.SysDept;
import io.github.futurewl.imooc.java.authority.management.param.DeptParam;
import io.github.futurewl.imooc.java.authority.management.util.BeanValidator;
import io.github.futurewl.imooc.java.authority.management.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public void update(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新部门不存在");

        SysDept after = SysDept
                .builder()
                .id(param.getId())
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();

        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator("system"); // todo
        after.setOperatorIp("127.0.0.1"); // todo
        after.setOperatorTime(new Date());

        updateWithChild(before, after);
    }

    @Transactional
    private void updateWithChild(SysDept before, SysDept after) {
        sysDeptMapper.updateByPrimaryKey(after);

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    /**
     * 判断数据是否有重复
     *
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

}
