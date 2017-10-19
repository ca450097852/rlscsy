package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbSaleinfo;
/**
 * <p>Title: 销售信息</p>
 * <p>Description: 销售信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface SaleinfoServiceInter {

	public String addSaleinfo(TbSaleinfo saleinfo);

	public String deleteSaleinfo(String ids);

	public String updateSaleinfo(TbSaleinfo saleinfo);

	public String findSaleinfoList(TbSaleinfo saleinfo, int page, int rows, String sort,String order);	

}
