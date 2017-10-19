package com.hontek.weixin.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.service.inter.TraceServiceInter;
/**
 * <p>Title: 溯源信息 Action</p>
 * <p>Description: 微信查询溯源信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author zh
 * @version 1.0
 */
public class TraceAction extends BaseAction {

	private String qrCode;
	private TraceServiceInter traceService;
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public TraceServiceInter getTraceService() {
		return traceService;
	}
	public void setTraceService(TraceServiceInter traceService) {
		this.traceService = traceService;
	}
	
	/**
	 * 微信公众平台溯源查询
	 */
	public void findTrace(){
		if(qrCode!=null&&!"".equals(qrCode)){
			printJsonString(traceService.findTrace(qrCode));		
		}
	}
	
	
}
