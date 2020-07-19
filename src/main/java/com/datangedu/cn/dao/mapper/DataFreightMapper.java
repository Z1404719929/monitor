package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataFreightExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DataFreightMapper {
    long countByExample(DataFreightExample example);

    int deleteByExample(DataFreightExample example);

    int deleteByPrimaryKey(Integer index);

    int insert(DataFreight record);

    int insertSelective(DataFreight record);

    List<DataFreight> selectByExample(DataFreightExample example);
    
    List<DataFreight> selectbyyear(String airportname,int start,int end);	//按年份，机场名统计
    
    List<DataFreight> selectbymonth(String airportname,int start,int end);	//按年份，机场名统计
    
    List<DataFreight> selectquery(String airportname,int monthstart,int monthend,int yearstart,int yearend,int page);	//查询分页
    
    List<DataFreight> selectqueryall(String airportname,int monthstart,int monthend,int yearstart,int yearend);	//查询

    DataFreight selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") DataFreight record, @Param("example") DataFreightExample example);

    int updateByExample(@Param("record") DataFreight record, @Param("example") DataFreightExample example);

    int updateByPrimaryKeySelective(DataFreight record);

    int updateByPrimaryKey(DataFreight record);
}