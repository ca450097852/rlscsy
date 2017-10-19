package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MarketInInfoDetail;

public interface MarketInInfoDetailServiceInter {

	String addMarketInInfoDetail(MarketInInfoDetail marketInInfoDetail);

	String updateMarketInInfoDetail(MarketInInfoDetail marketInInfoDetail);

	String deleteMarketInInfoDetail(String ids);

	Pager<MarketInInfoDetail> findList(MarketInInfoDetail marketInInfoDetail,
			int page, int rows, String sort, String order);

}
