package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbEartag;
import com.hontek.element.service.inter.EartagServiceInter;
/**
 * <p>Title: 耳标信息</p>
 * <p>Description: 耳标信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class EartagAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EartagServiceInter eartagServiceInter;
	private TbEartag eartag;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbEartag getEartag() {
		return eartag;
	}

	public void setEartag(TbEartag trace) {
		this.eartag = trace;
	}


	public void setEartagServiceInter(EartagServiceInter eartagServiceInter) {
		this.eartagServiceInter = eartagServiceInter;
	}

	/**
	 * 查找耳标信息
	 */
	public void findEartagList(){
		jsonMsg = eartagServiceInter.findEartagList(eartag, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加耳标
	 */
	public void addEartag(){
		jsonMsg = eartagServiceInter.addEartag(eartag);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改耳标
	 */
	public void updateEartag(){
		jsonMsg = eartagServiceInter.updateEartag(eartag);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除耳标
	 */
	public void deleteEartag(){
		jsonMsg = eartagServiceInter.deleteEartag(ids);
		printJsonString(jsonMsg);
	}
}
