package com.hontek.sys.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.StyleConfig;
import com.hontek.sys.pojo.TreeVo;

/**
 * 门户风格设置表
 * @author ZK
 *
 */
public class StyleConfigDao extends BaseDao<StyleConfig>{

	public void deleteByIds(String ids) {
		String hql = "delete from StyleConfig where scId in ("+ids+")";
		this.executeHql(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public List getStyleCombobox(String scTyle) {
		String sql = "select sc_id as \"id\",sc_name as \"text\" from t_style_config where state=1 and sc_type="+scTyle;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}
	

}
