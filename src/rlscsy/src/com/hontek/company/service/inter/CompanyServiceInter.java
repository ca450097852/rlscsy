package com.hontek.company.service.inter;

import java.util.List;

import com.hontek.company.pojo.Company;


/**
 * <p>Title: 企业信息表</p>
 * <p>Description: 企业信息表接口 类</p>
 * @version 1.0
 */
public interface CompanyServiceInter {

	public String addCompany(Company company);
	
	/**
	 * 企业注册
	 * @param company
	 * @return
	 */
	public String addCompany2(Company company);
	
	public String deleteCompany(String ids);
	
	public String updatePublishCompany(String ids);
	
	public String updateCompany(Company company);
	public String updateCompanyLogo(Company company);
	
	public String findCompanyPagerList(Company company,int page,int rows,String sort,String order);
	
	public String findCompanyAreaName(int comId);
	/**
	 * 分页查询企业
	 */
	public String findPagerList(Company company,int page,int rows,String sort,String order);
	
	public String updateCompanyState(String ids,String state);
	
	public String getCompanyToSelect(String area,String flag);
	
	/**
	 * 根据comId主键获取
	 * @param comId
	 * @return
	 */
	public Company getCompanyByComId(int comId);
	
	
	/**
	 * 根据企业comId获取
	 * @param comId
	 * @return
	 */
	public Company getNodeByComId(int comId);
	
	/**
	 * 根据企业comCode获取
	 * @param comCode
	 * @return
	 */
	public Company getCompanyByComCode(String comCode);
	
	
	
	/**
	 * @param company
	 * @return
	 */
	public List<Company> findList(Company company);
	
	/**
	 * 查询名称是否存在
	 */
	public String findNameIsUnique(String name);
	
	public String findNodeCompanyList();
	
}
