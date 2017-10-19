package com.hontek.company.dao;



import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;


import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.ComAssess;
import com.hontek.company.pojo.ComAssessDetail;

/**
 * 企业项目表
 * @author chenan
 *
 */
public class ComAssessDao extends BaseDao<ComAssess>{
	public Pager<ComAssess> findComAssessList(String condition, int page,int rows, String sort, String order) {
		condition = condition==null?"":condition;
		String hql = "from ComAssess where 1=1 "+condition;
		List<ComAssess> list = this.find(hql, page, rows);
		String hql_ct = "select count(*) from ComAssess where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<ComAssess> pager = new Pager<ComAssess>();
		pager.setTotal(total);
		pager.setData(list);
		return pager;
	}
	public Pager<Object> findComAssessListByCaId(String caId, int page,int rows, String sort, String order) {			
			String sql ="SELECT ca.ca_id as caId,cad_id as cadId,item_name as itemName,ca.name as caName," +
					"ca.addr,ca.link_user as linkUser,in_date as inDate,in_user as inUser,ca.phone,item_desc as itemDesc," +
					"check_self as checkSelf,check_audit as checkAudit " +
					" from tb_com_assess ca " +
					" LEFT JOIN tb_com_assess_detail cad ON ca.ca_id=cad.ca_id"+
					" LEFT JOIN tb_assess_item item  ON item.item_id=cad.item_id";
			String temp=" where 1=1 and ca.ca_id='"+caId+"'";
			SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp);
			sqlQuery.addScalar("caId", StandardBasicTypes.INTEGER);
			sqlQuery.addScalar("cadId", StandardBasicTypes.INTEGER);
			sqlQuery.addScalar("itemName", StandardBasicTypes.STRING);
			sqlQuery.addScalar("checkAudit", StandardBasicTypes.STRING);
			sqlQuery.addScalar("addr", StandardBasicTypes.STRING);
			sqlQuery.addScalar("linkUser", StandardBasicTypes.STRING);
			sqlQuery.addScalar("inDate", StandardBasicTypes.STRING);
			sqlQuery.addScalar("inUser", StandardBasicTypes.STRING);
			sqlQuery.addScalar("caName", StandardBasicTypes.STRING);
			sqlQuery.addScalar("itemDesc", StandardBasicTypes.STRING);
			sqlQuery.addScalar("phone", StandardBasicTypes.STRING);
			sqlQuery.addScalar("checkSelf", StandardBasicTypes.STRING);
			
			sqlQuery.setResultTransformer(Transformers.aliasToBean(ComAssessDetail.class));
			List<Object> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
			
			String hql = "select count(*) " +
					"from tb_com_assess ca LEFT JOIN tb_com_assess_detail cad ON ca.ca_id=cad.ca_id  "+
					"LEFT JOIN tb_assess_item item  ON item.item_id=cad.item_id  "+temp;
			Integer count = this.countBySql(hql);
			
			Pager<Object> pager = new Pager<Object> ();
			pager.setData ( list );
			pager.setTotal ( count.intValue());
			return pager;
			
		}
	}
		
		
	

	
	

