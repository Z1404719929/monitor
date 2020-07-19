package com.datangedu.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;

public interface AirQueryService {
	//货运
	public List<DataFreight> FreigthSelectQuery(HttpServletRequest request);		//查询分页
	public List<DataFreight> FreightSelectQueryall(HttpServletRequest request);		//查询所有
	//客运
	public List<DataPassenger> PassengerSelectQuery(HttpServletRequest request);		//查询分页
	public List<DataPassenger> PassengerSelectQueryall(HttpServletRequest request);		//查询所有
}
