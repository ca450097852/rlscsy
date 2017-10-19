package com.hontek.jcmanager.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.MarketInInfoBaseDao;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.service.inter.MarketInInfoBaseServiceInter;
import com.hontek.record.pojo.TbRecord;

public class MarketInInfoBaseServiceImpl extends BaseServiceImpl implements MarketInInfoBaseServiceInter{
	private MarketInInfoBaseDao marketInInfoBaseDao;

	public void setMarketInInfoBaseDao(MarketInInfoBaseDao marketInInfoBaseDao) {
		this.marketInInfoBaseDao = marketInInfoBaseDao;
	}

	public String addMarketInInfoBase(MarketInInfoBase marketInInfoBase) {
		String msg = "添加成功";
		try {
			if(marketInInfoBase!=null){
				marketInInfoBase.setMiibId(marketInInfoBaseDao.getTableSequence("tb_market_in_info_base".toUpperCase()));
				marketInInfoBase.setCreateTime(DateUtil.formatDateTime());
				marketInInfoBaseDao.save(marketInInfoBase);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateMarketInInfoBase(MarketInInfoBase marketInInfoBase) {
		String msg = "修改成功";
		try {
			if(marketInInfoBase!=null)
				marketInInfoBaseDao.update(marketInInfoBase);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public String daleteMarketInInfoBase(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				marketInInfoBaseDao.executeHql("delete from MarketInInfoBase where miibId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<MarketInInfoBase> findList(MarketInInfoBase marketInInfoBase,
			int page, int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(marketInInfoBase!=null){
			if(marketInInfoBase.getComId()!=0){
				condition.append(" and comId="+marketInInfoBase.getComId());
			}
			if(marketInInfoBase.getFlag()!=null && !"".equals(marketInInfoBase.getFlag())){
				condition.append(" and flag='"+marketInInfoBase.getFlag()+"' ");
			}
			if(marketInInfoBase.getQuarantineAnimalProductsId()!=null && !"".equals(marketInInfoBase.getQuarantineAnimalProductsId())){
				condition.append(" and quarantineAnimalProductsId='"+marketInInfoBase.getQuarantineAnimalProductsId()+"' ");
			}
			if(marketInInfoBase.getPtbId()!=0){
				condition.append(" and ptbId="+marketInInfoBase.getPtbId());
			}
		}
		
		condition.append(sortCondtion(sort, order));
		Pager<MarketInInfoBase> pager = marketInInfoBaseDao.findList(condition.toString(),page,rows);
		return pager;
	}
	
	
	public MarketInInfoBase findByJyh(MarketInInfoBase marketInInfoBase){
		if(marketInInfoBase!=null&&!"".equals(marketInInfoBase.getQuarantineAnimalProductsId())&&!"".equals(marketInInfoBase.getFlag())){
			List<MarketInInfoBase> list = marketInInfoBaseDao.find(" from MarketInInfoBase where quarantineAnimalProductsId = '"+marketInInfoBase.getQuarantineAnimalProductsId()+"' and flag = "+marketInInfoBase.getFlag());
			if(list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
		}else{
			return null;
		}
		
	}
}
