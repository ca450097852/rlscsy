package com.hontek.company.service.inter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.hontek.company.pojo.Zizhi;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.pojo.ZizhiType;

/**
 * <p>Title: 企业资质证书表</p>
 * <p>Description: 企业资质证书接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ZizhiServiceInter {

	public String addZizhi(Zizhi zizhi);
	
	public String deleteZizhi(String ids);
	
	public String updateZizhi(Zizhi zizhi);
	
	public String findZizhiPagerList(Zizhi zizhi,int page,int rows,String sort,String order);

	public String appendZizhiList(List<Zizhi> zzList) throws  IOException;

	public String isCreateZizhi(int entId, int i);

	public String findZizhiPagerListforMobile(String entCode, String zType,
			int page, int rows);

	public String addZizhiAndAppend(Zizhi zizhi,
			List<ZizhiAppendix> appendixList,ZizhiType zizhiType);

	public String findZizhiList(int loginUserEntId);

	public String updateZizhiAndAppend(Zizhi zizhi,
			List<ZizhiAppendix> appendixList,ZizhiType zizhiType);
}
