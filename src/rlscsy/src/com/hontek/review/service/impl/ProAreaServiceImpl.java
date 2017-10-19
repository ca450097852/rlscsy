package com.hontek.review.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProAreaDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbProArea;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProAreaServiceInter;
/**
 * <p>Title: 产地信息</p>
 * <p>Description: 产地信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProAreaServiceImpl extends BaseServiceImpl implements ProAreaServiceInter {

	private ProAreaDao proAreaDao;
	private TraceAppdixDao traceAppdixDao;
	/**
	 * 注入DAO
	 * @param proAreaDao
	 */
	public void setProAreaDao(ProAreaDao proAreaDao) {
		this.proAreaDao = proAreaDao;
	}
	
	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加产地信息
	 */
	public String addProArea(TbProArea proArea) {
		String msg = "添加产地信息成功!";
		try {
			proArea.setAreaId(proAreaDao.getTableSequence("tb_pro_area".toUpperCase()));
			proAreaDao.save(proArea);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加产地信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除产地信息
	 */
	public String deleteProArea(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbProArea proArea = proAreaDao.get(TbProArea.class, Integer.valueOf(idArray[i]));
					if(proArea!=null){
						proAreaDao.delete(proArea);
						//删除对应附件信息
						traceAppdixDao.deleteByPid(Integer.valueOf(idArray[i]),"1");
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询产地信息
	 */
	public String findProAreaList(TbProArea proArea, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(proArea!=null){
				String areaName = proArea.getAreaName();//名称
				if(areaName!=null&&!"".equals(areaName)){
					condition.append(" and areaName like '%"+areaName+"%' ");
				}						
				int proId = proArea.getProId();
				if(proId!=0){
					condition.append(" and proId = "+proId+" ");
				}
				int areaId = proArea.getAreaId();				
				if(areaId!=0){
					condition.append(" and areaId = "+areaId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbProArea>  pager = proAreaDao.findPager(TbProArea.class,condition.toString(), page, rows);			
			List<TbProArea> list = pager.getData();
			//添加附件查询
			for (TbProArea area : list) {
				List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(area.getAreaId(),"1");
				area.setTraceAppdixs(traceAppdixs);
			}
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询产地信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 前台查询产地信息
	 */
	public String findProAreaList(TbProArea proArea, int page, int rows) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(proArea!=null){
				String areaName = proArea.getAreaName();//名称
				if(areaName!=null&&!"".equals(areaName)){
					condition.append(" and areaName like '%"+areaName+"%' ");
				}						
				int proId = proArea.getProId();
				if(proId>0){
					condition.append(" and proId = "+proId+" ");
				}
				int areaId = proArea.getAreaId();				
				if(areaId>0&&!"".equals(areaId)){
					condition.append(" and areaId = "+areaId+" ");
				}
			}		
			Pager<TbProArea>  pager = proAreaDao.findPager(TbProArea.class,condition.toString(), page, rows);			
			if(pager.getData()!=null&&pager.getData().size()>0){
				List<TbProArea> list = pager.getData();
				for(TbProArea plan:list){ 
					List<TbTraceAppdix> tracelist = this.traceAppdixDao.findTraceAppdixsListByPid(plan.getAreaId(),"1");
					if(tracelist.size()>0){
						plan.setTraceAppdixs(tracelist);
					}
				}
			}
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询产地信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
	/**
	 * 修改产地信息
	 */
	public String updateProArea(TbProArea proArea) {
		String msg = "修改产地信息成功!";
		try {
			proAreaDao.update(proArea);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改产地信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String addProArea(TbProArea proArea, List<TbTraceAppdix> traceAppList) {
		String msg = "添加产地信息成功!";
		try {
			proArea.setAreaId(proAreaDao.getTableSequence("tb_pro_area".toUpperCase()));
			proAreaDao.save(proArea);
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(proArea.getAreaId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(proArea.getProId());
						ta.setAppdixType("1");
						traceAppdixDao.save(ta);
					}
				}				
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加产地信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}

	public String updateProAreaAndApp(TbProArea proArea,List<TbTraceAppdix> traceAppList) {
		String msg = "修改产地信息成功!";
		try {
			proAreaDao.update(proArea);
			traceAppdixDao.deleteByPid(proArea.getAreaId(),"1");
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(proArea.getAreaId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(proArea.getProId());
						ta.setAppdixType("1");
						traceAppdixDao.save(ta);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改产地信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
