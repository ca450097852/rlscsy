package com.hontek.company.service.inter;

import com.hontek.company.pojo.ProductionAppendix;

/**
 * <p>Title: 生产信息附件表</p>
 * <p>Description: 生产信息附件接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ProductionAppendixServiceInter {

	public String addProductionAppendix(ProductionAppendix productionAppendix);
	
	public String deleteProductionAppendix(String ids);
	
	public int deleteProductionAppendixByPath(String path);
	
	public String updateProductionAppendix(ProductionAppendix productionAppendix);
	
	public String findProductionAppendixPagerList(int proId,int page,int rows,String sort,String order);
}
