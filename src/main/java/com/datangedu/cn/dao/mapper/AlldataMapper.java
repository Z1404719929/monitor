package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.Alldata;
import com.datangedu.cn.model.sysUser.AlldataExample;
//import com.datangedu.cn.model.sysUser.DataFreightAll;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AlldataMapper {
    long countByExample(AlldataExample example);

    int deleteByExample(AlldataExample example);

    int insert(Alldata record);

    int insertSelective(Alldata record);

    List<Alldata> selectByExample(AlldataExample example);
    
    List<Alldata> selectall(String year,String month);	//地图搜索年月的值

    int updateByExampleSelective(@Param("record") Alldata record, @Param("example") AlldataExample example);

    int updateByExample(@Param("record") Alldata record, @Param("example") AlldataExample example);
}