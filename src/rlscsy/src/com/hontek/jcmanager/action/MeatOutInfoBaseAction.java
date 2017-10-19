package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Company;
import com.hontek.jcmanager.pojo.MeatOutInfoBase;
import com.hontek.jcmanager.service.inter.MeatOutInfoBaseServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

public class MeatOutInfoBaseAction extends BaseAction{
	private MeatOutInfoBaseServiceInter meatOutInfoBaseService;
	private MeatOutInfoBase meatOutInfoBase;
	private String ids;
	private TsEnterprise enterprise = new TsEnterprise();
	
	public TsEnterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}
	public MeatOutInfoBaseServiceInter getMeatOutInfoBaseService() {
		return meatOutInfoBaseService;
	}
	public void setMeatOutInfoBaseService(
			MeatOutInfoBaseServiceInter meatOutInfoBaseService) {
		this.meatOutInfoBaseService = meatOutInfoBaseService;
	}
	public MeatOutInfoBase getMeatOutInfoBase() {
		return meatOutInfoBase;
	}
	public void setMeatOutInfoBase(MeatOutInfoBase meatOutInfoBase) {
		this.meatOutInfoBase = meatOutInfoBase;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(meatOutInfoBaseService.addMeatOutInfoBase(meatOutInfoBase));
	}
	
	public void update(){
		this.printJsonString(meatOutInfoBaseService.updateMeatOutInfoBase(meatOutInfoBase));
	}
	
	public void delete(){
		this.printJsonString(meatOutInfoBaseService.deleteMeatOutInfoBase(ids));
	}
	
	public void findList(){
		Pager<MeatOutInfoBase> pager = meatOutInfoBaseService.findList(meatOutInfoBase,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
	/**
	 * 销售量统计
	 */
	public void getMarketList(){
		printJsonString(meatOutInfoBaseService.getMarketList(enterprise));
	}
	/**
	 * 企业销售量统计
	 */
	public void companyMarketList(){
		printJsonString(meatOutInfoBaseService.getMarketList(enterprise));
	}
	
	/**
	 * 获取交易凭证号
	 */
	public void getTranId(){
		Company loginCompany = (Company)getSession().getAttribute("loginCompany");
		String comCode = loginCompany.getComCode();
		printJsonString(meatOutInfoBaseService.getTranId(comCode));
		
	}
	
}
