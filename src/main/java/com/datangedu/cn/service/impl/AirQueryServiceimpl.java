package com.datangedu.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.DataFreightMapper;
import com.datangedu.cn.dao.mapper.DataPassengerMapper;
import com.datangedu.cn.model.sysUser.DataFreight;
import com.datangedu.cn.model.sysUser.DataPassenger;
import com.datangedu.cn.service.AirQueryService;

@Service
public class AirQueryServiceimpl implements AirQueryService{
	
	
	@Resource
	DataFreightMapper DataFreightmapper;
	@Resource
	DataPassengerMapper DataPassengermapper;	
	
	
	//货运机场数据
	@Override
	public List<DataFreight> FreigthSelectQuery(HttpServletRequest request) {
		int []arr= new int[6];
		arr=data(request);
		String airportname=request.getParameter("airportname");
		double datalow=0;
		double datahigh=999999999;
		if (!(request.getParameter("datalow")==null)) {
			try {
				datalow=Double.parseDouble(request.getParameter("datalow"));
		} catch (Exception e) {
			
		}
			
		}
		if (!(request.getParameter("datahigh")==null)) {
			try {
				datahigh=Double.parseDouble(request.getParameter("datahigh"));
		} catch (Exception e) {
			
		}
			
		}
		return DataFreightmapper.selectquery(airportname, arr[0], arr[1], arr[2], arr[3], arr[4],datalow,datahigh);
	};
	
	//货运机场数据
	@Override
	public List<DataFreight> FreightSelectQueryall(HttpServletRequest request){
		int []arr= new int[6];
		arr=data(request);
		String airportname=request.getParameter("airportname");
		double datalow=0;
		double datahigh=999999999;
		if (!(request.getParameter("datalow")==null)) {
			try {
				datalow=Double.parseDouble(request.getParameter("datalow"));
		} catch (Exception e) {
		}
		}
		if (!(request.getParameter("datahigh")==null)) {
			try {
				datahigh=Double.parseDouble(request.getParameter("datahigh"));
		} catch (Exception e) {
		}
		}
		return DataFreightmapper.selectqueryall(airportname, arr[0], arr[1], arr[2], arr[3],datalow,datahigh);
		}
	
	// 客运机场数据
	@Override
	public List<DataPassenger> PassengerSelectQuery(HttpServletRequest request) {
		int[] arr = new int[6];
		arr = data(request);
		String airportname = request.getParameter("airportname");
		double datalow=0;
		double datahigh=999999999;
		if (!(request.getParameter("datalow")==null)) {
			try {
				datalow=Double.parseDouble(request.getParameter("datalow"));
		} catch (Exception e) {
		}
		}
		if (!(request.getParameter("datahigh")==null)) {
			try {
				datahigh=Double.parseDouble(request.getParameter("datahigh"));
		} catch (Exception e) {
		}
		}
		return DataPassengermapper.selectquery(airportname, arr[0], arr[1], arr[2], arr[3], arr[4],datalow,datahigh);
	};

	// 客运机场数据
	@Override
	public List<DataPassenger> PassengerSelectQueryall(HttpServletRequest request) {
		String airportname = request.getParameter("airportname");
		int[] arr = new int[6];
		arr = data(request);
		double datalow=0;
		double datahigh=999999999;
		if (!(request.getParameter("datalow")==null)) {
			try {
				datalow=Double.parseDouble(request.getParameter("datalow"));
		} catch (Exception e) {
		}
		}
		if (!(request.getParameter("datahigh")==null)) {
			try {
				datahigh=Double.parseDouble(request.getParameter("datahigh"));
		} catch (Exception e) {
		}
		}
		return DataPassengermapper.selectqueryall(airportname, arr[0], arr[1], arr[2], arr[3],datalow,datahigh);
	}
	
	
	//存查询条件
	public int[] data(HttpServletRequest request){
		int []arr= new int[6];
		arr[0]=1;
		arr[1]=12;
		arr[2]=2007;
		arr[3]=2020;
		arr[4]=0;
//		System.out.println("qiguaniasf"+request.getParameter("page"));
		if (!request.getParameter("monthstart").isEmpty()) {
			arr[0] = Integer.valueOf(request.getParameter("monthstart"));
		}
		if (!request.getParameter("monthend").isEmpty()) {
			arr[1]=Integer.valueOf(request.getParameter("monthend"));
		}
		if (!request.getParameter("yearstart").isEmpty()) {
			arr[2]=Integer.valueOf(request.getParameter("yearstart"));
		}
		if (!request.getParameter("yearend").isEmpty()) {
			arr[3]=Integer.valueOf(request.getParameter("yearend"));
		}
		if (!request.getParameter("page").isEmpty()) {
			arr[4]=(Integer.valueOf(request.getParameter("page"))-1)*10;
		}
		return arr;
	}
	
	

	
}
