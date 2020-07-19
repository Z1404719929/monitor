package com.datangedu.cn.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.dao.mapper.RegisterUserMapper;
import com.datangedu.cn.model.sysUser.RegisterUser;
import com.datangedu.cn.model.sysUser.RegisterUserExample;
import com.datangedu.cn.service.RegisterUserService;


@Service
public class RegisterUserServiceimpl implements RegisterUserService{

	@Resource
	RegisterUserMapper registerusermapper;	//	sql语句
	
	//注册
	@Override
	public int Register(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");	//随机生成用户id
		System.out.println("id=="+uuid);
		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());	//生成注册时间
		RegisterUser mu=new RegisterUser();
		mu.setId(uuid);		//用户id
		mu.setUserName(request.getParameter("name"));	//用户名
		mu.setCellphone(request.getParameter("cellphone"));		//手机号
		mu.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));	//密码
		mu.setRegionId(request.getParameter("district")); 			//区id
		mu.setRegisterTime(ctime);		//注册时间
		mu.setBorn(ctime);			//出生日期默认当前
		mu.setGender(1);			//性别默认1男
		mu.setStatus(2);				//状态默认2未登录
		return registerusermapper.insert(mu);		//插入数据库
	}

	//登录查询，通过手机号查用户信息
	@Override
	public List<RegisterUser> Selectbycellphone(String by) {
		System.out.println("查询前");
		RegisterUserExample registeruserExample = new RegisterUserExample();
		RegisterUserExample.Criteria criteria = registeruserExample.createCriteria();
		criteria.andCellphoneEqualTo(by);
		System.out.println("查询结束");
		return registerusermapper.selectByExample(registeruserExample);
	}
	
	//通过id查找返回对象
	@Override
	public RegisterUser Select(String id) {
			return registerusermapper.selectByPrimaryKey(id);
		}

	//修改密码
	@Override
	public int updatepassword(RegisterUser ru,String cellphone) {
		RegisterUserExample ruExample = new RegisterUserExample();
		RegisterUserExample.Criteria criteria = ruExample.createCriteria();
		criteria.andCellphoneEqualTo(cellphone);
		return registerusermapper.updateByExampleSelective(ru, ruExample);
	}
	
	//用户信息上传
	@Override
	public int changeUserinfo(RegisterUser registeruser,String id){
		RegisterUserExample ruExample = new RegisterUserExample();
		RegisterUserExample.Criteria criteria = ruExample.createCriteria();
		criteria.andIdEqualTo(id);
		return registerusermapper.updateByExampleSelective(registeruser, ruExample);
		}
	
}
