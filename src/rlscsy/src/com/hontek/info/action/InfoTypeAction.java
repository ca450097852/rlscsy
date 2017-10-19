package com.hontek.info.action;

import com.hontek.comm.action.BaseAction;


import com.hontek.comm.base.LoginUser;
import com.hontek.comm.util.DateUtil;

import com.hontek.info.pojo.TInfoType;
import com.hontek.info.service.inter.InfoTypeServiceInter;

/**
 * 资讯分类 Action
 * @author qql
 *
 */

@SuppressWarnings("serial")
public class InfoTypeAction extends BaseAction{
	private TInfoType tinfoType;
	private InfoTypeServiceInter infoTypeService;
	private String infoTypes;

	
	/**
	 * 分类列表
	 */
	public void findInfoType(){
		if(this.tinfoType==null){
			this.tinfoType=new TInfoType();
		}
		jsonMsg = this.infoTypeService.findInfoTypeList(this.tinfoType, page, rows, sort, order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 分类下拉
	 * 
	 */
	public void getInfoTypeTree(){
		printJsonString(this.infoTypeService.getInfoTypeTree());
	}
	
	/**
	 * 添加分类
	 */
	public void addInfoType(){
		this.tinfoType.setCrttime(DateUtil.formatDate());
		this.tinfoType.setUserId(getLonginUserId());
		jsonMsg = this.infoTypeService.addInfoType(this.tinfoType);
		printJsonString(jsonMsg);
	}
	/**
	 * 更新分类
	 */
	public void updateInfoType(){
		
		jsonMsg = this.infoTypeService.updateInfoType(this.tinfoType);
		printJsonString(jsonMsg);
	}
	/**
	 * 删除分类
	 */
	public void deleteInfoType(){
		jsonMsg  = this.infoTypeService.deleteInfoType(this.infoTypes);
		printJsonString(jsonMsg);
	}
	
	
	public void getInfoTypeToSelect(){
		printJsonString(infoTypeService.getInfoTypeToSelect());
	}
	
	//getters and setters
	public TInfoType getTinfoType() {
		return tinfoType;
	}
	public void setTinfoType(TInfoType tinfoType) {
		this.tinfoType = tinfoType;
	}
	
	public void setInfoTypeService(InfoTypeServiceInter infoTypeService) {
		this.infoTypeService = infoTypeService;
	}
	public String getInfoTypes() {
		return infoTypes;
	}
	public void setInfoTypes(String infoTypes) {
		this.infoTypes = infoTypes;
	}

}
