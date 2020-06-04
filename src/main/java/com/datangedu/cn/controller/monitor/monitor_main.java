package com.datangedu.cn.controller.monitor;

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


@Controller
@RequestMapping("/monitor_main")
public class monitor_main {
	@Resource
	MonitorService monitorservice;
	
	@ResponseBody	
	@RequestMapping(value="/headImg", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> headImg(String id) throws Exception{
		System.out.println("tp");
		byte[] imageContent ;
		// 根据id获取当前用户的信息
		MonitorUser user = monitorservice.Select(id);
				        
		imageContent = user.getHeadImg();
		System.out.println("图片==="+user.getHeadImg());
				        
		// 设置http头部信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		// 返回响应实体
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}

}
