package com.hontek.complaint.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title:投诉举报 </p>
 * <p>Description:投诉举报 Dao  </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ComplaintDao extends BaseDao<TbComplaint>{
	
	
	/**
	* <p>Title: 添加举报</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	public void addComplaint(TbComplaint complaint){
		this.save(complaint);
	}
	

	/**
	* <p>Title: 举报列表</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	@SuppressWarnings("unchecked")
	public Pager<TbComplaint> findComplaintList(String condition, int page, int rows) throws AppException {
		String sql="select t1.C_ID as cid," +
		        "t1.ENT_ID as entId," +
		        "ent.NAME as entName, " +
		        "t2.OPINION as opinion," +
		        "t2.FINAL_RESULT as finalResult," +
		        "t2.DOUSER as douser," +
		        "t1.CONTENT as content," +
		        "t1.CRTTIME as crttime,"+
		        "t1.PHONE as phone," +
		        "t1.APP_NAME as appName," +
		        "t1.TITLE as title,"+
		        "t1.TYPENO as typeNo,"+
		        "t1.PRO_NAME as proName,"+
		        "t1.COMPANY_NAME as companyName,"+
		        "t1.USER_NAME as userName," +
		        "t1.USER_Addr as userAddr," +
		        "t1.REMARK as remark, " +
		        "t1.STS as sts," +
		        "t1.SYS_CODE as sysCode, " +
		        "t1.MAINENTID as mainentid " +
		        "from tb_complaint t1 " +
				"LEFT JOIN ts_enterprise ent on t1.ent_id = ent.ent_id " +
				//"LEFT JOIN ts_enterprise ep on t1.mainentid = ep.mainentid " +
				"LEFT JOIN tb_work_order t2  ON t1.c_id = t2.c_id " +
				"where 1=1 ";
		sql = sql.concat(condition); 
		sql+=" order by t1.CRTTIME desc";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.addScalar("cid", StandardBasicTypes.STRING);
		query.addScalar("entId", StandardBasicTypes.LONG);
		query.addScalar("entName", StandardBasicTypes.STRING);
		query.addScalar("opinion", StandardBasicTypes.STRING);
		query.addScalar("finalResult", StandardBasicTypes.STRING);
		query.addScalar("douser", StandardBasicTypes.STRING);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("proName", StandardBasicTypes.STRING);
		query.addScalar("companyName", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("crttime", StandardBasicTypes.STRING);
		query.addScalar("phone", StandardBasicTypes.STRING);
		query.addScalar("appName", StandardBasicTypes.STRING);
		query.addScalar("userName", StandardBasicTypes.STRING);
		query.addScalar("userAddr", StandardBasicTypes.STRING);
		query.addScalar("typeNo", StandardBasicTypes.STRING);
		
		query.addScalar("remark", StandardBasicTypes.STRING);
		query.addScalar("sts", StandardBasicTypes.LONG);
		query.addScalar("sysCode", StandardBasicTypes.STRING);
		query.addScalar("mainentid", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(TbComplaint.class));
		String count_sql = "select count(*) from tb_complaint t1 " +
				"LEFT JOIN ts_enterprise ent on t1.ent_id = ent.ent_id " +
				//"LEFT JOIN ts_enterprise ep on t1.mainentid = ep.mainentid " +
				"LEFT JOIN tb_work_order t2  ON t1.c_id = t2.c_id " +
				"where 1=1 ";
		count_sql = count_sql.concat(condition); 
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TbComplaint> info = new Pager<TbComplaint>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}
	/**
	 * 查出所有的区域名
	 */
	public List findAllEnterpriseName(){
		String sql ="select ep.ent_Id as \"entId\",ep.name as \"name\""+
		" from ts_enterprise ep where ep.flag = '1' ";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+" order by ep.name,ep.ent_code ");
		
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		List<TsEnterprise> list = sqlQuery.list();
		
		return list;
	}


	public Object findAllEntChildNameTree(String entId) {
		
		String sql ="select ep.ent_Id as \"entId\",ep.name as \"name\""+
		" from ts_enterprise ep where ep.flag = '1' and ep.parent_id = " + entId;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+" order by ep.name,ep.ent_code ");
		
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		List<TsEnterprise> list = sqlQuery.list();
		
		return list;
	}
}
