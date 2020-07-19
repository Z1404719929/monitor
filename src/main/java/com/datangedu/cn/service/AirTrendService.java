package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.model.sysUser.Freightbymonth;
import com.datangedu.cn.model.sysUser.Freightbyyear;
import com.datangedu.cn.model.sysUser.Passengerbymonth;
import com.datangedu.cn.model.sysUser.Passengerbyyear;

public interface AirTrendService {
	//查询freightbyyear表
	public List<Freightbyyear> SelectFreightbyyear();		//货运机场运量走势图
	public List<Passengerbyyear> SelectPassengerbyyear();		//客运机场运量走势图
	
	//查询freightbyyear表
	public List<Freightbymonth> SelectFreightbymonth();		//货运机场运量走势图
	public List<Passengerbymonth> SelectPassengerbymonth();		//客运机场运量走势图
	
	
	////////////////////////////////////////////////
	public List<DataFreight> SelectFreight(HttpServletRequest request);		//货运机场运量走势图
	public List<DataPassenger> SelectPassenger(HttpServletRequest request);		//客运机场运量走势图
}
