package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.RegisterUser;


public interface RegisterUserService {
	public List<RegisterUser> Selectbycellphone(String by);		//输入手机号查找
	public RegisterUser Select(String id);		//通过用户id查找
	public int Register(HttpServletRequest request);	//注册
	public int updatepassword(RegisterUser mu,String cellphone);	//修改密码
	public int changeUserinfo(RegisterUser monitoruser,String id);	//修改用户信息，包括头像上传
	
}
