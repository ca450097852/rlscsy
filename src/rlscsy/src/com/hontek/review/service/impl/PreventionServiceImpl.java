/**
 * 
 */
package com.hontek.review.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.PreventionDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbPrevention;
import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.PreventionServiceInter;

/**
 * @ClassName: PreventionServiceI
 * @Description: TODO(防疫表)
 * @date 2014-11-19 下午05:58:58
 * @author qql
 * @version 1.0
 */
public class PreventionServiceImpl extends BaseServiceImpl implements PreventionServiceInter {
	private PreventionDao preventionDao;
	
	@SuppressWarnings("unused")
	private TraceAppdixDao traceAppdixDao;

	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	public void setPreventionDao(PreventionDao preventionDao) {
		this.preventionDao = preventionDao;
	}

	public String addPrevention(TbPrevention prevention, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "添加失败!";
		try {
			prevention.setPtId(preventionDao.getTableSequence("TB_PREVENTION"));
			preventionDao.save(prevention);
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(prevention.getPtId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(prevention.getProId());
						ta.setAppdixType("4");
						traceAppdixDao.save(ta);
					}
				}
			}
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String updatePrevention(TbPrevention prevention, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "修改失败";
		try {
			preventionDao.update(prevention);
			traceAppdixDao.deleteByPid(prevention.getPtId(),"4");
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(prevention.getPtId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(prevention.getProId());
						ta.setAppdixType("4");
						traceAppdixDao.save(ta);
					}
				}
			}
			jsonMsg = "修改成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	public String deletePrevention(String ids) {
		String jsonMsg = "删除失败";
		try {
			String condition = " and ptId in ("+ids+")";
			Pager<TbPrevention> pager = preventionDao.findList(condition,1,1000);
			List<TbPrevention> list = pager.getData();
			for(TbPrevention tpa:list){
				//删除对应附件信息
				traceAppdixDao.deleteByPid(tpa.getPtId(),"4");
				
				preventionDao.delete(tpa);
				
			}
			jsonMsg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	public String findPreventionList(TbPrevention prevention, int page,
			int rows, String order, String sort) {
		StringBuffer condition = new StringBuffer();
		if(prevention!=null){
			if(prevention.getPtId()!=0){
				condition.append(" and pt.Pt_ID="+prevention.getPtId());
			}
			if(prevention.getProId()!=0){
				condition.append(" and pt.pro_id="+prevention.getProId());
			}
		}
		if(sort!=null&&order!=null){
			condition.append(sortCondtion(sort, order));
		}
		Pager<TbPrevention> pager = preventionDao.findPreventionList(condition.toString(),page,rows);
		List<TbPrevention> list = pager.getData();
		//添加附件查询
		for (TbPrevention p : list) {
			List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(p.getPtId(),"4");
			p.setTraceAppdixs(traceAppdixs);
		}
		
		return createEasyUiJson(pager);
	}

	public String findPreventionList(TbPrevention prevention, int page, int rows) {
		StringBuffer condition = new StringBuffer();
		if(prevention!=null){
			if(prevention.getPtId()!=0){
				condition.append(" and pt.Pt_ID="+prevention.getPtId());
			}
			if(prevention.getProId()!=0){
				condition.append(" and pt.pro_id="+prevention.getProId());
			}
		}
		Pager<TbPrevention> pager = preventionDao.findPreventionList(condition.toString(),page,rows);
		if(pager.getData()!=null&&pager.getData().size()>0){
			List<TbPrevention> list = pager.getData();
			for(TbPrevention prev:list){
				List<TbTraceAppdix> tracelist = this.traceAppdixDao.findTraceAppdixsListByPid(prev.getPtId(),"4");
				if(tracelist.size()>0){
					prev.setTraceAppdixs(tracelist);
				}
			}
		}
		
		return createEasyUiJson(pager);
	}
	


}
