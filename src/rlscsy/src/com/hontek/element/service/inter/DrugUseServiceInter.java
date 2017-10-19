package com.hontek.element.service.inter;

import com.hontek.element.pojo.DrugUse;
/**
 * <p>Title: 投入品使用信息</p>
 * <p>Description: 投入品使用信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface DrugUseServiceInter {

	public String addDrugUse(DrugUse DrugUse);

	public String deleteDrugUse(String ids);

	public String updateDrugUse(DrugUse DrugUse);

	public String findDrugUseList(DrugUse DrugUse, int page, int rows, String sort,String order);
	

}
