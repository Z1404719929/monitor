package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
//import com.datangedu.cn.dao.mapper.FreightbyairandmonthMapper;
//import com.datangedu.cn.dao.mapper.FreightbyairandyearMapper;
//import com.datangedu.cn.dao.mapper.PassengerbyairandmonthMapper;
//import com.datangedu.cn.dao.mapper.PassengerbyairandyearMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.StatisticsService;

@Service
public class StatisticsServiceimpl implements StatisticsService {

//	// 按机场名，年份
//	@Resource
//	FreightbyairandyearMapper Freightbyairandyearmapper;
//	@Resource
//	PassengerbyairandyearMapper Passengerbyairandyearmapper;
//	// 按机场名，月份
//	@Resource
//	FreightbyairandmonthMapper Freightbyairandmonthmapper;
//	@Resource
//	PassengerbyairandmonthMapper Passengerbyairandmonthmapper;
//
//	// 按年份
//	//货运机场数据
//	public List<Freightbyairandyear> Freightbyairandyear(HttpServletRequest request) {
//		String airportname = request.getParameter("airportname");
//		return Freightbyairandyearmapper.selectbyairandyear(airportname);
//	};
//	
//	// 货客机场数据
//	public List<Passengerbyairandyear> Passengerbyairandyear(HttpServletRequest request) {
//		String airportname = request.getParameter("airportname");
//		return Passengerbyairandyearmapper.selectbyairandyear(airportname);
//	};
//
//	// 按月份
//	// 货运机场数据
//	public List<Freightbyairandmonth> Freightbyairandmonth(HttpServletRequest request) {
//		String airportname = request.getParameter("airportname");
//		return Freightbyairandmonthmapper.selectbyairandmonth(airportname);
//	};
//
//	// 货客机场数据
//	public List<Passengerbyairandmonth> Passengerbyairandmonth(HttpServletRequest request) {
//		String airportname = request.getParameter("airportname");
//		return Passengerbyairandmonthmapper.selectbyairandmonth(airportname);
//	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Resource
	DataFreightMapper DataFreightmapper;
	@Resource
	DataPassengerMapper DataPassengermapper;

	// 按年份
	// 货运机场数据
	public List<DataFreight> FreightYs(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		int start = 2007;
		int end = 2020;
		if (!request.getParameter("start").isEmpty()) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (!request.getParameter("end").isEmpty()) {
			end = Integer.parseInt(request.getParameter("end"));
		}
		return DataFreightmapper.selectbyyear(airportname);
	};

//	//货运机场数据
	public List<DataPassenger> PassengerYs(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		int start = 2007;
		int end = 2020;
		if (!request.getParameter("start").isEmpty()) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (!request.getParameter("end").isEmpty()) {
			end = Integer.parseInt(request.getParameter("end"));
		}
		return DataPassengermapper.selectbyyear(airportname);
	};

	// 按月份
	// 货运机场数据
	public List<DataFreight> FreightMs(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		int start = 1;
		int end = 12;
		if (!request.getParameter("start").isEmpty()) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (!request.getParameter("end").isEmpty()) {
			end = Integer.parseInt(request.getParameter("end"));
		}
		return DataFreightmapper.selectbymonth(airportname);
	};

//		//货运机场数据
	public List<DataPassenger> PassengerMs(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		int start = 1;
		int end = 12;
		if (!request.getParameter("start").isEmpty()) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (!request.getParameter("end").isEmpty()) {
			end = Integer.parseInt(request.getParameter("end"));
		}
		return DataPassengermapper.selectbymonth(airportname);
	};

}
