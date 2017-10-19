package com.hontek.sys.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TsLog;
import com.hontek.sys.pojo.TsLogVo;

public class LogDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public Pager findLogList(Map mapCondition, int page, int rows) {
		String condition = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if("startDate".equals(key)){
						condition += " and log_time>='"+(String) obj+"'";
					}else if("endDate".equals(key)){
						condition += " and log_time<='"+(String) obj+"'";
					}else if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj
								+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
		}
				
		String sql = "select "
				+ "LOG_ID as \"logId\", tl.COL_ID as \"colId\", FUNC_NAME as \"funcName\", "
				+ "ACT_TYPE as \"actType\", tl.USER_ID as \"userId\",  tl.ENT_ID as \"entId\", "
				+ "LOG_TIME as \"logTime\", COMPUTER_IP as \"computerIp\", COL_NAME as \"colName\", "
				+ "USER_NAME as \"userName\", ACCOUNT as \"account\""
				+ " from  " + " ts_log tl left join ts_sys_col tc "
				+ "on (tl.col_id = tc.col_id) left join ts_user tu "
				+ "on (tl.user_id=tu.user_id) left join  "
				+ "ts_enterprise te on (tl.ent_id=te.ent_id)"+condition+ "order by LOG_TIME desc";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("logId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("colId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("funcName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("actType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("logTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("computerIp", StandardBasicTypes.STRING);
		sqlQuery.addScalar("colName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("userName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("account", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsLogVo.class));
		
		List list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		String sql_ct = "select count(*) ct from  " 
			+ " ts_log tl left join ts_sys_col tc "
			+ "on (tl.col_id = tc.col_id) left join ts_user tu "
			+ "on (tl.user_id=tu.user_id) left join  "
			+ "ts_enterprise te on (tl.ent_id=te.ent_id)"+condition;
		
		SQLQuery sqlQuery_ct = this.getCurrentSession().createSQLQuery(sql_ct);
		Object bigd =  sqlQuery_ct.uniqueResult();
		
		
		Pager pager = new Pager();
		pager.setData(list);
		pager.setTotal(Integer.valueOf(bigd.toString()));
		return pager;
	}

	@SuppressWarnings("unchecked")
	public void addSysLog(TsLog tslog) {
		this.save(tslog);
	}

}
