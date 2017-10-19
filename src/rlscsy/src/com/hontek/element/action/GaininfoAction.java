package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbGaininfo;
import com.hontek.element.service.inter.GaininfoServiceInter;
/**
 * @ClassName: GaininfoAction
 * @Description: TODO(采摘收获信息 Action类)
 * @date 2015-8-10 下午07:43:04
 * @author qql
 * @version 1.0
 */
public class GaininfoAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GaininfoServiceInter gaininfoServiceInter;
	private TbGaininfo gaininfo;
	private String ids;
	

	public TbGaininfo getGaininfo() {
		return gaininfo;
	}
	public void setGaininfo(TbGaininfo gaininfo) {
		this.gaininfo = gaininfo;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setGaininfoServiceInter(GaininfoServiceInter gaininfoServiceInter) {
		this.gaininfoServiceInter = gaininfoServiceInter;
	}
	/**
	 * 查找采摘收获信息
	 */
	public void findGaininfoList(){
		jsonMsg = gaininfoServiceInter.findGaininfoList(gaininfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加采摘收获信息
	 */
	public void addGaininfo(){
		jsonMsg = gaininfoServiceInter.addGaininfo(gaininfo);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改采摘收获信息
	 */
	public void updateGaininfo(){
		jsonMsg = gaininfoServiceInter.updateGaininfo(gaininfo);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除采摘收获信息
	 */
	public void deleteGaininfo(){
		jsonMsg = gaininfoServiceInter.deleteGaininfo(ids);
		printJsonString(jsonMsg);
	}
}
