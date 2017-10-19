package com.hontek.info.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.info.pojo.TInfotemp;
import com.hontek.info.service.inter.InfotempServiceInter;

@SuppressWarnings("serial")
public class InfotempAction extends BaseAction{
	private TInfotemp infotemp ;
	private InfotempServiceInter infotempService;
	
	public TInfotemp getInfotemp() {
		return infotemp;
	}

	public void setInfotemp(TInfotemp infotemp) {
		this.infotemp = infotemp;
	}

	public InfotempServiceInter getInfotempService() {
		return infotempService;
	}

	public void setInfotempService(InfotempServiceInter infotempService) {
		this.infotempService = infotempService;
	}
	
	/**
	 * 临时资讯列表
	 */
	public void findInfotempList(){
		printJsonString(this.infotempService.findInfotempList(infotemp, page, rows, sort, order)); 
	}
	/**
	 * 添加临时资讯
	 */
	public void addInfotemp(){
		infotemp.setCrttime(DateUtil.formatDate());
		printJsonString(this.infotempService.addInfotemp(infotemp));
	}
	/**
	 * 删除临时咨询
	 */
	public void deleteInfotemp(){
		printJsonString(this.infotempService.deleteInfotemp(this.getRequest().getParameter("tIds")));
	}
	/**
	 * 上报临时资讯
	 */
	public void reportInfotemp(){
		printJsonString(this.infotempService.updateReportInfotemp(this.getRequest().getParameter("tIds")));
	}
	/**
	 * 修改临时资讯
	 */
	public void updateInfotemp(){
		printJsonString(this.infotempService.updateInfotemp(infotemp));
	}
}	
