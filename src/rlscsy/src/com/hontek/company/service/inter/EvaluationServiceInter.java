package com.hontek.company.service.inter;

import com.hontek.company.pojo.Evaluation;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 信用评价信息表</p>
 * <p>Description: 信用评价信息接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface EvaluationServiceInter {

	public String addEvaluation(Evaluation evaluation,TsUser tsUser);
	
	public String deleteEvaluation(String ids);
	
	public String updateEvaluation(Evaluation evaluation);
	
	/**
	 * 企业回复
	 * @param evaluation
	 * @return
	 */
	public String updateEvaluationByCompany(Evaluation evaluation);

	
	public String findEvaluationPagerList(Evaluation evaluation,int page,int rows,String sort,String order);

	public String findEvaluationList(String entCode);
	
	public String findEvaluationListById(String entCode);

	public String updateEvaluationCompany(Evaluation evaluation);


}
