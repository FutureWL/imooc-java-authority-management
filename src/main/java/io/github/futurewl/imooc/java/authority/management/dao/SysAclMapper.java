package io.github.futurewl.imooc.java.authority.management.dao;

import io.github.futurewl.imooc.java.authority.management.model.SysAcl;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKeyWithBLOBs(SysAcl record);

    int updateByPrimaryKey(SysAcl record);
}