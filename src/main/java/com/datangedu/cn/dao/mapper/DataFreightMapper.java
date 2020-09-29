package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataFreightExample;
@Mapper
public interface DataFreightMapper {
    long countByExample(DataFreightExample example);

    int deleteByExample(DataFreightExample example);

    int insert(DataFreight record);

    int insertSelective(DataFreight record);

    List<DataFreight> selectByExample(DataFreightExample example);
    
    List<DataFreight> selecttop();	//	top N
    
    List<DataFreight> selectbyyear(String airportname);		//按年份
    
    List<DataFreight> selectbymonth(String airportname);	//按月份
    
    List<DataFreight> selectquery(String airportname,int monthstart,int monthend,int yearstart,int yearend,int page,double datalow,double datahigh);	//查询分页
    
    List<DataFreight> selectqueryall(String airportname,int monthstart,int monthend,int yearstart,int yearend,double datalow,double datahigh);	//查询

    List<DataFreight> selectmap(String year,String month);	//地图搜索年月的值
    
//    DataFreight selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") DataFreight record, @Param("example") DataFreightExample example);

    int updateByExample(@Param("record") DataFreight record, @Param("example") DataFreightExample example);
}