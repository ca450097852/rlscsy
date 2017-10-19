package com.hontek.company.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.company.pojo.Zizhi;
import com.hontek.company.pojo.ZizhiAppendix;
/**
 * <p>Title: 企业资质证书表</p>
 * <p>Description: 企业资质证书DAO 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiDao extends BaseDao<Zizhi> {

	public int isCreateZizhi(int entId, int ztype) {
		String sql = "select count(*) from  tb_zizhi zi,tb_zzappendix za where zi.z_id=za.z_id and ent_id="+entId+" and app_type="+ztype;
		int ct = this.countBySql(sql).intValue();
		return ct;
	}

	public List findZizhiPagerListforMobile(String entCode, String zType,
			int page, int rows) {
		String sql = "select z_name as \"zName\",app_name as \"appName\",path,uploadtime from tb_zzappendix tza,tb_zizhi tz WHERE tza.z_id=tz.z_id and" +
				" tz.ent_id=(SELECT ent_id from ts_enterprise where ent_code='"+entCode+"') ";
		
		if(zType!=null&&!"".equals(zType)){
			sql += " and app_type="+zType;
		}
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(ZizhiAppendix.class));
		List list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		return list;
	}

	public int updateState(String zid,String state){
		String sql = "update tb_zizhi set state="+state+" where z_id="+zid;
		return executeSql(sql);
	}
	
}
