package com.hontek.review.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProBatch;
import com.hontek.review.service.inter.ProBatchServiceInter;
/**
 * <p>Title: 批次信息</p>
 * <p>Description: 批次信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProBatchAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProBatchServiceInter proBatchServiceInter;
	private TbProBatch proBatch;
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbProBatch getProBatch() {
		return proBatch;
	}

	public void setProBatch(TbProBatch trace) {
		this.proBatch = trace;
	}
	public void setProBatchServiceInter(ProBatchServiceInter traceServiceInter) {
		this.proBatchServiceInter = traceServiceInter;
	}
	/**
	 * 查找批次信息
	 */
	public void findProBatchList(){
		jsonMsg = proBatchServiceInter.findProBatchList(proBatch, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加批次
	 */
	public void addProBatch(){
		jsonMsg = proBatchServiceInter.addProBatch(proBatch,getRequest());
		this.printJsonString(jsonMsg);
	}
	/**
	 * 修改批次
	 */
	public void updateProBatch(){
		jsonMsg = proBatchServiceInter.updateProBatch(proBatch);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 审核批次状态
	 */
	public void updateProBatchState(){
		jsonMsg = proBatchServiceInter.updateProBatchState(ids,getLoginUser());
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 删除批次
	 */
	public void deleteProBatch(){
		jsonMsg = proBatchServiceInter.deleteProBatch(ids);
		printJsonString(jsonMsg);
	}
}
