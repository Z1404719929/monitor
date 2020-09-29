package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.AirTrendService;

@Service
public class AirTrendServiceimpl implements AirTrendService{


	///////////////////////////////////////////////////
	@Resource
	DataFreightMapper Datafreightmapper;
	@Resource
	DataPassengerMapper Datapassengermapper;	
	
	//货运机场按年份
		public List<DataFreight> SelectFreightbyyear(HttpServletRequest request) {
			String airportname=request.getParameter("airportname");
			return Datafreightmapper.selectbyyear(airportname);
		};
		
		//客运机场按年份
		public List<DataPassenger> SelectPassengerbyyear(HttpServletRequest request){
			String airportname=request.getParameter("airportname");
			return Datapassengermapper.selectbyyear(airportname);
		};
	
	//货运机场按月份
	public List<DataFreight> SelectFreightbymonth(HttpServletRequest request) {
		String airportname=request.getParameter("airportname");
		return Datafreightmapper.selectbymonth(airportname);
	};
	
	//客运机场按月份
	public List<DataPassenger> SelectPassengerbymonth(HttpServletRequest request){
		String airportname=request.getParameter("airportname");
		return Datapassengermapper.selectbymonth(airportname);
	};
	
	
	
	
	
	
	
	
	
//	//查询freightbyyear表
//	@Resource
//	FreightbyyearMapper freightbyyearmapper;
//	@Resource
//	PassengerbyyearMapper passengerbyyearmapper;	
//	@Resource
//	FreightbymonthMapper freightbymonthmapper;
//	@Resource
//	PassengerbymonthMapper passengerbymonthmapper;	
//	
//	
//	
//	// 货运机场走势图
//	public List<Freightbyyear> SelectFreightbyyear() {
//		FreightbyyearExample FreightbyyearExample = new FreightbyyearExample();
//		FreightbyyearExample.Criteria criteria = FreightbyyearExample.createCriteria();
//		return freightbyyearmapper.selectByExample(FreightbyyearExample);
//	};
//
//	// 客运机场走势图
//	public List<Passengerbyyear> SelectPassengerbyyear() {
//		PassengerbyyearExample PassengerbyyearExample = new PassengerbyyearExample();
//		PassengerbyyearExample.Criteria criteria = PassengerbyyearExample.createCriteria();
//		return passengerbyyearmapper.selectByExample(PassengerbyyearExample);
//	};
//	
//	// 货运机场月份统计图
//	public List<Freightbymonth> SelectFreightbymonth() {
//		FreightbymonthExample FreightbymonthExample = new FreightbymonthExample();
//		FreightbymonthExample.Criteria criteria = FreightbymonthExample.createCriteria();
//		return freightbymonthmapper.selectByExample(FreightbymonthExample);
//	};
//
//	// 客运机场月份统计图
//	public List<Passengerbymonth> SelectPassengerbymonth() {
//		PassengerbymonthExample PassengerbymonthExample = new PassengerbymonthExample();
//		PassengerbymonthExample.Criteria criteria = PassengerbymonthExample.createCriteria();
//		return passengerbymonthmapper.selectByExample(PassengerbymonthExample);
//	};
//		
}
