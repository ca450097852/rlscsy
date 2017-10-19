package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ValidCodeRecordDao;
import com.hontek.company.pojo.ValidCodeRecord;
import com.hontek.company.service.inter.ValidCodeRecordServiceInter;

/**
 *
 */
public class ValidCodeRecordServiceImpl extends BaseServiceImpl implements ValidCodeRecordServiceInter {
	
	private ValidCodeRecordDao validCodeRecordDao;
	public void setValidCodeRecordDao(ValidCodeRecordDao validCodeRecordDao) {
		this.validCodeRecordDao = validCodeRecordDao;
	}
	
	Logger logger = Logger.getLogger(this.getClass());


	public String addValidCodeRecord(ValidCodeRecord validCodeRecord) {
		String msg = "添加成功!";
		try {
			validCodeRecord.setVrId(validCodeRecordDao.getTableSequence("tb_valid_Code_Record".toUpperCase()));
			validCodeRecord.setCreatTime(DateUtil.formatDateTime());
			validCodeRecordDao.save(validCodeRecord);						
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String findPagerList(ValidCodeRecord validCodeRecord, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(validCodeRecord!=null){
				String dimenno = validCodeRecord.getDimenno();
				if(dimenno!=null&&!"".equals(dimenno)){
					condition.append(" and dimenno = '"+dimenno+"' ");
				}		
			}		
			//添加排序
			String defalutSort = " creatTime desc ";
			condition.append(sortCondtion(sort, order,defalutSort));		
		
			Pager<ValidCodeRecord>  pager = validCodeRecordDao.findPager(ValidCodeRecord.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询记录出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
	
	
	
	
}
