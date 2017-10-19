package com.hontek.element.dao;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.element.pojo.TbAgriInput;
/**
 * <p>Title: 农业投入品信息</p>
 * <p>Description: 农业投入品信息DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriInputDao extends BaseDao<TbAgriInput> {

	public Pager<TbAgriInput> findPager(String condition, int page, int rows) {
		String sql = "select t1.agri_id as agriId,t1.rec_ID as recId,t1.agri_name as agriName,t1.buy_date as buyDate,t1.buy_addr as buyAddr,t1.buy_num as buyNum,t1.agri_img as agriImg,t1.ag_spc agspc,t1.ag_num as agnum,crttime," +
				"t1.buy_unit as buyUnit,t1.agri_company as agriCompany,t1.buy_user as buyUser,t1.type_id as typeId, t2.type_name as typeName from tb_agri_input t1 LEFT JOIN tb_agri_type t2 ON t1.type_id=t2.type_id where 1=1 ";
		//System.out.println(hql);
		//拼接条件查询语句
		sql = sql.concat(condition);	
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbAgriInput.class));

		List<TbAgriInput> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();	
		
		String countSQL = "select count(*) from tb_agri_input t1 LEFT JOIN tb_agri_type t2 ON t1.type_id=t2.type_id where 1=1 "+condition;
		if(condition.contains("order by")){
			 countSQL = countSQL.substring(0, countSQL.indexOf("order by"));
		}
		if(condition.contains("ORDER BY")){
			 countSQL = countSQL.substring(0, countSQL.indexOf("ORDER BY"));
		}
		Integer count = this.countBySql(countSQL);
		Pager<TbAgriInput> pager = new Pager<TbAgriInput> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue () );
		return pager;
	}
	
	
	public List<TbAgriInput> findCompanyAgriInputList(String entId,String ids) {
		String sql = "SELECT t1.agri_id as agriId,t1.rec_ID as recId,t1.agri_name as agriName,t1.buy_date as buyDate,t1.buy_addr as buyAddr,t1.buy_num as buyNum,t1.agri_img as agriImg,t1.ag_spc agspc,t1.ag_num as agnum,crttime," +
				"t1.buy_unit as buyUnit,t1.agri_company as agriCompany,t1.buy_user as buyUser,t1.type_id as typeId FROM tb_agri_input t1 WHERE t1.rec_id in(SELECT rec_id FROM tb_record WHERE obj_id="+entId+" and obj_type_id=1)";
		if(ids!=null&&!"".equals(ids)){
			sql += " and t1.type_id in("+ids+")"; 
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbAgriInput.class));
		List<TbAgriInput> list = sqlQuery.list();		
		return list;
	}
	
}
