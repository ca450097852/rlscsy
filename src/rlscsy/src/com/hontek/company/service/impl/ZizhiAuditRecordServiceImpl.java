package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ZizhiAuditRecordDao;
import com.hontek.company.dao.ZizhiDao;
import com.hontek.company.pojo.ZizhiAuditRecord;
import com.hontek.company.service.inter.ZizhiAuditRecordServiceInter;
import com.hontek.sys.pojo.TsUser;


/**
 * <p>Title: 资质审核信息表</p>
 * <p>Description: 资质审核信息表接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAuditRecordServiceImpl extends BaseServiceImpl implements ZizhiAuditRecordServiceInter {

	private ZizhiAuditRecordDao zizhiAuditRecordDao;
	private ZizhiDao zizhiDao;
	
	Logger logger = Logger.getLogger(ZizhiAuditRecordServiceImpl.class);
	
	public void setZizhiAuditRecordDao(ZizhiAuditRecordDao zizhiAuditRecordDao) {
		this.zizhiAuditRecordDao = zizhiAuditRecordDao;
	}

	public void setZizhiDao(ZizhiDao zizhiDao) {
		this.zizhiDao = zizhiDao;
	}


	/**
	 * 添加生产信息
	 */
	public String addZizhiAuditRecord(ZizhiAuditRecord zizhiAuditRecord) {
		String msg = "申请资质审核成功!";
		try {
			zizhiAuditRecord.setAuditId(zizhiAuditRecordDao.getTableSequence("tb_zizhiAuditRecord".toUpperCase()));
			zizhiAuditRecord.setApplyTime(DateUtil.formatDateTime());						
			zizhiAuditRecordDao.save(zizhiAuditRecord);
			
			zizhiDao.updateState(zizhiAuditRecord.getZid(), "2");
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加资质审核出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除资质审核
	 */
	public String deleteZizhiAuditRecord(String ids) {
		String msg = "删除资质审核成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					ZizhiAuditRecord zizhiAuditRecord = zizhiAuditRecordDao.get(ZizhiAuditRecord.class, Integer.valueOf(idArray[i]));
					if(zizhiAuditRecord!=null){
						zizhiAuditRecordDao.delete(zizhiAuditRecord);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除资质审核出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	

	/**
	 * 发布资质审核
	 */
	public String updateAuditZizhiAuditRecord(String ids,TsUser tsUser) {
		String msg = "批量资质审核成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					ZizhiAuditRecord zizhiAuditRecord = zizhiAuditRecordDao.get(ZizhiAuditRecord.class, Integer.valueOf(idArray[i]));
					if(zizhiAuditRecord!=null){
						zizhiAuditRecord.setAuditState("1");
						zizhiAuditRecord.setAuditTime(DateUtil.formatDateTime());
						zizhiAuditRecord.setOpinion("同意");						
						
						zizhiAuditRecord.setAuditEntid(tsUser.getEntId());
						zizhiAuditRecord.setAuditEntname(tsUser.getEntName());
						zizhiAuditRecord.setAuditUser(tsUser.getUserId());
						zizhiAuditRecord.setAuditUsername(tsUser.getUserName());
						zizhiAuditRecord.setAuditTime(DateUtil.formatDateTime());
						
						zizhiAuditRecordDao.update(zizhiAuditRecord);
						
						zizhiDao.updateState(zizhiAuditRecord.getZid(), "3");
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "批量资质审核出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
	/**
	 * 分页查询资质审核
	 */
	public String findZizhiAuditRecordPagerList(ZizhiAuditRecord zizhiAuditRecord,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(zizhiAuditRecord!=null){
				int entId = zizhiAuditRecord.getEntId();
				if(entId>0){
					condition.append(" and entId = "+entId+" ");
				}
				
				int auditEntid = zizhiAuditRecord.getAuditEntid();
				if(auditEntid>0){
					condition.append(" and auditEntid = "+auditEntid+" ");
				}
				
				String zid = zizhiAuditRecord.getZid();
				if(zid!=null&&!"".equals(zid)){
					condition.append(" and zid = "+zid+" ");
				}
				
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<ZizhiAuditRecord>  pager = zizhiAuditRecordDao.findPager(ZizhiAuditRecord.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询资质审核出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改资质审核
	 */
	public String updateZizhiAuditRecord(ZizhiAuditRecord zizhiAuditRecord) {
		String msg = "资质审核成功!";
		try {
			zizhiAuditRecordDao.update(zizhiAuditRecord);
			String state = zizhiAuditRecord.getAuditState();
			if("1".equals(state)){
				//通过
				String zid = zizhiAuditRecord.getZid();
				zizhiDao.updateState(zid, "3");
			}else{
				String zid = zizhiAuditRecord.getZid();
				zizhiDao.updateState(zid, "1");
			}		
		} catch (AppException e) {
			e.printStackTrace();
			msg = "资质审核出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
