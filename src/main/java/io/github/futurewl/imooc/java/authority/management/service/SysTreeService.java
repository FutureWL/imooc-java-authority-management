package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import io.github.futurewl.imooc.java.authority.management.dao.SysDeptMapper;
import io.github.futurewl.imooc.java.authority.management.dto.DeptLevelDto;
import io.github.futurewl.imooc.java.authority.management.model.SysDept;
import io.github.futurewl.imooc.java.authority.management.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-19:14:30
 * @version 1.0
 */
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public Comparator<DeptLevelDto> deptSeqComparator = Comparator.comparingInt(SysDept::getSeq);

    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();

        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptLevelDtoTree(dtoList);
    }

    public List<DeptLevelDto> deptLevelDtoTree(List<DeptLevelDto> deptLevelDtoList) {
        if (CollectionUtils.isEmpty(deptLevelDtoList)) {
            return Lists.newArrayList();
        }

        // level -> [dept1,dept2,...]
        Multimap<String, DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for (DeptLevelDto deptLevelDto : deptLevelDtoList) {
            levelDtoMultimap.put(deptLevelDto.getLevel(), deptLevelDto);
            if (LevelUtil.ROOT.equals(deptLevelDto.getLevel())) {
                rootList.add(deptLevelDto);
            }
        }

        // 按照 sql 从小到大排序
        rootList.sort(Comparator.comparingInt(SysDept::getSeq));
        // 递归生成树
        transformDeptTree(deptLevelDtoList, LevelUtil.ROOT, levelDtoMultimap);
        return rootList;
    }

    /**
     * level:0,0,all 0->0.1,0.2
     * level:0.1
     * level:0.2
     *
     * @param deptLevelDtoList 当前层级部门
     * @param level
     * @param levelDtoMultimap
     */
    public void transformDeptTree(
            List<DeptLevelDto> deptLevelDtoList,
            String level,
            Multimap<String, DeptLevelDto> levelDtoMultimap) {
        for (DeptLevelDto deptLevelDto : deptLevelDtoList) {
            // 遍历该层的每个元素
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDtoMultimap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                tempDeptList.sort(deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDeptLevelDtoList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDtoMultimap);
            }
        }
    }


}
