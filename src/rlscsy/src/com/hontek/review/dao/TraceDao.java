package com.hontek.review.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbTrace;
import com.hontek.sys.pojo.TsEnterprise;
/**
 * <p>Title: 溯源信息</p>
 * <p>Description: 溯源信息DAO</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TraceDao extends BaseDao<TbTrace>{

	public Pager<TbTrace> findTraceList(String condition, int page, int rows) {
//		String hql = "from TbTrace where 1=1 "+condition;
//		
//		List<TbTrace> data = this.find(hql, page, rows);
//		String hql_ct = "select count(*) from TbTrace where 1=1 ";
//		if(condition!=null&&!"".equals(condition)){
//			condition = condition.substring(condition.lastIndexOf("order by"));
//		}
//		
//		int ct = this.count(hql_ct).intValue();
//		
//		Pager<TbTrace> pager = new Pager<TbTrace>();
//		pager.setData(data);
//		pager.setTotal(ct);
		
		
		String sql = "SELECT t_id as tid,tr.pro_id as proId,p_url as purl,title,content,url,remark,tr.crttime as crttime,tr.sys_code as sysCode,sys_name as sysName from tb_trace tr,tb_inter_account ia where tr.sys_code=ia.sys_code"+condition;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("tid", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("title", StandardBasicTypes.STRING);
		sqlQuery.addScalar("content", StandardBasicTypes.STRING);
		sqlQuery.addScalar("purl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("url", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crttime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysName", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbTrace.class));

		if(condition!=null&&!"".equals(condition)){
			condition = condition.substring(condition.lastIndexOf("order by"));
		}
		
		String sql_ct = "select count(*) from tb_trace tr,tb_inter_account ia where tr.sys_code=ia.sys_code " + condition;
		
		int ct = countBySql(sql_ct);
		
		List<TbTrace> data = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		Pager<TbTrace> pager = new Pager<TbTrace>();
		
		pager.setData(data);
		pager.setTotal(ct);
		
		return pager;
	}

	/**
	 * 根据二维码查询溯源信息
	 * @param qrCode
	 * @return
	 */
	public TbTrace findTrace(String qrCode){
		
		String sql = "SELECT t_id as tid,tr.pro_id as proId,tr.p_url as purl,tr.title,tr.content,tr.url,tr.remark,tr.crttime as crttime,tr.sys_code as sysCode from tb_trace tr,tb_pro_qrcode tp where tr.pro_id=tp.pro_id and dimenno='"+qrCode+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("tid", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("title", StandardBasicTypes.STRING);
		sqlQuery.addScalar("content", StandardBasicTypes.STRING);
		sqlQuery.addScalar("purl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("url", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crttime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbTrace.class));		
		List<TbTrace> data = sqlQuery.list();
		if(data!=null&&!data.isEmpty()){
			return data.get(0);
		}
		return null;
	}
	
	
	public void deleteTrace(String ids) {
		String hql = "delete from TbTrace where tid in ("+ids+")";
		this.executeHql(hql);
	}
	

}
