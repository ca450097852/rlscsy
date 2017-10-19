package com.hontek.record.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.Combo;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.review.pojo.TreeVo;
/**
 * 分类二维码信息DAO
 * @author lzk
 *
 */
public class ProTypeQrcodeDao extends BaseDao<TbProTypeQrcode>{
	
	/**
	 * 企业分类溯源列表
	 */
	@SuppressWarnings("unchecked")
	public Pager<TbProTypeQrcode> findProTypeQrcodeList(String condition, int page, int rows) {
		
		condition = condition==null?"":condition;
		
		String sql = "select " +
				"t1.ptq_id as ptqId," +
				"t1.ent_id as entId," +
				"t1.type_id as typeId," +
				"t1.dimenno as dimenno," +
				"t1.url as url," +
				"t1.code_img as codeImg," +
				"t1.crrtime as crrtime," +
				"t1.certificate as certificate," +
				"t1.quantity as quantity," +
				"t1.listed as listed," +
				"t1.salearea as salearea," +
				"t1.type_img as typeImg," +
				"t2.name as companyName, " +
				"t2.legal_person as legalPerson, " +
				"t2.reg_addr as regAddr, " +
				"t2.manage_addr as manageAddr, " +
				"t2.tel as tel, " +
				"t2.email as email, " +
				"t2.dom_name as domName, " +
				"t2.intro as intro, " +
				"t1.pro_name as typeName, " +
				
				"t1.pro_state as proState, " +
				"t1.submit_time as submitTime, " +
				"t1.audit_time as auditTime, " +
				"t1.audit_ent_id as auditEntId " +
				
				
				"from tb_pro_type_qrcode t1,ts_enterprise t2 ,tb_pro_type t3 " +
				"where t1.ent_id=t2.ent_id and t1.type_id=t3.type_id "+condition;
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeQrcode.class));
		
		List<TbProTypeQrcode> list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
				
		String hql_ct = "select count(*) " +
						"from tb_pro_type_qrcode t1,ts_enterprise t2 ,tb_pro_type t3  " +
						"where t1.ent_id=t2.ent_id and t1.type_id=t3.type_id "+condition;
		
		int total = this.countBySql(hql_ct).intValue();
		
		Pager<TbProTypeQrcode> pager = new Pager<TbProTypeQrcode>();
		
		pager.setData(list);
		
		pager.setTotal(total);
		
		return pager;
	}
	
	
	/**
	 * 企业分类溯源列表
	 */
	@SuppressWarnings("unchecked")
	public Pager<TbProTypeQrcode> findTwoProTypeQrcode(String condition,int page, int rows) {
		condition = condition==null?"":condition;
		String sql = "select " +
				"t1.ptq_id as ptqId," +
				"t1.ent_id as entId," +
				"t1.type_id as typeId," +
				"t1.dimenno as dimenno," +
				"t1.pro_name as proName," +
				//"t1.code_img as codeImg," +
				"t1.crrtime as crrtime," +
				//"t1.certificate as certificate," +
				//"t1.quantity as quantity," +
				"t1.listed as listed," +
				//"t1.salearea as salearea," +
				"t1.type_img as typeImg," +
				"t2.name as companyName, " +
				//"t2.legal_person as legalPerson, " +
				"t2.addr as regAddr, " +
				//"t2.manage_addr as manageAddr, " +
				//"t2.tel as tel, " +
				//"t2.email as email, " +
				//"t2.dom_name as domName, " +
				//"t2.intro as intro, " +
				"t3.type_name as typeName ," +
				"t3.type_class as typeClass, " +
				
				"t1.pro_state as proState, " +
				"t1.submit_time as submitTime, " +
				"t1.audit_time as auditTime, " +
				"t1.audit_ent_id as auditEntId " +
				
				"from tb_pro_type_qrcode t1,tb_company t2 ,tb_pro_type t3 " +
				"where t1.ent_id=t2.com_id and t1.type_id=t3.type_id "+condition+" order by t1.crrtime desc";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeQrcode.class));
		
		List<TbProTypeQrcode> list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();
		
		String Countsql = "select count(*) from tb_pro_type_qrcode t1,tb_company t2 ,tb_pro_type t3 " +
						  "where t1.ent_id=t2.com_id and t1.type_id=t3.type_id "+condition;
		
		int count = countBySql(Countsql);
		
		Pager<TbProTypeQrcode> pager = new Pager<TbProTypeQrcode>();
		
		pager.setData(list);
		pager.setTotal(count);
		
		return pager;
	}
	
	
	
	/**
	 * 企业分类二维码溯源列表
	 * 包括一些二维码种类设置信息
	 */
	@SuppressWarnings("unchecked")
	public List<TbProTypeQrcode> findProTypeQrcodeList(String condition) {
		condition = condition==null?"":condition;
		String sql = "select " +
				"t1.ptq_id as ptqId," +
				"t1.ent_id as entId," +
				"t1.type_id as typeId," +
				"t1.dimenno as dimenno," +
				"t1.pro_name as proName," +
				//"t1.code_img as codeImg," +
				"t1.crrtime as crrtime," +
				"t1.certificate as certificate," +
				"t1.quantity as quantity," +
				"t1.listed as listed," +
				"t1.salearea as salearea," +
				"t1.type_img as typeImg," +
				"t2.name as companyName, " +
				//"t2.legal_person as legalPerson, " +
				"t2.addr as regAddr, " +
				//"t2.manage_addr as manageAddr, " +
				//"t2.tel as tel, " +
				//"t2.email as email, " +
				//"t2.dom_name as domName, " +
				//"t2.intro as intro, " +
				"t3.type_name as typeName ," +
				"t3.type_class as typeClass, " +
				
				"t1.pro_state as proState, " +
				"t1.submit_time as submitTime, " +
				"t1.audit_time as auditTime, " +
				"t1.audit_ent_id as auditEntId " +
				
				"from tb_pro_type_qrcode t1,tb_company t2 ,tb_pro_type t3 " +
				"where t1.ent_id=t2.com_id and t1.type_id=t3.type_id "+condition+" order by t1.crrtime desc";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeQrcode.class));
		
		List<TbProTypeQrcode> list = query.list();
		
		return list;
	}

	//判断是否有数据
	public boolean checkData(int entId, String typeId) {
		
		String hql = "select count(*) from TbProTypeQrcode where typeId="+typeId+" and entId="+entId;
		
		int ct = this.count(hql).intValue();
		
		return ct==0;
	}
	
	
	public List<Object> findTypeName(String recId){
		String sql ="SELECT type_name FROM tb_pro_type WHERE type_id IN (SELECT type_id FROM tb_pro_type_qrcode WHERE ptq_id = (SELECT obj_ID FROM tb_record WHERE rec_id = "+recId+" AND obj_type_id IN (2, 3)))";
		List<Object> list = findObjectListBySql(sql);
		return list;
	}

	public List<TbProTypeQrcode> getLoginProTypeQrcode(String condition) {
//		String sql = "select " +
//					"t1.ptq_id as ptqId," +
//					"t1.ent_id as entId," +
//					"t1.type_id as typeId," +
//					"t1.dimenno as dimenno," +
//					"t1.url as url," +
//					"t1.code_img as codeImg," +
//					"t1.crrtime as crrtime," +
//					"t1.certificate as certificate," +
//					"t1.quantity as quantity," +
//					"t1.listed as listed," +
//					"t1.salearea as salearea," +
//					"t1.type_img as typeImg," +
//					"t2.type_name as typeName " +
//					"from tb_pro_type_qrcode t1,tb_pro_type t2 " +
//					"where t1.type_id=t2.type_id "+condition;
		String sql = "select " +
					"t1.ptq_id as ptqId," +
					"t1.ent_id as entId," +
					"t1.type_id as typeId," +
					"t1.dimenno as dimenno," +
					"t1.url as url," +
					"t1.code_img as codeImg," +
					"t1.crrtime as crrtime," +
					"t1.certificate as certificate," +
					"t1.quantity as quantity," +
					"t1.listed as listed," +
					"t1.salearea as salearea," +
					"t1.type_img as typeImg," +
					"t1.pro_name as typeName, " +
					"t1.pro_Desc as proDesc, " +
					"t1.brand_Name as brandName " +
					"from tb_pro_type_qrcode t1 " +
					"where 1=1 "+condition;
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TbProTypeQrcode.class));
		
		List<TbProTypeQrcode> list = query.list();
		
		return list;
	}
	
	public Object[] findProTypeCode(String typeName,int entId){
		String sql = "SELECT t2.ptq_id,t3.rec_ID FROM tb_pro_type t1,tb_pro_type_qrcode t2 ,tb_record t3 WHERE t1.type_id = t2.type_id and t2.ptq_id =t3.obj_id and t3.obj_type_id=4 AND t1.type_name='"+typeName+"' and t2.ent_id="+entId;
		
		List<Object[]> obj = findBySql(sql);
		
		if(!obj.isEmpty()){
			return obj.get(0);
		}
		
		
		return null;
	}
	
	
	
	public List<Combo> getLoginProTypeList(int entId){
		List<Combo> list = null;		
		try {
			String sql="select t2.ptq_id as value,t2.pro_name as text from tb_pro_type_qrcode t2	where  t2.ent_id="+entId;
			SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
			sqlQuery.addScalar("value", StandardBasicTypes.STRING);
			sqlQuery.addScalar("text", StandardBasicTypes.STRING);
			
			sqlQuery.setResultTransformer(Transformers.aliasToBean(Combo.class));
			list = sqlQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<TreeVo> getEntTypeTree(int entId) {
		String sql = "select type_id as \"id\" ,pro_name as \"text\",ptq_id as \"attributes\" from tb_pro_type_qrcode where ent_id="+entId+" order by type_id desc";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.addScalar("attributes", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}
}
