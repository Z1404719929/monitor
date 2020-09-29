package com.datangedu.cn.controller.airport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.DataAll;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
//import com.datangedu.cn.model.sysUser.DataFreightAll;
//import com.datangedu.cn.model.sysUser.DataPassengerAll;
import com.datangedu.cn.service.AirMapService;


@Controller
@RequestMapping("/airmap")
public class air_map {
	
	//货运机场分布
	@Resource
	AirMapService amservice;
	
	//3
	@ResponseBody
	@RequestMapping(value = "/alldata",method = RequestMethod.POST)	//货运机场信息
	public Map <String,Object> alldata(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<DataFreight> mapfreight = amservice.Selectfreight(request);
		List<DataPassenger> mappassenger = amservice.Selectpassenger(request);
		
		map.put("mapfreight", mapfreight);
		map.put("mappassenger", mappassenger);
		
		List<DataAll> dataall = new ArrayList<DataAll>();
		
		
		for(int i=0;i<mapfreight.size();i++) {
			for(int j=0;j<mappassenger.size();j++) {
				if(mapfreight.get(i).getAirportname().equals(mappassenger.get(j).getAirportname())){
					int n=0;
					DataAll da=new DataAll();
					da.setAirportname(mapfreight.get(i).getAirportname());
					java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
					String a= df.format(mapfreight.get(i).getValue());
					String b= df.format(mappassenger.get(j).getValue());
					da.setfreightvalue(a);
					da.setpassengervalue(b);
					String c= df.format(mapfreight.get(i).getLatitude());
					String d= df.format(mapfreight.get(i).getLongitude());
					da.setLatitude(c);
					da.setLongitude(d);
					dataall.add(n, da);
					n=n+1;
				}
			}
		}
		
		map.put("airdata", dataall);
		for(int i=0;i<dataall.size();i++) {
			System.out.println(dataall.get(i).getAirportname());
		}
		System.out.println(dataall.size());
		System.out.println(mapfreight.size());
		System.out.println(mappassenger.size());
		
//		System.out.println(airfreight.get(0).getAirportname());
		return map;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//2
//	@ResponseBody
//	@RequestMapping(value = "/airfreight",method = RequestMethod.POST)	//货运机场信息
//	public Map <String,Object> Airfreights(HttpServletRequest request) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<DataFreightAll> airfreight = afservice.SelectFreightall(request);
//		map.put("airdata", airfreight);
////		System.out.println(airfreight.get(0).getAirportname());
//		return map;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/airpassenger",method = RequestMethod.POST)	//客运机场信息
//	public Map <String,Object> Airpassenger(HttpServletRequest request) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<DataPassengerAll> airpassenger = afservice.SelectPassengerall(request);
//		map.put("airdata", airpassenger);
//		System.out.println("11111");
//		return map;
//	}
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////
//	@ResponseBody
//	@RequestMapping(value = "/airfreight",method = RequestMethod.POST)	//货运机场信息
//	public Map <String,Object> Airfreight() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<AirFreight> airfreight = afservice.SelectallFreight();
//		map.put("airdata", airfreight);
//		System.out.println(airfreight.get(0).getAirportname());
//		return map;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/airpassenger",method = RequestMethod.POST)	//客运机场信息
//	public Map <String,Object> Airpassenger() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<AirPassenger> airpassenger = afservice.SelectallPassenger();
//		map.put("airdata", airpassenger);
//		System.out.println("11111");
//		return map;
//	}


}