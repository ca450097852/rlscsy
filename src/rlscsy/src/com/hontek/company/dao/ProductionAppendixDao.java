package com.hontek.company.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.ProductionAppendix;
/**
 * <p>Title: 生产信息附件表</p>
 * <p>Description: 生产信息附件DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionAppendixDao extends BaseDao<ProductionAppendix> {

	public int deleteByPath(String path){
		String hql = "delete from ProductionAppendix where path='"+path+"'";
		return super.executeHql(hql);
	}

	public List<ProductionAppendix> findProductAppend(String condition, int page,
			int rows) {
		String hql = "from ProductionAppendix where 1=1 "+condition;
		return this.find(hql, page, rows);
	}

	public void deleteAppByProId(int proId) {
		String hql = "delete from ProductionAppendix where proId="+proId;
		this.executeHql(hql);
	}
}
