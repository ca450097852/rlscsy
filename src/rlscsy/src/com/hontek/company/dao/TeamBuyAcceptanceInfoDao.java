package com.hontek.company.dao;




import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.TeamBuyAcceptanceInfo;

/**
 *   团体消费进货验收信息表
 * @author chenan
 *
 */
public class TeamBuyAcceptanceInfoDao extends BaseDao<TeamBuyAcceptanceInfo>{
	
	/*
	 * 根据comId分页查找
	 */
	public Pager<TeamBuyAcceptanceInfo> findTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo,int page, int rows){
		int comId=teamBuyAcceptanceInfo.getComId();
		String sql = "SELECT tbai_id as tbaiId," +
				"com_id as comId,t.team_consume_id as teamConsumeId,team_consume_name as teamConsumeName,"+
				"in_date as inDate,supplier_id as supplierId,supplier_name as supplierName," +
				"tran_id as tranId,goods_code as goodsCode,goods_name as goodsName,weight,price,"+
				"ws_supplier_id as wsSupplierId,ws_supplier_name as wsSupplierName "+
				" from tb_team_buy_acceptance_info t where 1=1 and com_id="+comId;		
		String count_sql = "select count(*) from tb_team_buy_acceptance_info t1 where 1=1 and com_id="+comId;
		
		sql+=" order by in_date desc";
		
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.aliasToBean(TeamBuyAcceptanceInfo.class));
		List<TeamBuyAcceptanceInfo> list = query.setFirstResult(( page - 1) * rows).setMaxResults(rows).list();

		int count = this.countBySql(count_sql).intValue();

		Pager<TeamBuyAcceptanceInfo> pager = new Pager<TeamBuyAcceptanceInfo>();
		pager.setData(list);
		pager.setTotal(count);
		
		return pager;
	
	}
}
