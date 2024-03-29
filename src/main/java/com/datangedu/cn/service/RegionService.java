package com.datangedu.cn.service;

import java.util.List;

import com.datangedu.cn.model.sysUser.Region;

public interface RegionService {
	public List<Region> getList(String id);
	public List<Region> getParent(String id);
	public List<Region> getLevel(short id);
	public List<Region> getshi(String id);
}