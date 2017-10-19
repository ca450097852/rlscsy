package com.hontek.jcmanager.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.RetailMarketTranInfoSummDao;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.RetailMarketTranInfoSumm;
import com.hontek.jcmanager.service.inter.RetailMarketTranInfoSummServiceInter;

public class RetailMarketTranInfoSummServiceImpl extends BaseServiceImpl implements RetailMarketTranInfoSummServiceInter{
	private RetailMarketTranInfoSummDao retailMarketTranInfoSummDao;

	public void setRetailMarketTranInfoSummDao(
			RetailMarketTranInfoSummDao retailMarketTranInfoSummDao) {
		this.retailMarketTranInfoSummDao = retailMarketTranInfoSummDao;
	}

	public String addRetailMarketTranInfoSumm(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm) {
		String msg = "添加成功";
		try {
			if(retailMarketTranInfoSumm!=null){
				retailMarketTranInfoSumm.setRmiisId(retailMarketTranInfoSummDao.getTableSequence("tb_retail_market_tran_info_summ".toUpperCase()));
				retailMarketTranInfoSummDao.save(retailMarketTranInfoSumm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateRetailMarketTranInfoSumm(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm) {
		String msg = "添加成功";
		try {
			if(retailMarketTranInfoSumm!=null)
				retailMarketTranInfoSummDao.update(retailMarketTranInfoSumm);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteRetailMarketTranInfoSumm(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				retailMarketTranInfoSummDao.executeHql("delete from RetailMarketTranInfoSumm where rmiisId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<RetailMarketTranInfoSumm> findList(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm, int page,
			int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(retailMarketTranInfoSumm!=null){
			if(retailMarketTranInfoSumm.getRetailMarketId()!=null && !"".equals(retailMarketTranInfoSumm.getRetailMarketId())){
				condition.append(" and retailMarketId='"+retailMarketTranInfoSumm.getRetailMarketId()+"' ");
			}
			
			if(retailMarketTranInfoSumm.getPtbId()!=0){
				condition.append(" and ptbId="+retailMarketTranInfoSumm.getPtbId());
			}
		}
		
		condition.append(sortCondtion(sort, order));
		Pager<RetailMarketTranInfoSumm> pager = retailMarketTranInfoSummDao.findList(condition.toString(),page,rows);
		return pager;
	}
	
}
