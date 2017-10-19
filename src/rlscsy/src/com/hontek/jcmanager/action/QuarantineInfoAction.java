package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.QuarantineInfo;
import com.hontek.jcmanager.service.inter.QuarantineInfoServiceInter;

/**
 * 屠宰厂检疫检验信息表
 * 
 * @author Administrator
 * 
 */
public class QuarantineInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private QuarantineInfoServiceInter quarantineInfoService;
	private QuarantineInfo quarantineInfo;
	private String ids;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public QuarantineInfoServiceInter getQuarantineInfoService() {
		return quarantineInfoService;
	}

	public void setQuarantineInfoService(QuarantineInfoServiceInter quarantineInfoService) {
		this.quarantineInfoService = quarantineInfoService;
	}

	public QuarantineInfo getQuarantineInfo() {
		return quarantineInfo;
	}

	public void setQuarantineInfo(QuarantineInfo quarantineInfo) {
		this.quarantineInfo = quarantineInfo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void add() {
		this.printJsonString(quarantineInfoService.addQuarantineInfo(quarantineInfo));
	}

	public void update() {
		this.printJsonString(quarantineInfoService.updateQuarantineInfo(quarantineInfo));
	}

	public void delete() {
		this.printJsonString(quarantineInfoService.deleteQuarantineInfo(ids));
	}

	public void findList() {
		Pager<QuarantineInfo> pager = quarantineInfoService.findList(quarantineInfo, page, rows, sort, order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
	
	public void findByJyh() {
		QuarantineInfo qinfo = quarantineInfoService.findByJyh(quarantineInfo.getQuarantineAnimalProductsId());
		this.printJsonString(this.getJSON(qinfo));
	}
	
	public void getQuarantineInfoByCode(){
		QuarantineInfo qinfo = quarantineInfoService.getQuarantineInfoByCode(code);
		this.printJsonString(this.getObjectJSON(qinfo));
	}
}
