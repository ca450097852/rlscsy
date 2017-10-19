package com.hontek.company.dao;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.ZizhiType;

/**
 * 资质类型Dao
 * @author lzk
 *
 */
public class ZizhiTypeDao extends BaseDao<ZizhiType>{
	
	/**
	 * 根据条件删除信息
	 * @param term
	 */
	public void deleteByTerm(String term) {
		if(term!=null&&!"".equals(term)){
			String hql = "delete from ZizhiType where 1=1 "+term;
			executeHql(hql);
		}
	}

}
