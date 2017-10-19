package com.hontek.company.service.inter;

import com.hontek.company.pojo.Useguide;


/**
 * <p>Title: 操作指引信息表</p>
 * <p>Description: 操作指引信息接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface UseguideServiceInter {

	public String addUseguide(Useguide useguide);
	
	public String deleteUseguide(String ids);
	
	public String updateUseguide(Useguide useguide);	
	
	public String findUseguidePagerList(Useguide supervise,int page,int rows,String sort,String order);

	public String findUseguideList(Useguide useguide);
	



}
