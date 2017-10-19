package com.hontek.weixin.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.service.inter.InfoServiceInter;
/**
 * <p>Title: 资讯信息 Action</p>
 * <p>Description: 微信查询通知公告信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author zh
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InfoAction extends BaseAction{
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
		printJsonString(infoService.findInfoList(info, this.page, this.rows, "", ""));
	}


}
