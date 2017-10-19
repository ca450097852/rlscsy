package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.ProcessUse;
import com.hontek.element.service.inter.ProcessUseServiceInter;
import com.hontek.sys.pojo.TsUser;
/**
 * 加工投入品信息表
 * @author IMZK
 *
 */
public class ProcessUseAction extends BaseAction{
	private ProcessUseServiceInter processUseService;
	private ProcessUse processUse;
	private String ids;
	public ProcessUseServiceInter getProcessUseService() {
		return processUseService;
	}
	public void setProcessUseService(ProcessUseServiceInter processUseService) {
		this.processUseService = processUseService;
	}
	public ProcessUse getProcessUse() {
		return processUse;
	}
	public void setProcessUse(ProcessUse processUse) {
		this.processUse = processUse;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		TsUser user = this.getLoginTsUser();
		if(processUse!=null)
			processUse.setUserId(user.getUserId());
		jsonMsg = processUseService.add(processUse);
		this.printJsonString(jsonMsg);
	}
	
	public void update(){
		jsonMsg = processUseService.update(processUse);
		this.printJsonString(jsonMsg);
	}
	
	public void delete(){
		jsonMsg = processUseService.delete(ids);
		this.printJsonString(jsonMsg);
	}
	
	public void findPager(){
		jsonMsg = processUseService.findPager(processUse, page, rows, sort , order);
		this.printJsonString(jsonMsg);
	}
}
