package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.service.inter.ZizhiTypeServiceInter;
/**
 * 资质类型Action
 * @author lzk
 *
 */
public class ZizhiTypeAction extends BaseAction{
	private ZizhiTypeServiceInter zizhiTypeService;

	public void setZizhiTypeService(ZizhiTypeServiceInter zizhiTypeService) {
		this.zizhiTypeService = zizhiTypeService;
	}

	
}
