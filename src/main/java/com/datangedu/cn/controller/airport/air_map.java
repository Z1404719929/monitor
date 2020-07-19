package com.datangedu.cn.controller.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.AirFreight;
import com.datangedu.cn.model.sysUser.AirPassenger;
import com.datangedu.cn.service.AirMapService;


@Controller
@RequestMapping("/airmap")
public class air_map {
	
	//货运机场分布
	@Resource
	AirMapService afservice;
	
	@ResponseBody
	@RequestMapping(value = "/airfreight",method = RequestMethod.POST)	//货运机场信息
	public Map <String,Object> Airfreight() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<AirFreight> airfreight = afservice.SelectallFreight();
		map.put("airdata", airfreight);
		System.out.println(airfreight.get(0).getAirportname());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/airpassenger",method = RequestMethod.POST)	//客运机场信息
	public Map <String,Object> Airpassenger() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<AirPassenger> airpassenger = afservice.SelectallPassenger();
		map.put("airdata", airpassenger);
		System.out.println("11111");
		return map;
	}


}