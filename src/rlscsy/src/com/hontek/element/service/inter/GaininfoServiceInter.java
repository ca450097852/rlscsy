package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbGaininfo;
/**
 * @ClassName: GaininfoServiceInter
 * @Description: TODO(采摘收获信息 service 接口)
 * @date 2015-8-10 下午07:35:34
 * @author qql
 * @version 1.0
 */
public interface GaininfoServiceInter {

	public String addGaininfo(TbGaininfo gaininfo);

	public String deleteGaininfo(String ids);

	public String updateGaininfo(TbGaininfo gaininfo);

	public String findGaininfoList(TbGaininfo gaininfo, int page, int rows, String sort,String order);	
	

}
