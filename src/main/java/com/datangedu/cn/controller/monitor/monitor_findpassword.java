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
@RequestMapping("/monitor_upwd")
public class monitor_findpassword {
	@Resource
	MonitorService monitorservice;
	
	@ResponseBody
	@RequestMapping(value = "/upwd",method = RequestMethod.POST)	//找回密码路径拦截
	public Map <String,Object> Login(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String name=request.getParameter("name");
		String cellphone=request.getParameter("cellphone");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String code=request.getParameter("code");
		if(name.isEmpty()) {
			map.put("msg","输入用户名" );
			return map;
		}
		if(cellphone.isEmpty()) {
			map.put("msg","输入手机号" );
			return map;
		}
		if(password.isEmpty()) {
			map.put("msg","输入密码" );
			return map;
		}
		if(password1.isEmpty()) {
			map.put("msg","确认密码" );
			return map;
		}
		if(code.isEmpty()) {
			map.put("msg","输入验证码" );
			return map;
		}
		if(!(cellphone.length()==11)) {
			map.put("msg","手机号必须为11位");
			return map;
		}
		//查找手机号是否存在
		List<MonitorUser> userInfo = monitorservice.Selectbycellphone(cellphone);
		if(userInfo.isEmpty()) {
			map.put("msg","手机号不存在" );
			return map;
		}
		if(!userInfo.get(0).getUserName().equals(name)) {
			map.put("msg","用户名不匹配" );
			return map;
		}
		if(!password.equals(password1)) {
			map.put("msg","两次密码不一致" );
			return map;
		}
		if(userInfo.get(0).getPassword().equals(MD5Util.getMD5(request.getParameter("password").getBytes()))) {
			map.put("msg","重置密码与原密码相同" );
			return map;
		}
		HttpSession session = request.getSession();
		System.out.println("验证码"+session.getAttribute("code"));
		if(!session.getAttribute("code").equals(code.toUpperCase())) {
			map.put("msg","验证码错误" );
			return map;
		}
		//修改密码
		MonitorUser mu=new MonitorUser();
		mu.setPassword(MD5Util.getMD5(password.getBytes()));
		int a = monitorservice.updatepassword(mu,cellphone);
		System.out.println("重置状态"+a);
		map.put("msg","重置成功");
		map.put("code",a);
		return map;
}

}
