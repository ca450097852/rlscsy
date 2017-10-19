package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.QmarketDetectionInfo;
import com.hontek.jcmanager.service.inter.QmarketDetectionInfoServiceInter;

public class QmarketDetectionInfoAction extends BaseAction{
	private QmarketDetectionInfoServiceInter qmarketDetectionInfoService;
	private QmarketDetectionInfo qmarketDetectionInfo;
	private String ids;
	public QmarketDetectionInfoServiceInter getQmarketDetectionInfoService() {
		return qmarketDetectionInfoService;
	}
	public void setQmarketDetectionInfoService(
			QmarketDetectionInfoServiceInter qmarketDetectionInfoService) {
		this.qmarketDetectionInfoService = qmarketDetectionInfoService;
	}
	public QmarketDetectionInfo getQmarketDetectionInfo() {
		return qmarketDetectionInfo;
	}
	public void setQmarketDetectionInfo(QmarketDetectionInfo qmarketDetectionInfo) {
		this.qmarketDetectionInfo = qmarketDetectionInfo;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		this.printJsonString(qmarketDetectionInfoService.addQmarketDetectionInfo(qmarketDetectionInfo));
	}
	
	public void update(){
		this.printJsonString(qmarketDetectionInfoService.updateQmarketDetectionInfo(qmarketDetectionInfo));
	}
	
	public void delete(){
		this.printJsonString(qmarketDetectionInfoService.deleteQmarketDetectionInfo(ids));
	}
	
	public void findList(){
		Pager<QmarketDetectionInfo> pager = qmarketDetectionInfoService.findList(qmarketDetectionInfo,page,rows,sort,order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
}
