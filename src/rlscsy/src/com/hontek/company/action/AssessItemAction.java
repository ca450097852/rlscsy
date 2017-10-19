package com.hontek.company.action;


import org.apache.log4j.Logger;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.AssessItem;
import com.hontek.company.service.inter.AssessItemServiceInter;
import com.hontek.review.action.ProTypeAction;

/**
 * <p>Title: 考核项目表</p>
 * <p>Description: 考核项目Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author chenan
 * @version 1.0
 */
public class AssessItemAction extends BaseAction {
	

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ProTypeAction.class);
	
	private AssessItemServiceInter assessItemServiceInter;
	
	private AssessItem assessItem;
	private String ids;
	private String nodeType;


	/**
	 * 分页查找考核项目
	 */
	public void findAssessItemList(){
		jsonMsg = assessItemServiceInter.findAssessItemList(assessItem, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 分页根据企业类型查找考核项目
	 */
	public void findAssessItemListByNodeType(){
		jsonMsg = assessItemServiceInter.findAssessItemListByNodeType(assessItem,nodeType, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加考核项目
	 */
	public void addAssessItem(){
		jsonMsg = assessItemServiceInter.addAssessItem(assessItem);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除查找考核项目
	 */
	public void deleteAssessItem(){
		jsonMsg = assessItemServiceInter.deleteAssessItem(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 更新查找考核项目
	 */
	public void updateAssessItem(){
		jsonMsg = assessItemServiceInter.updateAssessItem(assessItem);
		printJsonString(jsonMsg);
	}
	
	
	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setAssessItemServiceInter(
			AssessItemServiceInter assessItemServiceInter) {
		this.assessItemServiceInter = assessItemServiceInter;
	}
	
	public AssessItem getAssessItem() {
		return assessItem;
	}

	public void setAssessItem(AssessItem assessItem) {
		this.assessItem = assessItem;
	}
	
	

	
}
