package com.hontek.review.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbStoreTransport;

/**
 * <p>Title: 仓储运输</p>
 * <p>Description: 仓储运输DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class StoreTransportDao extends BaseDao<TbStoreTransport>{

	public void deleteStoreTransportByIds(String ids) {
		String hql = "delete from TbStoreTransport where stId in ("+ids+")";
		this.executeHql(hql);
	}
	/**
	 * 查询
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TbStoreTransport> findStoreTransportList(String condition,
			int page, int rows) {
		condition = condition==null?"":condition;
		String hql = "from TbStoreTransport where 1=1 "+condition;
		List<TbStoreTransport> data = this.find(hql,page,rows);
		String hql_ct = "select count(*) from TbStoreTransport where 1=1 "+condition;
		int ct = this.count(hql_ct).intValue();
		
		Pager<TbStoreTransport> pager = new Pager<TbStoreTransport>();
		pager.setData(data);
		pager.setTotal(ct);
		
		return pager;
	}

}
