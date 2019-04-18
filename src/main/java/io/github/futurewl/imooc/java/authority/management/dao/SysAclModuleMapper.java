package io.github.futurewl.imooc.java.authority.management.dao;

import io.github.futurewl.imooc.java.authority.management.model.SysAclModule;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKeyWithBLOBs(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);
}