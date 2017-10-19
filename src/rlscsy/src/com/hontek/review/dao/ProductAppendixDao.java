package com.hontek.review.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
/**
 * <p>Title: 产品图片管理</p>
 * <p>Description: 产品图片管理</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductAppendixDao extends BaseDao<TbProductAppendix>{

	public Pager<TbProductAppendix> findProAppList(String condition,int page,int rows) {
		String hql = "from TbProductAppendix where 1=1 "+condition;
		int index = hql.lastIndexOf("order by");
		String hql_ct = "select count(*) " + (index==-1?hql:hql.substring(0,index));
		List<TbProductAppendix> list = this.find(hql, page, rows);
		int ct = this.count(hql_ct).intValue();
		Pager<TbProductAppendix> pager = new Pager<TbProductAppendix>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}
	
	public Pager<TbProductAppendix> findProductAppList(String condition,int page,int rows) {
		String sql = "select app.PRO_ID as proId,"+
		  "app.APP_ID as appId,"+
		  "app.APP_NAME as appName,"+
		  "app.PATH as path,"+
		  "app.UPLOADTIME as uploadTime,"+         
		  "app.ORDERBY as orderby,"+         
		  "app.REMARK as remark "+       
		  "from tb_product_appendix app where 1=1 ";
		
		sql = sql.concat(condition);
		sql+=" order by app.orderby asc,app.uploadtime desc";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("appId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("appName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("path", StandardBasicTypes.STRING);
		sqlQuery.addScalar("uploadTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("orderby", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbProductAppendix.class));
		
		List<TbProductAppendix> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		String hql_ct = "select count(*) from tb_product_appendix app where 1=1";
		hql_ct = hql_ct.concat(condition);
		int ct = this.countBySql(hql_ct).intValue();
		Pager<TbProductAppendix> pager = new Pager<TbProductAppendix>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}

}
