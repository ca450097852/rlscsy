package com.hontek.weixin.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.service.inter.ProductAppendixServiceInter;
import com.hontek.review.service.inter.ProductServiceInter;
/**
 * <p>Title: 产品信息 Action</p>
 * <p>Description: 微信查询产品信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author zh
 * @version 1.0
 */
public class ProductAction extends BaseAction {

	
	private ProductServiceInter productService;
	private TbProduct product;
	/**
	 * 查找产品列表
	 * @param true:是超管;0:超管ent_id
	 * @return
	 */
	public String findList(){
		if(this.product==null){
			this.product = new TbProduct();
		}
		long entId = 0;
		Pager<TbProduct> pager = this.productService.findProductList(this.product,this.page,this.rows,true,entId,this.order,this.sort);
		jsonMsg = this.createEasyUiJson(pager);
		this.printJsonString(jsonMsg);
		return null;
	}
	public TbProduct getProduct() {
		return product;
	}
	public void setProduct(TbProduct product) {
		this.product = product;
	}
	public void setProductService(ProductServiceInter productService) {
		this.productService = productService;
	}
	
	

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
