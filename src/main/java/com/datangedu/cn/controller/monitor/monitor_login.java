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
import com.datangedu.cn.model.sysUser.Adminstrator;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.service.MonitorService;


@Controller
@RequestMapping("/monitor_login")
public class monitor_login {
	@Resource
	MonitorService monitorservice;
	
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)	//登录路径拦截
	public Map <String,Object> Login(HttpServletRequest request) {
		System.out.println("11111");
		Map<String,Object> map = new HashMap<String,Object>();
		String cellphone=request.getParameter("cellphone");
		String password=request.getParameter("password");
		String code=request.getParameter("code");

		if(!(cellphone.length()==11)) {
			map.put("msg","手机号必须为11位");
			map.put("status",0);
			return map;
		}
		
		String reg="^1\\d{10}$";
		if(!cellphone.matches(reg)) {
			map.put("msg","手机号格式不正确");
			map.put("status",4);
			return map;
		}
		
		List<MonitorUser> userInfo = monitorservice.Selectbycellphone(cellphone);
		if(userInfo.isEmpty()) {
			map.put("msg","账号不存在" );
			map.put("status",2);
			return map;
		}
		
		HttpSession session = request.getSession();
		System.out.println("验证码"+session.getAttribute("code"));
		if(!session.getAttribute("code").equals(code.toUpperCase())) {
			map.put("msg","验证码错误" );
			map.put("status",1);
			return map;
		}
		System.out.println("22222");

		if(!userInfo.get(0).getPassword().equals(MD5Util.getMD5(request.getParameter("password").getBytes()))) {
			map.put("msg","密码错误" );
			map.put("status",3);
			return map;
		}
		System.out.println(userInfo.get(0).getUserName());
		map.put("userid",userInfo.get(0).getId());
		map.put("username",userInfo.get(0).getUserName());
		map.put("msg","登陆成功");
		map.put("code", 1);
		System.out.println("33333");
		return map;
}

}
