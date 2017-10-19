package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MeatOutInfoDetail;
import com.hontek.jcmanager.service.inter.MeatOutInfoDetailServiceInter;

public class MeatOutInfoDetailAction extends BaseAction{
	private MeatOutInfoDetailServiceInter meatOutInfoDetailService;
	private MeatOutInfoDetail meatOutInfoDetail;
	private String ids;
	public MeatOutInfoDetailServiceInter getMeatOutInfoDetailService() {
		return meatOutInfoDetailService;
	}
	public void setMeatOutInfoDetailService(
			MeatOutInfoDetailServiceInter meatOutInfoDetailService) {
		this.meatOutInfoDetailService = meatOutInfoDetailService;
	}
	public MeatOutInfoDetail getMeatOutInfoDetail() {
		return meatOutInfoDetail;
	}
	public void setMeatOutInfoDetail(MeatOutInfoDetail meatOutInfoDetail) {
		this.meatOutInfoDetail = meatOutInfoDetail;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(meatOutInfoDetailService.addMeatOutInfoDetail(meatOutInfoDetail));
	}
	
	public void update(){
		this.printJsonString(meatOutInfoDetailService.updateMeatOutInfoDetail(meatOutInfoDetail));
	}
	
	public void delete(){
		this.printJsonString(meatOutInfoDetailService.deleteMeatOutInfoDetail(ids));
	}
	
	public void findList(){
		Pager<MeatOutInfoDetail> pager = meatOutInfoDetailService.findList(meatOutInfoDetail,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
}
