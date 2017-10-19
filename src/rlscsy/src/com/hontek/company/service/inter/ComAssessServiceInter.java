package com.hontek.company.service.inter;

import com.hontek.company.pojo.ComAssess;

/**
 * 企业考核表
 * @author chenan
 *
 */
public interface ComAssessServiceInter {
	//增加企业考核项目并返回改企业考核项目的id
	int addComAssess(ComAssess comAssess);
	
	//分页查找
	String findComAssessList(ComAssess comAssess, int page,int rows, String sort, String order);
	
	//根据id查找
	String findComAssessListByCaId(ComAssess comAssess,String caId, int page,int rows, String sort, String order);
	
	//审核修改属性
	String updateComAssess(int id,String tanwei2,String suyuanchen2 , String lingsou2);
	
	//删除
	String deleteComAssess(String ids);

}
