package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Evaluation;
import com.hontek.company.service.inter.EvaluationServiceInter;

/**
 * <p>Title: 信用评价信息表</p>
 * <p>Description: 信用评价信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EvaluationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EvaluationServiceInter evaluationServiceInter;	
	private Evaluation evaluation;
	private String ids;	
	
	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setEvaluationServiceInter(EvaluationServiceInter evaluationServiceInter) {
		this.evaluationServiceInter = evaluationServiceInter;
	}

	/**
	 * 添加信用评价信息
	 */
	public void addEvaluation(){
		jsonMsg  = evaluationServiceInter.addEvaluation(evaluation,getLoginTsUser());
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改信用评价信息
	 */
	public void updateEvaluation(){
		jsonMsg  = evaluationServiceInter.updateEvaluation(evaluation);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改信用评价信息
	 */
	public void updateEvaluationCompany(){
		jsonMsg  = evaluationServiceInter.updateEvaluationCompany(evaluation);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除信用评价信息
	 */
	public void deleteEvaluation(){
		jsonMsg  = evaluationServiceInter.deleteEvaluation(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询信用评价信息
	 */
	public void findEvaluationPagerList(){
		jsonMsg  = evaluationServiceInter.findEvaluationPagerList(evaluation, page, rows, sort , order);
		printJsonString(jsonMsg);
	}

	public void findEvaluationList(){
		jsonMsg  = evaluationServiceInter.findEvaluationList(ids);
		printJsonString(jsonMsg);
	}
	
}
