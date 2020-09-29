package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;

public interface AirMapService {
	//3
	public List<DataFreight> Selectfreight(HttpServletRequest request);
	
	public List<DataPassenger> Selectpassenger(HttpServletRequest request);
	
//	public List<DataPassengerAll> SelectPassengerall(HttpServletRequest request);
	//2
//	public List<DataFreightAll> SelectFreightall(HttpServletRequest request);
//	public List<DataPassengerAll> SelectPassengerall(HttpServletRequest request);
	//1
//	public List<AirFreight> SelectallFreight();		//货运机场分布数据，制作地图
//	public List<AirPassenger> SelectallPassenger();		//客运机场分布数据，制作地图
}
