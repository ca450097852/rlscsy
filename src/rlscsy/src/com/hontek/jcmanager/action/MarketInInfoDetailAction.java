package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoDetail;
import com.hontek.jcmanager.service.inter.MarketInInfoDetailServiceInter;

public class MarketInInfoDetailAction extends BaseAction{
	
	private MarketInInfoDetailServiceInter marketInInfoDetailService;
	private MarketInInfoDetail marketInInfoDetail;
	private String ids;
	public MarketInInfoDetailServiceInter getMarketInInfoDetailService() {
		return marketInInfoDetailService;
	}
	public void setMarketInInfoDetailService(
			MarketInInfoDetailServiceInter marketInInfoDetailService) {
		this.marketInInfoDetailService = marketInInfoDetailService;
	}
	public MarketInInfoDetail getMarketInInfoDetail() {
		return marketInInfoDetail;
	}
	public void setMarketInInfoDetail(MarketInInfoDetail marketInInfoDetail) {
		this.marketInInfoDetail = marketInInfoDetail;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(marketInInfoDetailService.addMarketInInfoDetail(marketInInfoDetail));
	}
	
	public void update(){
		this.printJsonString(marketInInfoDetailService.updateMarketInInfoDetail(marketInInfoDetail));
	}
	
	public void delete(){
		this.printJsonString(marketInInfoDetailService.deleteMarketInInfoDetail(ids));
	}
	
	public void findList(){
		Pager<MarketInInfoDetail> pager = marketInInfoDetailService.findList(marketInInfoDetail,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
}
