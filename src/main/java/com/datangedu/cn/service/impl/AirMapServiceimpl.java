package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.AirMapService;

@Service
public class AirMapServiceimpl implements AirMapService{
	
	@Resource
	DataFreightMapper freightrmapper;
	@Resource
	DataPassengerMapper passengermapper;
	
	public List<DataFreight> Selectfreight(HttpServletRequest request){
		String year="2019";
		if(!request.getParameter("year").isEmpty()) {
			year=request.getParameter("year");
		}
		String month="12";
		if(!request.getParameter("month").isEmpty()) {
			month=request.getParameter("month");
		}		
		return freightrmapper.selectmap(year, month);
	}
	
	public List<DataPassenger> Selectpassenger(HttpServletRequest request){
		String year="2019";
		if(!request.getParameter("year").isEmpty()) {
			year=request.getParameter("year");
		}
		String month="12";
		if(!request.getParameter("month").isEmpty()) {
			month=request.getParameter("month");
		}		
		return passengermapper.selectmap(year, month);
	}	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////
//	@Resource
//	DataFreightAllMapper DataFreightAllMapper;
//	@Resource
//	DataPassengerAllMapper DataPassengerAllMapper;
//	//货运机场分布数据，制作地图
//	public List<DataFreightAll> SelectFreightall(HttpServletRequest request) {
//		System.out.println("11111111");
//		String year="2020";
//		if(!request.getParameter("year").isEmpty()) {
//			year=request.getParameter("year");
//		}
//		String month="2";
//		if(!request.getParameter("month").isEmpty()) {
//			month=request.getParameter("month");
//		}		
//		return DataFreightAllMapper.selectmapvalue(year, month);
//	};
//	
//	//货运机场分布数据，制作地图
//	public List<DataPassengerAll> SelectPassengerall(HttpServletRequest request) {
//		System.out.println("11111111");
//		String year="2020";
//		if(!request.getParameter("year").isEmpty()) {
//			year=request.getParameter("year");
//		}
//		String month="2";
//		if(!request.getParameter("month").isEmpty()) {
//			month=request.getParameter("month");
//		}		
//		return DataPassengerAllMapper.selectmapvalue(year, month);
//	};
	
	
	
	
	
	
	////////////////////////////////////////
//	@Resource
//	AirFreightMapper AirFreightmapper;
//	@Resource
//	AirPassengerMapper AirPassengermapper;	
//	
//	
//	//货运机场分布数据，制作地图
//	public List<AirFreight> SelectallFreight() {
//		return AirFreightmapper.selectall();
//	};
//	
//	//货运机场分布数据，制作地图
//	public List<AirPassenger> SelectallPassenger(){
//		System.out.println("1111111111");
//			return AirPassengermapper.selectall();
//		};
//	
	
}
