package com.hontek.company.service.inter;

import com.hontek.company.pojo.AssessItem;

/**
 * 考核项目表
 * @author chenan
 *
 */
public interface AssessItemServiceInter {
	//分页查找考核项目
	String findAssessItemList(AssessItem assessItem, int page,int rows, String sort, String order);
	//根据企业类型查找
	String findAssessItemListByNodeType(AssessItem assessItem,String nodeType, int page,int rows, String sort, String order);
	//增加考核项目
	String addAssessItem(AssessItem assessItem);
	//修改考核项目
	String updateAssessItem(AssessItem assessItem);
	//删除考核项目
	String deleteAssessItem(String ids);

}
