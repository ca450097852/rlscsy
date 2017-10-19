package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.RetailMarketTranInfoSumm;

public interface RetailMarketTranInfoSummServiceInter {

	String addRetailMarketTranInfoSumm(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm);

	String updateRetailMarketTranInfoSumm(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm);

	String deleteRetailMarketTranInfoSumm(String ids);

	Pager<RetailMarketTranInfoSumm> findList(
			RetailMarketTranInfoSumm retailMarketTranInfoSumm, int page,
			int rows, String sort, String order);

}
