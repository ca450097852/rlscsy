package com.hontek.synchronous.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.synchronous.pojo.DataInfo;

public class DataInfoDao extends BaseDao<DataInfo> {

	public List<DataInfo> findDataInfos(){
		String sql = "SELECT ownerName,fromCity,fromProvince+fromCity+fromCountry+fromTown+fromAddress as fromAddress from dataInfo_ent where date_type in(1,2) order by data_id asc";

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("ownerName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromCity", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromAddress", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(DataInfo.class));

		List<DataInfo> list = sqlQuery.setFirstResult(0).setMaxResults(100).list();

		return list;
		
	}
	
	
	public List<DataInfo> findDataInfo12(String month){
		String sql = "SELECT ownerName,fromCity,fromProvince+fromCity+fromCountry as fromProvince,fromProvince+fromCity+fromCountry+fromTown+fromAddress as fromAddress,systemUserTime,unit,animalTypeName,earMark " +
				"from dataInfo125 where date_type in(1,2) AND systemUserTime like '"+month+"%' AND earMark != '' order by systemUserTime asc";

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("ownerName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromCity", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromProvince", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromAddress", StandardBasicTypes.STRING);
		sqlQuery.addScalar("systemUserTime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("unit", StandardBasicTypes.STRING);
		sqlQuery.addScalar("animalTypeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("earMark", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(DataInfo.class));

		List<DataInfo> list = sqlQuery.list();
		//List<DataInfo> list = sqlQuery.setFirstResult(0).setMaxResults(0).list();

		return list;
		
	}
	
	

	public List<DataInfo> findDataInfoProduct(String ownerName){
		String sql = "SELECT ownerName,unit,animalTypeName,earMark,fromProvince+fromCity+fromCountry+fromTown+fromAddress as fromAddress " +
				"from dataInfo4 where ownerName = '"+ownerName+"'";

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("ownerName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("unit", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fromAddress", StandardBasicTypes.STRING);
		sqlQuery.addScalar("animalTypeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("earMark", StandardBasicTypes.STRING);
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(DataInfo.class));

		List<DataInfo> list = sqlQuery.list();

		return list;
	
	
	}
}
