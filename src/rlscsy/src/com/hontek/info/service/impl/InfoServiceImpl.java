package com.hontek.info.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.info.dao.InfoDao;
import com.hontek.info.dao.InfoTypeDao;
import com.hontek.info.pojo.TInfo;
import com.hontek.info.service.inter.InfoServiceInter;
/**
 * 资讯类 service
 * @author qql
 *
 */
public class InfoServiceImpl extends BaseServiceImpl implements InfoServiceInter{
     
	private InfoDao infoDao;
	private InfoTypeDao infoTypeDao;

	Logger logger = Logger.getLogger(this.getClass());
	
	
	public InfoDao getInfoDao() {
		return infoDao;
	}
	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public InfoTypeDao getInfoTypeDao() {
		return infoTypeDao;
	}
	public void setInfoTypeDao(InfoTypeDao infoTypeDao) {
		this.infoTypeDao = infoTypeDao;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	/**
	 * 添加资讯
	 */
	public String addInfo(TInfo info) {
		String jsonMsg = "添加信息成功!";
		try{
			info.setInfoId(infoDao.getTableSequence("tb_info")+0l);
			if(info.getRsts()==null){
				info.setRsts(0l);
			}
			
			infoDao.save(info);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加信息失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	
	
	/**
	 * 删除资讯
	 * @param ids
	 * @return
	 */
	public String deleteInfo(String id) {
		String jsonString = "";
		try {
			String[] infos = id.split(",");
			int w = 0;
			for (int i = 0; i < infos.length; i++) {
				TInfo info = infoDao.getInfoById(Long.parseLong(infos[i]));
				if(info!=null){
					infoDao.delete(info);
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
	 * 修改资讯
	 */
	public String updateInfo(TInfo info) {		
		String jsonMsg="修改信息成功";
		 try {
			 if(info.getRsts()!=0&&info.getAudiDate()==null||"".equals(info.getAudiDate())){
				 info.setAudiDate(DateUtil.formatDate());
			 }
			 infoDao.update(info);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	/**
	 * 资讯列表
	 */
	
	
	public String findInfoList(TInfo info,int page,int rows,String sort,String order) {
		//拼接查询条件
		StringBuffer condition = new StringBuffer("");
		if(info!=null){
			Long infoId = info.getInfoId();
			Long infoType = info.getTypeId();
			String typeName=info.getTypeName();
			String title=info.getTitle();
			String crttime=info.getCrttime();
			Long rsts=info.getRsts();
			Long entId =info.getEntId();
			if(infoId!=null&&infoId!=0l){
				condition.append(" and info.INFO_ID = "+infoId);
			}
			if(infoType!=null&&infoType!=0l){
				condition.append(" and info.TYPE_ID = "+infoType);
			}
			if(typeName!=null&&!"".equals(typeName)){
				condition.append(" and type.TYPE_NAME like '%"+typeName+"%' ");
			}
			if(title!=null&&!"".equals(title)){
				condition.append(" and info.TITLE like '%"+title+"%' ");
			}
			if(crttime!=null&&!"".equals(crttime)){
				condition.append(" and info.CRTTIME = '"+crttime+"' ");
			}
			if(rsts!=null){
				condition.append(" and info.RSTS = "+rsts);
			}
			if(entId!=null&&entId!=0l){
				condition.append(" and info.ENT_ID = "+entId);
			}
		}		
		//添加排序
		condition.append(sortCondtion(sort, order));
		
		String jsonMsg ="查询成功";
		try {
			
			Pager<TInfo>  pager = infoDao.findInfoList(condition.toString(), page, rows);
		 
			jsonMsg = this.createEasyUiJson(pager);
			
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询出现异常!"+e.getMessage();
			logger.debug(jsonMsg);
		}		
		return jsonMsg;
	}
	
	/**
	 * 根据资讯id
	 * 批量审核资讯
	 */
	public String updateAuditInfo(String ids,String rsts){
		String jsonString ="";
			try {
				String[] infos = ids.split(",");
				int w = 0;
				for (int i = 0; i < infos.length; i++) {
					TInfo info = infoDao.getInfoById(Long.parseLong(infos[i]));
					
					if(info!=null){
						TInfo oldInfo = infoDao.get(TInfo.class, info.getInfoId());
						 oldInfo.setRsts(Long.parseLong(rsts));
						 LoginUser loginUser = (LoginUser) ServletActionContext.getRequest ().getSession ().getAttribute ( "loginUser" );
						 oldInfo.setAuditor(loginUser.getNickname());
						 oldInfo.setAudiDate(DateUtil.formatDate());
						 infoDao.update(oldInfo);
						w++;
					}
				}
				jsonString = "操作成功,共审核" + w + "条信息!";
			} catch (Exception e) {
				jsonString = "审核失败："+e.getMessage();
				logger.error(jsonString);
			}
			return jsonString;
	}
	
	/**
	 * 根据条件查询资讯列表
	 */
	public String findNews(String con){
		StringBuffer condition = new StringBuffer("");
		condition.append(con);
		List list = infoDao.findNews(condition.toString());
		return getJSON(list);
	}

}
