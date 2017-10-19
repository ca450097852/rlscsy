package com.hontek.info.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.info.dao.InfoDao;
import com.hontek.info.dao.InfoRecordDao;
import com.hontek.info.dao.InfotempDao;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.pojo.TInfoRecord;
import com.hontek.info.pojo.TInfotemp;
import com.hontek.info.service.inter.InfotempServiceInter;

public class InfotempServiceImpl extends BaseServiceImpl implements InfotempServiceInter {
	
	private InfotempDao infotempDao;
	private InfoDao infoDao;
	private InfoRecordDao infoRecordDao;
	Logger logger = Logger.getLogger(this.getClass());  //日志
	
	public InfoRecordDao getInfoRecordDao() {
		return infoRecordDao;
	}

	public void setInfoRecordDao(InfoRecordDao infoRecordDao) {
		this.infoRecordDao = infoRecordDao;
	}

	public InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public InfotempDao getInfotempDao() {
		return infotempDao;
	}

	public void setInfotempDao(InfotempDao infotempDao) {
		this.infotempDao = infotempDao;
	}
	/**
	 * 临时资讯列表
	 */
	public String findInfotempList(TInfotemp tInfotemp, int page, int rows,
			String sort, String order) {
		//拼接查询条件
		StringBuffer condition = new StringBuffer("");
		if(tInfotemp!=null){
			Long infoType = tInfotemp.getTypeId();
			String title=tInfotemp.getTitle();
			String crttime=tInfotemp.getCrttime();
			
			if(infoType!=null&&infoType!=0l){
				condition.append(" and info.TYPE_NAME = "+infoType);
			}
			
			if(title!=null&&!"".equals(title)){
				condition.append(" and info.TITLE like '%"+title+"%' ");
			}
			if(crttime!=null&&!"".equals(crttime)){
				condition.append(" and info.CRTTIME = '"+crttime+"' ");
			}
			
		}		
		//添加排序
		condition.append(sortCondtion(sort, order));
		
		String jsonMsg ="查询成功";
		try {
			
			Pager<TInfotemp>  pager = infotempDao.findInfotempList(condition.toString(), page, rows);
		 
			jsonMsg = this.createEasyUiJson(pager);
			
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询出现异常!"+e.getMessage();
			logger.debug(jsonMsg);
		}		
		return jsonMsg;
	}
	/**
	 * 添加临时资讯
	 */
	public String addInfotemp(TInfotemp tInfotemp) {
		String jsonMsg = "添加信息成功!";
		try{
			tInfotemp.setTid(infotempDao.getTableSequence("tb_infotemp")+0l);
			if("".equals(tInfotemp.getSysCode()) || tInfotemp.getSysCode()==null  ){
				tInfotemp.setSysCode("0");
			}
			infotempDao.save(tInfotemp);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加信息失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	/**
	 * 删除临时资讯
	 */
	public String deleteInfotemp(String tIds) {
		String jsonString = "";
		try {
			String[] infos = tIds.split(",");
			int w = 0;
			for (int i = 0; i < infos.length; i++) {
				TInfotemp infotemp = infotempDao.getInfotempById(Long.parseLong(infos[i]));
				if(infotemp!=null){
					infotempDao.delete(infotemp);
					w++;
				}				
			}
			jsonString = "操作成功,共删除" + w + "记录!";
		} catch (Exception e) {
			jsonString = "删除失败："+e.getMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	/**
	 * 上报临时资讯
	 */
	public String updateReportInfotemp(String tIds) {
		
		String jsonString = "";
		try {
			String[] infos = tIds.split(",");
			int w = 0;
			for (int i = 0; i < infos.length; i++) {
				TInfotemp infotemp = infotempDao.getInfotempById(Long.parseLong(infos[i]));
				TInfo info = null;
				TInfoRecord infoRecord = null;
				if(infotemp!=null){
					info = new TInfo();
					//把临时资讯添加到资讯列表中
					info.setInfoId((long)infoDao.getTableSequence("TB_INFO"));
					info.setTypeId(infotemp.getTypeId());
					info.setTitle(infotemp.getTitle());
					info.setContent(infotemp.getContent());
					info.setCrttime(DateUtil.formatDate());
					info.setRemark(infotemp.getRemark());
					info.setAudiDate("");
					info.setSysCode(0l);
					info.setRsts(0l);
					LoginUser loginUser = (LoginUser) ServletActionContext.getRequest ().getSession ().getAttribute ( "loginUser" );
					info.setUserId(loginUser.getUserId());
					//保存资讯
					infoDao.save(info);
					//把临时资讯添加到资讯上报表中
					infoRecord = new TInfoRecord();
					BeanUtils.copyProperties(infotemp, infoRecord);
					infoRecordDao.save(infoRecord);
					//删除临时资讯表中的记录
					infotempDao.delete(infotemp);
					w++;
				}				
			}
			jsonString = "操作成功,共上报" + w + "记录!";
		} catch (Exception e) {
			jsonString = "上报临时失败："+e.getMessage();
			logger.error(jsonString);
			e.printStackTrace();
		}
		return jsonString;
	}
	/**
	 * 修改临时资讯
	 */
	public String updateInfotemp(TInfotemp infotemp) {
		String jsonMsg="修改信息成功";
		 try {
			 infotempDao.update(infotemp);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	/**
	 * 添加同步资讯
	 */
	public void appendInfoTemp(List<TInfotemp> infoList) {
		for(TInfotemp info:infoList){
			info.setTid(infotempDao.getTableSequence("tb_infotemp")+0l);
			infotempDao.save(info);
		}
	}
	
	
}
