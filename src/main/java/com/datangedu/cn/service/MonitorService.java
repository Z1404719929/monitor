package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.MonitorUser;


public interface MonitorService {
	public List<MonitorUser> Selectbycellphone(String by);		//输入手机号查找
	public MonitorUser Select(String id);		//通过用户id查找
	public int Register(HttpServletRequest request);	//注册
	public int updatepassword(MonitorUser mu,String cellphone);	//修改密码
	public int changeUserinfo(MonitorUser monitoruser,String id);	//修改用户信息，包括头像上传
	
}
