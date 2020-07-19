package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Passengerbymonth;
import com.datangedu.cn.model.sysUser.PassengerbymonthExample;
@Mapper
public interface PassengerbymonthMapper {
    long countByExample(PassengerbymonthExample example);

    int deleteByExample(PassengerbymonthExample example);

    int insert(Passengerbymonth record);

    int insertSelective(Passengerbymonth record);

    List<Passengerbymonth> selectByExample(PassengerbymonthExample example);

    int updateByExampleSelective(@Param("record") Passengerbymonth record, @Param("example") PassengerbymonthExample example);

    int updateByExample(@Param("record") Passengerbymonth record, @Param("example") PassengerbymonthExample example);
}