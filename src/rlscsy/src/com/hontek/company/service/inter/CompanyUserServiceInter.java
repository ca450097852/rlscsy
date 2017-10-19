package com.hontek.company.service.inter;

import com.hontek.company.pojo.CompanyUser;


/**
 * <p>Title: 企业用户信息表</p>
 * <p>Description: 企业用户信息表接口 类</p>
 * @version 1.0
 */
public interface CompanyUserServiceInter {

	public String addCompanyUser(CompanyUser companyUser);
	
	public String deleteCompanyUser(String ids);
	
	public String updatePublishCompanyUser(String ids);
	
	public String updateCompanyUser(CompanyUser companyUser);
	
	public String findCompanyUserPagerList(CompanyUser companyUser,int page,int rows,String sort,String order);
	
	
	public CompanyUser findCompanyUser(String account,String password);
	/**
	 * 查询名称是否存在
	 */
	public String findAccountIsUnique(String account);
	
	public CompanyUser findCompanyUserById(int comUserId);
}
