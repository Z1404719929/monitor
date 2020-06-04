package com.datangedu.cn.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.dao.mapper.MonitorUserMapper;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.model.sysUser.MonitorUserExample;
import com.datangedu.cn.service.MonitorService;


@Service
public class MonitorServiceimpl implements MonitorService{

	@Resource
	MonitorUserMapper monitorusermapper;	//	sql语句
	
	//注册
	@Override
	public int Register(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");	//随机生成用户id
		System.out.println("id=="+uuid);
		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());	//生成注册时间
		MonitorUser mu=new MonitorUser();
		mu.setId(uuid);		//用户id
		mu.setUserName(request.getParameter("name"));	//用户名
		mu.setCellphone(request.getParameter("cellphone"));		//手机号
		mu.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));	//密码
		mu.setRegisterTime(ctime);		//注册时间
		mu.setBorn(ctime);			//出生日期默认当前
		mu.setGender(1);			//性别默认1男
		mu.setStatus(2);				//状态默认2未登录
		return monitorusermapper.insert(mu);		//插入数据库
	}

	//登录查询，通过手机号查用户信息
	@Override
	public List<MonitorUser> Selectby(String by) {
		System.out.println("查询前");
		MonitorUserExample monitoruserExample = new MonitorUserExample();
		MonitorUserExample.Criteria criteria = monitoruserExample.createCriteria();
		criteria.andCellphoneEqualTo(by);
		System.out.println("查询结束");
		return monitorusermapper.selectByExample(monitoruserExample);
	}
	
	//通过id查找返回对象
	@Override
	public MonitorUser Select(String id) {
			return monitorusermapper.selectByPrimaryKey(id);
		}

	//修改密码
	@Override
	public int updatepassword(MonitorUser mu,String cellphone) {
		MonitorUserExample muExample = new MonitorUserExample();
		MonitorUserExample.Criteria criteria = muExample.createCriteria();
		criteria.andCellphoneEqualTo(cellphone);
		return monitorusermapper.updateByExampleSelective(mu, muExample);
	}

}
