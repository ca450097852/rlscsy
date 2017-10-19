package com.hontek.review.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProSeedDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProSeedServiceInter;
/**
 * <p>Title: 种源信息</p>
 * <p>Description: 种源信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProSeedServiceImpl extends BaseServiceImpl implements ProSeedServiceInter {

	private ProSeedDao proSeedDao;
	private TraceAppdixDao traceAppdixDao;
	
	/**
	 * 注入DAO
	 * @param proSeedDao
	 */
	public void setProSeedDao(ProSeedDao proSeedDao) {
		this.proSeedDao = proSeedDao;
	}
	Logger logger = Logger.getLogger(this.getClass());

	
	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	/**
	 * 添加种源信息
	 */
	public String addProSeed(TbProSeed proSeed) {
		String msg = "添加种源信息成功!";
		try {
			proSeed.setSeedId(proSeedDao.getTableSequence("tb_pro_seed".toUpperCase()));
			proSeedDao.save(proSeed);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加种源信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除种源信息
	 */
	public String deleteProSeed(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbProSeed proSeed = proSeedDao.get(TbProSeed.class, Integer.valueOf(idArray[i]));
					if(proSeed!=null){
						proSeedDao.delete(proSeed);
						//删除对应附件信息
						traceAppdixDao.deleteByPid(Integer.valueOf(idArray[i]),"2");
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
	 * 分页查询种源信息
	 */
	public String findProSeedList(TbProSeed proSeed, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(proSeed!=null){
				String areaName = proSeed.getSeedName();//名称
				if(areaName!=null&&!"".equals(areaName)){
					condition.append(" and areaName like '%"+areaName+"%' ");
				}						
				int proId = proSeed.getProId();
				if(proId>0){
					condition.append(" and proId = "+proId+" ");
				}
				int seedId = proSeed.getSeedId();				
				if(seedId>0){
					condition.append(" and seedId = "+seedId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbProSeed>  pager = proSeedDao.findPager(TbProSeed.class,condition.toString(), page, rows);
			List<TbProSeed> list = pager.getData();
			//添加附件查询
			for (TbProSeed seed : list) {
				List<TbTraceAppdix> traceAppdixs = traceAppdixDao.findTraceAppdixsListByPid(seed.getSeedId(),"2");
				seed.setTraceAppdixs(traceAppdixs);
			}
			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询种源信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改种源信息
	 */
	public String updateProSeed(TbProSeed proSeed) {
		String msg = "修改种源信息成功!";
		try {
			proSeedDao.update(proSeed);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改种源信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String addProSeed(TbProSeed proSeed, List<TbTraceAppdix> traceAppList) {
		String msg = "添加种源信息成功!";
		try {
			proSeed.setSeedId(proSeedDao.getTableSequence("tb_pro_area".toUpperCase()));
			proSeedDao.save(proSeed);
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(proSeed.getSeedId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(proSeed.getProId());
						ta.setAppdixType("2");
						traceAppdixDao.save(ta);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加种源信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}

	public String updateProSeed(TbProSeed proSeed,List<TbTraceAppdix> traceAppList) {
		String msg = "修改种源信息成功!";
		try {
			proSeedDao.update(proSeed);
			
			traceAppdixDao.deleteByPid(proSeed.getSeedId(),"2");
			if(traceAppList!=null){
				for(TbTraceAppdix ta:traceAppList){
					if(ta!=null){
						ta.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
						ta.setPid(proSeed.getSeedId());
						ta.setUploadTime(DateUtil.formatDateTime());
						ta.setProId(proSeed.getProId());
						ta.setAppdixType("2");
						traceAppdixDao.save(ta);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改种源信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
