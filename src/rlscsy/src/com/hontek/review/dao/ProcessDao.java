package com.hontek.review.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProcess;

/**
 * <p>Title: 加工包装管理</p>
 * <p>Description: 加工包装DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProcessDao extends BaseDao<TbProcess>{
	/**
	 * 删除
	 * @param ids
	 */
	public void deleteProcessByIds(String ids) {
		String hql = "delete from TbProcess where processId in ("+ids+")";
		this.executeHql(hql);
	}
	/**
	 * 查询
	 * @param string
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TbProcess> findProcessList(String condition, int page, int rows) {
		condition = condition==null?"":condition;
		String hql = "from TbProcess where 1=1 "+condition;
		List<TbProcess> data = this.find(hql,page,rows);
		String hql_ct = "select count(*) from TbProcess where 1=1 "+condition;
		int ct = this.count(hql_ct).intValue();
		
		Pager<TbProcess> pager = new Pager<TbProcess>();
		pager.setData(data);
		pager.setTotal(ct);
		
		return pager;
	}

}
