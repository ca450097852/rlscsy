package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Tongbao;
import com.hontek.company.service.inter.TongbaoServiceInter;

/**
 * <p>Title: 前台 通报企业</p>
 * <p>Description: 通报企业Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class WebTongbaoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TongbaoServiceInter tongbaoServiceInter;	
	private Tongbao tongbao;
	
	public Tongbao getTongbao() {
		return tongbao;
	}

	public void setTongbao(Tongbao tongbao) {
		this.tongbao = tongbao;
	}

	public void setTongbaoServiceInter(TongbaoServiceInter tongbaoServiceInter) {
		this.tongbaoServiceInter = tongbaoServiceInter;
	}

	/**
	 * 查询通报企业
	 */
	public void findList(){
		jsonMsg  = tongbaoServiceInter.findTongbaoPagerList(tongbao, page, rows, sort , order);
		//获取文件夹
		printJsonString(jsonMsg);
	}
	
}
