package com.datangedu.cn.controller.register;

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
import com.datangedu.cn.model.sysUser.RegisterUser;
import com.datangedu.cn.service.RegisterUserService;


@Controller
@RequestMapping("/register_register")
public class register_register {
	@Resource
	RegisterUserService ruservice;
	
	@ResponseBody
	@RequestMapping(value = "/register",method = RequestMethod.POST)	//登录路径拦截
	public Map <String,Object> Login(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String name=request.getParameter("name");
		String cellphone=request.getParameter("cellphone");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String code=request.getParameter("code");

		if(request.getParameter("district").isEmpty()) {
			map.put("msg","必须选择地区");
			map.put("status",4);
			return map;
		}

		if(!(cellphone.length()==11)) {
			map.put("msg","手机号必须为11位");
			map.put("status",0);
			return map;
		}
		
		String reg="^1\\d{10}$";
		if(!cellphone.matches(reg)) {
			map.put("msg","手机号格式不正确");
			map.put("status",5);
			return map;
		}
		
		if(!password.equals(password1)) {
			map.put("msg","两次密码不一致" );
			map.put("status",1);
			return map;
		}
		HttpSession session = request.getSession();
		System.out.println("验证码"+session.getAttribute("code"));
		if(!session.getAttribute("code").equals(code.toUpperCase())) {
			map.put("msg","验证码错误" );
			map.put("status",2);
			return map;
		}
		//查找手机号是否存在
		List<RegisterUser> userInfo = ruservice.Selectbycellphone(cellphone);
		if(!userInfo.isEmpty()) {
			map.put("msg","账号已存在" );
			map.put("status",3);
			return map;
		}
		
		//注册
		int a = ruservice.Register(request);
		System.out.println("注册状态"+a);
		map.put("msg","注册成功");
		map.put("code",a);
		return map;
}
	
	
	

}
