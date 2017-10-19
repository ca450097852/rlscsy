package com.hontek.company.action;

import org.apache.log4j.Logger;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.AssessDetail;
import com.hontek.company.pojo.ComAssess;
import com.hontek.company.service.inter.AssessDetailServiceInter;
import com.hontek.company.service.inter.ComAssessServiceInter;
import com.hontek.review.action.ProTypeAction;

/**
 * <p>
 * Title: 企业考核明细表
 * </p>
 * <p>
 * Description: 考核项目Action 类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: **公司
 * </p>
 * 
 * @author chenan
 * @version 1.0
 */
public class AssessDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ProTypeAction.class);

	private AssessDetailServiceInter assessDetailServiceInter;
	private ComAssessServiceInter comAssessServiceInter;
	private AssessDetail assessDetail;
	private ComAssess comAssess;
	private String itemIds;
	private String checkSelf;
	private String checkAudit;
	private String cadId;
	private String caId;
	private String tanwei2 = "";
	private String suyuanchen2 = "";
	private String lingsou2 = "";

	private String nodeType;
	
	/**
	 * 添加企业考核明细
	 */
	public void addAssessDetail() {
		jsonMsg = "添加成功";
		int caId = comAssessServiceInter.addComAssess(comAssess);//先添加进企业考核表
		String[] ids = itemIds.split(","); //考核项目id
		String[] chSelf = checkSelf.split(","); //是否已自测
		if(itemIds.length()!=0){
			for (int i = 0; i < ids.length; i++) {
				AssessDetail assessDetail = new AssessDetail();
				assessDetail.setCaId(caId);
				assessDetail.setItemId(Integer.parseInt(ids[i]));
				if(checkSelf.length()!=0){
					assessDetail.setCheckSelf(chSelf[i]);
				}
				jsonMsg = assessDetailServiceInter.addAssessDetail(assessDetail);
			}
		}
	
		printJsonString(jsonMsg);
	}

	/**
	 * 修改企业考核明细
	 */
	public void updateAssessDetail() {
		String[] ids = cadId.split(",");
		String[] chAudit = checkAudit.split(",");
		String[] caid = caId.split(",");
		//根据考核项目id更新对应的是否自测
		for (int i = 0; i < ids.length; i++) {
			if(cadId.length()!=0&&!ids[i].equals("0")){
				jsonMsg = assessDetailServiceInter.updateAssessDetail(
						Integer.parseInt(ids[i]), chAudit[i]);
			}
		}
		//根据caid获取当前ComAssess对象，再把考核的属性存进该对象
		for (int i = 0; i < caid.length; i++) {
			jsonMsg=comAssessServiceInter.updateComAssess(
						Integer.parseInt(caid[i]), tanwei2, suyuanchen2,
						lingsou2);
			}
		printJsonString(jsonMsg);
	}

	public String getLingsou2() {
		return lingsou2;
	}

	public void setLingsou2(String lingsou2) {
		this.lingsou2 = lingsou2;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getCheckSelf() {
		return checkSelf;
	}

	public void setCheckSelf(String checkSelf) {
		this.checkSelf = checkSelf;
	}

	public String getCaId() {
		return caId;
	}

	public void setCaId(String caId) {
		this.caId = caId;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public AssessDetailServiceInter getAssessDetailServiceInter() {
		return assessDetailServiceInter;
	}

	public void setAssessDetailServiceInter(
			AssessDetailServiceInter assessDetailServiceInter) {
		this.assessDetailServiceInter = assessDetailServiceInter;
	}

	public ComAssessServiceInter getComAssessServiceInter() {
		return comAssessServiceInter;
	}

	public void setComAssessServiceInter(
			ComAssessServiceInter comAssessServiceInter) {
		this.comAssessServiceInter = comAssessServiceInter;
	}

	public AssessDetail getAssessDetail() {
		return assessDetail;
	}

	public void setAssessDetail(AssessDetail assessDetail) {
		this.assessDetail = assessDetail;
	}

	public ComAssess getComAssess() {
		return comAssess;
	}

	public void setComAssess(ComAssess comAssess) {
		this.comAssess = comAssess;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getTanwei2() {
		return tanwei2;
	}

	public void setTanwei2(String tanwei2) {
		this.tanwei2 = tanwei2;
	}

	public String getSuyuanchen2() {
		return suyuanchen2;
	}

	public void setSuyuanchen2(String suyuanchen2) {
		this.suyuanchen2 = suyuanchen2;
	}

	public String getCheckAudit() {
		return checkAudit;
	}

	public void setCheckAudit(String checkAudit) {
		this.checkAudit = checkAudit;
	}

	public String getCadId() {
		return cadId;
	}

	public void setCadId(String cadId) {
		this.cadId = cadId;
	}

}
