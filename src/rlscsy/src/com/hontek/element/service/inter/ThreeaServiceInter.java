package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbThreea;
/**
 * <p>Title: 三品一标信息</p>
 * <p>Description: 三品一标信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface ThreeaServiceInter {

	public String addThreea(TbThreea threea);

	public String deleteThreea(String ids);

	public String updateThreea(TbThreea threea);

	public String findThreeaList(TbThreea threea, int page, int rows, String sort,String order);	

}
