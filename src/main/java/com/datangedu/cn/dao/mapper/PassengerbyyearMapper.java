package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.Passengerbyyear;
import com.datangedu.cn.model.sysUser.PassengerbyyearExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PassengerbyyearMapper {
    long countByExample(PassengerbyyearExample example);

    int deleteByExample(PassengerbyyearExample example);

    int insert(Passengerbyyear record);

    int insertSelective(Passengerbyyear record);

    List<Passengerbyyear> selectByExample(PassengerbyyearExample example);

    int updateByExampleSelective(@Param("record") Passengerbyyear record, @Param("example") PassengerbyyearExample example);

    int updateByExample(@Param("record") Passengerbyyear record, @Param("example") PassengerbyyearExample example);
}