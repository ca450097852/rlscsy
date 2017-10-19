package com.hontek.company.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.company.pojo.Production;
import com.hontek.company.pojo.ProductionAppendix;
import com.hontek.company.service.inter.ProductionServiceInter;

/**
 * <p>Title: 生产信息表</p>
 * <p>Description: 生产信息Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductionServiceInter productionServiceInter;	
	private Production production;
	private String ids;	
	private String entCode;
	private List<ProductionAppendix> proAppList;
	
	public List<ProductionAppendix> getProAppList() {
		return proAppList;
	}

	public void setProAppList(List<ProductionAppendix> proAppList) {
		this.proAppList = proAppList;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setProductionServiceInter(ProductionServiceInter productionServiceInter) {
		this.productionServiceInter = productionServiceInter;
	}

	/**
	 * 添加生产信息
	 */
	public void addProduction(){
		if(production!=null){
			production.setCrttime(DateUtil.formatDateTime());
			production.setUserId(getLoginUserId());
		}
		jsonMsg  = productionServiceInter.addProduction(production);
		printJsonString(jsonMsg);
	}
	
	public void addProductionAndAppend(){
		
		//System.out.println("8888888888888888888===="+this.proAppList.size());
		if(production!=null){
			production.setCrttime(DateUtil.formatDateTime());
			production.setUserId(getLoginUserId());
			production.setEntId(this.getLoginUserEntId());
		}
		jsonMsg  = productionServiceInter.addProductionAndAppend(production,proAppList);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改生产信息
	 */
	public void updateProduction(){
		jsonMsg  = productionServiceInter.updateProduction(production);
		printJsonString(jsonMsg);
	}
	
	public void updateProductionAndAppend(){
		jsonMsg  = productionServiceInter.updateProductionAndAppend(production,proAppList);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除生产信息
	 */
	public void deleteProduction(){
		jsonMsg  = productionServiceInter.deleteProduction(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询生产信息
	 */
	public void findProductionPagerList(){
		jsonMsg  = productionServiceInter.findProductionPagerList(production, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	public void findProductionAndappend(){
		jsonMsg  = productionServiceInter.findProductionAndappend(this.getLoginUserEntId());
		printJsonString(jsonMsg);
	}
	
	/**
	 * 手机查询接口
	 */
	public void findProductForMobile(){
		jsonMsg  = productionServiceInter.findProductForMobile(entCode,page,rows);
		printJsonString(jsonMsg);
	}
}
