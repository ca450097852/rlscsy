package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbAgriUse;
import com.hontek.element.service.inter.AgriUseServiceInter;
/**
 * <p>Title: 投入品使用信息</p>
 * <p>Description: 投入品使用信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriUseAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgriUseServiceInter agriUseServiceInter;
	private TbAgriUse agriUse;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbAgriUse getAgriUse() {
		return agriUse;
	}

	public void setAgriUse(TbAgriUse trace) {
		this.agriUse = trace;
	}


	public void setAgriUseServiceInter(AgriUseServiceInter agriUseServiceInter) {
		this.agriUseServiceInter = agriUseServiceInter;
	}

	/**
	 * 查找投入品使用信息
	 */
	public void findAgriUseList(){
		jsonMsg = agriUseServiceInter.findAgriUseList(agriUse, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加投入品使用
	 */
	public void addAgriUse(){
		jsonMsg = agriUseServiceInter.addAgriUse(agriUse);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改投入品使用
	 */
	public void updateAgriUse(){
		jsonMsg = agriUseServiceInter.updateAgriUse(agriUse);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除投入品使用
	 */
	public void deleteAgriUse(){
		jsonMsg = agriUseServiceInter.deleteAgriUse(ids);
		printJsonString(jsonMsg);
	}
}
