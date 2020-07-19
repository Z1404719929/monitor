package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.AirPassenger;
import com.datangedu.cn.model.sysUser.AirPassengerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AirPassengerMapper {
    long countByExample(AirPassengerExample example);

    int deleteByExample(AirPassengerExample example);

    int deleteByPrimaryKey(Integer index);

    int insert(AirPassenger record);

    int insertSelective(AirPassenger record);

    List<AirPassenger> selectByExample(AirPassengerExample example);
    
    List<AirPassenger> selectall();	//查询所有

    AirPassenger selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") AirPassenger record, @Param("example") AirPassengerExample example);

    int updateByExample(@Param("record") AirPassenger record, @Param("example") AirPassengerExample example);

    int updateByPrimaryKeySelective(AirPassenger record);

    int updateByPrimaryKey(AirPassenger record);
}