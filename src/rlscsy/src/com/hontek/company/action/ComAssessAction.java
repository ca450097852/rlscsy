package com.hontek.company.action;


import org.apache.log4j.Logger;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.ComAssess;
import com.hontek.company.service.inter.AssessDetailServiceInter;
import com.hontek.company.service.inter.ComAssessServiceInter;
import com.hontek.review.action.ProTypeAction;

/**
 * <p>Title: 企业考核表</p>
 * <p>Description: 考核项目Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author chenan
 * @version 1.0
 */
public class ComAssessAction extends BaseAction {
	

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ProTypeAction.class);
	
	private ComAssessServiceInter comAssessServiceInter;
	private AssessDetailServiceInter assessDetailServiceInter;
	
	private ComAssess comAssess;
	private String caId;
	private String ids;

	/**
	 * 分页查找企业考核项目
	 */
	public void findComAssessList(){
		jsonMsg = comAssessServiceInter.findComAssessList(comAssess, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 根据id查找企业考核项目
	 */
	public void findComAssessListByCaId(){
		jsonMsg = comAssessServiceInter.findComAssessListByCaId(comAssess, caId, page, rows, sort, order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除企业考核项目
	 */
	public void deleteComassess(){
		//先删除企业考核表内的数据
		jsonMsg = comAssessServiceInter.deleteComAssess(ids);
		//再删除企业考核明细表的数据
		assessDetailServiceInter.deleteAssessDetailByCaId(ids);
		printJsonString(jsonMsg);
	}

	public String getCaId() {
		return caId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setCaId(String caId) {
		this.caId = caId;
	}
	
	public ComAssess getComAssess() {
		return comAssess;
	}

	public ComAssessServiceInter getComAssessServiceInter() {
		return comAssessServiceInter;
	}

	public void setComAssessServiceInter(ComAssessServiceInter comAssessServiceInter) {
		this.comAssessServiceInter = comAssessServiceInter;
	}

	public void setComAssess(ComAssess comAssess) {
		this.comAssess = comAssess;
	}

	public AssessDetailServiceInter getAssessDetailServiceInter() {
		return assessDetailServiceInter;
	}

	public void setAssessDetailServiceInter(AssessDetailServiceInter assessDetailServiceInter) {
		this.assessDetailServiceInter = assessDetailServiceInter;
	}
	
	

	
}
