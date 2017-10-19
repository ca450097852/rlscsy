package com.hontek.review.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.review.pojo.TbProType;
import com.hontek.review.pojo.TreeVo;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.comm.util.TranMapCondition;

/**
 * <p>Title: 产品分类</p>
 * <p>Description: 产品分类DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProTypeDao extends BaseDao<TbProType>{

	@SuppressWarnings("unchecked")
	public Pager findProTypeList(Map mapCondition, int page, int rows) {
		String condition = TranMapCondition.TranMaptoString(mapCondition);
		
		String hql_ct = "select count(*) from TbProType "+condition;
		
		condition += " order by typeNo ";
		
		String hql = "from TbProType "+condition;
		
		List list = this.find(hql, page, rows);
		
		
		int ct = this.count(hql_ct).intValue();
		
		Pager<TbProType> pager = new Pager<TbProType>();
		
		pager.setData(list);
		
		pager.setTotal(ct);
		
		return pager;
	}

	public void addProType(TbProType proType) {
		proType.setTypeId(getTableSequence("TB_PRO_TYPE"));
		this.save(proType);
	}
	
	
	public void addProType2(TbProType proType) {
		this.save(proType);
	}

	public void updateProType(TbProType proType) {
		this.update(proType);
	}

	public void updateProTypeState(String ids, String state) {
		String hql = "update TbProType set state="+state+" where typeId in ("+ids+")";
		
		this.executeHql(hql);
	}

	public List<TreeVo> getProTypeTree(Long parentId) {
		String sql = "select type_id as \"id\" ,type_name as \"text\", type_no as \"attributes\"from tb_pro_type where state=0 and upcate_id="+parentId+" order by type_no";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		
		for(TreeVo vo:list){//显示大类			
			vo.setChildren(getProTypeTree(vo.getId()));			
			if(!vo.getChildren().isEmpty()&& String.valueOf(vo.getAttributes()).length()>=4){
				vo.setState("closed");
			}
		}
		
		return list;
	}
	
	
	
	public List<TreeVo> getProTypeTree(Long parentId,String typeClass) {
		String sql = "select type_id as \"id\" ,type_name as \"text\" from tb_pro_type where state=0 and type_class="+typeClass+" and upcate_id="+parentId+" order by type_id desc";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		
		for(TreeVo vo:list){
			vo.setChildren(getProTypeTree(vo.getId(),typeClass));
		}
		
		return list;
	}
	
	/**
	 * 门户企业注册，选择产品分类
	 */
	public List<TreeVo> getProTypeTreeForPortal(Long parentId) {
		String sql = "select type_id as \"id\" ,type_name as \"text\",type_class as \"attributes\" from tb_pro_type where state=0 and upcate_id="+parentId+" order by type_id desc";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.addScalar("attributes", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		
		for(TreeVo vo:list){
			vo.setChildren(getProTypeTreeForPortal(vo.getId()));
		}
		
		return list;
	}
	
	
	public TbProType getProType(String condition){
		TbProType proType = null;
		String hql = "from TbProType where 1=1 "+condition;
		List<TbProType> list = this.find(hql);
		if(list.size()>0){
			proType = list.get(0);
		}
		
		return proType;
	}

	public List<TbProType> findQrcodeType(String entId) {
		String sql = "select t1.type_id as typeId,type_no as typeNo,type_name as typeName,upcate_id as upcateId,state,ptq_id as entId,remark,type_class as typeClass from tb_pro_type t1,tb_pro_type_qrcode t2 " +
				"where t1.type_id=t2.type_id and t2.ent_id= "+entId;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("typeId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("typeNo", StandardBasicTypes.STRING);
		sqlQuery.addScalar("typeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("upcateId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("state", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("typeClass", StandardBasicTypes.STRING);
		
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbProType.class));
		List<TbProType> list = sqlQuery.list();
		
		return list;
	}

	public List<TreeVo> getEntTypeTree(int entId) {
		String sql = "select type_id as \"id\" ,type_name as \"text\",type_class as \"attributes\" from tb_pro_type where state=0 and type_id in (select type_id from tb_pro_type_qrcode where ent_id="+entId+") order by type_id desc";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.addScalar("attributes", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}

	public List<TreeVo> getProTypeTreeByName(String typeName) {
		String sql = "select type_id as \"id\" ,type_name as \"text\" from tb_pro_type where state=0 and type_name like '%"+typeName+"%' order by type_id";
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.LONG);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		
		List<TreeVo> newList = new ArrayList<TreeVo>();
		
		for(TreeVo vo:list){
			int ct = this.countBySql("select count(*) from tb_pro_type where upcate_id="+vo.getId());
			if(ct==0){
				newList.add(vo);
			}
		}
		
		return newList;
	}

}
