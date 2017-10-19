package com.hontek.review.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.TraceAppdixServiceInter;
/**
 * @ClassName: TraceAppdixServiceImpl
 * @Description: TODO(附件)
 * @date 2014-11-19 下午04:39:17
 * @author qql
 * @version 1.0
 */
public class TraceAppdixServiceImpl extends BaseServiceImpl implements TraceAppdixServiceInter{
	private TraceAppdixDao traceAppdixDao;

	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	public String addTraceAppdix(TbTraceAppdix traceAppdix) {
		String jsonMsg = "添加失败!";
		try {
			traceAppdix.setAppdixId(traceAppdixDao.getTableSequence("TB_TRACE_APPDIX"));
			traceAppdix.setUploadTime(DateUtil.formatDateTime());
			traceAppdixDao.save(traceAppdix);
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String findTraceAppdixList(TbTraceAppdix traceAppdix, int page,
			int rows, String order, String sort) {
		StringBuffer condition = new StringBuffer();
		if(traceAppdix!=null){
			if(traceAppdix.getPid()!=0){
				condition.append(" and app.P_ID="+traceAppdix.getPid());
			}
		}
		if(sort!=null&&order!=null){
			condition.append(sortCondtion(sort, order));
		}
		Pager<TbTraceAppdix> pager = traceAppdixDao.findTraceAppdixList(condition.toString(),page,rows);
		return createEasyUiJson(pager);
	}

	
	/**
	 * 分页列表
	 */
	public String findTraceAppdixList(TbTraceAppdix traceAppdix, int page,
			int rows) {
		StringBuffer condition = new StringBuffer();
		if(traceAppdix!=null){
			if(traceAppdix.getPid()!=0){
				condition.append(" and app.P_ID="+traceAppdix.getPid());
			}
		}
		Pager<TbTraceAppdix> pager = traceAppdixDao.findTraceAppdixList(condition.toString(),page,rows);
		return createEasyUiJson(pager);
	}
	
	public String deleteApps(String ids) {
		String jsonMsg = "删除失败";
		try {
			String condition = " and appdixId in ("+ids+")";
			Pager<TbTraceAppdix> pager = traceAppdixDao.findAppList(condition,1,1000);
			List<TbTraceAppdix> list = pager.getData();
			for(TbTraceAppdix tpa:list){
				File file = new File(DimennoValueManager.ProImg+tpa.getAppdixUrl());//这里还需调整(图片路径问题)
				if(file.exists()){
					file.delete();
				}
				traceAppdixDao.delete(tpa);
			}
			jsonMsg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	/**
	 * 根据产品ID及附件类型查询附件信息
	 */
	public String findTraceAppdixsListByProId(TbTraceAppdix traceAppdix){
		String jsonMsg = ""; 
		if(traceAppdix!=null){
			int proId = traceAppdix.getProId();
			String appdixType = traceAppdix.getAppdixType();
			if(proId>0&&appdixType!=null&&!"".equals(appdixType)){
				List<TbTraceAppdix> list = traceAppdixDao.findTraceAppdixsListByProId(proId, appdixType);
				jsonMsg = getJSON(list);
			}
		}
		return jsonMsg;
	}
	
	public String findTraceAppdixsListByPid(TbTraceAppdix traceAppdix){
		String jsonMsg = ""; 
		if(traceAppdix!=null){
			int pId = traceAppdix.getPid();
			String appdixType = traceAppdix.getAppdixType();
			List<TbTraceAppdix> list = traceAppdixDao.findTraceAppdixsListByPid(pId,appdixType);
			jsonMsg = getJSON(list);
		}
		return jsonMsg;
	}
}
