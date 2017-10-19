/**
 * 
 */
package com.hontek.review.service.impl;
import java.util.List;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.PlantRaiseDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbPlantRaise;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.PlantRaiseServiceInter;

/**
 * @ClassName: PlantRaiseServiceImpl
 * @Description: TODO(施肥喂养表)
 * @date 2014-11-19 下午05:40:18
 * @author qql
 * @version 1.0
 */
public class PlantRaiseServiceImpl extends BaseServiceImpl implements PlantRaiseServiceInter {
	
	private PlantRaiseDao plantRaiseDao;
	
	@SuppressWarnings("unused")
	private TraceAppdixDao traceAppdixDao;

	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}
	public void setPlantRaiseDao(PlantRaiseDao plantRaiseDao) {
		this.plantRaiseDao = plantRaiseDao;
	}

	public String addPlantRaise(TbPlantRaise plantRaise, List<TbTraceAppdix> traceAppList) {
		String jsonMsg = "添加失败!";
		try {
			plantRaise.setPrId(plantRaiseDao.getTableSequence("TB_PLANT_RAISE"));
			plantRaiseDao.save(plantRaise);
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(plantRaise.getPrId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(plantRaise.getProId());
						ta.setAppdixType("3");
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
	
	public String updatePlantRaise(TbPlantRaise plantRaise, List<TbTraceAppdix> traceAppList){
		String jsonMsg = "修改失败!";
		try {
			plantRaiseDao.update(plantRaise);
			int pId = plantRaise.getPrId();
			traceAppdixDao.deleteByPid(pId,"3");
			
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(pId);
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(plantRaise.getProId());
						ta.setAppdixType("3");
						traceAppdixDao.save(ta);
					}
				}
			}
			jsonMsg = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String deletePlantRaise(String ids) {
		String jsonMsg = "删除失败";
		try {
			String condition = " and prId in ("+ids+")";
			Pager<TbPlantRaise> pager = plantRaiseDao.findList(condition,1,1000);
			List<TbPlantRaise> list = pager.getData();
			for(TbPlantRaise tpa:list){
				//删除对应附件信息
				traceAppdixDao.deleteByPid(tpa.getPrId(),"3");
				plantRaiseDao.delete(tpa);								
			}
			jsonMsg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String findPlantRaiseList(TbPlantRaise plantRaise, int page,
			int rows, String order, String sort) {
		StringBuffer condition = new StringBuffer();
		if(plantRaise!=null){
			if(plantRaise.getPrId()!=0){
				condition.append(" and pr.Pr_ID="+plantRaise.getPrId());
			}
			if(plantRaise.getProId()!=0){
				condition.append(" and pr.pro_id="+plantRaise.getProId());
			}
		}
		if(sort!=null&&order!=null){
			condition.append(sortCondtion(sort, order));
		}
		Pager<TbPlantRaise> pager = plantRaiseDao.findPlantRaiseList(condition.toString(),page,rows);
		List<TbPlantRaise> list = pager.getData();
		//添加附件查询
		for (TbPlantRaise tbPlantRaise : list) {
			List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(tbPlantRaise.getPrId(),"3");
			tbPlantRaise.setTraceAppdixs(traceAppdixs);
		}

		return createEasyUiJson(pager);
	}

	/**
	 * 列表:(有图片列)
	 */
	@SuppressWarnings("null")
	public String findPlantRaiseList(TbPlantRaise plantRaise, int page, int rows) {
		StringBuffer condition = new StringBuffer();
		if(plantRaise!=null){
			if(plantRaise.getPrId()!=0){
				condition.append(" and pr.Pr_ID="+plantRaise.getPrId());
			}
			if(plantRaise.getProId()!=0){
				condition.append(" and pr.pro_id="+plantRaise.getProId());
			}
		}
		Pager<TbPlantRaise> pager = plantRaiseDao.findPlantRaiseList(condition.toString(),page,rows);
		
		if(pager.getData()!=null&&pager.getData().size()>0){
			List<TbPlantRaise> list = pager.getData();
			for(TbPlantRaise plan:list){ 
				List<TbTraceAppdix> tracelist = this.traceAppdixDao.findTraceAppdixsListByPid(plan.getPrId(),"3");
				if(tracelist.size()>0){
					plan.setTraceAppdixs(tracelist);
				}
			}
		}
		
		return createEasyUiJson(pager);
	}

}
