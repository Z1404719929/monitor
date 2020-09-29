package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;


public interface AirTrendService {
//	// 查询freightbyyear表
//	public List<Freightbyyear> SelectFreightbyyear(); // 货运机场运量走势图
//
//	public List<Passengerbyyear> SelectPassengerbyyear(); // 客运机场运量走势图
//
//	// 查询freightbyyear表
//	public List<Freightbymonth> SelectFreightbymonth(); // 货运机场运量走势图
//
//	public List<Passengerbymonth> SelectPassengerbymonth(); // 客运机场运量走势图

	////////////////////////////////////////////////
	//货运机场按年份
	public List<DataFreight> SelectFreightbyyear(HttpServletRequest request);

	// 客运机场按年份
	public List<DataPassenger> SelectPassengerbyyear(HttpServletRequest request);

	//货运机场按月份
	public List<DataFreight> SelectFreightbymonth(HttpServletRequest request);

	//客运机场按月份
	public List<DataPassenger> SelectPassengerbymonth(HttpServletRequest request);
}
