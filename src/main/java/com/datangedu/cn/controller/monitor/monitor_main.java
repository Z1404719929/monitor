package com.datangedu.cn.controller.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.service.MonitorService;
import com.datangedu.cn.controller.pub.ControllerPub;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Controller
@RequestMapping("/monitor_main")
public class monitor_main {
	
	// linux连接
	@ResponseBody
	@RequestMapping(value = "/linklinux", method = RequestMethod.POST)
	public static Map<String,Object> exec(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String host=request.getParameter("local");		//登录主机ip
		System.out.println("host"+host);
//		String user="zhaochaoqun";						//登录用户名
//		String psw="123456";							//登录密码
//		int port=22;									//端口号
		String command=request.getParameter("command");			//命令
		System.out.println("command="+command);
		ControllerPub pub=new ControllerPub();
		int exec=pub.exec(host,command);
//		System.out.println("465464"+exec);
		map.put("status", exec);
		return map;
		}
	
	// linux连接
		@ResponseBody
		@RequestMapping(value = "/linkroot", method = RequestMethod.POST)
		public static Map<String,Object> exec1(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			String host=request.getParameter("local");		//登录主机ip
			System.out.println("host"+host);
//			String user="zhaochaoqun";						//登录用户名
//			String psw="123456";							//登录密码
//			int port=22;									//端口号
			String command=request.getParameter("command");			//命令
			System.out.println("command=="+command);
			ControllerPub pub=new ControllerPub();
			int exec=pub.exec1(host,command);
//			System.out.println("465464"+exec);
			map.put("status", exec);
			return map;
			}

}