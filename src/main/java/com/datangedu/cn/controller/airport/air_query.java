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

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.AirQueryService;


@Controller
@RequestMapping("/airquery")
public class air_query {
	
	//货运机场分布
	@Resource
	AirQueryService aqservice;
	
	@ResponseBody
	@RequestMapping(value = "/airfreight",method = RequestMethod.POST)	//货运机场信息
	public Map <String,Object> Airfreight(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<DataFreight> airfreight = aqservice.FreigthSelectQuery(request);
		List<DataFreight> airfreightall = aqservice.FreightSelectQueryall(request);
		int pagemax=airfreightall.get(0).getYear();		//计算最大长度
		map.put("airfreight", airfreight);
		map.put("pagemax", pagemax);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/airpassenger",method = RequestMethod.POST)	//客运机场信息
	public Map <String,Object> Airpassenger(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<DataPassenger> airpassenger = aqservice.PassengerSelectQuery(request);
		List<DataPassenger> airpassengerall = aqservice.PassengerSelectQueryall(request);
		int pagemax=airpassengerall.get(0).getYear();		//计算最大长度
		map.put("airpassenger", airpassenger);
		map.put("pagemax", pagemax);
		return map;
	}


}