package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.AirFreightMapper;
import com.datangedu.cn.dao.mapper.AirPassengerMapper;
import com.datangedu.cn.model.sysUser.AirFreight;
import com.datangedu.cn.model.sysUser.AirPassenger;
import com.datangedu.cn.service.AirMapService;

@Service
public class AirMapServiceimpl implements AirMapService{
	@Resource
	AirFreightMapper AirFreightmapper;
	@Resource
	AirPassengerMapper AirPassengermapper;	
	
	
	//货运机场分布数据，制作地图
	public List<AirFreight> SelectallFreight() {
		return AirFreightmapper.selectall();
	};
	
	//货运机场分布数据，制作地图
	public List<AirPassenger> SelectallPassenger(){
		System.out.println("1111111111");
			return AirPassengermapper.selectall();
		};
	
	
}
