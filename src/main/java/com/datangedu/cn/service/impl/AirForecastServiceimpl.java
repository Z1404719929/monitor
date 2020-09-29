package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.FreightForecastMapper;
import com.datangedu.cn.dao.mapper.PassengerForecastMapper;
import com.datangedu.cn.model.sysUser.FreightForecast;
import com.datangedu.cn.model.sysUser.PassengerForecast;
import com.datangedu.cn.service.AirForecastService;

@Service
public class AirForecastServiceimpl implements AirForecastService{
	//查询freightbyyear表
	@Resource
	FreightForecastMapper freightForecastmapper;
	@Resource
	PassengerForecastMapper passengerForecastmapper;	

	// 货运机场走势图
	public List<FreightForecast> SelectFreight(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		return freightForecastmapper.selectforecast(airportname);
	};

	// 客运机场走势图
	public List<PassengerForecast> Selectpassenger(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		return passengerForecastmapper.selectforecast(airportname);
	};
		
	
}
