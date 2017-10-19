package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbAgriInput;
import com.hontek.element.service.inter.AgriInputServiceInter;
/**
 * <p>Title: 投入品购买信息</p>
 * <p>Description: 投入品购买信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriInputAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgriInputServiceInter agriInputServiceInter;
	private TbAgriInput agriInput;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbAgriInput getAgriInput() {
		return agriInput;
	}

	public void setAgriInput(TbAgriInput trace) {
		this.agriInput = trace;
	}


	public void setAgriInputServiceInter(AgriInputServiceInter agriInputServiceInter) {
		this.agriInputServiceInter = agriInputServiceInter;
	}

	/**
	 * 查找投入品购买信息
	 */
	public void findAgriInputList(){
		jsonMsg = agriInputServiceInter.findAgriInputList(agriInput, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找投入品名称--下拉选择
	 */
	public void findAgriInputComboList(){
		jsonMsg = agriInputServiceInter.findAgriInputComboList(getLoginUser(),ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加投入品购买
	 */
	public void addAgriInput(){
		jsonMsg = agriInputServiceInter.addAgriInput(agriInput);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改投入品购买
	 */
	public void updateAgriInput(){
		jsonMsg = agriInputServiceInter.updateAgriInput(agriInput);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除投入品购买
	 */
	public void deleteAgriInput(){
		jsonMsg = agriInputServiceInter.deleteAgriInput(ids);
		printJsonString(jsonMsg);
	}
}
