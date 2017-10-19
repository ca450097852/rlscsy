package com.hontek.review.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProCheckServiceInter;

/**
 * <p>Title: 产品检验</p>
 * <p>Description: 产品检验ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProCheckAction extends BaseAction{
	private ProCheckServiceInter proCheckService;
	private TbProCheck proCheck;
	private String ids;
	private List<TbTraceAppdix> traceAppList;

	public void setProCheckService(ProCheckServiceInter proCheckService) {
		this.proCheckService = proCheckService;
	}

	public TbProCheck getProCheck() {
		return proCheck;
	}

	public void setProCheck(TbProCheck proCheck) {
		this.proCheck = proCheck;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public List<TbTraceAppdix> getTraceAppList() {
		return traceAppList;
	}

	public void setTraceAppList(List<TbTraceAppdix> traceAppList) {
		this.traceAppList = traceAppList;
	}

	public void findProCheckList(){
		printJsonString(proCheckService.findProCheckList(proCheck,page,rows,sort,order));
	}
	
	/**
	 * 添加
	 */
	public void addProCheck(){
/*		proCheck = new TbProCheck();
		proCheck.setCheckName("报告名称");
		proCheck.setCheckUnit("单位");
		proCheck.setCheckNum("2222222");
		proCheck.setCheckTime(DateUtil.formatDate());
		proCheck.setProId(2143);
		proCheck.setCheckResult("结果");*/
		printJsonString(proCheckService.addProCheck(proCheck,traceAppList));
	}
	/**
	 * 删除
	 */
	public void delteProCheckByIds(){
		printJsonString(proCheckService.deleteProCheckByIds(ids));
	}
	/**
	 * 修改
	 */
	public void updateProCheck(){
		printJsonString(proCheckService.updateProCheck(proCheck,traceAppList));
	}
	
}
