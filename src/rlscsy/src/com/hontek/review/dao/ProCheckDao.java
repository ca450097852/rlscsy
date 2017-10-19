package com.hontek.review.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbProcess;

/**
 * <p>Title: 产品检验管理</p>
 * <p>Description: 产品检验DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProCheckDao extends BaseDao<TbProCheck>{

	public void deleteProCheckByIds(String ids) {
		String hql = "delete from TbProCheck where checkId in ("+ids+")";
		this.executeHql(hql);
	}

	public Pager<TbProCheck> findProCheckList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		String hql = "from TbProCheck where 1=1 "+condition;
		List<TbProCheck> data = this.find(hql,page,rows);
		String hql_ct = "select count(*) from TbProCheck where 1=1 "+condition;
		int ct = this.count(hql_ct).intValue();
		Pager<TbProCheck> pager = new Pager<TbProCheck>();
		pager.setData(data);
		pager.setTotal(ct);
		
		return pager;
	}
	
}
