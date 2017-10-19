package com.hontek.review.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.StoreTransportDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbStoreTransport;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.StoreTransportServiceInter;
/**
 * <p>Title: 仓储运输</p>
 * <p>Description: 仓储运输</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class StoreTransportServiceImpl extends BaseServiceImpl implements StoreTransportServiceInter{
	private StoreTransportDao storeTransportDao;
	private TraceAppdixDao traceAppdixDao;
	public void setStoreTransportDao(StoreTransportDao storeTransportDao) {
		this.storeTransportDao = storeTransportDao;
	}

	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	public String addStoreTransport(TbStoreTransport storeTransport, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "添加成功";
		try {
			storeTransport.setStId(this.storeTransportDao.getTableSequence("tb_store_transport".toUpperCase()));
			this.storeTransportDao.save(storeTransport);
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(storeTransport.getStId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(storeTransport.getProId());
						ta.setAppdixType("6");
						traceAppdixDao.save(ta);
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteStoreTransportByIds(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				storeTransportDao.deleteStoreTransportByIds(ids);
				//删除对应附件信息
				traceAppdixDao.deleteByPids(ids,"6");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String updateStoreTransport(TbStoreTransport storeTransport, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "修改成功";
		try {
			storeTransportDao.update(storeTransport);
			traceAppdixDao.deleteByPid(storeTransport.getStId(),"6");
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(storeTransport.getStId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(storeTransport.getProId());
						ta.setAppdixType("6");
						traceAppdixDao.save(ta);
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}

	public String findStoreTransportList(TbStoreTransport storeTransport,int page, int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(storeTransport!=null){
			int proId = storeTransport.getProId();
			if(proId>0){
				condition.append(" and proId = ").append(proId).append(" ");
			}
		}
		condition.append(sortCondtion(sort, order));
		Pager<TbStoreTransport> pager = storeTransportDao.findStoreTransportList(condition.toString(),page,rows);
		
		List<TbStoreTransport> list = pager.getData();
		//添加附件查询
		for (TbStoreTransport st : list) {
			List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(st.getStId(),"6");
			st.setTraceAppdixs(traceAppdixs);
		}
		
		return this.createEasyUiJson(pager);
	}
	
}
