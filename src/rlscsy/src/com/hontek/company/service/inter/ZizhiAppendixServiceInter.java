package com.hontek.company.service.inter;

import com.hontek.company.pojo.ZizhiAppendix;

/**
 * <p>Title: 企业资质证书附件表</p>
 * <p>Description: 企业资质证书附件接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ZizhiAppendixServiceInter {

	public String addZizhiAppendix(ZizhiAppendix zizhiAppendix);
	
	public String deleteZizhiAppendix(String ids);
	
	public int deleteZizhiAppendixByPath(String path);
	
	public String updateZizhiAppendix(ZizhiAppendix zizhiAppendix);
	
	public String findZizhiAppendixPagerList(int zid,int page,int rows,String sort,String order);
}
