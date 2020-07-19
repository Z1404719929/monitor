package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.model.sysUser.MonitorUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MonitorUserMapper {
    long countByExample(MonitorUserExample example);

    int deleteByExample(MonitorUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(MonitorUser record);

    int insertSelective(MonitorUser record);

    List<MonitorUser> selectByExampleWithBLOBs(MonitorUserExample example);

    List<MonitorUser> selectByExample(MonitorUserExample example);

    MonitorUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MonitorUser record, @Param("example") MonitorUserExample example);

    int updateByExampleWithBLOBs(@Param("record") MonitorUser record, @Param("example") MonitorUserExample example);

    int updateByExample(@Param("record") MonitorUser record, @Param("example") MonitorUserExample example);

    int updateByPrimaryKeySelective(MonitorUser record);

    int updateByPrimaryKeyWithBLOBs(MonitorUser record);

    int updateByPrimaryKey(MonitorUser record);
}