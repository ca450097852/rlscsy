package com.hontek.company.service.inter;

import java.util.List;

import com.hontek.company.pojo.Production;
import com.hontek.company.pojo.ProductionAppendix;

/**
 * <p>Title: 生产信息表</p>
 * <p>Description: 生产信息接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ProductionServiceInter {

	public String addProduction(Production production);
	
	public String deleteProduction(String ids);
	
	public String updateProduction(Production production);
	
	public String findProductionPagerList(Production production,int page,int rows,String sort,String order);

	public String findProductForMobile(String entCode, int page, int rows);

	public String addProductionAndAppend(Production production,
			List<ProductionAppendix> proAppList);

	public String findProductionAndappend(int loginUserEntId);

	public String updateProductionAndAppend(Production production,
			List<ProductionAppendix> proAppList);
}
