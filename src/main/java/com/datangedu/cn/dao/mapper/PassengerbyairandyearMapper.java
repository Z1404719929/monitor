package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Passengerbyairandyear;
import com.datangedu.cn.model.sysUser.PassengerbyairandyearExample;
@Mapper
public interface PassengerbyairandyearMapper {
    long countByExample(PassengerbyairandyearExample example);

    int deleteByExample(PassengerbyairandyearExample example);

    int insert(Passengerbyairandyear record);

    int insertSelective(Passengerbyairandyear record);

    List<Passengerbyairandyear> selectByExample(PassengerbyairandyearExample example);
    
    List<Passengerbyairandyear> selectbyairandyear(String airportname);	//按年份，机场名统计

    int updateByExampleSelective(@Param("record") Passengerbyairandyear record, @Param("example") PassengerbyairandyearExample example);

    int updateByExample(@Param("record") Passengerbyairandyear record, @Param("example") PassengerbyairandyearExample example);
}