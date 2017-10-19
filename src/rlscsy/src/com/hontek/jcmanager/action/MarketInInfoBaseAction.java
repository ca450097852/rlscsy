package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.QuarantineInfo;
import com.hontek.jcmanager.service.inter.MarketInInfoBaseServiceInter;

public class MarketInInfoBaseAction extends BaseAction{
	
	private MarketInInfoBaseServiceInter marketInInfoBaseService;
	private MarketInInfoBase marketInInfoBase;
	private String ids;
	public MarketInInfoBaseServiceInter getMarketInInfoBaseService() {
		return marketInInfoBaseService;
	}
	public void setMarketInInfoBaseService(
			MarketInInfoBaseServiceInter marketInInfoBaseService) {
		this.marketInInfoBaseService = marketInInfoBaseService;
	}
	public MarketInInfoBase getMarketInInfoBase() {
		return marketInInfoBase;
	}
	public void setMarketInInfoBase(MarketInInfoBase marketInInfoBase) {
		this.marketInInfoBase = marketInInfoBase;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(marketInInfoBaseService.addMarketInInfoBase(marketInInfoBase));
	}
	
	public void update(){
		this.printJsonString(marketInInfoBaseService.updateMarketInInfoBase(marketInInfoBase));
	}
	
	public void delete(){
		this.printJsonString(marketInInfoBaseService.daleteMarketInInfoBase(ids));
	}
	
	public void findList(){
		Pager<MarketInInfoBase> pager = marketInInfoBaseService.findList(marketInInfoBase,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
	
	public void findByJyh() {
		this.printJsonString(this.getJSON(marketInInfoBaseService.findByJyh(marketInInfoBase)));
	}
}
