package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Freightbyairandmonth;
import com.datangedu.cn.model.sysUser.FreightbyairandmonthExample;
@Mapper
public interface FreightbyairandmonthMapper {
    long countByExample(FreightbyairandmonthExample example);

    int deleteByExample(FreightbyairandmonthExample example);

    int insert(Freightbyairandmonth record);

    int insertSelective(Freightbyairandmonth record);

    List<Freightbyairandmonth> selectByExample(FreightbyairandmonthExample example);
    
    List<Freightbyairandmonth> selectbyairandmonth(String airportname);	//按月份，机场名统计

    int updateByExampleSelective(@Param("record") Freightbyairandmonth record, @Param("example") FreightbyairandmonthExample example);

    int updateByExample(@Param("record") Freightbyairandmonth record, @Param("example") FreightbyairandmonthExample example);
}