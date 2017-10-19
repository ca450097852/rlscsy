package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.MeatOutInfoDetail;

public interface MeatOutInfoDetailServiceInter {

	String addMeatOutInfoDetail(MeatOutInfoDetail meatOutInfoDetail);

	String updateMeatOutInfoDetail(MeatOutInfoDetail meatOutInfoDetail);

	String deleteMeatOutInfoDetail(String ids);

	Pager<MeatOutInfoDetail> findList(MeatOutInfoDetail meatOutInfoDetail,
			int page, int rows, String sort, String order);

}
