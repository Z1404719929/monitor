package com.datangedu.cn.controller.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.service.MonitorService;


@Controller
@RequestMapping("/monitor_register")
public class monitor_register {
	@Resource
	MonitorService monitorservice;
	
	@ResponseBody
	@RequestMapping(value = "/register",method = RequestMethod.POST)	//登录路径拦截
	public Map <String,Object> Login(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String name=request.getParameter("name");
		String cellphone=request.getParameter("cellphone");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String code=request.getParameter("code");
//		if(name.isEmpty()) {
//			map.put("msg","输入用户名" );
//			return map;
//		}
//		if(cellphone.isEmpty()) {
//			map.put("msg","输入手机号" );
//			return map;
//		}
//		if(password.isEmpty()) {
//			map.put("msg","输入密码" );
//			return map;
//		}
//		if(password1.isEmpty()) {
//			map.put("msg","确认密码" );
//			return map;
//		}
//		if(code.isEmpty()) {
//			map.put("msg","输入验证码" );
//			return map;
//		}
		if(!(cellphone.length()==11)) {
			map.put("msg","手机号必须为11位");
			return map;
		}
		if(!password.equals(password1)) {
			map.put("msg","密码不一致" );
			return map;
		}
		HttpSession session = request.getSession();
		System.out.println("验证码"+session.getAttribute("code"));
		if(!session.getAttribute("code").equals(code.toUpperCase())) {
			map.put("msg","验证码错误" );
			return map;
		}
		//查找手机号是否存在
		List<MonitorUser> userInfo = monitorservice.Selectbycellphone(cellphone);
		if(!userInfo.isEmpty()) {
			map.put("msg","账号已存在" );
			return map;
		}
		
		//注册
		int a = monitorservice.Register(request);
		System.out.println("注册状态"+a);
		map.put("msg","注册成功");
		map.put("code",a);
		return map;
}

}
