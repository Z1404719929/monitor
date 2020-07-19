package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.model.sysUser.DataPassengerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DataPassengerMapper {
    long countByExample(DataPassengerExample example);

    int deleteByExample(DataPassengerExample example);

    int deleteByPrimaryKey(Integer index);

    int insert(DataPassenger record);

    int insertSelective(DataPassenger record);

    List<DataPassenger> selectByExample(DataPassengerExample example);
    
    List<DataPassenger> selectbyyear(String airportname,int start,int end);	//按年份，机场名统计
    
    List<DataPassenger> selectbymonth(String airportname,int start,int end);	//按年份，机场名统计
    
    List<DataPassenger> selectquery(String airportname,int monthstart,int monthend,int yearstart,int yearend,int page);	//查询分页
    
    List<DataPassenger> selectqueryall(String airportname,int monthstart,int monthend,int yearstart,int yearend);	//查询

    DataPassenger selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") DataPassenger record, @Param("example") DataPassengerExample example);

    int updateByExample(@Param("record") DataPassenger record, @Param("example") DataPassengerExample example);

    int updateByPrimaryKeySelective(DataPassenger record);

    int updateByPrimaryKey(DataPassenger record);
}