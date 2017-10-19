package com.hontek.sys.service.impl;

import java.util.Map;


import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.LogDao;
import com.hontek.sys.pojo.TsLog;
import com.hontek.sys.service.inter.LogServiceInter;

public class LogServiceImpl implements LogServiceInter{
	private LogDao logDao;

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public Pager findLogList(Map mapCondition, int page, int rows) {
		Pager pager = logDao.findLogList(mapCondition,page,rows);
		return pager;
	}

	
	public void saveLog(TsLog tslog) {
		tslog.setLogId(getTableSequence());
		logDao.addSysLog(tslog);
	}

	public int getTableSequence() {
		return logDao.getTableSequence("TS_LOG");
	}
	
	
}
