package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AuditRecord;
import com.hontek.record.pojo.ProAndBatchRecord;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbRecord;

/**
 * 档案DAO
 * @author lzk
 *
 */
public class RecordDao extends BaseDao<TbRecord>{

	public Pager<TbRecord> findRecordList(String condition, int page, int rows) {
		
		condition = condition==null?"":condition;
		
		String hql = "from TbRecord where 1=1"+condition;
		
		System.out.println("hql === "+hql);
		
		List<TbRecord> list = find(hql, page, rows);
		
		
		if(condition.contains("order by")){
			condition = condition.substring(0, condition.indexOf("order by"));
		}
		
		String hql_ct = "select count(*) from TbRecord where 1=1 "+condition;
		int total = this.count(hql_ct).intValue();
		Pager<TbRecord> pager = new Pager<TbRecord>();
		pager.setData(list);
		pager.setTotal(total);
		return pager;
	}
	
	/**
	 * 根据分类二维码查询档案
	 * @param @param condition
	 * @param @param page
	 * @param @param rows
	 * @param @return 
	 * @return List<TbRecord>    返回类型
	 * @throws
	 */
	public List<TbRecord> findRecordListByPtqDimennno(String dimenno) {
		
		
		String sql="select " +
			"t1.rec_id as recId," +
	        "t1.rec_name as recName," +
	        "t1.crttime as crttime," +
	        "t1.seq as seq,"+
	        "t1.crt_user as crtUser,"+
	        "t1.rec_sts as recSts "+
	        "from tb_record t1,tb_pro_type_qrcode t2 " +
			"where 1=1 and t1.obj_id=t2.ptq_id  " +
			"and t2.dimenno='"+dimenno+"' order by t1.seq ";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(TbRecord.class));
		List<TbRecord> list = query.list();
		
		return list;
	}
/**
 * 删除档案
 * @param ids
 */
	public void deleteRecord(String ids) {
		String hql = "delete from TbRecord where recId in ("+ids+")";
		this.executeHql(hql);
	}

	public Pager<TbRecord> findRecordTypeList(String condition,String entId, int page, int rows) {
		condition = condition==null?"":condition;
		
		String sql = "select rec_id as recId,rec_name as recName,crttime,seq,crt_user as crtUser,rec_sts as recSts,obj_id as objId,obj_type_id as objTypeId "+
			" ,p.pro_name as proName,p.pro_name as typeName "+
			" FROM "+
			" tb_record r,("+
			" SELECT ptq_id,pro_name FROM tb_pro_type_qrcode t1 where  t1.ent_id="+entId+
			" ) p "+
			"where obj_id=p.ptq_id and obj_type_id in (2,3,4) "+condition;
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(TbRecord.class));
		List<TbRecord> list = query.list();
		
		String sql_ct = "select count(*) FROM "+
		" tb_record r,("+
		" SELECT ptq_id,pro_name FROM tb_pro_type_qrcode t1 where  t1.ent_id="+entId+
		" ) p "+
		"where obj_id=p.ptq_id and obj_type_id in (2,3,4) "+condition;
		
		int total = this.countBySql(sql_ct);
		
		Pager<TbRecord> pager = new Pager<TbRecord>();
		
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

	public Pager<TbRecord> getAuditRecord(int entId ,int page, int rows) {
		
		String condition = "";
		if(entId != 0){
			condition = " and parent_id="+entId +" or parent_id in (select ent_id from ts_enterprise where parent_id="+entId+")";
		}
		
		String sql = "SELECT rec_id as recId,rec_name as recName,crttime,seq,crt_user as crtUser,rec_sts as recSts,obj_id as objId,obj_type_id as objTypeId, "+ 
					 " ptb.ent_id as entId,(select name from ts_enterprise where ent_id = ptb.ent_id) as entName,ptb.ptq_id as ptqId "+
					 " from tb_record re,tb_pro_type_batch ptb,tb_pro_type_qrcode ptq where "+  
					 " obj_type_id=5 and ptb_id = re.obj_id "+  
					 " and batch_state = '1'  "+
					 " and ptb.ptq_id = ptq.ptq_id and ptq.is_batch = 1 "+
					 " and ptb.ent_id in (SELECT ent_id FROM ts_enterprise where 1=1 "+condition+" and body_ent_id in (select ent_id from t_ent_expand where audit_batch='1')) "+
					 " UNION "+
					 " SELECT rec_id as recId,rec_name as recName,crttime,seq,crt_user as crtUser,rec_sts as recSts,obj_id as objId,obj_type_id as objTypeId,  "+
					 " ent_id as entId,(select name from ts_enterprise where ent_id = ptq.ent_id) as entName,ptq_id as ptqId  from tb_record re,tb_pro_type_qrcode ptq where   "+
					 " re.obj_type_id in (2,3,4) and obj_id = ptq.ptq_id  "+
					 " and ptq.is_batch = 0 and pro_state = '1' "+
					 " and ptq.ent_id in (SELECT ent_id FROM ts_enterprise where  1=1 "+condition+" and body_ent_id in (select ent_id from t_ent_expand where audit_pro='1'))";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(TbRecord.class));
		List<TbRecord> list = query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		String sql_ct = "SELECT count(*) from tb_record re,tb_pro_type_batch ptb,tb_pro_type_qrcode ptq where "+  
						 " obj_type_id=5 and ptb_id = re.obj_id "+  
						 " and batch_state = '1'  "+
						 " and ptb.ptq_id = ptq.ptq_id and ptq.is_batch = 1 "+
						 " and ptb.ent_id in (SELECT ent_id FROM ts_enterprise where 1=1 "+condition+" and body_ent_id in (select ent_id from t_ent_expand where audit_batch='1')) ";
		int total = this.countBySql(sql_ct);
		
		sql_ct = "SELECT count(*) from tb_record re,tb_pro_type_qrcode ptq where   "+
					 " re.obj_type_id in (2,3,4) and obj_id = ptq.ptq_id  "+
					 " and ptq.is_batch = 0 and pro_state = '1' "+
					 " and ptq.ent_id in (SELECT ent_id FROM ts_enterprise where  1=1 "+condition+" and body_ent_id in (select ent_id from t_ent_expand where audit_pro='1'))";
		
		total += this.countBySql(sql_ct);
		
		Pager<TbRecord> pager = new Pager<TbRecord>();
		
		pager.setData(list);
		pager.setTotal(total);
		
		return pager;
	}

	public List<ProAndBatchRecord> getLoginAuditRecord(String entId) {
		String sql = "SELECT ptq_id as objId,pro_name+'生产档案' as name,pro_name as typeName,dimenno,crrtime as crttime,pro_state as state,submit_time as submitTime,audit_time as auditTime,0 as isbatch FROM tb_pro_type_qrcode ptq where ent_id="+entId+" and is_batch = 0 "+ 
					" and 1=(select audit_pro from t_ent_expand where ent_id = (SELECT body_ent_id from ts_enterprise where ent_id = "+entId+")) "+
					" UNION "+
					" SELECT ptb_id as objId,batch_name as name,pro_name as typeName,ptb.dimenno,batch_time as crttime,batch_state as state, "+
					" ptb.submit_time as submitTime,ptb.audit_time as auditTime,1 as isbatch from tb_pro_type_batch ptb ,tb_pro_type_qrcode ptq "+ 
					" where ptb.ent_id="+entId+" and ptb.ptq_id=ptq.ptq_id  "+
					" and 1=(select audit_batch from t_ent_expand where ent_id = (SELECT body_ent_id from ts_enterprise where ent_id = "+entId+"))"; 

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
//		query.addScalar("objId", StandardBasicTypes.INTEGER);
//		query.addScalar("name", StandardBasicTypes.STRING);
//		query.addScalar("typeName", StandardBasicTypes.STRING);
//		query.addScalar("dimenno", StandardBasicTypes.STRING);
//		query.addScalar("crttime", StandardBasicTypes.STRING);
//		query.addScalar("state", StandardBasicTypes.STRING);
//		query.addScalar("submitTime", StandardBasicTypes.STRING);
//		query.addScalar("auditTime", StandardBasicTypes.STRING);
//		query.addScalar("isbatch", StandardBasicTypes.INTEGER);
		
		
		
		query.setResultTransformer(Transformers.aliasToBean(ProAndBatchRecord.class));
		List<ProAndBatchRecord> list = query.list();
		return list;
	}
	
	
	

/*bak:20160826
public Pager<TbRecord> findRecordTypeList(String condition,String entId, int page, int rows) {
	condition = condition==null?"":condition;
	
	String sql = "select rec_id as recId,rec_name as recName,crttime,seq,crt_user as crtUser,rec_sts as recSts,obj_id as objId,obj_type_id as objTypeId "+
		" ,p.type_name as typeName "+
		" FROM "+
		" tb_record r,("+
		" SELECT ptq_id,type_name FROM tb_pro_type_qrcode t1,tb_pro_type t2 where t1.type_id=t2.type_id and t1.ent_id="+entId+
		" ) p "+
		"where obj_id=p.ptq_id and obj_type_id in (3,4) "+condition;
	
	SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
	query.setResultTransformer(Transformers.aliasToBean(TbRecord.class));
	List<TbRecord> list = query.list();
	
	String sql_ct = "select count(*) FROM "+
	" tb_record r,("+
	" SELECT ptq_id,type_name FROM tb_pro_type_qrcode t1,tb_pro_type t2 where t1.type_id=t2.type_id and t1.ent_id="+entId+
	" ) p "+
	"where obj_id=p.ptq_id and obj_type_id in (3,4) "+condition;
	
	int total = this.countBySql(sql_ct);
	
	Pager<TbRecord> pager = new Pager<TbRecord>();
	
	pager.setData(list);
	pager.setTotal(total);
	
	return pager;
}
*/
	
	
	

}
