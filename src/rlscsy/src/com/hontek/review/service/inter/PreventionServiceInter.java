package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbPrevention;
import com.hontek.review.pojo.TbTraceAppdix;

public interface PreventionServiceInter {

	String findPreventionList(TbPrevention prevention, int page, int rows, String order,
			String sort);
	
	String findPreventionList(TbPrevention prevention, int page, int rows);

	String addPrevention(TbPrevention prevention, List<TbTraceAppdix> traceAppList);

	String deletePrevention(String ids);
	
	String updatePrevention(TbPrevention prevention, List<TbTraceAppdix> traceAppList);

}
