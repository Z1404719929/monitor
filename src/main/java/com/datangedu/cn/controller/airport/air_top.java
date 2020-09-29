package com.datangedu.cn.controller.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.AirTopService;


@Controller
@RequestMapping("/airtop")
public class air_top {
	
	
	
	@Resource
	AirTopService atservice;
	
	//货运
	@ResponseBody
	@RequestMapping(value = "/freight",method = RequestMethod.POST)	//货运机场信息
	public Map <String,Object> freight() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<DataFreight> data=atservice.Selectfreighttop();
		String []airname=new String[10];
		double []value=new double[10];
		for(int i=0;i<data.size();i++) {
			airname[i]=data.get(i).getAirportname();
			value[i]=data.get(i).getValue();
		}
		map.put("airname", airname);
		map.put("value", value);
		return map;
	}

	
	//客运
		@ResponseBody
		@RequestMapping(value = "/passenger",method = RequestMethod.POST)	//货运机场信息
		public Map <String,Object> passenger() {
			Map<String,Object> map = new HashMap<String,Object>();
			List<DataPassenger> data=atservice.Selectpassengertop();
			String []airname=new String[10];
			double []value=new double[10];
			for(int i=0;i<data.size();i++) {
				airname[i]=data.get(i).getAirportname();
				value[i]=data.get(i).getValue();
			}
			map.put("airname", airname);
			map.put("value", value);
			return map;
		}


}