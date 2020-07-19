package com.datangedu.cn.service;

import java.util.List;

import com.datangedu.cn.model.sysUser.AirFreight;
import com.datangedu.cn.model.sysUser.AirPassenger;

public interface AirMapService {
	public List<AirFreight> SelectallFreight();		//货运机场分布数据，制作地图
	public List<AirPassenger> SelectallPassenger();		//客运机场分布数据，制作地图
}
