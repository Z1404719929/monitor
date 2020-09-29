package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.PassengerForecast;
import com.datangedu.cn.model.sysUser.PassengerForecastExample;
@Mapper
public interface PassengerForecastMapper {
    long countByExample(PassengerForecastExample example);

    int deleteByExample(PassengerForecastExample example);

    int insert(PassengerForecast record);

    int insertSelective(PassengerForecast record);

    List<PassengerForecast> selectByExample(PassengerForecastExample example);
    
    List<PassengerForecast> selectforecast(String airportname);

    int updateByExampleSelective(@Param("record") PassengerForecast record, @Param("example") PassengerForecastExample example);

    int updateByExample(@Param("record") PassengerForecast record, @Param("example") PassengerForecastExample example);
}