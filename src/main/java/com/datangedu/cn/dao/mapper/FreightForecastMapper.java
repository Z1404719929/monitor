package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.FreightForecast;
import com.datangedu.cn.model.sysUser.FreightForecastExample;
@Mapper
public interface FreightForecastMapper {
    long countByExample(FreightForecastExample example);

    int deleteByExample(FreightForecastExample example);

    int insert(FreightForecast record);

    int insertSelective(FreightForecast record);

    List<FreightForecast> selectByExample(FreightForecastExample example);
    
    List<FreightForecast> selectforecast(String airportname);

    int updateByExampleSelective(@Param("record") FreightForecast record, @Param("example") FreightForecastExample example);

    int updateByExample(@Param("record") FreightForecast record, @Param("example") FreightForecastExample example);
}