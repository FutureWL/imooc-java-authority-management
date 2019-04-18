package io.github.futurewl.imooc.java.authority.management.dao;

import io.github.futurewl.imooc.java.authority.management.model.SysDept;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKeyWithBLOBs(SysDept record);

    int updateByPrimaryKey(SysDept record);
}