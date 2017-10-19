package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.sys.pojo.ComboboxData;
/**
 * 批次信息DAO
 *
 */
public class ProTypeBatchDao extends BaseDao<TbProTypeBatch>{

	
	public  List<String> findDimenNoByPtBId(int ptbId){
		String sql ="SELECT dimenno from tb_pro_type_batch where 1=1";
		if(ptbId!=0){
			sql+=" and ptb_id="+ptbId;
		}
		
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		List<String> ptbIdList=query.list();
		
		return ptbIdList;
		
	}
	public  List<Integer> findPtBIdByProTypeBatch(int ptbId){
				String sql = "select t2.ptb_id " +
						 " from tb_market_in_info_base t1, tb_meat_out_info_base t2"+
						" where  1=1 and t2.flag='1' and t2.tran_id=t1.tran_id";
				
				if(ptbId!=0){
					sql+=" and t1.ptb_id="+ptbId;
				}
				
				
				SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
					List<Integer> ptbIdList=query.list();
				
				return ptbIdList;
			
			}
	
	public  List<Integer> findPtBIdByProOut(int ptbId){
		String sql = "select t2.ptb_id"+
				 " from tb_market_in_info_base t1,tb_meat_out_info_base t2"+
				 " where  1=1 and t2.tran_id=t1.tran_id";
		
		if(ptbId!=0){
			sql+=" and t1.ptb_id="+ptbId;
		}
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		List<Integer> ptbIdList=query.list();
		return ptbIdList;
	}
	

	public Pager<TbProTypeBatch> findProTypeBatch(TbProTypeBatch proTypeBatch,int page, int rows){
System.out.println("lllllllllllllllll");
		String sql = "select " +
					"t1.ptq_id as ptqId," +
					"t1.ent_id as entId," +
					"t1.ptb_id as ptbId," +
					"t1.dimenno as dimenno," +
					"t1.url as url," +
					"t1.code_img as codeImg," +
					"t1.batch_name as batchName," +
					"t1.batch_time as batchTime," +
					"t1.batch_state as batchState," +
					//"t2.rec_id as recId, " +					
					"t1.submit_time as submitTime, " +
					"t1.audit_time as auditTime, " +
					"t1.audit_ent_id as auditEntId " +				
					"from tb_pro_type_batch t1 where 1=1 ";		
		String count_sql = "select count(*) from tb_pro_type_batch t1 where 1=1 ";
		
		if(proTypeBatch!=null){
			if(proTypeBatch.getPtqId()!=null&&proTypeBatch.getPtqId()>0){
				sql+=" and t1.ptq_Id="+proTypeBatch.getPtqId();
				count_sql+=" and t1.ptq_Id="+proTypeBatch.getPtqId();
			}
			if(proTypeBatch.getEntId()>0){
				sql+=" and t1.ent_id="+proTypeBatch.getEntId();
				count_sql+=" and t1.ent_id="+proTypeBatch.getEntId();
			}
			
		}
		
		sql+=" order by t1.batch_time desc";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeBatch.class));
		List<TbProTypeBatch> list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();

		int count = this.countBySql(count_sql).intValue();

		Pager<TbProTypeBatch> pager = new Pager<TbProTypeBatch>();
		pager.setData(list);
		pager.setTotal(count);
		
		return pager;
	
	}
	
	public List<TbProTypeBatch> findProTypeBatchList(int ptqId){

		String sql = "select " +
					"t1.ptq_id as ptqId," +
					"t1.ent_id as entId," +
					"t1.ptb_id as ptbId," +
					"t1.dimenno as dimenno," +
					"t1.url as url," +
					"t1.code_img as codeImg," +
					"t1.batch_name as batchName," +
					"t1.batch_time as batchTime," +
					"t1.batch_state as batchState," +
					"t2.rec_id as recId " +
					"from tb_pro_type_batch t1,tb_record t2 " +
					"where t1.ptb_id=t2.obj_ID and obj_type_id=5 and t1.ptq_Id="+ptqId;
		
					sql+=" order by t1.batch_time desc";

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeBatch.class));
		
		List<TbProTypeBatch> list = query.list();
		
		return list;
	
	}
	
	public List<TbProTypeBatch> findProTypeBatchListByTerm(String term){
		term = term==null?"":term;

		String sql = "select " +
					"t1.ptq_id as ptqId," +
					"t1.ent_id as entId," +
					"t1.ptb_id as ptbId," +
					"t1.dimenno as dimenno," +
					"t1.url as url," +
					"t1.code_img as codeImg," +
					"t1.batch_name as batchName," +
					"t1.batch_time as batchTime," +
					"t1.batch_state as batchState," +
					"t2.rec_id as recId " +
					"from tb_pro_type_batch t1,tb_record t2 " +
					"where t1.ptb_id=t2.obj_ID and obj_type_id=5 "+term;
		
					sql+=" order by t1.batch_time desc";

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeBatch.class));
		
		List<TbProTypeBatch> list = query.list();
		
		return list;
	
	}

	public String getMaxDimenno(String dimenno) {
		String sql = "SELECT max(dimenno) from tb_pro_type_batch where dimenno like '"+dimenno+"%'";
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		Object obj = query.uniqueResult();
		if(obj!=null){
			return obj.toString();
		}/*else{
			try {
				String rest = obj.toString();
				String seqStr = rest.substring(rest.length()-2);
				return String.valueOf(Integer.parseInt(seqStr)+101).substring(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}*/
		return null;
	}

	public List<ComboboxData> getCombobox(String entId) {
		String sql = "SELECT rec_id as \"id\" ,batch_name as \"text\" from tb_record r,tb_pro_type_batch b where r.obj_id=b.ptb_id and obj_type_id=5 and ent_id="+entId;
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(ComboboxData.class));
		List<ComboboxData> list = query.list();
		return list;
	}
	
	

	public List<TbProTypeBatch> findListByTerm(String term){
		term = term==null?"":term;

		String sql = "select " +
					"t1.ptq_id as ptqId," +
					"t1.ent_id as entId," +
					"t1.ptb_id as ptbId," +
					"t1.dimenno as dimenno," +
					"t1.url as url," +
					"t1.code_img as codeImg," +
					"t1.batch_name as batchName," +
					"t1.batch_time as batchTime," +
					"t1.batch_state as batchState " +
					//"t2.rec_id as recId " +
					"from tb_pro_type_batch t1  " +
					"where 1=1 "+term;
		
					sql+=" order by t1.batch_time desc";

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeBatch.class));
		
		List<TbProTypeBatch> list = query.list();
		
		return list;
	
	}
	
	
}
