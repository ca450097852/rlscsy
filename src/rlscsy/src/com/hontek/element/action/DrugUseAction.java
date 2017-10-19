package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.DrugUse;
import com.hontek.element.service.inter.DrugUseServiceInter;
/**
 * <p>Title: 投入品使用信息</p>
 * <p>Description: 投入品使用信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class DrugUseAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrugUseServiceInter drugUseServiceInter;
	private DrugUse DrugUse;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public DrugUse getDrugUse() {
		return DrugUse;
	}

	public void setDrugUse(DrugUse trace) {
		this.DrugUse = trace;
	}


	public void setDrugUseServiceInter(DrugUseServiceInter drugUseServiceInter) {
		this.drugUseServiceInter = drugUseServiceInter;
	}

	/**
	 * 查找投入品使用信息
	 */
	public void findDrugUseList(){
		jsonMsg = drugUseServiceInter.findDrugUseList(DrugUse, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加投入品使用
	 */
	public void addDrugUse(){
		jsonMsg = drugUseServiceInter.addDrugUse(DrugUse);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改投入品使用
	 */
	public void updateDrugUse(){
		jsonMsg = drugUseServiceInter.updateDrugUse(DrugUse);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除投入品使用
	 */
	public void deleteDrugUse(){
		jsonMsg = drugUseServiceInter.deleteDrugUse(ids);
		printJsonString(jsonMsg);
	}
}
