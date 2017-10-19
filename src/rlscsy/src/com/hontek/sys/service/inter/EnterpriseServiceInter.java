package com.hontek.sys.service.inter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;

import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title: 组织机构表</p>
 * <p>Description: 组织机构Service 接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface EnterpriseServiceInter {

	/**
	 * 查询机构
	 */
	public String findEnterpriseList(TsEnterprise enterprise, int page, int rows);
	/**
	 * 门户查询机构
	 */
	public String findList(TsEnterprise enterprise, int page, int rows);

	/**
	 * 查询企业
	 */
	public String findCompanyList(TsEnterprise enterprise, int page, int rows);
	
	/**
	 * 查询企业
	 */
	public List<TsEnterprise> findCompanyList(TsEnterprise enterprise);
	
	/**
	 * app查询企业
	 */
	public Pager<TsEnterprise> findCompanyPagerList(TsEnterprise enterprise, int page, int rows);
	
	/**
	 * 查询待审核企业
	 */
	public String findWaitAuditCompanyList(TsEnterprise enterprise,LoginUser loginUser, int page, int rows);
	/**
	 * 添加组织机构
	 */
	public String addEnterprise(TsEnterprise enterprise,String loginUserId);
	
	/**
	 * 添加企业
	 */
	public String addCompany(TsEnterprise enterprise,String loginUserId);
	
	/**
	 * 开通管理机构账号
	 */
	public String addEntAndUser(TsEnterprise enterprise,String loginUserId);	
	
	/**
	 * 开通企业账号
	 */
	public String addCompanyAccount(TsEnterprise enterprise,LoginUser loginUser);
	
	/**
	 * 企业注册
	 * 创建机构、用户
	 */
	public String addCompany2(TsEnterprise enterprise,HttpServletRequest request);

	/**
	 * 修改组织机构
	 */
	public String updateEnterprise(TsEnterprise enterprise);
	
	/**
	 * 删除机构
	 */
	public String deleteEnterprise(String entId) ;

	/**
	 * 查询机构下拉
	 */
	public String getEnterpriseToSelect() ;

	/**
	 * 查询机构树
	 * @param user
	 * @param isAdmin
	 * @return
	 */
	public String getEntTree(LoginUser user,boolean isAdmin) ;

	/**
	 * 修改机构状态
	 * @param entId
	 * @param sts
	 * @return
	 */
	public String updateSts(String entId,String sts) ;

	/**
	 * 查询机构
	 */
	public TsEnterprise getEnterPirseByEntId(int id);
	
	public String findAccountIsUnique(String account);
	
	public String findNameIsUnique(String name);
	
	public TsEnterprise findEnterpriseByAccount(String account);

	public TsEnterprise getEnterprise(TsEnterprise enterprise);
	
	public int getTableSequence() ;
	
	public String getAuditEntTree(String entId);

	/**
	 * 区域查询
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public String findAreaList(TsEnterprise enterprise, int page, int rows);		

	public String getEntAreaTree(LoginUser user,boolean isAdmin);
	
	//企业注册，级联下拉
	public String getAreaTree(String parentId);
	
	/**
	 * 查询区域企业数量
	 * @return
	 */
	public String getEntAreaList(TsEnterprise enterprise);
	
	public String getEntAreaListTable(TsEnterprise enterprise);
	
	public String getEntAreaListTable();
	
	public String getEntAreaListTableTotal();

	
	/**
	 * 添加同步企业
	 * @param entList
	 * @param mapList 
	 */
	public  List<Map> appendInterEnt(List<TsEnterprise> entList);
	public String getEntListJson(String jsonType);
	/**
	 * 获取当前登陆用户的企业信息
	 */
	public String getLoginEntInfo(int entId);
	/**
	 * 获取当前登陆用户的二维码信息
	 * @param httpServletRequest 
	 */
	public String getLoginCompanyDimenno(int entId, HttpServletRequest httpServletRequest,boolean flag);

	/**
	 * 查询机构属于第几级
	 * @param entId
	 * @return
	 */
	public int getEntLevel(String entId);
	/**
	 * 企业提交审核
	 * @param sts 
	 */
	public String updateEntRsts(int entId, String sts);
	/**
	 * 根据entCode获取企业信息 ,企业标识（flag）必须为3
	 */
	public String getCompanyInfoByEntCode(String entCode);
	/**
	 * 根据ENTCODE查找企业信息
	 * @param entCode
	 * @return
	 */
	public TsEnterprise getEnterpriseByEntCode(String entCode);
	
	
	public String findCompanyInfo(String entId, HttpServletRequest httpServletRequest);
	
	/**
	 * 企业注册；指定上级审核机构
	 * @param entId
	 * @return
	 */
	public String getAuditTree(String entId);
	
	/**
	 * 企业注册；根据区域查找管理机构ID；
	 * @param entId
	 * @return
	 */
	public String getEntManagerId(String entId);
	
	/**
	 * 获取有档案记录的企业
	 * @param enterprise 
	 * @param rows 
	 * @param page 
	 */
	public String getEntHasRecord(TsEnterprise enterprise, int page, int rows);
	
	/**
	 * 添加同步上来的企业，不添加用户
	 * @param ent
	 * @param request
	 */
	public Map<String, String> addSynchCompany(TsEnterprise enterprise, HttpServletRequest request);
	
	/**
	 * 添加主体
	 * @param enterprise
	 * @param entExpand
	 * @param loginUserId
	 * @return
	 */
	public String addMainBody(TsEnterprise enterprise,
			String loginUserId);
	
	/**
	 * 修改主体信息
	 * @param enterprise
	 * @return
	 */
	public String updateMainBody(TsEnterprise enterprise);
	
	
	/**
	 * 根据域名查找当前主体和旗下企业的ids
	 * @param domain
	 * @return
	 */
	public String getEntIdsByDomain(String domain);
	public String getEntTreeByAreaId(Integer areaId);
	public String findSuperviseEnt(int entid);
	public String updateSuperviseEnt(TsEnterprise enterprise);
	public String getEntAreaListTableNew(TsEnterprise enterprise);
}
