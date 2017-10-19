package com.hontek.info.service.inter;

import java.util.List;

import com.hontek.info.pojo.TInfotemp;

public interface InfotempServiceInter {

	String findInfotempList(TInfotemp tInfotemp, int page, int rows,
			String sort, String order);

	String addInfotemp(TInfotemp tInfotemp);

	String deleteInfotemp(String tIds);

	String updateReportInfotemp(String parameter);

	String updateInfotemp(TInfotemp infotemp);

	void appendInfoTemp(List<TInfotemp> infoList);

}
