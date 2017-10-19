package com.hontek.element.service.inter;

import java.util.List;

import com.hontek.element.pojo.TbAgriType;
/**
 * <p>Title: 投入品购买信息</p>
 * <p>Description: 投入品购买信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface AgriTypeServiceInter {

	public String addAgriType(TbAgriType agriType);

	public String deleteAgriType(String ids);

	public String updateAgriType(TbAgriType agriType);

	public String findAgriTypeList(TbAgriType agriType, int page, int rows, String sort,String order);
	
	public String findAgriTypeComboList();


}
