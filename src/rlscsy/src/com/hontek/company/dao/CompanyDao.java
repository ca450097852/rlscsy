package com.hontek.company.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Company;
/**
 * <p>Title: 企业信息表</p>
 * <p>Description: 企业信息表DAO 类</p>
 */
public class CompanyDao extends BaseDao<Company> {
	
	 /**
     * 加载节点 -- 下拉使用
     * @return
     */
	public List<Company> getCompanyToSelect(String area) throws AppException {
		String condition = "";
		if(area!=null&&!"".equals(area)){
			condition = " and area = '"+area+"' ";
		}
		List<Company> list = new ArrayList<Company>();
		String hql="from Company where parentId = 0 and state='1'"+condition;
		list  = this.find(hql);
		return list;
	}
	
	
	public Pager<Company> findPager(Company company, int page, int rows,String sort, String order) {
		
		String sql ="select t1.COM_ID as comId" +
					",t1.name as name" +
					",t1.PARENT_ID as parentId" +
					",t1.nature as nature" +
					",t1.COM_TYPE as comType" +
					",t1.COM_CODE as comCode" +
					",t1.NODE_CODE as nodeCode" +
					",t1.flag as flag" +
					",t1.LINK_MAN as linkMan" +
					",t1.phone as phone" +
					",t1.fax as fax" +
					",t1.addr as addr" +
					",t1.email as email" +
					",t1.area as area" +
					",t1.introduction as introduction" +
					",t1.corporate as corporate" +
					",t1.REG_CODE as regCode" +
					",t1.LICENSE_NUM as licenseNum" +
					",t1.LICENSE_IMG as licenseImg" +
					",t1.COM_LOGO as comLogo" +
					",t1.state as state" +
					",t1.REG_TIME as regTime" +
					",t1.RECORD_DATE as recordDate" +
					",t1.remark as remark" +
					",t1.ENT_ID as entId" +
					",t2.name as nodeName" +
					",t3.name as areaName "+
					"from tb_company t1 LEFT JOIN tb_company t2 on t1.parent_id=t2.com_id  "+
					"left join ts_enterprise t3 on t1.area=t3.ent_id  ";

		String temp=" where 1=1 ";
		if(company!=null){
			
			if(company.getName() !=null && !"".equals(company.getName())){
				temp+=" and t1.name like '%"+company.getName()+"%'";
			}
			if(company.getFlag() !=null && !"".equals(company.getFlag())){
				temp+=" and t1.flag like '%"+company.getFlag()+"%'";
			}
			if(company.getComType()!=null&&!"".equals(company.getComType())){
				temp+=" and t1.com_type = '"+company.getComType()+"' ";
			}
			if(company.getNature()!=null&&!"".equals(company.getNature())){
				temp+=" and t1.nature = '"+company.getNature()+"' ";
			}
			if(company.getState()!=null&&!"".equals(company.getState())){
				temp+=" and t1.state = '"+company.getState()+"' ";
			}
			if(company.getParentId()==0){
				temp+=" and t1.parent_id = 0 ";
			}
			if(company.getParentId()==-1){
				temp+=" and t1.parent_id <> 0 ";
			}
			if(company.getParentId()>0){
				temp+=" and t1.parent_id = "+company.getParentId();
			}
		}
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp+" order by t1.REG_TIME desc");
		sqlQuery.addScalar("comId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nature", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nodeCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("flag", StandardBasicTypes.STRING);
		sqlQuery.addScalar("linkMan", StandardBasicTypes.STRING);
		sqlQuery.addScalar("phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fax", StandardBasicTypes.STRING);
		sqlQuery.addScalar("addr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("email", StandardBasicTypes.STRING);
		sqlQuery.addScalar("area", StandardBasicTypes.STRING);
		sqlQuery.addScalar("introduction", StandardBasicTypes.STRING);
		sqlQuery.addScalar("corporate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("regCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("licenseNum", StandardBasicTypes.STRING);
		sqlQuery.addScalar("licenseImg", StandardBasicTypes.STRING);
		sqlQuery.addScalar("state", StandardBasicTypes.STRING);
		sqlQuery.addScalar("regTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("recordDate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nodeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comLogo", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Company.class));
		List<Company> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		String hql = "select count(*) " +
				"from tb_company t1 LEFT JOIN tb_company t2 on t1.parent_id=t2.com_id  "+
				"left join ts_enterprise t3 on t1.area=t3.ent_id  "+temp;
		Integer count = this.countBySql(hql);
		
		Pager<Company> pager = new Pager<Company> ();
		pager.setData ( list );
		pager.setTotal ( count.intValue());
		return pager;
		
	}
	
	public Company findCompany(int comId){
		String hql = "from Company where comId="+comId;		
		List<Company> list = find(hql);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<Company> findList(Company company) {
		
		String sql ="select t1.COM_ID as comId" +
					",t1.name as name" +
					",t1.PARENT_ID as parentId" +
					",t1.nature as nature" +
					",t1.COM_TYPE as comType" +
					",t1.COM_CODE as comCode" +
					",t1.NODE_CODE as nodeCode" +
					",t1.flag as flag" +
					",t1.LINK_MAN as linkMan" +
					",t1.phone as phone" +
					",t1.fax as fax" +
					",t1.addr as addr" +
					",t1.email as email" +
					",t1.area as area" +
					",t1.introduction as introduction" +
					",t1.corporate as corporate" +
					",t1.REG_CODE as regCode" +
					",t1.LICENSE_NUM as licenseNum" +
					",t1.LICENSE_IMG as licenseImg" +
					",t1.COM_LOGO as comLogo" +
					",t1.state as state" +
					",t1.REG_TIME as regTime" +
					",t1.RECORD_DATE as recordDate" +
					",t1.remark as remark" +
					",t1.ENT_ID as entId" +
					",t2.name as nodeName" +
					",t3.name as areaName "+
					"from tb_company t1 LEFT JOIN tb_company t2 on t1.parent_id=t2.com_id  "+
					"left join ts_enterprise t3 on t1.area=t3.ent_id  ";

		String temp=" where 1=1 ";
		if(company!=null){
			if(company.getComId()>0){
				temp+=" and t1.com_id = "+company.getComId();
			}
			if(company.getName() !=null && !"".equals(company.getName())){
				temp+=" and t1.name like '%"+company.getName()+"%'";
			}
			if(company.getFlag() !=null && !"".equals(company.getFlag())){
				temp+=" and t1.flag like '%"+company.getFlag()+"%'";
			}
			if(company.getComType()!=null&&!"".equals(company.getComType())){
				temp+=" and t1.com_type = '"+company.getComType()+"' ";
			}
			if(company.getComCode()!=null&&!"".equals(company.getComCode())){
				temp+=" and t1.com_code = '"+company.getComCode()+"' ";
			}
			if(company.getNodeCode()!=null&&!"".equals(company.getNodeCode())){
				temp+=" and t1.node_code = '"+company.getNodeCode()+"' ";
			}
			if(company.getNature()!=null&&!"".equals(company.getNature())){
				temp+=" and t1.nature = '"+company.getNature()+"' ";
			}
			if(company.getState()!=null&&!"".equals(company.getState())){
				temp+=" and t1.state = '"+company.getState()+"' ";
			}
			if(company.getParentId()==0){
				temp+=" and t1.parent_id = 0 ";
			}
			if(company.getParentId()==-1){
				temp+=" and t1.parent_id <> 0 ";
			}
		}
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+temp+" order by t1.REG_TIME desc");
		sqlQuery.addScalar("comId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("parentId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("name", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nature", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comType", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nodeCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("flag", StandardBasicTypes.STRING);
		sqlQuery.addScalar("linkMan", StandardBasicTypes.STRING);
		sqlQuery.addScalar("phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fax", StandardBasicTypes.STRING);
		sqlQuery.addScalar("addr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("email", StandardBasicTypes.STRING);
		sqlQuery.addScalar("area", StandardBasicTypes.STRING);
		sqlQuery.addScalar("introduction", StandardBasicTypes.STRING);
		sqlQuery.addScalar("corporate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("regCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("licenseNum", StandardBasicTypes.STRING);
		sqlQuery.addScalar("licenseImg", StandardBasicTypes.STRING);
		sqlQuery.addScalar("state", StandardBasicTypes.STRING);
		sqlQuery.addScalar("regTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("recordDate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nodeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("areaName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("comLogo", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Company.class));
		List<Company> list = sqlQuery.list();
		return list;
		
	}
	
	/**
	 * 参数为企业ID，非节点ID
	 * @param comId
	 * @return
	 */
	public String getNodeCode(int comId){
		String sql = "select node_code from tb_company WHERE com_id = (SELECT parent_id from tb_company where com_id="+comId+") ";
		Object object = findObjectBySql(sql);
		return String.valueOf(object);
	}
	
	/**
	 * 参数为企业ID，非节点ID
	 * @param comId
	 * @return
	 */
	public Company getNodeObject(int comId){
		String sql = "select * from tb_company WHERE com_id = (SELECT parent_id from tb_company where com_id="+comId+") ";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addEntity(Company.class);
		List<Company> list = sqlQuery.list();
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public String findCompanyAreaName(int comId){
		String sql="SELECT t2.name from tb_company t1,ts_enterprise t2 where t1.area=t2.ent_id and t1.com_id="+comId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<String> list=sqlQuery.list();
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * 参数为企业ID，非节点ID
	 * @param comId
	 * @return
	 */
	public String getComCode(int comId){
		String sql = "select com_code from tb_company WHERE com_id ="+comId+" ";
		Object object = findObjectBySql(sql);
		return String.valueOf(object);
	}
	
	
	
	/**
     * 验证企业名称是否唯一
     * @param account
     * @return
     */
	public boolean findNameIsUnique(String name) {
		String hql="select count(*) from Company";
		if(!"".equals(name)){
			hql +=" where name='"+name+"'";
		}
		Long i = this.count(hql);
		if(i.longValue()>0L){
			return true;
		}
		return false;
	}
}
