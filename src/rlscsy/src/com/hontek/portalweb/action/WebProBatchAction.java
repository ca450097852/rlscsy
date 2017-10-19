package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.ApplicationContextUtil;
import com.hontek.review.service.inter.ProBatchServiceInter;

public class WebProBatchAction extends BaseAction{
	
	private ProBatchServiceInter proBatchService;
	
	private String dimenno;
	
	public String getDimenno() {
		return dimenno;
	}
	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}
	public void setProBatchService(ProBatchServiceInter proBatchService) {
		this.proBatchService = proBatchService;
	}
	
	public ProBatchServiceInter getProBatchService() {
		return proBatchService;
	}
	/**
	 * 根据二维码查找批次信息
	 */
	public void getProBatch(){
		System.out.println(dimenno+"&&&&&&&&&&&&&&&&&======"+proBatchService);
		proBatchService = (ProBatchServiceInter)ApplicationContextUtil.getContext().getBean("proBatchServiceInter");
		printJsonString(proBatchService.getProBatchByDimenno(dimenno));
	}
	
}
