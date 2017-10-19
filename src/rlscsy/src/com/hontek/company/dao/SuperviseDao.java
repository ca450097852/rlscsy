package com.hontek.company.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.Supervise;
/**
 * <p>Title: 企业监管信息表</p>
 * <p>Description: 企业监管信息DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class SuperviseDao extends BaseDao<Supervise> {

	public List<Supervise> findSuperviseList(String entCode){
	
		String sql = "SELECT sup_id as supId, s.ent_id as entId, title, sup_ent as supEnt, " +
					"sup_time as supTime, sup_user as supUser, sup_code as supCode,contents,company_contents as companyContents,company_time as companyTime " +
					"FROM tb_supervise s,ts_enterprise e WHERE s.ent_id = e.ent_id and e.ent_code='"+entCode+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Supervise.class));
		List list = sqlQuery.list();
		return list;
	}
}
