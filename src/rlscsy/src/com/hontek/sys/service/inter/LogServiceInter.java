package com.hontek.sys.service.inter;

import java.util.Map;

import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TsLog;

public interface LogServiceInter {

	@SuppressWarnings("unchecked")
	Pager findLogList(Map mapCondition, int page, int rows);
	
	public void saveLog(TsLog tslog);
	
	public int getTableSequence();
}
