package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataFreightExample;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.model.sysUser.DataPassengerExample;
import com.datangedu.cn.service.AirTopService;

@Service
public class AirTopServiceimpl implements AirTopService{
	//查询freightbyyear表
	@Resource
	DataFreightMapper freightrmapper;
	@Resource
	DataPassengerMapper passengermapper;
	
	// 货运
	public List<DataFreight> Selectfreighttop() {
		DataFreightExample FreightExample = new DataFreightExample();
		DataFreightExample.Criteria criteria = FreightExample.createCriteria();
		return freightrmapper.selecttop();
	};

	// 客运
	public List<DataPassenger> Selectpassengertop() {
		DataPassengerExample PassengerExample = new DataPassengerExample();
		DataPassengerExample.Criteria criteria = PassengerExample.createCriteria();
		return passengermapper.selecttop();
	};
	

	
}
