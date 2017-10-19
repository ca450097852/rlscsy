package com.hontek.sys.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.EntExpandDao;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.EntExpandServiceInter;
/**
 * 企业扩展信息
 * @author ZK
 *
 */
public class EntExpandServiceImpl extends BaseServiceImpl implements EntExpandServiceInter{
	private EntExpandDao entExpandDao;

	public void setEntExpandDao(EntExpandDao entExpandDao) {
		this.entExpandDao = entExpandDao;
	}

	public Pager<TsEnterprise> findPager(TsEnterprise enterprise, int page, int rows,
			String sort, String order) {
		return entExpandDao.findPager(enterprise, page, rows,sort,order);
	}

	public EntExpand getEntExpandByEntId(int entId) {
		return entExpandDao.get(EntExpand.class, entId);
	}
	
	
	public List<EntExpand> findAllList(EntExpand entExpand){
		return entExpandDao.findAllList(entExpand);
	}
}
