package com.hontek.sys.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;
/**
 * 企业扩展信息
 * @author ZK
 *
 */
public class EntExpandDao extends BaseDao<EntExpand>{

	public Pager<TsEnterprise> findPager(TsEnterprise enterprise, int page, int rows,
			String sort, String order) {
		
		String sql ="select ep.ent_Id as entId,ep.parent_Id as parentId,ep.account as account,ep.name as name, "+
					"ep.seq as seq,ep.simple_Name as simpleName,ep.intro as intro,ep.tel as tel,ep.legal_person as legalPerson, "+
					"ep.integrity_record as integrityRecord,ep.ent_code as entCode,ep.reg_addr as regAddr,ep.manage_addr as manageAddr, "+
					"ep.post_Code as postCode,ep.email as email,ep.logo_Url as logoUrl,ep.dom_Name as domName,ep.sign as sign, "+
					"ep.ent_Type as entType,et.type_Name as typeName,ep.flag as flag,ep.sts as sts,ep.crt_Dt as crtDt, "+
					"ep.crt_User_Id as crtUserId,ep.isReported as isReported,ep.sys_code as sysCode,ep.org_code as orgCode, "+
					"ep2.name as parentName,ep.mobile as mobile,ep.area_id as areaId,ep3.name as areaName, "+
					//扩展字段
					"ontrial_start as ontrialStart,ontrial_end as ontrialEnd,ischarge,expired,ismainbody, "+
					"mb_name as mbName,mb_type as mbType,mb_domain as mbDomain, "+
					"create_time as createTime,remark ,ex.validCode as validCode,ex.showCode as showCode,audit_pro as auditPro,audit_batch as auditBatch "+
					
					"from t_ent_expand ex LEFT JOIN ts_enterprise ep on (ex.ent_id=ep.ent_id and ismainbody='1') "+
					"left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id  "+
					"left join ts_ent_type et on ep.ent_type = et.type_id  "+
					"left join ts_enterprise ep3 on ep.area_id = ep3.ent_id ";

		String temp=" where 1=1 ";
		if(enterprise!=null){
			if(enterprise.getAccount() !=null && !"".equals(enterprise.getAccount())){
				temp+=" and ep.account like '%"+enterprise.getAccount()+"%'";
			}
			if(enterprise.getName() !=null && !"".equals(enterprise.getName())){
				temp+=" and ep.name like '%"+enterprise.getName()+"%'";
			}
			if(enterprise.getSimpleName() !=null && !"".equals(enterprise.getSimpleName())){
				temp+=" and ep.simpleName like '%"+enterprise.getSimpleName()+"%'";
			}
			if(enterprise.getTel() !=null && !"".equals(enterprise.getTel())){
				temp+=" and ep.tel like '%"+enterprise.getTel()+"%'";
			}
			if(enterprise.getFlag() !=null && !"".equals(enterprise.getFlag())){
				temp+=" and ep.flag like '%"+enterprise.getFlag()+"%'";
			}
			if(enterprise.getSts() !=null && !"".equals(enterprise.getSts())){
				temp+=" and ep.sts like '%"+enterprise.getSts()+"%'";
			}
			if(enterprise.getParentName() !=null && !"".equals(enterprise.getParentName())){
				temp+=" and ep2.name like '%"+enterprise.getParentName()+"%'";
			}
			if(enterprise.getIsReported() !=null && !"".equals(enterprise.getIsReported())){
				temp+=" and ep.isReported = '"+enterprise.getIsReported()+"'";
			}
			if(enterprise.getIscharge() !=null && !"".equals(enterprise.getIscharge())){
				temp+=" and ischarge = '"+enterprise.getIscharge()+"'";
			}
			if(enterprise.getMbType() !=null && !"".equals(enterprise.getMbType())){
				temp+=" and mb_type = '"+enterprise.getMbType()+"'";
			}
		}
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp+" order by ep.crt_Dt desc");
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("account", StandardBasicTypes.STRING);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("seq", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("simpleName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("intro", StandardBasicTypes.STRING);
		sqlQuery.addScalar("tel", StandardBasicTypes.STRING);
		sqlQuery.addScalar("legalPerson", StandardBasicTypes.STRING);
		sqlQuery.addScalar("integrityRecord", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entCode", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("regAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("manageAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("postCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("email", StandardBasicTypes.STRING);
		sqlQuery.addScalar("logoUrl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("domName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sign", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entType", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("typeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("flag", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crtDt", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crtUserId", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("isReported", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("orgCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);
		
		
		sqlQuery.addScalar("ontrialStart", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ontrialEnd", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ischarge", StandardBasicTypes.STRING);
		sqlQuery.addScalar("expired", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ismainbody", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbDomain", StandardBasicTypes.STRING);
		sqlQuery.addScalar("createTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("validCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("showCode", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("auditPro", StandardBasicTypes.STRING);
		sqlQuery.addScalar("auditBatch", StandardBasicTypes.STRING);
		
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from t_ent_expand ex LEFT JOIN ts_enterprise ep on (ex.ent_id=ep.ent_id and ismainbody='1') "+
					"left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id  "+
					"left join ts_ent_type et on ep.ent_type = et.type_id  "+
					"left join ts_enterprise ep3 on ep.area_id = ep3.ent_id "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
		pager.setData ( list );
		pager.setTotal ( count.intValue());
		return pager;
		
	}

	public void deleteByIds(String entId) {
		if(entId!=null && !"".equals(entId)){
			String hql = "delete from EntExpand where entId in ("+entId+")";
			this.executeHql(hql);
		}
	}
	
	
	public List<EntExpand> findEntExpandList(String mbDomain){
		
		String sql = "select ent_Id as entId" +
				",ontrial_start as ontrialStart" +
				",ontrial_end as ontrialEnd" +
				",ischarge" +
				",expired" +
				",ismainbody" +
				",mb_name as mbName" +
				",mb_type as mbType" +
				",mb_domain as mbDomain" +
				",create_time as createTime" +
				",remark" +
				" from t_ent_expand where 1=1 and mb_domain='"+mbDomain+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("ontrialStart", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ontrialEnd", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ischarge", StandardBasicTypes.STRING);
		sqlQuery.addScalar("expired", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ismainbody", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbDomain", StandardBasicTypes.STRING);
		sqlQuery.addScalar("createTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntExpand.class));
		List<EntExpand> list = sqlQuery.list();
		if(list!=null){
			return list;
		}else{
			return null;
		}
		
	}
	
	public List<EntExpand> findAllList(EntExpand entExpand){
		
		String condition = "";
		String sql = "select ent_Id as entId" +
				",ontrial_start as ontrialStart" +
				",ontrial_end as ontrialEnd" +
				",ischarge" +
				",expired" +
				",ismainbody" +
				",mb_name as mbName" +
				",mb_type as mbType" +
				",mb_domain as mbDomain" +
				",create_time as createTime" +
				",remark" +
				" from t_ent_expand where 1=1";
		
		if(entExpand!=null){
			if(entExpand.getMbName()!=null&&entExpand.getMbName().equals("")){
				condition += " and mb_name like '%"+entExpand.getMbName()+"%'";
			}
			if(entExpand.getIscharge()!=null&&entExpand.getIscharge().equals("")){
				condition += " and ischarge = '"+entExpand.getIscharge()+"'";
			}
			if(entExpand.getIsmainbody()!=null&&entExpand.getIsmainbody().equals("")){
				condition += " and ismainbody = '"+entExpand.getIsmainbody()+"'";
			}
			if(entExpand.getMbType()!=null&&entExpand.getMbType().equals("")){
				condition += " and mb_type = '"+entExpand.getMbType()+"'";
			}
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("ontrialStart", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ontrialEnd", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ischarge", StandardBasicTypes.STRING);
		sqlQuery.addScalar("expired", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ismainbody", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mbDomain", StandardBasicTypes.STRING);
		sqlQuery.addScalar("createTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntExpand.class));
		List<EntExpand> list = sqlQuery.list();
		return list;
	}
	
	public EntExpand getEntExpandByMbDomain(String mbDomain){
		String sql = "select ent_Id as entId" +
				",ontrial_start as ontrialStart" +
				",ontrial_end as ontrialEnd" +
				",ischarge" +
				",expired" +
				",ismainbody" +
				",mb_name as mbName" +
				",mb_type as mbType" +
				",mb_domain as mbDomain" +
				",create_time as createTime" +
				",remark" +
				" from t_ent_expand where 1=1 and mb_domain='"+mbDomain+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntExpand.class));		
		List<EntExpand> list = sqlQuery.list();
		if(list!=null&&list.size()==1){
			return list.get(0);
		}		
		return null;
		
	}

	public EntExpand getEntExpandEntId(int entId) {
		String sql = "select ent_Id as entId" +
			",ontrial_start as ontrialStart" +
			",ontrial_end as ontrialEnd" +
			",ischarge" +
			",expired" +
			",ismainbody" +
			",mb_name as mbName" +
			",mb_type as mbType" +
			",mb_domain as mbDomain" +
			",create_time as createTime" +
			",remark" +
			" from t_ent_expand where 1=1 and ent_id='"+entId+"'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(EntExpand.class));		
		List<EntExpand> list = sqlQuery.list();
		if(list!=null&&list.size()==1){
			return list.get(0);
		}		
		return null;
	}

}
