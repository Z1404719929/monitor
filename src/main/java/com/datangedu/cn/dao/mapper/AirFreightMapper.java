package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.AirFreight;
import com.datangedu.cn.model.sysUser.AirFreightExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AirFreightMapper {
    long countByExample(AirFreightExample example);

    int deleteByExample(AirFreightExample example);

    int deleteByPrimaryKey(Integer index);

    int insert(AirFreight record);

    int insertSelective(AirFreight record);

    List<AirFreight> selectByExample(AirFreightExample example);
    
    List<AirFreight> selectall();	//查询所有

    AirFreight selectByPrimaryKey(Integer index);

    int updateByExampleSelective(@Param("record") AirFreight record, @Param("example") AirFreightExample example);

    int updateByExample(@Param("record") AirFreight record, @Param("example") AirFreightExample example);

    int updateByPrimaryKeySelective(AirFreight record);

    int updateByPrimaryKey(AirFreight record);
}