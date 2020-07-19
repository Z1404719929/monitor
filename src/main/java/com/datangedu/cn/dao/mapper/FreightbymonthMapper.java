package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Freightbymonth;
import com.datangedu.cn.model.sysUser.FreightbymonthExample;
@Mapper
public interface FreightbymonthMapper {
    long countByExample(FreightbymonthExample example);

    int deleteByExample(FreightbymonthExample example);

    int insert(Freightbymonth record);

    int insertSelective(Freightbymonth record);

    List<Freightbymonth> selectByExample(FreightbymonthExample example);

    int updateByExampleSelective(@Param("record") Freightbymonth record, @Param("example") FreightbymonthExample example);

    int updateByExample(@Param("record") Freightbymonth record, @Param("example") FreightbymonthExample example);
}