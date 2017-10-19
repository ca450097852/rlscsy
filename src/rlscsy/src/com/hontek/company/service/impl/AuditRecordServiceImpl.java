package com.hontek.company.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.AuditCtrlDao;
import com.hontek.company.dao.AuditRecordDao;
import com.hontek.company.pojo.AuditRecord;
import com.hontek.company.service.inter.AuditRecordServiceInter;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title: 企业审核记录表</p>
 * <p>Description: 企业审核记录接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class AuditRecordServiceImpl extends BaseServiceImpl implements AuditRecordServiceInter {

	private AuditRecordDao auditRecordDao;
	private AuditCtrlDao auditCtrlDao;
	private EnterpriseDao enterpriseDao;
	
	Logger logger = Logger.getLogger(AuditRecordServiceImpl.class);
	
	public void setAuditRecordDao(AuditRecordDao auditRecordDao) {
		this.auditRecordDao = auditRecordDao;
	}

	public void setAuditCtrlDao(AuditCtrlDao auditCtrlDao) {
		this.auditCtrlDao = auditCtrlDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}


	/**
	 * 添加企业审核记录
	 */
	public String addAuditRecord(AuditRecord auditRecord,TsEnterprise loginEnterprise) {
		String msg = "添加审核成功!";
		try {
			auditRecord.setAuditId(auditRecordDao.getTableSequence("tb_audit_record".toUpperCase()));
			auditRecord.setAuditTime(DateUtil.formatDateTime());
			auditRecordDao.save(auditRecord);
			//2、暂停；3、退回；4、通过
			String state = auditRecord.getAuditState();
			//更改企业审核
			TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, auditRecord.getEntId());
			
			
			if(enterprise!=null){
				//企业所属机构级别
				//int CompanyLevel =  enterpriseDao.getEntLevel(String.valueOf(enterprise.getParentId()));	
				
				//当前审核机构
				String CompanyEnt = String.valueOf(loginEnterprise.getEntId());
				//审核控制企业
				String auditEnt = auditCtrlDao.getAuditEnt(auditRecord.getEntId());
				
				boolean startsWith = auditEnt.concat(",").startsWith(CompanyEnt.concat(","));
				boolean endsWith = auditEnt.concat(",").endsWith(CompanyEnt.concat(","));			
				
				//当前审核机构级别
				int level =  enterpriseDao.getEntLevel(String.valueOf(auditRecord.getAuditEntId()));				
				/*if(level==2){
					//省级操作
					enterprise.setProvinceRsts(state);
					enterprise.setCompanyRsts(state);
					if("3".equals(state)){
						//省退回，市变待审
						if(!startsWith){
							enterprise.setCityRsts(GlobalValueManager.RSTS_DS);							
						}else{
							//省级企业，直接退回到企业
							enterprise.setCompanyRsts(GlobalValueManager.RSTS_NO);
						}
					}
				}else */if(level==2){
					//市级操作
					enterprise.setCityRsts(state);
					enterprise.setCompanyRsts(state);
					if("3".equals(state)){
						//市退回，区变待审
						if(!startsWith){
							enterprise.setAreaRsts(GlobalValueManager.RSTS_DS);
						}else{
							//市级企业，直接退回到企业
							enterprise.setCompanyRsts(GlobalValueManager.RSTS_NO);
						}
					}else if("4".equals(state)){
						//市通过，省变待审
						if(!endsWith){
							enterprise.setProvinceRsts(GlobalValueManager.RSTS_DS);
						}else{
							enterprise.setCompanyRsts(state);
						}
					}
				}else if(level==3){
					//区县级操作
					enterprise.setAreaRsts(state);
					if("3".equals(state)){
						//区退回，镇变待审
						if(!startsWith){
							enterprise.setTownRsts(GlobalValueManager.RSTS_DS);
						}else{
							//区级企业，直接退回到企业
							enterprise.setCompanyRsts(GlobalValueManager.RSTS_NO);
						}						
					}else if("4".equals(state)){
						//区通过，市变待审
						if(!endsWith){
							enterprise.setCityRsts(GlobalValueManager.RSTS_DS);
						}else{
							enterprise.setCompanyRsts(state);
						}
					}
				}else if(level==4){
					//镇级操作
					enterprise.setTownRsts(state);
					if("4".equals(state)){
						//镇通过，区待审
						if(!endsWith){
							enterprise.setAreaRsts(GlobalValueManager.RSTS_DS);
						}else{
							enterprise.setCompanyRsts(state);
						}
					}else if("3".equals(state)){
						//镇级企业，直接退回到企业
						enterprise.setCompanyRsts(GlobalValueManager.RSTS_NO);
					}
				}
				enterpriseDao.update(enterprise);
			}	
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteAuditRecord(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					AuditRecord auditRecord = auditRecordDao.get(AuditRecord.class, Integer.valueOf(idArray[i]));
					if(auditRecord!=null){
						auditRecordDao.delete(auditRecord);
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
	 * 分页查询审核记录
	 */
	public String findAuditRecordPagerList(AuditRecord auditRecord,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(auditRecord!=null){
				//被审核企业名称
				String entName = auditRecord.getEntName();//
				if(entName!=null&&!"".equals(entName)){
					condition.append(" and entName like '%"+entName+"%' ");
				}		
				//被审核企业
				int entId = auditRecord.getEntId();
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId+" ");
				}
			}		
			//添加排序
			String defalutSort = " auditTime desc ";
			condition.append(sortCondtion(sort, order,defalutSort));		
		
			Pager<AuditRecord>  pager = auditRecordDao.findPager(AuditRecord.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询审核记录出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateAuditRecord(AuditRecord auditRecord) {
		String msg = "修改成功!";
		try {
			auditRecordDao.update(auditRecord);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
