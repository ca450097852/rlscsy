package com.hontek.company.dao;

import java.util.List;

import org.hibernate.SQLQuery;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.AuditCtrl;
/**
 * <p>Title: 企业审核控制表</p>
 * <p>Description: 企业审核控制DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class AuditCtrlDao extends BaseDao<AuditCtrl> {

	/**
	 * 读取审核机构
	 * @param entId
	 * @return
	 */
	public String getAuditEnt(int entId){
		String sql = "select audit_ent from ts_audit_ctrl where ent_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		Object obj = sqlQuery.uniqueResult();
		return obj.toString();
	}
	/**
	 * 根据ENTID获取对象
	 * @param entId
	 * @return
	 */
	public AuditCtrl getAuditCtrlByEntId(int entId) {
		String hql = "from AuditCtrl where entId="+entId;
		List<AuditCtrl> list = this.find(hql);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
