package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.service.inter.ProductAppendixServiceInter;
/**
 * <p>Title: 产品图片</p>
 * <p>Description: 产品分图片</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WebProductAppendixAction extends BaseAction {
	private ProductAppendixServiceInter productAppendixService;
	private TbProductAppendix productAppend;
	
	/**
	 * 产品图片列表
	* <p>Title: findList</p> 
	* <p>Description: </p> 
	* param 
	* return
	 */
	public void findProAppList(){
		jsonMsg = "";
		if(this.productAppend==null){
			this.productAppend = new TbProductAppendix();
		}
		System.out.println("productAppend.proId=="+this.productAppend.getProId());
		jsonMsg = this.productAppendixService.findProductAppList(this.productAppend,page,rows);
		printJsonString(jsonMsg);
	}

	public TbProductAppendix getProductAppend() {
		return productAppend;
	}

	public void setProductAppend(TbProductAppendix productAppend) {
		this.productAppend = productAppend;
	}

	public void setProductAppendixService(
			ProductAppendixServiceInter productAppendixService) {
		this.productAppendixService = productAppendixService;
	}



}
