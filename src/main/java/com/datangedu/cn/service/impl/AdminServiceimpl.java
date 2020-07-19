package com.datangedu.cn.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.dao.mapper.AdminstratorMapper;
import com.datangedu.cn.model.sysUser.Adminstrator;
import com.datangedu.cn.model.sysUser.AdminstratorExample;
import com.datangedu.cn.service.AdminService;


@Service
public class AdminServiceimpl implements AdminService{

	@Resource
	AdminstratorMapper adminmapper;	//	sql语句
	
	//注册
	@Override
	public int Register(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");	//随机生成用户id
		System.out.println("id=="+uuid);
		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());	//生成注册时间
		Adminstrator mu=new Adminstrator();
		mu.setId(uuid);		//用户id
		mu.setUserName(request.getParameter("name"));	//用户名
		mu.setCellphone(request.getParameter("cellphone"));		//手机号
		mu.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));	//密码
		mu.setRegionId(request.getParameter("district")); 			//区id
		mu.setRegisterTime(ctime);		//注册时间
		mu.setBorn(ctime);			//出生日期默认当前
		mu.setGender(1);			//性别默认1男
		mu.setStatus(2);				//状态默认2未登录
		return adminmapper.insert(mu);		//插入数据库
	}

	//登录查询，通过手机号查用户信息
	@Override
	public List<Adminstrator> Selectbycellphone(String by) {
		System.out.println("查询前");
		AdminstratorExample monitoruserExample = new AdminstratorExample();
		AdminstratorExample.Criteria criteria = monitoruserExample.createCriteria();
		criteria.andCellphoneEqualTo(by);
		System.out.println("查询结束");
		return adminmapper.selectByExample(monitoruserExample);
	}
	
	//通过id查找返回对象
	@Override
	public Adminstrator Select(String id) {
			return adminmapper.selectByPrimaryKey(id);
		}

	//修改密码
	@Override
	public int updatepassword(Adminstrator mu,String cellphone) {
		AdminstratorExample muExample = new AdminstratorExample();
		AdminstratorExample.Criteria criteria = muExample.createCriteria();
		criteria.andCellphoneEqualTo(cellphone);
		return adminmapper.updateByExampleSelective(mu, muExample);
	}
	
	//用户信息上传
	@Override
	public int changeUserinfo(Adminstrator monitoruser,String id){
		AdminstratorExample muExample = new AdminstratorExample();
		AdminstratorExample.Criteria criteria = muExample.createCriteria();
		criteria.andIdEqualTo(id);
		return adminmapper.updateByExampleSelective(monitoruser, muExample);
		}
	
}
