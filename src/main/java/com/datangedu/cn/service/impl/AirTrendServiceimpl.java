package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
import com.datangedu.cn.dao.mapper.FreightbymonthMapper;
import com.datangedu.cn.dao.mapper.FreightbyyearMapper;
import com.datangedu.cn.dao.mapper.PassengerbymonthMapper;
import com.datangedu.cn.dao.mapper.PassengerbyyearMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.model.sysUser.Freightbymonth;
import com.datangedu.cn.model.sysUser.FreightbymonthExample;
import com.datangedu.cn.model.sysUser.Freightbyyear;
import com.datangedu.cn.model.sysUser.FreightbyyearExample;
import com.datangedu.cn.model.sysUser.Passengerbymonth;
import com.datangedu.cn.model.sysUser.PassengerbymonthExample;
import com.datangedu.cn.model.sysUser.Passengerbyyear;
import com.datangedu.cn.model.sysUser.PassengerbyyearExample;
import com.datangedu.cn.service.AirTrendService;

@Service
public class AirTrendServiceimpl implements AirTrendService{
	//查询freightbyyear表
	@Resource
	FreightbyyearMapper freightbyyearmapper;
	@Resource
	PassengerbyyearMapper passengerbyyearmapper;	
	@Resource
	FreightbymonthMapper freightbymonthmapper;
	@Resource
	PassengerbymonthMapper passengerbymonthmapper;	
	
	// 货运机场走势图
	public List<Freightbyyear> SelectFreightbyyear() {
		FreightbyyearExample FreightbyyearExample = new FreightbyyearExample();
		FreightbyyearExample.Criteria criteria = FreightbyyearExample.createCriteria();
		return freightbyyearmapper.selectByExample(FreightbyyearExample);
	};

	// 客运机场走势图
	public List<Passengerbyyear> SelectPassengerbyyear() {
		PassengerbyyearExample PassengerbyyearExample = new PassengerbyyearExample();
		PassengerbyyearExample.Criteria criteria = PassengerbyyearExample.createCriteria();
		return passengerbyyearmapper.selectByExample(PassengerbyyearExample);
	};
	
	// 货运机场月份统计图
	public List<Freightbymonth> SelectFreightbymonth() {
		FreightbymonthExample FreightbymonthExample = new FreightbymonthExample();
		FreightbymonthExample.Criteria criteria = FreightbymonthExample.createCriteria();
		return freightbymonthmapper.selectByExample(FreightbymonthExample);
	};

	// 客运机场月份统计图
	public List<Passengerbymonth> SelectPassengerbymonth() {
		PassengerbymonthExample PassengerbymonthExample = new PassengerbymonthExample();
		PassengerbymonthExample.Criteria criteria = PassengerbymonthExample.createCriteria();
		return passengerbymonthmapper.selectByExample(PassengerbymonthExample);
	};
		
		
		
	///////////////////////////////////////////////////
	@Resource
	DataFreightMapper Datafreightmapper;
	@Resource
	DataPassengerMapper Datapassengermapper;	
	
	
	//货运机场走势图
	public List<DataFreight> SelectFreight(HttpServletRequest request) {
		int start=1;int end=12;
		String airportname=request.getParameter("airportname");
		return Datafreightmapper.selectbymonth(airportname, start, end);
	};
	
	//客运机场走势图
	public List<DataPassenger> SelectPassenger(HttpServletRequest request){
		int start=1;int end=12;
		String airportname=request.getParameter("airportname");
		return Datapassengermapper.selectbymonth(airportname, start, end);
	};
	
}
