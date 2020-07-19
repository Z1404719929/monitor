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

import com.datangedu.cn.model.sysUser.Freightbyairandmonth;
import com.datangedu.cn.model.sysUser.Freightbyairandyear;
import com.datangedu.cn.model.sysUser.Passengerbyairandmonth;
import com.datangedu.cn.model.sysUser.Passengerbyairandyear;
import com.datangedu.cn.service.StatisticsService;

@Controller
@RequestMapping("/data_statistics")
public class data_Statistics {

	@Resource
	StatisticsService yservice;
	//按机场，年份，统计货运
		@ResponseBody
		@RequestMapping(value = "/freight_year", method = RequestMethod.POST)
		public Map<String, Object> freightbyairandyear(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Freightbyairandyear> yeardata = yservice.Freightbyairandyear(request);
			// 取出月份数据，存入数组
			String[] value = new String[yeardata.size()];
			int[] year = new int[yeardata.size()];
			for (int i = 0; i < yeardata.size(); i++) {
				year[i] = yeardata.get(i).getYear();
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
				String a = df.format(yeardata.get(i).getValue());
				value[i] = a;
			}
			map.put("month", year);
			map.put("data", value);
			return map;
		}
		
	
	
	//按机场，年份，统计客运
	@ResponseBody
	@RequestMapping(value = "/passenger_year", method = RequestMethod.POST)
	public Map<String, Object> Passengerbyairandyear(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Passengerbyairandyear> yeardata = yservice.Passengerbyairandyear(request);
		// 取出月份数据，存入数组
		String[] value = new String[yeardata.size()];
		int[] year = new int[yeardata.size()];
		for (int i = 0; i < yeardata.size(); i++) {
			year[i] = yeardata.get(i).getYear();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String a = df.format(yeardata.get(i).getValue());
			value[i] = a;
		}
		map.put("month", year);
		map.put("data", value);
		return map;
	}
	
	//按月份
	// 货运机场运量按年份统计
		@ResponseBody
		@RequestMapping(value = "/freight_month", method = RequestMethod.POST)
		public Map<String, Object> FreightMs(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Freightbyairandmonth> yeardata = yservice.Freightbyairandmonth(request);
			// 取出月份数据，存入数组
			String[] value = new String[yeardata.size()];
			int[] year = new int[yeardata.size()];
			for (int i = 0; i < yeardata.size(); i++) {
				year[i] = yeardata.get(i).getMonth();
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
				String a = df.format(yeardata.get(i).getValue());
				value[i] = a;
			}
			map.put("year", year);
			map.put("data", value);
			return map;
		}

		// 客运机场运量按年份统计
		@ResponseBody
		@RequestMapping(value = "/passenger_month", method = RequestMethod.POST) // 客运机场信息
		public Map<String, Object> PassengerMs(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Passengerbyairandmonth> yeardata = yservice.Passengerbyairandmonth(request);
			// 取出月份数据，存入数组
			String[] value = new String[yeardata.size()];
			int[] year = new int[yeardata.size()];
			for (int i = 0; i < yeardata.size(); i++) {
				year[i] = yeardata.get(i).getMonth();
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
				String a = df.format(yeardata.get(i).getValue());
				value[i] = a;
			}
			map.put("year", year);
			map.put("data", value);
			return map;
		}
	
	
	
	
	
	
	
////////////////////////////////////////////////////////////////
	// 货运机场运量按年份统计
//	@ResponseBody
//	@RequestMapping(value = "/freight_year", method = RequestMethod.POST)
//	public Map<String, Object> FreightYs(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<DataFreight> yeardata = yservice.FreightYs(request);
//		// 取出月份数据，存入数组
//		String[] value = new String[yeardata.size()];
//		int[] month = new int[yeardata.size()];
//		for (int i = 0; i < yeardata.size(); i++) {
//			month[i] = yeardata.get(i).getMonth();
//			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//			String a = df.format(yeardata.get(i).getValue());
//			value[i] = a;
//		}
//		map.put("month", month);
//		map.put("data", value);
//		return map;
//	}
//
//	// 客运机场运量按年份统计
//	@ResponseBody
//	@RequestMapping(value = "/passenger_year", method = RequestMethod.POST) // 客运机场信息
//	public Map<String, Object> PassengerYs(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<DataPassenger> yeardata = yservice.PassengerYs(request);
//		// 取出月份数据，存入数组
//		String[] value = new String[yeardata.size()];
//		int[] month = new int[yeardata.size()];
//		for (int i = 0; i < yeardata.size(); i++) {
//			month[i] = yeardata.get(i).getMonth();
//			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//			String a = df.format(yeardata.get(i).getValue());
//			value[i] = a;
//		}
//		map.put("month", month);
//		map.put("data", value);
//		return map;
//	}
//	
//	
//	//按月份
//	// 货运机场运量按年份统计
//		@ResponseBody
//		@RequestMapping(value = "/freight_month", method = RequestMethod.POST)
//		public Map<String, Object> FreightMs(HttpServletRequest request) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			List<DataFreight> yeardata = yservice.FreightMs(request);
//			// 取出月份数据，存入数组
//			String[] value = new String[yeardata.size()];
//			int[] year = new int[yeardata.size()];
//			for (int i = 0; i < yeardata.size(); i++) {
//				year[i] = yeardata.get(i).getYear();
//				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//				String a = df.format(yeardata.get(i).getValue());
//				value[i] = a;
//			}
//			map.put("year", year);
//			map.put("data", value);
//			return map;
//		}
//
//		// 客运机场运量按年份统计
//		@ResponseBody
//		@RequestMapping(value = "/passenger_month", method = RequestMethod.POST) // 客运机场信息
//		public Map<String, Object> PassengerMs(HttpServletRequest request) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			List<DataPassenger> yeardata = yservice.PassengerMs(request);
//			// 取出月份数据，存入数组
//			String[] value = new String[yeardata.size()];
//			int[] year = new int[yeardata.size()];
//			for (int i = 0; i < yeardata.size(); i++) {
//				year[i] = yeardata.get(i).getYear();
//				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//				String a = df.format(yeardata.get(i).getValue());
//				value[i] = a;
//			}
//			map.put("year", year);
//			map.put("data", value);
//			return map;
//		}


}