package com.hontek.review.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbPrevention;
/**
 * @ClassName: PreventionDao
 * @Description: TODO(防疫表、植保表)
 * @date 2014-11-19 下午05:21:25
 * @author qql
 * @version 1.0
 */
public class PreventionDao extends BaseDao<TbPrevention>{
	
	public void deletePreventionByIds(String ids) {
		String hql = "delete from TbPrevention where ptId in ("+ids+")";
		this.executeHql(hql);
	}

	public Pager<TbPrevention> findList(String condition,int page,int rows) {
		String hql = "from TbPrevention where 1=1 "+condition;
		int index = hql.lastIndexOf("order by");
		String hql_ct = "select count(*) " + (index==-1?hql:hql.substring(0,index));
		List<TbPrevention> list = this.find(hql, page, rows);
		int ct = this.count(hql_ct).intValue();
		Pager<TbPrevention> pager = new Pager<TbPrevention>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}
	@SuppressWarnings("unchecked")
	public Pager<TbPrevention> findPreventionList(String condition,int page,int rows) {
		String sql = "select pt.pt_id as ptId,"+
		  "pt.pro_id as proId,"+
		  "pt.drug_name as drugName,"+
		  "pt.drug_company as drugCompany,"+
		  "pt.drug_way as drugWay,"+
		  "pt.drug_cycle as drugCycle,"+         
		  "pt.drug_time as drugTime,"+
		  "pt.drug_object as drugObject,"+
		  "pt.dosage as dosage "+         
		  "from tb_prevention pt where 1=1 ";
		
		sql = sql.concat(condition);
		sql+=" order by pt.drug_time asc";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("ptId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("drugName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("drugCompany", StandardBasicTypes.STRING);
		sqlQuery.addScalar("drugWay", StandardBasicTypes.STRING);
		sqlQuery.addScalar("drugCycle", StandardBasicTypes.STRING);
		sqlQuery.addScalar("drugTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("drugObject", StandardBasicTypes.STRING);

		sqlQuery.addScalar("dosage", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbPrevention.class));
		
		List<TbPrevention> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		String hql_ct = "select count(*) from tb_prevention pt where 1=1";
		hql_ct = hql_ct.concat(condition);
		int ct = this.countBySql(hql_ct).intValue();
		Pager<TbPrevention> pager = new Pager<TbPrevention>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}

}
