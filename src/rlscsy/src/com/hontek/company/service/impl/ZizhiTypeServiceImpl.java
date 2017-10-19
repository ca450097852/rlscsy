package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.company.dao.ZizhiTypeDao;
import com.hontek.company.service.inter.ZizhiTypeServiceInter;

/**
 * 资质类型Service
 * @author lzk
 *
 */
public class ZizhiTypeServiceImpl extends BaseServiceImpl implements ZizhiTypeServiceInter{
	
	private ZizhiTypeDao zizhiTypeDao;

	public void setZizhiTypeDao(ZizhiTypeDao zizhiTypeDao) {
		this.zizhiTypeDao = zizhiTypeDao;
	}

	
	
	
}
