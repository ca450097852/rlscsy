package com.hontek.agri.service.inter;

import com.hontek.agri.pojo.TbAgriSupplier;
/**
 * <p>Title: 投入品供应商信息</p>
 * <p>Description: 投入品供应商信息service接口</p>
 * @author qql
 */
public interface AgriSupplierServiceInter {

	public String addAgriSupplier(TbAgriSupplier agriSupplier);

	public String deleteAgriSupplier(String ids);

	public String updateAgriSupplier(TbAgriSupplier agriSupplier);

	public String findAgriSupplierList(TbAgriSupplier agriSupplier, int page, int rows, String sort,String order);
	

}
