package com.hontek.jcmanager.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.MarketInInfoBaseDao;
import com.hontek.jcmanager.dao.MarketInInfoDetailDao;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.MarketInInfoDetail;
import com.hontek.jcmanager.service.inter.MarketInInfoDetailServiceInter;

public class MarketInInfoDetailServiceImpl extends BaseServiceImpl implements MarketInInfoDetailServiceInter{
	private MarketInInfoDetailDao marketInInfoDetailDao;
	private MarketInInfoBaseDao marketInInfoBaseDao;
	
	
	
	public void setMarketInInfoBaseDao(MarketInInfoBaseDao marketInInfoBaseDao) {
		this.marketInInfoBaseDao = marketInInfoBaseDao;
	}

	public void setMarketInInfoDetailDao(MarketInInfoDetailDao marketInInfoDetailDao) {
		this.marketInInfoDetailDao = marketInInfoDetailDao;
	}

	public String addMarketInInfoDetail(MarketInInfoDetail marketInInfoDetail) {
		String msg = "添加成功";
		try {
			if(marketInInfoDetail!=null){
				
				MarketInInfoBase marketInInfoBase = marketInInfoBaseDao.get(MarketInInfoBase.class,marketInInfoDetail.getMiibId());
				
				if(marketInInfoBase!=null){
					marketInInfoDetail.setMiidId(marketInInfoDetailDao.getTableSequence("tb_market_in_info_detail".toUpperCase()));
					
					marketInInfoDetail.setTranId(marketInInfoBase.getTranId());
					marketInInfoDetail.setQuarantineAnimalProductsId(marketInInfoBase.getQuarantineAnimalProductsId());
					marketInInfoDetail.setInspectionMeatId(marketInInfoBase.getInspectionMeatId());
					marketInInfoDetail.setProvId(marketInInfoBase.getProvId());
					marketInInfoDetail.setQuarantineVegeId(marketInInfoBase.getQuarantineVegeId());
					marketInInfoDetail.setBatchId(marketInInfoBase.getBatchId());
					
					marketInInfoDetailDao.save(marketInInfoDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateMarketInInfoDetail(MarketInInfoDetail marketInInfoDetail) {
		String msg = "添加成功";
		try {
			if(marketInInfoDetail!=null)
				marketInInfoDetailDao.update(marketInInfoDetail);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteMarketInInfoDetail(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				marketInInfoDetailDao.executeHql("delete from MarketInInfoDetail where miidId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<MarketInInfoDetail> findList(MarketInInfoDetail marketInInfoDetail, int page, int rows,
			String sort, String order) {
		
		StringBuffer condition = new StringBuffer();
		if(marketInInfoDetail!=null){
			if(marketInInfoDetail.getMiibId()!=0){
				condition.append(" and miibId="+marketInInfoDetail.getMiibId());
			}
		}
		condition.append(sortCondtion(sort, order));
		
		Pager<MarketInInfoDetail> pager = marketInInfoDetailDao.findList(condition.toString(), page,rows);
		
		return pager;
	}
	
	
}
