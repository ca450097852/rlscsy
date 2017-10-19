package com.hontek.company.service.inter;

import com.hontek.company.pojo.AssessDetail;

/**
 * 企业考核明细表
 * @author chenan
 *
 */
public interface AssessDetailServiceInter {
	//增加 企业考核明细
	String addAssessDetail(AssessDetail assessDetail);
	//更新  企业考核明细
	String updateAssessDetail(int id,String checkAudit);
	//删除  企业考核明细
	String deleteAssessDetailByCaId(String ids);

}
