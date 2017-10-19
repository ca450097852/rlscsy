package com.hontek.review.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.review.pojo.TbProcess;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProcessServiceInter;

/**
 * <p>Title: 加工包装</p>
 * <p>Description: 加工包装ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProcessAction extends BaseAction{
	private ProcessServiceInter processService;
	private List<TbTraceAppdix> traceAppList;
	private TbProcess process;
	
	private String ids;
	
	public TbProcess getProcess() {
		return process;
	}

	public void setProcess(TbProcess process) {
		this.process = process;
	}

	public void setProcessService(ProcessServiceInter processService) {
		this.processService = processService;
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

	public void findProcessList(){
		printJsonString(processService.findProcessList(process,page,rows,sort,order));
	}
	
	/**
	 * 根据ID删除加工信息
	 */
	public void deleteProcessByIds(){
		printJsonString(this.processService.deleteProcessByIds(ids));
	}
	/**
	 * 添加加工信息
	 */
	public void addProcess(){
//		process = new TbProcess();
//		process.setProcessUser("张老师");
//		process.setProcessAddr("广州");
//		process.setProcessCompany("汉唐");
//		process.setProcessTime(DateUtil.formatDate());
//		process.setProId(2143);
		printJsonString(this.processService.addProcess(process,traceAppList));
	}
	/**
	 * 修改
	 */
	public void updateProcess(){
		printJsonString(this.processService.updateProcess(process,traceAppList));
	}
	
}
