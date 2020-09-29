package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.FreightForecast;
import com.datangedu.cn.model.sysUser.PassengerForecast;

public interface AirForecastService {
	//查询freightbyyear表
	public List<FreightForecast> SelectFreight(HttpServletRequest request);		//货运机场运量走势图
	public List<PassengerForecast> Selectpassenger(HttpServletRequest request);		//客运机场运量走势图
	

}
