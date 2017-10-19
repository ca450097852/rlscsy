package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbProArea;
import com.hontek.review.pojo.TbTraceAppdix;
/**
 * <p>Title: 产地信息</p>
 * <p>Description: 产地信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface ProAreaServiceInter {

	public String addProArea(TbProArea proArea);

	public String deleteProArea(String ids);

	public String updateProArea(TbProArea proArea);

	public String findProAreaList(TbProArea proArea, int page, int rows, String sort,String order);
	
	public String findProAreaList(TbProArea proArea, int page, int rows);

	public String addProArea(TbProArea proArea, List<TbTraceAppdix> traceAppList);

	public String updateProAreaAndApp(TbProArea proArea,
			List<TbTraceAppdix> traceAppList);

}
