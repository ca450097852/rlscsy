package com.hontek.element.dao;


import com.hontek.comm.dao.BaseDao;
import com.hontek.element.pojo.TbElementApp;
/**
 * <p>Title: 附件信息</p>
 * <p>Description: 附件信息DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ElementAppDao extends BaseDao<TbElementApp> {
	/**
	 * 
	 * @param ids	
	 * @param type	附件类型
	 */
	public void deleteApps(String ids, int type) {
		if(ids!=null&&!"".equals(ids)){
			String hql = "delete from TbElementApp where appId in ("+ids+") and appType="+type;
			this.executeHql(hql);
		}
	}


	
}
