package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbCheckinfo;
import com.hontek.element.service.inter.CheckinfoServiceInter;
/**
 * <p>Title: 检验报告信息</p>
 * <p>Description: 检验报告信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class CheckinfoAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckinfoServiceInter checkinfoServiceInter;
	private TbCheckinfo checkinfo;
	private String ids;
	private String jsonApp;
	

	public String getJsonApp() {
		return jsonApp;
	}

	public void setJsonApp(String jsonApp) {
		this.jsonApp = jsonApp;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbCheckinfo getCheckinfo() {
		return checkinfo;
	}

	public void setCheckinfo(TbCheckinfo trace) {
		this.checkinfo = trace;
	}


	public void setCheckinfoServiceInter(CheckinfoServiceInter checkinfoServiceInter) {
		this.checkinfoServiceInter = checkinfoServiceInter;
	}

	/**
	 * 查找检验报告信息
	 */
	public void findCheckinfoList(){
		jsonMsg = checkinfoServiceInter.findCheckinfoList(checkinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加检验报告
	 */
	public void addCheckinfo(){
		jsonMsg = checkinfoServiceInter.addCheckinfo(checkinfo,jsonApp);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改检验报告
	 */
	public void updateCheckinfo(){
		jsonMsg = checkinfoServiceInter.updateCheckinfo(checkinfo,jsonApp,ids);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除检验报告
	 */
	public void deleteCheckinfo(){
		jsonMsg = checkinfoServiceInter.deleteCheckinfo(ids);
		printJsonString(jsonMsg);
	}
}
