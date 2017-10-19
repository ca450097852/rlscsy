package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.RetailMarketTranInfoSumm;
import com.hontek.jcmanager.service.inter.RetailMarketTranInfoSummServiceInter;

public class RetailMarketTranInfoSummAction extends BaseAction{
	private RetailMarketTranInfoSummServiceInter retailMarketTranInfoSummService;
	private RetailMarketTranInfoSumm retailMarketTranInfoSumm;
	private String ids;
	public RetailMarketTranInfoSummServiceInter getRetailMarketTranInfoSummService() {
		return retailMarketTranInfoSummService;
	}
	public void setRetailMarketTranInfoSummService(
			RetailMarketTranInfoSummServiceInter retailMarketTranInfoSummService) {
		this.retailMarketTranInfoSummService = retailMarketTranInfoSummService;
	}
	public RetailMarketTranInfoSumm getRetailMarketTranInfoSumm() {
		return retailMarketTranInfoSumm;
	}
	public void setRetailMarketTranInfoSumm(RetailMarketTranInfoSumm retailMarketTranInfoSumm) {
		this.retailMarketTranInfoSumm = retailMarketTranInfoSumm;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(retailMarketTranInfoSummService.addRetailMarketTranInfoSumm(retailMarketTranInfoSumm));
	}
	
	public void update(){
		this.printJsonString(retailMarketTranInfoSummService.updateRetailMarketTranInfoSumm(retailMarketTranInfoSumm));
	}
	
	public void delete(){
		this.printJsonString(retailMarketTranInfoSummService.deleteRetailMarketTranInfoSumm(ids));
	}
	
	public void findList(){
		Pager<RetailMarketTranInfoSumm> pager = retailMarketTranInfoSummService.findList(retailMarketTranInfoSumm,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
}
