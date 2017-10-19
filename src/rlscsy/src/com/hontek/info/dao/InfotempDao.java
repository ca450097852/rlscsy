package com.hontek.info.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.pojo.TInfotemp;

public class InfotempDao extends BaseDao<TInfotemp> {

	public Pager<TInfotemp> findInfotempList(String condition, int page, int rows) {
		
		String sql= "select type.TYPE_ID as typeId," +
			        "type.TYPE_NAME as typename," +
			        "info.T_ID as tid," +
			        "info.TITLE as title,"+
			        "info.CONTENT as content," +
			        "info.CRTTIME as crttime," +
			        "info.URL as url," +
			        "info.REMARK as remark," +
			        "info.SYS_CODE as sysCode from tb_infotemp info" +
			        " left join tb_infotype type on info.type_name = type.type_name where 1=1 ";
		sql = sql.concat(condition);
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("tid", StandardBasicTypes.LONG);
		query.addScalar("typename", StandardBasicTypes.STRING);
		query.addScalar("typeId", StandardBasicTypes.LONG);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("crttime", StandardBasicTypes.STRING);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("url", StandardBasicTypes.STRING);
		query.addScalar("sysCode", StandardBasicTypes.STRING);
		
		query.setResultTransformer(Transformers.aliasToBean(TInfotemp.class));
		String count_sql = "select count(*) from tb_infotemp info left join tb_infotype type on info.type_name = type.type_id where 1=1 "+condition;
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TInfotemp> info = new Pager<TInfotemp>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}
	/**
	 * 根据tId查出TInfotemp
	 * @param tid
	 * @return 一条临时资讯信息
	 */
	public TInfotemp getInfotempById(long tid) {
		String hql = "from TInfotemp";
		if(!"".equals(tid)){
			hql += "  where tid="+tid;
		}
		List list = this.find(hql);
		TInfotemp infotemp =null;
		if(list.size()>0){
			infotemp = (TInfotemp) list.get(0);
		}
		return infotemp;
	}
	
}
