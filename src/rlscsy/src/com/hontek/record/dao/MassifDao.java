package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbMassif;
/**
 * 地块信息DAO
 *
 */
public class MassifDao extends BaseDao<TbMassif>{

	/**
	 * 查询基地对应的地块信息
	 * @param ptaId
	 * @return
	 */
	public List<TbMassif> findMassifList(int ptaId){
		String hql = "from TbMassif WHERE ptaId ="+ptaId;
		return find(hql);
	}
	
	
	/**
	 * 查询种类对应的地块信息
	 * @param ptaId
	 * @return
	 */
	public List<TbMassif> findListByPtqId(int ptqId){
		
		String sql="select " +
				"t1.ma_id as maId," +
		        "t1.pta_id as ptaId," +
		        "t1.ma_name as maName," +
		        "t1.ma_acreage as maAcreage,"+
		        "t1.start_time as startTime,"+
		        "t1.get_time as getTime,"+
		        "t1.state as state, " +
		        "t1.ma_img as maImg,"+
		        "t1.ptq_id as ptqId,"+
		        "t4.pro_name as typeName, "+
		        "t3.area_name as areaName "+
		        "from tb_massif t1,tb_pro_type t2,tb_pro_type_area t3, tb_pro_type_qrcode t4 " +
				"where t1.ptq_id = t4.ptq_id and t2.type_id = t4.type_id and t3.ent_id = t4.ent_id and t3.pta_id = t1.pta_id and t3.pta_id = t1.pta_id and t1.ptq_id = " +ptqId;

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("maId", StandardBasicTypes.INTEGER);
		query.addScalar("ptaId", StandardBasicTypes.INTEGER);
		query.addScalar("ptqId", StandardBasicTypes.INTEGER);

		
		query.addScalar("maName", StandardBasicTypes.STRING);
		query.addScalar("maAcreage", StandardBasicTypes.STRING);
		query.addScalar("startTime", StandardBasicTypes.STRING);
		query.addScalar("getTime", StandardBasicTypes.STRING);
		query.addScalar("state", StandardBasicTypes.STRING);
		query.addScalar("maImg", StandardBasicTypes.STRING);
		query.addScalar("typeName", StandardBasicTypes.STRING);
		query.addScalar("areaName", StandardBasicTypes.STRING);
		
		query.setResultTransformer(Transformers.aliasToBean(TbMassif.class));
		
		List<TbMassif> list = query.list();
		
		return list;
	}
	
	/**
	* <p>Title: 档案要素列表</p> 
	* <p>Description: </p> 
	* param 
	* return
	*/
	@SuppressWarnings("unchecked")
	public Pager<TbMassif> findMassifList(String ptaId, int page, int rows) throws AppException {
		String sql="select " +
				"t1.ma_id as maId," +
		        "t1.pta_id as ptaId," +
		        "t1.ma_name as maName," +
		        "t1.ma_acreage as maAcreage,"+
		        "t1.start_time as startTime,"+
		        "t1.get_time as getTime,"+
		        "t1.state as state, " +
		        "t1.ma_img as maImg,"+
		        "t1.ptq_id as ptqId,"+
		        "t4.pro_name as typeName "+
		        "from tb_massif t1,tb_pro_type t2,tb_pro_type_area t3, tb_pro_type_qrcode t4 " +
				"where t1.ptq_id = t4.ptq_id and t2.type_id = t4.type_id and t3.ent_id = t4.ent_id and t3.pta_id = t1.pta_id and t3.pta_id="+ptaId;

		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("maId", StandardBasicTypes.INTEGER);
		query.addScalar("ptaId", StandardBasicTypes.INTEGER);
		query.addScalar("ptqId", StandardBasicTypes.INTEGER);

		
		query.addScalar("maName", StandardBasicTypes.STRING);
		query.addScalar("maAcreage", StandardBasicTypes.STRING);
		query.addScalar("startTime", StandardBasicTypes.STRING);
		query.addScalar("getTime", StandardBasicTypes.STRING);
		query.addScalar("state", StandardBasicTypes.STRING);
		query.addScalar("maImg", StandardBasicTypes.STRING);
		query.addScalar("typeName", StandardBasicTypes.STRING);
		
		query.setResultTransformer(Transformers.aliasToBean(TbMassif.class));
		String count_sql = "select count(*) from tb_massif t1,tb_pro_type t2,tb_pro_type_area t3, tb_pro_type_qrcode t4 " +
				"where t1.ptq_id = t4.ptq_id and t2.type_id = t4.type_id and t3.ent_id = t4.ent_id and t3.pta_id = t1.pta_id and t3.pta_id="+ptaId;
		List list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(count_sql).intValue();
		Pager<TbMassif> info = new Pager<TbMassif>();
		info.setData(list);
		info.setTotal(count);
		return info;
	}
	
}
