package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.service.inter.ProductServiceInter;
/**
 * 
 * <p>Title: 产品展示 </p>
 * <p>Description: 产品展示 Action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WebProductAction extends BaseAction{
	
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
	
	
	/**
	 * 查找企业产品列表
	 * @param true:是超管;0:超管ent_id
	 * 
	 * @return
	 */
	public String findCompanyProList(){
		Pager<TbProduct> pager = this.productService.findProductList(this.product,this.page,this.rows,true,0,this.order,this.sort);
		jsonMsg = this.createEasyUiJson(pager);
		this.printJsonString(jsonMsg);
		return null;
	}
	
	public void findAnimalProductList(){
		jsonMsg = this.productService.findAnimalProductList(this.page,this.rows);
		this.printJsonString(jsonMsg);
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
	
	
}
