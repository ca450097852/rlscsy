package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbAgriUse;
/**
 * <p>Title: 投入品使用信息</p>
 * <p>Description: 投入品使用信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface AgriUseServiceInter {

	public String addAgriUse(TbAgriUse agriUse);

	public String deleteAgriUse(String ids);

	public String updateAgriUse(TbAgriUse agriUse);

	public String findAgriUseList(TbAgriUse agriUse, int page, int rows, String sort,String order);
	

}
