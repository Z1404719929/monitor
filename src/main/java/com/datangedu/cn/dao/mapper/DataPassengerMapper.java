package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.model.sysUser.DataPassengerExample;
@Mapper
public interface DataPassengerMapper {
    long countByExample(DataPassengerExample example);

    int deleteByExample(DataPassengerExample example);

    int insert(DataPassenger record);

    int insertSelective(DataPassenger record);

    List<DataPassenger> selectByExample(DataPassengerExample example);
    
    List<DataPassenger> selecttop();	//	top N
    
    List<DataPassenger> selectbyyear(String airportname);	//按年份
    
    List<DataPassenger> selectbymonth(String airportname);	//按月份
    
    List<DataPassenger> selectquery(String airportname,int monthstart,int monthend,int yearstart,int yearend,int page,double datalow,double datahigh);	//查询分页
    
    List<DataPassenger> selectqueryall(String airportname,int monthstart,int monthend,int yearstart,int yearend,double datalow,double datahigh);	//查询

    List<DataPassenger> selectmap(String year,String month);	//地图搜索年月的值
    
//    DataPassenger selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") DataPassenger record, @Param("example") DataPassengerExample example);

    int updateByExample(@Param("record") DataPassenger record, @Param("example") DataPassengerExample example);
}