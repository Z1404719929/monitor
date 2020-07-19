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

import com.datangedu.cn.model.sysUser.Freightbymonth;
import com.datangedu.cn.model.sysUser.Freightbyyear;
import com.datangedu.cn.model.sysUser.Passengerbymonth;
import com.datangedu.cn.model.sysUser.Passengerbyyear;
import com.datangedu.cn.service.AirTrendService;


@Controller
@RequestMapping("/airtrend")
public class air_trend {
	
	
	@Resource
	AirTrendService atservice;
	
	//按年
	//通过freightbyyear查走势图
	@ResponseBody
	@RequestMapping(value = "/freight_trend",method = RequestMethod.POST)
	public Map <String,Object> Freight(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
//		List<DataFreight> trenddata = atservice.SelectFreight(request);
		List<Freightbyyear> trenddata = atservice.SelectFreightbyyear();

		//取出年份和总数，分别存入数组
		int []year= new int[trenddata.size()];
		String []sum= new String[trenddata.size()];
		
		for(int i=0;i<trenddata.size();i++) {
			year[i]=trenddata.get(i).getYear();
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			String a= df.format(trenddata.get(i).getValue());
			sum[i]=a;
		}
			
		map.put("year", year);
		map.put("sum", sum);
		return map;
	}
	
	//客运机场运量走势图数据
	@ResponseBody
	@RequestMapping(value = "/passenger_trend",method = RequestMethod.POST)	//客运机场信息
	public Map <String,Object> Passenger(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Passengerbyyear> trenddata = atservice.SelectPassengerbyyear();
		
		//取出年份和总数，分别存入数组
		int []year= new int[trenddata.size()];
		String []sum= new String[trenddata.size()];
		
		for(int i=0;i<trenddata.size();i++) {
			year[i]=trenddata.get(i).getYear();
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			String a= df.format(trenddata.get(i).getValue());
			sum[i]=a;
		}
		map.put("year", year);
		map.put("sum", sum);
		return map;
	}
	
	// 按月
	// 通过freightbyyear查走势图
	@ResponseBody
	@RequestMapping(value = "/freightbymonth", method = RequestMethod.POST)
	public Map<String, Object> Freightbymonth(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
//			List<DataFreight> trenddata = atservice.SelectFreight(request);
		List<Freightbymonth> trenddata = atservice.SelectFreightbymonth();

		// 取出年份和总数，分别存入数组
		int[] year = new int[trenddata.size()];
		String[] sum = new String[trenddata.size()];

		for (int i = 0; i < trenddata.size(); i++) {
			year[i] = trenddata.get(i).getMonth();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String a = df.format(trenddata.get(i).getValue());
			sum[i] = a;
		}

		map.put("year", year);
		map.put("sum", sum);
		return map;
	}

	// 客运机场运量走势图数据
	@ResponseBody
	@RequestMapping(value = "/passengerbymonth", method = RequestMethod.POST) // 客运机场信息
	public Map<String, Object> Passengerbymonth(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Passengerbymonth> trenddata = atservice.SelectPassengerbymonth();

		// 取出年份和总数，分别存入数组
		int[] year = new int[trenddata.size()];
		String[] sum = new String[trenddata.size()];

		for (int i = 0; i < trenddata.size(); i++) {
			year[i] = trenddata.get(i).getMonth();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String a = df.format(trenddata.get(i).getValue());
			sum[i] = a;
		}
		map.put("year", year);
		map.put("sum", sum);
		return map;
	}
	
	
	
	
	
	//通过freight_data查走势图
	//货运机场运量走势图数据
//	@ResponseBody
//	@RequestMapping(value = "/freight_trend",method = RequestMethod.POST)
//	public Map <String,Object> Freight(HttpServletRequest request) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<DataFreight> trenddata = atservice.SelectFreight(request);
//		List<Freightbyyear> trenddata1 = atservice.SelectFreightbyyear();
//		System.out.println("test=="+trenddata1.get(0).getYear()+trenddata1.get(0).getValue());
//		System.out.println("test=="+trenddata1.get(1).getYear()+trenddata1.get(1).getValue());
//
//		//取出年份和总数，分别存入数组
//		int []year= new int[trenddata.size()];
//		String []sum= new String[trenddata.size()];
//		
//		for(int i=0;i<trenddata.size();i++) {
//			year[i]=trenddata.get(i).getYear();
//			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
//			String a= df.format(trenddata.get(i).getValue());
//			sum[i]=a;
//		}
//			
//		map.put("year", year);
//		map.put("sum", sum);
//		return map;
//	}
//	
//	//客运机场运量走势图数据
//	@ResponseBody
//	@RequestMapping(value = "/passenger_trend",method = RequestMethod.POST)	//客运机场信息
//	public Map <String,Object> Passenger(HttpServletRequest request) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<DataPassenger> trenddata = atservice.SelectPassenger(request);
//		
//		//取出年份和总数，分别存入数组
//		int []year= new int[trenddata.size()];
//		String []sum= new String[trenddata.size()];
//		
//		for(int i=0;i<trenddata.size();i++) {
//			year[i]=trenddata.get(i).getYear();
//			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
//			String a= df.format(trenddata.get(i).getValue());
//			sum[i]=a;
//		}
//		map.put("year", year);
//		map.put("sum", sum);
//		return map;
//	}


}