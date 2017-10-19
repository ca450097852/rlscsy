package com.hontek.synchronous.service.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.synchronous.pojo.DataInfo;

public interface DataInfoServiceInter {

	public List<DataInfo> addList2(String type);
	
	public List<DataInfo> addList();
}
