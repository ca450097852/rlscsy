package com.hontek.review.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProcessDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbProcess;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProcessServiceInter;

/**
 * <p>Title: 加工包装</p>
 * <p>Description: 加工包装SERVICE</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProcessServiceImpl extends BaseServiceImpl implements ProcessServiceInter{
	private ProcessDao processDao;
	private TraceAppdixDao traceAppdixDao;

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}
	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}
	/**
	 * 删除
	 */
	public String deleteProcessByIds(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				this.processDao.deleteProcessByIds(ids);
				//删除对应附件信息
				traceAppdixDao.deleteByPids(ids,"5");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}
	/**
	 * 添加
	 */
	public String addProcess(TbProcess process, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "添加成功";
		if(process!=null){
			try {
				process.setProcessId(this.processDao.getTableSequence("tb_process".toUpperCase()));
				processDao.save(process);
				if(traceAppList!=null){
					for(TbTraceAppdix ta:traceAppList){
						if(ta!=null){
							ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
							ta.setPid(process.getProcessId());
							ta.setUploadTime(DateUtil.formatDateTime());
							ta.setProId(process.getProId());
							ta.setAppdixType("5");
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
	/**
	 * 修改
	 */
	public String updateProcess(TbProcess process, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "修改成功";
		try {
			processDao.update(process);
			traceAppdixDao.deleteByPid(process.getProcessId(),"5");
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(process.getProcessId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(process.getProId());
						ta.setAppdixType("5");
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
	
	/**
	 * 查询
	 */
	public String findProcessList(TbProcess process, int page, int rows,
			String sort, String order) {
		//拼接查询条件
		StringBuffer condition = new StringBuffer("");
		if(process!=null){
			int proId = process.getProId();
			if(proId>0){
				condition.append(" and proId=").append(proId).append(" ");
			}
		}		
		//添加排序
		condition.append(sortCondtion(sort, order));	
		
		Pager<TbProcess> pager = processDao.findProcessList(condition.toString(),page,rows);
		List<TbProcess> list = pager.getData();
		//添加附件查询
		for (TbProcess proc : list) {
			List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(proc.getProcessId(),"5");
			proc.setTraceAppdixs(traceAppdixs);
		}
		return this.createEasyUiJson(pager);
	}
	
	/**
	 * 前台查询
	 */
	public String findProcessList(TbProcess process, int page, int rows) {
		//拼接查询条件
		StringBuffer condition = new StringBuffer("");
		if(process!=null){
			int proId = process.getProId();
			if(proId>0){
				condition.append(" and proId=").append(proId).append(" ");
			}
		}		
		
		Pager<TbProcess> pager = processDao.findProcessList(condition.toString(),page,rows);
		if(pager.getData()!=null&&pager.getData().size()>0){
			List<TbProcess> list = pager.getData();
			List<TbProcess> savelist = null;
			@SuppressWarnings("unused")
			TbTraceAppdix traceAppdix = null;
			for(TbProcess plan:list){ 
				List<TbTraceAppdix> tracelist = this.traceAppdixDao.findTraceAppdixsListByProId(plan.getProId(),"5");
				if(tracelist.size()>0){
					plan.setTraceAppdixs(tracelist);
					savelist.add(plan);
				}
			}
			pager.setData(savelist);
		}
		return this.createEasyUiJson(pager);
	}
	
}
