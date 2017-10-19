package com.hontek.agri.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.agri.pojo.TbWarning;
import com.hontek.agri.service.inter.WarningServiceInter;
/**
 * <p>Title: 预警信息</p>
 * <p>Description: 预警信息Action实现类</p>
 * @author qql
 * @version 1.0
 */
public class WarningAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WarningServiceInter warningService;
	private TbWarning warning;
	private String ids;
	private String state;
	


	public TbWarning getWarning() {
		return warning;
	}


	public void setWarning(TbWarning warning) {
		this.warning = warning;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setWarningService(WarningServiceInter warningService) {
		this.warningService = warningService;
	}


	/**
	 * 查找预警信息
	 */
	public void findWarningList(){
		jsonMsg = warningService.findWarningList(warning, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找预警信息
	 */
	public void findWaringEntList(){
		jsonMsg = warningService.findWaringEntList(warning, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加预警信息
	 */
	public void addWarning(){
		jsonMsg = warningService.addWarning(warning);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改预警信息
	 */
	public void updateWarning(){
		jsonMsg = warningService.updateWarning(warning);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除预警信息
	 */
	public void deleteWarning(){
		jsonMsg = warningService.deleteWarning(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改预警信息State
	 */
	public void updateState(){
		jsonMsg = warningService.updateState(ids);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改预警信息State
	 */
	public void updateStates(){
		jsonMsg = warningService.updateStates(ids,state);
		this.printJsonString(jsonMsg);
	}
}
