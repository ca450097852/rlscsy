package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbTrace;
/**
 * <p>Title: 溯源信息</p>
 * <p>Description: 溯源信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public interface TraceServiceInter {

	String findTraceList(TbTrace trace, int page, int rows, String sort,
			String order);

	String addTrace(TbTrace trace);

	String appendTraceList(List<TbTrace> traceList);

	String updateTrace(TbTrace trace);

	String deleteTrace(String ids);

	String findTrace(String qrCode);
}
