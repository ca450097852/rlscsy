package com.hontek.review.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbTrace;
import com.hontek.review.service.inter.TraceServiceInter;
/**
 * <p>Title: 溯源信息</p>
 * <p>Description: 溯源信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TraceAction extends BaseAction{
	private TraceServiceInter traceService;
	private TbTrace trace;
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbTrace getTrace() {
		return trace;
	}

	public void setTrace(TbTrace trace) {
		this.trace = trace;
	}

	public TraceServiceInter getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceServiceInter traceService) {
		this.traceService = traceService;
	}
	/**
	 * 查找溯源信息
	 */
	public void findTraceList(){
		jsonMsg = traceService.findTraceList(trace, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加
	 */
	public void addTrace(){
		jsonMsg = traceService.addTrace(trace);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 修改
	 */
	public void updateTrace(){
		jsonMsg = traceService.updateTrace(trace);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 删除
	 */
	public void deleteTrace(){
		jsonMsg = traceService.deleteTrace(ids);
		printJsonString(jsonMsg);
	}
}
