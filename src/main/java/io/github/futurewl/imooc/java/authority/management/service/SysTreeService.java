package io.github.futurewl.imooc.java.authority.management.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import io.github.futurewl.imooc.java.authority.management.dao.SysAclMapper;
import io.github.futurewl.imooc.java.authority.management.dao.SysAclModuleMapper;
import io.github.futurewl.imooc.java.authority.management.dao.SysDeptMapper;
import io.github.futurewl.imooc.java.authority.management.dto.AclDto;
import io.github.futurewl.imooc.java.authority.management.dto.AclModuleLevelDto;
import io.github.futurewl.imooc.java.authority.management.dto.DeptLevelDto;
import io.github.futurewl.imooc.java.authority.management.model.SysAcl;
import io.github.futurewl.imooc.java.authority.management.model.SysAclModule;
import io.github.futurewl.imooc.java.authority.management.model.SysDept;
import io.github.futurewl.imooc.java.authority.management.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 功能描述：系统树结构服务实现
 *
 * @author weilai create by 2019-04-19:14:30
 * @version 1.0
 */
@Service
public class SysTreeService {

    @Resource
    private SysCoreService sysCoreService;

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    @Resource
    private SysAclMapper sysAclMapper;

    @Resource
    private SysLogService sysLogService;

    private Comparator<DeptLevelDto> deptSeqComparator = Comparator.comparingInt(SysDept::getSeq);
    private Comparator<AclModuleLevelDto> aclModuleLevelDtoComparator = Comparator.comparingInt(SysAclModule::getSeq);
    private Comparator<AclDto> aclSeqComparator = Comparator.comparingInt(SysAcl::getSeq);

    public List<AclModuleLevelDto> userAclTree(int userId) {
        List<SysAcl> userAclList = sysCoreService.getUserAclList(userId);
        List<AclDto> aclDtoList = Lists.newArrayList();
        for (SysAcl acl : userAclList) {
            AclDto dto = AclDto.adapt(acl);
            dto.setHasAcl(true);
            dto.setChecked(true);
            aclDtoList.add(dto);
        }
        return aclListToTree(aclDtoList);
    }

    /**
     * 获取所有的权限模块树结构
     *
     * @return
     */
    public List<AclModuleLevelDto> aclModuleTree() {
        List<SysAclModule> aclModuleList = sysAclModuleMapper.getAllAclModule();
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        // 将所有的部门对象转换为部门层树
        for (SysAclModule aclModule : aclModuleList) {
            AclModuleLevelDto dto = AclModuleLevelDto.aAclModule(aclModule);
            dtoList.add(dto);
        }
        return aclModuleLevelDtoTree(dtoList);
    }

    /**
     * 获取所有的权限模块层级树
     *
     * @param aclModuleLevelDtoList
     * @return
     */
    private List<AclModuleLevelDto> aclModuleLevelDtoTree(List<AclModuleLevelDto> aclModuleLevelDtoList) {
        // 判断是否是最后顶级部门
        if (CollectionUtils.isEmpty(aclModuleLevelDtoList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1,dept2,...]
        Multimap<String, AclModuleLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        List<AclModuleLevelDto> rootList = Lists.newArrayList();
        // 对应
        for (AclModuleLevelDto aclModuleLevelDto : aclModuleLevelDtoList) {
            levelDtoMultimap.put(aclModuleLevelDto.getLevel(), aclModuleLevelDto);
            // 如果是顶级目部门，则添加到顶层部门列表
            if (LevelUtil.ROOT.equals(aclModuleLevelDto.getLevel())) {
                rootList.add(aclModuleLevelDto);
            }
        }
        // 按照 sql 从小到大排序
        rootList.sort(Comparator.comparingInt(SysAclModule::getSeq));
        // 递归生成树
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelDtoMultimap);
        return rootList;
    }

    /**
     * level:0,0,all 0->0.1,0.2
     * level:0.1
     * level:0.2
     *
     * @param aclModuleLevelDtoList 当前层级权限模块
     * @param level
     * @param levelDtoMultimap
     */
    private void transformAclModuleTree(List<AclModuleLevelDto> aclModuleLevelDtoList, String level, Multimap<String, AclModuleLevelDto> levelDtoMultimap) {
        // 遍历该层的每个元素
        for (AclModuleLevelDto deaclModuleLevelDtotLevelDto : aclModuleLevelDtoList) {
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deaclModuleLevelDtotLevelDto.getId());
            // 处理下一层
            List<AclModuleLevelDto> tempAclModuleList = (List<AclModuleLevelDto>) levelDtoMultimap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempAclModuleList)) {
                // 排序
                tempAclModuleList.sort(aclModuleLevelDtoComparator);
                // 设置下一层部门
                deaclModuleLevelDtotLevelDto.setAclModuleList(tempAclModuleList);
                // 进入到下一层处理
                transformAclModuleTree(tempAclModuleList, nextLevel, levelDtoMultimap);
            }
        }
    }

    /**
     * 获取所有的部门树结构
     *
     * @return
     */
    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        // 将所有的部门对象转换为部门层树
        for (SysDept dept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptLevelDtoTree(dtoList);
    }

    /**
     * 获取所有的部门层级树
     *
     * @param deptLevelDtoList
     * @return
     */
    private List<DeptLevelDto> deptLevelDtoTree(List<DeptLevelDto> deptLevelDtoList) {
        // 判断是否是最后顶级部门
        if (CollectionUtils.isEmpty(deptLevelDtoList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1,dept2,...]
        Multimap<String, DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();
        // 对应
        for (DeptLevelDto deptLevelDto : deptLevelDtoList) {
            levelDtoMultimap.put(deptLevelDto.getLevel(), deptLevelDto);
            // 如果是顶级目部门，则添加到顶层部门列表
            if (LevelUtil.ROOT.equals(deptLevelDto.getLevel())) {
                rootList.add(deptLevelDto);
            }
        }
        // 按照 sql 从小到大排序
        rootList.sort(Comparator.comparingInt(SysDept::getSeq));
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDtoMultimap);
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
    private void transformDeptTree(List<DeptLevelDto> deptLevelDtoList, String level, Multimap<String, DeptLevelDto> levelDtoMultimap) {
        // 遍历该层的每个元素
        for (DeptLevelDto deptLevelDto : deptLevelDtoList) {
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDtoMultimap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                tempDeptList.sort(deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDtoMultimap);
            }
        }
    }

    public List<AclModuleLevelDto> roleTree(int roleId) {
        // 1、当前用户已分配的权限点
        List<SysAcl> userAclList = sysCoreService.getCurrentUserAclList();
        // 2、当前角色分配的权限点
        List<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleId);
        // 3、当前系统所有权限点
        List<AclDto> aclDtoList = Lists.newArrayList();

        Set<Integer> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Integer> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());

        List<SysAcl> allAclList = sysAclMapper.getAll();
        for (SysAcl acl : allAclList) {
            AclDto dto = AclDto.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
        }
        return aclListToTree(aclDtoList);
    }

    private List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree();

        Multimap<Integer, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDto acl : aclDtoList) {
            if (acl.getStatus() == 1) {
                moduleIdAclMap.put(acl.getAclModuleId(), acl);
            }
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }

    private void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<Integer, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return;
        }
        for (AclModuleLevelDto dto : aclModuleLevelList) {
            List<AclDto> aclDtoList = (List<AclDto>) moduleIdAclMap.get(dto.getId());
            if (CollectionUtils.isNotEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getAclModuleList(), moduleIdAclMap);
        }
    }


}
