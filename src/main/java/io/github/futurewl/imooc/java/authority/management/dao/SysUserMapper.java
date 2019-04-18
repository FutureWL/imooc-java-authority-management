package io.github.futurewl.imooc.java.authority.management.dao;

import io.github.futurewl.imooc.java.authority.management.model.SysUser;
import io.github.futurewl.imooc.java.authority.management.model.SysUserWithBLOBs;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserWithBLOBs record);

    int insertSelective(SysUserWithBLOBs record);

    SysUserWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysUserWithBLOBs record);

    int updateByPrimaryKey(SysUser record);
}