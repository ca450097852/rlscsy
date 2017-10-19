package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbAgriType;
import com.hontek.element.service.inter.AgriTypeServiceInter;
/**
 * <p>Title: 投入品类别信息</p>
 * <p>Description: 投入品类别信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriTypeAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgriTypeServiceInter agriTypeServiceInter;
	private TbAgriType agriType;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbAgriType getAgriType() {
		return agriType;
	}

	public void setAgriType(TbAgriType trace) {
		this.agriType = trace;
	}


	public void setAgriTypeServiceInter(AgriTypeServiceInter agriTypeServiceInter) {
		this.agriTypeServiceInter = agriTypeServiceInter;
	}

	/**
	 * 查找投入品类别信息
	 */
	public void findAgriTypeList(){
		jsonMsg = agriTypeServiceInter.findAgriTypeList(agriType, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找投入品类别信息
	 */
	public void findAgriTypeComboList(){
		jsonMsg = agriTypeServiceInter.findAgriTypeComboList();
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加投入品类别
	 */
	public void addAgriType(){
		jsonMsg = agriTypeServiceInter.addAgriType(agriType);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改投入品类别
	 */
	public void updateAgriType(){
		jsonMsg = agriTypeServiceInter.updateAgriType(agriType);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除投入品类别
	 */
	public void deleteAgriType(){
		jsonMsg = agriTypeServiceInter.deleteAgriType(ids);
		printJsonString(jsonMsg);
	}
}
