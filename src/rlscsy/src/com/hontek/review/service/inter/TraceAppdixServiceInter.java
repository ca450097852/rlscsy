package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbTraceAppdix;

public interface TraceAppdixServiceInter {

	String findTraceAppdixList(TbTraceAppdix traceAppdix, int page, int rows, String order,
			String sort);
	
	String findTraceAppdixList(TbTraceAppdix traceAppdix, int page, int rows);

	String addTraceAppdix(TbTraceAppdix traceAppdix);

	String deleteApps(String ids);

	/**
	 * 根据产品ID查询附件信息
	 */
	public String findTraceAppdixsListByProId(TbTraceAppdix traceAppdix);

	String findTraceAppdixsListByPid(TbTraceAppdix traceAppdix);
	
}
