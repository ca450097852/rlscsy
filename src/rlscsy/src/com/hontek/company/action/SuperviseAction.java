package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Supervise;
import com.hontek.company.service.inter.SuperviseServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title: 监管信息表</p>
 * <p>Description: 监管信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class SuperviseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SuperviseServiceInter superviseServiceInter;	
	private Supervise supervise;
	private TsEnterprise enterprise;
	private String ids;	
	
	public Supervise getSupervise() {
		return supervise;
	}

	public TsEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
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
	 * 添加监管信息
	 */
	public void addSupervise(){
		jsonMsg  = superviseServiceInter.addSupervise(supervise,getLoginTsUser());
		printJsonString(jsonMsg);
	}
	
	public void addSuperviseList(){
		jsonMsg = superviseServiceInter.addSuperviseList(supervise,getLoginTsUser(),enterprise,ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改监管信息
	 */
	public void updateSupervise(){
		jsonMsg  = superviseServiceInter.updateSupervise(supervise);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除监管信息
	 */
	public void deleteSupervise(){
		jsonMsg  = superviseServiceInter.deleteSupervise(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询监管信息
	 */
	public void findSupervisePagerList(){
		jsonMsg  = superviseServiceInter.findSupervisePagerList(supervise, page, rows, sort , order);
		printJsonString(jsonMsg);
	}

	public void findSuperviseList(){
		jsonMsg  = superviseServiceInter.findSuperviseList(ids);
		printJsonString(jsonMsg);
	}
	
}
