package com.hontek.company.dao;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.CompanyUser;
/**
 * <p>Title: 企业用户信息表</p>
 * <p>Description: 企业用户信息表DAO 类</p>
 */
public class CompanyUserDao extends BaseDao<CompanyUser> {
	/**
     * 验证账号是否唯一
     * @param account
     * @return
     */
	public boolean findAccountIsUnique(String account) {
		String hql="select count(*) from CompanyUser";
		if(!"".equals(account)){
			hql +=" where account='"+account+"'";
		}
		Long i = this.count(hql);
		if(i.longValue()>0L){
			return true;
		}
		return false;
	}
}
