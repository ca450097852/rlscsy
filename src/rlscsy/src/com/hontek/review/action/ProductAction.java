package com.hontek.review.action;


import org.apache.log4j.Logger;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.ProductInfo;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.service.inter.ProductServiceInter;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
/**
 * <p>Title: 产品</p>
 * <p>Description: 产品</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductAction extends BaseAction{
	private Logger logger = Logger.getLogger(ProductAction.class);
	
	private ProductServiceInter productServiceInter;
	private EnterpriseServiceInter enterpriseService;
	private ProductInfo productInfo;
	
	private TbProduct product;
	
	private String ids;
	private String state;
	private String dimenno;
	private String entCode;
	
	public String getIds() {
		return ids;
	}


	public void setEnterpriseService(EnterpriseServiceInter enterpriseService) {
		this.enterpriseService = enterpriseService;
	}


	public String getEntCode() {
		return entCode;
	}


	public void setEntCode(String entCode) {
		this.entCode = entCode;
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

	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public TbProduct getProduct() {
		return product;
	}

	public void setProduct(TbProduct product) {
		this.product = product;
	}

	public void setProductServiceInter(ProductServiceInter productServiceInter) {
		this.productServiceInter = productServiceInter;
	}
	
	public ProductInfo getProductInfo() {
		return productInfo;
	}


	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}


	/**
	 * 查找产品列表
	 * @return
	 */
	public String findProductList(){
		long entId = isAdmin()?0:this.getEnterprse().getEntId();
		Pager<TbProduct> pager = productServiceInter.findProductList(product,this.page,this.rows,this.isAdmin(),entId,order,sort);
		jsonMsg = this.createEasyUiJson(pager);
		this.printJsonString(jsonMsg);
		return null;
	}
	
	/**
	 * 查找产品列表
	 * @return
	 */
	public String findProductListForEnt(){
		long entId = isAdmin()?0:this.getEnterprse().getEntId();
		Pager<TbProduct> pager = productServiceInter.findProductListForEnt(product,this.page,this.rows,this.isAdmin(),entId,order,sort);
		jsonMsg = this.createEasyUiJson(pager);
		this.printJsonString(jsonMsg);
		return null;
	}
	
	/**
	 * 手机查询产品接口
	 * @return
	 */
	public void findProductListforMobile(){
		if(entCode!=null&&!"".equals(entCode)){
			int entId = enterpriseService.getEnterpriseByEntCode(entCode).getEntId();
			Pager<TbProduct> pager = productServiceInter.findProductList(null,this.page,this.rows,false,entId,order,sort);
			jsonMsg = this.createEasyUiJson(pager);
			this.printJsonString(jsonMsg);
		}
	}
	
	/**
	 * 添加新产品
	 * @return
	 */
	public String addProduct(){
		jsonMsg = "添加失败!";
		try {
			//int entId =isAdmin()?0:this.getEnterprse().getEntId();
			
			//product.setEntId(entId);
			
			//String proCode = this.getEnterprse().getEntCode();;
			//product.setProCode(proCode);
			
			product.setUpdateTime(DateUtil.formatDateTime());
			
			product.setUserId(this.getLoginUserId());
			
			productServiceInter.addProduct(product,productInfo);
			
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加产品出错："+e.getMessage());
		}
		
		this.printJsonString(jsonMsg);
		
		return null;
	}
	
	public String updateProduct(){
		jsonMsg = "修改失败!";
		try {
			product.setUpdateTime(DateUtil.formatDateTime());
			
			product.setUserId(this.getLoginUserId()+"");
			
			productServiceInter.updateProduct(product,productInfo);
			
			jsonMsg = "修改成功!";	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改产品出错："+e.getMessage());
		}
		
		this.printJsonString(jsonMsg);
		
		return null;
	}
	
	public String updateProductState(){
		jsonMsg = "操作失败!";
		
		try {
			productServiceInter.updateProductState(ids,state);
			jsonMsg = "操作成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新产品状态出错："+e.getMessage());
		}
		
		this.printJsonString(jsonMsg);
		
		return null;
	}
	/**
	 * 查询二维码是否唯一
	 * @return
	 */
	public String findIsUniqueCode(){
		
		boolean flag = productServiceInter.findIsUniqueCode(dimenno);
		
		this.printJsonString(flag+"");
		
		return null;
	}
	
	public String deleteProduct(){
		jsonMsg = "删除失败";
		
		try {
			productServiceInter.deleteProduct(ids);
			
			jsonMsg = "删除成功";
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.printJsonString(jsonMsg);
		return null;
	}
	
	/**
	 * 统计企业产品数量
	 */
	public void findEntProductChart(){
		String areaName = getRequest().getParameter("areaname");
		printJsonString(productServiceInter.findEntProductChart(areaName));
	}
	
	/**
	 * 统计产品数量
	 */
	public void findProductChart(){
		printJsonString(productServiceInter.findProductChart(product));
	}
	
	/**
	 * 统计产品数量
	 */
	public void findProductTable(){
		printJsonString(productServiceInter.findProductTable(product));
	}
}
