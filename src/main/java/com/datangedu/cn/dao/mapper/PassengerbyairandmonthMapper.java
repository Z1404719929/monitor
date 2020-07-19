package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Passengerbyairandmonth;
import com.datangedu.cn.model.sysUser.PassengerbyairandmonthExample;
@Mapper
public interface PassengerbyairandmonthMapper {
    long countByExample(PassengerbyairandmonthExample example);

    int deleteByExample(PassengerbyairandmonthExample example);

    int insert(Passengerbyairandmonth record);

    int insertSelective(Passengerbyairandmonth record);

    List<Passengerbyairandmonth> selectByExample(PassengerbyairandmonthExample example);
    
    List<Passengerbyairandmonth> selectbyairandmonth(String airportname);	//按月份，机场名统计

    int updateByExampleSelective(@Param("record") Passengerbyairandmonth record, @Param("example") PassengerbyairandmonthExample example);

    int updateByExample(@Param("record") Passengerbyairandmonth record, @Param("example") PassengerbyairandmonthExample example);
}