package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Useguide;
import com.hontek.company.service.inter.UseguideServiceInter;

/**
 * <p>Title: 操作指引信息表</p>
 * <p>Description: 操作指引信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class UseguideAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UseguideServiceInter useguideServiceInter;	
	private Useguide useguide;
	private String ids;	
	
	public Useguide getUseguide() {
		return useguide;
	}

	public void setUseguide(Useguide useguide) {
		this.useguide = useguide;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setUseguideServiceInter(UseguideServiceInter useguideServiceInter) {
		this.useguideServiceInter = useguideServiceInter;
	}

	/**
	 * 添加操作指引信息
	 */
	public void addUseguide(){
		jsonMsg  = useguideServiceInter.addUseguide(useguide);
		printJsonString(jsonMsg);
	}	
	
	/**
	 * 修改操作指引信息
	 */
	public void updateUseguide(){
		jsonMsg  = useguideServiceInter.updateUseguide(useguide);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除操作指引信息
	 */
	public void deleteUseguide(){
		jsonMsg  = useguideServiceInter.deleteUseguide(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询操作指引信息
	 */
	public void findUseguidePagerList(){
		jsonMsg  = useguideServiceInter.findUseguidePagerList(useguide, page, rows, sort , order);
		printJsonString(jsonMsg);
	}

	
	public void findUseguideList(){
		jsonMsg  = useguideServiceInter.findUseguideList(useguide);
		printJsonString(jsonMsg);
	}
	
}
