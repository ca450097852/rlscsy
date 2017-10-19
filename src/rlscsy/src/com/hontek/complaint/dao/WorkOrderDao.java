package com.hontek.complaint.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.complaint.pojo.TbWorkOrder;

/**
 * <p>Title:举报工单 </p>
 * <p>Description:举报工单 Dao  </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class WorkOrderDao extends BaseDao<TbWorkOrder>{
	
	
	/**
	* <p>Title: 添加工单</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public void addWorkOrder(TbWorkOrder workOrder){
		this.save(workOrder);
	}

	/**
	* <p>Title: 工单列表</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	@SuppressWarnings("unchecked")
	public Pager<TbWorkOrder> findWorkOrderList(String condition, int page, int rows) throws AppException {
		String sql="select wo.WO_ID as woId," +
		        "wo.C_ID as cid," +
		        "wo.OPINION as opinion," +
		        "wo.DOTIME as dotime,"+
		        "wo.FINAL_RESULT as finalResult," +
		        "wo.DOUSER as douser," +
		        "wo.REMARK as remark, " +
		        "cp.TITLE as title, " +
		        "cp.PRO_NAME as proName,"+
		        "cp.COMPANY_NAME as companyName,"+
		        "cp.CONTENT as content, " +
		        "wo.STS as sts " +
		        "from tb_work_order wo " +
		        " left join tb_complaint cp on wo.C_ID = cp.C_ID "+
		        " where 1=1 ";
		sql = sql.concat(condition); 
		sql+=" order by wo.WO_ID desc";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("woId", StandardBasicTypes.LONG);
		query.addScalar("cid", StandardBasicTypes.STRING);
		query.addScalar("opinion", StandardBasicTypes.STRING);
		query.addScalar("dotime", StandardBasicTypes.STRING);
		query.addScalar("finalResult", StandardBasicTypes.STRING);
		query.addScalar("douser", StandardBasicTypes.STRING);
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("proName", StandardBasicTypes.STRING);
		query.addScalar("companyName", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("sts", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(TbWorkOrder.class));
		String count_sql = "select count(*) from tb_work_order wo  left join tb_complaint cp on wo.C_ID = cp.C_ID where 1=1";
		count_sql = count_sql.concat(condition); 
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TbWorkOrder> info = new Pager<TbWorkOrder>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}
	
	/**
	* <p>Title: 统计工单</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public int countWorkOrder(String condition) throws AppException {
		String count_sql = "select count(*) from tb_work_order wo  left join tb_complaint cp on wo.C_ID = cp.C_ID where 1=1";
		count_sql = count_sql.concat(condition); 
		int count = this.countBySql(count_sql).intValue();
		return count;
	}

}
