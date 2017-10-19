package com.hontek.info.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;


import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.info.pojo.TInfo;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 资讯 DAO
 * @author qql
 *
 */
public class InfoDao extends BaseDao<TInfo>{
	
	public TInfo getInfoById(long infos) {
		String hql = "from TInfo";
		if(!"".equals(infos)){
			hql += "  where infoId="+infos;
		}
		List list = this.find(hql);
		TInfo info =null;
		if(list.size()>0){
			info = (TInfo) list.get(0);
		}
		return info;
	}
	
	/**
	 *资讯列表
	 * @param condition
	 * @return
	 */
	public List findNews(String condition){
		String sql="select type.TYPE_ID as typeId," +
        "type.TYPE_NAME as typeName," +
        "info.INFO_ID as infoId," +
        "info.TITLE as title,"+
        "info.CONTENT as content," +
        "info.SEQ as seq," +
        "info.USER_ID as userId," +
        "info.CRTTIME as crttime," +
        "info.AUDITOR as auditor," +
        "info.AUDI_DATE as audiDate," +
        "info.OPINION as opinion," +
        "info.RSTS as rsts," +
        "info.REMARK as remark," +
        "info.SYS_CODE as sysCode from tb_info info,tb_infotype type where info.type_id = type.type_id ";
		sql = sql.concat(condition);
		sql+=" order by info.CRTTIME desc";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("typeId", StandardBasicTypes.LONG);
		query.addScalar("typeName", StandardBasicTypes.STRING);
		query.addScalar("infoId", StandardBasicTypes.LONG);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("seq", StandardBasicTypes.LONG);
		query.addScalar("userId", StandardBasicTypes.STRING);
		query.addScalar("crttime", StandardBasicTypes.STRING);
		query.addScalar("auditor", StandardBasicTypes.STRING);
		query.addScalar("audiDate", StandardBasicTypes.STRING);
		query.addScalar("opinion", StandardBasicTypes.STRING);
		query.addScalar("rsts", StandardBasicTypes.LONG);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("sysCode", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(TInfo.class));
		
		List list = query.list();
		
		for (Object object : list) {
			System.out.println(object.getClass());
		}
		System.out.println(list.size());
		

		return list;
	}
	
	/**
	 * 资讯列表 
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public Pager<TInfo> findInfoList(String condition, int page, int rows) throws AppException {
		
		String sql= "select type.TYPE_ID as typeId," +
			        "type.TYPE_NAME as typeName," +
			        "info.INFO_ID as infoId," +
			        "tu.NICKNAME as nickName," +
			        "info.TITLE as title,"+
			        "info.CONTENT as content," +
			        "info.SEQ as seq," +
			        "info.USER_ID as userId," +
			        "info.CRTTIME as crttime," +
			        "info.AUDITOR as auditor," +
			        "info.AUDI_DATE as audiDate," +
			        "info.OPINION as opinion," +
			        "info.RSTS as rsts," +
			        "info.REMARK as remark," +
			        "info.title_img as titleImg," +
			        "info.ENT_ID as entId," +
			        "info.SYS_CODE as sysCode from tb_info info" +
			        " left join ts_user tu on info.user_id=tu.user_id"+
			        " left join ts_enterprise te on info.ent_id=te.ent_id"+
			        " left join tb_infotype type on info.type_id = type.type_id where 1=1 ";
		sql = sql.concat(condition);
		sql+=" order by info.CRTTIME desc";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("typeId", StandardBasicTypes.LONG);
		query.addScalar("typeName", StandardBasicTypes.STRING);
		query.addScalar("nickName", StandardBasicTypes.STRING);
		query.addScalar("infoId", StandardBasicTypes.LONG);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("seq", StandardBasicTypes.LONG);
		query.addScalar("userId", StandardBasicTypes.STRING);
		query.addScalar("crttime", StandardBasicTypes.STRING);
		query.addScalar("auditor", StandardBasicTypes.STRING);
		query.addScalar("audiDate", StandardBasicTypes.STRING);
		query.addScalar("opinion", StandardBasicTypes.STRING);
		query.addScalar("rsts", StandardBasicTypes.LONG);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("sysCode", StandardBasicTypes.LONG);
		query.addScalar("titleImg", StandardBasicTypes.STRING);
		query.addScalar("entId", StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(TInfo.class));
		String count_sql = "select count(*) from tb_info info left join ts_user tu on info.user_id=tu.user_id left join ts_enterprise te on info.ent_id=te.ent_id left join tb_infotype type on info.type_id = type.type_id where 1=1 "+condition;
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TInfo> info = new Pager<TInfo>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}

}
