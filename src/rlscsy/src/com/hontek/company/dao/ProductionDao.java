package com.hontek.company.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Production;
/**
 * <p>Title: 生产信息表</p>
 * <p>Description: 生产信息表DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ProductionDao extends BaseDao<Production> {
	
	
	
	public Pager<Production> findPager(String condition, int page, int rows) {						
		
		String sql = "select tp.pro_id as proId,tp.productinfo as productinfo,tp.license as license," +
					 "tp.crttime as crttime,tp.user_id as userId ,tp.ent_id as entId,te.name as entName " +
					 "from tb_production tp,TS_ENTERPRISE te where tp.ent_id = te.ent_id "+condition;
				
		String sql_ct = "select count(*) from tb_production tp,TS_ENTERPRISE te where tp.ent_id = te.ent_id "+condition;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("productinfo", StandardBasicTypes.STRING);
		sqlQuery.addScalar("license", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crttime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(Production.class));
		
		List<Production> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		Integer pagecount = this.countBySql(sql_ct);
		
		Pager<Production> pager = new Pager<Production>();
		pager.setData(list);
		pager.setTotal(pagecount.intValue());
		
		return pager;
	}

}
