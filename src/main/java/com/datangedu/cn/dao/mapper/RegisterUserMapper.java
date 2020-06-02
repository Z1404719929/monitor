package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.RegisterUser;
import com.datangedu.cn.model.sysUser.RegisterUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegisterUserMapper {
    long countByExample(RegisterUserExample example);

    int deleteByExample(RegisterUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(RegisterUser record);

    int insertSelective(RegisterUser record);

    List<RegisterUser> selectByExample(RegisterUserExample example);

    RegisterUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RegisterUser record, @Param("example") RegisterUserExample example);

    int updateByExample(@Param("record") RegisterUser record, @Param("example") RegisterUserExample example);

    int updateByPrimaryKeySelective(RegisterUser record);

    int updateByPrimaryKey(RegisterUser record);
}