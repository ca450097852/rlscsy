package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Supervise;
import com.hontek.company.service.inter.SuperviseServiceInter;

/**
 * <p>Title: 监管信息表</p>
 * <p>Description: 监管信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class WebSuperviseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SuperviseServiceInter superviseServiceInter;	
	private Supervise supervise;
	private String ids;	
	
	public Supervise getSupervise() {
		return supervise;
	}

	public void setSupervise(Supervise supervise) {
		this.supervise = supervise;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setSuperviseServiceInter(SuperviseServiceInter superviseServiceInter) {
		this.superviseServiceInter = superviseServiceInter;
	}

	
	/**
	 * 查询监管信息
	 */

	public void findSuperviseListById(){
		jsonMsg  = superviseServiceInter.findSuperviseListById(ids);
		printJsonString(jsonMsg);
	}
	
	public void findSuperviseList(){
		jsonMsg  = superviseServiceInter.findSuperviseList(ids);
		printJsonString(jsonMsg);
	}
	
}
