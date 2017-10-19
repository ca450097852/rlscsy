package com.hontek.company.dao;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.ZizhiAppendix;
/**
 * <p>Title: 企业资质证书附件表</p>
 * <p>Description: 企业资质证书附件DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAppendixDao extends BaseDao<ZizhiAppendix> {

	public int deleteByPath(String path){
		String hql = "delete from ZizhiAppendix where path='"+path+"'";
		return super.executeHql(hql);
	}

	public void deleteByZid(int id) {
		String hql = "delete from ZizhiAppendix where zid = "+id;
		this.executeHql(hql);
	}

}
