package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Freightbyairandyear;
import com.datangedu.cn.model.sysUser.FreightbyairandyearExample;
import com.datangedu.cn.model.sysUser.Passengerbyairandyear;
@Mapper
public interface FreightbyairandyearMapper {
    long countByExample(FreightbyairandyearExample example);

    int deleteByExample(FreightbyairandyearExample example);

    int insert(Freightbyairandyear record);

    int insertSelective(Freightbyairandyear record);

    List<Freightbyairandyear> selectByExample(FreightbyairandyearExample example);
    
    List<Freightbyairandyear> selectbyairandyear(String airportname);	//按年份，机场名统计

    int updateByExampleSelective(@Param("record") Freightbyairandyear record, @Param("example") FreightbyairandyearExample example);

    int updateByExample(@Param("record") Freightbyairandyear record, @Param("example") FreightbyairandyearExample example);
}