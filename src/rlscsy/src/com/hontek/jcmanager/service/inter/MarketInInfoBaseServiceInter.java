package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoBase;
import com.hontek.jcmanager.pojo.QuarantineInfo;

public interface MarketInInfoBaseServiceInter {

	String addMarketInInfoBase(MarketInInfoBase marketInInfoBase);

	String updateMarketInInfoBase(MarketInInfoBase marketInInfoBase);

	String daleteMarketInInfoBase(String ids);

	Pager<MarketInInfoBase> findList(MarketInInfoBase marketInInfoBase,
			int page, int rows, String sort, String order);

	
	MarketInInfoBase findByJyh(MarketInInfoBase marketInInfoBase);
}
