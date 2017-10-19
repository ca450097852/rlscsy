package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MeatOutInfoBase;
import com.hontek.sys.pojo.TsEnterprise;

public interface MeatOutInfoBaseServiceInter {

	String addMeatOutInfoBase(MeatOutInfoBase meatOutInfoBase);

	String updateMeatOutInfoBase(MeatOutInfoBase meatOutInfoBase);

	String deleteMeatOutInfoBase(String ids);

	Pager<MeatOutInfoBase> findList(MeatOutInfoBase meatOutInfoBase, int page,
			int rows, String sort, String order);
	/**
	 * 查询销售量
	 * @return
	 */
	 String getMarketList(TsEnterprise enterprise);
	 
	 
	 String getTranId(String comCode);
}
