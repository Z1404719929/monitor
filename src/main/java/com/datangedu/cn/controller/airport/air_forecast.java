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

import com.datangedu.cn.model.sysUser.FreightForecast;
import com.datangedu.cn.model.sysUser.PassengerForecast;
import com.datangedu.cn.service.AirForecastService;


@Controller
@RequestMapping("/forecast")
public class air_forecast {
	
	
	@Resource
	AirForecastService afservice;
	
	// 货运量预测
	@ResponseBody
	@RequestMapping(value = "/freight_forecast", method = RequestMethod.POST)
	public Map<String, Object> Freightforecas(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FreightForecast> data = afservice.SelectFreight(request);

		// 取出月份和总数，分别存入数组
		int[] month = new int[data.size()];
		String[] sum = new String[data.size()];

		for (int i = 0; i < data.size(); i++) {
			month[i] = data.get(i).getMonth();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String a = df.format(data.get(i).getValue());
			sum[i] = a;
		}
		System.out.println(month.length);
		map.put("month", month);
		map.put("sum", sum);
		return map;
	}

	// 客运量预测
	@ResponseBody
	@RequestMapping(value = "/passenger_forecast", method = RequestMethod.POST) // 客运机场信息
	public Map<String, Object> Passengerforecas(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PassengerForecast> data = afservice.Selectpassenger(request);

		// 取出月份和总数，分别存入数组
		int[] month = new int[data.size()];
		String[] sum = new String[data.size()];

		for (int i = 0; i < data.size(); i++) {
			month[i] = data.get(i).getMonth();
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			String a = df.format(data.get(i).getValue());
			sum[i] = a;
		}
		System.out.println(month.length);
		map.put("month", month);
		map.put("sum", sum);
		return map;
	}
	

}