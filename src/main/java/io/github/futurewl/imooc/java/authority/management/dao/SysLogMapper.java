package io.github.futurewl.imooc.java.authority.management.dao;

import io.github.futurewl.imooc.java.authority.management.model.SysLog;
import io.github.futurewl.imooc.java.authority.management.model.SysLogWithBLOBs;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);

    SysLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    int updateByPrimaryKey(SysLog record);
}