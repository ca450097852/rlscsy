package com.hontek.agri.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.agri.pojo.TbAgriSupplier;
import com.hontek.agri.service.inter.AgriSupplierServiceInter;
/**
 * <p>Title: 投入品供应商</p>
 * <p>Description: 投入品供应商Action实现类</p>
 * @author qql
 * @version 1.0
 */
public class AgriSupplierAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgriSupplierServiceInter agriSupplierService;
	private TbAgriSupplier agriSupplier;
	private String ids;
	
	public TbAgriSupplier getAgriSupplier() {
		return agriSupplier;
	}


	public void setAgriSupplier(TbAgriSupplier agriSupplier) {
		this.agriSupplier = agriSupplier;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}

	

	public void setAgriSupplierService(AgriSupplierServiceInter agriSupplierService) {
		this.agriSupplierService = agriSupplierService;
	}


	/**
	 * 查找投入品供应商
	 */
	public void findAgriSupplierList(){
		jsonMsg = agriSupplierService.findAgriSupplierList(agriSupplier, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 添加投入品供应商
	 */
	public void addAgriSupplier(){
		jsonMsg = agriSupplierService.addAgriSupplier(agriSupplier);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改投入品供应商
	 */
	public void updateAgriSupplier(){
		jsonMsg = agriSupplierService.updateAgriSupplier(agriSupplier);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除投入品供应商
	 */
	public void deleteAgriSupplier(){
		jsonMsg = agriSupplierService.deleteAgriSupplier(ids);
		printJsonString(jsonMsg);
	}
}
