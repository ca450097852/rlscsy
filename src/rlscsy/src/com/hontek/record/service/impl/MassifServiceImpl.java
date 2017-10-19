package com.hontek.record.service.impl;

import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.dao.MassifDao;
import com.hontek.record.pojo.TbMassif;
import com.hontek.record.service.inter.MassifServiceInter;
import com.hontek.sys.pojo.TsUser;
/**
 * 分类地块信息实现类
 *
 */
public class MassifServiceImpl extends BaseServiceImpl implements MassifServiceInter{
	private MassifDao massifDao;

	public void setMassifDao(MassifDao massifDao) {
		this.massifDao = massifDao;
	}

	//log4j 日志处理
	Logger logger = Logger.getLogger(MassifServiceImpl.class);

	/**
	 * 添加地块
	 */
	public String addMassif(TbMassif massif,TsUser user,HttpServletRequest request) {
		String jsonMsg = "添加地块成功!";
		try{			
			massif.setMaId(massifDao.getTableSequence("tb_massif"));			
			this.massifDao.save(massif);									
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加地块失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;					
	}

	public String deleteMassif(String ids) {
		String jsonMsg ="删除失败!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (String id : idArray) {
					TbMassif massif = massifDao.get(TbMassif.class, Integer.valueOf(id));
					if(massif!=null){
						massifDao.delete(massif);										
						jsonMsg ="删除成功!";
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String findMassif(TbMassif massif,int page, int rows, String order, String sort) {
		String jsonMsg ="";
		if(massif!=null){
			int ptqId = massif.getPtqId();
			String hql = "from TbMassif where ptqId ="+ptqId;			
			List<TbMassif> list = massifDao.find(hql, page, rows);
			
			String countHQL = "select count(*) from TbMassif where ptqId ="+ptqId;
			int total = massifDao.count(countHQL).intValue();
			
			Pager<TbMassif> pager = new Pager<TbMassif>();			
			
			pager.setData(list);
			pager.setTotal(total);
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}
	
	
	public String findMassif(String ptaId,int page, int rows, String order, String sort) {
		String jsonMsg ="";
		if(ptaId!=null&&!"".equals(ptaId)){
			
			Pager<TbMassif> pager = massifDao.findMassifList(ptaId, page, rows);						
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}

	public String updateMassif(TbMassif massif) {
		String msg = "修改成功!";
		try {
			massifDao.update(massif);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败!"+e.getMessage();
		}
		
		return msg;
	}
	


}
