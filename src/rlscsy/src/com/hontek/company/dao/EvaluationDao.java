package com.hontek.company.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.Evaluation;
/**
 * <p>Title: 信用评价信息表</p>
 * <p>Description: 信用评价信息DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EvaluationDao extends BaseDao<Evaluation> {

	public List<Evaluation> findEvaluationList(String entCode){
	
		String sql = "SELECT eval_id as evalId, s.ent_id as entId, eval_contents as evalContents, " +
					"eval_time as evalTime, company_id as companyId,company_contents as companyContents,company_time as companyTime " +
					"FROM tb_supervise s,ts_enterprise e WHERE s.ent_id = e.ent_id and e.ent_code='"+entCode+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Evaluation.class));
		List list = sqlQuery.list();
		return list;
	}
}
