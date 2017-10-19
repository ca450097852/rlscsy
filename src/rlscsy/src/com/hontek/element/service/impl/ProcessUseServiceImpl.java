package com.hontek.element.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.ProcessUseDao;
import com.hontek.element.pojo.ProcessUse;
import com.hontek.element.service.inter.ProcessUseServiceInter;
/**
 * 加工投入品信息表
 * @author IMZK
 *
 */
public class ProcessUseServiceImpl extends BaseServiceImpl implements ProcessUseServiceInter{
	private ProcessUseDao processUseDao;

	public void setProcessUseDao(ProcessUseDao processUseDao) {
		this.processUseDao = processUseDao;
	}

	public String add(ProcessUse processUse) {
		String msg = "添加失败";
		
		try {
			processUse.setPuId(processUseDao.getTableSequence("tb_process_use".toUpperCase()));
			processUse.setCrttime(DateUtil.formatDateTime());
			processUseDao.save(processUse);
			msg = "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public String update(ProcessUse processUse) {
		String msg = "修改失败";
		
		try {
			processUseDao.update(processUse);
			msg = "修改成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public String delete(String ids) {
		String msg = "删除失败";
		
		try {
			if(ids!=null && !"".equals(ids))
				processUseDao.executeHql("delete from ProcessUse where puId in ("+ids+")");
			msg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String findPager(ProcessUse processUse, int page, int rows,
			String sort, String order) {
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(processUse!=null){
				
				int recId = processUse.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<ProcessUse>  pager = processUseDao.findPager(ProcessUse.class,condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询信息出现异常!"+e.getMessage();
		}		
		return jsonMsg;
	}
	
}
