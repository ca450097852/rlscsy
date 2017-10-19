package com.hontek.review.dao;


import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.review.pojo.TbProBatch;
/**
 * <p>Title: 产品批次信息</p>
 * <p>Description: 产品批次信息DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProBatchDao extends BaseDao<TbProBatch> {

	public TbProBatch getProBatchByNo(String batchNo) {
		String hql = "from TbProBatch where batchNo='"+batchNo+"'";
		List<TbProBatch> list = this.find(hql);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public int findProBatchByNo(String batchNo) {
		String sql = "select count(*) from Tb_Pro_Batch where batch_No='"+batchNo+"'";

		return countBySql(sql);
	}

	public TbProBatch getProBatchByDimenno(String dimenno) {
		if(dimenno!=null&&!"".equals(dimenno.trim())){
			String hql = "from TbProBatch where dimenno='"+dimenno+"'";
			return this.get(hql);
		}
		return null;
	}


	
}
