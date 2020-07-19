package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.Freightbyyear;
import com.datangedu.cn.model.sysUser.FreightbyyearExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FreightbyyearMapper {
    long countByExample(FreightbyyearExample example);

    int deleteByExample(FreightbyyearExample example);

    int insert(Freightbyyear record);

    int insertSelective(Freightbyyear record);

    List<Freightbyyear> selectByExample(FreightbyyearExample example);

    int updateByExampleSelective(@Param("record") Freightbyyear record, @Param("example") FreightbyyearExample example);

    int updateByExample(@Param("record") Freightbyyear record, @Param("example") FreightbyyearExample example);
}