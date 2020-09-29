package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;


public interface AirTopService {
	//查询freightbyyear表
	public List<DataFreight> Selectfreighttop();		//货运机场运量走势图
	public List<DataPassenger> Selectpassengertop();		//客运机场运量走势图
	

}
