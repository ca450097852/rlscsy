package com.hontek.sys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.ComboboxData;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsEnterprise;

public class EnterpriseDao extends BaseDao<TsEnterprise> {
	
	
	private String entIds="";
	private int entLevel;
	
	@SuppressWarnings("unchecked")
	public void getEnterprise(Object entId){
		String hql = "select t.entId from TsEnterprise t where t.flag=2 and t.parentId="+entId;
		//System.out.println(hql);
		List<Object> list =  super.findObject(hql);
		if(list!=null&&!list.isEmpty()){
			for (Object object : list) {
				entIds+=object+",";				
				getEnterprise(object);
			}
		}		
	}

	public List findLowerEnt(List list,int entId){
		String hql = "from TsEnterprise where flag=2 and parentId="+entId;
		List<TsEnterprise> list1 = this.find(hql); 
		list.addAll(list1);
		for(int i=0;i<list1.size();i++){
			int et = ((TsEnterprise)list1.get(i)).getEntId();
			findLowerEnt(list,et);
		}
		return list;
	}
	
	/**
	 * 查找下级机构ID
	 * @param list
	 * @param entId
	 * @return
	 */
	public List findLowerEnt(List list,long entId){
		String hql = "from TsEnterprise where flag=2 and parentId="+entId;
		List<TsEnterprise> list1 = this.find(hql); 
		list.addAll(list1);
		for(int i=0;i<list1.size();i++){
			int et = ((TsEnterprise)list1.get(i)).getEntId();
			findLowerEnt(list,et);
		}
		
		return list;
	}
	
	/**
	 * 分页查询组织机构
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TsEnterprise> findEnterpriseList(TsEnterprise enterprise, int page, int rows)throws AppException {
		
		String sql ="select ep.ent_Id as entId,ep.parent_Id as parentId,ep.account as account,ep.name as name," +
				"ep.seq as seq,ep.simple_Name as simpleName,ep.intro as intro,ep.tel as tel,ep.legal_person as legalPerson," +
				"ep.integrity_record as integrityRecord,ep.ent_code as entCode,ep.reg_addr as regAddr,ep.manage_addr as manageAddr," +
				"ep.post_Code as postCode,ep.email as email,ep.logo_Url as logoUrl,ep.dom_Name as domName,ep.sign as sign," +
				"ep.ent_Type as entType,et.type_Name as typeName,ep.flag as flag,ep.sts as sts,ep.crt_Dt as crtDt," +
				"ep.crt_User_Id as crtUserId,ep.isReported as isReported,ep.sys_code as sysCode,ep.org_code as orgCode," +
				"ep2.name as parentName,ep.mobile as mobile,ep.area_id as areaId,ep3.name as areaName" +
				" from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
				"left join ts_ent_type et on ep.ent_type = et.type_id " +
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
			if (!"".equals(enterprise.getEntId()) && enterprise.getEntId() >0) {		
				//获取所有子机构的id
				entIds="";
				getEnterprise(enterprise.getEntId());		
				if(entIds.endsWith(",")){
					entIds = entIds.substring(0, entIds.length()-1);
				}
				if(entIds!=null&&!"".equals(entIds)){
					temp += " and ep.parent_Id in(" +entIds+","+enterprise.getEntId()+") ";
				}
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

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id left join ts_ent_type et on ep.ent_type = et.type_id left join ts_enterprise ep3 on ep.area_id = ep3.ent_id "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue());
		return pager;
	}
	
	
	/**
	 * 分页查询企业
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TsEnterprise> findCompanyList(TsEnterprise enterprise, int page, int rows)throws AppException {
		
		String sql ="select ep.ent_Id as entId,ep.parent_Id as parentId,ep.area_Id as areaId,ep.account as account,ep.name as name," +
				"ep.seq as seq,ep.simple_Name as simpleName,ep.intro as intro,ep.tel as tel,ep.legal_person as legalPerson," +
				"ep.integrity_record as integrityRecord,ep.ent_code as entCode,ep.reg_addr as regAddr,ep.manage_addr as manageAddr," +
				"ep.post_Code as postCode,ep.email as email,ep.logo_Url as logoUrl,ep.dom_Name as domName,ep.sign as sign," +
				"ep.ent_Type as entType,et.type_Name as typeName,ep.flag as flag,ep.sts as sts,ep.crt_Dt as crtDt," +
				"ep.crt_User_Id as crtUserId,ep.isReported as isReported,ep.sys_code as sysCode,ep.org_code as orgCode," +
				"ep.company_rsts as companyRsts,ep.town_rsts as townRsts,ep.area_rsts as areaRsts,ep.city_rsts as cityRsts,ep.province_rsts as provinceRsts," +
				"ep2.name as parentName," +
				"ep3.name as areaName ," +
				"ep.is_batch as isbatch, " +
				"ep.mobile as mobile " +
				"from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
				"left join ts_enterprise ep3 on ep.area_Id=ep3.ent_id " +
				"left join ts_ent_type et on ep.ent_type = et.type_id ";
		
		String temp=" where 1=1 and ep.flag='3'";
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
			if(enterprise.getSts() !=null && !"".equals(enterprise.getSts())){
				temp+=" and ep.sts like '%"+enterprise.getSts()+"%'";
			}
			if(enterprise.getCompanyRsts() !=null && !"".equals(enterprise.getCompanyRsts())){
				temp+=" and ep.company_rsts in ("+enterprise.getCompanyRsts()+")";
			}
			
			if(GlobalValueManager.RSTS_DS.equals(enterprise.getProvinceRsts())){
				temp+=" and ep.province_rsts = '"+GlobalValueManager.RSTS_DS+"'";
			}
			if(GlobalValueManager.RSTS_DS.equals(enterprise.getCityRsts())){
				temp+=" and ep.city_rsts = '"+GlobalValueManager.RSTS_DS+"'";
			}
			if(GlobalValueManager.RSTS_DS.equals(enterprise.getAreaRsts())){
				temp+=" and ep.area_rsts = '"+GlobalValueManager.RSTS_DS+"'";
			}
			if(GlobalValueManager.RSTS_DS.equals(enterprise.getTownRsts())){
				temp+=" and ep.town_rsts = '"+GlobalValueManager.RSTS_DS+"'";
			}
					
			if(enterprise.getIsReported() !=null && !"".equals(enterprise.getIsReported())){
				temp+=" and ep.isReported = '"+enterprise.getIsReported()+"'";
			}
			
			if(enterprise.getAreaId()!=null&&enterprise.getAreaId()!=0){
				temp += " and ( ep.area_id="+enterprise.getAreaId()+" or ep.area_id in (select ent_id from ts_enterprise where parent_id="+enterprise.getAreaId()+"))";
			}
			
			if(enterprise.getValidCode()!=null && !"".equals(enterprise.getValidCode())){
				temp += " and ep.body_ent_id in (SELECT ent_id from t_ent_expand where validCode="+enterprise.getValidCode()+")";
			}
			
			if (!"".equals(enterprise.getEntId()) && enterprise.getEntId() >0) {		
				//获取所有子机构的id
				entIds="";
				getEnterprise(enterprise.getEntId());		
				if(entIds.endsWith(",")){
					entIds = entIds+enterprise.getEntId();
				}else{
					entIds = String.valueOf(enterprise.getEntId());
				}
				
				if(entIds!=null&&!"".equals(entIds)){
					temp += " and ep.parent_Id in(" +entIds+") ";
				}
			} 
			
			if(enterprise.getParentName() !=null && !"".equals(enterprise.getParentName())){
				temp+=" and ep2.name like '%"+enterprise.getParentName()+"%'";
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

		sqlQuery.addScalar("areaId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);

		sqlQuery.addScalar("companyRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("townRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("cityRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("provinceRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
		sqlQuery.addScalar("isbatch", StandardBasicTypes.INTEGER);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
				"left join ts_enterprise ep3 on ep.area_Id=ep3.ent_id " +
				"left join ts_ent_type et on ep.ent_type = et.type_id "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue());
		return pager;
	}
	
	
	/**
	 * 查询企业
	 * @param entId
	 * @return
	 */
	public TsEnterprise findCompany(String entId)throws AppException {
		
		String sql ="select ep.ent_Id as entId,ep.parent_Id as parentId,ep.area_Id as areaId,ep.account as account,ep.name as name," +
				"ep.seq as seq,ep.simple_Name as simpleName,ep.intro as intro,ep.tel as tel,ep.legal_person as legalPerson," +
				"ep.integrity_record as integrityRecord,ep.ent_code as entCode,ep.reg_addr as regAddr,ep.manage_addr as manageAddr," +
				"ep.post_Code as postCode,ep.email as email,ep.logo_Url as logoUrl,ep.dom_Name as domName,ep.sign as sign," +
				"ep.ent_Type as entType,et.type_Name as typeName,ep.flag as flag,ep.sts as sts,ep.crt_Dt as crtDt," +
				"ep.crt_User_Id as crtUserId,ep.isReported as isReported,ep.sys_code as sysCode,ep.org_code as orgCode," +
				"ep.company_rsts as companyRsts,ep.town_rsts as townRsts,ep.area_rsts as areaRsts,ep.city_rsts as cityRsts,ep.province_rsts as provinceRsts," +
				"ep2.name as parentName," +
				"ep3.name as areaName, " +
				"ep.mobile as mobile " +
				"from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
				"left join ts_enterprise ep3 on ep.area_Id=ep3.ent_id " +
				"left join ts_ent_type et on ep.ent_type = et.type_id ";
		
		String temp=" where 1=1 and ep.flag='3'";
		if(entId!=null&&!"".equals(entId)){
			temp+=" and ep.ent_Id="+entId;
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp);
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

		sqlQuery.addScalar("areaId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);

		sqlQuery.addScalar("companyRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("townRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("cityRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("provinceRsts", StandardBasicTypes.STRING);
		sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));

		List<TsEnterprise> list = sqlQuery.list();
		if(!list.isEmpty()){
			return (TsEnterprise)list.get(0);			
		}		
		return null;
	}
	
	
	/**
	 * 门户分页查询组织机构
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TsEnterprise> findList(TsEnterprise enterprise, int page, int rows)throws AppException {
		
		String sql ="select ep.ent_Id as \"entId\",ep.parent_Id as \"parentId\",ep.account as \"account\",ep.name as \"name\"," +
				"ep.seq as \"seq\",ep.simple_Name as \"simpleName\",ep.intro as \"intro\",ep.tel as \"tel\",ep.legal_person as \"legalPerson\"," +
				"ep.integrity_record as \"integrityRecord\",ep.ent_code as \"entCode\",ep.reg_addr as \"regAddr\",ep.manage_addr as \"manageAddr\"," +
				"ep.post_Code as \"postCode\",ep.email as \"email\",ep.logo_Url as \"logoUrl\",ep.dom_Name as \"domName\",ep.sign as \"sign\"," +
				"ep.ent_Type as \"entType\",et.type_Name as \"typeName\",ep.flag as \"flag\",ep.sts as \"sts\",ep.crt_Dt as \"crtDt\"," +
				"ep.crt_User_Id as \"crtUserId\",ep.isReported as \"isReported\",ep.sys_code as \"sysCode\",ep2.name as\"parentName\",ep.mobile as \"mobile\"" +
				" from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id left join ts_ent_type et on ep.ent_type = et.type_id ";
		
		String temp=" where 1=1 ";
		if(enterprise!=null){
			if(enterprise.getName() !=null && !"".equals(enterprise.getName())){
				temp+=" and ep.name like '%"+enterprise.getName()+"%'";
			}
			if(enterprise.getSimpleName() !=null && !"".equals(enterprise.getSimpleName())){
				temp+=" and ep.simpleName like '%"+enterprise.getSimpleName()+"%'";
			}
			if(enterprise.getFlag() !=null && !"".equals(enterprise.getFlag())){
				temp+=" and ep.flag like '%"+enterprise.getFlag()+"%'";
			}
			if(enterprise.getCompanyRsts() !=null && !"".equals(enterprise.getCompanyRsts())){
				temp+=" and ep.company_rsts = '"+enterprise.getCompanyRsts()+"'";
			}
			if(enterprise.getParentName() !=null && !"".equals(enterprise.getParentName())){
				temp+=" and ep2.name like '%"+enterprise.getParentName()+"%'";
			}
			if (!"".equals(enterprise.getEntId()) && enterprise.getEntId() >0) {		
				temp += " and ep.ent_id =" +enterprise.getEntId();
			} 
		}

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp);
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
		sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id left join ts_ent_type et on ep.ent_type = et.type_id "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue());
		return pager;
	}
	
	/**
	 * portalweb门户分页查询组织机构
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TsEnterprise> findPortalCompanyList(TsEnterprise enterprise, int page, int rows)throws AppException {
		
		String sql ="select ep.ent_Id as \"entId\",ep.parent_Id as \"parentId\",ep.account as \"account\",ep.name as \"name\"," +
				"ep.seq as \"seq\",ep.simple_Name as \"simpleName\",ep.intro as \"intro\",ep.tel as \"tel\",ep.legal_person as \"legalPerson\"," +
				"ep.integrity_record as \"integrityRecord\",ep.ent_code as \"entCode\",ep.reg_addr as \"regAddr\",ep.manage_addr as \"manageAddr\"," +
				"ep.post_Code as \"postCode\",ep.email as \"email\",ep.logo_Url as \"logoUrl\",ep.dom_Name as \"domName\",ep.sign as \"sign\"," +
				"ep.ent_Type as \"entType\",et.type_Name as \"typeName\",ep.flag as \"flag\",ep.sts as \"sts\",ep.crt_Dt as \"crtDt\"," +
				"ep.crt_User_Id as \"crtUserId\",ep.isReported as \"isReported\",ep.sys_code as \"sysCode\",ep2.name as\"parentName\"," +
				"ep.p_url as \"purl\",ep.m_url as \"murl\",ep.mobile as \"mobile\"" +
				" from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id left join ts_ent_type et on ep.ent_type = et.type_id ";
		
		String temp=" where 1=1 ";
		if(enterprise!=null){
			if(enterprise.getName() !=null && !"".equals(enterprise.getName())){
				temp+=" and ep.name like '%"+enterprise.getName()+"%'";
			}
			if(enterprise.getSimpleName() !=null && !"".equals(enterprise.getSimpleName())){
				temp+=" and ep.simpleName like '%"+enterprise.getSimpleName()+"%'";
			}
			if(enterprise.getFlag() !=null && !"".equals(enterprise.getFlag())){
				temp+=" and ep.flag like '%"+enterprise.getFlag()+"%'";
			}
			if(enterprise.getCompanyRsts() !=null && !"".equals(enterprise.getCompanyRsts())){
				temp+=" and ep.company_rsts = '"+enterprise.getCompanyRsts()+"'";
			}
			if(enterprise.getParentName() !=null && !"".equals(enterprise.getParentName())){
				temp+=" and ep2.name like '%"+enterprise.getParentName()+"%'";
			}
			if (!"".equals(enterprise.getEntId()) && enterprise.getEntId() >0) {		
				temp += " and ep.ent_id =" +enterprise.getEntId();
			}
			if (!"".equals(enterprise.getParentId()) && enterprise.getParentId() >0) {		
				temp += " and (ep.parent_Id =" +enterprise.getParentId()+" or ep.ent_id =" +enterprise.getParentId()+" )";
				temp += " or ep.parent_id in (select ent_id from ts_enterprise where parent_id="+enterprise.getParentId()+")";
			}
			
			if (enterprise.getSysCode() !=null&&!"".equals(enterprise.getSysCode())) {		
				temp += " and ep.sys_code ='" +enterprise.getSysCode()+"'";
			}
			if (enterprise.getIsReported() !=null&&!"".equals(enterprise.getIsReported())) {		
				temp += " and ep.isReported ='" +enterprise.getIsReported()+"'";
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
		sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
		sqlQuery.addScalar("purl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("murl", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id left join ts_ent_type et on ep.ent_type = et.type_id "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue());
		return pager;
	}
	/**
	 * 区域查询
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 * @throws AppException
	 */
	public Pager<TsEnterprise> findAreaList(TsEnterprise enterprise, int page, int rows)throws AppException {		
		String sql ="select ep.ent_Id as \"entId\",ep.parent_Id as \"parentId\",ep.account as \"account\",ep.name as \"name\"," +
				"ep.flag as \"flag\",ep.sts as \"sts\",ep.ent_code as \"entCode\","+
				"ep.crt_Dt as \"crtDt\",ep.crt_User_Id as \"crtUserId\",ep.isReported as \"isReported\",ep.sys_code as \"sysCode\",ep2.name as\"parentName\"" +
				" from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id where ep.flag = '1' ";
		
		String temp="";
		if(enterprise!=null){
			if(enterprise.getName() !=null && !"".equals(enterprise.getName())){
				temp+=" and ep.name like '%"+enterprise.getName()+"%' ";
			}
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp+" order by ep.ent_code ");
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("flag", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crtDt", StandardBasicTypes.STRING);
		sqlQuery.addScalar("crtUserId", StandardBasicTypes.STRING);		
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
		
		String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id where ep.flag = '1' "+temp;
		List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		Integer count = this.countBySql(hql);
		
		Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue());
		return pager;
	
	}

	/**
	 * 添加一个组织机构
	 * @param enterprise
	 */
	public void addEnterprise(TsEnterprise enterprise)throws AppException {
		this.save(enterprise);
	}

	/**
	 * 修改一个组织机构
	 * @param enterprise
	 */
	public void updateEnterprise(TsEnterprise enterprise) throws AppException {
		this.saveOrUpdate(enterprise);
	}

	/**
	 * 删除一个组织机构
	 * @param enterprise
	 */
	public void deleteEnterprise(TsEnterprise enterprise)throws AppException {
		this.delete(enterprise);
	}
    /**
     * 查询组织机构 - 下拉
     * @return
     */
	public List<TsEnterprise> getEnterpriseToSelect() {
		List<TsEnterprise> list = new ArrayList<TsEnterprise>();
		String hql="from TsEnterprise where parentId=0";
		list  = this.find(hql);
		return list;
	}
	
    /**
     * 查询组织机构
     * @return
     */
	public List<TsEnterprise> getEnterpriseList() {
		List<TsEnterprise> list = new ArrayList<TsEnterprise>();
		String hql="from TsEnterprise";
		list  = this.find(hql);
		return list;
	}
	
    /**
     * 根据entId查询是否存在某对象
     * @param sEntId
     * @return 
     */
	public TsEnterprise findEnterpriseByAccount(String account) {
//		String hql="from TsEnterprise where account='"+account+"' and flag='2'";
		String hql="from TsEnterprise where account='"+account+"' ";
		TsEnterprise enterprise = this.get(hql);
		return enterprise;
	}
	

	@SuppressWarnings("unchecked")
	public List getEntTree(int pId,int entId) {
		String sql = "select ent_id as \"id\",name as \"text\" from ts_enterprise where flag in ('2','0') and parent_id="+pId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			if(vo.getId()==entId){
				vo.setChecked(true);
			}
			vo.setChildren(this.getEntTree(vo.getId(),entId));
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getEntRow(int entId) {
		String sql = "select ent_id as \"id\",name as \"text\" from ts_enterprise where flag in ('2','0') and ent_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		for (int i = 0; i < list.size(); i++) {
			TreeVo vo = list.get(i);
			vo.setChecked(true);
		}
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List getEntAreaTree(int pId,int entId) {
		String sql = "select ent_id as \"id\",name as \"text\" from ts_enterprise where flag in ('1','0') and parent_id="+pId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		TreeVo vo = null; 
		for(int i=0;i<list.size();i++){
			vo = list.get(i);
			if(vo.getId()==entId){
				vo.setChecked(true);
			}
			vo.setChildren(this.getEntAreaTree(vo.getId(),entId));
		}
		return list;
	}
	
	/**
	 * 20150729
	 * @Title: getAreaTree
	 * @param @param pId
	 * @param @return 
	 * @return List    返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List getAreaTree(String parentId) {
		String sql = "select ent_id as \"id\",name as \"text\" from ts_enterprise where flag in ('1','0') and parent_id="+parentId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getEntAreaRow(int entId) {
		String sql = "select ent_id as \"id\",name as \"text\" from ts_enterprise where flag in ('1','0') and ent_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list = sqlQuery.list();
		for (int i = 0; i < list.size(); i++) {
			TreeVo vo = list.get(i);
			vo.setChecked(true);
		}
		return list;
	}
	
	
	
	
    /**
     * 修改使用状态
     * @param enterprise
     */
	public void updateSts(TsEnterprise enterprise) {
		this.saveOrUpdate(enterprise);
	}
    /**
     * 根据ID查询对象
     * @param id
     * @return
     */
	public TsEnterprise getEnterPirseByEntId(int id) {
		String hql = "from TsEnterprise";
		hql += "  where entId="+id;
			
		List list = this.find(hql);
		TsEnterprise enterprise =null;
		if(list.size()>0){
			enterprise = (TsEnterprise) list.get(0);
		}
		return enterprise;
	}
    /**
     * 根据条件查询对象
     * @param id
     * @return
     */
	public TsEnterprise getEnterprise(TsEnterprise enterprise) {
		String hql = "from TsEnterprise where 1=1";
		
		if (enterprise != null) {
			int entId = enterprise.getEntId();
			int parentId = enterprise.getParentId();
			String account = enterprise.getAccount();
			if (entId > 0)
				hql += " and entId=" + entId;
			if (parentId > 0)
				hql += " and parentId=" + parentId;
			if (account != null && !"".equals(account))
				hql += " and account=" + account;
		}
		
		List list = this.find(hql);
		enterprise =null;
		if(list.size()>0){
			enterprise = (TsEnterprise) list.get(0);
		}
		return enterprise;
	}
	
    /**
     * 根据条件查询对象
     * @param id
     * @return
     */
	public Object[] getEnterpriseByCondi(String name,String addr) {
		String hql = "select entId,entCode from TsEnterprise where name='"+name+"' and regAddr like '"+addr+"%'";				
		Query q = this.getCurrentSession().createQuery(hql);
		List<Object[]> list = q.list();
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	/**
     * 根据条件查询对象
     * @param id
     * @return
     */
	public TsEnterprise getEnterprise(String condition) {
		String hql = "from TsEnterprise where 1=1 "+condition;
		TsEnterprise enterprise =null;
		List list = this.find(hql);
		if(list.size()>0){
			enterprise = (TsEnterprise) list.get(0);
		}
		return enterprise;
	}
	 /**
     * 验证企业名称是否唯一
     * @param account
     * @return
     */
	public boolean findNameIsUnique(String name) {
		String hql="select count(*) from TsEnterprise";
		if(!"".equals(name)){
			hql +=" where name='"+name+"'";
		}
		Long i = this.count(hql);
		if(i.longValue()>0L){
			return true;
		}
		return false;
	}
	
    /**
     * 验证组织帐号是否唯一
     * @param account
     * @return
     */
	public boolean findAccountIsUnique(String account) {
		String hql="select count(*) from TsEnterprise";
		if(!"".equals(account)){
			hql +=" where account='"+account+"'";
		}
		Long i = this.count(hql);
		if(i.longValue()>0L){
			return true;
		}
		return false;
	}

	public String getEnterpriseByEntId(int entId) {
		String hql = "from TsEnterprise where entId="+entId;
		TsEnterprise te = this.get(hql);
		String entName = te.getName();
		return entName;
	}

	/**
	 * 获取企业流水号
	 * @param string
	 * @return
	 */
	public int getEntCodeSeq(String condition) {
		String sql = "select Max(ent_code) as ent_code from ts_enterprise where 1=1 "+condition;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("ent_code", StandardBasicTypes.STRING);
		Object obj = sqlQuery.uniqueResult();
		if(obj==null){
			return 1;
		}else{
			String entCode = String.valueOf(obj);
			String seq = entCode.substring(entCode.length()-3);
			return Integer.parseInt(seq);
		}
	}
	
	
	/**
	 * 统计企业数
	 * @param condtions
	 * @return
	 */
	public List<Map<Object, Object>> getEntAreaList(String condtions){		
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		
		String sql = "SELECT t1.name as entName, (SELECT COUNT(*)  FROM ts_enterprise tc where tc.flag=3 and ( tc.area_id = t1.ent_id OR area_id in(SELECT ent_id FROM ts_enterprise WHERE parent_id=t1.ent_id and flag=1) ) )as total " +
				"FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=1 ";
		if(condtions!=null&&!"".equals(condtions)){
			sql = "SELECT t1.name as entName, (SELECT COUNT(*)  FROM ts_enterprise tc where tc.flag=3  "+condtions+"and ( tc.area_id = t1.ent_id OR area_id in(SELECT ent_id FROM ts_enterprise WHERE parent_id=t1.ent_id and flag=1))) as total " +
			"FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=1 ";
		}
	
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("total", StandardBasicTypes.INTEGER);

		List<Object[]> list =  sqlQuery.list();		
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	} 
	
	public List<Map<Object, Object>> getEntAreaListNew(String condtions,TsEnterprise enterprise){		
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		
		
		String sql = "SELECT (select name from ts_enterprise where ent_id=tc.area_id) as entName,count(*) as total FROM ts_enterprise tc where body_ent_id="+enterprise.getBodyEntId()+" and "+ 
				" tc.area_id in (SELECT ent_id from ts_enterprise where parent_id = "+enterprise.getAreaId()+") "+condtions+" GROUP BY  area_id";
	
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("total", StandardBasicTypes.INTEGER);

		List<Object[]> list =  sqlQuery.list();		
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	} 
	
	
	/**
	 * 统计企业数
	 * @param condtions
	 * @return
	 */
	public List<Map<Object, Object>> getEntAreaList(String condtions,int areaId){		
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		
		String sql = "SELECT t1.name as entName, (SELECT COUNT(*)  FROM ts_enterprise tc where tc.flag=3 and (tc.area_id = t1.ent_id OR area_id in(SELECT ent_id FROM ts_enterprise WHERE parent_id=t1.ent_id and flag=1) )) as total " +
				"FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id="+areaId;
		if(condtions!=null&&!"".equals(condtions)){
			sql = "SELECT t1.name as entName, (SELECT COUNT(*)  FROM ts_enterprise tc where tc.flag=3 "+condtions+" and ( tc.area_id = t1.ent_id OR area_id in(SELECT ent_id FROM ts_enterprise WHERE parent_id=t1.ent_id and flag=1))) as total " +
			"FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id="+areaId;
		}
	
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("total", StandardBasicTypes.INTEGER);

		List<Object[]> list =  sqlQuery.list();		
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	}  
	
	public Map<String, TsEnterprise> getAreaEnterpriseList(){
		String sql = "SELECT t1.name as name,t1.ent_id as areaId,t1.ent_code as entCode FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=1 ";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("entCode", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));

		List<TsEnterprise> list = sqlQuery.list();
		Map<String, TsEnterprise> map = new HashMap<String, TsEnterprise>();

		if(!list.isEmpty()){
			for (TsEnterprise tsEnterprise : list) {
				map.put(tsEnterprise.getName(), tsEnterprise);
			}
		}

		return map;
	
	}
	
	
	/**
	 * 统计企业数
	 * @param condtions
	 * @return
	 */
	public Object getEntAreaCount(String condtions){		
		String sql = "SELECT count(*) FROM ts_enterprise tc where tc.flag=3 ";
		if(condtions!=null&&!"".equals(condtions)){
			sql +=condtions;
		}
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		Object obj = sqlQuery.uniqueResult();

		return obj;
	}
	
	public List<ComboboxData> getEntListJson(String jsonType) {
		String sql = "select ent_id as id,name as text from ts_enterprise where flag=3 "+("1".equals(jsonType)?"and isReported=1":"and isReported=0");
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(ComboboxData.class));
		return sqlQuery.list();
	} 
	
	/**
	 * 计算有几层 递归查询
	 * @param entId
	 */
	public void CountEntlevel(String entId){
		if(entId==null||"".equals(entId)||"0".equals(entId)){
			return;
		}
		entLevel++;
		
		String sql = "select parent_id as entId from ts_enterprise where ent_id="+entId;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entId", StandardBasicTypes.STRING);
		entId = (String)sqlQuery.uniqueResult();
		CountEntlevel(entId);
	}
	/**
	 * 查询机构属于第几级
	 * @param entId
	 * @return
	 */
	public int getEntLevel(String entId){
		entLevel=0;//归零
		CountEntlevel(entId);
		return entLevel;
	}
	
	private List<TreeVo> listTreeVos = null;
	public void getParentEnt(int entId){
		String sql = "select ent_id as \"id\",name as \"text\",parent_id as \"parentId\" from ts_enterprise where flag='2' and ent_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.addScalar("parentId",StandardBasicTypes.INTEGER);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list2 = sqlQuery.list();		
		if(list2!=null&&!list2.isEmpty()){
			TreeVo tVo = list2.get(0);
			listTreeVos.add(tVo);
			getParentEnt(tVo.getParentId());
		}
	}
	
	public List<TreeVo> getParentEntList(int entId) {
		listTreeVos = new ArrayList<TreeVo>();
		getParentEnt(entId);
		return listTreeVos;
	}
	
	
	
	/**
	 * 根据区域查找其下的管理机构Id
	 */
	public String getEntManagerId(String entId){
		String entId2 = entId;
		if(entId==null||"".equals(entId)||"0".equals(entId)){
			return null;
		}
		String sql = "select ent_id as entId from ts_enterprise where flag=2 and area_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entId", StandardBasicTypes.STRING);
		entId = (String)sqlQuery.uniqueResult();
		
		if(entId==null||entId.equals("")){
			sql = "select ent_id as entId from ts_enterprise where flag=2 and area_id= ( select parent_id from ts_enterprise where flag=1 and ent_id="+entId2+" )";
			sqlQuery = this.getCurrentSession().createSQLQuery(sql);
			sqlQuery.addScalar("entId", StandardBasicTypes.STRING);
			entId = (String)sqlQuery.uniqueResult();
		}
		
		return entId;
	}

	public Pager<TsEnterprise> getEntHasRecord(TsEnterprise enterprise,int page, int rows) {
		String sql ="select ep.ent_Id as entId,ep.parent_Id as parentId,ep.area_Id as areaId,ep.account as account,ep.name as name," +
		"ep.seq as seq,ep.simple_Name as simpleName,ep.intro as intro,ep.tel as tel,ep.legal_person as legalPerson," +
		"ep.integrity_record as integrityRecord,ep.ent_code as entCode,ep.reg_addr as regAddr,ep.manage_addr as manageAddr," +
		"ep.post_Code as postCode,ep.email as email,ep.logo_Url as logoUrl,ep.dom_Name as domName,ep.sign as sign," +
		"ep.ent_Type as entType,et.type_Name as typeName,ep.flag as flag,ep.sts as sts,ep.crt_Dt as crtDt," +
		"ep.crt_User_Id as crtUserId,ep.isReported as isReported,ep.sys_code as sysCode,ep.org_code as orgCode," +
		"ep.company_rsts as companyRsts,ep.town_rsts as townRsts,ep.area_rsts as areaRsts,ep.city_rsts as cityRsts,ep.province_rsts as provinceRsts," +
		"ep2.name as parentName," +
		"ep3.name as areaName ," +
		"ep.is_batch as isbatch, " +
		"ep.mobile as mobile " +
		"from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
		"left join ts_enterprise ep3 on ep.area_Id=ep3.ent_id " +
		"left join ts_ent_type et on ep.ent_type = et.type_id ";

		String temp=" where 1=1 and ep.flag='3' and (ep.ent_id in (SELECT obj_id FROM tb_obj_element where obj_type_id in (1,2)) or ep.ent_id in  (SELECT ent_id FROM tb_pro_type_qrcode))";
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
		if(enterprise.getSts() !=null && !"".equals(enterprise.getSts())){
			temp+=" and ep.sts like '%"+enterprise.getSts()+"%'";
		}
		if(enterprise.getCompanyRsts() !=null && !"".equals(enterprise.getCompanyRsts())){
			temp+=" and ep.company_rsts = '"+enterprise.getCompanyRsts()+"'";
		}
		
		if(GlobalValueManager.RSTS_DS.equals(enterprise.getProvinceRsts())){
			temp+=" and ep.province_rsts = '"+GlobalValueManager.RSTS_DS+"'";
		}
		if(GlobalValueManager.RSTS_DS.equals(enterprise.getCityRsts())){
			temp+=" and ep.city_rsts = '"+GlobalValueManager.RSTS_DS+"'";
		}
		if(GlobalValueManager.RSTS_DS.equals(enterprise.getAreaRsts())){
			temp+=" and ep.area_rsts = '"+GlobalValueManager.RSTS_DS+"'";
		}
		if(GlobalValueManager.RSTS_DS.equals(enterprise.getTownRsts())){
			temp+=" and ep.town_rsts = '"+GlobalValueManager.RSTS_DS+"'";
		}
				
		if(enterprise.getIsReported() !=null && !"".equals(enterprise.getIsReported())){
			temp+=" and ep.isReported = '"+enterprise.getIsReported()+"'";
		}
		if (!"".equals(enterprise.getEntId()) && enterprise.getEntId() >0) {		
			//获取所有子机构的id
			entIds="";
			getEnterprise(enterprise.getEntId());		
			if(entIds.endsWith(",")){
				entIds = entIds+enterprise.getEntId();
			}else{
				entIds = String.valueOf(enterprise.getEntId());
			}
			
			if(entIds!=null&&!"".equals(entIds)){
				temp += " and ep.parent_Id in(" +entIds+") ";
			}
		} 
		
		if(enterprise.getParentName() !=null && !"".equals(enterprise.getParentName())){
			temp+=" and ep2.name like '%"+enterprise.getParentName()+"%'";
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
	
	sqlQuery.addScalar("areaId", StandardBasicTypes.INTEGER);
	sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);
	
	sqlQuery.addScalar("companyRsts", StandardBasicTypes.STRING);
	sqlQuery.addScalar("townRsts", StandardBasicTypes.STRING);
	sqlQuery.addScalar("areaRsts", StandardBasicTypes.STRING);
	sqlQuery.addScalar("cityRsts", StandardBasicTypes.STRING);
	sqlQuery.addScalar("provinceRsts", StandardBasicTypes.STRING);
	sqlQuery.addScalar("mobile", StandardBasicTypes.STRING);
	sqlQuery.addScalar("isbatch", StandardBasicTypes.INTEGER);
	
	sqlQuery.setResultTransformer(Transformers.aliasToBean(TsEnterprise.class));
	
	String hql = "select count(*) from ts_enterprise ep left join ts_enterprise ep2 on ep.parent_Id=ep2.ent_id " +
			"left join ts_enterprise ep3 on ep.area_Id=ep3.ent_id " +
			"left join ts_ent_type et on ep.ent_type = et.type_id "+temp;
	List<TsEnterprise> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	Integer count = this.countBySql(hql);
	
	Pager<TsEnterprise> pager = new Pager<TsEnterprise> ();
	pager.setData ( list );
	pager.setTotal ( count.intValue());
	return pager;
	}
	
/*	private TreeVo tVo = null;
	public void getParent(int entId){
		String sql = "select ent_id as \"id\",name as \"text\",parent_id as \"parentId\" from ts_enterprise where flag!='1' and ent_id="+entId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("id", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("text", StandardBasicTypes.STRING);	
		sqlQuery.addScalar("parentId",StandardBasicTypes.INTEGER);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TreeVo.class));
		List<TreeVo> list2 = sqlQuery.list();		
		if(list2!=null&&!list2.isEmpty()){
			if(tVo == null){
				tVo = list2.get(0);
			}else{
				TreeVo tmp = tVo;
				tVo = list2.get(0);
				List<TreeVo> tList = new ArrayList<TreeVo>();
				tList.add(tmp);
				tVo.setChildren(tList);
			}
			getParent(tVo.getParentId());
		}
	}
	
	public TreeVo getParentEntTree(int entId) {
		tVo =null;
		getParent(entId);
		return tVo;
	}*/
}
