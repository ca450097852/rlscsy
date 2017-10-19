package com.hontek.sys.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.sys.pojo.EntStyle;
/**
 * 风格关系
 * @author qql
 *
 */
public class EntStyleDao extends BaseDao<EntStyle>{

	public void deleteByIds(String ids) {
		String hql = "delete from EntStyle where esId in ("+ids+")";
		this.executeHql(hql);
	}
	
	
	public void deleteByIds(String entId, String scType) {
		String hql = "delete from EntStyle where entId ="+entId+" and scType="+scType;
		this.executeHql(hql);
	}
	
	/**
	 * 根据主体Id 和风格类型 获取风格；
	 */
	public EntStyle getEntStyleInfo(String entId, String scType) throws AppException {
		
		String sql = "select es.es_id as \"esId\" " +
				", es.sc_id as \"scId\" " +
				", es.ent_id as \"entId\" " +
				", es.logo_image as \"logoImage\" " +
				", es.bottom_info as \"bottomInfo\" " +
				", es.sc_type as \"scType\" " +
				", es.user_id as \"userId\" " +
				", es.create_time as \"createTime\" " +
				", es.remark as \"remark\" " +
				", es.login_view as \"loginView\" " +
				" from t_ent_style es left join t_style_config sc on es.sc_id = sc.sc_id where sc.sc_type='"+scType+"' and es.ent_id="+entId;	
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntStyle.class));
		
		List<EntStyle> list = sqlQuery.list();
		
		System.out.println("查出的集合的大小：" + list.size());
		if (list.size() > 0) {
			EntStyle entStyle = (EntStyle) list.get(0);
			return entStyle;
		} else {
			return null;
		}
	}

	public EntStyle getEntStyleByEntId(int entId) {
		String sql = "SELECT "+
					"t1.es_id as \"esId\",t1.sc_id as \"scId\",t1.ent_id as \"entId\",t1.logo_image as \"logoImage\", "+
					"t1.bottom_info as \"bottomInfo\",t1.sc_type as \"scType\",t1.user_id as \"userId\",t1.login_view as \"loginView\", "+
					"t2.sc_name as \"scName\",t2.sc_css as \"scCss\",t1.create_time as \"createTime\" from t_ent_style t1,t_style_config t2 where t1.sc_id=t2.sc_id and t2.sc_type=3 and t1.ent_id="+entId; 
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntStyle.class));
		
		List<EntStyle> list = sqlQuery.list();
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	
	public EntStyle getEntStyleByType(int entId,String scType) {
		String sql = "SELECT "+
					"t1.es_id as \"esId\",t1.sc_id as \"scId\",t1.ent_id as \"entId\",t1.logo_image as \"logoImage\", "+
					"t1.bottom_info as \"bottomInfo\",t1.sc_type as \"scType\",t1.user_id as \"userId\",t1.login_view as \"loginView\", "+
					"t2.sc_name as \"scName\",t2.sc_css as \"scCss\",t1.create_time as \"createTime\" from t_ent_style t1,t_style_config t2 where t1.sc_id=t2.sc_id and t2.sc_type='"+scType+"' and t1.ent_id="+entId; 
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntStyle.class));
		
		List<EntStyle> list = sqlQuery.list();
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return null;
	}
	
	
	/**
	 * 根据主体Id 和风格类型 获取风格；
	 */
	public List<EntStyle> getEntStyleList(String mbDomain) throws AppException {
		
		String sql = "select es.es_id as \"esId\" " +
				", es.sc_id as \"scId\" " +
				", es.ent_id as \"entId\" " +
				", es.logo_image as \"logoImage\" " +
				", es.bottom_info as \"bottomInfo\" " +
				", es.sc_type as \"scType\" " +
				", es.user_id as \"userId\" " +
				", es.create_time as \"createTime\" " +
				", es.remark as \"remark\" " +
				", es.login_view as \"loginView\" " +
				", sc.sc_css as \"scCss\" " +
				" from t_ent_style es " +
				" left join t_style_config sc on es.sc_id = sc.sc_id" +
				" where es.ent_id=(select ent_id from t_ent_expand ep where ep.mb_domain = '"+mbDomain+"')";	
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntStyle.class));
		
		List<EntStyle> list = sqlQuery.list();
		
		System.out.println("查出的集合的大小：" + list.size());
		return list;
	}


	public EntStyle getEntStyleByDomain(String domain,String scType) {
		String sql = "SELECT "+
		"t1.es_id as \"esId\",t1.sc_id as \"scId\",t1.ent_id as \"entId\",t1.logo_image as \"logoImage\", "+
		"t1.bottom_info as \"bottomInfo\",t1.sc_type as \"scType\",t1.user_id as \"userId\",t1.login_view as \"loginView\", "+
		"t2.sc_name as \"scName\",t2.sc_css as \"scCss\",t1.create_time as \"createTime\" from t_ent_style t1,t_style_config t2 where t1.sc_id=t2.sc_id and t2.sc_type="+scType+" and t1.ent_id=(select ent_id from t_ent_expand where mb_domain='"+domain+"')"; 
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntStyle.class));
		
		List<EntStyle> list = sqlQuery.list();
		if(list!=null && !list.isEmpty())
		return list.get(0);
		return null;
	}
	
}
