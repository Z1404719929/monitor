package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.Adminstrator;
import com.datangedu.cn.model.sysUser.AdminstratorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminstratorMapper {
    long countByExample(AdminstratorExample example);

    int deleteByExample(AdminstratorExample example);

    int deleteByPrimaryKey(String id);

    int insert(Adminstrator record);

    int insertSelective(Adminstrator record);

    List<Adminstrator> selectByExample(AdminstratorExample example);

    Adminstrator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Adminstrator record, @Param("example") AdminstratorExample example);

    int updateByExample(@Param("record") Adminstrator record, @Param("example") AdminstratorExample example);

    int updateByPrimaryKeySelective(Adminstrator record);

    int updateByPrimaryKey(Adminstrator record);
}