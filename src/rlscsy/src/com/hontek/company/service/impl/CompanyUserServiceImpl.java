package com.hontek.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.CompanyUserDao;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.company.service.inter.CompanyUserServiceInter;


/**
 * <p>Title:企业信息表</p>
 * <p>Description: 企业信息表接口实现 类</p>
 * @version 1.0
 */
public class CompanyUserServiceImpl extends BaseServiceImpl implements CompanyUserServiceInter {

	private CompanyUserDao companyUserDao;
	Logger logger = Logger.getLogger(CompanyUserServiceImpl.class);
	
	public void setCompanyUserDao(CompanyUserDao companyUserDao) {
		this.companyUserDao = companyUserDao;
	}

	/**
	 * 添加企业
	 */
	public String addCompanyUser(CompanyUser companyUser) {
		String msg = "添加企业成功!";
		try {
			companyUser.setComId(companyUserDao.getTableSequence("tb_company_user".toUpperCase()));
			companyUserDao.save(companyUser);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除企业
	 */
	public String deleteCompanyUser(String ids) {
		String msg = "删除企业成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					CompanyUser companyUser = companyUserDao.get(CompanyUser.class, Integer.valueOf(idArray[i]));
					if(companyUser!=null){
						companyUserDao.delete(companyUser);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	

	/**
	 * 发布企业
	 */
	public String updatePublishCompanyUser(String ids) {
		String msg = "发布企业成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					CompanyUser companyUser = companyUserDao.get(CompanyUser.class, Integer.valueOf(idArray[i]));
					if(companyUser!=null){
						companyUser.setState("1");
						companyUserDao.update(companyUser);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "发布企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
	/**
	 * 分页查询企业
	 */
	public String findCompanyUserPagerList(CompanyUser companyUser,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(companyUser!=null){
				String realName = companyUser.getRealName();//产品情况
				if(realName!=null&&!"".equals(realName)){
					condition.append(" and realName like '%"+realName+"%' ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<CompanyUser>  pager = companyUserDao.findPager(CompanyUser.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询企业出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改企业
	 */
	public String updateCompanyUser(CompanyUser companyUser) {
		String msg = "修改企业成功!";
		try {
			companyUserDao.update(companyUser);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
	public CompanyUser findCompanyUser(String account,String password){
		List<CompanyUser> comuList  = companyUserDao.find(" from CompanyUser where account='"+account+"' and password='"+password+"'");
		if(comuList.size()>0){
			return comuList.get(0);
		}else{
			return null;
		}
	}
	
	public CompanyUser findCompanyUserById(int comUserId){
		return companyUserDao.get(CompanyUser.class,comUserId);
	}
	
	/**
	 * 查询名称是否存在
	 */
	public String findAccountIsUnique(String account){
		boolean f = companyUserDao.findAccountIsUnique(account);
		return String.valueOf(f);
	}

}
