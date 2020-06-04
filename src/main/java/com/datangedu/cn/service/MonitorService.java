package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.MonitorUser;


public interface MonitorService {
	public List<MonitorUser> Selectby(String by);		//条件查找
	public MonitorUser Select(String id);		//条件查找
	public int Register(HttpServletRequest request);	//注册
	public int updatepassword(MonitorUser mu,String cellphone);
	
}
