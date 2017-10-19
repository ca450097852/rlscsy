package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.service.inter.InfoServiceInter;
import com.hontek.sys.pojo.EntStyle;
/**
 * 
 * <p>Title: 前台资讯信息 Action</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WebInfoAction extends BaseAction{
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private InfoServiceInter infoService;
	private TInfo info;
	
	
	public InfoServiceInter getInfoService() {
		return infoService;
	}
	public void setInfoService(InfoServiceInter infoService) {
		this.infoService = infoService;
	}
	public TInfo getInfo() {
		return info;
	}
	public void setInfo(TInfo info) {
		this.info = info;
	}
	/**
	 * 资讯列表
	* <p>Title: findNewsList</p> 
	* <p>Description: </p> 
	* param 
	* return
	 */
	public void findNewsList(){
		if(info==null){
			info = new TInfo();
		}
		Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			int entid = entStyle.getEntId()==177792?0:entStyle.getEntId();
			info.setEntId(entid*1l);
		}
		
		printJsonString(infoService.findInfoList(info, this.page, this.rows, "", ""));
	}


}
