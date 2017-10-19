package com.hontek.review.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProCheckDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbProArea;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProCheckServiceInter;

/**
 * <p>Title: 产品检验</p>
 * <p>Description:产品检验 SERVICE</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProCheckServiceImpl extends BaseServiceImpl implements ProCheckServiceInter{
	private ProCheckDao proCheckDao;
	private TraceAppdixDao traceAppdixDao;

	public void setProCheckDao(ProCheckDao proCheckDao) {
		this.proCheckDao = proCheckDao;
	}
	
	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}


	public String addProCheck(TbProCheck proCheck, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "添加成功";
		if(proCheck!=null){
			try {
				proCheck.setCheckId(proCheckDao.getTableSequence("tb_pro_check".toUpperCase()));
				proCheckDao.save(proCheck);
				if(traceAppList!=null){
					for(TbTraceAppdix ta:traceAppList){
						if(ta!=null){
							ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
							ta.setPid(proCheck.getCheckId());
							ta.setUploadTime(DateUtil.formatDateTime());
							ta.setProId(proCheck.getProId());
							ta.setAppdixType("7");
							traceAppdixDao.save(ta);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonMsg = "添加失败";
			}
		}
		return jsonMsg;
	}

	public String deleteProCheckByIds(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				proCheckDao.deleteProCheckByIds(ids);
				//删除对应附件信息
				traceAppdixDao.deleteByPids(ids,"7");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String updateProCheck(TbProCheck proCheck, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "修改成功";
		try {
			if(proCheck!=null){
				proCheckDao.update(proCheck);
				traceAppdixDao.deleteByPid(proCheck.getCheckId(),"7");
				if(traceAppList!=null){
					for(TbTraceAppdix ta:traceAppList){
						if(ta!=null){
							ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
							ta.setPid(proCheck.getCheckId());
							ta.setUploadTime(DateUtil.formatDateTime());
							ta.setProId(proCheck.getProId());
							ta.setAppdixType("7");
							traceAppdixDao.save(ta);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}

	public String findProCheckList(TbProCheck proCheck, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(proCheck!=null){
			int proId = proCheck.getProId();
			if(proId>0){
				condition.append(" and proId=").append(proId).append(" ");
			}
		}
		condition.append(this.sortCondtion(sort, order));
		
		Pager<TbProCheck> pager = proCheckDao.findProCheckList(condition.toString(),page,rows);
		List<TbProCheck> list = pager.getData();
		//添加附件查询
		for (TbProCheck check : list) {
			List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(check.getCheckId(),"7");
			check.setTraceAppdixs(traceAppdixs);
		}
		return this.createEasyUiJson(pager);
	}
	
	/*
	 * 前台查询
	 */
	public String findProCheckList(TbProCheck proCheck, int page, int rows) {
		StringBuffer condition = new StringBuffer();
		if(proCheck!=null){
			int proId = proCheck.getProId();
			if(proId>0){
				condition.append(" and proId=").append(proId).append(" ");
			}
		}
		
		Pager<TbProCheck> pager = proCheckDao.findProCheckList(condition.toString(),page,rows);
		if(pager.getData()!=null&&pager.getData().size()>0){
			List<TbProCheck> list = pager.getData();
			//添加附件查询
			for (TbProCheck check : list) {
				List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(check.getCheckId(),"7");
				check.setTraceAppdixs(traceAppdixs);
			}
		}
		return this.createEasyUiJson(pager);
	}
	
}
