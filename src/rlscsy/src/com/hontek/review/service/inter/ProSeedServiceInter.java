package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbTraceAppdix;
/**
 * <p>Title: 种源信息</p>
 * <p>Description: 种源信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface ProSeedServiceInter {


	public String addProSeed(TbProSeed proSeed);

	public String deleteProSeed(String ids);

	public String updateProSeed(TbProSeed proSeed);

	public String findProSeedList(TbProSeed proSeed, int page, int rows, String sort,String order);

	public String addProSeed(TbProSeed proSeed, List<TbTraceAppdix> traceAppList);

	public String updateProSeed(TbProSeed proSeed,
			List<TbTraceAppdix> traceAppList);

}
