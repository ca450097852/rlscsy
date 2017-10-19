package com.hontek.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.EvaluationDao;
import com.hontek.company.pojo.Evaluation;
import com.hontek.company.service.inter.EvaluationServiceInter;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 信用评价信息表</p>
 * <p>Description: 信用评价信息接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EvaluationServiceImpl extends BaseServiceImpl implements EvaluationServiceInter {

	private EvaluationDao evaluationDao;
	
	Logger logger = Logger.getLogger(EvaluationServiceImpl.class);
	
	public void setEvaluationDao(EvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}


	/**
	 * 添加信用评价记录
	 */
	public String addEvaluation(Evaluation evaluation,TsUser tsUser) {
		String msg = "添加评价成功!";
		try {
			if(evaluation.getEvalId()<=0){
				evaluation.setEvalId(evaluationDao.getTableSequence("tb_evaluation".toUpperCase()));
				evaluation.setEntId(tsUser.getEntId());
				evaluation.setCompanyTime(DateUtil.formatDate());
				evaluationDao.save(evaluation);	
			}else{
				updateEvaluation(evaluation);
			}
								
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteEvaluation(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Evaluation evaluation = evaluationDao.get(Evaluation.class, Integer.valueOf(idArray[i]));
					if(evaluation!=null){
						evaluationDao.delete(evaluation);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询信用评价记录
	 */
	public String findEvaluationPagerList(Evaluation evaluation,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(evaluation!=null){	
				//监管机构
				int entId = evaluation.getEntId();
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId+" ");
				}
				
				//被信用评价企业
				int companyId = evaluation.getCompanyId();
				if(companyId>0){
					condition.append(" and companyId = "+companyId+" ");
				}
			}		
			//添加排序
			String defalutSort = " evalTime desc ";
			condition.append(sortCondtion(sort, order,defalutSort));		
		
			Pager<Evaluation>  pager = evaluationDao.findPager(Evaluation.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询监管记录出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateEvaluation(Evaluation evaluation) {
		String msg = "修改成功!";
		try {
			
			Evaluation evaluationOld = evaluationDao.get(Evaluation.class, evaluation.getEvalId());

			evaluationOld.setEvalContents(evaluation.getEvalContents());
			evaluationOld.setEvalTime(evaluation.getEvalTime());
			
			evaluationDao.update(evaluationOld);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 企业回复信用评价信息
	 * @param evaluation
	 * @return
	 */
	public String updateEvaluationByCompany(Evaluation evaluation){
		String msg = "回复信用评价信息成功!";
		try {
			
			Evaluation evaluationOld = evaluationDao.get(Evaluation.class, evaluation.getEvalId());

			evaluationOld.setCompanyContents(evaluation.getCompanyContents());
			evaluationOld.setCompanyTime(evaluation.getCompanyTime());
			
			evaluationDao.update(evaluationOld);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "回复信用评价信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	
	}
	
	
	public String findEvaluationListById(String id){
		String hql = "from Evaluation where evalId="+id;
		List<Evaluation>  list = evaluationDao.find(hql);
		return getJSON(list);
	}
	
	public String findEvaluationList(String entCode){
		List<Evaluation>  list = evaluationDao.findEvaluationList(entCode);
		return getJSON(list);
	}


	public String updateEvaluationCompany(Evaluation evaluation) {
		String msg = "修改成功!";
		try {
			evaluation.setCompanyTime(DateUtil.formatDateTime());
			evaluationDao.update(evaluation);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
}
