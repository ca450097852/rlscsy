package com.hontek.company.service.inter;

import com.hontek.company.pojo.Supervise;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 企业监管信息表</p>
 * <p>Description: 企业监管信息接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface SuperviseServiceInter {

	public String addSupervise(Supervise supervise,TsUser tsUser);
	
	public String deleteSupervise(String ids);
	
	public String updateSupervise(Supervise supervise);
	
	/**
	 * 企业回复
	 * @param supervise
	 * @return
	 */
	public String updateSuperviseByCompany(Supervise supervise);

	
	public String findSupervisePagerList(Supervise supervise,int page,int rows,String sort,String order);

	public String findSuperviseList(String entCode);
	
	public String findSuperviseListById(String entCode);

	public String addSuperviseList(Supervise supervise, TsUser loginTsUser,
			TsEnterprise enterprise, String ids);


}
