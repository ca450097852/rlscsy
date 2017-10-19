package com.hontek.sys.service.inter;

import com.hontek.sys.pojo.TbInterAccount;

/**
 * <p>Title: 接入系统账号表</p>
 * <p>Description: 接入系统账号表接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface InterAccountServiceInter {

	public String addInterAccount(TbInterAccount interAccount);
	
	public String deleteInterAccount(String ids);
	
	public String updateInterAccount(TbInterAccount interAccount);
	
	public String findInterAccountPagerList(TbInterAccount interAccount,int page,int rows,String sort,String order);

	public TbInterAccount getInterAccount(String condition);
	
	public String findInterAccountExist(String account);
}
